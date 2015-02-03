package com.ceselegend.rozmod.tileEntities;

import com.ceselegend.rozmod.handler.ConfigurationHandler;
import com.ceselegend.rozmod.handler.RozExplosion;
import com.ceselegend.rozmod.init.ModBlocks;
import net.minecraft.world.ChunkPosition;

import java.util.Iterator;

public class TileEntityFatMan extends TileEntityBomb {

    private RozExplosion explosion = new RozExplosion();

    public TileEntityFatMan() {
        super();
        explosion.radius = ConfigurationHandler.rozFatMan_radius;
        explosion.forceInit = ConfigurationHandler.rozFatMan_forceInit;
    }

    @Override
        public void setPrimed() {
            this.primed = true;
            worldObj.addBlockEvent(xCoord,yCoord,zCoord,ModBlocks.rozFatMan,2,0);
            explosion.getAffectedBlocks(worldObj, xCoord, yCoord, zCoord);
        }

        @Override
        public void updateEntity() {
        if (!worldObj.isRemote) {
            if (fuse == 0) {
                worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                worldObj.removeTileEntity(xCoord,yCoord,zCoord);
                explosion.explode(worldObj, this, xCoord, yCoord, zCoord);
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
