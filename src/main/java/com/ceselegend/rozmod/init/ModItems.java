package com.ceselegend.rozmod.init;

import com.ceselegend.rozmod.item.ExItem;
import com.ceselegend.rozmod.item.ItemRozmod;
import com.ceselegend.rozmod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	
	public static final ItemRozmod exItem = new ExItem();

	public static void init() {
		GameRegistry.registerItem(exItem, "exempleItem");
	}
}
