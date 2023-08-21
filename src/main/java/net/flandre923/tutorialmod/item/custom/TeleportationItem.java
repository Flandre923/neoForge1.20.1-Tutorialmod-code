package net.flandre923.tutorialmod.item.custom;

import com.ibm.icu.lang.UCharacter;
import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.dimension.MiningDimensionTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.awt.*;

public class TeleportationItem extends Item {
    public static final ResourceKey<Level> MAIN_WORLD_DIMENSION = ResourceKey.create(Registries.DIMENSION,new ResourceLocation("overworld"));
    public static final ResourceKey<Level> MINING_WORLD_DIMENSION = ResourceKey.create(Registries.DIMENSION,new ResourceLocation(TutorialMod.MOD_ID,"mining"));

    public TeleportationItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();

        if (!level.isClientSide) {
            ServerPlayer player = (ServerPlayer) pContext.getPlayer();

            if (player.isPassenger()) {
                player.displayClientMessage(Component.literal("Please dismout before using the teleporter."),true);
                return InteractionResult.FAIL;
            }

            if (player.level().dimension() == MAIN_WORLD_DIMENSION) {
                teleportToMiningWorld(player, player.getOnPos());
            } else if (player.level().dimension() == MINING_WORLD_DIMENSION) {
                teleportToMainWorld(player, player.getOnPos());
            } else {
                player.displayClientMessage(Component.literal("Cannot teleport from this dimension."), true);
                return InteractionResult.FAIL;
            }
            player.getCooldowns().addCooldown(this, 20);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    private void teleportToMiningWorld(ServerPlayer player, BlockPos pos) {
        ServerLevel miningWorld = player.getServer().getLevel(MINING_WORLD_DIMENSION);
        if(miningWorld != null) {
            player.changeDimension(miningWorld,new MiningDimensionTeleporter(pos));
        } else {
            player.displayClientMessage(Component.literal("Mining world not found."), true);
        }
    }

    private void teleportToMainWorld(ServerPlayer player, BlockPos pos) {
        ServerLevel mainWorld = player.getServer().getLevel(MAIN_WORLD_DIMENSION);
        if(mainWorld != null) {
            player.changeDimension(mainWorld,new MiningDimensionTeleporter(pos));
        } else {
            player.displayClientMessage(Component.literal("Main world not found."), true);
        }
    }


    private BlockPos findSafeLocation(ServerLevel world, BlockPos pos) {
        // Find a safe location near pos
        return pos.above();
    }


}