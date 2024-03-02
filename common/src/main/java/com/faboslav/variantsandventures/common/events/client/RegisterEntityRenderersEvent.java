package com.faboslav.variantsandventures.common.events.client;

import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public record RegisterEntityRenderersEvent(Registrar registrar)
{

	public static final EventHandler<RegisterEntityRenderersEvent> EVENT = new EventHandler<>();

	public <T extends Entity> void register(EntityType<T> type, EntityRendererFactory<T> factory) {
		registrar.register(type, factory);
	}

	@FunctionalInterface
	public interface Registrar
	{
		<T extends Entity> void register(EntityType<? extends T> type, EntityRendererFactory<T> factory);
	}
}
