package com.ceselegend.rozmod.block;

import com.ceselegend.rozmod.entity.RozNukePrimed;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Random;

public class RozNuke extends BlockRozmod {

    public int fuse;

    public RozNuke(int fuse)
    {
        super(Material.tnt);
        this.setBlockName("rozNuke");
        this.fuse = fuse;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int posX, int posY, int posZ) {
        super.onBlockAdded(world, posX, posY, posZ);

        if (world.isBlockIndirectlyGettingPowered(posX, posY, posZ)) {
            this.onBlockDestroyedByPlayer(world, posX, posY, posZ, 1);
            world.setBlockToAir(posX, posY, posZ);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World world, int posX, int posY, int posZ, Block neighborBlock) {
        if (world.isBlockIndirectlyGettingPowered(posX, posY, posZ)) {
            this.onBlockDestroyedByPlayer(world, posX, posY, posZ, 1);
            world.setBlockToAir(posX, posY, posZ);
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random) {
        return 0;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World world, int posX, int posY, int posZ, Explosion explosion) {
        if (!world.isRemote) {
            RozNukePrimed entitytntprimed = new RozNukePrimed(world, (double)((float)posX + 0.5F), (double)((float)posY + 0.5F), (double)((float)posZ + 0.5F), explosion.getExplosivePlacedBy());
            entitytntprimed.fuse = world.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
            world.spawnEntityInWorld(entitytntprimed);
        }
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World world, int posX, int posY, int posZ, int metaData) {
        this.spawnNukePrimed(world, posX, posY, posZ, metaData, (EntityLivingBase) null);
    }

    public void spawnNukePrimed(World world, int posX, int posY, int posZ, int metaData, EntityLivingBase entity) {
        if (!world.isRemote) {
            if ((metaData & 1) == 1) {
                RozNukePrimed entitytntprimed = new RozNukePrimed(world, (double)((float)posX + 0.5F), (double)((float)posY + 0.5F), (double)((float)posZ + 0.5F), entity);
                world.spawnEntityInWorld(entitytntprimed);
                // entity, sound, volume, pitch
                world.playSoundAtEntity(entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
            }
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel) {
            this.spawnNukePrimed(world, posX, posY, posZ, 1, player);
            world.setBlockToAir(posX, posY, posZ);
            player.getCurrentEquippedItem().damageItem(1, player);
            return true;
        }
        else {
            return super.onBlockActivated(world, posX, posY, posZ, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
        }
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World world, int posX, int posY, int posZ, Entity entity) {
        if (entity instanceof EntityArrow && !world.isRemote) {
            EntityArrow entityarrow = (EntityArrow)entity;

            if (entityarrow.isBurning()) {
                this.spawnNukePrimed(world, posX, posY, posZ, 1, entityarrow.shootingEntity instanceof EntityLivingBase ? (EntityLivingBase) entityarrow.shootingEntity : null);
                world.setBlockToAir(posX, posY, posZ);
            }
        }
    }

    /**
     * Return whether this block can drop from an explosion.
     */
    public boolean canDropFromExplosion(Explosion explosion) {
        return false;
    }

    /*@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
        this.field_150116_a = p_149651_1_.registerIcon(this.getTextureName() + "_top");
        this.field_150115_b = p_149651_1_.registerIcon(this.getTextureName() + "_bottom");
    }*/
}
