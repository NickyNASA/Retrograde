package com.nicky.retrograde.common.network.packets;

import org.jline.utils.Log;

import com.nicky.retrograde.Retrograde;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketChangeGui implements IMessageHandler<PacketChangeGui.ChangeGuiMessage, IMessage>
{
	
	@Override
	public IMessage onMessage(ChangeGuiMessage message, MessageContext ctx)
	{
		EntityPlayer player = ctx.getServerHandler().player;
		
		Log.info("Attempting to change to gui id: " + message.gui);
		//player.closeScreen();
		player.openGui(Retrograde.instance, message.gui, player.world, message.pos.getX(), message.pos.getY(), message.pos.getZ());
		return null;
	}
	
	public static class ChangeGuiMessage implements IMessage	
	{
		public int gui;
		public BlockPos pos;
		
		public ChangeGuiMessage() {};
		
		public ChangeGuiMessage(int guiId, BlockPos pos)
		{
			this.gui = guiId;
			this.pos = pos;
		}

		@Override
		public void fromBytes(ByteBuf buf)
		{
			this.gui = buf.readInt();
			int x = buf.readInt();
			int y = buf.readInt();
			int z = buf.readInt();
			this.pos = new BlockPos(x, y, z);
		}
		
		@Override
		public void toBytes(ByteBuf buf)
		{
			buf.writeInt(this.gui);
			buf.writeInt(this.pos.getX());
			buf.writeInt(this.pos.getY());
			buf.writeInt(this.pos.getZ());
		}
	}
}