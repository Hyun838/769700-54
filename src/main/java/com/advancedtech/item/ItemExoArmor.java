package com.advancedtech.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Экзо-броня. Шлем - ночное зрение, нагрудник - иммунитет к яду (в полном комплекте - и к иссушению),
 * поножи - скорость (в полном комплекте скорость II), ботинки - гасят 60% урона от падения.
 */
public class ItemExoArmor extends ItemATArmor {
    public ItemExoArmor(String name, EntityEquipmentSlot slot) {
        super(name, ATMaterials.EXO_ARMOR, slot);
    }

    public static boolean has(EntityPlayer p, EntityEquipmentSlot slot) {
        return p.getItemStackFromSlot(slot).getItem() instanceof ItemExoArmor;
    }

    public static boolean fullSet(EntityPlayer p) {
        return has(p, EntityEquipmentSlot.HEAD) && has(p, EntityEquipmentSlot.CHEST)
                && has(p, EntityEquipmentSlot.LEGS) && has(p, EntityEquipmentSlot.FEET);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (world.isRemote) return;
        switch (armorType) {
            case HEAD: {
                PotionEffect cur = player.getActivePotionEffect(MobEffects.NIGHT_VISION);
                if (cur == null || cur.getDuration() < 240)
                    player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 400, 0, true, false));
                break;
            }
            case CHEST:
                if (player.isPotionActive(MobEffects.POISON)) player.removePotionEffect(MobEffects.POISON);
                if (fullSet(player) && player.isPotionActive(MobEffects.WITHER)) player.removePotionEffect(MobEffects.WITHER);
                break;
            case LEGS:
                player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 40, fullSet(player) ? 1 : 0, true, false));
                break;
            default:
                break;
        }
    }
}
