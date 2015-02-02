package com.ceselegend.rozmod.block;

import com.ceselegend.rozmod.entity.TileEntityFatMan;
import com.ceselegend.rozmod.init.CreativeTabRoz;
import com.ceselegend.rozmod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class RozFatMan extends BlockContainer {

    public RozFatMan() {
        super(Material.anvil);
        this.setCreativeTab(CreativeTabRoz.RozTab);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par1) {
        return new TileEntityFatMan();
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":","rozFatMan" );
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(Reference.MOD_ID+":rozFatMan");
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack items) {
        if(entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entity;
            TileEntityFatMan bomb = (TileEntityFatMan) world.getTileEntity(x,y,z);
            bomb.setOwner(player.getDisplayName());
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        TileEntityFatMan bomb = (TileEntityFatMan) world.getTileEntity(x,y,z);
        if(!bomb.getPrimed() && player.getDisplayName().equals(bomb.getOwner())) {
            bomb.setPrimed();
            return true;
        }else {
            return super.onBlockActivated(world,x,y,z,player,p_149727_6_,p_149727_7_,p_149727_8_,p_149727_9_);
        }

    }
}
