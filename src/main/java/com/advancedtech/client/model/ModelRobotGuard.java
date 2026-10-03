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
public class ModelRobotGuard extends ModelBase {
    private final ModelRenderer body, chest, pack, belt, head, visor, earR, earL, ant, antTip, armR, padR, cannon, barrel, muzzle, armL, padL, handL, legR, kneeR, bootR, legL, kneeL, bootL;

    public ModelRobotGuard() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        body = new ModelRenderer(this, 0, 0);
        body.setRotationPoint(0F, 0F, 0F);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        chest = new ModelRenderer(this, 48, 28);
        chest.setRotationPoint(0F, 0F, 0F);
        chest.addBox(-3F, 1F, -3F, 6, 5, 1);
        body.addChild(chest);
        pack = new ModelRenderer(this, 0, 16);
        pack.setRotationPoint(0F, 0F, 0F);
        pack.addBox(-3F, 1F, 2F, 6, 9, 3);
        body.addChild(pack);
        belt = new ModelRenderer(this, 0, 28);
        belt.setRotationPoint(0F, 0F, 0F);
        belt.addBox(-4.5F, 9F, -2.5F, 9, 2, 5);
        body.addChild(belt);
        head = new ModelRenderer(this, 24, 0);
        head.setRotationPoint(0F, 0F, 0F);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        visor = new ModelRenderer(this, 114, 28);
        visor.setRotationPoint(0F, 0F, 0F);
        visor.addBox(-3F, -6F, -5F, 6, 2, 1);
        head.addChild(visor);
        earR = new ModelRenderer(this, 62, 28);
        earR.setRotationPoint(0F, 0F, 0F);
        earR.addBox(-5F, -5F, -1F, 1, 3, 2);
        head.addChild(earR);
        earL = new ModelRenderer(this, 68, 28);
        earL.setRotationPoint(0F, 0F, 0F);
        earL.addBox(4F, -5F, -1F, 1, 3, 2);
        head.addChild(earL);
        ant = new ModelRenderer(this, 74, 28);
        ant.setRotationPoint(0F, 0F, 0F);
        ant.addBox(2F, -12F, 0F, 1, 4, 1);
        head.addChild(ant);
        antTip = new ModelRenderer(this, 0, 35);
        antTip.setRotationPoint(0F, 0F, 0F);
        antTip.addBox(1.5F, -13F, -0.5F, 2, 1, 2);
        head.addChild(antTip);
        armR = new ModelRenderer(this, 88, 0);
        armR.setRotationPoint(-5F, 2F, 0F);
        armR.addBox(-3F, -2F, -2F, 4, 10, 4);
        padR = new ModelRenderer(this, 82, 16);
        padR.setRotationPoint(0F, 0F, 0F);
        padR.addBox(-3.5F, -3F, -2.5F, 5, 3, 5);
        armR.addChild(padR);
        cannon = new ModelRenderer(this, 18, 16);
        cannon.setRotationPoint(0F, 0F, 0F);
        cannon.addBox(-2.5F, 8F, -2.5F, 5, 4, 5);
        armR.addChild(cannon);
        barrel = new ModelRenderer(this, 28, 28);
        barrel.setRotationPoint(0F, 0F, 0F);
        barrel.addBox(-1F, 12F, -1F, 2, 5, 2);
        armR.addChild(barrel);
        muzzle = new ModelRenderer(this, 78, 28);
        muzzle.setRotationPoint(0F, 0F, 0F);
        muzzle.addBox(-1.5F, 17F, -1.5F, 3, 1, 3);
        armR.addChild(muzzle);
        armL = new ModelRenderer(this, 104, 0);
        armL.setRotationPoint(5F, 2F, 0F);
        armL.addBox(-1F, -2F, -2F, 4, 10, 4);
        padL = new ModelRenderer(this, 102, 16);
        padL.setRotationPoint(0F, 0F, 0F);
        padL.addBox(-1.5F, -3F, -2.5F, 5, 3, 5);
        armL.addChild(padL);
        handL = new ModelRenderer(this, 36, 28);
        handL.setRotationPoint(0F, 0F, 0F);
        handL.addBox(-1.5F, 8F, -1.5F, 3, 4, 3);
        armL.addChild(handL);
        legR = new ModelRenderer(this, 56, 0);
        legR.setRotationPoint(-1.9F, 12F, 0F);
        legR.addBox(-2F, 0F, -2F, 4, 12, 4);
        kneeR = new ModelRenderer(this, 90, 28);
        kneeR.setRotationPoint(0F, 0F, 0F);
        kneeR.addBox(-2.5F, 3F, -3F, 5, 3, 1);
        legR.addChild(kneeR);
        bootR = new ModelRenderer(this, 38, 16);
        bootR.setRotationPoint(0F, 0F, 0F);
        bootR.addBox(-2.5F, 9F, -3F, 5, 3, 6);
        legR.addChild(bootR);
        legL = new ModelRenderer(this, 72, 0);
        legL.setRotationPoint(1.9F, 12F, 0F);
        legL.addBox(-2F, 0F, -2F, 4, 12, 4);
        kneeL = new ModelRenderer(this, 102, 28);
        kneeL.setRotationPoint(0F, 0F, 0F);
        kneeL.addBox(-2.5F, 3F, -3F, 5, 3, 1);
        legL.addChild(kneeL);
        bootL = new ModelRenderer(this, 60, 16);
        bootL.setRotationPoint(0F, 0F, 0F);
        bootL.addBox(-2.5F, 9F, -3F, 5, 3, 6);
        legL.addChild(bootL);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        body.render(scale);
        head.render(scale);
        armR.render(scale);
        armL.render(scale);
        legR.render(scale);
        legL.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        head.rotateAngleY = netHeadYaw * 0.017453292F;
        head.rotateAngleX = headPitch * 0.017453292F;
        float s = MathHelper.cos(limbSwing * 0.6662F);
        legR.rotateAngleX = s * 1.2F * limbSwingAmount;
        legL.rotateAngleX = -s * 1.2F * limbSwingAmount;
        armL.rotateAngleX = s * 0.9F * limbSwingAmount;
        armL.rotateAngleZ = -0.06F;
        boolean aim = entity instanceof EntityLiving && ((EntityLiving) entity).getAttackTarget() != null;
        if (aim) {
            armR.rotateAngleX = -1.5707F + head.rotateAngleX * 0.8F;
            armR.rotateAngleY = 0.1F;
        } else {
            armR.rotateAngleX = -s * 0.9F * limbSwingAmount;
            armR.rotateAngleY = 0.0F;
        }
        armR.rotateAngleZ = 0.06F;
        ant.rotateAngleZ = MathHelper.sin(ageInTicks * 0.12F) * 0.08F;
        antTip.rotateAngleZ = ant.rotateAngleZ;
    }
}
