package com.advancedtech.entity;

import com.advancedtech.registry.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/**
 * Дрон-шахтёр: летающий робот. Парит над целью, стреляет лазером-буром.
 * Не использует навигацию по земле - летает сам (ускорение к точке над игроком).
 */
public class EntityMiningDrone extends EntityMob {
    private int shotCooldown = 40;

    public EntityMiningDrone(World w) {
        super(w);
        setSize(0.9F, 0.9F);
        setNoGravity(true);
        experienceValue = 6;
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 12.0F));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(26.0D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3.0D);
    }

    @Override
    public void onLivingUpdate() {
        if (!world.isRemote) {
            EntityLivingBase t = getAttackTarget();
            if (t != null && t.isEntityAlive()) {
                double dx = t.posX - posX;
                double dz = t.posZ - posZ;
                double dy = (t.posY + t.getEyeHeight() + 1.5D) - posY;
                double horiz = Math.sqrt(dx * dx + dz * dz);
                if (horiz > 8.0D) {
                    motionX += dx / horiz * 0.05D;
                    motionZ += dz / horiz * 0.05D;
                } else if (horiz < 4.0D && horiz > 0.01D) {
                    motionX -= dx / horiz * 0.04D;
                    motionZ -= dz / horiz * 0.04D;
                }
                motionY += Math.max(-1.0D, Math.min(1.0D, dy)) * 0.03D;
                faceEntity(t, 30.0F, 30.0F);

                if (--shotCooldown <= 0 && horiz < 16.0D && canEntityBeSeen(t)) {
                    shotCooldown = 36 + rand.nextInt(12);
                    shoot(t);
                }
            } else {
                motionY += Math.sin(ticksExisted * 0.1D) * 0.002D;
            }
        }
        super.onLivingUpdate();
    }

    private void shoot(EntityLivingBase target) {
        double sx = posX, sy = posY + 0.3D, sz = posZ;
        double tx = target.posX, ty = target.posY + target.height * 0.6D, tz = target.posZ;
        if (world instanceof WorldServer) {
            WorldServer ws = (WorldServer) world;
            int steps = 14;
            for (int i = 1; i <= steps; i++) {
                double f = (double) i / steps;
                ws.spawnParticle(EnumParticleTypes.FLAME, sx + (tx - sx) * f, sy + (ty - sy) * f,
                        sz + (tz - sz) * f, 1, 0.0D, 0.0D, 0.0D, 0.0D);
            }
        }
        playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 0.8F, 1.9F);
        target.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
    }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        if (!wasRecentlyHit) return;
        if (rand.nextInt(2) == 0) entityDropItem(new ItemStack(ModItems.CIRCUIT, 1), 0.0F);
        entityDropItem(new ItemStack(ModItems.get("dust_copper"), 1 + rand.nextInt(2 + lootingModifier)), 0.0F);
        if (rand.nextInt(4) == 0) entityDropItem(new ItemStack(ModItems.get("plate_iron"), 1), 0.0F);
    }

    @Override protected SoundEvent getHurtSound(DamageSource src) { return SoundEvents.ENTITY_IRONGOLEM_HURT; }
    @Override protected SoundEvent getDeathSound() { return SoundEvents.ENTITY_GENERIC_EXPLODE; }
}
