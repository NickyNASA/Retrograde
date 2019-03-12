package com.nicky.retrograde.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.IEnergyStorage;

public class CustomEnergyStorage implements IEnergyStorage
{
	protected int energy;
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;
	
	
	public CustomEnergyStorage(int capacity)
    {
        //super(capacity, capacity, capacity, 0);
		this(capacity, capacity, capacity);
    }

    public CustomEnergyStorage(int capacity, int maxTransfer)
    {
        //super(capacity, maxTransfer, maxTransfer, 0);
    	this(capacity, maxTransfer, maxTransfer);
    }

    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract)
    {
        //super(capacity, maxReceive, maxExtract, 0);
    	this.capacity = capacity;
    	this.maxReceive = maxReceive;
    	this.maxExtract = maxExtract;
    }
    
    public CustomEnergyStorage readFromNBT(NBTTagCompound compound)
    {
    	this.energy = compound.getInteger("GuiEnergy");
    	
    	if(energy > capacity){
    		energy = capacity;
    	}
    	
    	return this;
    }
    
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
    	if(energy < 0){
    		energy = 0;
    	}
    	
    	compound.setInteger("GuiEnergy", energy);
    	return compound;
    }

    public CustomEnergyStorage setCapacity(int capacity)
    {
    	this.capacity = capacity;
    	
    	if(energy > capacity){
    		energy = capacity;
    	}
    	
    	return this;
	}
    
    public CustomEnergyStorage setMaxTransfer(int maxTransfer)
    {
    	setMaxReceive(maxTransfer);
    	setMaxExtract(maxTransfer);
    	return this;
    }
    
    public CustomEnergyStorage setMaxReceive(int maxReceive)
    {
		this.maxReceive = maxReceive;
		return this;
	}

	public CustomEnergyStorage setMaxExtract(int maxExtract)
	{
		this.maxExtract = maxExtract;
		return this;
	}

	public int getMaxReceive()
	{
		return maxReceive;
	}

	public int getMaxExtract()
	{
		return maxExtract;
	}
    
	/**
	 * This function is included to allow for server to client sync. Do not call this externally to the containing Tile Entity, as not all IEnergyHandlers are guaranteed to have it.
	 */
	public void setEnergyStored(int energy)
	{
		this.energy = energy;
		
		if(this.energy > capacity){
			this.energy = capacity;
		}else if(this.energy < 0){
			this.energy = 0;
		}
	}
	
	/**
	 * This function is included to allow the containing tile to directly and efficiently modify the energy contained in the EnergyStorage. Do not rely on this externally, as not all IEnergyHandlers are guaranteed to have it.
	 */
	public void modifyEnergyStored(int addedEnergy)
	{
		energy += addedEnergy;

		if(this.energy > capacity){
			this.energy = capacity;
		}else if(this.energy < 0){
			this.energy = 0;
		}
	}
	
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate)
	{
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
		
		if(!simulate){
			energy += energyReceived;
		}
		
		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate)
	{
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
		
		if(!simulate){
			energy -= energyExtracted;
		}
		
		return energyExtracted;
	}

	@Override
	public int getEnergyStored()
	{
		return energy;
	}

	@Override
	public int getMaxEnergyStored()
	{
		return capacity;
	}

	@Override
	public boolean canExtract()
	{
		return this.canExtract();
	}

	@Override
	public boolean canReceive()
	{
		return this.canReceive();
	}

//    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy)
//    {
//    	super(capacity, maxReceive, maxExtract, energy);
//    }
    
    /*
    public void readFromNBT(NBTTagCompound compound)
    {
    	this.energy = compound.getInteger("Energy");
    	this.capacity = compound.getInteger("Capacity");
    	this.maxReceive = compound.getInteger("MaxReceive");
    	this.maxExtract = compound.getInteger("MaxExtract");
    }
    
    public void writeToNBT(NBTTagCompound compound)
    {
    	compound.setInteger("Energy", this.energy);
    	compound.setInteger("Capacity", this.capacity);
    	compound.setInteger("MaxReceive", this.maxReceive);
    	compound.setInteger("MaxExtract", this.maxExtract);
    }
    
    public CustomEnergyStorage setCapacity(int capacity)
    {
    	this.capacity = capacity;
    	
    	if(energy > capacity){
    		energy = capacity;
    	}
    	return this;
    }
    
    public CustomEnergyStorage setMaxTransfer(int maxTransfer)
    {
    	setMaxReceive(maxTransfer);
    	setMaxExtract(maxTransfer);
    	return this;
    }
    
    public CustomEnergyStorage setMaxReceive(int maxReceive)
    {
    	this.maxReceive = maxReceive;
    	return this;
    }
    
    public CustomEnergyStorage setMaxExtract(int maxExtract)
    {
    	this.maxExtract = maxExtract;
    	return this;
    }
    
    public int getMaxReceive()
    {
		return maxReceive;
	}

	public int getMaxExtract()
	{
		return maxExtract;
	}
	
	public void setEnergyStored(int energy)
	{
		this.energy = energy;
		
		if(this.energy > capacity){
			this.energy = capacity;
		}else if(this.energy < 0){
			this.energy = 0;
		}
	}
    
	public void modifyEnergyStored(int energy)
	{
		this.energy += energy;

		if(this.energy > capacity){
			this.energy = capacity;
		}else if(this.energy < 0){
			this.energy = 0;
		}
	}
	
    /*public int recieveEnergyInternal(int maxReceive, boolean simulate)
    {
    	int before = this.maxReceive;
    	this.maxReceive = Integer.MAX_VALUE;
    	
    	int toReturn = this.receiveEnergy(maxReceive, simulate);
    	
    	this.maxReceive = before;
    	return toReturn;
    }
    
    public int extractEnergyInternal(int maxExtract, boolean simulate)
    {
    	int before = this.maxExtract;
    	this.maxExtract = Integer.MAX_VALUE;
    	
    	int toReturn = this.extractEnergy(maxExtract, simulate);
    	
    	this.maxExtract = before;
    	return toReturn;
    }
    
    /* IEnergyStorage functions 
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate)
    {
    	int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
    	
    	if(!simulate){
    		energy += energyReceived;
    	}
    	return energyReceived;
    }
    
    @Override
    public int extractEnergy(int maxExtract, boolean simulate)
    {
    	int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
    	
    	if(!simulate){
    		energy -= energyExtracted;
    	}
    	return energyExtracted;
    }
    
    @Override
    public int getEnergyStored() 
    {
    	return super.getEnergyStored();
    }
    
    @Override
    public int getMaxEnergyStored()
    {
    	return super.getMaxEnergyStored();
    }
    */
}
