package com.nicky.retrograde.common.block;

import com.nicky.retrograde.api.ItemNames;
import com.nicky.retrograde.api.enums.MetalVariant;
import com.nicky.retrograde.api.interfaces.IMetaBlockName;
import com.nicky.retrograde.common.block.base.BlockRetrograde;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockMetalStorage extends BlockRetrograde implements IMetaBlockName
{
	public static final PropertyEnum<MetalVariant> TYPE = PropertyEnum.create("type", MetalVariant.class);
			
	public BlockMetalStorage()
	{
		super(ItemNames.METAL_STORAGE, Material.IRON);
		
		setSoundType(SoundType.METAL);
		setHardness(6.0f);
		setResistance(2.0f);
		setHarvestLevel("pickaxe", 2);
		setDefaultState(this.blockState.getBaseState().withProperty(TYPE, MetalVariant.COPPER));
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {TYPE});
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((MetalVariant)state.getValue(TYPE)).getId();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(TYPE, MetalVariant.values()[meta]);
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return getMetaFromState(state);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for(MetalVariant metal : MetalVariant.values()){
			items.add(new ItemStack(this, 1, metal.getId()));
		}
	}
	
	@Override
	public String getSpecialName(ItemStack stack)
	{
		return MetalVariant.values()[stack.getItemDamage()].getName() + "_block";
	}
}
