package com.nicky.retrograde.api.interfaces;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public interface IPacketHandler
{

	public void onPacket(IMessage message);
}
