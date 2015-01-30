package com.ceselegend.rozmod.proxy;

import com.ceselegend.rozmod.entity.RozNukePrimed;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(RozNukePrimed.class, new RenderTNTPrimed());
    }

    @Override
    public ClientProxy getClientProxy() {
        return this;
    }
}
