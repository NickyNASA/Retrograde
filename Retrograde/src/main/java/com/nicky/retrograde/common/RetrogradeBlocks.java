package com.nicky.retrograde.common;

import com.nicky.retrograde.common.block.BlockMetalStorage;
import com.nicky.retrograde.common.block.BlockRuby;
import com.nicky.retrograde.common.block.item.ItemBlockWithMetadata;
import com.nicky.retrograde.common.block.machines.BlockBlueprintTable;
import com.nicky.retrograde.common.block.machines.BlockCircuitAssembler;
import com.nicky.retrograde.common.block.machines.BlockCircuitAssemblerAdvanced;
import com.nicky.retrograde.common.block.machines.BlockPartAssembler;
import com.nicky.retrograde.common.block.machines.BlockMetalAlloyer;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class RetrogradeBlocks extends Blocks 
{
	public static final Block ruby = new BlockRuby();
	public static final Block metalStorage = new BlockMetalStorage();
	
	// Machines
	public static final Block blueprintTable = new BlockBlueprintTable();
	public static final Block circuitAssembler = new BlockCircuitAssembler();
	public static final Block circuitAssemblerAdvanced = new BlockCircuitAssemblerAdvanced();
	public static final Block partAssembler = new BlockPartAssembler();
	public static final Block metalAlloyer = new BlockMetalAlloyer();
	
	
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		IForgeRegistry<Block> registry = event.getRegistry();
		
		registry.register(ruby);
		registry.register(metalStorage);
		
		// Machines
		registry.register(blueprintTable);
		registry.register(circuitAssembler);
		registry.register(circuitAssemblerAdvanced);
		registry.register(partAssembler);
		registry.register(metalAlloyer);
	}
	
	public static void registerItemBlocks(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(new ItemBlock(ruby).setRegistryName(ruby.getRegistryName().toString()));
		registry.register(new ItemBlockWithMetadata(metalStorage).setRegistryName(metalStorage.getRegistryName().toString()));
		
		// Machines
		registry.register(new ItemBlock(blueprintTable).setRegistryName(blueprintTable.getRegistryName().toString()));
		registry.register(new ItemBlock(circuitAssembler).setRegistryName(circuitAssembler.getRegistryName().toString()));
		registry.register(new ItemBlock(circuitAssemblerAdvanced).setRegistryName(circuitAssemblerAdvanced.getRegistryName().toString()));
		registry.register(new ItemBlock(partAssembler).setRegistryName(partAssembler.getRegistryName().toString()));
		registry.register(new ItemBlock(metalAlloyer).setRegistryName(metalAlloyer.getRegistryName().toString()));
	}
	
	
}
