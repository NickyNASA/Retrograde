package com.nicky.retrograde;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nicky.retrograde.api.enums.MetalVariant;
import com.nicky.retrograde.api.handlers.GuiHandler;
import com.nicky.retrograde.api.helpers.StringHelper;
import com.nicky.retrograde.api.recipes.MetalAlloyerManager;
import com.nicky.retrograde.api.recipes.PartAssemblerManager;
import com.nicky.retrograde.common.CommonProxy;
import com.nicky.retrograde.common.CreativeTabRetrograde;
import com.nicky.retrograde.common.Reference;
import com.nicky.retrograde.common.RetrogradeBlocks;
import com.nicky.retrograde.common.RetrogradeItems;
import com.nicky.retrograde.common.network.PacketHandler;
import com.nicky.retrograde.common.tileentity.machines.TileEntityBlueprintTable;
import com.nicky.retrograde.common.tileentity.machines.TileEntityCircuitAssembler;
import com.nicky.retrograde.common.tileentity.machines.TileEntityCircuitAssemblerAdvanced;
import com.nicky.retrograde.common.tileentity.machines.TileEntityMetalAlloyer;
import com.nicky.retrograde.common.tileentity.machines.TileEntityPartAssembler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
@Mod.EventBusSubscriber()
public class Retrograde
{
	public static PacketHandler packetHandler = new PacketHandler();
	public static Logger logger = LogManager.getLogger("Retrograde");
	public static CreativeTabRetrograde tabRetrograde = new CreativeTabRetrograde();
	
	/*
	 * 
	 * machine constructor multiblock with laser to craft the main machine block
	 * 
	 * have two tiers of the machine crafter. One at the beginning and one later in the game that automatically puts items in the right slot
	 * 
	 * shift click on main block to show ghost blocks
	 * 
	 * use the machine block in the multiblock (it would be the master)
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Circuit board base -> Circuit Assembler (Machine)
	 * 
	 * Circuit Assembler (can make circuit boards for crafting or custom circuit boards)
	 * 		- Circuit boards for crafting
	 * 		- Custom circuit boards (can use blueprints) -> make programs that use the circuit boards
	 */
	
	@Instance
	public static Retrograde instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
	
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		packetHandler.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		//ModRecipes.init();
		
		//RegistryHandler.initRegistries();
		registerOreDict();
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		initRecipes();
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		RetrogradeItems.registerItems(event);
		RetrogradeBlocks.registerItemBlocks(event);
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		RetrogradeBlocks.registerBlocks(event);
		
		registerTileEntities();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		proxy.registerItemRenders();
		proxy.registerBlockRenders();
	}
	
	public static void initRecipes()
	{
		MetalAlloyerManager.init();
		PartAssemblerManager.init();
	}
	
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityBlueprintTable.class, new ResourceLocation(Reference.MOD_ID + ":blueprint_table"));
		GameRegistry.registerTileEntity(TileEntityCircuitAssembler.class, new ResourceLocation(Reference.MOD_ID + ":circuit_assembler"));
		GameRegistry.registerTileEntity(TileEntityCircuitAssemblerAdvanced.class, new ResourceLocation(Reference.MOD_ID + ":circuit_assembler_advanced"));
		GameRegistry.registerTileEntity(TileEntityPartAssembler.class, new ResourceLocation(Reference.MOD_ID + ":part_assembler"));
		GameRegistry.registerTileEntity(TileEntityMetalAlloyer.class, new ResourceLocation(Reference.MOD_ID + ":metal_alloyer"));
	}
	
	public static void registerOreDict()
	{
		// Register's all nuggets, ingots, and blocks from the MetalVariant Enum
		for(MetalVariant type : MetalVariant.values()){
			OreDictionary.registerOre("nugget" + StringHelper.capitalize(type.getName()), new ItemStack(RetrogradeItems.nugget, 1, type.getId()));
			OreDictionary.registerOre("ingot" + StringHelper.capitalize(type.getName()), new ItemStack(RetrogradeItems.ingot, 1, type.getId()));
			OreDictionary.registerOre("block" + StringHelper.capitalize(type.getName()), new ItemStack(RetrogradeBlocks.metalStorage, 1, type.getId()));
		}
		
		/*
		OreDictionary.registerOre("nuggetCopper"   , new ItemStack(RetrogradeItems.nugget, 1, 0));
		OreDictionary.registerOre("nuggetTin"      , new ItemStack(RetrogradeItems.nugget, 1, 1));
		OreDictionary.registerOre("nuggetAluminum" , new ItemStack(RetrogradeItems.nugget, 1, 2));
		OreDictionary.registerOre("nuggetSteel"    , new ItemStack(RetrogradeItems.nugget, 1, 3));
		OreDictionary.registerOre("nuggetTitanium" , new ItemStack(RetrogradeItems.nugget, 1, 4));
		
		OreDictionary.registerOre("ingotCopper"   , new ItemStack(RetrogradeItems.ingot, 1, 0));
		OreDictionary.registerOre("ingotTin"      , new ItemStack(RetrogradeItems.ingot, 1, 1));
		OreDictionary.registerOre("ingotAluminum" , new ItemStack(RetrogradeItems.ingot, 1, 2));
		OreDictionary.registerOre("ingotSteel"    , new ItemStack(RetrogradeItems.ingot, 1, 3));
		OreDictionary.registerOre("ingotTitanium" , new ItemStack(RetrogradeItems.ingot, 1, 4));
		
		OreDictionary.registerOre("blockCopper"   , new ItemStack(RetrogradeBlocks.metalStorage, 1, 0));
		OreDictionary.registerOre("blockTin"      , new ItemStack(RetrogradeBlocks.metalStorage, 1, 1));
		OreDictionary.registerOre("blockAluminum" , new ItemStack(RetrogradeBlocks.metalStorage, 1, 2));
		OreDictionary.registerOre("blockSteel"    , new ItemStack(RetrogradeBlocks.metalStorage, 1, 3));
		OreDictionary.registerOre("blockTitanium" , new ItemStack(RetrogradeBlocks.metalStorage, 1, 4));*/
		
	}
	
	
}
