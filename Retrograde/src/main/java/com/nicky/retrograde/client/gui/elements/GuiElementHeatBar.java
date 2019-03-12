package com.nicky.retrograde.client.gui.elements;

import com.nicky.retrograde.api.RetrogradeConfig;
import com.nicky.retrograde.common.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiElementHeatBar extends GuiElementBase
{
	private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_elements.png");
	public static final int DEFAULT_SCALE = 50;
	
	protected int sliderY;
	protected int currentTemp;
	protected int texXBase;
	protected int texYBase;
	protected int sliderXBase;
	protected int sliderYBase;
	protected int min;
	protected int max;
	
	//protected int goalTemp;
	//protected int temp;
	
	protected int meltingPoint;
	protected int boilingPoint;
	
	protected boolean follow = false;
	
	public GuiElementHeatBar(GuiContainer gui, int posX, int posY, int min, int max, int melt, int boil)
	{
		super(gui, posX, posY);
		
		this.texture = DEFAULT_TEXTURE;
		this.sliderY = posY + sizeY;
		this.currentTemp = RetrogradeConfig.ROOM_TEMP;
		this.min = min;
		this.max = max;
		this.meltingPoint = melt;
		this.boilingPoint = boil;
		
		this.sizeX = 6;
		this.sizeY = 50;
		this.texXBase = 140;
		this.texYBase = 0;
		this.textureX = 146;
		this.textureY = 0;
	}
	
	@Override
	public void drawBackground(int mouseX, int mouseY, float gameTicks)
	{
		//RenderHelper.bindTexture(this.texture);
		Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
		
		drawTexturedModalRect(posX, posY, texXBase, texYBase, sizeX, sizeY); // Base
		drawTexturedModalRect(posX, currentTemp, textureX, textureY, sizeX, (posY + sizeY) - currentTemp); // Temperature bar
		drawTexturedModalRect(posX - 1, sliderY, 152, 5, 8, 3); // Slider
	}
	
	@Override
	public void drawForeground(int mouseX, int mouseY)
	{
		// TODO Auto-generated method stub
		
	}
	
	/* MOUSE HANDLERS */
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button)
	{
		if(button == 0) {
			if(intersectsWith(mouseX, mouseY)){
				this.follow = true;
			}
		}
	}
	
	@Override
	public void mouseClickMove(int mouseX, int mouseY, int button)
	{
		super.mouseClickMove(mouseX, mouseY, button);
		
		if(this.follow){
			this.sliderY = mouseY;
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int button)
	{
		super.mouseReleased(mouseX, mouseY, button);
		
		this.follow = false;
	}
	
	/* SETTING FIELDS */
	public void setCurrentTemp(int x)
	{
		this.currentTemp = x;
	}
	
	public void setMeltingPoint(int x)
	{
		this.meltingPoint = x;
	}
	
	public void setBoilingPoint(int x)
	{
		this.boilingPoint = x;
	}
	
	/* GETTING FIELDS */
	public int getCurrentTemp()
	{
		return this.currentTemp;
	}
	
	public int getMin()
	{
		return this.min;
	}
	
	public int getMax()
	{
		return this.max;
	}
	
	public int getMeltingPoint() 
	{
		return this.meltingPoint;
	}
	
	public int getBoilingPoint()
	{
		return this.boilingPoint;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private void updateTemp()
//	{
//		if(this.temp != this.goalTemp){
//			if(this.temp < this.goalTemp){
//				this.temp += 1;
//			}
//			else if(this.temp > this.min){
//				this.temp -= 1;
//			}
//		}
//	}
//	
//	public void update()
//	{
//		if(this.follow == true) {
//			this.goalTemp = MathHelper.constrain(x, this.min, this.max);
//		}
//	}
	
}
