package com.nicky.retrograde.common.item;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.nicky.retrograde.api.ItemNames;
import com.nicky.retrograde.api.Lang;
import com.nicky.retrograde.api.helpers.NBTHelper;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlueprint extends ItemRetrograde
{
	public ItemBlueprint()
	{
		super(ItemNames.BLUEPRINT);
		setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		NBTTagCompound compound = stack.getTagCompound();
		
		if(compound != null && !compound.hasNoTags()){
			if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				tooltip.add(I18n.format(Lang.INFO + Lang.BLUEPRINT_MATERIALS_SHIFT));
				
				ArrayList<ItemStack> items = NBTHelper.getMaterialsFromNBT(compound);
				
				for(ItemStack item : items){
				
					tooltip.add(String.format("%1$sx %2$s", item.getCount(), item.getDisplayName()));
				}
				
			}else{
				tooltip.add(I18n.format(Lang.INFO + Lang.BLUEPRINT_MATERIALS));
			}
		}
	}
}
