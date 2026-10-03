package com.advancedtech.gui;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

/** Данные Книги Рецептов (сгенерировано из таблицы рецептов, совпадает с JSON-рецептами). */
public class GuideData {
    public static class Entry {
        public final int tab;
        public final String result;
        public final int count;
        public final String[] grid;

        public Entry(int tab, String result, int count, String[] grid) {
            this.tab = tab; this.result = result; this.count = count; this.grid = grid;
        }

        public String shortName() { return result.substring(result.indexOf(':') + 1); }

        public ItemStack resultStack() {
            Item it = ForgeRegistries.ITEMS.getValue(new ResourceLocation(result));
            if (it == null || it == Items.AIR) return ItemStack.EMPTY;
            return new ItemStack(it, count);
        }
    }

    public static final List<Entry> ENTRIES = new ArrayList<>();

    public static List<Entry> forTab(int tab) {
        List<Entry> out = new ArrayList<>();
        for (Entry e : ENTRIES) if (e.tab == tab) out.add(e);
        return out;
    }

    static {
        ENTRIES.add(new Entry(1, "advancedtech:engineer_book", 1, new String[]{"minecraft:book", "minecraft:redstone", "minecraft:iron_ingot", null, null, null, null, null, null}));
        ENTRIES.add(new Entry(1, "advancedtech:circuit", 1, new String[]{"minecraft:redstone", "minecraft:redstone", "minecraft:redstone", "ore:ingotCopper", "minecraft:iron_ingot", "ore:ingotCopper", null, null, null}));
        ENTRIES.add(new Entry(1, "advancedtech:machine_frame", 1, new String[]{"ore:plateIron", "ore:ingotBronze", "ore:plateIron", "ore:ingotBronze", null, "ore:ingotBronze", "ore:plateIron", "ore:ingotBronze", "ore:plateIron"}));
        ENTRIES.add(new Entry(1, "advancedtech:cable_lv", 6, new String[]{"minecraft:redstone", "minecraft:redstone", "minecraft:redstone", "ore:ingotCopper", "ore:ingotCopper", "ore:ingotCopper", null, null, null}));
        ENTRIES.add(new Entry(1, "advancedtech:cable_mv", 1, new String[]{"advancedtech:cable_lv", "ore:ingotTin", "minecraft:iron_ingot", null, null, null, null, null, null}));
        ENTRIES.add(new Entry(1, "advancedtech:cable_hv", 1, new String[]{"advancedtech:cable_mv", "ore:ingotSilver", "minecraft:diamond", null, null, null, null, null, null}));
        ENTRIES.add(new Entry(1, "advancedtech:cable_ev", 1, new String[]{"advancedtech:cable_hv", "advancedtech:quantum_core", null, null, null, null, null, null, null}));
        ENTRIES.add(new Entry(5, "advancedtech:bronze_pickaxe", 1, new String[]{"ore:ingotBronze", "ore:ingotBronze", "ore:ingotBronze", null, "minecraft:stick", null, null, "minecraft:stick", null}));
        ENTRIES.add(new Entry(5, "advancedtech:bronze_sword", 1, new String[]{"ore:ingotBronze", null, null, "ore:ingotBronze", null, null, "minecraft:stick", null, null}));
        ENTRIES.add(new Entry(5, "advancedtech:bronze_helmet", 1, new String[]{"ore:ingotBronze", "ore:ingotBronze", "ore:ingotBronze", "ore:ingotBronze", null, "ore:ingotBronze", null, null, null}));
        ENTRIES.add(new Entry(5, "advancedtech:bronze_chestplate", 1, new String[]{"ore:ingotBronze", null, "ore:ingotBronze", "ore:ingotBronze", "ore:ingotBronze", "ore:ingotBronze", "ore:ingotBronze", "ore:ingotBronze", "ore:ingotBronze"}));
        ENTRIES.add(new Entry(2, "advancedtech:combustion_generator", 1, new String[]{"minecraft:iron_ingot", "minecraft:iron_ingot", "minecraft:iron_ingot", "minecraft:iron_ingot", "minecraft:furnace", "minecraft:iron_ingot", "minecraft:iron_ingot", "advancedtech:circuit", "minecraft:iron_ingot"}));
        ENTRIES.add(new Entry(2, "advancedtech:solar_panel", 1, new String[]{"minecraft:glass", "minecraft:glass", "minecraft:glass", "advancedtech:circuit", "ore:plateSilver", "advancedtech:circuit", "minecraft:iron_ingot", "minecraft:redstone", "minecraft:iron_ingot"}));
        ENTRIES.add(new Entry(2, "advancedtech:battery_box", 1, new String[]{"ore:plateTin", "ore:ingotCopper", "ore:plateTin", "ore:plateTin", "minecraft:redstone", "ore:plateTin", "ore:plateTin", "ore:ingotCopper", "ore:plateTin"}));
        ENTRIES.add(new Entry(2, "advancedtech:air_filter", 1, new String[]{"minecraft:paper", "minecraft:paper", "minecraft:paper", "minecraft:iron_bars", "minecraft:coal", "minecraft:iron_bars", null, null, null}));
        ENTRIES.add(new Entry(3, "advancedtech:electric_furnace", 1, new String[]{"minecraft:iron_ingot", "minecraft:iron_ingot", "minecraft:iron_ingot", "advancedtech:circuit", "minecraft:furnace", "advancedtech:circuit", "minecraft:iron_ingot", "minecraft:redstone", "minecraft:iron_ingot"}));
        ENTRIES.add(new Entry(3, "advancedtech:crusher", 1, new String[]{"minecraft:flint", "minecraft:flint", "minecraft:flint", "minecraft:iron_ingot", "advancedtech:circuit", "minecraft:iron_ingot", "minecraft:iron_ingot", "minecraft:redstone", "minecraft:iron_ingot"}));
        ENTRIES.add(new Entry(3, "advancedtech:compressor", 1, new String[]{"minecraft:iron_ingot", "minecraft:piston", "minecraft:iron_ingot", "minecraft:iron_ingot", "advancedtech:circuit", "minecraft:iron_ingot", "minecraft:iron_ingot", "minecraft:redstone", "minecraft:iron_ingot"}));
        ENTRIES.add(new Entry(3, "advancedtech:alloy_smelter", 1, new String[]{"minecraft:iron_ingot", "minecraft:furnace", "minecraft:iron_ingot", "advancedtech:circuit", "minecraft:redstone", "advancedtech:circuit", "minecraft:iron_ingot", "minecraft:furnace", "minecraft:iron_ingot"}));
        ENTRIES.add(new Entry(4, "advancedtech:casing_upgrade_improved", 1, new String[]{"ore:plateIron", "ore:plateIron", "ore:plateIron", "ore:plateIron", "advancedtech:circuit", "ore:plateIron", "ore:plateIron", "ore:plateIron", "ore:plateIron"}));
        ENTRIES.add(new Entry(4, "advancedtech:casing_upgrade_industrial", 1, new String[]{"ore:plateBronze", "ore:plateBronze", "ore:plateBronze", "ore:plateBronze", "advancedtech:casing_upgrade_improved", "ore:plateBronze", "ore:plateBronze", "ore:plateBronze", "ore:plateBronze"}));
        ENTRIES.add(new Entry(4, "advancedtech:casing_upgrade_quantum", 1, new String[]{"ore:plateGold", "advancedtech:quantum_core", "ore:plateGold", "ore:plateGold", "advancedtech:casing_upgrade_industrial", "ore:plateGold", "ore:plateGold", "ore:plateGold", "ore:plateGold"}));
        ENTRIES.add(new Entry(4, "advancedtech:energy_drink", 1, new String[]{"minecraft:glass_bottle", "minecraft:sugar", "minecraft:redstone", "minecraft:glowstone_dust", null, null, null, null, null}));
        ENTRIES.add(new Entry(1, "advancedtech:energy_cell", 2, new String[]{"ore:plateTin", "minecraft:redstone", "ore:plateTin", "ore:ingotCopper", "minecraft:redstone", "ore:ingotCopper", "ore:plateTin", "minecraft:redstone", "ore:plateTin"}));
        ENTRIES.add(new Entry(1, "advancedtech:composite_plate", 2, new String[]{"ore:plateBronze", "ore:plateIron", null, "ore:plateIron", "ore:plateBronze", null, null, null, null}));
        ENTRIES.add(new Entry(2, "advancedtech:bio_reactor", 1, new String[]{"ore:plateIron", "advancedtech:biomass", "ore:plateIron", "advancedtech:circuit", "advancedtech:machine_frame", "advancedtech:circuit", "ore:plateIron", "minecraft:redstone", "ore:plateIron"}));
        ENTRIES.add(new Entry(2, "advancedtech:geothermal_generator", 1, new String[]{"ore:plateIron", "minecraft:lava_bucket", "ore:plateIron", "advancedtech:circuit", "advancedtech:machine_frame", "advancedtech:circuit", "ore:plateIron", "minecraft:redstone", "ore:plateIron"}));
        ENTRIES.add(new Entry(2, "advancedtech:wind_turbine", 1, new String[]{"ore:plateIron", "ore:plateIron", "ore:plateIron", "advancedtech:circuit", "advancedtech:machine_frame", "advancedtech:circuit", "minecraft:iron_ingot", "minecraft:redstone", "minecraft:iron_ingot"}));
        ENTRIES.add(new Entry(2, "advancedtech:smog_reactor", 1, new String[]{"minecraft:iron_ingot", "advancedtech:air_filter", "minecraft:iron_ingot", "advancedtech:circuit", "advancedtech:machine_frame", "advancedtech:circuit", "minecraft:iron_ingot", "minecraft:redstone", "minecraft:iron_ingot"}));
        ENTRIES.add(new Entry(2, "advancedtech:storm_collector", 1, new String[]{"ore:plateSilver", "minecraft:diamond", "ore:plateSilver", "advancedtech:circuit", "advancedtech:machine_frame", "advancedtech:circuit", "ore:plateGold", "advancedtech:energy_cell", "ore:plateGold"}));
        ENTRIES.add(new Entry(4, "advancedtech:laser_miner", 1, new String[]{"minecraft:diamond", "advancedtech:circuit", "minecraft:diamond", "ore:plateIron", "advancedtech:machine_frame", "ore:plateIron", "advancedtech:energy_cell", "minecraft:glowstone", "advancedtech:energy_cell"}));
        ENTRIES.add(new Entry(4, "advancedtech:ore_scanner", 1, new String[]{null, "advancedtech:energy_cell", null, "ore:plateBronze", "advancedtech:circuit", "ore:plateBronze", "ore:plateBronze", "minecraft:redstone", "ore:plateBronze"}));
        ENTRIES.add(new Entry(5, "advancedtech:arc_rifle", 1, new String[]{"advancedtech:composite_plate", "ore:plateSilver", "advancedtech:energy_cell", "advancedtech:circuit", "advancedtech:energy_cell", "advancedtech:composite_plate", null, null, "advancedtech:composite_plate"}));
        ENTRIES.add(new Entry(5, "advancedtech:plasma_blade", 1, new String[]{"advancedtech:composite_plate", null, null, "advancedtech:energy_cell", null, null, "ore:ingotBronze", null, null}));
        ENTRIES.add(new Entry(5, "advancedtech:exo_helmet", 1, new String[]{"advancedtech:composite_plate", "advancedtech:composite_plate", "advancedtech:composite_plate", "advancedtech:composite_plate", "advancedtech:circuit", "advancedtech:composite_plate", null, null, null}));
        ENTRIES.add(new Entry(5, "advancedtech:exo_chestplate", 1, new String[]{"advancedtech:composite_plate", "advancedtech:circuit", "advancedtech:composite_plate", "advancedtech:composite_plate", "advancedtech:energy_cell", "advancedtech:composite_plate", "advancedtech:composite_plate", "advancedtech:composite_plate", "advancedtech:composite_plate"}));
    }
}
