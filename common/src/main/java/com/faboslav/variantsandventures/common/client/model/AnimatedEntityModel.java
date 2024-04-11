package com.faboslav.variantsandventures.common.client.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;

import java.util.Optional;

@Environment(EnvType.CLIENT)
public interface AnimatedEntityModel
{
	default Optional<ModelPart> variantsandventures$getChild(String name) {
		return this.variantsandventures$getPart().traverse().filter(part -> part.hasChild(name)).findFirst().map(part -> part.getChild(name));
	}

	ModelPart variantsandventures$getPart();
}
