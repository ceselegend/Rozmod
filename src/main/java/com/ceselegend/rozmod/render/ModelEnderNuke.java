package com.ceselegend.rozmod.render;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import java.util.ArrayList;

public class ModelEnderNuke extends ModelBase{

    private ArrayList<ModelRenderer> parts;

    public ModelEnderNuke() {
        parts = new ArrayList<ModelRenderer>();
        textureWidth = 1024;
        textureHeight = 1024;
    }

    public void render(float mult) {
        for(ModelRenderer part : parts){
            part.render(mult);
        }
    }
}
