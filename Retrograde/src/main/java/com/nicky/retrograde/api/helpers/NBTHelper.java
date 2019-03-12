package com.nicky.retrograde.api.helpers;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public final class NBTHelper
{
	public static ArrayList<ItemStack> getMaterials(NBTTagCompound tag)
    {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
			
	    for(int i = 0; i < nbttaglist.tagCount(); i++){
	    	NBTTagCompound compound = nbttaglist.getCompoundTagAt(i);
	        ItemStack stack = new ItemStack(compound);
        	
	        int index = ArrayHelper.findStackInList(stack, list);
	        
	        if(index != -1){
	        	ItemStack currentStack = list.get(index);
	        	
	        	list.remove(index);
	        	list.add(new ItemStack(currentStack.getItem(), currentStack.getCount() + 1, currentStack.getItemDamage()));
		    }else{
		    	list.add(new ItemStack(compound));
		    }
	    }
	    
        return list;
    }
	
	public static ArrayList<ItemStack> getMaterialsFromNBT(NBTTagCompound compound)
	{
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		NBTTagList nbtList = compound.getTagList("Materials", 10);
		
		for(int i = 0; i < nbtList.tagCount(); i++){
			NBTTagCompound nbt = nbtList.getCompoundTagAt(i);
			
			items.add(new ItemStack(nbt));
		}
		
		return items;
	}
	
	/* NBT Compounds */
	public static NBTTagCompound getTag(ItemStack stack)
	{
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
		}
		
		return stack.getTagCompound();
	}
	
	public static String getString(ItemStack stack, String key)
	{
		return getTag(stack).getString(key);
	}
	
	public static void setString(ItemStack stack, String key, String nbt)
	{
		getTag(stack).setString(key, nbt);
	}
}
