package com.faboslav.variantsandventures.forge;

import com.faboslav.variantsandventures.common.VariantsAndVenturesClient;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityLayersEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityRenderersEvent;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;

@SuppressWarnings({"all", "deprecated", "removal"})
public final class VariantsAndVenturesClientForge
{
	public static void init(IEventBus modEventBus, IEventBus eventBus) {
		VariantsAndVenturesClient.init();

		modEventBus.addListener(VariantsAndVenturesClientForge::onClientSetup);
		modEventBus.addListener(VariantsAndVenturesClientForge::onRegisterEntityRenderers);
		modEventBus.addListener(VariantsAndVenturesClientForge::onRegisterEntityLayers);
	}

	private static void onClientSetup(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			if (ModList.get().isLoaded("yet_another_config_lib_v3")) {
				ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () ->
					new ConfigScreenHandler.ConfigScreenFactory(
						(mc, screen) -> VariantsAndVenturesClient.getConfigScreen(screen)
					)
				);
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
