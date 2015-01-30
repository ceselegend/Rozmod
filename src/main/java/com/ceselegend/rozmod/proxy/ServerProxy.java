package com.ceselegend.rozmod.proxy;

public class ServerProxy extends CommonProxy {

    @Override
    public ClientProxy getClientProxy() {
        return null;
    }

    @Override
    public void registerRenderers() {}
}
