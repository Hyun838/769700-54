package com.advancedtech.registry;

import com.advancedtech.AdvancedTech;
import com.advancedtech.block.BlockCable;
import com.advancedtech.block.BlockMachine;
import com.advancedtech.energy.EnergyTier;
import com.advancedtech.item.ATMaterials;
import com.advancedtech.item.ItemATArmor;
import com.advancedtech.item.ItemATAxe;
import com.advancedtech.item.ItemATPickaxe;
import com.advancedtech.item.ItemCasingUpgrade;
import com.advancedtech.item.ItemEnergyDrink;
import com.advancedtech.item.ItemEngineerBook;
import com.advancedtech.item.ItemArcRifle;
import com.advancedtech.item.ItemExoArmor;
import com.advancedtech.item.ItemOreScanner;
import com.advancedtech.item.ItemPlasmaBlade;
import com.advancedtech.research.ResearchRegistry;
import com.advancedtech.tile.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@Mod.EventBusSubscriber(modid = AdvancedTech.MODID)
public class RegistryHandler {

    private static final String[] INGOT_METALS = {"copper", "tin", "silver", "bronze"};
    private static final String[] ALL_METALS = {"copper", "tin", "silver", "bronze", "iron", "gold"};

    private static String cap(String m) { return Character.toUpperCase(m.charAt(0)) + m.substring(1); }

    // ---------------- блоки ----------------
    private static Block ore(String name, int harvest) {
        Block b = new Block(Material.ROCK);
        b.setRegistryName(AdvancedTech.MODID, name);
        b.setTranslationKey(AdvancedTech.MODID + "." + name);
        b.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        b.setHardness(3.0f);
        b.setResistance(5.0f);
        b.setHarvestLevel("pickaxe", harvest);
        return b;
    }

    private static Block metalBlock(String name, float hardness) {
        Block b = new Block(Material.IRON);
        b.setRegistryName(AdvancedTech.MODID, name);
        b.setTranslationKey(AdvancedTech.MODID + "." + name);
        b.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        b.setHardness(hardness);
        b.setResistance(10.0f);
        b.setHarvestLevel("pickaxe", 1);
        b.setSoundType(SoundType.METAL);
        return b;
    }

