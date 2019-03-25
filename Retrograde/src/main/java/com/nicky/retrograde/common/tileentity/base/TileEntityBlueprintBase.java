package com.nicky.retrograde.common.tileentity.base;

import com.nicky.retrograde.api.helpers.NBTHelper;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public abstract class TileEntityBlueprintBase extends TileEntityPoweredBase
{
        public ArrayList<ItemStack> blueprints = new ArrayList<ItemStack>();
        
        public TileEntityBlueprintBase(String name, int inventorySlots)
        {
                super(name, inventorySlots);
                
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
}
