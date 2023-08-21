package net.flandre923.tutorialmod.item;


import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
public class ModCreativeModeTab {

    public static final String TUTORIAL_TAB_STRING = "creativetab.tutorial_tab";
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);
    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("tutorial_tab",
            ()-> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.ZIRCON.get()))
                    .title(Component.translatable(TUTORIAL_TAB_STRING))
                    .displayItems((pParameters, pOutput) -> {
                        // 物品
                        pOutput.accept(ModItems.ZIRCON.get());
                        pOutput.accept(ModItems.RAW_ZIRCON.get());
                        pOutput.accept(ModItems.EIGHT_BALL.get());
                        pOutput.accept(ModItems.BLUEBERRY_SEEDS.get());
                        pOutput.accept(ModItems.BLUEBERRY.get());
                        pOutput.accept(ModItems.SOAP_WATER_BUCKET.get());
                        pOutput.accept(ModItems.TUTORIAL_SWORD.get());
                        pOutput.accept(ModItems.ZIRCON_PICKAXE.get());
                        pOutput.accept(ModItems.EIGHT_BALL.get());
                        pOutput.accept(ModItems.TELEPORTATION_ITEM.get());
                        //方块
                        pOutput.accept(ModBlocks.ZIRCON_BLOCK.get());
                        pOutput.accept(ModBlocks.ZIRCON_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_ZIRCON_ORE.get());
                        pOutput.accept(ModBlocks.JUMPY_BLOCK.get());
                        pOutput.accept(ModBlocks.ZIRCON_LAMP.get());
                        pOutput.accept(ModBlocks.GEM_INFUSING_STATION.get());
                        pOutput.accept(ModBlocks.MY_GENERATOR_BLOCK.get());

                        pOutput.accept(ModBlocks.EBONY_LEAVES.get());
                        pOutput.accept(ModBlocks.EBONY_LOG.get());
                        pOutput.accept(ModBlocks.EBONY_WOOD.get());
                        pOutput.accept(ModBlocks.EBONY_PLANKS.get());
                        pOutput.accept(ModBlocks.STRIPPED_EBONY_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_EBONY_WOOD.get());
                        pOutput.accept(ModBlocks.EBONY_SAPLING.get());
                        // 原版
                        pOutput.accept(Items.DIAMOND);
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
