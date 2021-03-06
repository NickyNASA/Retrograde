package com.nicky.retrograde.common.block.machines;

import java.util.Random;

import com.nicky.retrograde.Retrograde;
import com.nicky.retrograde.api.ItemNames;
import com.nicky.retrograde.api.handlers.GuiHandler.GuiTypes;
import com.nicky.retrograde.api.helpers.ItemHelper;
import com.nicky.retrograde.common.RetrogradeBlocks;
import com.nicky.retrograde.common.block.base.BlockMachineBase;
import com.nicky.retrograde.common.tileentity.machines.TileEntityCircuitAssemblerAdvanced;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCircuitAssemblerAdvanced extends BlockMachineBase
{

	public BlockCircuitAssemblerAdvanced()
	{
		super(ItemNames.CIRCUIT_ASSEMBLER_ADVANCED, Material.IRON);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(RetrogradeBlocks.circuitAssemblerAdvanced);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote){
			playerIn.openGui(Retrograde.instance, GuiTypes.CIRCUIT_ASSEMBLER_ADVANCED.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityCircuitAssemblerAdvanced();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		ItemHelper.dropInventory(worldIn, (TileEntityCircuitAssemblerAdvanced)worldIn.getTileEntity(pos), pos);
		
		super.breakBlock(worldIn, pos, state);
	}
}
