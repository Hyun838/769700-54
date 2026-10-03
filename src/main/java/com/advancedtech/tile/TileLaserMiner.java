package com.advancedtech.tile;

import com.advancedtech.energy.EnergyTier;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

/**
 * Лазерный карьер: вместо бура - луч, который послойно испаряет область 9x9 под машиной сверху вниз.
 * Стоимость блока = 300 + твёрдость*100 FE. Добыча идёт в 9 внутренних слотов и выталкивается в соседние инвентари.
 */
public class TileLaserMiner extends TileEntityMachine {
    public static final int RADIUS = 4;
    public static final int BASE_COST = 300;
    public static final int COST_PER_HARDNESS = 100;
    public static final int CAPACITY = 60_000;

    private int curX = -RADIUS, curZ = -RADIUS, curY = -1;   // curY < 0: ещё не инициализировано
    private boolean finished;
    private int cooldown;

    public TileLaserMiner() {
        super(9, EnergyTier.MV);
        energy.setCapacityOverride(CAPACITY);
    }

    @Override
    public void update() {
        if (world.isRemote) return;
        if (world.getTotalWorldTime() % 8 == 0) pushItems();

        if (finished || overheated) { tickHeat(false); progress = 0; return; }
        if (curY < 0) curY = pos.getY() - 1;

        boolean working = false;
        if (cooldown > 0) cooldown--;
        else {
            // найдём следующий добываемый блок (пропуская воздух и т.п. - не больше 64 шагов за тик)
            for (int guard = 0; guard < 64; guard++) {
                if (curY < 1) { finished = true; break; }
                BlockPos t = new BlockPos(pos.getX() + curX, curY, pos.getZ() + curZ);
                IBlockState st = world.getBlockState(t);
                if (!canMine(st, t)) { advance(); continue; }

                int cost = BASE_COST + Math.round(st.getBlockHardness(world, t) * COST_PER_HARDNESS);
                if (!hasSpace()) break;
                if (!energy.consume(cost)) break;

                NonNullList<ItemStack> drops = NonNullList.create();
                st.getBlock().getDrops(drops, world, t, st, 0);
                for (ItemStack d : drops) store(d, t);
                world.destroyBlock(t, false);
                if (world instanceof WorldServer) {
                    ((WorldServer) world).spawnParticle(EnumParticleTypes.FLAME, t.getX() + .5, t.getY() + .5, t.getZ() + .5, 6, .3, .3, .3, 0.02);
                    ((WorldServer) world).spawnParticle(EnumParticleTypes.SMOKE_NORMAL, t.getX() + .5, t.getY() + .8, t.getZ() + .5, 4, .3, .3, .3, 0.01);
                }
                working = true;
                advance();
                cooldown = 4;
                break;
            }
        }
        progress = working ? 1 : 0;
        // тепло от энергии цикла (грубо): работа греет, корпус охлаждает
        tickHeatCustom(working);
        markDirty();
    }

    private void tickHeatCustom(boolean working) {
        if (!com.advancedtech.config.ATConfig.enableHeat) { heat = AMBIENT; overheated = false; return; }
        if (working) heat += 4f;
        heat -= (heat - AMBIENT) * COOL_COEFF * COOLING[casing];
        if (heat >= OVERHEAT) overheated = true;
        else if (overheated && heat <= RECOVER) overheated = false;
    }

    private boolean canMine(IBlockState st, BlockPos t) {
        Block b = st.getBlock();
        if (st.getMaterial() == Material.AIR || st.getMaterial().isLiquid()) return false;
        if (b == Blocks.BEDROCK) return false;
        if (st.getBlockHardness(world, t) < 0) return false;
        if (b.hasTileEntity(st)) return false;
        return true;
    }

    private void advance() {
        curX++;
        if (curX > RADIUS) { curX = -RADIUS; curZ++; }
        if (curZ > RADIUS) { curZ = -RADIUS; curY--; }
        if (curY < 1) finished = true;
    }

    private boolean hasSpace() {
        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack s = inventory.getStackInSlot(i);
            if (s.isEmpty() || s.getCount() < s.getMaxStackSize()) return true;
        }
        return false;
    }

    private void store(ItemStack d, BlockPos at) {
        ItemStack rest = d.copy();
        for (int i = 0; i < inventory.getSlots() && !rest.isEmpty(); i++) {
            ItemStack cur = inventory.getStackInSlot(i);
            if (cur.isEmpty()) {
                inventory.setStackInSlot(i, rest.copy());
                rest = ItemStack.EMPTY;
            } else if (ItemHandlerHelper.canItemStacksStack(cur, rest)) {
                int can = Math.min(rest.getCount(), cur.getMaxStackSize() - cur.getCount());
                if (can > 0) { cur.grow(can); rest.shrink(can); }
            }
        }
        if (!rest.isEmpty()) net.minecraft.inventory.InventoryHelper.spawnItemStack(world, pos.getX() + .5, pos.getY() + 1.1, pos.getZ() + .5, rest);
    }

    private void pushItems() {
        for (EnumFacing f : EnumFacing.VALUES) {
            TileEntity te = world.getTileEntity(pos.offset(f));
            if (te == null || te instanceof TileLaserMiner) continue;
            IItemHandler h = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, f.getOpposite());
            if (h == null) continue;
            for (int i = 0; i < inventory.getSlots(); i++) {
                ItemStack s = inventory.getStackInSlot(i);
                if (s.isEmpty()) continue;
                ItemStack left = ItemHandlerHelper.insertItemStacked(h, s.copy(), false);
                inventory.setStackInSlot(i, left);
                if (left.getCount() != s.getCount()) return;
            }
        }
    }

    /** 0..1 - прогресс по глубине (для GUI). */
    public int depthDone() { return curY < 0 ? 0 : Math.max(0, pos.getY() - 1 - curY); }

    @Override public int getProcessTime() { return Math.max(1, pos.getY() - 1); }
    @Override public int[] packSync() {
        int[] a = super.packSync();
        a[5] = depthDone();
        return a;
    }
    @Override protected void tickHeat(boolean working) { tickHeatCustom(working); }
    @Override public boolean canProcess() { return false; }
    @Override public void finishProcess() { }
    @Override public int getBaseProcessTime() { return 1; }
    @Override public int getEnergyPerTick() { return 0; }
    @Override public boolean isItemValidForSlot(int slot, ItemStack stack) { return false; }

    @Override
    public int[][] getSlotLayout() {
        int[][] l = new int[9][];
        for (int i = 0; i < 9; i++) l[i] = new int[]{62 + (i % 3) * 18, 17 + (i / 3) * 18};
        return l;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1, pos.getX() + 2, pos.getY() + 3, pos.getZ() + 2);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound t) {
        super.writeToNBT(t);
        t.setInteger("CX", curX); t.setInteger("CZ", curZ); t.setInteger("CY", curY);
        t.setBoolean("Done", finished);
        return t;
    }

    @Override
    public void readFromNBT(NBTTagCompound t) {
        super.readFromNBT(t);
        energy.setCapacityOverride(CAPACITY);
        curX = t.hasKey("CX") ? t.getInteger("CX") : -RADIUS;
        curZ = t.hasKey("CZ") ? t.getInteger("CZ") : -RADIUS;
        curY = t.hasKey("CY") ? t.getInteger("CY") : -1;
        finished = t.getBoolean("Done");
    }
}
