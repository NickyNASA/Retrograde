package com.nicky.retrograde.client.gui.elements;

import com.nicky.retrograde.api.helpers.RenderHelper;
import com.nicky.retrograde.common.network.PacketHandler;
import com.nicky.retrograde.common.network.packets.PacketAddBlueprint;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class GuiElementButtonInsert extends GuiElementBase
{
	private static final ResourceLocation DEFAULT_TEXTURE = RenderHelper.makeGuiTextureLocation("gui_buttons");
	
	protected boolean hovered = false;
	
	public TileEntity tileentity;
	
	public GuiElementButtonInsert(GuiContainer gui, TileEntity tile, int posX, int posY, int mode)
	{
		super(gui, posX, posY);
		
		this.tileentity = tile;
		this.texture = DEFAULT_TEXTURE;
		
		this.sizeX = 10;
		this.sizeY = 16;
		
		switch(mode){
		case 0:
			this.textureX = 82;
			this.textureY = 0;
			break;
		case 1:
			this.textureX = 82;
			this.textureY = 16;
			break;
		}
	}

	@Override
	public void drawBackground(int mouseX, int mouseY, float gameTicks)
	{
		RenderHelper.bindTexture(this.texture);
		
		this.hovered = isMouseOver(mouseX, mouseY);
		int textureOffset = this.hovered ? 10 : 0;
		drawTexturedModalRect(posX, posY, textureX + textureOffset, textureY, sizeX, sizeY);
	}

	@Override
	public void drawForeground(int mouseX, int mouseY)
	{
		
	}
	
	/* MOUSE HANDLERS */
	private boolean isMouseOver(int mouseX, int mouseY)
	{
		return mouseX >= posX && mouseX < posX + sizeX &&
			   mouseY >= posY && mouseY < posY + sizeY;
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button)
	{
		if(button == 0) {
			if(this.hovered){
				BlockPos pos = this.tileentity.getPos();
				
				PacketHandler.network.sendToServer(new PacketAddBlueprint.AddBlueprintMessage(pos));
			}
		}
	}
}
