package com.nicky.retrograde.api.recipes;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.nicky.retrograde.api.enums.MetalVariant;
import com.nicky.retrograde.api.helpers.ArrayHelper;
import com.nicky.retrograde.api.helpers.ItemHelper;
import com.nicky.retrograde.api.helpers.StringHelper;
import com.nicky.retrograde.api.interfaces.IMachineRecipe;
import com.nicky.retrograde.api.recipes.RecipeRegistry.RecipeType;
import com.nicky.retrograde.common.RetrogradeItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

@SuppressWarnings("unused")
public class MetalAlloyerManager
{
	public static final int xOffset = 0;
	public static final int yOffset = 0;
	
	//public static final MetalAlloyerManager instance = new MetalAlloyerManager();
	
	private static Map<ItemStack[], MetalAlloyerRecipe> recipeList = Maps.<ItemStack[], MetalAlloyerRecipe>newHashMap();
	
	
	public static void init()
	{
		//ItemStack[] steelIn = new ItemStack[] {};
		//ItemStack[] steelOut 
		
		NonNullList<ItemStack> steelIn = ArrayHelper.makeNNList(new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.IRON_INGOT));

		addRecipe(new ItemStack[] {new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.IRON_INGOT)},
				  new ItemStack(RetrogradeItems.ingot, 1, MetalVariant.STEEL.ordinal()), 575);
		addRecipe(new ItemStack[] {new ItemStack(Items.COAL, 2, 1), new ItemStack(Items.IRON_INGOT)},
				  new ItemStack(RetrogradeItems.ingot, 1, MetalVariant.STEEL.ordinal()), 575);
		//addRecipe(new ItemStack(Items.COAL, 1, 0), new ItemStack(Items.IRON_INGOT, 1), new FluidStack(FluidRegistry.WATER, Fluid.BUCKET_VOLUME), 750);
		//addRecipe(new ItemStack(Items.COAL, 2, 1), new ItemStack(Items.IRON_INGOT, 1), new ItemStack(RetrogradeItems.ingot, 1, MetalVariant.STEEL.ordinal()), 750);
		//addOreDictRecipe(new ItemStack(Items.COAL, 1, 0), "iron", new ItemStack(RetrogradeItems.ingot, 1, MetalVariant.STEEL.ordinal()), 575);
		
		//OreDictionary.get(name)
		
	}
	

	public static MetalAlloyerRecipe getRecipe(ItemStack[] inputs)
	{
		return recipeList.get(inputs);
	}
	
	
	/* ADD RECIPES */
//	public static void addRecipe(ItemStack input1, ItemStack input2, ItemStack output, int requiredTemp)
//	{
//		addRecipe(input1, input2, ItemStack.EMPTY, output, requiredTemp);
//	}
//	
//	public static MetalAlloyerRecipe addRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack output, int requiredTemp)
//	{
//		if(input1.isEmpty() || input2.isEmpty() || input3.isEmpty() || getRecipe(input1, input2, input3) != null){
//			return null;
//		}
//		
//		MetalAlloyerRecipe recipe = new MetalAlloyerRecipe(input1, input2, input3, output, requiredTemp);//MetalAlloyerRecipe(input1, input2, input3, output, requiredTemp);
//		
//		recipeList.put(asList(input1, input2, input3), recipe);
//		return recipe;
//	}
	public static void addRecipe(ItemStack[] inputs, ItemStack output, int temp)
	{
		recipeList.put(inputs, new MetalAlloyerRecipe(inputs, output, temp));
	}
	
	/* ORE DICT RECIPES */
	/*
	public static void addOreDictRecipe(String ore1, String ore2, String ore3, int requiredTemp)
	{
		List<List<ItemStack>> oreDictList = new ArrayList<>();
		
		if(ore1 != "") oreDictList.add(OreDictionary.getOres("ingot" + ore1, false));
		if(ore2 != "") oreDictList.add(OreDictionary.getOres("ingot" + ore2, false));
		if(ore3 != "") oreDictList.add(OreDictionary.getOres("ingot" + ore3, false)); // -> Mekanism.copperIngot, Retrograde.copperIngot
		
		for(List<ItemStack> currentList : oreDictList){
			//OreDictionary.get
		}
	}
	*/
	
	/* RECIPE CLASS */
	public static class MetalAlloyerRecipe implements IMachineRecipe
	{
//		private final ItemStack input1;
//		private final ItemStack input2;
//		private final ItemStack input3;
		private final ItemStack[] inputs;
		private final ItemStack output;
		private final int requiredTemp;
		
		
		public MetalAlloyerRecipe(ItemStack[] inputs, ItemStack output, int requiredTemp)
		{
			this.inputs = inputs;
			this.output = output;
			this.requiredTemp = requiredTemp;
		}
		
		public ItemStack[] getInputs()
		{
			return this.inputs;
		}
		
		public ItemStack getOutput()
		{
			return this.output;
		}
		
		public int getRequiredTemp()
		{
			return this.requiredTemp;
		}
	}
}
