package com.advancedtech.item;

import com.advancedtech.AdvancedTech;
import com.advancedtech.registry.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

/**
 * Дуговая винтовка: мгновенный разряд на 24 блока (7 урона) + цепная молния на 2 ближайших цели (4 урона).
 * Патронов нет - расходуется прочность; чинится энергоячейками.
 */
public class ItemArcRifle extends Item {
    public static final double RANGE = 24.0D;

    public ItemArcRifle() {
        setRegistryName(AdvancedTech.MODID, "arc_rifle");
        setTranslationKey(AdvancedTech.MODID + ".arc_rifle");
        setCreativeTab(CreativeTabs.COMBAT);
        setMaxStackSize(1);
        setMaxDamage(240);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.get("energy_cell");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World w, EntityPlayer p, EnumHand hand) {
        ItemStack stack = p.getHeldItem(hand);
        if (p.getCooldownTracker().hasCooldown(this)) return new ActionResult<>(EnumActionResult.PASS, stack);
        p.getCooldownTracker().setCooldown(this, 14);
        if (w.isRemote) return new ActionResult<>(EnumActionResult.SUCCESS, stack);

        Vec3d eye = p.getPositionEyes(1.0F);
        Vec3d look = p.getLookVec();
        Vec3d end = eye.add(look.x * RANGE, look.y * RANGE, look.z * RANGE);
        RayTraceResult blk = w.rayTraceBlocks(eye, end, false, true, false);
        double maxDist = blk != null ? blk.hitVec.distanceTo(eye) : RANGE;
        Vec3d cut = eye.add(look.x * maxDist, look.y * maxDist, look.z * maxDist);

        EntityLivingBase target = null;
        double best = maxDist;
        AxisAlignedBB box = new AxisAlignedBB(eye.x, eye.y, eye.z, cut.x, cut.y, cut.z).grow(1.5D);
        List<EntityLivingBase> list = w.getEntitiesWithinAABB(EntityLivingBase.class, box);
        for (EntityLivingBase e : list) {
            if (e == p || !e.isEntityAlive()) continue;
            AxisAlignedBB bb = e.getEntityBoundingBox().grow(0.3D);
            RayTraceResult r = bb.calculateIntercept(eye, cut);
            if (r != null) {
                double d = eye.distanceTo(r.hitVec);
                if (d < best) { best = d; target = e; }
            }
        }
        Vec3d hit = target != null ? eye.add(look.x * best, look.y * best, look.z * best) : cut;

        beam(w, eye.add(look.x * 0.8D, look.y * 0.8D - 0.15D, look.z * 0.8D), hit);
        w.playSound(null, p.posX, p.posY, p.posZ, SoundEvents.ENTITY_LIGHTNING_IMPACT, SoundCategory.PLAYERS, 0.35F, 2.0F);

        if (target != null) {
            target.attackEntityFrom(DamageSource.causePlayerDamage(p), 7.0F);
            int chained = 0;
            List<EntityLivingBase> near = w.getEntitiesWithinAABB(EntityLivingBase.class, target.getEntityBoundingBox().grow(5.0D));
            for (EntityLivingBase e : near) {
                if (chained >= 2) break;
                if (e == p || e == target || !e.isEntityAlive()) continue;
                beam(w, new Vec3d(target.posX, target.posY + target.height * 0.5D, target.posZ),
                        new Vec3d(e.posX, e.posY + e.height * 0.5D, e.posZ));
                e.attackEntityFrom(DamageSource.causePlayerDamage(p), 4.0F);
                chained++;
            }
        }
        if (!p.capabilities.isCreativeMode) stack.damageItem(1, p);
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    private static void beam(World w, Vec3d a, Vec3d b) {
        if (!(w instanceof WorldServer)) return;
        WorldServer ws = (WorldServer) w;
        double len = a.distanceTo(b);
        int steps = Math.max(2, (int) (len * 2.5D));
        for (int i = 0; i <= steps; i++) {
            double f = (double) i / steps;
            ws.spawnParticle(EnumParticleTypes.CRIT_MAGIC, a.x + (b.x - a.x) * f, a.y + (b.y - a.y) * f,
                    a.z + (b.z - a.z) * f, 1, 0.04D, 0.04D, 0.04D, 0.0D);
        }
    }
}
