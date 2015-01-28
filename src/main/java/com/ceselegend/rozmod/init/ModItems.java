package com.ceselegend.rozmod.init;

import com.ceselegend.rozmod.item.ExItem;
import com.ceselegend.rozmod.item.ItemRozmod;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static final ItemRozmod exItem = new ExItem();

	public static void init() {
		GameRegistry.registerItem(exItem, "exempleItem");
	}
}
