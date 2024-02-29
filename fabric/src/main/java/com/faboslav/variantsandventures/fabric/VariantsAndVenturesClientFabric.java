package com.faboslav.variantsandventures.fabric;

import com.faboslav.variantsandventures.common.VariantsAndVenturesClient;
import com.faboslav.variantsandventures.common.events.client.RegisterItemColorEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

public final class VariantsAndVenturesClientFabric implements ClientModInitializer
{
	@Override
	@Environment(EnvType.CLIENT)
	public void onInitializeClient() {
		VariantsAndVenturesClient.init();
		VariantsAndVenturesClient.postInit();

		initEvents();
	}

	private void initEvents() {
		RegisterItemColorEvent.EVENT.invoke(new RegisterItemColorEvent(
			ColorProviderRegistry.ITEM::register,
			(state, level, pos, i) -> ColorProviderRegistry.BLOCK.get(state.getBlock()).getColor(state, level, pos, i)));
	}
}
