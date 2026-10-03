package com.advancedtech.item;

import com.advancedtech.AdvancedTech;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class ItemATPickaxe extends ItemPickaxe {
    public ItemATPickaxe(String name, Item.ToolMaterial material) {
        super(material);
        setRegistryName(AdvancedTech.MODID, name);
        setTranslationKey(AdvancedTech.MODID + "." + name);
        setCreativeTab(CreativeTabs.TOOLS);
    }
}
