package com.nicky.retrograde.common.container.machines;

import com.nicky.retrograde.api.helpers.ItemHelper;
import com.nicky.retrograde.api.recipes.MetalAlloyerManager;
import com.nicky.retrograde.common.container.base.ContainerRetrograde;
import com.nicky.retrograde.common.tileentity.machines.TileEntityMetalAlloyer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerMetalAlloyer extends ContainerRetrograde
{
	private final TileEntityMetalAlloyer tileentity;
	
	//public int processTime;
	//public int processMax;
	
	public ContainerMetalAlloyer(InventoryPlayer inventory, TileEntityMetalAlloyer tile)
	{
		super(inventory, MetalAlloyerManager.xOffset, MetalAlloyerManager.yOffset);
		
		this.tileentity = tile;
		
		this.handler = this.tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 100, 47));
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 130, 47));
		this.addSlotToContainer(new SlotItemHandler(handler, 2, 160, 47));
		
		this.addPlayerInventory(inventory, this.xOffset, this.yOffset);
	}
	
//	@Override
//	public void detectAndSendChanges()
//	{
//		this.processTime = this.tile.getField(0);
//		this.processMax = this.tile.getField(1);
//		
//		super.detectAndSendChanges();
//	}
	
//	@SideOnly(Side.CLIENT)
//	@Override
//	public void updateProgressBar(int id, int data)
//	{
//		this.tile.setField(id, data);
//	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		
//		Log.info("Index = " + index);
//		Log.info("Slot 0: " + this.inventorySlots.get(0).getStack().getUnlocalizedName());
		
//		Log.info("Is smeltable material = " + ItemHelper.isSmeltableMaterial(slot.getStack()));
//		Log.info("Slot 1: " + this.inventorySlots.get(1).getStack().getUnlocalizedName());
//		Log.info("Slot 2: " + this.inventorySlots.get(2).getStack().getUnlocalizedName());
		
		if(slot != null && slot.getHasStack()){
			ItemStack stack = slot.getStack();
			itemstack = stack.copy();
			
			if(index != 0 && index != 1 && index != 2){
				if(ItemHelper.isSmeltableMaterial(stack)){
					if(!this.mergeItemStack(stack, 0, 3, false)){
						return ItemStack.EMPTY;
					}
				}
				else if(index >= 3 && index < 30){
					if(!this.mergeItemStack(stack, 30, 39, false)){
						return ItemStack.EMPTY;
					}
				}
				else if(index >= 30 && index < 39) {
					if(!this.mergeItemStack(stack, 3, 30, false)){
						return ItemStack.EMPTY;
					}
				}
			}
			else if(!this.mergeItemStack(stack, 3, 30, false)){
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
		
		return itemstack;
	}
}
