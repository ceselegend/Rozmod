package com.ceselegend.rozmod.handler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RozExplosion {
    public int radius;
    public double forceInit;
    public double attenuationPerBlock;
    public double attenuationRandomFactor;
    public int edgeNoise=1;
    public float itemDropRate;
    public List<ChunkPosition> affectedBlockPositions = new ArrayList<ChunkPosition>();

    public void getAffectedBlocks(World world, int xCoord, int yCoord, int zCoord) {
        int i, j, k;
        for (i = xCoord - radius; i <= xCoord + radius; i++) {
            for (j = yCoord - radius; j <= yCoord + radius; j++) {
                for (k = zCoord - radius; k <= zCoord + radius; k++) {
                    if (StrictMath.sqrt((i - xCoord) * (i - xCoord) + (j - yCoord) * (j - yCoord) + (k - zCoord) * (k - zCoord)) <= radius + world.rand.nextInt(edgeNoise)) {
                        this.affectedBlockPositions.add(new ChunkPosition(i, j, k));
                    }
                }
            }
        }
    }

    public void explode(World world, TileEntity tileEntity, int xCoord, int yCoord, int zCoord) {
        Iterator iterator = this.affectedBlockPositions.iterator();
        ChunkPosition chunkPosition;
        int i,j,k;
        double force;
        Block block;
        while (iterator.hasNext()) {
            chunkPosition = (ChunkPosition) iterator.next();
            i = chunkPosition.chunkPosX;
            j = chunkPosition.chunkPosY;
            k = chunkPosition.chunkPosZ;
            block = world.getBlock(i, j, k);
            force = this.forceInit - tileEntity.getDistanceFrom(i, j, k)*attenuationPerBlock*world.rand.nextInt(10)/attenuationRandomFactor;
            if (block.getMaterial() != Material.air && force > block.getBlockHardness(world, i, j, k)) {
                block.dropBlockAsItemWithChance(world, i, j, k, 0, itemDropRate, 0);
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
        List list = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(x1, y1, z1, x2, y2, z2));
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

}
