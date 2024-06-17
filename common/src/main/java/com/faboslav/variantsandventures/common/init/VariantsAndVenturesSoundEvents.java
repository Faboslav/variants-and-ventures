package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntry;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistries;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

/**
 * @see SoundEvents
 */
public final class VariantsAndVenturesSoundEvents
{
	public static final ResourcefulRegistry<SoundEvent> SOUND_EVENTS = ResourcefulRegistries.create(Registry.SOUND_EVENT, VariantsAndVentures.MOD_ID);

	public static final Supplier<SoundEvent> ENTITY_GELID_AMBIENT = registerSoundEvent("entity.gelid.ambient");
	public static final Supplier<SoundEvent> ENTITY_GELID_ATTACK = registerSoundEvent("entity.gelid.attack");
	public static final Supplier<SoundEvent> ENTITY_GELID_DEATH = registerSoundEvent("entity.gelid.death");
	public static final Supplier<SoundEvent> ENTITY_GELID_HURT = registerSoundEvent("entity.gelid.hurt");
	public static final Supplier<SoundEvent> ENTITY_GELID_STEP = registerSoundEvent("entity.gelid.step");
	public static final Supplier<SoundEvent> ENTITY_MURK_AMBIENT = registerSoundEvent("entity.murk.ambient");
	public static final Supplier<SoundEvent> ENTITY_MURK_AMBIENT_WATER = registerSoundEvent("entity.murk.ambient_water");
	public static final Supplier<SoundEvent> ENTITY_MURK_ATTACK = registerSoundEvent("entity.murk.attack");
	public static final Supplier<SoundEvent> ENTITY_MURK_DEATH = registerSoundEvent("entity.murk.death");
	public static final Supplier<SoundEvent> ENTITY_MURK_DEATH_WATER = registerSoundEvent("entity.murk.death_water");
	public static final Supplier<SoundEvent> ENTITY_MURK_SHEAR = registerSoundEvent("entity.murk.shear");
	public static final Supplier<SoundEvent> ENTITY_MURK_HURT = registerSoundEvent("entity.murk.hurt");
	public static final Supplier<SoundEvent> ENTITY_MURK_HURT_WATER = registerSoundEvent("entity.murk.hurt_water");
	public static final Supplier<SoundEvent> ENTITY_MURK_STEP = registerSoundEvent("entity.murk.step");
	public static final Supplier<SoundEvent> ENTITY_SNOWBALL_IMPACT = registerSoundEvent("entity.snowball.impact");
	public static final Supplier<SoundEvent> ENTITY_THICKET_AMBIENT = registerSoundEvent("entity.thicket.ambient");
	public static final Supplier<SoundEvent> ENTITY_THICKET_ATTACK = registerSoundEvent("entity.thicket.attack");
	public static final Supplier<SoundEvent> ENTITY_THICKET_DEATH = registerSoundEvent("entity.thicket.death");
	public static final Supplier<SoundEvent> ENTITY_THICKET_HURT = registerSoundEvent("entity.thicket.hurt");
	public static final Supplier<SoundEvent> ENTITY_THICKET_STEP = registerSoundEvent("entity.thicket.step");
	public static final Supplier<SoundEvent> ENTITY_VERDANT_AMBIENT = registerSoundEvent("entity.verdant.ambient");
	public static final Supplier<SoundEvent> ENTITY_VERDANT_ATTACK = registerSoundEvent("entity.verdant.attack");
	public static final Supplier<SoundEvent> ENTITY_VERDANT_DEATH = registerSoundEvent("entity.verdant.death");
	public static final Supplier<SoundEvent> ENTITY_VERDANT_HURT = registerSoundEvent("entity.verdant.hurt");
	public static final Supplier<SoundEvent> ENTITY_VERDANT_STEP = registerSoundEvent("entity.verdant.step");

	private static RegistryEntry<SoundEvent> registerSoundEvent(String path) {
		return SOUND_EVENTS.register(path, () -> new SoundEvent(VariantsAndVentures.makeID(path)));
	}

	private VariantsAndVenturesSoundEvents() {
	}
}
