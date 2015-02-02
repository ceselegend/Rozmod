package com.ceselegend.rozmod.entity;


import com.ceselegend.rozmod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.List;


public class TileEntityFatMan extends TileEntity {

    private int fuse;
    private boolean primed;

    public TileEntityFatMan() {
        fuse = 100;
        primed = false;
    }

    public void setPrimed() {
        this.primed = true;
        worldObj.addBlockEvent(xCoord,yCoord,zCoord,ModBlocks.rozFatMan,2,0);
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
        if (!worldObj.isRemote) {
            if (fuse == 0) {
                worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                worldObj.removeTileEntity(xCoord,yCoord,zCoord);
                this.explode(worldObj, 32, xCoord, yCoord, zCoord);
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
        if(worldObj.isRemote && id == 1){
            worldObj.playSound(xCoord,yCoord,zCoord,"rozmod:nuclear_blast",10,0,false);
            // Insert particle code here
        }
        else if(worldObj.isRemote && id == 2){
            if(!primed){
                this.primed = true;
            }
        }
        return true;
    }

    public void explode(World world, int radius, double posX, double posY, double posZ) {
        int i,j,k;
        // Change radius to radius + coords
        for (i = (int)posX-radius ; i <= (int)posX+radius ; i++) {
            for (j = (int)posY-radius ; j <= (int)posY+radius ; j++) {
                for (k = (int)posZ-radius ; k <= (int)posZ+radius ; k++) {
                    if (StrictMath.sqrt((i - posX) * (i - posX) + (j - posY) * (j - posY) + (k - posZ) * (k - posZ)) <= radius) {
                        Block block = world.getBlock(i, j, k);
                        if (block.getMaterial() != Material.air && block.getBlockHardness(world,(int)posX,(int)posY,(int)posZ) != -1.0F) {
                            block.dropBlockAsItemWithChance(world, i, j, k, 0, 0.01F, 0);
                            world.setBlockToAir(i,j,k);
                        //block.getExplosionResistance(entity);
                        }

                    }
                }
            }
        }

        //Damage entities in the blast area
        radius *= (double)3/2;
        int x1 = (int)posX-radius-1;
        int x2 = (int)posX+radius+1;
        int y1 = (int)posY-radius-1;
        int y2 = (int)posY+radius+1;
        int z1 = (int)posZ-radius-1;
        int z2 = (int)posZ+radius+1;
        List list = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(x1,y1,z1,x2,y2,z2));
        for (Object aList : list) {
            Entity entity = (Entity) aList;
            double dist = entity.getDistance(posX, posY, posZ) / (double) radius;
            if (dist <= 1.0F) {
                entity.attackEntityFrom(DamageSource.setExplosionSource(null), (float) (radius));
                entity.motionX += entity.posX - posX;
                entity.motionY += entity.posY - posY - entity.getEyeHeight();
                entity.motionZ += entity.posZ - posZ;
            }
        }
    }
}
