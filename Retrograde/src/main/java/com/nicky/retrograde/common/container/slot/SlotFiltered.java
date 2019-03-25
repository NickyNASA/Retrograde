package com.nicky.retrograde.common.container.slot;

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
                return stack.getItem() instanceof this.filter.getItem();
        }
}
