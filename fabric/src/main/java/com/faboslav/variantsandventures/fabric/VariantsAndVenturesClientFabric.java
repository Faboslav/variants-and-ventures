package com.faboslav.variantsandventures.fabric;

import com.faboslav.variantsandventures.common.VariantsAndVenturesClient;
import com.faboslav.variantsandventures.common.events.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public final class VariantsAndVenturesClientFabric implements ClientModInitializer
{
	@Override
	@Environment(EnvType.CLIENT)
	public void onInitializeClient() {
		VariantsAndVenturesClient.init();

		initEvents();
	}

	private void initEvents() {
		RegisterEntityRenderersEvent.EVENT.invoke(new RegisterEntityRenderersEvent(EntityRendererRegistry::register));
		RegisterEntityLayersEvent.EVENT.invoke(new RegisterEntityLayersEvent((type, supplier) -> EntityModelLayerRegistry.registerModelLayer(type, supplier::get)));
		RegisterRenderLayersEvent.EVENT.invoke(new RegisterRenderLayersEvent(BlockRenderLayerMap.INSTANCE::putFluid, BlockRenderLayerMap.INSTANCE::putBlock));
		RegisterBlockColorEvent.EVENT.invoke(new RegisterBlockColorEvent(ColorProviderRegistry.BLOCK::register));
		RegisterItemColorEvent.EVENT.invoke(new RegisterItemColorEvent(ColorProviderRegistry.ITEM::register,
			(state, level, pos, i) -> ColorProviderRegistry.BLOCK.get(state.getBlock()).getColor(state, level, pos, i)));

	}
}
