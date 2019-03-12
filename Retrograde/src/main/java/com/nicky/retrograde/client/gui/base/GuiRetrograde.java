package com.nicky.retrograde.client.gui.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.nicky.retrograde.client.gui.elements.GuiElementBase;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

public abstract class GuiRetrograde extends GuiContainer
{
	protected int mouseX = 0;
	protected int mouseY = 0;
	
	protected String name;
	protected ResourceLocation texture;
	
	//public ArrayList<TabBase> tabs = new ArrayList<>();
	public ArrayList<GuiElementBase> guiElements = new ArrayList<>();
	public ArrayList<String> tooltip = new ArrayList<>();
	
	protected boolean showTooltips = true;
	
	protected GuiRetrograde(Container container)
	{
		super(container);
	}
	
	public GuiRetrograde(Container container, ResourceLocation texture)
	{
		super(container);
		this.texture = texture;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		this.guiElements.clear();
	}
	
	@Override
	public void drawScreen(int x, int y, float partialTicks)
	{
		updateElementInformation();
		
		this.drawDefaultBackground();
		super.drawScreen(x, y, partialTicks);
		this.renderHoveredToolTip(x, y);
		
		if(showTooltips && mc.player.inventory.getItemStack().isEmpty()){
			addTooltips(tooltip);
			drawTooltip(tooltip);
		}
		
		mouseX = x - guiLeft;
		mouseY = y - guiTop;
		
		updateElements(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		fontRenderer.drawString(name, getCenteredOffset(name), 6, 4210752);
		fontRenderer.drawString("Inventory", 8, ySize - 96 + 3, 4210752);
		
		drawElements(0, true);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y)
	{
		GlStateManager.color(1, 1, 1, 1);
		bindTexture(texture);
		
		if(xSize > 256 || ySize > 256){
			drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, xSize, ySize, 512, 512);
		}else{
			drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		}
		
		//this.drawTexturedModalRect(this.guiLeft + 82, this.guiTop + 37, 23, 0, 12, 4);
		mouseX = x - guiLeft;
		mouseY = y - guiTop;
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(guiLeft, guiTop, 0.0F);
		drawElements(partialTicks, false);
		//drawTabs(partialTicks, false);
		GlStateManager.popMatrix();
	}
	
	protected void drawElements(float partialTicks, boolean foreground)
	{
		if(foreground){
			for(GuiElementBase element : guiElements){
				element.drawForeground(mouseX, mouseY);
			}
		}else{
			for(GuiElementBase element : guiElements){
				element.drawBackground(mouseX, mouseY, partialTicks);
			}
		}
	}
	
	public void addTooltips(List<String> tooltip)
	{
		GuiElementBase element = getElementAtPosition(mouseX, mouseY);
		
		if(element != null){
			element.addTooltip(tooltip);
		}
	}
	
	public GuiElementBase addElement(GuiElementBase element)
	{
		guiElements.add(element);
		return element;
	}
	
	protected GuiElementBase getElementAtPosition(int mX, int mY)
	{
		for(int i = 0; i < guiElements.size(); i++){
			GuiElementBase element = guiElements.get(i);
			
			if(element.intersectsWith(mX, mY)){
				return element;
			}
		}
		
		return null;
	}
	
	protected final void updateElements(int mouseX, int mouseY)
	{
		for(int i = guiElements.size(); i-- > 0; ){
			GuiElementBase element = guiElements.get(i);
			
			if(element.isVisible()){
				element.update(mouseX, mouseY);
			}
		}
	}
	
	protected void updateElementInformation()
	{
		
	}
	
	/* MOUSE HANDLERS */
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int button) throws IOException
	{
		//int x = (mouseX - (width - xSize) / 2);
		//int y = (mouseY - (height - ySize) / 2);
		
		super.mouseClicked(mouseX, mouseY, button);
		
		for(GuiElementBase element : guiElements){
			element.mouseClicked(this.mouseX, this.mouseY, button);
		}
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int button)
	{
		super.mouseReleased(mouseX, mouseY, button);
		
		for(GuiElementBase element : guiElements){
			element.mouseReleased(this.mouseX, this.mouseY, button);
		}
	}
	
	public Slot getSlotAtPosition(int xCoord, int yCoord)
	{
		for(int k = 0; k < this.inventorySlots.inventorySlots.size(); k++){
			Slot slot = this.inventorySlots.inventorySlots.get(k);
			
			if(this.isMouseOverSlot(slot, xCoord, yCoord)){
				return slot;
			}
		}
		
		return null;
	}
	
	public boolean isMouseOverSlot(Slot theSlot, int xCoord, int yCoord)
	{
		return this.isPointInRegion(theSlot.xPos, theSlot.yPos, 16, 16, xCoord, yCoord);
	}
	
	public void bindTexture(ResourceLocation texture)
	{
		mc.renderEngine.bindTexture(texture);
	}
	
	public void drawTooltip(List<String> list)
	{
		super.drawHoveringText(list, mouseX + guiLeft, mouseY + guiTop, fontRenderer);
		tooltip.clear();
    }
    
	
	protected int getCenteredOffset(String string)
	{
		return this.getCenteredOffset(string, xSize / 2);
	}
	
	protected int getCenteredOffset(String string, int xPos)
	{
		return ((xPos * 2) - fontRenderer.getStringWidth(string)) / 2;
	}
	
	public int getMouseX()
	{
		return mouseX;
	}
	
	public int getMouseY()
	{
		return mouseY;
	}
}
