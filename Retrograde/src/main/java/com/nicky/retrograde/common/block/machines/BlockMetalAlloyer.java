package com.nicky.retrograde.common.block.machines;

import java.util.Random;

import org.jline.utils.Log;

import com.nicky.retrograde.Retrograde;
import com.nicky.retrograde.api.ItemNames;
import com.nicky.retrograde.api.handlers.GuiHandler.GuiTypes;
import com.nicky.retrograde.common.RetrogradeBlocks;
import com.nicky.retrograde.common.block.base.BlockMachineBase;
import com.nicky.retrograde.common.tileentity.machines.TileEntityMetalAlloyer;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMetalAlloyer extends BlockMachineBase
{
	public BlockMetalAlloyer()
	{
		super(ItemNames.METAL_ALLOYER, Material.IRON);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(RetrogradeBlocks.metalAlloyer);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(RetrogradeBlocks.metalAlloyer);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote){
			Log.info("Opening GUI");
			playerIn.openGui(Retrograde.instance, GuiTypes.METAL_ALLOYER.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		super.onBlockAdded(worldIn, pos, state);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityMetalAlloyer();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntityMetalAlloyer tileentity = (TileEntityMetalAlloyer)worldIn.getTileEntity(pos);
		
		worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(0)));
		worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(1)));
		worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileentity.handler.getStackInSlot(2)));
		
		super.breakBlock(worldIn, pos, state);
	}
}
