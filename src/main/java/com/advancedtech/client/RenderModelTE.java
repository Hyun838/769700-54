package com.advancedtech.client;

import com.advancedtech.AdvancedTech;
import com.advancedtech.block.BlockMachine;
import com.advancedtech.tile.ISpinning;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Рисует тайл-механизм полноценной 3D-моделью (с вращающимися частями и светящимся слоем). */
@SideOnly(Side.CLIENT)
public class RenderModelTE<T extends TileEntity> extends TileEntitySpecialRenderer<T> {
    private final ModelBase model;
    private final ResourceLocation tex, glow;

    public RenderModelTE(ModelBase model, String name) {
        this.model = model;
        this.tex = new ResourceLocation(AdvancedTech.MODID, "textures/entity/" + name + ".png");
        this.glow = new ResourceLocation(AdvancedTech.MODID, "textures/entity/" + name + "_glow.png");
    }

    @Override
    public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        float spin = te instanceof ISpinning ? ((ISpinning) te).getSpin(partialTicks) : 0F;
        float age = (te.getWorld() != null ? te.getWorld().getTotalWorldTime() : 0) + partialTicks;
        float yaw = 0F;
        if (te.getWorld() != null) {
            IBlockState st = te.getWorld().getBlockState(te.getPos());
            if (st.getBlock() instanceof BlockMachine) {
                EnumFacing f = st.getValue(BlockMachine.FACING);
                yaw = f.getHorizontalAngle();
            }
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GlStateManager.scale(1.0F, -1.0F, -1.0F);
        GlStateManager.rotate(yaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.enableRescaleNormal();

        bindTexture(tex);
        model.render(null, spin, 0.0F, age, 0.0F, 0.0F, 0.0625F);

        // светящийся слой (полная яркость)
        bindTexture(glow);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
        model.render(null, spin, 0.0F, age, 0.0F, 0.0F, 0.0625F);
        GlStateManager.depthMask(true);
        if (te.getWorld() != null) {
            int l = te.getWorld().getCombinedLight(te.getPos(), 0);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) (l % 65536), (float) (l / 65536));
        }
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }
}
