package com.nicky.retrograde.common.tileentity.base;

import com.nicky.retrograde.api.interfaces.IHasRSControl;
import com.nicky.retrograde.energy.CustomEnergyStorage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public abstract class TileEntityPoweredBase extends TileEntityContainerBase implements IEnergyStorage, ITickable, IHasRSControl
{
	protected CustomEnergyStorage energyStorage;
	
	private RedstoneControl redstoneMode = RedstoneControl.DISABLED;
	public int energy;
	
	/**
	 * 
	 * @param name - Name of the machine
	 * @param inventorySlots - Number of inventory slots the machine has
	 * @param maxEnergy - Maximum amount of energy that the machine can store
	 */
	public TileEntityPoweredBase(String name, int inventorySlots, int maxEnergy)
	{
		super(name, inventorySlots);
		
		this.energyStorage = new CustomEnergyStorage(maxEnergy);
		this.energy = this.energyStorage.getEnergyStored();
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return super.hasCapability(capability, facing) || capability == CapabilityEnergy.ENERGY;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if(capability == CapabilityEnergy.ENERGY) return (T)this.energyStorage;
		
		return super.getCapability(capability, facing);
	}
	
	/* IHasRSControl */
	@Override
	public boolean shouldRun()
	{
		switch(this.redstoneMode){
		case DISABLED:
			return true;
		case LOW:
			return !world.isBlockPowered(pos);
		case HIGH:
			return world.isBlockPowered(pos);
		default:
			// Should never happen
			return true;
		}
	}
	
	@Override
	public void setControlMode(RedstoneControl mode)
	{
		this.redstoneMode = mode;
	}

	@Override
	public RedstoneControl getControlMode()
	{
		return this.redstoneMode;
	}
	
	/* NBT METHODS*/
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.energyStorage.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		this.energyStorage.writeToNBT(compound);
		
		return compound;
	}
	
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate)
	{
		return this.energyStorage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate)
	{
		return this.energyStorage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored()
	{
		return this.energyStorage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored()
	{
		return this.energyStorage.getMaxEnergyStored();
	}

	@Override
	public boolean canExtract()
	{
		return this.energyStorage.canExtract();
	}

	@Override
	public boolean canReceive()
	{
		return this.energyStorage.canReceive();
	}
}
