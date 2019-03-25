package com.nicky.retrograde.common.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketTakeBlueprint implements IMessageHandler<PacketTakeBlueprint.TakeBlueprintMessage, IMessage>
{
	@Override
	public IMessage onMessage(TakeBlueprintMessage message, MessageContext ctx)
	{
		EntityPlayer player = ctx.getServerHandler().player;
		ContainerBlueprintBase container = (ContainerBlueprintBase)player.openContainer;
		
		container.onTakeBlueprintPacket();
		
		return null;
	}
	
	public static class TakeBlueprintMessage implements IMessage
	{
		public BlockPos pos;
		
		public TakeBlueprintMessage() {};
		
		public TakeBlueprintMessage(BlockPos pos)
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
