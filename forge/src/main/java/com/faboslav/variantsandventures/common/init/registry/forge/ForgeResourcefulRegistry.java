package com.faboslav.variantsandventures.common.init.registry.forge;

import com.faboslav.variantsandventures.common.init.registry.RegistryEntries;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntry;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

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
public final class ForgeResourcefulRegistry<T> implements ResourcefulRegistry<T>
{
	private final DeferredRegister<T> register;
	private final RegistryEntries<T> entries = new RegistryEntries<>();

	public ForgeResourcefulRegistry(RegistryKey<? extends Registry<T>> registry, String id) {
		this.register = DeferredRegister.create(registry, id);
	}

	@Override
	public <I extends T> RegistryEntry<I> register(String id, Supplier<I> supplier) {
		return this.entries.add(new ForgeRegistryEntry<>(register.register(id, supplier)));
	}

	@Override
	public Collection<RegistryEntry<T>> getEntries() {
		return this.entries.getEntries();
	}

	@Override
	public void init() {
		register.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
