package com.faboslav.variantsandventures.common.init.registry;

/**
 * Event/registry related is code based on The Bumblezone/Resourceful Lib mods with permissions from the authors
 *
 * @author TelepathicGrunt
 * <a href="https://github.com/TelepathicGrunt/Bumblezone">https://github.com/TelepathicGrunt/Bumblezone</a>
 * @author ThatGravyBoat
 * <a href="https://github.com/Team-Resourceful/ResourcefulLib">https://github.com/Team-Resourceful/ResourcefulLib</a>
 */
public interface ReferenceRegistryEntry<T> extends RegistryEntry<T>
{
	net.minecraft.core.Holder<T> referenceRegistryEntry();

	@Override
	default T get() {
		return referenceRegistryEntry().value();
	}
}