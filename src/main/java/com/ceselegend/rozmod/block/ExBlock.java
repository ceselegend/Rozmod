package com.ceselegend.rozmod.block;

public class ExBlock extends BlockRozmod {
    public ExBlock() {
        super();
        this.setBlockName("exempleBlock");
        this.setHardness(1.5F); //Block resistance when mined
        this.setResistance(10.0F); //Block resistance to explosion
    }
}
