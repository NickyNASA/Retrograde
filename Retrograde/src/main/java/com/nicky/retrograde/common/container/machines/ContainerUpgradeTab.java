package com.nicky.retrograde.common.container.machines;

import com.nicky.retrograde.api.helpers.GuiHelper;
import com.nicky.retrograde.common.container.base.ContainerRetrograde;
import com.nicky.retrograde.common.container.slot.SlotMachineUpgrade;
import com.nicky.retrograde.common.tileentity.base.TileEntityContainerBase;
import com.nicky.retrograde.common.tileentity.base.TileEntityMachineBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerUpgradeTab extends ContainerRetrograde
{
	private TileEntityMachineBase tileentity;
	
	public ContainerUpgradeTab(InventoryPlayer inventory, TileEntityMachineBase tile)
	{
		super(inventory, GuiHelper.GUI_MEDIUM_X, GuiHelper.GUI_MEDIUM_Y);
		
		this.tileentity = tile;
		this.handler = ((TileEntityContainerBase)tile).handler;
		
		int startIndex = this.tileentity.getUpgradeSlot();
		
		this.addSlotToContainer(new SlotMachineUpgrade(handler, startIndex, 17, 16));
		this.addSlotToContainer(new SlotMachineUpgrade(handler, startIndex + 1, 35, 16));
		this.addSlotToContainer(new SlotMachineUpgrade(handler, startIndex + 2, 53, 16));
		this.addSlotToContainer(new SlotMachineUpgrade(handler, startIndex + 3, 71, 16));
		
		this.addSlotToContainer(new SlotMachineUpgrade(handler, startIndex + 4, 17, 34));
		this.addSlotToContainer(new SlotMachineUpgrade(handler, startIndex + 5, 35, 34));
		this.addSlotToContainer(new SlotMachineUpgrade(handler, startIndex + 6, 53, 34));
		this.addSlotToContainer(new SlotMachineUpgrade(handler, startIndex + 7, 71, 34));
		
		//this.addSlotToContainer(new SlotMachineUpgrade(upgradeInv, 8,  17, 66));
		//this.addSlotToContainer(new SlotMachineUpgrade(upgradeInv, 9,  35, 66));
		//this.addSlotToContainer(new SlotMachineUpgrade(upgradeInv, 10, 53, 66));
		//this.addSlotToContainer(new SlotMachineUpgrade(upgradeInv, 11, 71, 66));
		
		addPlayerInventory(inventory, this.xOffset, this.yOffset);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()){
			ItemStack stack = slot.getStack();
			itemstack = stack.copy();
			
			if(index >= 0 && index < 8){
				if(!this.mergeItemStack(stack, 8, 44, true)){
					return ItemStack.EMPTY;
				}
			}else{// if(index >= 8 && index < 44){
				if(!this.mergeItemStack(stack, 0, 8, false)){
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
