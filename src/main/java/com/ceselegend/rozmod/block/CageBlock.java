package com.ceselegend.rozmod.block;

public class CageBlock extends BlockRozmod {
    public CageBlock() {
        super();
        this.setBlockName("cageBlock");
        this.setHardness(20.0F); //Block resistance when mined
        this.setResistance(100.0F); //Block resistance to explosion
        this.setLightLevel(1.0F); //Block light level 1 -> 15
    }
}
