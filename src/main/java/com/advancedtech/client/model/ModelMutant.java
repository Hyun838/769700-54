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
public class ModelMutant extends ModelBase {
    private final ModelRenderer body, hump, belly, tumorA, tumorB, tumorC, pipeA, pipeB, pipeTipA, pipeTipB, head, armR, fistR, armL, handL, legR, footR, legL, footL;

    public ModelMutant() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        body = new ModelRenderer(this, 24, 0);
        body.setRotationPoint(0F, 13F, 0F);
        body.addBox(-5F, -13F, -3F, 10, 13, 6);
        hump = new ModelRenderer(this, 52, 22);
        hump.setRotationPoint(0F, 0F, 0F);
        hump.addBox(-3F, -17F, 0F, 6, 5, 5);
        body.addChild(hump);
        belly = new ModelRenderer(this, 58, 34);
        belly.setRotationPoint(0F, 0F, 0F);
        belly.addBox(-4F, -6F, -3.5F, 8, 5, 1);
        body.addChild(belly);
        tumorA = new ModelRenderer(this, 88, 34);
        tumorA.setRotationPoint(0F, 0F, 0F);
        tumorA.addBox(3F, -10F, -3.5F, 3, 3, 2);
        body.addChild(tumorA);
        tumorB = new ModelRenderer(this, 46, 34);
        tumorB.setRotationPoint(0F, 0F, 0F);
        tumorB.addBox(-6F, -6F, 0F, 3, 4, 3);
        body.addChild(tumorB);
        tumorC = new ModelRenderer(this, 76, 34);
        tumorC.setRotationPoint(0F, 0F, 0F);
        tumorC.addBox(-2F, -13F, 2.5F, 3, 3, 3);
        body.addChild(tumorC);
        pipeA = new ModelRenderer(this, 112, 0);
        pipeA.setRotationPoint(0F, 0F, 0F);
        pipeA.addBox(-4F, -12F, 3F, 2, 10, 2);
        body.addChild(pipeA);
        pipeB = new ModelRenderer(this, 74, 22);
        pipeB.setRotationPoint(0F, 0F, 0F);
        pipeB.addBox(2F, -12F, 3F, 2, 8, 2);
        body.addChild(pipeB);
        pipeTipA = new ModelRenderer(this, 98, 34);
        pipeTipA.setRotationPoint(0F, 0F, 0F);
        pipeTipA.addBox(-4.5F, -13F, 2.5F, 3, 1, 3);
        body.addChild(pipeTipA);
        pipeTipB = new ModelRenderer(this, 110, 34);
        pipeTipB.setRotationPoint(0F, 0F, 0F);
        pipeTipB.addBox(1.5F, -13F, 2.5F, 3, 1, 3);
        body.addChild(pipeTipB);
        head = new ModelRenderer(this, 0, 22);
        head.setRotationPoint(0F, -12F, -2F);
        head.addBox(-3F, -5F, -3F, 6, 6, 6);
        body.addChild(head);
        armR = new ModelRenderer(this, 0, 0);
        armR.setRotationPoint(-7F, -10F, 0F);
        armR.addBox(-3F, -2F, -3F, 6, 16, 6);
        body.addChild(armR);
        fistR = new ModelRenderer(this, 24, 22);
        fistR.setRotationPoint(0F, 0F, 0F);
        fistR.addBox(-3.5F, 13F, -3.5F, 7, 5, 7);
        armR.addChild(fistR);
        armL = new ModelRenderer(this, 56, 0);
        armL.setRotationPoint(7F, -10F, 0F);
        armL.addBox(-2F, -2F, -2F, 4, 13, 4);
        body.addChild(armL);
        handL = new ModelRenderer(this, 26, 34);
        handL.setRotationPoint(0F, 0F, 0F);
        handL.addBox(-2.5F, 10F, -2.5F, 5, 4, 5);
        armL.addChild(handL);
        legR = new ModelRenderer(this, 72, 0);
        legR.setRotationPoint(-3F, 13F, 0F);
        legR.addBox(-2.5F, 0F, -2.5F, 5, 11, 5);
        footR = new ModelRenderer(this, 82, 22);
        footR.setRotationPoint(0F, 0F, 0F);
        footR.addBox(-3F, 8F, -4F, 6, 3, 7);
        legR.addChild(footR);
        legL = new ModelRenderer(this, 92, 0);
        legL.setRotationPoint(3F, 13F, 0F);
        legL.addBox(-2.5F, 0F, -2.5F, 5, 11, 5);
        footL = new ModelRenderer(this, 0, 34);
        footL.setRotationPoint(0F, 0F, 0F);
        footL.addBox(-3F, 8F, -4F, 6, 3, 7);
        legL.addChild(footL);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        body.render(scale);
        legR.render(scale);
        legL.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        float s = MathHelper.cos(limbSwing * 0.6662F);
        boolean att = entity instanceof EntityLiving && ((EntityLiving) entity).getAttackTarget() != null;
        body.rotateAngleX = 0.42F + MathHelper.sin(ageInTicks * 0.08F) * 0.03F;
        body.rotateAngleZ = s * 0.07F * limbSwingAmount;
        head.rotateAngleX = headPitch * 0.017453292F - body.rotateAngleX + 0.12F;
        head.rotateAngleY = netHeadYaw * 0.017453292F;
        legR.rotateAngleX = s * 1.1F * limbSwingAmount;
        legL.rotateAngleX = -s * 1.1F * limbSwingAmount;
        if (att) {
            armR.rotateAngleX = -1.25F + MathHelper.sin(ageInTicks * 0.3F) * 0.28F;
            armL.rotateAngleX = -1.0F - MathHelper.sin(ageInTicks * 0.3F) * 0.28F;
        } else {
            armR.rotateAngleX = -s * 0.5F * limbSwingAmount - 0.1F + MathHelper.sin(ageInTicks * 0.06F) * 0.05F;
            armL.rotateAngleX = s * 0.5F * limbSwingAmount - 0.2F - MathHelper.sin(ageInTicks * 0.06F) * 0.05F;
        }
        armR.rotateAngleZ = 0.1F;
        armL.rotateAngleZ = -0.1F;
        tumorA.rotateAngleZ = MathHelper.sin(ageInTicks * 0.2F) * 0.1F;
        tumorB.rotateAngleZ = MathHelper.sin(ageInTicks * 0.17F + 1.0F) * 0.1F;
    }
}
