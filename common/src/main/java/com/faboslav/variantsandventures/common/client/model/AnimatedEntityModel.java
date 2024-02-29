package com.faboslav.variantsandventures.common.client.model;

import net.minecraft.client.model.ModelPart;

import java.util.Optional;

public interface AnimatedEntityModel
{
	default Optional<ModelPart> variantsandventures$getChild(String name) {
		return this.variantsandventures$getPart().traverse().filter(part -> part.hasChild(name)).findFirst().map(part -> part.getChild(name));
	}

	ModelPart variantsandventures$getPart();
}
