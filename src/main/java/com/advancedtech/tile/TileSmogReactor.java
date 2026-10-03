package com.advancedtech.tile;

import com.advancedtech.energy.EnergyTier;
import com.advancedtech.pollution.PollutionManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Смог-реактор: перерабатывает загрязнение чанка в энергию (30 FE/т) и очищает воздух.
 * Чем грязнее чанк - тем дольше он работает. Идеальная пара для топливных генераторов.
 */
public class TileSmogReactor extends TileEntityMachine {
    public static final int OUTPUT = 30;
    public static final int TICKS_PER_UNIT = 10;

    private int burnLeft;

    public TileSmogReactor() { super(0, EnergyTier.LV); }

    @Override
    public void update() {
        if (world.isRemote) return;
        if (burnLeft <= 0 && energy.getEnergyStored() + OUTPUT * TICKS_PER_UNIT <= energy.getMaxEnergyStored()) {
            float got = PollutionManager.drain(world, pos, 1.0f);
            if (got >= 0.5f) burnLeft = Math.round(got * TICKS_PER_UNIT);
        }
        if (burnLeft > 0) {
            energy.generate(OUTPUT);
            burnLeft--;
        }
        progress = burnLeft;
        pushEnergy();
        if (world.getTotalWorldTime() % 20 == 0) markDirty();
    }

    @Override protected void tickHeat(boolean working) { }
    @Override public boolean acceptsEnergy() { return false; }
    @Override public boolean canProcess() { return false; }
    @Override public void finishProcess() { }
    @Override public int getBaseProcessTime() { return 1; }
    @Override public int getProcessTime() { return TICKS_PER_UNIT; }
    @Override public int getEnergyPerTick() { return 0; }
    @Override public boolean isItemValidForSlot(int slot, ItemStack stack) { return false; }
    @Override public int[][] getSlotLayout() { return new int[0][]; }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound t) {
        super.writeToNBT(t);
        t.setInteger("BurnLeft", burnLeft);
        return t;
    }

    @Override
    public void readFromNBT(NBTTagCompound t) {
        super.readFromNBT(t);
        burnLeft = t.getInteger("BurnLeft");
    }
}
