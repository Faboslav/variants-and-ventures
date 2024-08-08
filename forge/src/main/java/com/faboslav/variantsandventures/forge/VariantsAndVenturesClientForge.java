package com.faboslav.variantsandventures.forge;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.VariantsAndVenturesClient;
import com.faboslav.variantsandventures.common.config.ConfigScreenBuilder;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityLayersEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityRenderersEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterItemColorEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesItems;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntry;
import com.faboslav.variantsandventures.common.items.DispenserAddedSpawnEgg;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public final class VariantsAndVenturesClientForge
{
	public static void init(IEventBus modEventBus, IEventBus eventBus) {
		VariantsAndVentures.getLogger().info("VariantsAndVenturesClientForge init");
		VariantsAndVenturesClient.init();

		modEventBus.addListener(VariantsAndVenturesClientForge::onClientSetup);
		modEventBus.addListener(VariantsAndVenturesClientForge::onRegisterEntityRenderers);
		modEventBus.addListener(VariantsAndVenturesClientForge::onRegisterEntityLayers);
		modEventBus.addListener(VariantsAndVenturesClientForge::onRegisterItemColors);
	}

	private static void onClientSetup(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			if (ModList.get().isLoaded("cloth_config")) {
				ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () ->
					new ConfigScreenHandler.ConfigScreenFactory(
						(mc, screen) -> ConfigScreenBuilder.createConfigScreen(VariantsAndVentures.getConfig(), screen)
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

	private static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
		RegisterItemColorEvent.EVENT.invoke(new RegisterItemColorEvent(event::register, event.getBlockColors()::getColor));
		VariantsAndVenturesItems.ITEMS.stream()
			.map(RegistryEntry::get)
			.filter(item -> item instanceof DispenserAddedSpawnEgg)
			.map(item -> (DispenserAddedSpawnEgg) item)
			.forEach(item -> event.register((stack, index) -> item.getColor(index), item));
	}
}
