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
public class ModelLaserMiner extends ModelBase {
    private final ModelRenderer pillar0, crystal0, pillar1, crystal1, pillar2, crystal2, pillar3, crystal3, plate, floor, tower, mastA, dish, drill, bit, tip;

    public ModelLaserMiner() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        pillar0 = new ModelRenderer(this, 0, 0);
        pillar0.setRotationPoint(0F, 0F, 0F);
        pillar0.addBox(-7F, 8F, -7F, 3, 16, 3);
        crystal0 = new ModelRenderer(this, 12, 35);
        crystal0.setRotationPoint(0F, 0F, 0F);
        crystal0.addBox(-6.5F, 5F, -6.5F, 2, 3, 2);
        pillar1 = new ModelRenderer(this, 12, 0);
        pillar1.setRotationPoint(0F, 0F, 0F);
        pillar1.addBox(4F, 8F, -7F, 3, 16, 3);
        crystal1 = new ModelRenderer(this, 20, 35);
        crystal1.setRotationPoint(0F, 0F, 0F);
        crystal1.addBox(4.5F, 5F, -6.5F, 2, 3, 2);
        pillar2 = new ModelRenderer(this, 24, 0);
        pillar2.setRotationPoint(0F, 0F, 0F);
        pillar2.addBox(-7F, 8F, 4F, 3, 16, 3);
        crystal2 = new ModelRenderer(this, 28, 35);
        crystal2.setRotationPoint(0F, 0F, 0F);
        crystal2.addBox(-6.5F, 5F, 4.5F, 2, 3, 2);
        pillar3 = new ModelRenderer(this, 36, 0);
        pillar3.setRotationPoint(0F, 0F, 0F);
        pillar3.addBox(4F, 8F, 4F, 3, 16, 3);
        crystal3 = new ModelRenderer(this, 36, 35);
        crystal3.setRotationPoint(0F, 0F, 0F);
        crystal3.addBox(4.5F, 5F, 4.5F, 2, 3, 2);
        plate = new ModelRenderer(this, 0, 19);
        plate.setRotationPoint(0F, 0F, 0F);
        plate.addBox(-7F, 6F, -7F, 14, 2, 14);
        floor = new ModelRenderer(this, 56, 19);
        floor.setRotationPoint(0F, 0F, 0F);
        floor.addBox(-7F, 23F, -7F, 14, 1, 14);
        tower = new ModelRenderer(this, 48, 0);
        tower.setRotationPoint(0F, 0F, 0F);
        tower.addBox(-4F, -5F, -4F, 8, 11, 8);
        mastA = new ModelRenderer(this, 8, 35);
        mastA.setRotationPoint(0F, 0F, 0F);
        mastA.addBox(-0.5F, -11F, -0.5F, 1, 6, 1);
        dish = new ModelRenderer(this, 44, 35);
        dish.setRotationPoint(0F, -11F, 0F);
        dish.addBox(-3F, -2F, -1F, 6, 3, 2);
        drill = new ModelRenderer(this, 112, 19);
        drill.setRotationPoint(0F, 8F, 0F);
        drill.addBox(-2F, 0F, -2F, 4, 5, 4);
        bit = new ModelRenderer(this, 0, 35);
        bit.setRotationPoint(0F, 0F, 0F);
        bit.addBox(-1F, 5F, -1F, 2, 7, 2);
        drill.addChild(bit);
        tip = new ModelRenderer(this, 60, 35);
        tip.setRotationPoint(0F, 0F, 0F);
        tip.addBox(-0.5F, 12F, -0.5F, 1, 4, 1);
        drill.addChild(tip);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        pillar0.render(scale);
        crystal0.render(scale);
        pillar1.render(scale);
        crystal1.render(scale);
        pillar2.render(scale);
        crystal2.render(scale);
        pillar3.render(scale);
        crystal3.render(scale);
        plate.render(scale);
        floor.render(scale);
        tower.render(scale);
        mastA.render(scale);
        dish.render(scale);
        drill.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        dish.rotateAngleY = ageInTicks * 0.08F;
        drill.rotateAngleY = limbSwing;
    }
}
