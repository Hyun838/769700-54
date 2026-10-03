package com.advancedtech.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Сгенерировано из спецификации (tools/mobs.py). UV-раскладка совпадает с текстурой. */
@SideOnly(Side.CLIENT)
public class ModelStormCollector extends ModelBase {
    private final ModelRenderer foot, housing, coil, cross1, cross2, top, orb, spike, spikeTip;

    public ModelStormCollector() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        foot = new ModelRenderer(this, 64, 0);
        foot.setRotationPoint(0F, 0F, 0F);
        foot.addBox(-7F, 21F, -7F, 14, 3, 14);
        housing = new ModelRenderer(this, 24, 0);
        housing.setRotationPoint(0F, 0F, 0F);
        housing.addBox(-5F, 12F, -5F, 10, 9, 10);
        coil = new ModelRenderer(this, 0, 0);
        coil.setRotationPoint(0F, 0F, 0F);
        coil.addBox(-3F, -4F, -3F, 6, 16, 6);
        cross1 = new ModelRenderer(this, 92, 22);
        cross1.setRotationPoint(0F, 6F, 0F);
        cross1.addBox(-7F, -1F, -1F, 14, 1, 2);
        cross2 = new ModelRenderer(this, 0, 22);
        cross2.setRotationPoint(0F, 0F, 0F);
        cross2.addBox(-1F, -1F, -7F, 2, 1, 14);
        top = new ModelRenderer(this, 32, 22);
        top.setRotationPoint(0F, 0F, 0F);
        top.addBox(-4F, -10F, -4F, 8, 6, 8);
        orb = new ModelRenderer(this, 64, 22);
        orb.setRotationPoint(0F, 0F, 0F);
        orb.addBox(-3F, -9F, -3F, 6, 4, 6);
        spike = new ModelRenderer(this, 88, 22);
        spike.setRotationPoint(0F, 0F, 0F);
        spike.addBox(-0.5F, -18F, -0.5F, 1, 8, 1);
        spikeTip = new ModelRenderer(this, 0, 37);
        spikeTip.setRotationPoint(0F, 0F, 0F);
        spikeTip.addBox(-1F, -19F, -1F, 2, 1, 2);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        foot.render(scale);
        housing.render(scale);
        coil.render(scale);
        cross1.render(scale);
        cross2.render(scale);
        top.render(scale);
        orb.render(scale);
        spike.render(scale);
        spikeTip.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        cross1.rotateAngleY = ageInTicks * 0.11F;
        cross2.rotateAngleY = -ageInTicks * 0.17F;
        spikeTip.rotateAngleY = ageInTicks * 0.3F;
    }
}
