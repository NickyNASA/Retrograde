package com.nicky.retrograde.common.container.base;

import java.util.ArrayList;

import com.nicky.retrograde.common.tileentity.base.TileEntityMachineBase;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class ContainerMachineBase extends ContainerRetrograde
{
	public TileEntityMachineBase tileentity;

	protected ArrayList<ItemStack> upgrades;
	protected ArrayList<ItemStack> blueprints;

	public ContainerMachineBase(InventoryPlayer inventory, int xOffset, int yOffset)
	{
		super(inventory, xOffset, yOffset);

		this.upgrades = this.tileentity.upgrades;
		this.blueprints = this.tileentity.blueprints;
	}

	/*
	Might need to add the blueprint arrayList to the detectAndSendChanges function.
	 */
	public void onAddBlueprintPacket()
	{
		ItemStack stack = this.handler.getStackInSlot(this.tileentity.BLUEPRINT_SLOT).copy();

		if(stack != null && stack != ItemStack.EMPTY){
			this.tileentity.blueprints.add(stack);
			this.putStackInSlot(this.tileentity.BLUEPRINT_SLOT, ItemStack.EMPTY);
			this.detectAndSendChanges();
		}
	}

	public void onTakeBlueprintPacket(int selectedIndex)
	{
		ItemStack stack = this.handler.getStackInSlot(this.tileentity.BLUEPRINT_SLOT + 1).copy();

		if(stack == null && stack == ItemStack.EMPTY){
			ItemStack newStack = this.blueprints.get(selectedIndex);

			this.putStackInSlot(this.tileentity.BLUEPRINT_SLOT + 1, newStack);
			this.detectAndSendChanges();
		}
	}
}