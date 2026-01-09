package com.faboslav.variantsandventures.fabric;

import com.faboslav.variantsandventures.common.VariantsAndVenturesClient;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityLayersEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityRenderersEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

//? if < 1.21.4 {
/*import com.faboslav.variantsandventures.common.events.item.RegisterItemColorEvent;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
*///?}

public final class VariantsAndVenturesClientFabric implements ClientModInitializer
{
	@Override
	public void onInitializeClient() {
		VariantsAndVenturesClient.init();

		initEvents();
	}

	private void initEvents() {
		RegisterEntityRenderersEvent.EVENT.invoke(new RegisterEntityRenderersEvent(EntityRendererRegistry::register));
		RegisterEntityLayersEvent.EVENT.invoke(new RegisterEntityLayersEvent((type, supplier) -> EntityModelLayerRegistry.registerModelLayer(type, supplier::get)));
		//? if < 1.21.4 {
		/*RegisterItemColorEvent.EVENT.invoke(new RegisterItemColorEvent(ColorProviderRegistry.ITEM::register));
		*///?}
	}
}
