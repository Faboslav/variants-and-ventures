package com.faboslav.variantsandventures.common;

import com.faboslav.variantsandventures.common.block.SkullBlockType;
import com.faboslav.variantsandventures.common.client.render.entity.model.MurkSkullEntityModel;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityLayersEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityRenderersEvent;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityRenderers;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SkullEntityModel;

public final class VariantsAndVenturesClient
{
	@Environment(EnvType.CLIENT)
	public static void init() {
		RegisterEntityRenderersEvent.EVENT.addListener(VariantsAndVenturesEntityRenderers::init);
		RegisterEntityLayersEvent.EVENT.addListener(VariantsAndVenturesModelLayers::registerEntityLayers);
	}
}

