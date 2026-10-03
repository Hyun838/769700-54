package com.advancedtech.tile;

import com.advancedtech.energy.EnergyTier;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Грозовой коллектор (катушка Теслы). Во время грозы притягивает молнии и запасает по 60 000 FE за удар.
 * Буфер 500 000 FE, отдаёт 512 FE/т (HV). Стоять рядом во время грозы опасно!
 */
public class TileStormCollector extends TileEntityMachine {
    public static final int CAPACITY = 500_000;
    public static final int PER_STRIKE = 60_000;

    public TileStormCollector() {
        super(0, EnergyTier.HV);
        energy.setCapacityOverride(CAPACITY);
    }

    @Override
    public void update() {
        if (world.isRemote) return;
        if (world.isThundering() && world.canSeeSky(pos.up()) && world.rand.nextInt(500) == 0
                && energy.getEnergyStored() + PER_STRIKE <= energy.getMaxEnergyStored()) {
            world.addWeatherEffect(new EntityLightningBolt(world, pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D, true));
            energy.generate(PER_STRIKE);
            markDirty();
        }
        pushEnergy();
        if (world.getTotalWorldTime() % 20 == 0) markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound t) {
        super.readFromNBT(t);
        energy.setCapacityOverride(CAPACITY);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1, pos.getX() + 2, pos.getY() + 3, pos.getZ() + 2);
    }

    @Override protected void tickHeat(boolean working) { }
    @Override public boolean acceptsEnergy() { return false; }
    @Override public boolean canProcess() { return false; }
    @Override public void finishProcess() { }
    @Override public int getBaseProcessTime() { return 1; }
    @Override public int getEnergyPerTick() { return 0; }
    @Override public boolean isItemValidForSlot(int slot, ItemStack stack) { return false; }
    @Override public int[][] getSlotLayout() { return new int[0][]; }
}
