package net.flandre923.tutorialmod.datagen;

import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGen extends BlockTagsProvider {
    public ModBlockTagGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // 需要稿子
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ZIRCON_BLOCK.get())
                .add(ModBlocks.ZIRCON_ORE.get())
                .add(ModBlocks.DEEPSLATE_ZIRCON_ORE.get());
        // 需要铁工具以上
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ZIRCON_BLOCK.get())
                .add(ModBlocks.ZIRCON_ORE.get())
                .add(ModBlocks.DEEPSLATE_ZIRCON_ORE.get());



    }
}
