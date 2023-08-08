package net.flandre923.tutorialmod.world.feature;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModConfigureFeature {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_BLACK_ZIRCON_ORE_KEY = registerKey("black_zircon_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_BLACK_ZIRCON_ORE_KEY = registerKey("end_black_zircon_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_BLACK_ZIRCON_ORE_KEY = registerKey("nether_black_zircon_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EBONY_KEY = registerKey("ebony");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZIRCON_GEODE_KEY = registerKey("zircon_geode");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endstoneReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldBlackZirconOres = List.of(OreConfiguration.target(stoneReplaceables,
                        ModBlocks.ZIRCON_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_ZIRCON_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_BLACK_ZIRCON_ORE_KEY, Feature.ORE, new OreConfiguration(overworldBlackZirconOres, 9));
        register(context, END_BLACK_ZIRCON_ORE_KEY, Feature.ORE, new OreConfiguration(endstoneReplaceables,
                ModBlocks.ENDSTONE_ZIRCON_ORE.get().defaultBlockState(), 9));
        register(context, NETHER_BLACK_ZIRCON_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables,
                ModBlocks.NETHERRACK_ZIRCON_ORE.get().defaultBlockState(), 9));


        register(context, EBONY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.EBONY_LOG.get()),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(ModBlocks.EBONY_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context,ZIRCON_GEODE_KEY,Feature.GEODE, new GeodeConfiguration(new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR),
                BlockStateProvider.simple(Blocks.DEEPSLATE),
                BlockStateProvider.simple(ModBlocks.ZIRCON_ORE.get()),
                BlockStateProvider.simple(Blocks.DIRT),
                BlockStateProvider.simple(Blocks.EMERALD_BLOCK),
                List.of(ModBlocks.ZIRCON_BLOCK.get().defaultBlockState()),
                BlockTags.FEATURES_CANNOT_REPLACE , BlockTags.GEODE_INVALID_BLOCKS),
                new GeodeLayerSettings(1.7D, 1.2D, 2.5D, 3.5D),
                new GeodeCrackSettings(0.25D, 1.5D, 1), 0.5D, 0.1D,
                true, UniformInt.of(3, 8),
                UniformInt.of(2, 6), UniformInt.of(1, 2),
                -18, 18, 0.075D, 1));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(TutorialMod.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
