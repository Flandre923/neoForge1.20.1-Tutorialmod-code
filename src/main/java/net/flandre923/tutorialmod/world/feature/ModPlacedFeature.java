package net.flandre923.tutorialmod.world.feature;

import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeature {

    public static final ResourceKey<PlacedFeature> BLACK_ZIRCON_PLACED_KEY = createKey("black_zircon_placed");
    public static final ResourceKey<PlacedFeature> END_BLACK_ZIRCON_PLACED_KEY = createKey("end_black_zircon_placed");
    public static final ResourceKey<PlacedFeature> NETHER_BLACK_ZIRCON_PLACED_KEY = createKey("nether_black_zircon_placed");
    public static final ResourceKey<PlacedFeature> EBONY_PLACED_KEY = createKey("ebony_placed");
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, BLACK_ZIRCON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfigureFeature.OVERWORLD_BLACK_ZIRCON_ORE_KEY),
                ModOrePlacement.commonOrePlacement(16, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.absolute(80))));
        register(context, NETHER_BLACK_ZIRCON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfigureFeature.NETHER_BLACK_ZIRCON_ORE_KEY),
                ModOrePlacement.commonOrePlacement(9, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.absolute(80))));
        register(context, END_BLACK_ZIRCON_PLACED_KEY, configuredFeatures.getOrThrow(ModConfigureFeature.END_BLACK_ZIRCON_ORE_KEY),
                ModOrePlacement.commonOrePlacement(9, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.absolute(80))));

        register(context, EBONY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfigureFeature.EBONY_KEY),
                        VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), ModBlocks.EBONY_SAPLING.get()));
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(TutorialMod.MOD_ID, name));
    }
    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
