package net.flandre923.tutorialmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class MyGeneratorBlockEntity extends BlockEntity implements IEnergyStorage {
    private EnergyStorage energyStorage = new EnergyStorage(32000, 64);

    public MyGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MY_GENERATOR.get(), pPos, pBlockState);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return this.energyStorage.receiveEnergy(maxReceive,simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return this.energyStorage.extractEnergy(maxExtract,simulate);
    }

    @Override
    public int getEnergyStored() {
        return this.energyStorage.getEnergyStored();
    }


    @Override
    public int getMaxEnergyStored() {
        return this.energyStorage.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }



    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return LazyOptional.of(() -> energyStorage).cast();
        }
        return super.getCapability(cap, side);
    }


    public static void tick(Level level, BlockPos pos, BlockState state, MyGeneratorBlockEntity pEntity) {
        if(level.isClientSide){
            return;
        }
        pEntity.energyStorage.receiveEnergy(64,false);
        setChanged(level,pos,state);
    }

    public boolean getIsGenerating() {
        return this.energyStorage.getEnergyStored() < this.energyStorage.getMaxEnergyStored();
    }
}
