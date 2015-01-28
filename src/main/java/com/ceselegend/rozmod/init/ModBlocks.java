package com.ceselegend.rozmod.init;

import com.ceselegend.rozmod.block.BlockRozmod;
import com.ceselegend.rozmod.block.ExBlock;
import com.ceselegend.rozmod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    public static final BlockRozmod exBlock = new ExBlock();

    public static void init() {
        GameRegistry.registerBlock(exBlock, "exempleBlock");
    }

}
