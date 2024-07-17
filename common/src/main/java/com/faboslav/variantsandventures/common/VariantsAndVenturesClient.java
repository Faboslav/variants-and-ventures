package com.faboslav.variantsandventures.common;

import com.faboslav.variantsandventures.common.block.SkullBlockType;
import com.faboslav.variantsandventures.common.client.render.entity.model.MurkSkullEntityModel;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityLayersEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityRenderersEvent;
import com.faboslav.variantsandventures.common.events.client.RegisterSkullModelEvent;
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
		RegisterSkullModelEvent.EVENT.addListener(VariantsAndVenturesClient::registerSkullModels);
	}

	public static void registerSkullModels(RegisterSkullModelEvent event) {
		event.register(SkullBlockType.GELID.get(), new SkullEntityModel(event.entityModelLoader().getModelPart(EntityModelLayers.ZOMBIE_HEAD)));
		event.register(SkullBlockType.MURK.get(), new MurkSkullEntityModel(event.entityModelLoader().getModelPart(VariantsAndVenturesModelLayers.MURK_SKULL)));
		event.register(SkullBlockType.THICKET.get(), new SkullEntityModel(event.entityModelLoader().getModelPart(EntityModelLayers.ZOMBIE_HEAD)));
		event.register(SkullBlockType.VERDANT.get(), new SkullEntityModel(event.entityModelLoader().getModelPart(EntityModelLayers.SKELETON_SKULL)));
	}
}

