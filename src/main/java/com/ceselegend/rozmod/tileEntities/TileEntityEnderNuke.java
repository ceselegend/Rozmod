package com.ceselegend.rozmod.tileEntities;


import com.ceselegend.rozmod.handler.ConfigurationHandler;
import com.ceselegend.rozmod.handler.RozExplosion;
import com.ceselegend.rozmod.init.ModBlocks;

public class TileEntityEnderNuke extends TileEntityBomb{

    private RozExplosion explosion = new RozExplosion();
    public float coreRotation;
    public float red;
    public float blue;
    public float green;

    public TileEntityEnderNuke(){
        super();
        explosion.radius = ConfigurationHandler.enderNuke_radius;
        this.fuse = ConfigurationHandler.enderNuke_fuse;
        coreRotation = 0F;
        red =0.0F;
        green = 0F;
        blue = 0F;
    }

    @Override
    public void setPrimed() {
        this.primed = true;
        worldObj.addBlockEvent(xCoord,yCoord,zCoord,ModBlocks.enderNuke,2,0);
        explosion.getAffectedBlocks(worldObj,xCoord,yCoord,zCoord);
    }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            if (fuse == 0) {
                worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                worldObj.removeTileEntity(xCoord,yCoord,zCoord);
                this.doExplosion();
            }
            else if(fuse == 1) {
                worldObj.addBlockEvent(xCoord,yCoord,zCoord, ModBlocks.enderNuke,1,0);
            }
            if(primed){
                this.fuse--;
            }
        }
        else {
            if (primed) {
                worldObj.spawnParticle("portal", (double) xCoord, (double) yCoord + 1, (double) zCoord, 0, 0.5, 0);
            }
            coreRotation += ConfigurationHandler.enderNuke_coreRotation;
            red += ConfigurationHandler.enderNuke_redRotation;
            blue += ConfigurationHandler.enderNuke_blueRotation;
            green += ConfigurationHandler.enderNuke_greenRotation;
        }
    }

    @Override
    public boolean receiveClientEvent(int id, int value) {
        if(worldObj.isRemote && id == 1) {
            //worldObj.playSound(xCoord, yCoord, zCoord, "rozmod:nuclear_blast", 10, 0, false);
           //Particle fun here
        }
        else if(worldObj.isRemote && id == 2){
            if(!primed){
                this.primed = true;
            }
        }
        return true;
    }

    public void doExplosion(){

    }
}
