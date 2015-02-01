package com.ceselegend.rozmod.entity;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFatMan extends TileEntity {

    private int fuse;
    private boolean primed;

    public TileEntityFatMan() {
        fuse = 100;
        primed = false;
    }

    public void setPrimed() {
        this.primed = true;
    }

    public boolean getPrimed() {
        return primed;
    }

    @Override
    public void writeToNBT(NBTTagCompound coumpound) {
        super.writeToNBT(coumpound);

        coumpound.setShort("fuse",(short)fuse);
        coumpound.setBoolean("primed",primed);
    }

    @Override
    public void readFromNBT(NBTTagCompound coumpond) {
        super.readFromNBT(coumpond);

        fuse = coumpond.getShort("fuse");
        primed = coumpond.getBoolean("primed");
    }

    @Override
    public void updateEntity() {
        if(!worldObj.isRemote) {
            if(this.primed && fuse > 0) {
                this.fuse -= 1;

            }else if(fuse == 0){
                worldObj.setBlockToAir(xCoord,yCoord,zCoord);
                worldObj.removeTileEntity(xCoord,yCoord,zCoord);
            }
        }else {
            if(primed){
                worldObj.spawnParticle("heart",(double)xCoord+worldObj.rand.nextDouble(),(double)yCoord+1,(double)zCoord+worldObj.rand.nextDouble(),0,0.5,0);
            }
        }
    }
}
