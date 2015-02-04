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
    public static ConfigCategory confEnderNuke = new ConfigCategory("endernuke");

    // confRozFatMan
    public static int rozFatMan_radius;
    public static float rozFatMan_forceInit;
    public static int rozFatMan_fuse;
    public static int rozFatMan_edgeNoise;
    public static float rozFatMan_attenuationPerBlock;
    public static int rozFatMan_attenuationRandomFactor;
    public static float rozFatMan_dropRate;

    //confEnderNuke
    public static int enderNuke_radius;
    public static int enderNuke_fuse;
    public static float enderNuke_coreRotation;
    public static float enderNuke_redRotation;
    public static float enderNuke_blueRotation;
    public static float enderNuke_greenRotation;

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
        /* RozFatMan config */
        rozFatMan_radius = configuration.getInt("RozFatManRadius", confRozFatMan.getName(), 32, 0, 256, "Radius of explosion");
        rozFatMan_forceInit = configuration.getFloat("RozFatManForceInit", confRozFatMan.getName(), 64, 0, 17000000, "Initial explosion force");
        rozFatMan_fuse = configuration.getInt("RozFatManFuse", confRozFatMan.getName(), 100 , 1, 72000, "Fuse time");
        rozFatMan_edgeNoise = configuration.getInt("RozFatManNoise", confRozFatMan.getName(), 5 , 1, 256, "Noise factor of explosion edge (increase explosion radius)");
        rozFatMan_attenuationPerBlock = configuration.getFloat("RozFatManAttenuation", confRozFatMan.getName(), 0.3F, 0, 17000000.0F, "Attenuation of explosion per block");
        rozFatMan_attenuationRandomFactor = configuration.getInt("RozFatManAttenuationRandomFactor", confRozFatMan.getName(), 10, 1, 1000, "Random factor of attenuation");
        rozFatMan_dropRate = configuration.getFloat("RozFatManDropRate", confRozFatMan.getName(), 0.05F , 0.0F, 1.0F, "Drop rate of blocks affected by explosion");

        /* EnderNuke config */
        enderNuke_radius = configuration.getInt("EnderNukeRadius", confEnderNuke.getName(), 32, 0, 256, "Radius of explosion");
        enderNuke_fuse = configuration.getInt("EnderNukeFuse", confEnderNuke.getName(), 100, 1, 72000, "Fuse time");
        enderNuke_coreRotation = configuration.getFloat("EnderNukeCoreRotation", confEnderNuke.getName(), 0.02F, 0.0F, 1.0F, "Core rotation speed");
        enderNuke_redRotation = configuration.getFloat("EnderNukeRedRotation", confEnderNuke.getName(), 0.02F, 0.0F, 1.0F, "Red color rotation speed");
        enderNuke_blueRotation = configuration.getFloat("EnderNukeBlueRotation", confEnderNuke.getName(), 0.04F, 0.0F, 1.0F, "Blue color rotation speed");
        enderNuke_greenRotation = configuration.getFloat("EnderNukeGreenRotation", confEnderNuke.getName(), 0.01F, 0.0F, 1.0F, "Green color rotation speed");

        /* Save new config in case of change */
        if (configuration.hasChanged()) {
			configuration.save();
		}
	}
	
}
