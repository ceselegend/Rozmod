package com.ceselegend.rozmod.init;

import com.ceselegend.rozmod.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabRoz
{
    public static final CreativeTabs RozTab = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.exItem;
        }
    };
}
