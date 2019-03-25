package com.nicky.retrograde.common.container.base;

import java.util.ArrayList;

import com.nicky.retrograde.api.interfaces.IBlueprintTile;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class ContainerBlueprintBase extends ContainerRetrograde
{
	//private IBlueprintTile tileentity;
	
	protected ArrayList<ItemStack> blueprints;
	
	public ContainerBlueprintBase(InventoryPlayer inventory, int xOffset, int yOffset)
	{
		super(inventory, xOffset, yOffset);
		
		
	}
	
	
}
