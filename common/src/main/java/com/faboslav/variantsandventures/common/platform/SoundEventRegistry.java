package com.faboslav.variantsandventures.common.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.sound.SoundEvent;

import java.util.function.Supplier;

public final class SoundEventRegistry
{
	@ExpectPlatform
	public static <T extends SoundEvent> Supplier<T> register(String name, Supplier<T> soundEvent) {
		throw new AssertionError();
	}

	private SoundEventRegistry() {
	}
}
