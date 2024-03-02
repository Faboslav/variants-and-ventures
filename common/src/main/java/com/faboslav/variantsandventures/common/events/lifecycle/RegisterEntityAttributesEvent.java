package com.faboslav.variantsandventures.common.events.lifecycle;

import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;

import java.util.function.BiConsumer;

public record RegisterEntityAttributesEvent(
	BiConsumer<EntityType<? extends LivingEntity>, DefaultAttributeContainer.Builder> attributes)
{

	public static final EventHandler<RegisterEntityAttributesEvent> EVENT = new EventHandler<>();

	public void register(EntityType<? extends LivingEntity> entityType, DefaultAttributeContainer.Builder builder) {
		attributes.accept(entityType, builder);
	}
}
