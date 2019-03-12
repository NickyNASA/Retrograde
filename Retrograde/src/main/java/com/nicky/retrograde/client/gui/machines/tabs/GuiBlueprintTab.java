package com.nicky.retrograde.client.gui.machines.tabs;

import java.io.IOException;

import org.jline.utils.Log;

import com.nicky.retrograde.api.helpers.RenderHelper;
import com.nicky.retrograde.api.interfaces.IBlueprintTile;
import com.nicky.retrograde.client.gui.base.GuiRetrograde;
import com.nicky.retrograde.client.gui.elements.GuiElementScrollBar;
import com.nicky.retrograde.client.gui.elements.GuiElementSlot;
import com.nicky.retrograde.client.gui.elements.GuiElementSlot.SlotType;
import com.nicky.retrograde.common.container.machines.ContainerBlueprintTab;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiBlueprintTab extends GuiRetrograde
{
	private static final ResourceLocation TEXTURE = RenderHelper.makeGuiTextureLocation("blueprint_tab");//gui_base_medium");
	
	public IBlueprintTile tileentity;
	private GuiTextField searchBar;
	
	public GuiBlueprintTab(InventoryPlayer inventory, IBlueprintTile tile)
	{
		super(new ContainerBlueprintTab(inventory, tile), TEXTURE);
		
		this.tileentity = tile;
		this.xSize = 176;
		this.ySize = 180;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		//this.addElement(new GuiElementSlot(this, 15, 36, SlotType.ACTIVE));
		//this.addElement(new GuiElementSlot(this, 15, 62, SlotType.ACTIVE));
		this.addElement(new GuiElementScrollBar(this, 149, 19, 66));
		
		
		this.searchBar = new GuiTextField(0, this.fontRenderer, 50, 19, 97, this.fontRenderer.FONT_HEIGHT);
		this.searchBar.setTextColor(-1);
		this.searchBar.setDisabledTextColour(-1);
		this.searchBar.setEnableBackgroundDrawing(false);
		this.searchBar.setMaxStringLength(20);
		this.searchBar.setEnabled(true);
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
		
		this.searchBar.drawTextBox();
	}
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if(this.searchBar.textboxKeyTyped(typedChar, keyCode)){
			Log.info(this.searchBar.getText());
		}else{
			super.keyTyped(typedChar, keyCode);	
		}
	}
	
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int button) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, button);
		this.searchBar.mouseClicked(mouseX - guiLeft, mouseY - guiTop, button);
	}
}
