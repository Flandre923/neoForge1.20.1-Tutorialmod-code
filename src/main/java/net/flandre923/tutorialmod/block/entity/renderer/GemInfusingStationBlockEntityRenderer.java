package net.flandre923.tutorialmod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.flandre923.tutorialmod.block.custom.GemInfusingStationBlock;
import net.flandre923.tutorialmod.block.entity.GemInfusingStationBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class GemInfusingStationBlockEntityRenderer implements BlockEntityRenderer<GemInfusingStationBlockEntity> {
    public GemInfusingStationBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(GemInfusingStationBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = pBlockEntity.getRenderStack();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.65f, 0.5f);
        pPoseStack.scale(0.25f, 0.25f, 0.25f);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(90));

        switch (pBlockEntity.getBlockState().getValue(GemInfusingStationBlock.FACING)) {
            case NORTH -> pPoseStack.mulPose(Axis.ZP.rotationDegrees(0));
            case EAST -> pPoseStack.mulPose(Axis.ZP.rotationDegrees(90));
            case SOUTH -> pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
            case WEST -> pPoseStack.mulPose(Axis.ZP.rotationDegrees(270));
        }

        itemRenderer.renderStatic(itemStack, ItemDisplayContext.GUI,getLightLevel(pBlockEntity.getLevel(),pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY,pPoseStack,pBufferSource,pBlockEntity.getLevel(),1);

        pPoseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}