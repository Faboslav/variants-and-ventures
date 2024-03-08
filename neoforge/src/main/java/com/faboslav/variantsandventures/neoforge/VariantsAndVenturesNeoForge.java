package com.faboslav.variantsandventures.neoforge;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.AddItemGroupEntriesEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntityAttributesEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.SetupEvent;
import com.faboslav.variantsandventures.common.init.registry.neoforge.ResourcefulRegistriesImpl;
import net.minecraft.registry.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@Mod(VariantsAndVentures.MOD_ID)
public final class VariantsAndVenturesNeoForge
{
	public VariantsAndVenturesNeoForge(IEventBus modEventBus) {
		IEventBus eventBus = NeoForge.EVENT_BUS;

		modEventBus.addListener(EventPriority.NORMAL, ResourcefulRegistriesImpl::onRegisterNeoForgeRegistries);
		VariantsAndVentures.init();

		if (FMLEnvironment.dist == Dist.CLIENT) {
			VariantsAndVenturesClientNeoForge.init(modEventBus, eventBus);
		}

		modEventBus.addListener(VariantsAndVenturesNeoForge::onSetup);
		modEventBus.addListener(VariantsAndVenturesNeoForge::onAddItemGroupEntries);
		modEventBus.addListener(VariantsAndVenturesNeoForge::onRegisterAttributes);
	}

	private static void onSetup(FMLCommonSetupEvent event) {
		SetupEvent.EVENT.invoke(new SetupEvent(event::enqueueWork));

		event.enqueueWork(() -> {
			VariantsAndVentures.lateInit();
		});
	}

	private static void onAddItemGroupEntries(BuildCreativeModeTabContentsEvent event) {
		AddItemGroupEntriesEvent.EVENT.invoke(
			new AddItemGroupEntriesEvent(
				AddItemGroupEntriesEvent.Type.toType(Registries.ITEM_GROUP.getKey(event.getTab()).orElse(null)),
				event.getTab(),
				event.hasPermissions(),
				event::add
			)
		);
	}

	private static void onRegisterAttributes(EntityAttributeCreationEvent event) {
		RegisterEntityAttributesEvent.EVENT.invoke(new RegisterEntityAttributesEvent((entity, builder) -> event.put(entity, builder.build())));
	}
}
