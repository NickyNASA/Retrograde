package com.nicky.retrograde.common.container.machines;

import com.nicky.retrograde.api.helpers.GuiHelper;
import com.nicky.retrograde.common.container.base.ContainerRetrograde;
import com.nicky.retrograde.common.container.slot.SlotBlueprint;
import com.nicky.retrograde.common.tileentity.base.TileEntityContainerBase;
import com.nicky.retrograde.common.tileentity.base.TileEntityMachineBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBlueprintTab extends ContainerRetrograde
{
	private TileEntityMachineBase tileentity;
	
	public ContainerBlueprintTab(InventoryPlayer inventory, TileEntityMachineBase tile)
	{
		super(inventory, GuiHelper.GUI_MEDIUM_X, GuiHelper.GUI_MEDIUM_Y);
		
		this.tileentity = tile;
		this.handler = ((TileEntityContainerBase)tile).handler;
		
		int startIndex = this.tileentity.getBlueprintSlot();
		
		this.addSlotToContainer(new SlotBlueprint(handler, startIndex, 15, 36));
		this.addSlotToContainer(new SlotBlueprint(handler, startIndex + 1, 15, 62));
		
		this.addPlayerInventory(inventory, this.xOffset, this.yOffset);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()){
			ItemStack stack = slot.getStack();
			itemstack = stack.copy();
			
			if(index == 0){
				if(!this.mergeItemStack(stack, 1, 37, true)){
					return ItemStack.EMPTY;
				}
			}else{
				if(!this.mergeItemStack(stack, 0, 1, false)){
					return ItemStack.EMPTY;
				}
			}
			
			if(stack.isEmpty()){
				slot.putStack(ItemStack.EMPTY);
			}
			else{
				slot.onSlotChanged();
			}
			
			//if(stack.getCount() == itemstack.getCount()){
			//	return ItemStack.EMPTY;
			//}
			
			slot.onTake(playerIn, stack);
		}
		
		return itemstack;
	}

}
