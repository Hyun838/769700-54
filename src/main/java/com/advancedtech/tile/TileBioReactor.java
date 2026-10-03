package com.advancedtech.tile;

import com.advancedtech.config.ATConfig;
import com.advancedtech.energy.EnergyTier;
import com.advancedtech.pollution.PollutionManager;
import com.advancedtech.registry.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Биореактор: «сжигает» биомассу (от мутантов), гнилую плоть, паучьи глаза и слизь.
 * 38 FE/т (MV). Почти не загрязняет, в отличие от топливного генератора.
 */
public class TileBioReactor extends TileEntityMachine {
    public static final int OUTPUT = 38;

    private int burnLeft, burnMax, emitTimer;

    public TileBioReactor() { super(1, EnergyTier.MV); }

    public static int fuelTime(ItemStack s) {
        if (s.isEmpty()) return 0;
        if (s.getItem() == ModItems.BIOMASS) return 1600;
        if (s.getItem() == Items.SLIME_BALL) return 800;
        if (s.getItem() == Items.ROTTEN_FLESH) return 600;
        if (s.getItem() == Items.SPIDER_EYE) return 400;
        return 0;
    }

    @Override
    public void update() {
        if (world.isRemote) return;
        if (burnLeft <= 0) {
            ItemStack fuel = inventory.getStackInSlot(0);
            int t = fuelTime(fuel);
            if (t > 0 && energy.getEnergyStored() + OUTPUT <= energy.getMaxEnergyStored()) {
                burnMax = burnLeft = t;
                fuel.shrink(1);
                markDirty();
            }
        }
        if (burnLeft > 0 && energy.getEnergyStored() + OUTPUT <= energy.getMaxEnergyStored()) {
            energy.generate(OUTPUT);
            burnLeft--;
            if (++emitTimer >= 40) {
                emitTimer = 0;
                if (ATConfig.enablePollution) PollutionManager.add(world, pos, 0.4f);
            }
        }
        progress = burnMax - burnLeft;
        pushEnergy();
        if (world.getTotalWorldTime() % 20 == 0) markDirty();
    }

    @Override protected void tickHeat(boolean working) { }
    @Override public boolean acceptsEnergy() { return false; }
    @Override public boolean canProcess() { return false; }
    @Override public void finishProcess() { }
    @Override public int getBaseProcessTime() { return 1; }
    @Override public int getProcessTime() { return Math.max(1, burnMax); }
    @Override public int getEnergyPerTick() { return 0; }
    @Override public boolean isItemValidForSlot(int slot, ItemStack stack) { return slot == 0 && fuelTime(stack) > 0; }
    @Override public int[][] getSlotLayout() { return new int[][]{{80, 35}}; }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound t) {
        super.writeToNBT(t);
        t.setInteger("BurnLeft", burnLeft);
        t.setInteger("BurnMax", burnMax);
        return t;
    }

    @Override
    public void readFromNBT(NBTTagCompound t) {
        super.readFromNBT(t);
        burnLeft = t.getInteger("BurnLeft");
        burnMax = t.getInteger("BurnMax");
    }
}
