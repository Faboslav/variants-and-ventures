package com.faboslav.variantsandventures.common.events.client;

import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public record RegisterEntityLayersEvent(BiConsumer<EntityModelLayer, Supplier<TexturedModelData>> registrar)
{

	public static final EventHandler<RegisterEntityLayersEvent> EVENT = new EventHandler<>();

	public void register(EntityModelLayer location, Supplier<TexturedModelData> definition) {
		registrar.accept(location, definition);
	}
}
