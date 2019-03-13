package com.nicky.retrograde.common.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketAddBlueprint implements IMessageHandler<PacketAddBlueprint.AddBlueprintMessage, IMessage>
{
	@Override
	public IMessage onMessage(AddBlueprintMessage message, MessageContext ctx)
	{
		EntityPlayer player = ctx.getServerHandler().player;
		
		Container container = player.openContainer;
		//container.onAddBlueprintPacket();
		container.detectAndSendChanges();
		
		return null;
	}
	
	public static class AddBlueprintMessage implements IMessage
	{
		public BlockPos pos;
		
		public AddBlueprintMessage() {};
		
		public AddBlueprintMessage(BlockPos pos)
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
