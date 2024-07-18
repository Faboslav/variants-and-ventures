package com.faboslav.variantsandventures.neoforge;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.VariantsAndVenturesClient;
import com.faboslav.variantsandventures.common.config.ConfigScreenBuilder;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityLayersEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityRenderersEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterItemColorEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterSkullModelEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesItems;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntry;
import com.faboslav.variantsandventures.common.items.DispenserAddedSpawnEgg;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public final class VariantsAndVenturesClientNeoForge
{
	public static void init(IEventBus modEventBus, IEventBus eventBus) {
		VariantsAndVenturesClient.init();

		modEventBus.addListener(VariantsAndVenturesClientNeoForge::onClientSetup);
		modEventBus.addListener(VariantsAndVenturesClientNeoForge::onRegisterEntityRenderers);
		modEventBus.addListener(VariantsAndVenturesClientNeoForge::onRegisterEntityLayers);
		modEventBus.addListener(VariantsAndVenturesClientNeoForge::onRegisterSkullModels);
		modEventBus.addListener(VariantsAndVenturesClientNeoForge::onRegisterItemColors);
	}

	private static void onClientSetup(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			if (ModList.get().isLoaded("cloth_config")) {
				ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (client, screen) -> {
					return ConfigScreenBuilder.createConfigScreen(VariantsAndVentures.getConfig(), screen);
				});
			}
		});
	}

	private static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		RegisterEntityRenderersEvent.EVENT.invoke(new RegisterEntityRenderersEvent(event::registerEntityRenderer));
	}

	private static void onRegisterEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		RegisterEntityLayersEvent.EVENT.invoke(new RegisterEntityLayersEvent(event::registerLayerDefinition));
	}

	private static void onRegisterSkullModels(EntityRenderersEvent.CreateSkullModels event) {
		RegisterSkullModelEvent.EVENT.invoke(new RegisterSkullModelEvent(event::registerSkullModel, event.getEntityModelSet()));
	}

	private static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
		RegisterItemColorEvent.EVENT.invoke(new RegisterItemColorEvent(event::register, event.getBlockColors()::getColor));
		VariantsAndVenturesItems.ITEMS.stream()
			.map(RegistryEntry::get)
			.filter(item -> item instanceof DispenserAddedSpawnEgg)
			.map(item -> (DispenserAddedSpawnEgg) item)
			.forEach(item -> event.register((stack, index) -> item.getColor(index), item));
	}
}
