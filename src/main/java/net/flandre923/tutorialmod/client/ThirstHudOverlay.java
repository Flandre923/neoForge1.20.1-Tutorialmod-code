package net.flandre923.tutorialmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.flandre923.tutorialmod.TutorialMod;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

/**
 * 在添加一个这个类，这个类是负责给client绘制hud的类
 */
public class ThirstHudOverlay {
    // 绘制的贴图的位置
    private static final ResourceLocation FILLED_THIRST = new ResourceLocation(TutorialMod.MOD_ID,
            "textures/thirst/filled_thirst.png");
    // 绘制的贴图的位置，包含了空瓶子和有水的瓶子
    private static final ResourceLocation EMPTY_THIRST = new ResourceLocation(TutorialMod.MOD_ID,
            "textures/thirst/empty_thirst.png");
    // 通过这个属性进行绘制，这个是一个IguiOverLay的接口，实现这个接口，我们等会注册他。
    // 通过lammbd表达式实现。
    public static final IGuiOverlay HUD_THIRST = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        // 通过宽高获得绘制的x，y
        int x = screenWidth / 2;
        int y = screenHeight;
        //设置绘制的信息
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_THIRST);
        // 绘制我们的内容 这里绘制的是空水瓶
        for(int i = 0; i < 10; i++) {
            // 贴图，x坐标，y坐标，z坐标（图层 越高越不容易被遮盖）
            guiGraphics.blit(EMPTY_THIRST,x - 94 + (i * 9), y - 54,90,0,0,12,12,
                    12,12);
        }

        RenderSystem.setShaderTexture(0, FILLED_THIRST);
        for(int i = 0; i < 10; i++) {
            if(ClientThirstData.getPlayerThirst() > i) {
                // 根据饥渴值绘制有水瓶的图标。
                guiGraphics.blit(FILLED_THIRST,x - 94 + (i * 9),y - 54,90,0,0,12,12,
                        12,12);
            } else {
                break;
            }
        }
    };
}