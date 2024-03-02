package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityTypes;
import com.faboslav.variantsandventures.common.tag.VariantsAndVenturesTags;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DungeonFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DungeonFeature.class)
public class DungeonFeatureMixin
{
	@ModifyExpressionValue(
		method = "generate",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/DungeonFeature;getMobSpawnerEntity(Lnet/minecraft/util/math/random/Random;)Lnet/minecraft/entity/EntityType;")
	)
	private EntityType<?> variantsandventures$getMobSpawnerEntity(
		EntityType<?> original,
		@Local(ordinal = 0) LocalRef<BlockPos> blockPos,
		@Local(ordinal = 0) LocalRef<Random> random,
		@Local(ordinal = 0) LocalRef<StructureWorldAccess> structureWorldAccess
	) {
		RegistryEntry<Biome> biome = structureWorldAccess.get().getBiome(blockPos.get());

		if (original == EntityType.ZOMBIE) {
			if (
				biome.isIn(VariantsAndVenturesTags.HAS_HUSK)
				&& VariantsAndVentures.getConfig().enableHuskSpawners
				&& random.get().nextFloat() <= VariantsAndVentures.getConfig().huskSpawnerChance
			) {
				return EntityType.HUSK;
			}

			if (
				biome.isIn(VariantsAndVenturesTags.HAS_GALID)
				&& VariantsAndVentures.getConfig().enableGelid
				&& VariantsAndVentures.getConfig().enableGelidSpawners
				&& random.get().nextFloat() <= VariantsAndVentures.getConfig().gelidSpawnerChance
			) {
				VariantsAndVentures.getLogger().info(String.valueOf(blockPos.get()));
				return VariantsAndVenturesEntityTypes.GELID.get();
			}

			if (
				biome.isIn(VariantsAndVenturesTags.HAS_THICKET)
				&& VariantsAndVentures.getConfig().enableThicket
				&& VariantsAndVentures.getConfig().enableThicketSpawners
				&& random.get().nextFloat() <= VariantsAndVentures.getConfig().thicketSpawnerChance
			) {
				return VariantsAndVenturesEntityTypes.THICKET.get();
			}
		} else if (original == EntityType.SKELETON) {
			if (
				biome.isIn(VariantsAndVenturesTags.HAS_STRAY)
				&& VariantsAndVentures.getConfig().enableStraySpawners
				&& random.get().nextFloat() <= VariantsAndVentures.getConfig().straySpawnerChance
			) {
				return EntityType.STRAY;
			}

			if (
				biome.isIn(VariantsAndVenturesTags.HAS_VERDANT)
				&& VariantsAndVentures.getConfig().enableVerdant
				&& VariantsAndVentures.getConfig().enableVerdantSpawners
				&& random.get().nextFloat() <= VariantsAndVentures.getConfig().verdantSpawnerChance
			) {
				return VariantsAndVenturesEntityTypes.VERDANT.get();
			}
		}

		return original;
	}
}
