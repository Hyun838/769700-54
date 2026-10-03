package com.advancedtech.tile;

import com.advancedtech.energy.EnergyTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Ветрогенератор: чем выше над землёй и чем хуже погода - тем больше FE/т (до ~27). Нужно открытое небо. */
public class TileWindTurbine extends TileEntityMachine implements ISpinning {
    private float spin, spinSpeed;

    public TileWindTurbine() { super(0, EnergyTier.LV); }

    /** Выдача в FE/т. Одинаковая формула на клиенте (скорость вращения) и на сервере (энергия). */
    public static int output(World w, BlockPos pos) {
        if (!w.canSeeSky(pos.up())) return 0;
        float base = Math.max(0, Math.min(18, (pos.getY() - 60) / 3));
        if (w.isThundering()) base *= 1.5f;
        else if (w.isRaining()) base *= 1.25f;
        return Math.round(base);
    }

    @Override
    public void update() {
        int out = output(world, pos);
        if (world.isRemote) {
            spinSpeed += (out * 0.03f - spinSpeed) * 0.05f;
            spin += spinSpeed;
            return;
        }
        if (out > 0) energy.generate(out);
        progress = out;
        pushEnergy();
        if (world.getTotalWorldTime() % 20 == 0) markDirty();
    }

    @Override public float getSpin(float partialTicks) { return spin + spinSpeed * partialTicks; }

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(pos.getX() - 2, pos.getY(), pos.getZ() - 2, pos.getX() + 3, pos.getY() + 3, pos.getZ() + 3);
    }

    @Override protected void tickHeat(boolean working) { }
    @Override public boolean acceptsEnergy() { return false; }
    @Override public boolean canProcess() { return false; }
    @Override public void finishProcess() { }
    @Override public int getBaseProcessTime() { return 1; }
    @Override public int getProcessTime() { return 27; }
    @Override public int getEnergyPerTick() { return 0; }
    @Override public boolean isItemValidForSlot(int slot, ItemStack stack) { return false; }
    @Override public int[][] getSlotLayout() { return new int[0][]; }
}
