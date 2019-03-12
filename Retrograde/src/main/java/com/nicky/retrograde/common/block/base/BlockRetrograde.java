package com.nicky.retrograde.common.block.base;

import com.nicky.retrograde.Retrograde;
import com.nicky.retrograde.common.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class BlockRetrograde extends Block
{
	public BlockRetrograde(String name, Material material)
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
		setCreativeTab(Retrograde.tabRetrograde);
	}
}
