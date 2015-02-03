package com.ceselegend.rozmod.render;


import com.ceselegend.rozmod.reference.Reference;
import com.ceselegend.rozmod.tileEntities.TileEntityEnderNuke;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderEnderNuke extends TileEntitySpecialRenderer{

    private ModelEnderNuke model;
    private ResourceLocation texture = new ResourceLocation(Reference.MOD_ID,"textures/blocks/modelEnderNuke.png");

    public RenderEnderNuke(ModelEnderNuke model){
        this.model = model;
    }
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTickTime) {
        TileEntityEnderNuke enderNuke = (TileEntityEnderNuke) tileEntity;

        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F,(float)y + 0.5F,(float)z + 0.5F);
        GL11.glScalef(-1F,-1F,1F);

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.render(enderNuke.coreRotation,0.005F);

        GL11.glPopMatrix();
    }
}
