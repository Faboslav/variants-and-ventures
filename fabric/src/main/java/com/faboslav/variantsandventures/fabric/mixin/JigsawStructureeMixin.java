package com.faboslav.variantsandventures.fabric.mixin;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeCoords;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.structure.JigsawStructure;
import net.minecraft.world.gen.structure.Structure;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Optional;
import java.util.function.Predicate;

@Mixin(JigsawStructure.class)
public abstract class JigsawStructureeMixin extends Structure
{
	@Shadow
	@Final
	private int maxDistanceFromCenter;

	protected JigsawStructureeMixin(Config config) {
		super(config);
	}

	@Inject(
		method = "getStructurePosition",
		at = @At(value = "TAIL"),
		cancellable = true
	)
	private void villagesAndPillages$getStructurePosition(
		Structure.Context context,
		CallbackInfoReturnable<Optional<Structure.StructurePosition>> cir
	) {
		int checkRadius = this.maxDistanceFromCenter;
		int stepSize = 16;
		BlockPos.Mutable mutableBlockPos = new BlockPos.Mutable();
		Predicate<RegistryEntry<Biome>> blackListedBiomes = biomeEntry -> {
			return biomeEntry.matchesKey(BiomeKeys.RIVER) || biomeEntry.matchesKey(BiomeKeys.FROZEN_RIVER);
		};

		for (int xOffset = -checkRadius; xOffset <= checkRadius; xOffset += stepSize) {
			for (int zOffset = -checkRadius; zOffset <= checkRadius; zOffset += stepSize) {
				int x = xOffset + mutableBlockPos.getX();
				int y = mutableBlockPos.getY();
				int z = zOffset + mutableBlockPos.getZ();

				if (xOffset % checkRadius == 0 && zOffset % checkRadius == 0) {
					var structurePosition = new Structure.StructurePosition(new BlockPos(x, y, z), collector -> {
					});

					if (this.isBiomeValid(
						structurePosition,
						context.chunkGenerator(),
						context.noiseConfig(),
						blackListedBiomes
					) == false) {
						VariantsAndVentures.getLogger().info("Prevented on x: " + structurePosition.toString());
						cir.setReturnValue(Optional.empty());
					}
				}
			}
		}
	}

	private boolean isBiomeValid(
		Structure.StructurePosition result,
		ChunkGenerator chunkGenerator,
		NoiseConfig noiseConfig,
		Predicate<RegistryEntry<Biome>> validBiomes
	) {
		BlockPos blockPos = result.position();
		return validBiomes.test(chunkGenerator.getBiomeSource().getBiome(BiomeCoords.fromBlock(blockPos.getX()), BiomeCoords.fromBlock(blockPos.getY()), BiomeCoords.fromBlock(blockPos.getZ()), noiseConfig.getMultiNoiseSampler()));
	}
}
