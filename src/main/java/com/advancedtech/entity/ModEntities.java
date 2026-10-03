package com.advancedtech.entity;

import com.advancedtech.AdvancedTech;
import com.advancedtech.config.ATConfig;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModEntities {

    public static void register() {
        int id = 0;
        EntityRegistry.registerModEntity(new ResourceLocation(AdvancedTech.MODID, "robot_guard"),
                EntityRobotGuard.class, "robot_guard", id++, AdvancedTech.instance, 64, 3, true, 0x8A8A8A, 0xFF2200);
        EntityRegistry.registerModEntity(new ResourceLocation(AdvancedTech.MODID, "mutant"),
                EntityMutant.class, "mutant", id++, AdvancedTech.instance, 64, 3, true, 0x3B6B1F, 0xB5D400);
        EntityRegistry.registerModEntity(new ResourceLocation(AdvancedTech.MODID, "mechanical_golem"),
                EntityMechanicalGolem.class, "mechanical_golem", id++, AdvancedTech.instance, 80, 3, true, 0x333333, 0xFF8800);
        EntityRegistry.registerModEntity(new ResourceLocation(AdvancedTech.MODID, "mining_drone"),
                EntityMiningDrone.class, "mining_drone", id++, AdvancedTech.instance, 64, 3, true, 0xC8A020, 0x30343C);
        EntityRegistry.registerModEntity(new ResourceLocation(AdvancedTech.MODID, "scrap_spider"),
                EntityScrapSpider.class, "scrap_spider", id++, AdvancedTech.instance, 64, 3, true, 0x4A4F58, 0x00E5FF);
        EntityRegistry.registerModEntity(new ResourceLocation(AdvancedTech.MODID, "spore_bloat"),
                EntitySporeBloat.class, "spore_bloat", id++, AdvancedTech.instance, 64, 3, true, 0x6FB32A, 0xE8D84A);
    }

    public static void registerSpawns() {
        if (!ATConfig.enableMobSpawns) return;
        Biome[] all = ForgeRegistries.BIOMES.getValuesCollection().toArray(new Biome[0]);
        EntityRegistry.addSpawn(EntityRobotGuard.class, 3, 1, 1, EnumCreatureType.MONSTER, all);
        EntityRegistry.addSpawn(EntityMiningDrone.class, 4, 1, 2, EnumCreatureType.MONSTER, all);
        EntityRegistry.addSpawn(EntityScrapSpider.class, 5, 1, 2, EnumCreatureType.MONSTER, all);
        Biome[] swamps = BiomeDictionary.getBiomes(BiomeDictionary.Type.SWAMP).toArray(new Biome[0]);
        if (swamps.length > 0) EntityRegistry.addSpawn(EntityMutant.class, 20, 1, 3, EnumCreatureType.MONSTER, swamps);
        if (swamps.length > 0) EntityRegistry.addSpawn(EntitySporeBloat.class, 14, 1, 2, EnumCreatureType.MONSTER, swamps);
    }
}
