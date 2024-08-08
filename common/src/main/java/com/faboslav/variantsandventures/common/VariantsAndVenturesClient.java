package com.faboslav.variantsandventures.common;

import com.faboslav.variantsandventures.common.events.client.*;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesBlocks;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityRenderers;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesItems;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public final class VariantsAndVenturesClient
{
	@Environment(EnvType.CLIENT)
	public static void init() {
		RegisterEntityRenderersEvent.EVENT.addListener(VariantsAndVenturesEntityRenderers::init);
		RegisterEntityLayersEvent.EVENT.addListener(VariantsAndVenturesModelLayers::registerEntityLayers);
		RegisterRenderLayersEvent.EVENT.addListener(VariantsAndVenturesBlocks::registerRenderLayers);
		RegisterBlockColorEvent.EVENT.addListener(VariantsAndVenturesBlocks::registerBlockColors);
		RegisterItemColorEvent.EVENT.addListener(VariantsAndVenturesItems::registerItemColors);
	}
}