    private static void reg(net.minecraftforge.registries.IForgeRegistry<Block> r, Block b) {
        ModBlocks.ALL.add(b);
        r.register(b);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        net.minecraftforge.registries.IForgeRegistry<Block> r = e.getRegistry();

        ModBlocks.ELECTRIC_FURNACE = new BlockMachine("electric_furnace", TileElectricFurnace::new, "processing");
        ModBlocks.COMBUSTION_GENERATOR = new BlockMachine("combustion_generator", TileCombustionGenerator::new, "electric_circuits");
        ModBlocks.SOLAR_PANEL = new BlockMachine("solar_panel", TileSolarPanel::new, "solar_energy");
        ModBlocks.CRUSHER = new BlockMachine("crusher", TileCrusher::new, "processing");
        ModBlocks.COMPRESSOR = new BlockMachine("compressor", TileCompressor::new, "processing");
        ModBlocks.ALLOY_SMELTER = new BlockMachine("alloy_smelter", TileAlloySmelter::new, "automation");
        ModBlocks.CREATIVE_SOURCE = new BlockMachine("creative_energy_source", TileCreativeSource::new, null);
        ModBlocks.BATTERY_BOX = new BlockMachine("battery_box", TileBatteryBox::new, null);

        ModBlocks.BIO_REACTOR = new BlockMachine("bio_reactor", TileBioReactor::new, "alt_energy");
        ModBlocks.GEOTHERMAL = new BlockMachine("geothermal_generator", TileGeothermal::new, "alt_energy");
        ModBlocks.SMOG_REACTOR = new BlockMachine("smog_reactor", TileSmogReactor::new, "smog_tech");
        ModBlocks.WIND_TURBINE = new BlockMachine("wind_turbine", TileWindTurbine::new, "alt_energy").tesr();
        ModBlocks.STORM_COLLECTOR = new BlockMachine("storm_collector", TileStormCollector::new, "hv_tier").tesr();
        ModBlocks.LASER_MINER = new BlockMachine("laser_miner", TileLaserMiner::new, "mining_tech").tesr();

        reg(r, ModBlocks.ELECTRIC_FURNACE);
        reg(r, ModBlocks.COMBUSTION_GENERATOR);
        reg(r, ModBlocks.SOLAR_PANEL);
        reg(r, ModBlocks.CRUSHER);
        reg(r, ModBlocks.COMPRESSOR);
        reg(r, ModBlocks.ALLOY_SMELTER);
        reg(r, ModBlocks.CREATIVE_SOURCE);
        reg(r, ModBlocks.BATTERY_BOX);
        reg(r, ModBlocks.BIO_REACTOR);
        reg(r, ModBlocks.GEOTHERMAL);
        reg(r, ModBlocks.SMOG_REACTOR);
        reg(r, ModBlocks.WIND_TURBINE);
        reg(r, ModBlocks.STORM_COLLECTOR);
        reg(r, ModBlocks.LASER_MINER);

        ModBlocks.ORE_COPPER = ore("ore_copper", 1);
        ModBlocks.ORE_TIN = ore("ore_tin", 1);
        ModBlocks.ORE_SILVER = ore("ore_silver", 2);
        reg(r, ModBlocks.ORE_COPPER);
        reg(r, ModBlocks.ORE_TIN);
        reg(r, ModBlocks.ORE_SILVER);

        ModBlocks.BLOCK_COPPER = metalBlock("block_copper", 4.0f);
        ModBlocks.BLOCK_TIN = metalBlock("block_tin", 4.0f);
        ModBlocks.BLOCK_SILVER = metalBlock("block_silver", 4.5f);
        ModBlocks.BLOCK_BRONZE = metalBlock("block_bronze", 5.0f);
        ModBlocks.MACHINE_FRAME = metalBlock("machine_frame", 3.0f);
        reg(r, ModBlocks.BLOCK_COPPER);
        reg(r, ModBlocks.BLOCK_TIN);
        reg(r, ModBlocks.BLOCK_SILVER);
        reg(r, ModBlocks.BLOCK_BRONZE);
        reg(r, ModBlocks.MACHINE_FRAME);

        for (EnergyTier t : EnergyTier.values()) {
            BlockCable c = new BlockCable("cable_" + t.name().toLowerCase(), t);
            ModBlocks.CABLES[t.ordinal()] = c;
            reg(r, c);
        }

        GameRegistry.registerTileEntity(TileElectricFurnace.class, new ResourceLocation(AdvancedTech.MODID, "electric_furnace"));
        GameRegistry.registerTileEntity(TileCombustionGenerator.class, new ResourceLocation(AdvancedTech.MODID, "combustion_generator"));
        GameRegistry.registerTileEntity(TileSolarPanel.class, new ResourceLocation(AdvancedTech.MODID, "solar_panel"));
        GameRegistry.registerTileEntity(TileCrusher.class, new ResourceLocation(AdvancedTech.MODID, "crusher"));
        GameRegistry.registerTileEntity(TileCompressor.class, new ResourceLocation(AdvancedTech.MODID, "compressor"));
        GameRegistry.registerTileEntity(TileAlloySmelter.class, new ResourceLocation(AdvancedTech.MODID, "alloy_smelter"));
        GameRegistry.registerTileEntity(TileCreativeSource.class, new ResourceLocation(AdvancedTech.MODID, "creative_energy_source"));
        GameRegistry.registerTileEntity(TileBatteryBox.class, new ResourceLocation(AdvancedTech.MODID, "battery_box"));
        GameRegistry.registerTileEntity(TileBioReactor.class, new ResourceLocation(AdvancedTech.MODID, "bio_reactor"));
        GameRegistry.registerTileEntity(TileGeothermal.class, new ResourceLocation(AdvancedTech.MODID, "geothermal_generator"));
        GameRegistry.registerTileEntity(TileSmogReactor.class, new ResourceLocation(AdvancedTech.MODID, "smog_reactor"));
        GameRegistry.registerTileEntity(TileWindTurbine.class, new ResourceLocation(AdvancedTech.MODID, "wind_turbine"));
        GameRegistry.registerTileEntity(TileStormCollector.class, new ResourceLocation(AdvancedTech.MODID, "storm_collector"));
        GameRegistry.registerTileEntity(TileLaserMiner.class, new ResourceLocation(AdvancedTech.MODID, "laser_miner"));
        GameRegistry.registerTileEntity(TileCable.class, new ResourceLocation(AdvancedTech.MODID, "cable"));

        // Блокировки крафта до изучения
        ResearchRegistry.lock("advancedtech:electric_furnace", "processing");
        ResearchRegistry.lock("advancedtech:crusher", "processing");
        ResearchRegistry.lock("advancedtech:compressor", "processing");
        ResearchRegistry.lock("advancedtech:alloy_smelter", "automation");
        ResearchRegistry.lock("advancedtech:combustion_generator", "electric_circuits");
        ResearchRegistry.lock("advancedtech:solar_panel", "solar_energy");
        ResearchRegistry.lock("advancedtech:air_filter", "pollution_control");
        ResearchRegistry.lock("advancedtech:cable_mv", "mv_tier");
        ResearchRegistry.lock("advancedtech:cable_hv", "hv_tier");
        ResearchRegistry.lock("advancedtech:cable_ev", "quantum_tech");
        ResearchRegistry.lock("advancedtech:bio_reactor", "alt_energy");
        ResearchRegistry.lock("advancedtech:geothermal_generator", "alt_energy");
        ResearchRegistry.lock("advancedtech:wind_turbine", "alt_energy");
        ResearchRegistry.lock("advancedtech:smog_reactor", "smog_tech");
        ResearchRegistry.lock("advancedtech:storm_collector", "hv_tier");
        ResearchRegistry.lock("advancedtech:laser_miner", "mining_tech");
        ResearchRegistry.lock("advancedtech:ore_scanner", "mining_tech");
        ResearchRegistry.lock("advancedtech:arc_rifle", "exo_tech");
        ResearchRegistry.lock("advancedtech:plasma_blade", "exo_tech");
        ResearchRegistry.lock("advancedtech:exo_helmet", "exo_tech");
        ResearchRegistry.lock("advancedtech:exo_chestplate", "exo_tech");
        ResearchRegistry.lock("advancedtech:exo_leggings", "exo_tech");
        ResearchRegistry.lock("advancedtech:exo_boots", "exo_tech");
        ResearchRegistry.lock("advancedtech:casing_upgrade_improved", "automation");
        ResearchRegistry.lock("advancedtech:casing_upgrade_industrial", "hv_tier");
        ResearchRegistry.lock("advancedtech:casing_upgrade_quantum", "quantum_tech");
    }

