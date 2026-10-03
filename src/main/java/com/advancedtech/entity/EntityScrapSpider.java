package com.advancedtech.entity;

import com.advancedtech.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/** Скрап-паук: механический паук на 8 лапах. Лазит по стенам, замедляет жертву. Роняет детали. */
public class EntityScrapSpider extends EntitySpider {
    public EntityScrapSpider(World w) {
        super(w);
        experienceValue = 8;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(22.0D);
        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.34D);
    }

    /** Без наездников-скелетов и случайных эффектов обычного паука. */
    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData data) {
        return data;
    }

    @Override
    public boolean attackEntityAsMob(Entity target) {
        boolean ok = super.attackEntityAsMob(target);
        if (ok && target instanceof EntityLivingBase)
            ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, 1));
        return ok;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() { return null; }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int looting) {
        if (!wasRecentlyHit) return;
        entityDropItem(new ItemStack(ModItems.get("dust_iron"), 1 + rand.nextInt(2 + looting)), 0.0F);
        if (rand.nextInt(3) == 0) entityDropItem(new ItemStack(ModItems.CIRCUIT, 1), 0.0F);
        if (rand.nextInt(6) == 0) entityDropItem(new ItemStack(ModItems.get("plate_iron"), 1), 0.0F);
    }
}
