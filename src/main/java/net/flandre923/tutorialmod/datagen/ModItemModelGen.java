package net.flandre923.tutorialmod.datagen;

import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.block.ModBlocks;
import net.flandre923.tutorialmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItemModelGen extends ItemModelProvider{
    public static final String GENERATED = "item/generated";
    public static final String HANDHELD = "item/handheld";
    public static final String EGG_TEMPLATE = "item/template_spawn_egg";

    public ModItemModelGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        itemGeneratedModel(ModItems.ZIRCON.get(), resourceItem(itemName(ModItems.ZIRCON.get())));
        itemGeneratedModel(ModItems.RAW_ZIRCON.get(), resourceItem(itemName(ModItems.RAW_ZIRCON.get())));
        itemGeneratedModel(ModItems.EIGHT_BALL.get(),resourceItem(itemName(ModItems.EIGHT_BALL.get())));
        itemGeneratedModel(ModItems.BLUEBERRY_SEEDS.get(),resourceItem(itemName(ModItems.BLUEBERRY_SEEDS.get())));
        itemGeneratedModel(ModItems.BLUEBERRY.get(),resourceItem(itemName(ModItems.BLUEBERRY.get())));
        // 添加流体捅
        itemGeneratedModel(ModItems.SOAP_WATER_BUCKET.get(),resourceItem(itemName(ModItems.SOAP_WATER_BUCKET.get())));
        itemGeneratedModel(ModItems.ZIRCON_PICKAXE.get(),resourceItem(itemName(ModItems.ZIRCON_PICKAXE.get())));
        itemGeneratedModel(ModBlocks.EBONY_SAPLING.get().asItem(),resourceItem(itemName(ModBlocks.EBONY_SAPLING.get().asItem())));

        eggItem(ModItems.CHOMPER_SPAWN_EGG.get());
    }

    private void saplingItem(Item item,ResourceLocation texture) {
         withExistingParent(itemName(item),
                GENERATED).texture("layer0", texture);
    }
    private void eggItem(Item item) {
        withExistingParent(itemName(item),
                EGG_TEMPLATE);
    }

    public void itemGeneratedModel(Item item, ResourceLocation texture) {
        withExistingParent(itemName(item), GENERATED).texture("layer0", texture);
    }

    private String itemName(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return new ResourceLocation(TutorialMod.MOD_ID, "block/" + path);
    }

    public ResourceLocation resourceItem(String path) {
        return new ResourceLocation(TutorialMod.MOD_ID, "item/" + path);
    }
}
