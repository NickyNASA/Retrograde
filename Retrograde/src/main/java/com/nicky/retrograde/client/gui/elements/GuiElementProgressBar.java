package com.nicky.retrograde.client.gui.elements;

import java.util.List;

import org.jline.utils.Log;

import com.nicky.retrograde.api.helpers.RenderHelper;
import com.nicky.retrograde.common.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiElementProgressBar extends GuiElementBase
{
	private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_elements.png");
	public static final int DEFAULT_SCALE = 23;
	
	protected int currentProgress;
	protected int texXBase;
	protected int texYBase;
	protected ProgressBarType type;
	
	public GuiElementProgressBar(GuiContainer gui, int posX, int posY, ProgressBarType type)
	{
		super(gui, posX, posY);
		
		this.texture = DEFAULT_TEXTURE;
		
		this.type = type;
		
		switch(type){
		case ARROW_UP:
			this.sizeX = 0;
			this.sizeY = 0;
			
			this.texXBase = 0;
			this.texYBase = 0;
			
			this.textureX = 0;
			this.textureY = 0;
			break;
		case ARROW_DOWN:
			Log.info("Arrow Down");
			this.sizeX = 12;
			this.sizeY = 23;
			
			this.texXBase = 0;
			this.texYBase = 24;
			
			this.textureX = 0;
			this.textureY = 47;
			break;
		case ARROW_LEFT:
			this.sizeX = 0;
			this.sizeY = 0;
			
			this.texXBase = 0;
			this.texYBase = 0;
			
			this.textureX = 0;
			this.textureY = 0;
			break;
		case ARROW_RIGHT:
			this.sizeX = 0;
			this.sizeY = 0;
			
			this.texXBase = 0;
			this.texYBase = 0;
			
			this.textureX = 0;
			this.textureY = 0;
			break;
		}
	}
	
	public GuiElementProgressBar setProgress(int progress)
	{
		this.currentProgress = progress;
		return this;
	}
	
	@Override
	public void drawBackground(int mouseX, int mouseY, float gameTicks)
	{
		RenderHelper.bindTexture(this.texture);
		
		drawTexturedModalRect(posX, posY, texXBase, texYBase, sizeX, sizeY);
		drawTexturedModalRect(posX, posY, textureX, textureY, sizeX, currentProgress);
	}

	@Override
	public void drawForeground(int mouseX, int mouseY)
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {}
	
	@Override
	public void addTooltip(List<String> list)
	{
		// TODO Auto-generated method stub
	}
	
	public enum ProgressBarType
	{
		ARROW_UP,
		ARROW_DOWN,
		ARROW_LEFT,
		ARROW_RIGHT;
	}
}
