package net.flandre923.tutorialmod.datagen;

import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.item.ModItems;
import net.flandre923.tutorialmod.world.feature.ModConfigureFeature;
import net.flandre923.tutorialmod.world.feature.ModPlacedFeature;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGen extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModConfigureFeature::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeature::bootstrap);

    public ModWorldGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER,Set.of(TutorialMod.MOD_ID));
    }
}
