package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.platform.SoundEventRegistry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.function.Supplier;

/**
 * @see SoundEvents
 */
public final class VariantsAndVenturesSoundEvents
{
	public static final Supplier<SoundEvent> ENTITY_GELID_AMBIENT;
	public static final Supplier<SoundEvent> ENTITY_GELID_ATTACK;
	public static final Supplier<SoundEvent> ENTITY_GELID_DEATH;
	public static final Supplier<SoundEvent> ENTITY_GELID_HURT;
	public static final Supplier<SoundEvent> ENTITY_GELID_STEP;
	public static final Supplier<SoundEvent> ENTITY_SNOWBALL_IMPACT;
	public static final Supplier<SoundEvent> ENTITY_THICKET_AMBIENT;
	public static final Supplier<SoundEvent> ENTITY_THICKET_ATTACK;
	public static final Supplier<SoundEvent> ENTITY_THICKET_DEATH;
	public static final Supplier<SoundEvent> ENTITY_THICKET_HURT;
	public static final Supplier<SoundEvent> ENTITY_THICKET_STEP;
	public static final Supplier<SoundEvent> ENTITY_VERDANT_AMBIENT;
	public static final Supplier<SoundEvent> ENTITY_VERDANT_ATTACK;
	public static final Supplier<SoundEvent> ENTITY_VERDANT_DEATH;
	public static final Supplier<SoundEvent> ENTITY_VERDANT_HURT;
	public static final Supplier<SoundEvent> ENTITY_VERDANT_STEP;

	static {
		ENTITY_GELID_AMBIENT = register("entity", "gelid.ambient");
		ENTITY_GELID_ATTACK = register("entity", "gelid.attack");
		ENTITY_GELID_DEATH = register("entity", "gelid.death");
		ENTITY_GELID_HURT = register("entity", "gelid.hurt");
		ENTITY_GELID_STEP = register("entity", "gelid.step");
		ENTITY_SNOWBALL_IMPACT = register("entity", "snowball.impact");
		ENTITY_THICKET_AMBIENT = register("entity", "thicket.ambient");
		ENTITY_THICKET_ATTACK = register("entity", "thicket.attack");
		ENTITY_THICKET_DEATH = register("entity", "thicket.death");
		ENTITY_THICKET_HURT = register("entity", "thicket.hurt");
		ENTITY_THICKET_STEP = register("entity", "thicket.step");
		ENTITY_VERDANT_AMBIENT = register("entity", "verdant.ambient");
		ENTITY_VERDANT_ATTACK = register("entity", "verdant.attack");
		ENTITY_VERDANT_DEATH = register("entity", "verdant.death");
		ENTITY_VERDANT_HURT = register("entity", "verdant.hurt");
		ENTITY_VERDANT_STEP = register("entity", "verdant.step");
	}

	private static Supplier<SoundEvent> register(String type, String name) {
		String id = type + "." + name;
		var soundEvent = SoundEvent.of(VariantsAndVentures.makeID(id));

		return SoundEventRegistry.register(id, () -> soundEvent);
	}

	public static void init() {
	}

	private VariantsAndVenturesSoundEvents() {
	}
}
