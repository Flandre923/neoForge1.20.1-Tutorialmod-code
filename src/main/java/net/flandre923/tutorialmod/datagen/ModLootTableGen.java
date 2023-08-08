package net.flandre923.tutorialmod.datagen;

import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.block.ModBlocks;
import net.flandre923.tutorialmod.block.custom.BlueberryCropBlock;
import net.flandre923.tutorialmod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.stream.Collectors;


public class ModLootTableGen extends VanillaBlockLoot  {

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ZIRCON_BLOCK.get());
        dropSelf(ModBlocks.JUMPY_BLOCK.get());
        dropSelf(ModBlocks.ZIRCON_LAMP.get());
        dropSelf(ModBlocks.MY_GENERATOR_BLOCK.get());
        dropSelf(ModBlocks.SOAP_WATER_BLOCK.get());
        dropSelf(ModBlocks.GEM_INFUSING_STATION.get());
        dropSelf(ModBlocks.JASMINE.get());
        dropSelf(ModBlocks.POTTED_JASMINE.get());

        this.dropSelf(ModBlocks.EBONY_LOG.get());
        this.dropSelf(ModBlocks.EBONY_WOOD.get());
        this.dropSelf(ModBlocks.EBONY_PLANKS.get());
        this.dropSelf(ModBlocks.STRIPPED_EBONY_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_EBONY_LOG.get());
        this.dropSelf(ModBlocks.EBONY_SAPLING.get());
        this.add(ModBlocks.EBONY_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.EBONY_LEAVES.get(), NORMAL_LEAVES_SAPLING_CHANCES));


        add(ModBlocks.ZIRCON_ORE.get(), this::createZirconOreDrops);
        add(ModBlocks.DEEPSLATE_ZIRCON_ORE.get(),this::createZirconOreDrops);
        add(ModBlocks.ENDSTONE_ZIRCON_ORE.get(),this::createZirconOreDrops);
        add(ModBlocks.NETHERRACK_ZIRCON_ORE.get(),this::createZirconOreDrops);
        add(ModBlocks.BLUEBERRY_CROP.get(),pBlock->{
            return createCropDrops(ModBlocks.BLUEBERRY_CROP.get(),ModItems.BLUEBERRY.get(),ModItems.BLUEBERRY_SEEDS.get(), blueberryCropConditionBulider);
        });
    }

    LootItemCondition.Builder blueberryCropConditionBulider = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BLUEBERRY_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueberryCropBlock.AGE, 6));


    protected LootTable.Builder createZirconOreDrops(Block pBlock) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(ModItems.RAW_ZIRCON.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 9.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getEntries().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(TutorialMod.MOD_ID))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}
