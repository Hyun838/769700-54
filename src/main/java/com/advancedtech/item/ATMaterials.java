package com.advancedtech.item;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

/** Материалы инструментов и брони мода. */
public class ATMaterials {
    public static final Item.ToolMaterial BRONZE_TOOL =
            EnumHelper.addToolMaterial("ADVANCEDTECH_BRONZE", 2, 320, 6.0F, 2.0F, 12);

    public static final ItemArmor.ArmorMaterial BRONZE_ARMOR =
            EnumHelper.addArmorMaterial("ADVANCEDTECH_BRONZE", "advancedtech:bronze", 16,
                    new int[]{2, 5, 5, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F);

    /** Плазменный клинок: алмазный уровень, быстрый, но хрупкий. */
    public static final Item.ToolMaterial PLASMA =
            EnumHelper.addToolMaterial("ADVANCEDTECH_PLASMA", 3, 700, 8.0F, 3.0F, 18);

    /** Экзо-броня: плотнее алмазной по защите и выносливее. */
    public static final ItemArmor.ArmorMaterial EXO_ARMOR =
            EnumHelper.addArmorMaterial("ADVANCEDTECH_EXO", "advancedtech:exo", 34,
                    new int[]{3, 6, 8, 3}, 14, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
}
