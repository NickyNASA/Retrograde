package com.nicky.retrograde.common.tileentity.base;

import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityRetrograde extends TileEntity
{
	protected final boolean checkTime(int ticks)
	{
		return world.getTotalWorldTime() % ticks == 0;
	}
}
