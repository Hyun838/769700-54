package com.advancedtech.item;

import com.advancedtech.AdvancedTech;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/** Энергетик: скорость + ускоренное копание на минуту. Можно пить всегда. */
public class ItemEnergyDrink extends ItemFood {
    public ItemEnergyDrink() {
        super(2, 0.3F, false);
        setAlwaysEdible();
        setRegistryName(AdvancedTech.MODID, "energy_drink");
        setTranslationKey(AdvancedTech.MODID + ".energy_drink");
        setCreativeTab(CreativeTabs.FOOD);
        setMaxStackSize(16);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1200, 1));
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1200, 0));
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) { return EnumAction.DRINK; }
}
