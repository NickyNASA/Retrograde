package com.nicky.retrograde.common.tileentity.machines;

import org.jline.utils.Log;

import com.nicky.retrograde.api.ItemNames;
import com.nicky.retrograde.api.RetrogradeConfig;
import com.nicky.retrograde.api.interfaces.IHasGui;
import com.nicky.retrograde.api.interfaces.IProcessingTile;
import com.nicky.retrograde.api.recipes.PartAssemblerManager;
import com.nicky.retrograde.api.recipes.PartAssemblerManager.PartAssemblerRecipe;
import com.nicky.retrograde.common.block.machines.BlockPartAssembler;
import com.nicky.retrograde.common.tileentity.base.TileEntityMultiblockPart;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityPartAssembler extends TileEntityMultiblockPart<TileEntityPartAssembler> implements ITickable, IProcessingTile, IHasGui
{
	private PartAssemblerRecipe currentRecipe;
	
	private int processTime;
	private int processMax;
	
	private int ENERGY_USE = RetrogradeConfig.PART_ASSEMBLER_ENERGY_USE;
	private static final int[] size = {3, 2, 3};
	
	public TileEntityPartAssembler()
	{
		super(ItemNames.PART_ASSEMBLER, 9, 1000, size);
	}

	@Override
	public boolean isProcessing()
	{
		return this.processTime > 0;
	}

	@Override
	public boolean canProcess()
	{
		return this.currentRecipe != null;
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
	public void getRecipe()
	{
		ItemStack[] inventory = new ItemStack[this.handler.getSlots()];
		String[] ls = new String[9];
		
		for(int i = 0; i < this.handler.getSlots(); i++){
			inventory[i] = this.handler.getStackInSlot(i);
			ls[i] = this.handler.getStackInSlot(i).getItem().getUnlocalizedName();
		}
		
		Log.info(ls[0] + " + " + ls[1] + " + " + ls[2] + " + " + ls[3]);
		this.currentRecipe = PartAssemblerManager.getRecipe(inventory);
	}
	
	@Override
	public void update()
	{
		if(!world.isRemote){
			boolean wasProcessing = this.isProcessing();
			
			if(world.getWorldTime() % 40 == 0){
				getRecipe();
			}
			
			if(this.currentRecipe != null) {
				if(this.getEnergyStored() >= ENERGY_USE){
					this.processTime++;
					
					//Log.info("Process time = " + this.processTime);
					//Log.info("isProcessing = " + this.isProcessing());
				}
			}
			
			if(wasProcessing != this.isProcessing()){
				BlockPartAssembler.setState(this.isProcessing(), world, pos);
			}
		}
		
		this.receiveEnergy(5, false);
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
