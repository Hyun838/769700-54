package com.advancedtech.item;

import com.advancedtech.AdvancedTech;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class ItemATAxe extends ItemAxe {
    public ItemATAxe(String name, Item.ToolMaterial material) {
        super(material, 8.0F, -3.1F);
        setRegistryName(AdvancedTech.MODID, name);
        setTranslationKey(AdvancedTech.MODID + "." + name);
        setCreativeTab(CreativeTabs.TOOLS);
    }
}
