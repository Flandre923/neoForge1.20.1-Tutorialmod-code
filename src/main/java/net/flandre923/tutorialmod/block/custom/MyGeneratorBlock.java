package net.flandre923.tutorialmod.block.custom;

import net.flandre923.tutorialmod.block.ModBlocks;
import net.flandre923.tutorialmod.block.entity.ModBlockEntities;
import net.flandre923.tutorialmod.block.entity.MyGeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class MyGeneratorBlock extends BaseEntityBlock {
    public MyGeneratorBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof MyGeneratorBlockEntity) {
                MyGeneratorBlockEntity genBlockEntity = (MyGeneratorBlockEntity)blockEntity;
                int energyStored = genBlockEntity.getEnergyStored();
                boolean isGenerating = genBlockEntity.getIsGenerating();

                pPlayer.sendSystemMessage(Component.literal("Energy:"+energyStored));
                pPlayer.sendSystemMessage(Component.literal("Generating:"+isGenerating));
            }
        }

        return InteractionResult.SUCCESS;
    }


    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new MyGeneratorBlockEntity(pPos,pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType,
                ModBlockEntities.MY_GENERATOR.get(),
                MyGeneratorBlockEntity::tick);
    }
}
