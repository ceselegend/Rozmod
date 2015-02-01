package com.ceselegend.rozmod.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import java.util.ArrayList;


public class ModelFatMan extends ModelBase {

    private ArrayList<ModelRenderer> parts;

    public ModelFatMan() {
        parts = new ArrayList<ModelRenderer>();
        textureWidth = 1024;
        textureHeight = 1024;

        ModelRenderer main = new ModelRenderer(this,0,0);
        main.addBox(-80F,-70F,-70F,160,140,140);
        main.setRotationPoint(0,0,0);
        parts.add(main);

        ModelRenderer frontBack  = new ModelRenderer(this,0,280);
        frontBack.addBox(-100,-50,-50,20,100,100);
        frontBack.addBox(80,-50,-50,20,100,100);
        frontBack.setRotationPoint(0,0,0);
        parts.add(frontBack);

        ModelRenderer front = new ModelRenderer(this,240,280);
        front.addBox(-115,-40,-40,15,80,80);
        front.setRotationPoint(0,0,0);
        parts.add(front);

        for(int r = 0;r < 4;r++){
            ModelRenderer rotHelper = new ModelRenderer(this,0,0);
            ModelRenderer fin = new ModelRenderer(this,430,280);
            fin.addBox(-10,-5,-5,60,10,10);
            rotHelper.addChild(fin);
            rotHelper.setRotationPoint(0,0,0);
            fin.setRotationPoint(100,50,-50);
            fin.rotateAngleZ = (float)Math.PI/8;
            fin.rotateAngleY = (float)Math.PI/8;
            rotHelper.rotateAngleX = (float)(r*Math.PI/2);
            parts.add(rotHelper);

            ModelRenderer tail = new ModelRenderer(this,430,300);
            tail.addBox(-15,72-5F,-77,30,10,154);
            tail.setRotationPoint(90+55.43F-(r % 2 == 0 ? 0 : 0.01F),(r % 2 == 0 ? 0 : 0.01F)-(r == 0 ? 0 : 0.03F),-(r % 2 == 0 ? 0 : 0.01F)-(r == 1 ? 0 : 0.03F));
            tail.rotateAngleX = (float)(r*Math.PI/2);
            parts.add(tail);

        }

    }

    public void render(float mult) {
        for(ModelRenderer part : parts){
            part.render(mult);
        }
    }
}
