package com.nicky.retrograde.common.item;

import com.nicky.retrograde.Retrograde;
import com.nicky.retrograde.common.Reference;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemRetrograde extends Item
{
	public ItemRetrograde(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
		setCreativeTab(Retrograde.tabRetrograde);
	}
}
