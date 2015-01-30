package com.ceselegend.rozmod.init;

import com.ceselegend.rozmod.block.BlockRozmod;
import com.ceselegend.rozmod.block.CageBlock;
import com.ceselegend.rozmod.block.RozNuke;
import com.ceselegend.rozmod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    public static final BlockRozmod cageBlock = new CageBlock();
    public static final BlockRozmod rozNuke = new RozNuke(15);

    public static void init() {
        GameRegistry.registerBlock(cageBlock, "cageBlock");
        GameRegistry.registerBlock(rozNuke, "rozNuke");
    }

}
