package com.nicky.retrograde.common.network.packets;

import com.nicky.retrograde.common.container.machines.ContainerBlueprintTable;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSaveToBlueprint implements IMessageHandler<PacketSaveToBlueprint.SaveToBlueprintMessage, IMessage>
{
	
	@Override
	public IMessage onMessage(SaveToBlueprintMessage message, MessageContext ctx)
	{
		EntityPlayer player = ctx.getServerHandler().player;
		ContainerBlueprintTable container = (ContainerBlueprintTable)player.openContainer;
		
		container.onBlueprintPacket();
	
		return null;
	}
	
	public static class SaveToBlueprintMessage implements IMessage
	{
		public BlockPos pos;
		
		public SaveToBlueprintMessage() {};
		
		public SaveToBlueprintMessage(BlockPos pos)
		{
			this.pos = pos;
		}
		
		@Override
		public void fromBytes(ByteBuf buf)
		{
			int x = buf.readInt();
			int y = buf.readInt();
			int z = buf.readInt();
			this.pos = new BlockPos(x, y, z);
		}

		@Override
		public void toBytes(ByteBuf buf)
		{
			buf.writeInt(this.pos.getX());
			buf.writeInt(this.pos.getY());
			buf.writeInt(this.pos.getZ());
		}
		
	}
}
