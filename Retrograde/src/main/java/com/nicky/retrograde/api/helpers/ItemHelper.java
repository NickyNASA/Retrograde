package com.nicky.retrograde.api.helpers;

import com.nicky.retrograde.common.tileentity.base.TileEntityContainerBase;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.oredict.OreDictionary;

public final class ItemHelper
{
	
	/* GUI Methods */
	public static void setStackInSlot(IItemHandler handler, int index, ItemStack stack)
	{
		if(handler instanceof IItemHandlerModifiable){
			((IItemHandlerModifiable) handler).setStackInSlot(index, stack);
		}else{
			handler.extractItem(index, Integer.MAX_VALUE, false);
			handler.insertItem(index, stack, false);
		}
	}
	
	/* ORE DICTIONARY */
	public static ItemStack getOre(String name)
	{
		return OreDictionary.getOres(name, false).get(0).copy();
	}
	
	public static String getOreName(ItemStack stack)
	{
		int[] oreIds = OreDictionary.getOreIDs(stack);
		
		if(oreIds == null || oreIds.length == 0){
			return "";
		}
		
		return OreDictionary.getOreName(oreIds[0]);
	}
	
	public static boolean matchesOre(ItemStack stack, String name)
	{
		//int itemId = OreDictionary.getOreID(name);
		//int[] oreIds = OreDictionary.getOreIDs(stack);
		NonNullList<ItemStack> ores = OreDictionary.getOres(name);
		
		//OreDictionary.itemMatches(target, input, strict)
		//OreDictionary.getOres("ingotCopper");
		
		for(ItemStack item : ores){
			if(OreDictionary.itemMatches(item, stack, false)){
				return true;
			}
		}
			
		return false;
	}
	
	public static boolean isSmeltableMaterial(ItemStack stack)
	{
		String oreName = ItemHelper.getOreName(stack);
		
//		for(MetalVariant type : MetalVariant.values()) {
//			if(oreName == "nugget" + StringHelper.capitalize(type.getName()) ||
//			   oreName == "ingot" + StringHelper.capitalize(type.getName()) ||
//			   oreName == "block" + StringHelper.capitalize(type.getName()) ||
//			   oreName == "ore" + StringHelper.capitalize(type.getName()) ||
//			   oreName == "dust" + StringHelper.capitalize(type.getName()) ||
//			   ){
//				
//			}
//		}
		return oreName.startsWith("nugget") ||
			   oreName.startsWith("ingot") ||
			   oreName.startsWith("block") ||
			   oreName.startsWith("ore") ||
			   oreName.startsWith("dust") ||
			   stack.getUnlocalizedName() == "item.coal";
	}
	
	/* INVENTORY - CONTAINER */
	
	public static void dropInventory(World worldIn, TileEntityContainerBase tile, BlockPos pos)
	{
		for(int i = 0; i < tile.handler.getSlots(); i++){
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tile.handler.getStackInSlot(i)));
		}
	}
}
