package com.nicky.retrograde.client.gui.elements;

import org.jline.utils.Log;

import com.nicky.retrograde.api.helpers.RenderHelper;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class GuiElementScrollBar extends GuiElementBase
{
	private static final ResourceLocation DEFAULT_TEXTURE = RenderHelper.makeGuiTextureLocation("gui_buttons");
	
	protected int currentScroll;
	protected int height;
	
	protected boolean canScroll = false;
	protected boolean follow = false;
	
	public GuiElementScrollBar(GuiContainer gui, int posX, int posY, int height)
	{
		super(gui, posX - 1, posY - 1);
		
		this.texture = DEFAULT_TEXTURE;
		
		this.currentScroll = 0;
		this.sizeX = 10;
		this.sizeY = 13;
		
		this.height = height;
		
		this.textureX = 102;
		this.textureY = 0;
	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float gameTicks)
	{
		RenderHelper.bindTexture(this.texture);
		
		int offset = this.canScroll ? 0 : 10;
		drawTexturedModalRect(posX, posY + currentScroll, textureX + offset, textureY, sizeX, sizeY);
	}

	@Override
	public void drawForeground(int mouseX, int mouseY)
	{
		
	}
	
	@Override
	public void update(int mouseX, int mouseY)
	{
		if(this.follow){
			this.currentScroll = MathHelper.clamp(mouseY - this.posY - 6, 0, this.height - 15);
		}
		
		Log.info(this.currentScroll);
	}
	
	/* MOUSE HANDLERS */
	private boolean isMouseOver(int mouseX, int mouseY)
	{
		int buttonX = this.posX;
		int buttonY = this.posY + this.currentScroll;
		
		return mouseX >= buttonX && mouseX < buttonX + sizeX &&
			   mouseY >= buttonY && mouseY < buttonY + sizeY;
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button)
	{
		if(button == 0) {
			if(isMouseOver(mouseX, mouseY)){
				this.follow = true;
			}
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int button)
	{
		this.follow = false;
	}
	
	public void handleMouseInput()
	{
		
	}
	
	/* SETTING FIELDS */
	public void setValue(int x)
	{
		this.currentScroll = x;
	}
	
	public void setCanScroll(boolean canScroll)
	{
		this.canScroll = canScroll;
	}
	
	/* GETTING FIELDS */
	public int getValue()
	{
		return this.currentScroll;
	}
}
