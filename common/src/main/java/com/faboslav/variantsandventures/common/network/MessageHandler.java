package com.faboslav.variantsandventures.common.network;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.network.packet.SyncZombieIsShakingPacketFromServer;

//? if >= 1.21.1 {
import com.teamresourceful.resourcefullib.common.network.Network;
//?} else {
/*import com.teamresourceful.resourcefullib.common.network.NetworkChannel;
*///?}

public final class MessageHandler
{
	//? if >= 1.21.1 {
	public static final Network DEFAULT_CHANNEL = new Network(VariantsAndVentures.makeID("networking"), 1);
	//?} else {
	/*public static final NetworkChannel DEFAULT_CHANNEL = new NetworkChannel(VariantsAndVentures.MOD_ID, 1, "networking");
	*///?}

	public static void init() {
		DEFAULT_CHANNEL.register(SyncZombieIsShakingPacketFromServer.TYPE);
	}
}
