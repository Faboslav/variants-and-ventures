package com.faboslav.variantsandventures.common.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;

import java.util.function.Supplier;

public final class EntityModelLayerRegistry
{
	@ExpectPlatform
	public static void register(EntityModelLayer location, Supplier<TexturedModelData> definition) {
		throw new AssertionError();
	}

	private EntityModelLayerRegistry() {
	}
}
