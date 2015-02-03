package com.ceselegend.rozmod.proxy;

import com.ceselegend.rozmod.render.*;
import com.ceselegend.rozmod.tileEntities.TileEntityEnderNuke;
import com.ceselegend.rozmod.tileEntities.TileEntityFatMan;
import com.ceselegend.rozmod.init.ModBlocks;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        ModelFatMan model = new ModelFatMan();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFatMan.class, new RenderFatMan(model));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.rozFatMan),new RenderFatManItem(model));

        ModelEnderNuke model_ender = new ModelEnderNuke();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnderNuke.class,new RenderEnderNuke(model_ender));
    }

    @Override
    public ClientProxy getClientProxy() {
        return this;
    }
}
