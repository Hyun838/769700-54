package com.advancedtech.client;

import com.advancedtech.AdvancedTech;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Универсальный рендер мобов мода: своя 3D-модель + текстура + масштаб + светящийся слой. */
@SideOnly(Side.CLIENT)
public class RenderMob<T extends EntityLiving> extends RenderLiving<T> {
    private final ResourceLocation texture;
    private final float scale;

    public RenderMob(RenderManager manager, ModelBase model, float shadow, String name, float scale) {
        super(manager, model, shadow);
        this.texture = new ResourceLocation(AdvancedTech.MODID, "textures/entity/" + name + ".png");
        this.scale = scale;
        addLayer(new LayerGlow<T>(model, new ResourceLocation(AdvancedTech.MODID, "textures/entity/" + name + "_glow.png")));
    }

    @Override
    protected ResourceLocation getEntityTexture(T entity) { return texture; }

    @Override
    protected void preRenderCallback(T entity, float partialTickTime) {
        GlStateManager.scale(scale, scale, scale);
    }
}
