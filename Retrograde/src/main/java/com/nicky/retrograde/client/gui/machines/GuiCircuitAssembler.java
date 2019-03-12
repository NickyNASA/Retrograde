package com.nicky.retrograde.client.gui.machines;

import java.io.IOException;

import com.nicky.retrograde.api.handlers.GuiHandler.GuiTypes;
import com.nicky.retrograde.api.helpers.RenderHelper;
import com.nicky.retrograde.client.gui.base.GuiRetrograde;
import com.nicky.retrograde.client.gui.elements.GuiElementBlueprintTab;
import com.nicky.retrograde.client.gui.elements.GuiElementSlot;
import com.nicky.retrograde.client.gui.elements.GuiElementUpgradeTab;
import com.nicky.retrograde.client.gui.elements.GuiElementSlot.SlotType;
import com.nicky.retrograde.common.container.machines.ContainerCircuitAssembler;
import com.nicky.retrograde.common.tileentity.machines.TileEntityCircuitAssembler;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCircuitAssembler extends GuiRetrograde
{
	private static final ResourceLocation TEXTURE = RenderHelper.makeGuiTextureLocation("circuit_assembler");
	
	public TileEntityCircuitAssembler tileentity;
	
	public GuiCircuitAssembler(InventoryPlayer inventory, TileEntityCircuitAssembler tile)
	{
		super(new ContainerCircuitAssembler(inventory, tile), TEXTURE);
		
		this.tileentity = tile;
		this.xSize = 176;
		this.ySize = 204;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		this.addElement(new GuiElementUpgradeTab(this, this.tileentity, this.xSize + 1, 10, GuiTypes.CIRCUIT_ASSEMBLER.ordinal()));
		this.addElement(new GuiElementBlueprintTab(this, this.tileentity, this.xSize + 1, 40, GuiTypes.CIRCUIT_ASSEMBLER.ordinal()));
		
		for(int y = 0; y < 5; y++){
			for(int x = 0; x < 5; x++){
				int xPos = 62 + x*18;
				int yPos = 18 + y*18;
				
				this.addElement(new GuiElementSlot(this, xPos, yPos, SlotType.ACTIVE));
			}
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y)
	{
		super.drawGuiContainerBackgroundLayer(partialTicks, x, y);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int button) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, button);
	}
}
