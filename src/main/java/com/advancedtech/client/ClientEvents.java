package com.advancedtech.client;

import com.advancedtech.AdvancedTech;
import com.advancedtech.client.model.ModelLaserMiner;
import com.advancedtech.client.model.ModelMechanicalGolem;
import com.advancedtech.client.model.ModelScrapSpider;
import com.advancedtech.client.model.ModelSporeBloat;
import com.advancedtech.client.model.ModelStormCollector;
import com.advancedtech.client.model.ModelWindTurbine;
import com.advancedtech.entity.EntityScrapSpider;
import com.advancedtech.entity.EntitySporeBloat;
import com.advancedtech.tile.TileLaserMiner;
import com.advancedtech.tile.TileStormCollector;
import com.advancedtech.tile.TileWindTurbine;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import com.advancedtech.client.model.ModelMiningDrone;
import com.advancedtech.client.model.ModelMutant;
import com.advancedtech.client.model.ModelRobotGuard;
import com.advancedtech.entity.EntityMechanicalGolem;
import com.advancedtech.entity.EntityMiningDrone;
import com.advancedtech.entity.EntityMutant;
import com.advancedtech.entity.EntityRobotGuard;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = AdvancedTech.MODID)
public class ClientEvents {
    @SubscribeEvent
    public static void registerRenderers(ModelRegistryEvent e) {
        RenderingRegistry.registerEntityRenderingHandler(EntityRobotGuard.class,
                m -> new RenderMob<EntityRobotGuard>(m, new ModelRobotGuard(), 0.6F, "robot_guard", 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityMutant.class,
                m -> new RenderMob<EntityMutant>(m, new ModelMutant(), 0.7F, "mutant", 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityMechanicalGolem.class,
                m -> new RenderMob<EntityMechanicalGolem>(m, new ModelMechanicalGolem(), 1.6F, "mechanical_golem", 1.6F));
        RenderingRegistry.registerEntityRenderingHandler(EntityMiningDrone.class,
                m -> new RenderMob<EntityMiningDrone>(m, new ModelMiningDrone(), 0.4F, "mining_drone", 0.9F));
        RenderingRegistry.registerEntityRenderingHandler(EntityScrapSpider.class,
                m -> new RenderMob<EntityScrapSpider>(m, new ModelScrapSpider(), 0.9F, "scrap_spider", 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntitySporeBloat.class,
                m -> new RenderMob<EntitySporeBloat>(m, new ModelSporeBloat(), 0.5F, "spore_bloat", 1.0F));

        ClientRegistry.bindTileEntitySpecialRenderer(TileWindTurbine.class, new RenderModelTE<TileWindTurbine>(new ModelWindTurbine(), "wind_turbine"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileStormCollector.class, new RenderModelTE<TileStormCollector>(new ModelStormCollector(), "storm_collector"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileLaserMiner.class, new RenderModelTE<TileLaserMiner>(new ModelLaserMiner(), "laser_miner"));
    }
}
