package com.ceselegend.rozmod.render;


import com.ceselegend.rozmod.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderFatManItem implements IItemRenderer {

    private ModelFatMan model;
    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID,"textures/blocks/modelNuke.png");

    public RenderFatManItem(ModelFatMan model) {
        this.model = model;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        GL11.glScalef(-1F,-1F,1F);

        switch(type) {
            case ENTITY:
                GL11.glScalef(1.5F,1.5F,1.5F);
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glTranslatef(-0.2F,-0.8F,0.6F);
                break;
            case INVENTORY:
                GL11.glTranslatef(-0.2F,0,0);
                break;
            case EQUIPPED:
                GL11.glTranslatef(-0.2F,-0.2F,0.6F);
                GL11.glRotatef((float)180,0,1,0);
                break;
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.render(0.005F);

        GL11.glPopMatrix();
    }
}
