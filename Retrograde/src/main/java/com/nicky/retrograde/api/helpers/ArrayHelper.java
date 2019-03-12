package com.nicky.retrograde.api.helpers;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public final class ArrayHelper
{
	public static <E> NonNullList<E> makeNNList(E... inputList)
	{
		NonNullList<E> newList = NonNullList.create();
		
		for(E x : inputList){
			newList.add(x);
		}
		
		return newList;
	}
	
	public static int findStackInList(ItemStack stack, ArrayList<ItemStack> list)
	{
		for(int i = 0; i < list.size(); i++){
			ItemStack item = list.get(i);
			
			if(stack.isItemEqual(item)){
				return i;
			}
		}
		
		return -1;
	}
}
