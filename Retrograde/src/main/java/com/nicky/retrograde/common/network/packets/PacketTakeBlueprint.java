package com.nicky.retrograde.common.network.packets;

import com.nicky.retrograde.common.container.base.ContainerMachineBase;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketTakeBlueprint implements IMessageHandler<PacketTakeBlueprint.TakeBlueprintMessage, IMessage>
{
	@Override
	public IMessage onMessage(TakeBlueprintMessage message, MessageContext ctx)
	{
		EntityPlayer player = ctx.getServerHandler().player;
		ContainerMachineBase container = (ContainerMachineBase)player.openContainer;
		
		container.onTakeBlueprintPacket(message.index);
		
		return null;
	}
	
	public static class TakeBlueprintMessage implements IMessage
	{
		public int index;
		
		public TakeBlueprintMessage() {};
		
		public TakeBlueprintMessage(int index)
		{
			this.index = index;
		}
		
		@Override
		public void fromBytes(ByteBuf buf)
		{
			this.index = buf.readInt();
		}

		@Override
		public void toBytes(ByteBuf buf)
		{
			buf.writeInt(this.index);
		}
	}
}
