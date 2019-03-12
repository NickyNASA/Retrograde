package com.nicky.retrograde.client.gui.machines;

import com.nicky.retrograde.api.helpers.RenderHelper;
import com.nicky.retrograde.client.gui.base.GuiRetrograde;
import com.nicky.retrograde.common.container.machines.ContainerCircuitAssemblerAdvanced;
import com.nicky.retrograde.common.tileentity.machines.TileEntityCircuitAssemblerAdvanced;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCircuitAssemblerAdvanced extends GuiRetrograde
{
	private static final ResourceLocation TEXTURE = RenderHelper.makeGuiTextureLocation("circuit_assembler_advanced");
	
	public GuiCircuitAssemblerAdvanced(InventoryPlayer inventory, TileEntityCircuitAssemblerAdvanced tile)
	{
		super(new ContainerCircuitAssemblerAdvanced(inventory, tile), TEXTURE);
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
	}
	
}
