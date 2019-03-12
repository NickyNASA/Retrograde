package com.nicky.retrograde.client.gui.machines;

import java.io.IOException;

import com.nicky.retrograde.api.RetrogradeConfig;
import com.nicky.retrograde.client.gui.base.GuiRetrograde;
import com.nicky.retrograde.client.gui.elements.GuiElementHeatBar;
import com.nicky.retrograde.client.gui.elements.GuiElementProgressBar;
import com.nicky.retrograde.client.gui.elements.GuiElementProgressBar.ProgressBarType;
import com.nicky.retrograde.common.Reference;
import com.nicky.retrograde.common.container.machines.ContainerMetalAlloyer;
import com.nicky.retrograde.common.tileentity.machines.TileEntityMetalAlloyer;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiMetalAlloyer extends GuiRetrograde
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/gui/metal_alloyer.png");
	
	private TileEntityMetalAlloyer myTile;
	private GuiElementHeatBar heatBar;
	private GuiElementProgressBar progressBar;
	
	public GuiMetalAlloyer(InventoryPlayer inventory, TileEntityMetalAlloyer tile)
	{
		super(new ContainerMetalAlloyer(inventory, tile), TEXTURE);
		
		this.myTile = tile;
		
		this.xSize = 176;
		this.ySize = 180;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		this.progressBar = (GuiElementProgressBar) addElement(new GuiElementProgressBar(this, 60, 30, ProgressBarType.ARROW_DOWN));
		this.heatBar = (GuiElementHeatBar) addElement(new GuiElementHeatBar(this, 30, 20, RetrogradeConfig.ROOM_TEMP, 3000, 0, 0));
			
		//this.buttonList.add(new GuiButton(RetrogradeConfig.BUTTON_UPGRADE, guiLeft + 10, guiTop + 10, 60, 20, "Upgrades"));
		//this.buttonList.add(new GuiButton(RetrogradeConfig.BUTTON_BACK, guiLeft + 10, guiTop + 10, "Back"));
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
	
	/* MOUSE HANDLERS */
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
		//this.heatBar.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
	{
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		//this.heatBar.mouseClickMove(mouseX, mouseY, clickedMouseButton);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state)
	{
		super.mouseReleased(mouseX, mouseY, state);
		//this.heatBar.mouseReleased(mouseX, mouseY, state);
	}
}
