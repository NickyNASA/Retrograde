package com.nicky.retrograde.common.network;

import com.nicky.retrograde.common.network.packets.PacketChangeGui;
import com.nicky.retrograde.common.network.packets.PacketSaveToBlueprint;
import com.nicky.retrograde.common.network.packets.PacketSaveToBlueprint.SaveToBlueprintMessage;
import com.nicky.retrograde.common.network.packets.PacketChangeGui.ChangeGuiMessage;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
	public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel("RETROGRADE");
	
	public void init()
	{
		network.registerMessage(PacketChangeGui.class, ChangeGuiMessage.class, 10, Side.CLIENT);
		network.registerMessage(PacketChangeGui.class, ChangeGuiMessage.class, 11, Side.SERVER);
		network.registerMessage(PacketSaveToBlueprint.class, SaveToBlueprintMessage.class, 15, Side.CLIENT);
		network.registerMessage(PacketSaveToBlueprint.class, SaveToBlueprintMessage.class, 16, Side.SERVER);
		//network.sendTo(message, player);
		//network.sendToServer(message);
	}
}
