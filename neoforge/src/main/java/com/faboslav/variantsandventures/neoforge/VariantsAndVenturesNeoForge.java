package com.faboslav.variantsandventures.neoforge;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.AddItemGroupEntriesEvent;
import com.faboslav.variantsandventures.common.events.entity.EntitySpawnEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntityAttributesEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntitySpawnRestrictionsEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.SetupEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesStructurePoolAliases;
import com.faboslav.variantsandventures.common.init.registry.neoforge.ResourcefulRegistriesImpl;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;

@Mod(VariantsAndVentures.MOD_ID)
public final class VariantsAndVenturesNeoForge
{
	public VariantsAndVenturesNeoForge(IEventBus modEventBus) {
		IEventBus eventBus = NeoForge.EVENT_BUS;

		VariantsAndVentures.init();

		if (FMLEnvironment.dist == Dist.CLIENT) {
			VariantsAndVenturesClientNeoForge.init(modEventBus, eventBus);
		}

		eventBus.addListener(VariantsAndVenturesNeoForge::onServerAboutToStartEvent);
		eventBus.addListener(VariantsAndVenturesNeoForge::onEntitySpawn);

		modEventBus.addListener(VariantsAndVenturesNeoForge::onSetup);
		modEventBus.addListener(VariantsAndVenturesNeoForge::onAddItemGroupEntries);
		modEventBus.addListener(VariantsAndVenturesNeoForge::onRegisterAttributes);
		modEventBus.addListener(VariantsAndVenturesNeoForge::onRegisterSpawnRestrictions);
	}

	private static void onSetup(FMLCommonSetupEvent event) {
		SetupEvent.EVENT.invoke(new SetupEvent(event::enqueueWork));
	}

	private static void onEntitySpawn(FinalizeSpawnEvent event) {
		EntitySpawnEvent.EVENT.invoke(new EntitySpawnEvent(event.getEntity(), event.getLevel(), event.getEntity().isBaby(), event.getSpawnType()), event.isCanceled());
	}

	private static void onAddItemGroupEntries(BuildCreativeModeTabContentsEvent event) {
		AddItemGroupEntriesEvent.EVENT.invoke(
			new AddItemGroupEntriesEvent(
				AddItemGroupEntriesEvent.Type.toType(BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(event.getTab()).orElse(null)),
				event.getTab(),
				event.hasPermissions(),
				event::accept
			)
		);
	}

	private static void onRegisterAttributes(EntityAttributeCreationEvent event) {
		RegisterEntityAttributesEvent.EVENT.invoke(new RegisterEntityAttributesEvent((entity, builder) -> event.put(entity, builder.build())));
	}

	private static void onRegisterSpawnRestrictions(RegisterSpawnPlacementsEvent event) {
		RegisterEntitySpawnRestrictionsEvent.EVENT.invoke(new RegisterEntitySpawnRestrictionsEvent(VariantsAndVenturesNeoForge.registerEntitySpawnRestriction(event)));
	}

	private static RegisterEntitySpawnRestrictionsEvent.Registrar registerEntitySpawnRestriction(
		RegisterSpawnPlacementsEvent event
	) {
		return new RegisterEntitySpawnRestrictionsEvent.Registrar()
		{
			@Override
			public <T extends Mob> void register(
				EntityType<T> type,
				RegisterEntitySpawnRestrictionsEvent.Placement<T> placement
			) {
				event.register(type, placement.location(), placement.heightmap(), placement.predicate(), RegisterSpawnPlacementsEvent.Operation.AND);
			}
		};
	}

	public static void onServerAboutToStartEvent(ServerAboutToStartEvent event) {
		VariantsAndVenturesStructurePoolAliases.init(event.getServer());
	}
}
