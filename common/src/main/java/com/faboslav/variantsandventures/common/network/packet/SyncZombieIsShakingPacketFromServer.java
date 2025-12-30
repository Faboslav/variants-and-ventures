package com.faboslav.variantsandventures.common.network.packet;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.api.ZombieApi;
import com.faboslav.variantsandventures.common.network.MessageHandler;
import com.teamresourceful.resourcefullib.common.network.Packet;
import com.teamresourceful.resourcefullib.common.network.base.ClientboundPacketType;
import com.teamresourceful.resourcefullib.common.network.base.PacketType;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.zombie.Zombie;

//? if >= 1.21.1 {
import net.minecraft.network.RegistryFriendlyByteBuf;
//?} else {
/*import net.minecraft.network.FriendlyByteBuf;
*///?}

public record SyncZombieIsShakingPacketFromServer(int zombieId, boolean isShaking) implements Packet<SyncZombieIsShakingPacketFromServer> {

	public static final Identifier ID = VariantsAndVentures.makeID("sync_horse_owner_uuid_from_server");
	public static final ClientboundPacketType<SyncZombieIsShakingPacketFromServer> TYPE = new Handler();

	public static void sendToClient(Entity entity, int zombieId, boolean isShaking) {
		MessageHandler.DEFAULT_CHANNEL.sendToPlayersInLevel(new SyncZombieIsShakingPacketFromServer(zombieId, isShaking), entity.level());
	}

	@Override
	public PacketType<SyncZombieIsShakingPacketFromServer> type() {
		return TYPE;
	}

	private static final class Handler implements ClientboundPacketType<SyncZombieIsShakingPacketFromServer>
	{
		@Override
		public void encode(
			SyncZombieIsShakingPacketFromServer message,
			//? if >= 1.21.1 {
			RegistryFriendlyByteBuf buffer
			//?} else {
			/*FriendlyByteBuf buffer
			 *///?}
		) {
			buffer.writeVarInt(message.zombieId());
			buffer.writeBoolean(message.isShaking());
		}

		@Override
		public SyncZombieIsShakingPacketFromServer decode(
			//? if >= 1.21.1 {
			RegistryFriendlyByteBuf buffer
			//?} else {
			/*FriendlyByteBuf buffer
			*///?}
		) {
			return new SyncZombieIsShakingPacketFromServer(buffer.readVarInt(), buffer.readBoolean());
		}

		@Override
		public Runnable handle(SyncZombieIsShakingPacketFromServer packet) {
			return () -> {
				Entity entity = Minecraft.getInstance().level.getEntity(packet.zombieId());

				if (entity instanceof Zombie zombie) {
					((ZombieApi)zombie).variantsandventures$setFreezeConverting(packet.isShaking());
				}
			};
		}

		//? if < 1.21.1 {
		/*@Override
		public Class<SyncZombieIsShakingPacketFromServer> type() {
			return SyncZombieIsShakingPacketFromServer.class;
		}
		*///?}

		@Override
		public Identifier id() {
			return ID;
		}
	}
}
