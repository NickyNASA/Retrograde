package com.nicky.retrograde.common.block;

import org.jline.utils.Log;

import com.nicky.retrograde.api.ItemNames;
import com.nicky.retrograde.common.block.base.BlockRetrograde;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRuby extends BlockRetrograde
{
	public BlockRuby()
	{
		super(ItemNames.RUBY_BLOCK, Material.IRON);
		
		setSoundType(SoundType.METAL);
		setHardness(6.0f);
		setResistance(1f);
		setHarvestLevel("pickaxe", 2);
		//setLightLevel();
		//setLightOpacity(1);
		//setBlockUnbreakable();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState blockState, EntityPlayer entityPlayer, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		//world.setBlockState(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()), Block.getDefaultState());
		//world.createExplosion(entityPlayer, pos.getX(), pos.getY(), pos.getY(), 10.0F, true);
		//world.newExplosion(entityPlayer, pos.getX(), pos.getY(), pos.getZ(), 4.0F, false, true);
		Log.info("Ruby Block Explosion");
		return true;
	}
}
