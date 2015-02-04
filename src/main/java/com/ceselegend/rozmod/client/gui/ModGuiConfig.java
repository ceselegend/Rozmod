package com.ceselegend.rozmod.client.gui;

import com.ceselegend.rozmod.handler.ConfigurationHandler;
import com.ceselegend.rozmod.reference.Reference;
import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ModGuiConfig extends GuiConfig {
    public ModGuiConfig (GuiScreen guiScreen) {
        super(guiScreen, getConfigElements(),Reference.MOD_ID, false, false, "Rozmod's config");
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        //Add categories to config GUI
        list.add(new ConfigElement(ConfigurationHandler.configuration.getCategory(ConfigurationHandler.confRozFatMan.getName())));
        list.add(new ConfigElement(ConfigurationHandler.configuration.getCategory(ConfigurationHandler.confEnderNuke.getName())));

        return list;
    }

    /** Creates a button linking to another screen where all options of the category are available */
    private static IConfigElement categoryElement(String category, String name, String tooltip_key) {
        return new DummyConfigElement.DummyCategoryElement(name, tooltip_key,
                new ConfigElement(ConfigurationHandler.configuration.getCategory(category)).getChildElements());
    }
}
