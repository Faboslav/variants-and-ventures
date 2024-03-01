package com.faboslav.variantsandventures.common.platform.fabric;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

import java.util.function.Supplier;

public final class SoundEventRegistryImpl
{
	public static <T extends SoundEvent> Supplier<T> register(String name, Supplier<T> soundEvent) {
		var registry = Registry.register(Registries.SOUND_EVENT, VariantsAndVentures.makeID(name), soundEvent.get());
		return () -> registry;
	}

	private SoundEventRegistryImpl() {
	}
}
