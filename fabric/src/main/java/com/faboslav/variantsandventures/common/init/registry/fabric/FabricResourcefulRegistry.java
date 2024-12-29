package com.faboslav.variantsandventures.common.init.registry.fabric;

import com.faboslav.variantsandventures.common.init.registry.ReferenceRegistryEntry;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntries;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntry;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

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
public class FabricResourcefulRegistry<T> implements ResourcefulRegistry<T>
{
	private final RegistryEntries<T> entries = new RegistryEntries<>();
	private final Registry<T> registry;
	private final String id;

	public FabricResourcefulRegistry(Registry<T> registry, String id) {
		this.registry = registry;
		this.id = id;
	}

	@Override
	public <I extends T> RegistryEntry<I> register(String id, Supplier<I> supplier) {
		return entries.add(FabricRegistryEntry.of(this.registry, ResourceLocation.fromNamespaceAndPath(this.id, id), supplier));
	}

	@Override
	public ReferenceRegistryEntry<T> registerReference(String id, Supplier<T> supplier) {
		return entries.add(FabricHolderRegistryEntry.of(this.registry, ResourceLocation.fromNamespaceAndPath(this.id, id), supplier));

	}

	@Override
	public Collection<RegistryEntry<T>> getEntries() {
		return this.entries.getEntries();
	}

	@Override
	public void init() {
	}
}