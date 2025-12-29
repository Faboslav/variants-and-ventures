package com.faboslav.variantsandventures.common.tag;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public final class VariantsAndVenturesTags
{
	public static final TagKey<Biome> HAS_GELID = biomeTag("has_gelid");
	public static final TagKey<Biome> HAS_MURK = biomeTag("has_murk");
	public static final TagKey<Biome> HAS_HUSK = biomeTag("has_husk");
	public static final TagKey<Biome> HAS_STRAY = biomeTag("has_stray");
	//? if >= 1.20.6 {
	public static final TagKey<Biome> HAS_BOGGED = biomeTag("has_bogged");
	//?}
	public static final TagKey<Biome> HAS_THICKET = biomeTag("has_thicket");
	public static final TagKey<Biome> HAS_VERDANT = biomeTag("has_verdant");

	private static TagKey<Biome> biomeTag(String name) {
		return TagKey.create(Registries.BIOME, VariantsAndVentures.makeID(name));
	}

	public static void init() {
	}
}
