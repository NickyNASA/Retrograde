package com.nicky.retrograde.client.gui.elements;

import com.nicky.retrograde.api.helpers.RenderHelper;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiElementSlot extends GuiElementBase
{
	private static final ResourceLocation DEFAULT_TEXTURE = RenderHelper.makeGuiTextureLocation("gui_elements");
	
	public GuiElementSlot(GuiContainer gui, int posX, int posY, SlotType type)
	{
		super(gui, posX - 1, posY - 1);
		
		this.texture = DEFAULT_TEXTURE;
		this.sizeX = 18;
		this.sizeY = 18;
		
		switch(type){
		case ACTIVE:
			this.textureX = 46;
			this.textureY = 0;
			break;
		case INACTIVE:
			this.textureX = 46;
			this.textureY = 18;
			break;
		case LOCKED:
			this.textureX = 46;
			this.textureY = 36;
			break;
		}
	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float gameTicks)
	{
		RenderHelper.bindTexture(this.texture);
		
		drawTexturedModalRect(posX, posY, textureX, textureY, sizeX, sizeY);
	}

	@Override
	public void drawForeground(int mouseX, int mouseY) {}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {}
	
	public enum SlotType
	{
		ACTIVE,
		INACTIVE,
		LOCKED;
	}
}
