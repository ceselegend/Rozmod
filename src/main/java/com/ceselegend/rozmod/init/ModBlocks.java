package com.ceselegend.rozmod.init;

import com.ceselegend.rozmod.block.BlockRozmod;
import com.ceselegend.rozmod.block.CageBlock;
import com.ceselegend.rozmod.block.EnderNuke;
import com.ceselegend.rozmod.block.RozFatMan;
import com.ceselegend.rozmod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    public static final BlockRozmod cageBlock = new CageBlock();
    public static final RozFatMan rozFatMan = new RozFatMan();
    public static final EnderNuke enderNuke = new EnderNuke();

    public static void init() {
        GameRegistry.registerBlock(cageBlock, "cageBlock");
        GameRegistry.registerBlock(rozFatMan,"rozFatman");
        GameRegistry.registerBlock(enderNuke,"enderNuke");
    }

}
