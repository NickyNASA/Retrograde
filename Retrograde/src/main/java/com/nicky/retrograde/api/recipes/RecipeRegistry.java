package com.nicky.retrograde.api.recipes;

import com.nicky.retrograde.api.interfaces.IMachineRecipe;

public class RecipeRegistry
{
	
	
	//private Map<RecipeType, Map<String, IMachineRecipe>> recipeList = new HashMap<RecipeType, Map<String, IMachineRecipe>>();
	
	public void addRecipe(IMachineRecipe recipe)
	{
		//recipeList.put(recipe.getRecipeType(), recipe);
	}
	
	public void init()
	{
		
	}
	
	public enum RecipeType
	{
		ALLOY("metal_alloyer");
		
		private String name;
		
		private RecipeType(String name)
		{
			this.name = name;
		}
		
		@Override
		public String toString()
		{
			return this.name;
		}
	}
}
