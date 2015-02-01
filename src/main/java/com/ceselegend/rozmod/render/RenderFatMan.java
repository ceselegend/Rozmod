package com.ceselegend.rozmod.render;


import com.ceselegend.rozmod.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderFatMan extends TileEntitySpecialRenderer {

    private ModelFatMan model;
    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID,"textures/blocks/modelNuke.png");

    public RenderFatMan(ModelFatMan model){
        this.model = model;
    }

    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float partialTickTIme) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F,(float)y + 0.5F,(float)z + 0.5F);
        GL11.glScalef(-1F,-1F,1F);

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.render(0.005F);

        GL11.glPopMatrix();
    }
}
