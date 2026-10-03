package com.advancedtech;

import com.advancedtech.item.ItemExoArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Экзо-ботинки гасят 60% урона от падения. */
@Mod.EventBusSubscriber(modid = AdvancedTech.MODID)
public class ExoEvents {
    @SubscribeEvent
    public static void onFall(LivingFallEvent e) {
        if (!(e.getEntityLiving() instanceof EntityPlayer)) return;
        if (ItemExoArmor.has((EntityPlayer) e.getEntityLiving(), EntityEquipmentSlot.FEET))
            e.setDistance(e.getDistance() * 0.4F);
    }
}
