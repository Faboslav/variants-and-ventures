package com.faboslav.variantsandventures.common;

import com.faboslav.variantsandventures.common.config.VariantsAndVenturesConfig;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityLayersEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityRenderersEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityRenderers;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import net.minecraft.client.gui.screens.Screen;

public final class VariantsAndVenturesClient
{
	public static void init() {
		RegisterEntityRenderersEvent.EVENT.addListener(VariantsAndVenturesEntityRenderers::init);
		RegisterEntityLayersEvent.EVENT.addListener(VariantsAndVenturesModelLayers::registerEntityLayers);
	}

	public static Screen getConfigScreen(Screen parentScreen) {
		return VariantsAndVenturesConfig.HANDLER.generateGui().generateScreen(parentScreen);
	}
}

