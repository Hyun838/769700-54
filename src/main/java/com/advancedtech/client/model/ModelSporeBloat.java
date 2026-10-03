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
public class ModelSporeBloat extends ModelBase {
    private final ModelRenderer bladder, pod1, pod2, pod3, spikeA, spikeB, spikeC, tend0, tendTip0, tend1, tendTip1, tend2, tendTip2, tend3, tendTip3, tend4, tendTip4;

    public ModelSporeBloat() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        bladder = new ModelRenderer(this, 0, 0);
        bladder.setRotationPoint(0F, 12F, 0F);
        bladder.addBox(-6F, -6F, -6F, 12, 12, 12);
        pod1 = new ModelRenderer(this, 24, 24);
        pod1.setRotationPoint(0F, 0F, 0F);
        pod1.addBox(-7F, -3F, -2F, 2, 4, 4);
        bladder.addChild(pod1);
        pod2 = new ModelRenderer(this, 36, 24);
        pod2.setRotationPoint(0F, 0F, 0F);
        pod2.addBox(5F, -2F, 1F, 2, 4, 4);
        bladder.addChild(pod2);
        pod3 = new ModelRenderer(this, 48, 24);
        pod3.setRotationPoint(0F, 0F, 0F);
        pod3.addBox(-2F, -8F, 1F, 4, 3, 4);
        bladder.addChild(pod3);
        spikeA = new ModelRenderer(this, 0, 35);
        spikeA.setRotationPoint(0F, 0F, 0F);
        spikeA.addBox(-5F, -9F, -4F, 2, 3, 2);
        bladder.addChild(spikeA);
        spikeB = new ModelRenderer(this, 8, 35);
        spikeB.setRotationPoint(0F, 0F, 0F);
        spikeB.addBox(3F, -9F, -4F, 2, 3, 2);
        bladder.addChild(spikeB);
        spikeC = new ModelRenderer(this, 16, 35);
        spikeC.setRotationPoint(0F, 0F, 0F);
        spikeC.addBox(-1F, -9F, 3F, 2, 3, 2);
        bladder.addChild(spikeC);
        tend0 = new ModelRenderer(this, 48, 0);
        tend0.setRotationPoint(-3F, 6F, -3F);
        tend0.addBox(-1F, 0F, -1F, 2, 9, 2);
        bladder.addChild(tend0);
        tendTip0 = new ModelRenderer(this, 24, 35);
        tendTip0.setRotationPoint(0F, 9F, 0F);
        tendTip0.addBox(-1.5F, 0F, -1.5F, 3, 2, 3);
        tend0.addChild(tendTip0);
        tend1 = new ModelRenderer(this, 56, 0);
        tend1.setRotationPoint(3F, 6F, -3F);
        tend1.addBox(-1F, 0F, -1F, 2, 9, 2);
        bladder.addChild(tend1);
        tendTip1 = new ModelRenderer(this, 36, 35);
        tendTip1.setRotationPoint(0F, 9F, 0F);
        tendTip1.addBox(-1.5F, 0F, -1.5F, 3, 2, 3);
        tend1.addChild(tendTip1);
        tend2 = new ModelRenderer(this, 0, 24);
        tend2.setRotationPoint(-3F, 6F, 3F);
        tend2.addBox(-1F, 0F, -1F, 2, 9, 2);
        bladder.addChild(tend2);
        tendTip2 = new ModelRenderer(this, 48, 35);
        tendTip2.setRotationPoint(0F, 9F, 0F);
        tendTip2.addBox(-1.5F, 0F, -1.5F, 3, 2, 3);
        tend2.addChild(tendTip2);
        tend3 = new ModelRenderer(this, 8, 24);
        tend3.setRotationPoint(3F, 6F, 3F);
        tend3.addBox(-1F, 0F, -1F, 2, 9, 2);
        bladder.addChild(tend3);
        tendTip3 = new ModelRenderer(this, 0, 40);
        tendTip3.setRotationPoint(0F, 9F, 0F);
        tendTip3.addBox(-1.5F, 0F, -1.5F, 3, 2, 3);
        tend3.addChild(tendTip3);
        tend4 = new ModelRenderer(this, 16, 24);
        tend4.setRotationPoint(0F, 6F, 0F);
        tend4.addBox(-1F, 0F, -1F, 2, 9, 2);
        bladder.addChild(tend4);
        tendTip4 = new ModelRenderer(this, 12, 40);
        tendTip4.setRotationPoint(0F, 9F, 0F);
        tendTip4.addBox(-1.5F, 0F, -1.5F, 3, 2, 3);
        tend4.addChild(tendTip4);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        bladder.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        bladder.rotationPointY = 12.0F + MathHelper.sin(ageInTicks * 0.1F) * 1.5F;
        bladder.rotateAngleY = netHeadYaw * 0.017453292F * 0.6F;
        bladder.rotateAngleX = headPitch * 0.008F;
        ModelRenderer[] t = {tend0, tend1, tend2, tend3, tend4};
        for (int i = 0; i < 5; i++) {
            t[i].rotateAngleX = MathHelper.sin(ageInTicks * 0.13F + i * 1.3F) * 0.28F;
            t[i].rotateAngleZ = MathHelper.cos(ageInTicks * 0.11F + i * 1.9F) * 0.28F;
        }
        spikeA.rotateAngleZ = MathHelper.sin(ageInTicks * 0.2F) * 0.1F;
        spikeB.rotateAngleZ = -MathHelper.sin(ageInTicks * 0.2F) * 0.1F;
    }
}
