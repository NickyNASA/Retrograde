package com.nicky.retrograde.common.container.machines;

import java.util.ArrayList;

import com.nicky.retrograde.api.helpers.GuiHelper;
import com.nicky.retrograde.api.helpers.NBTHelper;
import com.nicky.retrograde.common.container.base.ContainerRetrograde;
import com.nicky.retrograde.common.container.slot.SlotBlueprint;
import com.nicky.retrograde.common.container.slot.SlotFake;
import com.nicky.retrograde.common.tileentity.machines.TileEntityBlueprintTable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.items.CapabilityItemHandler;

public class ContainerBlueprintTable extends ContainerRetrograde
{
	private final TileEntityBlueprintTable tileentity;
	
	public ContainerBlueprintTable(InventoryPlayer inventory, TileEntityBlueprintTable tile)
	{
		super(inventory, GuiHelper.BLUEPRINT_TABLE_X, GuiHelper.BLUEPRINT_TABLE_Y);
		
		this.tileentity = tile;
		this.handler = this.tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new SlotBlueprint(this.handler, 0, 26, 54));
		
		for(int y = 0; y < 5; y++){
			for(int x = 0; x < 5; x++){
				int xPos = 62 + x*18;
				int yPos = 18 + y*18;
				
				this.addSlotToContainer(new SlotFake(this.handler, y*5 + x + 1, xPos, yPos));
			}
		}
		
		this.addPlayerInventory(inventory, this.xOffset, this.yOffset);
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player)
	{
		if(SlotFake.checkFake(this, slotId, player)){
			return ItemStack.EMPTY;
		}
		
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()){
			ItemStack stack = slot.getStack();
			itemstack = stack.copy();
			
			if(index != 0){
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
		
		return itemstack;
	}
	
	/* Packet */
	public NBTTagCompound saveToNBT()
	{
		NBTTagCompound compound = new NBTTagCompound();
		NBTTagList itemList = new NBTTagList();
		
		for(int i = 1; i <= 25; i++){
			ItemStack stack = this.handler.getStackInSlot(i).copy();
			
			if(!stack.isEmpty()){
				NBTTagCompound tag = new NBTTagCompound();
		        tag.setByte("Slot", (byte)i);
		        stack.writeToNBT(tag);
		        itemList.appendTag(tag);
			}
		}
		
		compound.setTag("Items", itemList);
		
		// Material List
		ArrayList<ItemStack> stacks = NBTHelper.getMaterials(compound);
		NBTTagList materialList = new NBTTagList();
		
		for(int i = 0; i < stacks.size(); i++){
			NBTTagCompound tempNBT = new NBTTagCompound();
			
			ItemStack currentStack = stacks.get(i);
			String name = Item.REGISTRY.getNameForObject(currentStack.getItem()).toString();
			
			tempNBT.setString("id", name);
			tempNBT.setByte("Count", (byte)currentStack.getCount());
			tempNBT.setShort("Damage", (short)currentStack.getItemDamage());
			
			materialList.appendTag(tempNBT);
		}
		
		compound.setTag("Materials", materialList);
		//	compound.setString("Name", name);
		
		return compound;
	}
	
	public void onBlueprintPacket()
	{
		ItemStack stack = this.handler.getStackInSlot(0).copy();
		
		NBTTagCompound compound = saveToNBT();
		
		stack.setTagCompound(compound);
		
		this.putStackInSlot(0, stack);
		this.detectAndSendChanges();
	}
}
