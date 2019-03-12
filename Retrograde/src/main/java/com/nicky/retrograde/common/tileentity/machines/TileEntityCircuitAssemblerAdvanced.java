package com.nicky.retrograde.common.tileentity.machines;

import com.nicky.retrograde.api.ItemNames;
import com.nicky.retrograde.api.interfaces.IHasGui;
import com.nicky.retrograde.api.interfaces.IProcessingTile;
import com.nicky.retrograde.common.tileentity.base.TileEntityMultiblockPart;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityCircuitAssemblerAdvanced extends TileEntityMultiblockPart<TileEntityCircuitAssemblerAdvanced> implements ITickable, IProcessingTile, IHasGui
{
	private int processTime;
	private int processMax;
	
	public TileEntityCircuitAssemblerAdvanced()
	{
		super(ItemNames.CIRCUIT_ASSEMBLER_ADVANCED, 50, 100000, null);
	}

	@Override
	public boolean isProcessing()
	{
		return this.processTime > 0;
	}

	@Override
	public boolean canProcess() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getRecipe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		this.processTime = compound.getInteger("ProcessTime");
		this.processMax = compound.getInteger("ProcessMax");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		compound.setInteger("ProcessTime", this.processTime);
		compound.setInteger("ProcessMax", this.processMax);
		
		return compound;
	}
	
	@Override
	public int getField(int id)
	{
		switch(id){
		case 0:
			return this.processTime;
		case 1:
			return this.processMax;
		case 2:
			return this.getEnergyStored();
		case 3:
			return this.getMaxEnergyStored();
		default:
			return 0;
		}
	}

	@Override
	public int setField(int id, int value)
	{
		return 0;
	}
}
