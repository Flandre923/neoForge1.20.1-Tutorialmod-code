package net.flandre923.tutorialmod.datagen;

import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.block.ModBlocks;
import net.flandre923.tutorialmod.fluid.ModFluids;
import net.flandre923.tutorialmod.item.ModCreativeModeTab;
import net.flandre923.tutorialmod.item.ModItems;
import net.flandre923.tutorialmod.networking.packet.DrinkWaterC2SPacket;
import net.flandre923.tutorialmod.util.KeyBinding;
import net.flandre923.tutorialmod.villager.ModVillagers;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class ModLangGen extends LanguageProvider {
    public ModLangGen(PackOutput output, String locale) {
        super(output, TutorialMod.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add(ModItems.ZIRCON.get(),"Sapphire");
        add(ModItems.RAW_ZIRCON.get(),"RawSapphire");
        add(ModItems.EIGHT_BALL.get(),"Eight Ball" );
        add(ModItems.BLUEBERRY_SEEDS.get(),"Blueberry Seed");
        add(ModItems.BLUEBERRY.get(),"Blueberry");
        add(ModItems.SOAP_WATER_BUCKET.get(),"Soap Water Bucket");
        add(ModItems.TUTORIAL_SWORD.get(),"Tutorial Mod Sword");
        add(ModItems.ZIRCON_PICKAXE.get(),"Zircon Pickaxe");
        add(ModItems.CHOMPER_SPAWN_EGG.get(),"Chomper Egg");
        add(ModCreativeModeTab.TUTORIAL_TAB_STRING,"TutorialMod Tab");
        //方块
        add(ModBlocks.ZIRCON_BLOCK.get(),"Zircon Block");
        add(ModBlocks.ZIRCON_ORE.get(),"Zircon Ore");
        add(ModBlocks.DEEPSLATE_ZIRCON_ORE.get(), "Deepslate Zircon Ore");
        add(ModBlocks.JUMPY_BLOCK.get(), "Jumpy block");
        add(ModBlocks.ZIRCON_LAMP.get(), "Zircon Lamp");
        add(ModBlocks.ENDSTONE_ZIRCON_ORE.get(), "End Zircon Ore");
        add(ModBlocks.NETHERRACK_ZIRCON_ORE.get(), "Nether Zircon Ore");
        add(ModBlocks.GEM_INFUSING_STATION.get(), "Gem Infusing Station");
        add(ModBlocks.MY_GENERATOR_BLOCK.get(), "My Generator");
        add(ModBlocks.JASMINE.get(),"Jasmine");
        add(ModBlocks.POTTED_JASMINE.get(),"Potted Jasmine" );
        // 村名
        addVillager(ModVillagers.JUMP_MASTER.get(),"Jumpy Master");
        // 按键
        add(KeyBinding.KEY_DRINK_WATER,"Drinking Water Key");
        add(KeyBinding.KEY_CATEGORY_TUTORIAL,"Tutorial Category");
        // packet
        add(DrinkWaterC2SPacket.MESSAGE_DRINK_WATER,"Drinking Water");
        add(DrinkWaterC2SPacket.MESSAGE_NO_WATER,"No water nearby");
        // 液体渲染
        add("tutorialmod.tooltip.liquid.amount.with.capacity","%s / %s mB");
        add("tutorialmod.tooltip.liquid.amount","%s mB");

    }

    public void addVillager(VillagerProfession villagerProfession, String name) {
        add("entity.minecraft.villager."+ForgeRegistries.VILLAGER_PROFESSIONS.getKey(villagerProfession).toLanguageKey(), name);
    }
}
