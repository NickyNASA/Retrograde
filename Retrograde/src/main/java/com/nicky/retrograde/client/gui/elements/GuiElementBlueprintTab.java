package com.nicky.retrograde.client.gui.elements;

import java.util.List;

import com.nicky.retrograde.api.handlers.GuiHandler.GuiTypes;
import com.nicky.retrograde.api.helpers.RenderHelper;
import com.nicky.retrograde.common.network.PacketHandler;
import com.nicky.retrograde.common.network.packets.PacketChangeGui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class GuiElementBlueprintTab extends GuiElementBase
{
	private static final ResourceLocation DEFAULT_TEXTURE = RenderHelper.makeGuiTextureLocation("gui_buttons");
	
	protected boolean hovered = false;
	protected int activeTextureX;
	protected int activeTextureY;
	private int prevGuiID;
	
	private TileEntity tileentity;
	
	public GuiElementBlueprintTab(GuiContainer gui, TileEntity tile, int posX, int posY, int prevGuiID)
	{
		super(gui, posX, posY);
		
		this.prevGuiID = prevGuiID;
		this.tileentity = tile;
		this.texture = DEFAULT_TEXTURE;
		
		this.sizeX = 25;
		this.sizeY = 26;
		
		this.textureX = 0;
		this.textureY = 26;
	}
	
	private boolean isMouseOver(int mouseX, int mouseY)
	{
		int buttonX = this.posX + 3;
		int buttonY = this.posY + 4;
		int buttonSize = 18;
		
		return mouseX >= buttonX && mouseX < buttonX + buttonSize &&
			   mouseY >= buttonY && mouseY < buttonY + buttonSize;
	}
	
	@Override
	public void drawBackground(int mouseX, int mouseY, float gameTicks)
	{
		RenderHelper.bindTexture(this.texture);
		
		this.hovered = isMouseOver(mouseX, mouseY);
		int textureOffset = this.hovered ? 25 : 0;
		
		drawTexturedModalRect(posX, posY, textureX + textureOffset, textureY, sizeX, sizeY);
	}

	@Override
	public void drawForeground(int mouseX, int mouseY)
	{
		
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int button)
	{
		if(this.hovered){
			BlockPos pos = this.tileentity.getPos();
			
			PacketHandler.network.sendToServer(new PacketChangeGui.ChangeGuiMessage(GuiTypes.BLUEPRINT_TAB.ordinal(), pos));
		}
	}

	@Override
	public void addTooltip(List<String> list)
	{
		if(this.hovered){
			list.add("Blueprints");
		}
	}
}
