package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityTypes;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.MonsterRoomFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MonsterRoomFeature.class)
public abstract class DungeonFeatureMixin
{
	@ModifyExpressionValue(
		method = "place",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/MonsterRoomFeature;randomEntityId(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/world/entity/EntityType;")
	)
	private EntityType<?> variantsandventures$getMobSpawnerEntity(
		EntityType<?> original,
		@Local(ordinal = 0) LocalRef<BlockPos> blockPos,
		@Local(ordinal = 0) LocalRef<RandomSource> random,
		@Local(ordinal = 0) LocalRef<WorldGenLevel> structureWorldAccess
	) {
		Holder<Biome> biome = structureWorldAccess.get().getBiome(blockPos.get());

		if (original == EntityType.ZOMBIE) {
			if (
				biome.is(VariantsAndVenturesTags.HAS_HUSK)
				&& VariantsAndVentures.getConfig().enableHuskSpawners
				&& random.get().nextInt(100) <= VariantsAndVentures.getConfig().huskSpawnerChance
			) {
				return EntityType.HUSK;
			}

			if (
				biome.is(VariantsAndVenturesTags.HAS_GELID)
				&& VariantsAndVentures.getConfig().enableGelid
				&& VariantsAndVentures.getConfig().enableGelidSpawners
				&& random.get().nextInt(100) <= VariantsAndVentures.getConfig().gelidSpawnerChance
			) {
				return VariantsAndVenturesEntityTypes.GELID.get();
			}

			if (
				biome.is(VariantsAndVenturesTags.HAS_THICKET)
				&& VariantsAndVentures.getConfig().enableThicket
				&& VariantsAndVentures.getConfig().enableThicketSpawners
				&& random.get().nextInt(100) <= VariantsAndVentures.getConfig().thicketSpawnerChance
			) {
				return VariantsAndVenturesEntityTypes.THICKET.get();
			}
		} else if (original == EntityType.SKELETON) {
			if (
				biome.is(VariantsAndVenturesTags.HAS_STRAY)
				&& VariantsAndVentures.getConfig().enableStraySpawners
				&& random.get().nextInt(100) <= VariantsAndVentures.getConfig().straySpawnerChance
			) {
				return EntityType.STRAY;
			}

			//? if >= 1.20.6 {
			if (
				biome.is(VariantsAndVenturesTags.HAS_BOGGED)
				&& VariantsAndVentures.getConfig().enableBoggedSpawners
				&& random.get().nextInt(100) <= VariantsAndVentures.getConfig().boggedSpawnerChance
			) {
				return EntityType.BOGGED;
			}
			//?}

			//? if >= 1.21.11 {
			if (
				biome.is(VariantsAndVenturesTags.HAS_PARCHED)
				&& VariantsAndVentures.getConfig().enableParchedSpawners
				&& random.get().nextInt(100) <= VariantsAndVentures.getConfig().parchedSpawnerChance
			) {
				return EntityType.PARCHED;
			}
			//?}

			if (
				biome.is(VariantsAndVenturesTags.HAS_VERDANT)
				&& VariantsAndVentures.getConfig().enableVerdant
				&& VariantsAndVentures.getConfig().enableVerdantSpawners
				&& random.get().nextInt(100) <= VariantsAndVentures.getConfig().verdantSpawnerChance
			) {
				return VariantsAndVenturesEntityTypes.VERDANT.get();
			}
		}

		return original;
	}
}
