package com.faboslav.variantsandventures.forge.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.forge.worldgen.MobSpawnsBiomeModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VariantsAndVenturesBiomeModifiers
{
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, VariantsAndVentures.MOD_ID);

	public static final RegistryObject<Codec<MobSpawnsBiomeModifier>> BIOME_MODIFIER = BIOME_MODIFIER_SERIALIZERS.register("mob_spawns", () -> MobSpawnsBiomeModifier.CODEC);
}
