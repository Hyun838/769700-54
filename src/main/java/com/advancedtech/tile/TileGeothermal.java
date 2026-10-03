package com.advancedtech.tile;

import com.advancedtech.energy.EnergyTier;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

/** Геотермальный генератор: 11 FE/т за каждый блок лавы, соприкасающийся с ним (до 32 FE/т). Без выбросов. */
public class TileGeothermal extends TileEntityMachine {
    public static final int PER_LAVA = 11;

    public TileGeothermal() { super(0, EnergyTier.LV); }

    @Override
    public void update() {
        if (world.isRemote) return;
        int lava = 0;
        for (EnumFacing f : EnumFacing.VALUES)
            if (world.getBlockState(pos.offset(f)).getMaterial() == Material.LAVA) lava++;
        if (lava > 0) energy.generate(lava * PER_LAVA);
        progress = lava;
        pushEnergy();
        if (world.getTotalWorldTime() % 20 == 0) markDirty();
    }

    @Override protected void tickHeat(boolean working) { }
    @Override public boolean acceptsEnergy() { return false; }
    @Override public boolean canProcess() { return false; }
    @Override public void finishProcess() { }
    @Override public int getBaseProcessTime() { return 1; }
    @Override public int getProcessTime() { return 6; }
    @Override public int getEnergyPerTick() { return 0; }
    @Override public boolean isItemValidForSlot(int slot, ItemStack stack) { return false; }
    @Override public int[][] getSlotLayout() { return new int[0][]; }
}
