package com.nicky.retrograde.api.recipes;

import java.util.ArrayList;

import com.nicky.retrograde.api.interfaces.IMachineRecipe;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.oredict.OreIngredient;

public class PartAssemblerManager
{
	private static ArrayList<PartAssemblerRecipe> recipeList = new ArrayList<PartAssemblerRecipe>();
	
	public static void init()
	{
		addRecipe(new ItemStack(Items.APPLE, 1), 100, 40, Items.ENDER_PEARL, Items.BLAZE_POWDER);
		addRecipe(new ItemStack(Items.DIAMOND, 4), 250, 80, "ingotIron", "ingotGold", "ingotTin");
	}
	
	private static void addRecipe(ItemStack output, int energy, int processTime, Object... inputs)
	{
		//Ingredient a = Ingredient.fromItem(Items.APPLE);
		//Ingredient b = new OreIngredient("ingotCopper");
		
		Ingredient[] ingredients = new Ingredient[inputs.length];
		
		for(int i = 0; i < inputs.length; i++){
			Object item = inputs[i];
			
			if(item instanceof Item){
				ingredients[i] = Ingredient.fromItem((Item)item);
			}
			if(item instanceof ItemStack){
				ingredients[i] = Ingredient.fromStacks((ItemStack)item);
			}
			else if(item instanceof String){
				ingredients[i] = new OreIngredient((String)item);
			}
		}
		
		recipeList.add(new PartAssemblerRecipe(ingredients, output, energy, processTime));
	}
	
	public static PartAssemblerRecipe getRecipe(ItemStack... inputs)
	{
		for(PartAssemblerRecipe recipe : recipeList){
			Ingredient[] ingredients = recipe.getInputs();
			//Boolean[] matchedIngredients = new Boolean[ingredients.length];
			//if(inputs.length != ingredients.length) return null;
			boolean isMatchingRecipe = true;
			
			for(Ingredient item : ingredients){
				boolean foundIngredient = false;
				
				for(ItemStack stack : inputs){
					if(item.apply(stack) == true){
						foundIngredient = true;
						break;
					}
				}
				
				if(foundIngredient){
					isMatchingRecipe = isMatchingRecipe && foundIngredient;
				}else{
					return null;
				}
			}
			
			return recipe;
		}
		
		//PartAssemblerRecipe recipe = recipeList.get(0);
		//if(recipe.getInputs()[0].apply(inputs[0])){
		//	
		//	if(recipe.getInputs()[1].apply(inputs[1])){
		//		Log.info("Found recipe");
		//		return recipe;
		//	}
		//}
		
		return null;
	}
	
	public static class PartAssemblerRecipe implements IMachineRecipe
	{
		private final Ingredient[] inputs;
		private final ItemStack output;
		private int requiredEnergy;
		private int processTime;
		
		public PartAssemblerRecipe(Ingredient[] inputs, ItemStack output, int requiredEnergy, int processTime)
		{
			this.inputs = inputs;
			this.output = output;
			this.requiredEnergy = requiredEnergy;
			this.processTime = processTime;
		}
		
		public Ingredient[] getInputs()
		{
			return this.inputs;
		}
		
		public ItemStack getOutput()
		{
			return this.output;
		}
		
		public int getRequiredEnergy()
		{
			return this.requiredEnergy;
		}
		
		public int getProcessTime()
		{
			return this.processTime;
		}
	}
}
