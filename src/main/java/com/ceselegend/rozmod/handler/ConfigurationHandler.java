package com.ceselegend.rozmod.handler;

import java.io.File;

import com.ceselegend.rozmod.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

	public static Configuration configuration;

    public static ConfigCategory confRozFatMan = new ConfigCategory("rozfatman");

    // confRozFatMan
    public static int rozFatMan_radius;
    public static float rozFatMan_forceInit;
    public static int rozFatMan_fuse;
    public static int rozFatMan_edgeNoise;
    public static float rozFatMan_attenuationPerBlock;
    public static int rozFatMan_attenuationRandomFactor;
    public static float rozFatMan_dropRate;
	
	public static void init(File configFile){
        confRozFatMan.setRequiresMcRestart(true);
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)){
            configuration.save();
        }
    }

    private static void loadConfiguration() {
        rozFatMan_radius = configuration.getInt("RozFatManRadius", confRozFatMan.getName(), 32, 0, 256, "Radius of explosion");
        rozFatMan_forceInit = configuration.getFloat("RozFatManForceInit", Configuration.CATEGORY_GENERAL, 64, 0, 17000000, "Initial explosion force");
        rozFatMan_fuse = configuration.getInt("RozFatManFuse", Configuration.CATEGORY_GENERAL, 100 , 1, 72000, "Fuse time");
        rozFatMan_edgeNoise = configuration.getInt("RozFatManNoise", Configuration.CATEGORY_GENERAL, 5 , 1, 256, "Noise factor of explosion edge (increase explosion radius)");
        rozFatMan_attenuationPerBlock = configuration.getFloat("RozFatManAttenuation", Configuration.CATEGORY_GENERAL, 0.3F, 0, 17000000.0F, "Attenuation of explosion per block");
        rozFatMan_attenuationRandomFactor = configuration.getInt("RozFatManAttenuationRandomFactor", Configuration.CATEGORY_GENERAL, 10, 1, 1000, "Random factor of attenuation");
        rozFatMan_dropRate = configuration.getFloat("RozFatManDropRate", Configuration.CATEGORY_GENERAL, 0.05F , 0.0F, 1.0F, "Drop rate of blocks affected by explosion");

        if (configuration.hasChanged()) {
			configuration.save();
		}
	}
	
}
