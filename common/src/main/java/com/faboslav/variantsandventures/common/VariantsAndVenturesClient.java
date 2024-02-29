package com.faboslav.variantsandventures.common;

import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityRenderers;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public final class VariantsAndVenturesClient
{
	@Environment(EnvType.CLIENT)
	public static void init() {
		VariantsAndVenturesModelLayers.init();
	}

	@Environment(EnvType.CLIENT)
	public static void postInit() {
		VariantsAndVenturesEntityRenderers.postInit();
	}

}

