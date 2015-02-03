package com.ceselegend.rozmod.proxy;

import com.ceselegend.rozmod.entity.TileEntityFatMan;
import com.ceselegend.rozmod.init.ModBlocks;
import com.ceselegend.rozmod.render.ModelFatMan;
import com.ceselegend.rozmod.render.RenderFatMan;
import com.ceselegend.rozmod.render.RenderFatManItem;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        ModelFatMan model = new ModelFatMan();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFatMan.class, new RenderFatMan(model));
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.rozFatMan),new RenderFatManItem(model));
    }

    @Override
    public ClientProxy getClientProxy() {
        return this;
    }
}
