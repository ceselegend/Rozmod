package com.ceselegend.rozmod.tileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;


public abstract class TileEntityBomb extends TileEntity {

    protected String owner;
    protected int fuse;
    protected boolean primed;

    public TileEntityBomb(){
        this.fuse = 100;
        this.primed = false;
        this.owner = null;
    }

    public boolean getPrimed() {
        return primed;
    }

    public abstract void setPrimed();

    public void setOwner(String owner){
        this.owner  = owner;
    }

    public String getOwner(){
        return this.owner;
    }

    @Override
    public void writeToNBT(NBTTagCompound coumpound) {
        super.writeToNBT(coumpound);

        coumpound.setShort("fuse",(short)fuse);
        coumpound.setBoolean("primed",primed);
        coumpound.setString("owner",owner);
    }

    @Override
    public void readFromNBT(NBTTagCompound coumpond) {
        super.readFromNBT(coumpond);

        fuse = coumpond.getShort("fuse");
        primed = coumpond.getBoolean("primed");
        owner = coumpond.getString("owner");
    }
}
