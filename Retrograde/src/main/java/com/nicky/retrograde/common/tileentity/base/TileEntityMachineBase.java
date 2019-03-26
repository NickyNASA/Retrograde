package com.nicky.retrograde.common.tileentity.base;

import java.util.ArrayList;

import com.nicky.retrograde.api.helpers.NBTHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class TileEntityMachineBase extends TileEntityPoweredBase
{
	public ArrayList<String> VALID_UPGRADES;// = new ArrayList<String>();
	
	public boolean hasUpgrades;
	public boolean hasBlueprints;
	
	public int UPGRADE_SLOT;
	public int BLUEPRINT_SLOT;
	public int ENERGY_USE;
	
	public ArrayList<ItemStack> upgrades = new ArrayList<ItemStack>();
	public ArrayList<ItemStack> blueprints = new ArrayList<ItemStack>();
	
	public TileEntityMachineBase(String name, int inventorySlots, int maxEnergy)
	{
		this(name, inventorySlots, maxEnergy, false, false);
	}
	
	public TileEntityMachineBase(String name, int inventorySlots, int maxEnergy, boolean upgrade, boolean blueprint)
	{
		super(name, inventorySlots, maxEnergy);
		
		this.hasUpgrades = upgrade;
		this.hasBlueprints = blueprint;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		this.blueprints = NBTHelper.deserializeBlueprints(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		NBTHelper.serializeBlueprints(this.blueprints, compound);

		return compound;
	}
	
	public int getUpgradeSlot()
	{
		return UPGRADE_SLOT;
	}
	
	public int getBlueprintSlot()
	{
		return BLUEPRINT_SLOT;
	}
}
