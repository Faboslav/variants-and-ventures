package com.faboslav.variantsandventures.common.events.client;

import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Event related is code based on The Bumblezone/Resourceful Lib mods with permissions from the authors
 *
 * @author TelepathicGrunt
 * <a href="https://github.com/TelepathicGrunt/Bumblezone">https://github.com/TelepathicGrunt/Bumblezone</a>
 * @author ThatGravyBoat
 * <a href="https://github.com/Team-Resourceful/ResourcefulLib">https://github.com/Team-Resourceful/ResourcefulLib</a>
 */
public record RegisterEntityLayersEvent(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> registrar)
{
	public static final EventHandler<RegisterEntityLayersEvent> EVENT = new EventHandler<>();

	public void register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
		registrar.accept(location, definition);
	}
}
