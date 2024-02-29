package com.faboslav.variantsandventures.common.platform.forge;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import net.minecraft.sound.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public final class SoundEventRegistryImpl
{
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, VariantsAndVentures.MOD_ID);

	public static <T extends SoundEvent> Supplier<T> register(String name, Supplier<T> soundEvent) {
		return SOUND_EVENTS.register(name, soundEvent);
	}

	private SoundEventRegistryImpl() {
	}
}
