package com.nicky.retrograde.common.container.slot;

import com.nicky.retrograde.api.interfaces.ISlotValidator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotFiltered extends SlotItemHandler implements ISlotValidator
{
        public ItemStack filter;
        
        public SlotFiltered(IItemHandler itemHandler, int index, int xPos, int yPos, ItemStack filter)
        {
                super(itemHandler, index, xPos, yPos);
                
                this.filter = filter;
        }
        
        @Override
        public boolean isItemValid(ItemStack stack)
        {
                return stack.getItem() == this.filter.getItem();
        }
}
