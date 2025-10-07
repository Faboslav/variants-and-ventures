package com.faboslav.variantsandventures.neoforge;

import com.faboslav.variantsandventures.common.VariantsAndVenturesClient;
import com.faboslav.variantsandventures.common.config.ModMobsConfig;
import com.faboslav.variantsandventures.common.config.client.gui.VariantsAndVenturesConfigScreen;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityLayersEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityRenderersEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public final class VariantsAndVenturesClientNeoForge
{
	public static void init(IEventBus modEventBus, IEventBus eventBus) {
		VariantsAndVenturesClient.init();

		modEventBus.addListener(VariantsAndVenturesClientNeoForge::onClientSetup);
		modEventBus.addListener(VariantsAndVenturesClientNeoForge::onRegisterEntityRenderers);
		modEventBus.addListener(VariantsAndVenturesClientNeoForge::onRegisterEntityLayers);
	}

	private static void onClientSetup(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			if (ModList.get().isLoaded("yet_another_config_lib_v3")) {
				ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (client, screen) -> {
					return new VariantsAndVenturesConfigScreen(screen);
					//return ModMobsConfig.HANDLER.generateGui().generateScreen(screen);
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
}
