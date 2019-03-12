package com.nicky.retrograde.common.block.item;

import com.nicky.retrograde.Retrograde;
import com.nicky.retrograde.api.interfaces.IMetaBlockName;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockWithMetadata extends ItemBlock
{

	public ItemBlockWithMetadata(Block block)
	{
		super(block);
		if(!(block instanceof IMetaBlockName)){
			throw new IllegalArgumentException(String.format("The given block %s does not have any metadata variants", block.getUnlocalizedName()));
		}
		
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		
		setCreativeTab(Retrograde.tabRetrograde);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return getUnlocalizedName() + "." + ((IMetaBlockName)this.block).getSpecialName(stack);
	}
	
	
	public int getMetadata(int damage)
	{
		return damage;
	}
}
