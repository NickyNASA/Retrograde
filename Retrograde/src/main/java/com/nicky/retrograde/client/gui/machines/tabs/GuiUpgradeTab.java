package com.nicky.retrograde.client.gui.machines.tabs;

import com.nicky.retrograde.api.helpers.RenderHelper;
import com.nicky.retrograde.client.gui.base.GuiRetrograde;
import com.nicky.retrograde.client.gui.elements.GuiElementSlot;
import com.nicky.retrograde.client.gui.elements.GuiElementSlot.SlotType;
import com.nicky.retrograde.common.container.machines.ContainerUpgradeTab;
import com.nicky.retrograde.common.tileentity.base.TileEntityMachineBase;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiUpgradeTab extends GuiRetrograde
{
	private static final ResourceLocation TEXTURE = RenderHelper.makeGuiTextureLocation("gui_base_medium");
	
	public TileEntityMachineBase tileentity;
	
	public GuiUpgradeTab(InventoryPlayer inventory, TileEntityMachineBase tile)
	{
		super(new ContainerUpgradeTab(inventory, tile), TEXTURE);
		
		this.tileentity = tile;
		this.xSize = 176;
		this.ySize = 180;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		this.addElement(new GuiElementSlot(this, 17, 16, SlotType.ACTIVE));
		this.addElement(new GuiElementSlot(this, 35, 16, SlotType.ACTIVE));
		this.addElement(new GuiElementSlot(this, 53, 16, SlotType.ACTIVE));
		this.addElement(new GuiElementSlot(this, 71, 16, SlotType.ACTIVE));
		
		this.addElement(new GuiElementSlot(this, 17, 34, SlotType.ACTIVE));
		this.addElement(new GuiElementSlot(this, 35, 34, SlotType.ACTIVE));
		this.addElement(new GuiElementSlot(this, 53, 34, SlotType.ACTIVE));
		this.addElement(new GuiElementSlot(this, 71, 34, SlotType.ACTIVE));
	}
}
