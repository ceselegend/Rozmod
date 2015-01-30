package com.ceselegend.rozmod.render;

import com.ceselegend.rozmod.entity.RozNukePrimed;
import com.ceselegend.rozmod.init.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderRozNukePrimed extends Render {
    private RenderBlocks blockRenderer = new RenderBlocks();
    private static final ResourceLocation entityTexture = new ResourceLocation("rozmod:RozNuke_side");

    public RenderRozNukePrimed() {
        this.shadowSize = 0.5F;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(RozNukePrimed nuke, double x, double y, double z, float par5, float par6) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)z);
        float f2;

        if ((float)nuke.fuse - par6 + 1.0F < 10.0F) {
            f2 = 1.0F - ((float)nuke.fuse - par6 + 1.0F) / 10.0F;

            if (f2 < 0.0F) {
                f2 = 0.0F;
            }

            if (f2 > 1.0F) {
                f2 = 1.0F;
            }

            f2 *= f2;
            f2 *= f2;
            float f3 = 1.0F + f2 * 0.3F;
            GL11.glScalef(f3, f3, f3);
        }

        f2 = (1.0F - ((float)nuke.fuse - par6 + 1.0F) / 100.0F) * 0.8F;
        this.bindEntityTexture(nuke);
        this.blockRenderer.renderBlockAsItem(ModBlocks.rozNuke, 0, nuke.getBrightness(par6));

        if (nuke.fuse / 5 % 2 == 0) {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f2);
            this.blockRenderer.renderBlockAsItem(ModBlocks.rozNuke, 0, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }

        GL11.glPopMatrix();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(RozNukePrimed nuke) {
        return entityTexture;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getEntityTexture((RozNukePrimed)entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity entity, double x, double y, double z, float par5, float par6) {
        this.doRender((RozNukePrimed)entity, x, y, z, par5, par6);
    }
}
