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
public class ModelMechanicalGolem extends ModelBase {
    private final ModelRenderer torso, core, plateR, plateL, exhaustR, exhaustL, hip, head, hornR, hornL, jaw, armR, podR, foreR, armL, podL, foreL, legR, kneeR, footR, legL, kneeL, footL;

    public ModelMechanicalGolem() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        torso = new ModelRenderer(this, 0, 0);
        torso.setRotationPoint(0F, 0F, 0F);
        torso.addBox(-8F, 0F, -5F, 16, 13, 10);
        core = new ModelRenderer(this, 108, 57);
        core.setRotationPoint(0F, 0F, 0F);
        core.addBox(-3F, 3F, -6F, 6, 6, 1);
        torso.addChild(core);
        plateR = new ModelRenderer(this, 0, 69);
        plateR.setRotationPoint(0F, 0F, 0F);
        plateR.addBox(-7F, 1F, -6F, 4, 6, 1);
        torso.addChild(plateR);
        plateL = new ModelRenderer(this, 10, 69);
        plateL.setRotationPoint(0F, 0F, 0F);
        plateL.addBox(3F, 1F, -6F, 4, 6, 1);
        torso.addChild(plateL);
        exhaustR = new ModelRenderer(this, 32, 41);
        exhaustR.setRotationPoint(0F, 0F, 0F);
        exhaustR.addBox(-6F, -3F, 5F, 3, 11, 3);
        torso.addChild(exhaustR);
        exhaustL = new ModelRenderer(this, 44, 41);
        exhaustL.setRotationPoint(0F, 0F, 0F);
        exhaustL.addBox(3F, -3F, 5F, 3, 11, 3);
        torso.addChild(exhaustL);
        hip = new ModelRenderer(this, 68, 57);
        hip.setRotationPoint(0F, 0F, 0F);
        hip.addBox(-6F, 12F, -4F, 12, 2, 8);
        torso.addChild(hip);
        head = new ModelRenderer(this, 0, 41);
        head.setRotationPoint(0F, -1F, -1F);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        hornR = new ModelRenderer(this, 20, 69);
        hornR.setRotationPoint(0F, 0F, 0F);
        hornR.addBox(-5F, -10F, -1F, 1, 4, 1);
        head.addChild(hornR);
        hornL = new ModelRenderer(this, 24, 69);
        hornL.setRotationPoint(0F, 0F, 0F);
        hornL.addBox(4F, -10F, -1F, 1, 4, 1);
        head.addChild(hornL);
        jaw = new ModelRenderer(this, 64, 69);
        jaw.setRotationPoint(0F, 0F, 0F);
        jaw.addBox(-3F, 0F, -4.5F, 6, 2, 1);
        head.addChild(jaw);
        armR = new ModelRenderer(this, 52, 0);
        armR.setRotationPoint(-11F, 3F, 0F);
        armR.addBox(-4F, -3F, -4F, 8, 13, 8);
        podR = new ModelRenderer(this, 56, 41);
        podR.setRotationPoint(0F, 0F, 0F);
        podR.addBox(-4F, -9F, -4F, 8, 6, 8);
        armR.addChild(podR);
        foreR = new ModelRenderer(this, 0, 23);
        foreR.setRotationPoint(0F, 10F, 0F);
        foreR.addBox(-4.5F, 0F, -4.5F, 9, 9, 9);
        armR.addChild(foreR);
        armL = new ModelRenderer(this, 84, 0);
        armL.setRotationPoint(11F, 3F, 0F);
        armL.addBox(-4F, -3F, -4F, 8, 13, 8);
        podL = new ModelRenderer(this, 88, 41);
        podL.setRotationPoint(0F, 0F, 0F);
        podL.addBox(-4F, -9F, -4F, 8, 6, 8);
        armL.addChild(podL);
        foreL = new ModelRenderer(this, 36, 23);
        foreL.setRotationPoint(0F, 10F, 0F);
        foreL.addBox(-4.5F, 0F, -4.5F, 9, 9, 9);
        armL.addChild(foreL);
        legR = new ModelRenderer(this, 72, 23);
        legR.setRotationPoint(-4.5F, 13F, 0F);
        legR.addBox(-3.5F, 0F, -3.5F, 7, 11, 7);
        kneeR = new ModelRenderer(this, 28, 69);
        kneeR.setRotationPoint(0F, 0F, 0F);
        kneeR.addBox(-4F, 3F, -4.5F, 8, 3, 1);
        legR.addChild(kneeR);
        footR = new ModelRenderer(this, 0, 57);
        footR.setRotationPoint(0F, 0F, 0F);
        footR.addBox(-4F, 8F, -5F, 8, 3, 9);
        legR.addChild(footR);
        legL = new ModelRenderer(this, 100, 23);
        legL.setRotationPoint(4.5F, 13F, 0F);
        legL.addBox(-3.5F, 0F, -3.5F, 7, 11, 7);
        kneeL = new ModelRenderer(this, 46, 69);
        kneeL.setRotationPoint(0F, 0F, 0F);
        kneeL.addBox(-4F, 3F, -4.5F, 8, 3, 1);
        legL.addChild(kneeL);
        footL = new ModelRenderer(this, 34, 57);
        footL.setRotationPoint(0F, 0F, 0F);
        footL.addBox(-4F, 8F, -5F, 8, 3, 9);
        legL.addChild(footL);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        torso.render(scale);
        head.render(scale);
        armR.render(scale);
        armL.render(scale);
        legR.render(scale);
        legL.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        float s = MathHelper.cos(limbSwing * 0.5F);
        boolean att = entity instanceof EntityLiving && ((EntityLiving) entity).getAttackTarget() != null;
        head.rotateAngleY = netHeadYaw * 0.0125F;
        head.rotateAngleX = headPitch * 0.0125F;
        legR.rotateAngleX = s * 0.9F * limbSwingAmount;
        legL.rotateAngleX = -s * 0.9F * limbSwingAmount;
        torso.rotateAngleZ = s * 0.04F * limbSwingAmount;
        if (att) {
            armR.rotateAngleX = -1.75F + MathHelper.sin(ageInTicks * 0.15F) * 0.05F;
            armL.rotateAngleX = -1.75F - MathHelper.sin(ageInTicks * 0.15F) * 0.05F;
        } else {
            armR.rotateAngleX = -s * 0.7F * limbSwingAmount;
            armL.rotateAngleX = s * 0.7F * limbSwingAmount;
        }
        armR.rotateAngleZ = 0.05F;
        armL.rotateAngleZ = -0.05F;
        foreR.rotateAngleX = att ? -0.2F : 0.0F;
        foreL.rotateAngleX = att ? -0.2F : 0.0F;
    }
}
