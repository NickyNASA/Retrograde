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
	
	public void onAddBlueprintPacket()
	{
		ItemStack stack = this.handler.getStackInSlot(this.tileentity.BLUEPRINT_SLOT).copy();
		
		if(stack != null && stack != ItemStack.EMPTY){
			this.blueprints.add(stack);
			this.handler.putStackInSlot(this.tileentity.BLUEPRINT_SLOT, ItemStack.EMPTY);
			this.detectAndSendChanges();
		}
	}
	
	public void onTakeBlueprintPacket(int selectedIndex)
	{
		ItemStack stack = this.handler.getStackInSlot(this.tileentity.BLUEPRINT_SLOT + 1).copy();
		
		if(stack == null && stack == ItemStack.EMPTY){
			NBTTagCompound compound = 
			ItemStack newStack = this.blueprints.get(selectedIndex);
			
			this.handler.putStackInSlot(this.tileentity.BLUEPRINT_SLOT + 1, newStack);
			this.detectAndSendChanges();
		}
	}
}
