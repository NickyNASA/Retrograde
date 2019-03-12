package com.nicky.retrograde.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class CreativeTabRetrograde extends CreativeTabs
{
	
	/*public static final ModdedTabs ABSOLUTE_ZERO = new ModdedTabs(CreativeTabs.getNextID(), "absoluteZero")
	{
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(Blocks.PACKED_ICE);
		}
	};*/
	
	public CreativeTabRetrograde(){
		super("tabRetrograde");
	}
	
	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(Blocks.PACKED_ICE);
	}
}
