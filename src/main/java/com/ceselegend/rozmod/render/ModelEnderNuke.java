package com.ceselegend.rozmod.render;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class ModelEnderNuke extends ModelBase{

    private ArrayList<ModelRenderer> parts;
    private ModelRenderer core;

    public ModelEnderNuke() {
        parts = new ArrayList<ModelRenderer>();
        textureWidth = 1024;
        textureHeight = 1024;

        ModelRenderer caps = new ModelRenderer(this,0,0);
        caps.addBox(-100,60,-100,200,40,200);
        caps.addBox(-100,-100,-100,200,40,200);
        parts.add(caps);

        for(int r = 0; r<4; r++){
            ModelRenderer sides = new ModelRenderer(this,800,0);
            sides.addBox(60,-60,60,40,120,40);
            sides.rotateAngleY = r*(float)Math.PI/2;
            parts.add(sides);
        }

        core = new ModelRenderer(this,0,240);
        core.addBox(-30,-30,-30,60,60,60);
        core.setRotationPoint(0,0,0);
        core.rotateAngleX = (float)Math.PI/4;
        core.rotateAngleZ = (float)Math.PI/4;
    }

    public void render(float coreRotation,float mult) {
        for(ModelRenderer part : parts){
            part.render(mult);
        }
        GL11.glRotatef(coreRotation*(180/3),0,1,0);
        core.render(mult);
    }
}
