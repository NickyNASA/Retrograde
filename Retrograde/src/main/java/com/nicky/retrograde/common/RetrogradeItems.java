package com.nicky.retrograde.common;

import com.nicky.retrograde.common.item.ItemBlueprint;
import com.nicky.retrograde.common.item.ItemIngot;
import com.nicky.retrograde.common.item.ItemNugget;
import com.nicky.retrograde.common.item.ItemRuby;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public final class RetrogradeItems
{
	public static final Item itemRuby = new ItemRuby();
	public static final Item blueprint = new ItemBlueprint();
	public static final Item nugget = new ItemNugget();
	public static final Item ingot = new ItemIngot();
	
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> registry = event.getRegistry();
				
		registry.register(itemRuby);
		registry.register(blueprint);
		registry.register(nugget);
		registry.register(ingot);
	}
	
	
	/*/* MATERIALS 
	public static final ToolMaterial MATERIAL_RUBY = EnumHelper.addToolMaterial("material_ruby", 3, 952, 10.0F, 4.0F, 12);
	public static final ArmorMaterial ARMOR_MATERIAL_RUBY = EnumHelper.addArmorMaterial("armor_material_ruby", Reference.MOD_ID + ":ruby", 35, new int[]{4, 7, 8, 4}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
	
	/* ITEMS 
	public static final Item OBSIDIAN_INGOT = new ItemBase("obsidian_ingot");
	public static final Item RUBY = new ItemBase("ruby");
	
	/* UPGRADES 
	public static final ItemMachineUpgrade ENERGY_UPGRADE_1 = new ItemMachineUpgrade(Upgrade.ENERGY_1);
	public static final ItemMachineUpgrade ENERGY_UPGRADE_2 = new ItemMachineUpgrade(Upgrade.ENERGY_2);
	public static final ItemMachineUpgrade SPEED_UPGRADE_1 = new ItemMachineUpgrade(Upgrade.SPEED_1);
	public static final ItemMachineUpgrade SPEED_UPGRADE_2 = new ItemMachineUpgrade(Upgrade.SPEED_2);
	
	/* TOOLS 
	public static final ItemSword RUBY_SWORD = new ToolSword("ruby_sword", MATERIAL_RUBY);
	public static final ItemPickaxe RUBY_PICKAXE = new ToolPickaxe("ruby_pickaxe", MATERIAL_RUBY);
	public static final ItemAxe RUBY_AXE = new ToolAxe("ruby_axe", MATERIAL_RUBY, 10.0F, -3.0F);
	
	/* ARMOR 
	public static final Item RUBY_HELMET = new ModArmor("ruby_helmet", ARMOR_MATERIAL_RUBY, 1, EntityEquipmentSlot.HEAD);
	public static final Item RUBY_CHESTPLATE = new ModArmor("ruby_chestplate", ARMOR_MATERIAL_RUBY, 1, EntityEquipmentSlot.CHEST);
	public static final Item RUBY_LEGGINGS = new ModArmor("ruby_leggings", ARMOR_MATERIAL_RUBY, 2, EntityEquipmentSlot.LEGS);
	public static final Item RUBY_BOOTS = new ModArmor("ruby_boots", ARMOR_MATERIAL_RUBY, 1, EntityEquipmentSlot.FEET);*/
}
