package com.faboslav.variantsandventures.common.init.registry.fabric;

import com.faboslav.variantsandventures.common.init.registry.BasicRegistryEntry;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntries;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntry;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * Event/registry related is code based on The Bumblezone/Resourceful Lib mods with permissions from the authors
 *
 * @author TelepathicGrunt
 * <a href="https://github.com/TelepathicGrunt/Bumblezone">https://github.com/TelepathicGrunt/Bumblezone</a>
 * @author ThatGravyBoat
 * <a href="https://github.com/Team-Resourceful/ResourcefulLib">https://github.com/Team-Resourceful/ResourcefulLib</a>
 */
public final class FabricCustomResourcefulRegistry<T> implements ResourcefulRegistry<T>
{
	private final RegistryEntries<T> entries = new RegistryEntries<>();
	private final Registry<T> registry;
	private final String id;

	public FabricCustomResourcefulRegistry(Registry<T> registry, String id) {
		this.registry = registry;
		this.id = id;
	}

	@Override
	public <I extends T> RegistryEntry<I> register(String id, Supplier<I> supplier) {
		return entries.add(new BasicRegistryEntry<>(Identifier.of(this.id, id), Registry.register(registry, Identifier.of(this.id, id), supplier.get())));
	}

	@Override
	public Collection<RegistryEntry<T>> getEntries() {
		return this.entries.getEntries();
	}

	@Override
	public void init() {
	}
}
