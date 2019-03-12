package com.nicky.retrograde.common.container.slot;

import com.nicky.retrograde.api.interfaces.ISlotValidator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotMachineUpgrade extends SlotItemHandler implements ISlotValidator
{
	
	public SlotMachineUpgrade(IItemHandler handler, int index, int x, int y)
	{
		super(handler, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		//if(stack instanceof ItemMachineUpgrade){
			return true;
		//}
		
		//return false;
	}
}
