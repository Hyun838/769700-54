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
public class ModelWindTurbine extends ModelBase {
    private final ModelRenderer foot, base, mast, nacelle, tail, beacon, rotor, blade0, blade1, blade2;

    public ModelWindTurbine() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        foot = new ModelRenderer(this, 16, 0);
        foot.setRotationPoint(0F, 0F, 0F);
        foot.addBox(-7F, 21F, -7F, 14, 3, 14);
        base = new ModelRenderer(this, 72, 0);
        base.setRotationPoint(0F, 0F, 0F);
        base.addBox(-4F, 14F, -4F, 8, 8, 8);
        mast = new ModelRenderer(this, 0, 0);
        mast.setRotationPoint(0F, 0F, 0F);
        mast.addBox(-2F, -6F, -2F, 4, 21, 4);
        nacelle = new ModelRenderer(this, 0, 25);
        nacelle.setRotationPoint(0F, -7F, 0F);
        nacelle.addBox(-3F, -3F, -5F, 6, 6, 10);
        tail = new ModelRenderer(this, 56, 25);
        tail.setRotationPoint(0F, 0F, 0F);
        tail.addBox(-0.5F, -5F, 4F, 1, 7, 6);
        nacelle.addChild(tail);
        beacon = new ModelRenderer(this, 84, 25);
        beacon.setRotationPoint(0F, 0F, 0F);
        beacon.addBox(-1F, -5F, -1F, 2, 2, 2);
        nacelle.addChild(beacon);
        rotor = new ModelRenderer(this, 70, 25);
        rotor.setRotationPoint(0F, -7F, -5F);
        rotor.addBox(-2F, -2F, -2F, 4, 4, 3);
        blade0 = new ModelRenderer(this, 32, 25);
        blade0.setRotationPoint(0F, 0F, 0F);
        blade0.addBox(-1.5F, -16F, -0.5F, 3, 14, 1);
        rotor.addChild(blade0);
        blade1 = new ModelRenderer(this, 40, 25);
        blade1.setRotationPoint(0F, 0F, 0F);
        blade1.addBox(-1.5F, -16F, -0.5F, 3, 14, 1);
        blade1.rotateAngleZ = 2.094F;
        rotor.addChild(blade1);
        blade2 = new ModelRenderer(this, 48, 25);
        blade2.setRotationPoint(0F, 0F, 0F);
        blade2.addBox(-1.5F, -16F, -0.5F, 3, 14, 1);
        blade2.rotateAngleZ = 4.189F;
        rotor.addChild(blade2);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        foot.render(scale);
        base.render(scale);
        mast.render(scale);
        nacelle.render(scale);
        rotor.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        rotor.rotateAngleZ = limbSwing;
    }
}
