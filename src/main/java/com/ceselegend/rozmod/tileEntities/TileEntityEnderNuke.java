package com.ceselegend.rozmod.tileEntities;


import com.ceselegend.rozmod.handler.RozExplosion;

public class TileEntityEnderNuke extends TileEntityBomb{

    private RozExplosion explosion = new RozExplosion();

    public TileEntityEnderNuke(){
        super();
        explosion.radius = 32;
    }

    @Override
    public void setPrimed() {
        this.primed = true;
        //TODO client event here
        explosion.getAffectedBlocks(worldObj,xCoord,yCoord,zCoord);
    }
}
