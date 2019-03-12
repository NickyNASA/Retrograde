package com.nicky.retrograde.client.gui.elements;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public abstract class GuiElementBase
{
	protected GuiContainer gui;
	protected ResourceLocation texture;
	protected FontRenderer fontRenderer;
	
	protected int posX;
	protected int posY;
	protected int sizeX;
	protected int sizeY;
	protected int textureX = 256;
	protected int textureY = 256;
	
	protected String name;
	protected boolean visible = true;
	
	public GuiElementBase(GuiContainer gui, int posX, int posY)
	{
		this.gui = gui;
		this.posX = posX;
		this.posY = posY;
	}
	
	public GuiElementBase(GuiContainer gui, int posX, int posY, int sizeX, int sizeY)
	{
		this.gui = gui;
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	/* SETTING FIELDS */
	public GuiElementBase setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public GuiElementBase setPosition(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
		return this;
	}

	public GuiElementBase setSize(int sizeX, int sizeY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		return this;
	}

	public GuiElementBase setTexture(String texture, int texX, int texY)
	{
		this.texture = new ResourceLocation(texture);
		this.textureX = texX;
		this.textureY = texY;
		return this;
	}
	
	public final GuiElementBase setVisible(boolean visible)
	{
		this.visible = visible;
		return this;
	}
	
	/* GETTING FIELDS */
	public String getName()
	{
		return this.name;
	}
	
	public ResourceLocation getTexture()
	{
		return this.texture;
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	
	/* GUI DRAWING */
	public void update(int mouseX, int mouseY) {};
	
	public abstract void drawBackground(int mouseX, int mouseY, float gameTicks);
	
	public abstract void drawForeground(int mouseX, int mouseY);
	
	public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
	{
		gui.drawTexturedModalRect(x, y, textureX, textureY, width, height);
	}
	
	public void addTooltip(List<String> list) {};
	
	/* MOUSE HANDLERS */
	public void mouseClicked(int mouseX, int mouseY, int button) {};
	
	public void mouseClickMove(int mouseX, int mouseY, int button) {};
	
	public void mouseReleased(int mouseX, int mouseY, int button) {};
	
	public boolean intersectsWith(int mouseX, int mouseY)
	{
		return mouseX >= this.posX && mouseX <= this.posX + this.sizeX && mouseY >= this.posY && mouseY <= this.posY + this.sizeY;
	}
}
