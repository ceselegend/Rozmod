package com.ceselegend.rozmod.render;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;


import java.util.ArrayList;

public class ModelEnderNuke extends ModelBase{

    private ArrayList<ModelRenderer> parts;
    private ModelRenderer sides;
    private ModelRenderer core;
    private ModelRenderer bottom;

    public ModelEnderNuke() {
        parts = new ArrayList<ModelRenderer>();
        textureWidth = 1024;
        textureHeight = 1024;

        bottom = new ModelRenderer(this,0,0);
        bottom.addBox(-100, 60, -100, 200, 40, 200);

        sides = new ModelRenderer(this,0,0);
        for(int r = 0; r<4; r++){
            ModelRenderer side = new ModelRenderer(this,800,0);
            side.addBox(50,-50,50,20,100,20);
            side.rotateAngleY = r*(float)Math.PI/2;
            sides.addChild(side);
        }
        sides.setRotationPoint(0,-10,0);

        core = new ModelRenderer(this,0,240);
        core.addBox(-30,-40,-30,60,60,60);
        core.setRotationPoint(0,0,0);
        core.rotateAngleX = (float)Math.PI/4;
        core.rotateAngleZ = (float)Math.PI/4;
    }

    public void render(float coreRotation,float red,float green, float blue,boolean primed,float mult) {
        for(ModelRenderer part : parts){
            part.render(mult);
        }
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(1F, 1F, 1F, 0.5F);
        sides.rotateAngleY=coreRotation*3;
        sides.render(mult);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor3d(Math.cos(3*red+0.5) * Math.cos(3*red+0.5), 0.2, 0.6);
        bottom.render(mult);
        if(!primed){
            GL11.glColor3d(Math.cos(red) * Math.cos(red), Math.cos(green) * Math.cos(green), Math.cos(blue) * Math.cos(blue));
            GL11.glRotatef(coreRotation * (180 / 3), 0, 1, 0);
            core.setRotationPoint(0,(float)Math.cos(5*coreRotation)*10,0);
        }
        else {
            GL11.glColor3d(Math.cos(10*red) * Math.cos(10*red),0.1, 0.1);
            GL11.glRotatef(5*coreRotation * (180 / 3), 0, 1, 0);
            core.setRotationPoint(0,(float)Math.cos(5*5*coreRotation)*10,0);
        }
        core.render(mult);
    }
}
