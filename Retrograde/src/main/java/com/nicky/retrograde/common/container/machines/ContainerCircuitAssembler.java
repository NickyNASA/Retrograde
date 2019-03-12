package com.nicky.retrograde.common.container.machines;

import com.nicky.retrograde.api.helpers.GuiHelper;
import com.nicky.retrograde.common.container.base.ContainerRetrograde;
import com.nicky.retrograde.common.tileentity.machines.TileEntityCircuitAssembler;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCircuitAssembler extends ContainerRetrograde
{
	private TileEntityCircuitAssembler tileentity;
	
	private int processTime;
	private int processMax;
	private int energyStored;
	private int energyMax;
	
	public ContainerCircuitAssembler(InventoryPlayer inventory, TileEntityCircuitAssembler tile)
	{
		super(inventory, GuiHelper.CIRCUIT_ASSEMBLER_X, GuiHelper.CIRCUIT_ASSEMBLER_Y);
		
		this.tileentity = tile;
		this.handler = this.tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new SlotItemHandler(this.handler, 0, 26, 54));
		
		for(int y = 0; y < 5; y++){
			for(int x = 0; x < 5; x++){
				int xPos = 62 + x*18;
				int yPos = 18 + y*18;
				
				this.addSlotToContainer(new SlotItemHandler(handler, y*5 + x + 1, xPos, yPos));
			}
		}
		
		this.addPlayerInventory(inventory, this.xOffset, this.yOffset);
	}
	
	@Override
	public ItemStack transferStackInSlot()
	{
		/*
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()){
			ItemStack stack = slot.getStack();
			itemstack = stack.copy();
			
			if(index != 0 && index != 1){
				if(!this.mergeItemStack(stack, 26, 62, false)){
					return ItemStack.EMPTY;
				}
				else if(index >= 1 && index < 26){
					SlotFake.checkFake(this, index, playerIn);
				}
				else if(index >= 26 && index < 62) {
					if(!this.mergeItemStack(stack, 0, 1, false)){
						return ItemStack.EMPTY;
					}
				}
			}
			else if(!this.mergeItemStack(stack, 26, 62, false)){
				return ItemStack.EMPTY;
			}
			
			if(stack.isEmpty()){
				slot.putStack(ItemStack.EMPTY);
			}
			else{
				slot.onSlotChanged();
			}
			
			if(stack.getCount() == itemstack.getCount()){
				return ItemStack.EMPTY;
			}
			
			slot.onTake(playerIn, stack);
		}
		
		return itemstack;*/
	}
	
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); i++){
			IContainerListener listener = this.listeners.get(i);
			
			if(this.processTime != this.tileentity.getField(0)){
				listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			}
			if(this.processMax != this.tileentity.getField(1)){
				listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			}
			if(this.energyStored != this.tileentity.getField(2)){
				listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			}
			if(this.energyMax != this.tileentity.getField(3)){
				listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
			}
		}
		
		this.processTime = this.tileentity.getField(0);
		this.processMax = this.tileentity.getField(1);
		this.energyStored = this.tileentity.getField(2);
		this.energyMax = this.tileentity.getField(3);
	}
	
	public void onAddBlueprintPacket()
	{
		ItemStack stack = this.handler.getStackInSlot(this.tileentity.BLUEPRINT_SLOT);
		
		if(stack != null && stack != ItemStack.EMPTY){
			// add the blueprint to the Blueprint array in the container
		}
	}
}
