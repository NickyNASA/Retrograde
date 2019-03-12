package com.nicky.retrograde.common.tileentity.machines;

import com.nicky.retrograde.api.ItemNames;
import com.nicky.retrograde.api.RetrogradeConfig;
import com.nicky.retrograde.api.interfaces.IHasHeat;
import com.nicky.retrograde.api.interfaces.IProcessingTile;
import com.nicky.retrograde.api.recipes.MetalAlloyerManager.MetalAlloyerRecipe;
import com.nicky.retrograde.common.tileentity.base.TileEntityPoweredBase;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class TileEntityMetalAlloyer extends TileEntityPoweredBase implements ITickable, IProcessingTile, IHasHeat
{
	private MetalAlloyerRecipe currentRecipe;
	
	private double goalTemp;
	private double currentTemp;
	private int processTime;
	private int processMax = RetrogradeConfig.METAL_ALLOYER_PROCESS_TIME;
	private int maxTemp = RetrogradeConfig.METAL_ALLOYER_MAX_TEMP;
	private double roomTemp = RetrogradeConfig.ROOM_TEMP;
	
	private FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME * RetrogradeConfig.METAL_ALLOYER_TANK_SIZE);
	private FluidStack renderFluid = new FluidStack(FluidRegistry.WATER, 0);
	
	private int ENERGY_USE = RetrogradeConfig.METAL_ALLOYER_ENERGY_USE;
	
	public TileEntityMetalAlloyer()
	{
		super(ItemNames.METAL_ALLOYER, 3, 100000);
	}
	
	/* IHasHeat */
	@Override
	public void setTempGoal(double temp)
	{
		this.goalTemp = (temp < this.maxTemp) ? temp : this.maxTemp;
	}
	
	@Override
	public void decreaseTemp()
	{
		if(this.currentTemp != roomTemp){
			if(this.currentTemp - roomTemp <= 0.01){
				this.currentTemp = roomTemp;
			}
			
			this.currentTemp -= (this.currentTemp - roomTemp) / 100;
		}
	}
	
	@Override
	public void heatBlock()
	{
		if(this.shouldRun()){
			if(this.currentTemp < this.goalTemp){
				this.currentTemp += 0.1;
			}
			
			this.energyStorage.modifyEnergyStored(-ENERGY_USE);
		}else{
			decreaseTemp();
		}
		
//		if(this.currentTemp <= this.goalTemp){
//			if(world.isBlockPowered(pos)){
//				this.currentTemp += 0.1;
//				this.energyStorage.modifyEnergyStored(-this.ENERGY_USE);
//			}else{
//				this.currentTemp -= 0.1;
//			}
//		}
	}
	
	/* IProcessingTile */
	@Override
	public boolean isProcessing()
	{
		return this.processTime > 0;
	}
	
	@Override
	public boolean canProcess()
	{
//		if(this.getEnergyStored() <= 0) return false;
//		
//		ItemStack input1 = this.handler.getStackInSlot(0);
//		ItemStack input2 = this.handler.getStackInSlot(1);
//		ItemStack input3 = this.handler.getStackInSlot(2);
//		FluidStack output = this.tank.getFluid();
//		
//		getRecipe();
//		if(this.currentRecipe == null || this.currentRecipe.getOutput() == null) return false;
//		if(this.currentTemp < this.currentRecipe.getRequiredTemp()) return false;
//		
//		ItemStack newInput1 = this.currentRecipe.getInput(0);
//		ItemStack newInput2 = this.currentRecipe.getInput(1);
//		ItemStack newInput3 = this.currentRecipe.getInput(2);
//		FluidStack newOutput = this.currentRecipe.getOutput();
//		
//		// If there are not enough items in the input slots for the current recipe
//		if(input1.getCount() < newInput1.getCount() || input2.getCount() < newInput2.getCount() || input3.getCount() < newInput3.getCount()) {
//			return false;
//		}
//		
//		// If the output FluidStack does not match the output
//		// or does not have enough room to fit the extra fluid
//		if(output.getFluid() != newOutput.getFluid()) return false;
//		if(this.tank.getFluidAmount() + newOutput.amount > this.tank.getCapacity()) return false;
//		
//		return true;
		return false;
	}
	
	@Override
	public void startProcess()
	{
//		this.processTime = 1;
//		
//		FluidStack prevFluid = this.renderFluid.copy();
//		this.renderFluid = currentRecipe.getOutput().copy();
//		this.renderFluid.amount = 0;
//		
//		if(!this.renderFluid.isFluidEqual(prevFluid)){
//			// Send fluid packet
//		}
		
		
	}

	@Override
	public void finishProcess()
	{
//		this.handler.getStackInSlot(0).shrink(this.currentRecipe.getInput(0).getCount());
//		this.handler.getStackInSlot(1).shrink(this.currentRecipe.getInput(1).getCount());
//		this.handler.getStackInSlot(2).shrink(this.currentRecipe.getInput(2).getCount());
//		
//		if(this.tank.getFluid() == null){
//			this.tank.setFluid(this.currentRecipe.getOutput());
//		}else{
//			this.tank.fill(this.currentRecipe.getOutput(), true);
//		}
//		
//		this.currentRecipe = null;
//		this.processTime = 0;
		
		//this.handler.getStackInSlot(0).shrink(this.currentRecipe.getInputs()[0].getCount());
	}
	
	@Override
	public void getRecipe()
	{
//		ItemStack slot1 = this.handler.getStackInSlot(0);
//		ItemStack slot2 = this.handler.getStackInSlot(1);
//		ItemStack slot3 = this.handler.getStackInSlot(2);
//		
//		this.currentRecipe = //MetalAlloyerManager.getRecipe(slot1, slot2, slot3);
		
		//if(this.handler.getStackInSlot(0) == ItemStack.EMPTY)
	}
	
	/* ITickable */
	@Override
	public void update()
	{
		if(!world.isRemote){
			this.heatBlock();
			
			if(this.isProcessing()){
				// Changes based off of the temperature
				this.processTime += 1;
				
				if(this.processTime >= this.processMax){
					finishProcess();
					// transferInput();
					// transferOutput();
					
				}
			}else{
				if(checkTime(10)) {
					// transferInput();
					// transferOutput();
					if(this.canProcess()){
						startProcess();
					}
				}
			}
		}
		
		//BlockMetalAlloyer.setState(this.isProcessing(), world, pos);
		
		// If the temperature is too hot, do something bad
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return super.hasCapability(capability, facing) || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return (T)this.tank;
		
		return super.getCapability(capability, facing);
	}
	
	public FluidStack getRenderFluid()
	{
		return renderFluid;
	}
	
	/**
	 * Gets the field of the tileentity
	 * 
	 * @param id -  0 - processTime
	 *     			1 - processMax
	 *   			2 - currentTemp
	 *   			3 - maxTemp
	 */
	/*@Override
	public int getField(int id)
	{
		switch(id){
		case 0:
			return this.processTime;
		case 1:
			return this.processMax;
		case 2:
			return (int)this.currentTemp;
		case 3:
			return this.maxTemp;
		default:
			return 0;
		}
	}
	
	@Override
	public void setField(int id, int value)
	{
		switch(id){
		case 0:
			this.processTime = value;
			break;
		case 1:
			this.processMax = value;
			break;
		case 2:
			this.currentTemp = value;
			break;
		case 3:
			this.maxTemp = value;
			break;
		}
	}*/
}
