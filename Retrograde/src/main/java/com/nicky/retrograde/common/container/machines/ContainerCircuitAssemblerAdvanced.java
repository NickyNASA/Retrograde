package com.nicky.retrograde.common.container.machines;

import com.nicky.retrograde.api.helpers.GuiHelper;
import com.nicky.retrograde.common.container.base.ContainerRetrograde;
import com.nicky.retrograde.common.tileentity.machines.TileEntityCircuitAssemblerAdvanced;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCircuitAssemblerAdvanced extends ContainerRetrograde
{
	private TileEntityCircuitAssemblerAdvanced tileentity;
	
	private int processTime;
	private int processMax;
	private int energyStored;
	private int energyMax;
	
	public ContainerCircuitAssemblerAdvanced(InventoryPlayer inventory, TileEntityCircuitAssemblerAdvanced tile)
	{
		super(inventory, GuiHelper.CIRCUIT_ASSEMBLER_ADVANCED_X, GuiHelper.CIRCUIT_ASSEMBLER_ADVANCED_Y);
		
		this.tileentity = tile;
		this.handler = this.tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 17, 50));
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 35, 50));
		this.addSlotToContainer(new SlotItemHandler(handler, 2, 53, 50));
		
		this.addPlayerInventory(inventory, this.xOffset, this.yOffset);
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
}
