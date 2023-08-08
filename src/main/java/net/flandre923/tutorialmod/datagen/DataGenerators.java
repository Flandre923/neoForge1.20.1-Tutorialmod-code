package net.flandre923.tutorialmod.datagen;

import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.datagen.provider.ModPlaceableTagProvider;
import net.flandre923.tutorialmod.datagen.provider.ModPoiTypeTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID,bus=Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        ExistingFileHelper helper = event.getExistingFileHelper();
        // neoforge doc
        generator.addProvider(event.includeClient(),new ModItemModelGen(output, helper));
        generator.addProvider(event.includeClient(),new ModLangGen(output,"en_us"));
        generator.addProvider(event.includeClient(),new ModBlockModelGen(output,helper));

        //
        ModBlockTagGen blockModelGen = new ModBlockTagGen(output,lookupProvider,event.getExistingFileHelper());
        generator.addProvider(event.includeServer(),blockModelGen);
        generator.addProvider(event.includeServer(),new ModRecipesGen(output));
        generator.addProvider(event.includeServer(),new ModPoiTypeTagProvider(output,lookupProvider,event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(),new ModPlaceableTagProvider(output,lookupProvider,event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(),new ModWorldGen(output,lookupProvider));
        generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModLootTableGen::new, LootContextParamSets.BLOCK))));
    }
}
