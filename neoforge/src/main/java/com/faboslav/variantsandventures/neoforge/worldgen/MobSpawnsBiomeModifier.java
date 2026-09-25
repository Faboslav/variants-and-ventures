package com.faboslav.variantsandventures.neoforge.worldgen;

import com.faboslav.variantsandventures.common.events.lifecycle.AddSpawnBiomeModificationsEvent;
import com.faboslav.variantsandventures.neoforge.init.VariantsAndVenturesBiomeModifiers;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

//? if >=26.3 {
import net.minecraft.util.valueproviders.UniformInt;
//?}

public class MobSpawnsBiomeModifier implements BiomeModifier
{
	public static final MapCodec<MobSpawnsBiomeModifier> CODEC = MapCodec.unit(MobSpawnsBiomeModifier::new);

	public MobSpawnsBiomeModifier() {
	}

	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase == Phase.ADD) {
			AddSpawnBiomeModificationsEvent.EVENT.invoke(new AddSpawnBiomeModificationsEvent((tag, spawnGroup, entityType, spawnWeight, minGroupSize, maxGroupSize) -> {
				if (biome.is(tag)) {
					//? if >=26.3 {
					builder.getMobSpawnSettings().addSpawn(
						entityType,
						spawnGroup,
						spawnWeight,
						UniformInt.of(minGroupSize, maxGroupSize)
					);
					//?} else if >=1.21.5 {
					/*builder.getMobSpawnSettings().addSpawn(
						spawnGroup,
						spawnWeight,
						new MobSpawnSettings.SpawnerData(
							entityType,
							minGroupSize,
							maxGroupSize
						)
					);
					*///?} else {
					/*builder.getMobSpawnSettings().getSpawner(spawnGroup).add(
						new MobSpawnSettings.SpawnerData(
							entityType,
							spawnWeight,
							minGroupSize,
							maxGroupSize
						)
					);
					*///?}
				}
			}));
		}
	}

	@Override
	public MapCodec<? extends BiomeModifier> codec() {
		return VariantsAndVenturesBiomeModifiers.BIOME_MODIFIER.get();
	}
}
