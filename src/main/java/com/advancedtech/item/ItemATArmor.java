package com.advancedtech.item;

import com.advancedtech.AdvancedTech;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemATArmor extends ItemArmor {
    public ItemATArmor(String name, ItemArmor.ArmorMaterial material, EntityEquipmentSlot slot) {
        super(material, 0, slot);
        setRegistryName(AdvancedTech.MODID, name);
        setTranslationKey(AdvancedTech.MODID + "." + name);
        setCreativeTab(CreativeTabs.COMBAT);
    }
}
