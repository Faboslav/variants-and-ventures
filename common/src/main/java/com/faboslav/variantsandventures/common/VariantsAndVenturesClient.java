package com.faboslav.variantsandventures.common;

import com.faboslav.variantsandventures.common.events.client.RegisterEntityLayersEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityRenderersEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityRenderers;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;

public final class VariantsAndVenturesClient
{
	public static void init() {
		RegisterEntityRenderersEvent.EVENT.addListener(VariantsAndVenturesEntityRenderers::init);
		RegisterEntityLayersEvent.EVENT.addListener(VariantsAndVenturesModelLayers::registerEntityLayers);
	}
}

