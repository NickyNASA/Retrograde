package com.nicky.retrograde.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy
{
	/**
	 * Used to register the item models in the client proxy.
	 */
	public void registerItemRenders(){}
	
	public void registerBlockRenders(){}
	
	public void registerTileEntities(){}
	
	public EntityPlayer getPlayer(MessageContext context)
	{
		return context.getServerHandler().player;
	}
	
	public void handlePacket(Runnable runnable, EntityPlayer player)
	{
		if(player instanceof EntityPlayerMP){
			((WorldServer)player.world).addScheduledTask(runnable);
		}
	}
}
