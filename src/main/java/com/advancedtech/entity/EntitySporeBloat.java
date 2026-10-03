package com.advancedtech.entity;

import com.advancedtech.pollution.PollutionManager;
import com.advancedtech.registry.ModItems;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

/**
 * Споровый пузырь: летающая мутировавшая споровая колба. Дрейфует к игроку и взрывается ядовитым облаком,
 * заражая чанк загрязнением. Лёгкая добыча: 10 здоровья. Даёт биомассу.
 */
public class EntitySporeBloat extends EntityMob {
    private int fuse = -1;
    private boolean burst;

    public EntitySporeBloat(World w) {
        super(w);
        setSize(1.0F, 1.1F);
        setNoGravity(true);
        experienceValue = 5;
    }

    @Override
    protected void initEntityAI() {
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24.0D);
    }

    @Override
    public void onLivingUpdate() {
        if (!world.isRemote) {
            EntityLivingBase t = getAttackTarget();
            if (t != null && t.isEntityAlive()) {
                double dx = t.posX - posX, dz = t.posZ - posZ;
                double dy = (t.posY + t.getEyeHeight() * 0.7D) - posY;
                double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
                if (dist > 0.01D) {
                    double sp = fuse >= 0 ? 0.01D : 0.028D;
                    motionX += dx / dist * sp;
                    motionY += dy / dist * sp;
                    motionZ += dz / dist * sp;
                    motionX *= 0.92D; motionY *= 0.92D; motionZ *= 0.92D;
                }
                faceEntity(t, 20.0F, 20.0F);
                if (fuse < 0 && dist < 2.6D) {
                    fuse = 24;
                    playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.6F);
                }
            } else {
                motionY += Math.sin(ticksExisted * 0.07D) * 0.002D;
                motionX *= 0.95D; motionZ *= 0.95D;
                fuse = -1;
            }
            if (fuse >= 0 && --fuse < 0 && !burst) {
                release(1.0F);
                setDead();
            }
        }
        super.onLivingUpdate();
    }

    private void release(float size) {
        if (burst) return;
        burst = true;
        EntityAreaEffectCloud c = new EntityAreaEffectCloud(world, posX, posY + 0.4D, posZ);
        c.setOwner(this);
        c.setRadius(3.0F * size);
        c.setDuration(180);
        c.setWaitTime(5);
        c.setRadiusPerTick(-c.getRadius() / (float) c.getDuration());
        c.setColor(0x7BC82A);
        c.addEffect(new PotionEffect(MobEffects.POISON, 100, 0));
        c.addEffect(new PotionEffect(MobEffects.NAUSEA, 120, 0));
        world.spawnEntity(c);
        playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 0.7F, 1.6F);
        PollutionManager.add(world, getPosition(), 12.0F * size);
    }

    @Override
    public void onDeath(DamageSource src) {
        if (!world.isRemote) release(0.6F);
        super.onDeath(src);
    }

    @Override
    public boolean isOnLadder() { return false; }

    @Override
    public void fall(float distance, float multiplier) { }

    @Override
    protected void updateFallState(double y, boolean onGround, net.minecraft.block.state.IBlockState state, net.minecraft.util.math.BlockPos pos) { }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int looting) {
        if (!wasRecentlyHit) return;
        entityDropItem(new ItemStack(ModItems.BIOMASS, 1 + rand.nextInt(2 + looting)), 0.0F);
    }

    @Override protected SoundEvent getHurtSound(DamageSource src) { return SoundEvents.ENTITY_SLIME_HURT; }
    @Override protected SoundEvent getDeathSound() { return SoundEvents.ENTITY_SLIME_DEATH; }
}
