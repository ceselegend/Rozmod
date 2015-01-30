package com.ceselegend.rozmod.proxy;

import com.ceselegend.rozmod.entity.RozNukePrimed;
import com.ceselegend.rozmod.render.RenderRozNukePrimed;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(RozNukePrimed.class, new RenderRozNukePrimed());
    }

    @Override
    public ClientProxy getClientProxy() {
        return this;
    }
}
