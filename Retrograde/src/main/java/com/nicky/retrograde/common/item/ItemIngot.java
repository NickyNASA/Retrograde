package com.nicky.retrograde.common.item;

import com.nicky.retrograde.api.ItemNames;
import com.nicky.retrograde.api.enums.MetalVariant;
import com.nicky.retrograde.api.interfaces.IHasMeta;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemIngot extends ItemRetrograde implements IHasMeta
{
	public static MetalVariant[] types = MetalVariant.values();
	
	public ItemIngot()
	{
		super(ItemNames.INGOT);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if(this.isInCreativeTab(tab)){
			for(int i = 0; i < types.length; i++){
				items.add(new ItemStack(this, 1, i));
			}
        }
	}
	
	@Override
	public String getTexture(int meta)
	{
		return types[meta].getName() + "_ingot";
	}

	@Override
	public int getVariants()
	{
		return types.length;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return "item." + types[stack.getItemDamage()].getName() + "_ingot";
	}
}
