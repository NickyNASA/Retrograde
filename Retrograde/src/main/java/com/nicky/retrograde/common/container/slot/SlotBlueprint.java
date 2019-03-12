package com.nicky.retrograde.common.container.slot;

import com.nicky.retrograde.api.interfaces.ISlotValidator;
import com.nicky.retrograde.common.item.ItemBlueprint;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotBlueprint extends SlotItemHandler implements ISlotValidator
{
	public SlotBlueprint(IItemHandler itemHandler, int index, int xPos, int yPos)
	{
		super(itemHandler, index, xPos, yPos);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return stack.getItem() instanceof ItemBlueprint;
	}
}
