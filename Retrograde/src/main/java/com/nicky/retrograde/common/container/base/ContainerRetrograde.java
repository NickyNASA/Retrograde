package com.nicky.retrograde.common.container.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.IItemHandler;

public class ContainerRetrograde extends Container
{
	private final InventoryPlayer playerInv;
	protected IItemHandler handler;
	protected final int xOffset;
	protected final int yOffset;
	
	public ContainerRetrograde(InventoryPlayer inventory, int xOffset, int yOffset)
	{
		this.playerInv = inventory;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	protected void addPlayerInventory(InventoryPlayer inventory, int xOffset, int yOffset)
	{
		for(int y = 0; y < 3; y++){
			for(int x = 0; x < 9; x++){
				this.addSlotToContainer(new Slot(inventory, x + y*9 + 9, (8 + xOffset) + x*18, (84 + yOffset) + y*18));
			}
		}
		
		for(int z = 0; z < 9; z++){
			this.addSlotToContainer(new Slot(inventory, z, (8 + xOffset) + z*18, 142 + yOffset));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}
	
	public InventoryPlayer getInventoryPlayer()
	{
		return this.playerInv;
	}
	
//	@Override
//	public void detectAndSendChanges()
//	{
//		for(IContainerListener listener : this.listeners){
//			if(this.processTime != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
//			if(this.processMax != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
//		}
//		
//		this.processTime = this.tileentity.getField(0);
//		this.processMax = this.tileentity.getField(1);
//		
//		super.detectAndSendChanges();
//	}
	
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void updateProgressBar(int id, int data)
//	{
//		this.tileentity.setField(id, data);
//	}
}
