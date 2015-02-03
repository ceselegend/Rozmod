package com.ceselegend.rozmod.entity;

import com.ceselegend.rozmod.handler.RozExplosion;
import com.ceselegend.rozmod.init.ModBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkPosition;

import java.util.Iterator;

public class TileEntityFatMan extends TileEntity {
    private boolean primed;
    private String owner;

    public TileEntityFatMan() {
        explosion.radius = 32;
        explosion.forceInit = 64.0D;
        explosion.fuse = 100;
        primed = false;
        owner = null;
    }

    public RozExplosion explosion = new RozExplosion();

    public void setPrimed() {
        this.primed = true;
        worldObj.addBlockEvent(xCoord,yCoord,zCoord,ModBlocks.rozFatMan,2,0);
        explosion.getAffectedBlocks(worldObj, xCoord, yCoord, zCoord);
    }

    public boolean getPrimed() {
        return primed;
    }

    public void setOwner(String owner){
        this.owner  = owner;
    }

    public String getOwner(){
        return this.owner;
    }

    @Override
    public void writeToNBT(NBTTagCompound coumpound) {
        super.writeToNBT(coumpound);

        coumpound.setShort("fuse",(short)explosion.fuse);
        coumpound.setBoolean("primed",primed);
    }

    @Override
    public void readFromNBT(NBTTagCompound coumpond) {
        super.readFromNBT(coumpond);

        explosion.fuse = coumpond.getShort("fuse");
        primed = coumpond.getBoolean("primed");
    }

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            if (explosion.fuse == 0) {
                worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                worldObj.removeTileEntity(xCoord,yCoord,zCoord);
                explosion.explode(worldObj, this, xCoord, yCoord, zCoord);
            }
            else if(explosion.fuse == 1) {
                worldObj.addBlockEvent(xCoord,yCoord,zCoord, ModBlocks.rozFatMan,1,0);
            }
            if(primed){
                this.explosion.fuse--;
            }
        }
        else {
            if (primed) {
                worldObj.spawnParticle("heart", (double) xCoord + worldObj.rand.nextDouble(), (double) yCoord + 1, (double) zCoord + worldObj.rand.nextDouble(), 0, 0.5, 0);
            }
        }
    }

    @Override
    public boolean receiveClientEvent(int id, int value) {
        if(worldObj.isRemote && id == 1) {
            worldObj.playSound(xCoord, yCoord, zCoord, "rozmod:nuclear_blast", 10, 0, false);
            int i,j,k;
            Iterator iterator = explosion.affectedBlockPositions.iterator();
            ChunkPosition chunkPosition;
            while (iterator.hasNext()) {
                chunkPosition = (ChunkPosition) iterator.next();
                i = chunkPosition.chunkPosX;
                j = chunkPosition.chunkPosY;
                k = chunkPosition.chunkPosZ;
                worldObj.spawnParticle("largeexplode", i, j, k, 1.0D * worldObj.rand.nextDouble(), 1.0D * worldObj.rand.nextDouble(), 1.0D * worldObj.rand.nextDouble());
            }
        }
        else if(worldObj.isRemote && id == 2){
            if(!primed){
                this.primed = true;
            }
        }
        return true;
    }




}