    // ---------------- предметы ----------------
    private static Item basic(String name, CreativeTabs tab) {
        Item i = new Item();
        i.setRegistryName(AdvancedTech.MODID, name);
        i.setTranslationKey(AdvancedTech.MODID + "." + name);
        i.setCreativeTab(tab);
        return i;
    }

    private static Item tool(Item i, String name) {
        i.setRegistryName(AdvancedTech.MODID, name);
        i.setTranslationKey(AdvancedTech.MODID + "." + name);
        i.setCreativeTab(CreativeTabs.TOOLS);
        return i;
    }

    private static Item add(net.minecraftforge.registries.IForgeRegistry<Item> r, String name, Item item, String ore) {
        r.register(item);
        ModItems.add(name, item);
        if (ore != null) OreDictionary.registerOre(ore, item);
        return item;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        net.minecraftforge.registries.IForgeRegistry<Item> r = e.getRegistry();

        for (String m : INGOT_METALS)
            add(r, "ingot_" + m, basic("ingot_" + m, CreativeTabs.MATERIALS), "ingot" + cap(m));
        for (String m : ALL_METALS) {
            add(r, "dust_" + m, basic("dust_" + m, CreativeTabs.MATERIALS), "dust" + cap(m));
            add(r, "plate_" + m, basic("plate_" + m, CreativeTabs.MATERIALS), "plate" + cap(m));
        }

        ModItems.ENGINEER_BOOK = add(r, "engineer_book", new ItemEngineerBook(), null);
        ModItems.CIRCUIT = add(r, "circuit", basic("circuit", CreativeTabs.MATERIALS), "circuitBasic");
        ModItems.AIR_FILTER = add(r, "air_filter", basic("air_filter", CreativeTabs.MISC).setMaxStackSize(1).setMaxDamage(1200), null);
        ModItems.BIOMASS = add(r, "biomass", basic("biomass", CreativeTabs.MATERIALS), null);
        ModItems.QUANTUM_CORE = add(r, "quantum_core", basic("quantum_core", CreativeTabs.MATERIALS), null);
        ModItems.CASINGS[0] = add(r, "casing_upgrade_improved", new ItemCasingUpgrade("casing_upgrade_improved", 1), null);
        ModItems.CASINGS[1] = add(r, "casing_upgrade_industrial", new ItemCasingUpgrade("casing_upgrade_industrial", 2), null);
        ModItems.CASINGS[2] = add(r, "casing_upgrade_quantum", new ItemCasingUpgrade("casing_upgrade_quantum", 3), null);

        // --- бронзовые инструменты ---
        ATMaterials.BRONZE_TOOL.setRepairItem(new ItemStack(ModItems.get("ingot_bronze")));
        ATMaterials.BRONZE_ARMOR.setRepairItem(new ItemStack(ModItems.get("ingot_bronze")));
        add(r, "bronze_pickaxe", new ItemATPickaxe("bronze_pickaxe", ATMaterials.BRONZE_TOOL), null);
        add(r, "bronze_axe", new ItemATAxe("bronze_axe", ATMaterials.BRONZE_TOOL), null);
        add(r, "bronze_shovel", tool(new ItemSpade(ATMaterials.BRONZE_TOOL), "bronze_shovel"), null);
        add(r, "bronze_hoe", tool(new ItemHoe(ATMaterials.BRONZE_TOOL), "bronze_hoe"), null);
        Item sword = tool(new ItemSword(ATMaterials.BRONZE_TOOL), "bronze_sword");
        sword.setCreativeTab(CreativeTabs.COMBAT);
        add(r, "bronze_sword", sword, null);
        add(r, "bronze_helmet", new ItemATArmor("bronze_helmet", ATMaterials.BRONZE_ARMOR, EntityEquipmentSlot.HEAD), null);
        add(r, "bronze_chestplate", new ItemATArmor("bronze_chestplate", ATMaterials.BRONZE_ARMOR, EntityEquipmentSlot.CHEST), null);
        add(r, "bronze_leggings", new ItemATArmor("bronze_leggings", ATMaterials.BRONZE_ARMOR, EntityEquipmentSlot.LEGS), null);
        add(r, "bronze_boots", new ItemATArmor("bronze_boots", ATMaterials.BRONZE_ARMOR, EntityEquipmentSlot.FEET), null);
        add(r, "energy_drink", new ItemEnergyDrink(), null);

        // --- v0.3: уникальные предметы мода ---
        add(r, "energy_cell", basic("energy_cell", CreativeTabs.MATERIALS), null);
        add(r, "composite_plate", basic("composite_plate", CreativeTabs.MATERIALS), null);
        add(r, "ore_scanner", new ItemOreScanner(), null);
        add(r, "arc_rifle", new ItemArcRifle(), null);
        add(r, "plasma_blade", new ItemPlasmaBlade(), null);
        add(r, "exo_helmet", new ItemExoArmor("exo_helmet", EntityEquipmentSlot.HEAD), null);
        add(r, "exo_chestplate", new ItemExoArmor("exo_chestplate", EntityEquipmentSlot.CHEST), null);
        add(r, "exo_leggings", new ItemExoArmor("exo_leggings", EntityEquipmentSlot.LEGS), null);
        add(r, "exo_boots", new ItemExoArmor("exo_boots", EntityEquipmentSlot.FEET), null);
        ATMaterials.EXO_ARMOR.setRepairItem(new ItemStack(ModItems.get("composite_plate")));
        ATMaterials.PLASMA.setRepairItem(new ItemStack(ModItems.get("composite_plate")));

        for (Block b : ModBlocks.ALL) {
            ItemBlock ib = new ItemBlock(b);
            ib.setRegistryName(b.getRegistryName());
            r.register(ib);
            ModItems.ALL.add(ib);
            if (b == ModBlocks.ORE_COPPER) OreDictionary.registerOre("oreCopper", ib);
            if (b == ModBlocks.ORE_TIN) OreDictionary.registerOre("oreTin", ib);
            if (b == ModBlocks.ORE_SILVER) OreDictionary.registerOre("oreSilver", ib);
            if (b == ModBlocks.BLOCK_COPPER) OreDictionary.registerOre("blockCopper", ib);
            if (b == ModBlocks.BLOCK_TIN) OreDictionary.registerOre("blockTin", ib);
            if (b == ModBlocks.BLOCK_SILVER) OreDictionary.registerOre("blockSilver", ib);
            if (b == ModBlocks.BLOCK_BRONZE) OreDictionary.registerOre("blockBronze", ib);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent e) {
        for (Item item : ModItems.ALL) {
            ModelLoader.setCustomModelResourceLocation(item, 0,
                    new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
