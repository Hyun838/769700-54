package com.advancedtech.tile;

import com.advancedtech.block.BlockMachine;
import com.advancedtech.energy.EnergyTier;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Накопитель энергии (MV, 200 000 FE). Принимает энергию со всех сторон, КРОМЕ лицевой,
 * а лицевая сторона - выход (отдаёт до 128 FE/т в соседний кабель или машину).
 */
public class TileBatteryBox extends TileEntityMachine {
    public static final int CAPACITY = 200_000;

    private final View inView = new View(true);
    private final View outView = new View(false);

    public TileBatteryBox() {
        super(0, EnergyTier.MV);
        energy.setCapacityOverride(CAPACITY);
    }

    private class View implements IEnergyStorage {
        private final boolean input;
        View(boolean input) { this.input = input; }
        @Override public int receiveEnergy(int max, boolean simulate) { return input ? energy.receiveEnergy(max, simulate) : 0; }
        @Override public int extractEnergy(int max, boolean simulate) { return input ? 0 : energy.extractEnergy(max, simulate); }
        @Override public int getEnergyStored() { return energy.getEnergyStored(); }
        @Override public int getMaxEnergyStored() { return energy.getMaxEnergyStored(); }
        @Override public boolean canExtract() { return !input; }
        @Override public boolean canReceive() { return input; }
    }

    private EnumFacing outFace() {
        if (world != null) {
            IBlockState s = world.getBlockState(pos);
            if (s.getBlock() instanceof BlockMachine) return s.getValue(BlockMachine.FACING);
        }
        return EnumFacing.NORTH;
    }

    @Override
    public void update() {
        if (world.isRemote) return;
        EnumFacing out = outFace();
        TileEntity te = world.getTileEntity(pos.offset(out));
        if (te != null && !(te instanceof TileEntityMachine && !((TileEntityMachine) te).acceptsEnergy())) {
            IEnergyStorage target = te.getCapability(CapabilityEnergy.ENERGY, out.getOpposite());
            if (target != null && target.canReceive()) {
                int amount = Math.min(energy.getEnergyStored(), energy.getTier().maxTransfer);
                if (amount > 0) energy.consume(target.receiveEnergy(amount, false));
            }
        }
        if (world.getTotalWorldTime() % 20 == 0) markDirty();
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> cap, @Nullable EnumFacing f) {
        if (cap == CapabilityEnergy.ENERGY) {
            if (f == null) return (T) inView;
            return (T) (f == outFace() ? outView : inView);
        }
        return super.getCapability(cap, f);
    }

    @Override
    public void readFromNBT(NBTTagCompound t) {
        super.readFromNBT(t);
        energy.setCapacityOverride(CAPACITY);
    }

    @Override protected void tickHeat(boolean working) { }
    @Override public boolean canProcess() { return false; }
    @Override public void finishProcess() { }
    @Override public int getBaseProcessTime() { return 1; }
    @Override public int getEnergyPerTick() { return 0; }
    @Override public boolean isItemValidForSlot(int slot, ItemStack stack) { return false; }
    @Override public int[][] getSlotLayout() { return new int[0][]; }
}
