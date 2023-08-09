package net.flandre923.tutorialmod;

import com.mojang.logging.LogUtils;
import net.flandre923.tutorialmod.block.ModBlocks;
import net.flandre923.tutorialmod.block.entity.ModBlockEntities;
import net.flandre923.tutorialmod.fluid.ModFluidTypes;
import net.flandre923.tutorialmod.fluid.ModFluids;
import net.flandre923.tutorialmod.item.ModCreativeModeTab;
import net.flandre923.tutorialmod.item.ModItems;
import net.flandre923.tutorialmod.loot.ModLootModifiers;
import net.flandre923.tutorialmod.networking.ModMessages;
import net.flandre923.tutorialmod.painting.ModPaintings;
import net.flandre923.tutorialmod.recipe.ModRecipes;
import net.flandre923.tutorialmod.screen.GemInfusingStationScreen;
import net.flandre923.tutorialmod.screen.ModMenuTypes;
import net.flandre923.tutorialmod.villager.ModVillagers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TutorialMod.MOD_ID)
public class TutorialMod
{
    public static final String MOD_ID = "tutorialmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TutorialMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // item
        ModItems.register(modEventBus);
        // block
        ModBlocks.register(modEventBus);
        // item group
        ModCreativeModeTab.register(modEventBus);
        // villager
        ModVillagers.register(modEventBus);
        // painting
        ModPaintings.register(modEventBus);
        // modmessage
        ModMessages.register();
        // fluid
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        // blockentity
        ModBlockEntities.register(modEventBus);
        // screen
        ModMenuTypes.register(modEventBus);
        //recipes
        ModRecipes.register(modEventBus);
        // loot table
        ModLootModifiers.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(()->{
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.JASMINE.getId(),ModBlocks.POTTED_JASMINE);
        });
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SOAP_WATER.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOAP_WATER.get(),RenderType.translucent());
            // scrren
            MenuScreens.register(ModMenuTypes.GEM_INFUSING_STATION_MENU.get(), GemInfusingStationScreen::new);
        }
    }
}
