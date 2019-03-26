package com.nicky.retrograde.api.handlers;

import com.nicky.retrograde.client.gui.machines.GuiBlueprintTable;
import com.nicky.retrograde.client.gui.machines.GuiCircuitAssembler;
import com.nicky.retrograde.client.gui.machines.GuiCircuitAssemblerAdvanced;
import com.nicky.retrograde.client.gui.machines.GuiMetalAlloyer;
import com.nicky.retrograde.client.gui.machines.GuiPartAssembler;
import com.nicky.retrograde.client.gui.machines.tabs.GuiBlueprintTab;
import com.nicky.retrograde.client.gui.machines.tabs.GuiUpgradeTab;
import com.nicky.retrograde.common.container.machines.ContainerBlueprintTab;
import com.nicky.retrograde.common.container.machines.ContainerBlueprintTable;
import com.nicky.retrograde.common.container.machines.ContainerCircuitAssembler;
import com.nicky.retrograde.common.container.machines.ContainerCircuitAssemblerAdvanced;
import com.nicky.retrograde.common.container.machines.ContainerMetalAlloyer;
import com.nicky.retrograde.common.container.machines.ContainerPartAssembler;
import com.nicky.retrograde.common.container.machines.ContainerUpgradeTab;
import com.nicky.retrograde.common.tileentity.base.TileEntityMachineBase;
import com.nicky.retrograde.common.tileentity.machines.TileEntityBlueprintTable;
import com.nicky.retrograde.common.tileentity.machines.TileEntityCircuitAssembler;
import com.nicky.retrograde.common.tileentity.machines.TileEntityCircuitAssemblerAdvanced;
import com.nicky.retrograde.common.tileentity.machines.TileEntityMetalAlloyer;
import com.nicky.retrograde.common.tileentity.machines.TileEntityPartAssembler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public enum GuiTypes
	{
		BLUEPRINT_TABLE,
		CIRCUIT_ASSEMBLER,
		CIRCUIT_ASSEMBLER_ADVANCED,
		PART_ASSEMBLER,
		METAL_ALLOYER,
		UPGRADE_TAB,
		BLUEPRINT_TAB;
	}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		
		switch(GuiTypes.values()[id]){
		case BLUEPRINT_TABLE:
			return new ContainerBlueprintTable(player.inventory, (TileEntityBlueprintTable)tile);
		case CIRCUIT_ASSEMBLER:
			return new ContainerCircuitAssembler(player.inventory, (TileEntityCircuitAssembler)tile);
		case CIRCUIT_ASSEMBLER_ADVANCED:
			return new ContainerCircuitAssemblerAdvanced(player.inventory, (TileEntityCircuitAssemblerAdvanced)tile);
		case PART_ASSEMBLER:
			return new ContainerPartAssembler(player.inventory, (TileEntityPartAssembler)tile);
		case METAL_ALLOYER:
			return new ContainerMetalAlloyer(player.inventory, (TileEntityMetalAlloyer)tile);
		case UPGRADE_TAB:
			return new ContainerUpgradeTab(player.inventory, (TileEntityMachineBase)tile);
		case BLUEPRINT_TAB:
			return new ContainerBlueprintTab(player.inventory, (TileEntityMachineBase)tile);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		
		switch(GuiTypes.values()[id]){
		case BLUEPRINT_TABLE:
			return new GuiBlueprintTable(player.inventory, (TileEntityBlueprintTable)tile);
		case CIRCUIT_ASSEMBLER:
			return new GuiCircuitAssembler(player.inventory, (TileEntityCircuitAssembler)tile);
		case CIRCUIT_ASSEMBLER_ADVANCED:
			return new GuiCircuitAssemblerAdvanced(player.inventory, (TileEntityCircuitAssemblerAdvanced)tile);
		case PART_ASSEMBLER:
			return new GuiPartAssembler(player.inventory, (TileEntityPartAssembler)tile);
		case METAL_ALLOYER:
			return new GuiMetalAlloyer(player.inventory, (TileEntityMetalAlloyer)tile);
		case UPGRADE_TAB:
			return new GuiUpgradeTab(player.inventory, (TileEntityMachineBase) tile);
		case BLUEPRINT_TAB:
			return new GuiBlueprintTab(player.inventory, (TileEntityMachineBase)tile);
		default:
			return null;
		}
	}

}
