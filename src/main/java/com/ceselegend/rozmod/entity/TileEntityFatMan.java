package com.ceselegend.rozmod.entity;

import com.ceselegend.rozmod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TileEntityFatMan extends TileEntity {

    private int fuse;
    private boolean primed;
    private String owner;
    private int radius = 32;
    public List<ChunkPosition> affectedBlockPositions = new ArrayList<ChunkPosition>();

    public TileEntityFatMan() {
        fuse = 100;
        primed = false;
        owner = null;
    }

    public void setPrimed() {
        this.primed = true;
        worldObj.addBlockEvent(xCoord,yCoord,zCoord,ModBlocks.rozFatMan,2,0);
        getAffectedBlocks();
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
        if (!worldObj.isRemote) {
            if (fuse == 0) {
                worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                worldObj.removeTileEntity(xCoord,yCoord,zCoord);
                this.explode(worldObj);
            }
            else if(fuse == 1) {
                worldObj.addBlockEvent(xCoord,yCoord,zCoord, ModBlocks.rozFatMan,1,0);
            }
            if(primed){
                this.fuse--;
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
            Iterator iterator = this.affectedBlockPositions.iterator();
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

    public void explode(World world) {
        Iterator iterator = this.affectedBlockPositions.iterator();
        ChunkPosition chunkPosition;
        int i,j,k;
        Block block;
        while (iterator.hasNext()) {
            chunkPosition = (ChunkPosition) iterator.next();
            i = chunkPosition.chunkPosX;
            j = chunkPosition.chunkPosY;
            k = chunkPosition.chunkPosZ;
            block = this.worldObj.getBlock(i, j, k);
            if (block.getMaterial() != Material.air && block.getBlockHardness(world, xCoord, yCoord, zCoord) != -1.0F) {
                block.dropBlockAsItemWithChance(world, i, j, k, 0, 0.01F, 0);
                world.setBlockToAir(i, j, k);
            }
        }

        //Damage entities in the blast area
        radius *= (double)3/2;
        int x1 = xCoord-radius-1;
        int x2 = xCoord+radius+1;
        int y1 = yCoord-radius-1;
        int y2 = yCoord+radius+1;
        int z1 = zCoord-radius-1;
        int z2 = zCoord+radius+1;
        List list = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(x1,y1,z1,x2,y2,z2));
        for (Object aList : list) {
            Entity entity = (Entity) aList;
            double dist = entity.getDistance(xCoord, yCoord, zCoord) / (double) radius;
            if (dist <= 1.0F) {
                entity.attackEntityFrom(DamageSource.setExplosionSource(null), (float) (radius));
                entity.motionX += entity.posX - xCoord;
                entity.motionY += entity.posY - yCoord - entity.getEyeHeight();
                entity.motionZ += entity.posZ - zCoord;
            }
        }
    }

    public void getAffectedBlocks() {
        int i, j, k;
        for (i = xCoord - radius; i <= xCoord + radius; i++) {
            for (j = yCoord - radius; j <= yCoord + radius; j++) {
                for (k = zCoord - radius; k <= zCoord + radius; k++) {
                    if (StrictMath.sqrt((i - xCoord) * (i - xCoord) + (j - yCoord) * (j - yCoord) + (k - zCoord) * (k - zCoord)) <= radius) {
                        this.affectedBlockPositions.add(new ChunkPosition(i, j, k));
                    }
                }
            }
        }
    }
}
