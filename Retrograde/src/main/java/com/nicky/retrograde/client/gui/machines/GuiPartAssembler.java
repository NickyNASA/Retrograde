package com.nicky.retrograde.client.gui.machines;

import java.io.IOException;

import com.nicky.retrograde.api.helpers.RenderHelper;
import com.nicky.retrograde.client.gui.base.GuiRetrograde;
import com.nicky.retrograde.common.container.machines.ContainerPartAssembler;
import com.nicky.retrograde.common.tileentity.machines.TileEntityPartAssembler;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPartAssembler extends GuiRetrograde
{
	private static final ResourceLocation TEXTURE = RenderHelper.makeGuiTextureLocation("metal_alloyer");//part_assembler");
	
	private TileEntityPartAssembler tileentity;
	//private final InventoryPlayer inventory;
	//private GuiElementProgressBar progressBar;
	
	public GuiPartAssembler(InventoryPlayer inventory, TileEntityPartAssembler tile)
	{
		super(new ContainerPartAssembler(inventory, tile), TEXTURE);
		
		this.tileentity = tile;
		this.xSize = 176;
		this.ySize = 180;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		//this.progressBar = (GuiElementProgressBar) addElement(new GuiElementProgressBar(this, 60, 30, ProgressBarType.ARROW_DOWN));
			
		//this.buttonList.add(new GuiButton(RetrogradeConfig.BUTTON_UPGRADE, guiLeft + 10, guiTop + 10, 60, 20, "Upgrades"));
		//this.buttonList.add(new GuiButton(RetrogradeConfig.BUTTON_BACK, guiLeft + 10, guiTop + 10, "Back"));
		
		
		/*
		 * 100
		 * 54
		 * 21
		 * 
		 * 100
		 * 77
		 * 33
		 * 0
		 * 
		 */
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
