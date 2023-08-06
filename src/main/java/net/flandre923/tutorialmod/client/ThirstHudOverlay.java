package net.flandre923.tutorialmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.flandre923.tutorialmod.TutorialMod;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ThirstHudOverlay {
    private static final ResourceLocation FILLED_THIRST = new ResourceLocation(TutorialMod.MOD_ID,
            "textures/thirst/filled_thirst.png");
    private static final ResourceLocation EMPTY_THIRST = new ResourceLocation(TutorialMod.MOD_ID,
            "textures/thirst/empty_thirst.png");

    public static final IGuiOverlay HUD_THIRST = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_THIRST);
//        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        for(int i = 0; i < 10; i++) {
            guiGraphics.blit(EMPTY_THIRST,x - 94 + (i * 9), y - 54,90,0,0,12,12,
                    12,12);
        }

        RenderSystem.setShaderTexture(0, FILLED_THIRST);
        for(int i = 0; i < 10; i++) {
            if(ClientThirstData.getPlayerThirst() > i) {
                guiGraphics.blit(FILLED_THIRST,x - 94 + (i * 9),y - 54,90,0,0,12,12,
                        12,12);
            } else {
                break;
            }
        }
    };
}