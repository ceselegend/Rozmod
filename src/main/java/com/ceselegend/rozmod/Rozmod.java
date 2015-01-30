package com.ceselegend.rozmod;

import com.ceselegend.rozmod.entity.RozNukePrimed;
import com.ceselegend.rozmod.handler.ConfigurationHandler;
import com.ceselegend.rozmod.init.ModBlocks;
import com.ceselegend.rozmod.init.ModItems;
import com.ceselegend.rozmod.proxy.IProxy;
import com.ceselegend.rozmod.reference.Reference;
import com.ceselegend.rozmod.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.MOD_NAME, guiFactory = Reference.GUI_FACTORY_CLASS)
public class Rozmod {
	
	
	@Mod.Instance(Reference.MOD_ID)
	public static Rozmod instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	ConfigurationHandler.init(event.getSuggestedConfigurationFile());
    	FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
    	ModItems.init();
        ModBlocks.init();
        proxy.registerRenderers();

    	LogHelper.info("Pre-initialization done.");

    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

        // Entity registering
        EntityRegistry.registerGlobalEntityID(RozNukePrimed.class, "rozNukePrimed", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(RozNukePrimed.class,"rozNukePrimed",1,this,128,1,false);

        LogHelper.info("Initialization done.");
    }


    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	LogHelper.info("Post-inititialization done.");
    }
}
