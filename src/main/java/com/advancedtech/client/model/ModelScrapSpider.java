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
public class ModelScrapSpider extends ModelBase {
    private final ModelRenderer head, mandR, mandL, thorax, abdomen, exhaust, legR0, footR0, legL0, footL0, legR1, footR1, legL1, footL1, legR2, footR2, legL2, footL2, legR3, footR3, legL3, footL3;

    public ModelScrapSpider() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        head = new ModelRenderer(this, 44, 0);
        head.setRotationPoint(0F, 15F, -3F);
        head.addBox(-4F, -4F, -8F, 8, 8, 8);
        mandR = new ModelRenderer(this, 56, 20);
        mandR.setRotationPoint(0F, 0F, 0F);
        mandR.addBox(-3F, 2F, -9F, 2, 3, 1);
        head.addChild(mandR);
        mandL = new ModelRenderer(this, 62, 20);
        mandL.setRotationPoint(0F, 0F, 0F);
        mandL.addBox(1F, 2F, -9F, 2, 3, 1);
        head.addChild(mandL);
        thorax = new ModelRenderer(this, 76, 0);
        thorax.setRotationPoint(0F, 15F, 0F);
        thorax.addBox(-3F, -3F, -3F, 6, 6, 6);
        abdomen = new ModelRenderer(this, 0, 0);
        abdomen.setRotationPoint(0F, 15F, 9F);
        abdomen.addBox(-5F, -4F, -6F, 10, 8, 12);
        exhaust = new ModelRenderer(this, 40, 20);
        exhaust.setRotationPoint(0F, 0F, 0F);
        exhaust.addBox(-2F, -6F, 2F, 4, 2, 4);
        abdomen.addChild(exhaust);
        legR0 = new ModelRenderer(this, 68, 20);
        legR0.setRotationPoint(-4F, 15F, 2F);
        legR0.addBox(-15F, -1F, -1F, 16, 2, 2);
        footR0 = new ModelRenderer(this, 100, 0);
        footR0.setRotationPoint(-15F, 0F, 0F);
        footR0.addBox(-1F, 0F, -1F, 2, 9, 2);
        legR0.addChild(footR0);
        legL0 = new ModelRenderer(this, 0, 31);
        legL0.setRotationPoint(4F, 15F, 2F);
        legL0.addBox(-1F, -1F, -1F, 16, 2, 2);
        footL0 = new ModelRenderer(this, 108, 0);
        footL0.setRotationPoint(15F, 0F, 0F);
        footL0.addBox(-1F, 0F, -1F, 2, 9, 2);
        legL0.addChild(footL0);
        legR1 = new ModelRenderer(this, 36, 31);
        legR1.setRotationPoint(-4F, 15F, 1F);
        legR1.addBox(-15F, -1F, -1F, 16, 2, 2);
        footR1 = new ModelRenderer(this, 116, 0);
        footR1.setRotationPoint(-15F, 0F, 0F);
        footR1.addBox(-1F, 0F, -1F, 2, 9, 2);
        legR1.addChild(footR1);
        legL1 = new ModelRenderer(this, 72, 31);
        legL1.setRotationPoint(4F, 15F, 1F);
        legL1.addBox(-1F, -1F, -1F, 16, 2, 2);
        footL1 = new ModelRenderer(this, 0, 20);
        footL1.setRotationPoint(15F, 0F, 0F);
        footL1.addBox(-1F, 0F, -1F, 2, 9, 2);
        legL1.addChild(footL1);
        legR2 = new ModelRenderer(this, 0, 35);
        legR2.setRotationPoint(-4F, 15F, 0F);
        legR2.addBox(-15F, -1F, -1F, 16, 2, 2);
        footR2 = new ModelRenderer(this, 8, 20);
        footR2.setRotationPoint(-15F, 0F, 0F);
        footR2.addBox(-1F, 0F, -1F, 2, 9, 2);
        legR2.addChild(footR2);
        legL2 = new ModelRenderer(this, 36, 35);
        legL2.setRotationPoint(4F, 15F, 0F);
        legL2.addBox(-1F, -1F, -1F, 16, 2, 2);
        footL2 = new ModelRenderer(this, 16, 20);
        footL2.setRotationPoint(15F, 0F, 0F);
        footL2.addBox(-1F, 0F, -1F, 2, 9, 2);
        legL2.addChild(footL2);
        legR3 = new ModelRenderer(this, 72, 35);
        legR3.setRotationPoint(-4F, 15F, -1F);
        legR3.addBox(-15F, -1F, -1F, 16, 2, 2);
        footR3 = new ModelRenderer(this, 24, 20);
        footR3.setRotationPoint(-15F, 0F, 0F);
        footR3.addBox(-1F, 0F, -1F, 2, 9, 2);
        legR3.addChild(footR3);
        legL3 = new ModelRenderer(this, 0, 39);
        legL3.setRotationPoint(4F, 15F, -1F);
        legL3.addBox(-1F, -1F, -1F, 16, 2, 2);
        footL3 = new ModelRenderer(this, 32, 20);
        footL3.setRotationPoint(15F, 0F, 0F);
        footL3.addBox(-1F, 0F, -1F, 2, 9, 2);
        legL3.addChild(footL3);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        head.render(scale);
        thorax.render(scale);
        abdomen.render(scale);
        legR0.render(scale);
        legL0.render(scale);
        legR1.render(scale);
        legL1.render(scale);
        legR2.render(scale);
        legL2.render(scale);
        legR3.render(scale);
        legL3.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        head.rotateAngleY = netHeadYaw * 0.017453292F;
        head.rotateAngleX = headPitch * 0.017453292F;
        ModelRenderer[] lr = {legR0, legR1, legR2, legR3};
        ModelRenderer[] ll = {legL0, legL1, legL2, legL3};
        float[] baseY = {0.7F, 0.35F, -0.35F, -0.7F};
        float f = limbSwing * 1.3F;
        for (int i = 0; i < 4; i++) {
            float ph = f + i * 1.2F + (i % 2 == 0 ? 0.0F : 3.1416F);
            lr[i].rotateAngleY = baseY[i] + MathHelper.cos(ph) * 0.45F * limbSwingAmount;
            ll[i].rotateAngleY = -baseY[i] - MathHelper.cos(ph + 3.1416F) * 0.45F * limbSwingAmount;
            lr[i].rotateAngleZ = -0.75F + Math.max(0.0F, MathHelper.sin(ph)) * 0.4F * limbSwingAmount;
            ll[i].rotateAngleZ = 0.75F - Math.max(0.0F, MathHelper.sin(ph + 3.1416F)) * 0.4F * limbSwingAmount;
        }
        abdomen.rotateAngleX = MathHelper.sin(ageInTicks * 0.1F) * 0.04F;
    }
}
