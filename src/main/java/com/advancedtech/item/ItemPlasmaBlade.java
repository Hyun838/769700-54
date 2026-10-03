package com.advancedtech.item;

import com.advancedtech.AdvancedTech;
import com.advancedtech.registry.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

/** Плазменный клинок: быстрый меч, поджигающий цель. Чинится композитными пластинами. */
public class ItemPlasmaBlade extends ItemSword {
    public ItemPlasmaBlade() {
        super(ATMaterials.PLASMA);
        setRegistryName(AdvancedTech.MODID, "plasma_blade");
        setTranslationKey(AdvancedTech.MODID + ".plasma_blade");
        setCreativeTab(CreativeTabs.COMBAT);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        target.setFire(5);
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == ModItems.get("composite_plate");
    }
}
