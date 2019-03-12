package com.nicky.retrograde.client.gui.machines;

import java.io.IOException;

import com.nicky.retrograde.api.helpers.RenderHelper;
import com.nicky.retrograde.client.gui.base.GuiRetrograde;
import com.nicky.retrograde.common.container.machines.ContainerBlueprintTable;
import com.nicky.retrograde.common.network.PacketHandler;
import com.nicky.retrograde.common.network.packets.PacketSaveToBlueprint;
import com.nicky.retrograde.common.tileentity.machines.TileEntityBlueprintTable;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class GuiBlueprintTable extends GuiRetrograde
{
	private static final ResourceLocation TEXTURE = RenderHelper.makeGuiTextureLocation("blueprint_table");
	
	private final TileEntityBlueprintTable tile;
	
	public GuiBlueprintTable(InventoryPlayer inventory, TileEntityBlueprintTable tile)
	{
		super(new ContainerBlueprintTable(inventory, tile), TEXTURE);
		
		this.tile = tile;
		this.xSize = 176;
		this.ySize = 204;
		
//		this.addElement(new GuiElementSlot(this, 26, 54, SlotType.ACTIVE));
//		
//		for(int y = 0; y < 5; y++){
//			for(int x = 0; x < 5; x++){
//				this.addElement(new GuiElementSlot(this, 26, 54, SlotType.ACTIVE));
//			}
//		}
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		this.buttonList.add(new GuiButton(0, this.guiLeft + 15, this.guiTop + 50, 36, 20, "Write"));
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
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if(button.id == 0){
			BlockPos pos = this.tile.getPos();
			
			PacketHandler.network.sendToServer(new PacketSaveToBlueprint.SaveToBlueprintMessage(pos));
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
	{
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}
}
