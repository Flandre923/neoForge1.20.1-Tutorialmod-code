package net.flandre923.tutorialmod.datagen.provider;

import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.painting.ModPaintings;
import net.flandre923.tutorialmod.villager.ModVillagers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModPlaceableTagProvider extends PaintingVariantTagsProvider {

    public ModPlaceableTagProvider(PackOutput p_255750_, CompletableFuture<HolderLookup.Provider> p_256184_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_255750_, p_256184_, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
//        super.addTags(pProvider);
        tag(PaintingVariantTags.PLACEABLE)
                .add(ModPaintings.WANDERER.getKey())
                .add(ModPaintings.PLANT.getKey())
                .add(ModPaintings.SUNSET.getKey());
;
    }
}
