package com.faboslav.variantsandventures.common.tag;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public final class VariantsAndVenturesTags
{
	public static final TagKey<Biome> HAS_GALID = biomeTag("has_gelid");
	public static final TagKey<Biome> HAS_HUSK = biomeTag("has_husk");
	public static final TagKey<Biome> HAS_STRAY = biomeTag("has_stray");
	public static final TagKey<Biome> HAS_THICKET = biomeTag("has_thicket");
	public static final TagKey<Biome> HAS_VERDANT = biomeTag("has_verdant");

	private static TagKey<Biome> biomeTag(String name) {
		return TagKey.of(Registry.BIOME_KEY, VariantsAndVentures.makeID(name));
	}

	public static void init() {
	}
}
