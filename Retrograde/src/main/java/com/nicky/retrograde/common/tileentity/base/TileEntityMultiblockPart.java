package com.nicky.retrograde.common.tileentity.base;

import net.minecraft.nbt.NBTTagCompound;

public abstract class TileEntityMultiblockPart<T extends TileEntityMultiblockPart<T>> extends TileEntityPoweredBase
{
	public boolean isFormed;
	private T master;
	private boolean isMaster;
	
	
	protected final int[] multiblockSize;
	
	public TileEntityMultiblockPart(String name, int inventorySlots, int maxEnergy, int[] size)
	{
		super(name, inventorySlots, maxEnergy);
		
		this.multiblockSize = size;
	}
	
	public boolean isMaster() 
	{
		return this.isMaster;
	}
	
	public T getMaster()
	{
		return this.master;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		this.isMaster = compound.getBoolean("isMaster");
		this.isFormed = compound.getBoolean("isFormed");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		compound.setBoolean("isMaster", this.isMaster);
		compound.setBoolean("isFormed", this.isFormed);
		
		return compound;
	}
}
