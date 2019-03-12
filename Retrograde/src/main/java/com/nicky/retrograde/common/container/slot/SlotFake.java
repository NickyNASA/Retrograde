package com.nicky.retrograde.common.container.slot;

import com.nicky.retrograde.api.interfaces.ISlotValidator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotFake extends SlotItemHandler implements ISlotValidator
{

	public SlotFake(IItemHandler itemHandler, int index, int xPos, int yPos)
	{
		super(itemHandler, index, xPos, yPos);
	}
    
	public static boolean checkFake(Container container, int index, EntityPlayer player)
	{
		if(index >= 0 && index < container.inventorySlots.size()){
			Slot slot = container.getSlot(index);
			
			if(slot instanceof SlotFake){
				((SlotFake)slot).slotClick(player);
				return true;
			}
		}
		
		return false;
	}
	
	private void slotClick(EntityPlayer player)
	{
		ItemStack heldStack = player.inventory.getItemStack();
		ItemStack stack = this.getStack();
		
		if(heldStack == ItemStack.EMPTY){
			player.inventory.setItemStack(stack);
			this.putStack(ItemStack.EMPTY);
		}else{
			ItemStack newStack = heldStack.copy();
			newStack.setCount(1);
			
			this.putStack(newStack);
		}
	}
//	@Override
//	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
//	{
//		Log.info("onTake");
//		//return super.onTake(thePlayer, stack);
//		return ItemStack.EMPTY;
//	}
	
	@Override
	public void putStack(ItemStack stack)
	{
		super.putStack(stack.copy());
		
		//Log.info("putStack1");
		//return;
		//super.putStack(stack);
		
//		if(!stack.isEmpty()){
//			stack = stack.copy();
//			stack.setCount(1);
//		}
		
//		IItemHandler handler = this.getItemHandler();
//		InventoryPlayer player = Minecraft.getMinecraft().player.inventory;
//		int slot = this.getSlotIndex();
//		
//		ItemStack newStack = stack.copy();
//		newStack.setCount(4);
//		
//		handler.extractItem(slot, Integer.MAX_VALUE, false);
//		
//		player.setItemStack(stack);
		//handler.setStackInSlot(slot, newStack);
		//handler.insertItem(slot, newStack, false);
	}
	
//	@Override
//	public ItemStack decrStackSize(int amount)
//	{
//		Log.info("decrStackSize");
//		this.getItemHandler().extractItem(this.getSlotIndex(), amount, false);
//		return ItemStack.EMPTY;
//	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return false;
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer playerIn)
	{
		return false;
	}
}
