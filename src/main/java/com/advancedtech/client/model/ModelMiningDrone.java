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
public class ModelMiningDrone extends ModelBase {
    private final ModelRenderer body, eye, belly, drill, bit, ant, antTip, arm0, hub0, bladeA0, bladeB0, arm1, hub1, bladeA1, bladeB1, arm2, hub2, bladeA2, bladeB2, arm3, hub3, bladeA3, bladeB3;

    public ModelMiningDrone() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        body = new ModelRenderer(this, 0, 0);
        body.setRotationPoint(0F, 14F, 0F);
        body.addBox(-5F, -3F, -5F, 10, 6, 10);
        eye = new ModelRenderer(this, 0, 38);
        eye.setRotationPoint(0F, 0F, 0F);
        eye.addBox(-3F, -2F, -6F, 6, 4, 1);
        body.addChild(eye);
        belly = new ModelRenderer(this, 22, 27);
        belly.setRotationPoint(0F, 0F, 0F);
        belly.addBox(-3F, 3F, -3F, 6, 2, 6);
        body.addChild(belly);
        drill = new ModelRenderer(this, 46, 27);
        drill.setRotationPoint(0F, 0F, 0F);
        drill.addBox(-1F, 5F, -1F, 2, 4, 2);
        body.addChild(drill);
        bit = new ModelRenderer(this, 14, 38);
        bit.setRotationPoint(0F, 0F, 0F);
        bit.addBox(-0.5F, 9F, -0.5F, 1, 3, 1);
        body.addChild(bit);
        ant = new ModelRenderer(this, 54, 27);
        ant.setRotationPoint(0F, 0F, 0F);
        ant.addBox(-0.5F, -8F, -0.5F, 1, 5, 1);
        body.addChild(ant);
        antTip = new ModelRenderer(this, 28, 47);
        antTip.setRotationPoint(0F, 0F, 0F);
        antTip.addBox(-1F, -9F, -1F, 2, 1, 2);
        body.addChild(antTip);
        arm0 = new ModelRenderer(this, 18, 38);
        arm0.setRotationPoint(0F, 0F, 0F);
        arm0.addBox(0F, -1F, -1F, 8, 2, 2);
        arm0.rotateAngleY = -0.785F;
        body.addChild(arm0);
        hub0 = new ModelRenderer(this, 38, 38);
        hub0.setRotationPoint(8F, -2F, 0F);
        hub0.addBox(-1F, -1F, -1F, 2, 2, 2);
        arm0.addChild(hub0);
        bladeA0 = new ModelRenderer(this, 36, 47);
        bladeA0.setRotationPoint(0F, -1.5F, 0F);
        bladeA0.addBox(-5F, 0F, -0.5F, 10, 1, 1);
        hub0.addChild(bladeA0);
        bladeB0 = new ModelRenderer(this, 40, 0);
        bladeB0.setRotationPoint(0F, 0F, 0F);
        bladeB0.addBox(-0.5F, 0F, -5F, 1, 1, 10);
        bladeA0.addChild(bladeB0);
        arm1 = new ModelRenderer(this, 0, 43);
        arm1.setRotationPoint(0F, 0F, 0F);
        arm1.addBox(0F, -1F, -1F, 8, 2, 2);
        arm1.rotateAngleY = -2.356F;
        body.addChild(arm1);
        hub1 = new ModelRenderer(this, 20, 43);
        hub1.setRotationPoint(8F, -2F, 0F);
        hub1.addBox(-1F, -1F, -1F, 2, 2, 2);
        arm1.addChild(hub1);
        bladeA1 = new ModelRenderer(this, 0, 51);
        bladeA1.setRotationPoint(0F, -1.5F, 0F);
        bladeA1.addBox(-5F, 0F, -0.5F, 10, 1, 1);
        hub1.addChild(bladeA1);
        bladeB1 = new ModelRenderer(this, 0, 16);
        bladeB1.setRotationPoint(0F, 0F, 0F);
        bladeB1.addBox(-0.5F, 0F, -5F, 1, 1, 10);
        bladeA1.addChild(bladeB1);
        arm2 = new ModelRenderer(this, 28, 43);
        arm2.setRotationPoint(0F, 0F, 0F);
        arm2.addBox(0F, -1F, -1F, 8, 2, 2);
        arm2.rotateAngleY = -3.927F;
        body.addChild(arm2);
        hub2 = new ModelRenderer(this, 48, 43);
        hub2.setRotationPoint(8F, -2F, 0F);
        hub2.addBox(-1F, -1F, -1F, 2, 2, 2);
        arm2.addChild(hub2);
        bladeA2 = new ModelRenderer(this, 22, 51);
        bladeA2.setRotationPoint(0F, -1.5F, 0F);
        bladeA2.addBox(-5F, 0F, -0.5F, 10, 1, 1);
        hub2.addChild(bladeA2);
        bladeB2 = new ModelRenderer(this, 22, 16);
        bladeB2.setRotationPoint(0F, 0F, 0F);
        bladeB2.addBox(-0.5F, 0F, -5F, 1, 1, 10);
        bladeA2.addChild(bladeB2);
        arm3 = new ModelRenderer(this, 0, 47);
        arm3.setRotationPoint(0F, 0F, 0F);
        arm3.addBox(0F, -1F, -1F, 8, 2, 2);
        arm3.rotateAngleY = -5.498F;
        body.addChild(arm3);
        hub3 = new ModelRenderer(this, 20, 47);
        hub3.setRotationPoint(8F, -2F, 0F);
        hub3.addBox(-1F, -1F, -1F, 2, 2, 2);
        arm3.addChild(hub3);
        bladeA3 = new ModelRenderer(this, 0, 53);
        bladeA3.setRotationPoint(0F, -1.5F, 0F);
        bladeA3.addBox(-5F, 0F, -0.5F, 10, 1, 1);
        hub3.addChild(bladeA3);
        bladeB3 = new ModelRenderer(this, 0, 27);
        bladeB3.setRotationPoint(0F, 0F, 0F);
        bladeB3.addBox(-0.5F, 0F, -5F, 1, 1, 10);
        bladeA3.addChild(bladeB3);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        body.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        body.rotationPointY = 14.0F + MathHelper.sin(ageInTicks * 0.15F) * 1.2F;
        body.rotateAngleY = netHeadYaw * 0.017453292F * 0.5F;
        body.rotateAngleX = MathHelper.clamp(limbSwingAmount, 0.0F, 1.0F) * 0.35F + headPitch * 0.008F;
        body.rotateAngleZ = MathHelper.sin(ageInTicks * 0.1F) * 0.05F;
        float spin = ageInTicks * 1.7F;
        bladeA0.rotateAngleY = spin; bladeA1.rotateAngleY = -spin; bladeA2.rotateAngleY = spin; bladeA3.rotateAngleY = -spin;
        antTip.rotateAngleY = spin * 0.3F;
        bit.rotateAngleY = spin * 2.0F;
    }
}
