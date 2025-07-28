package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.mixin.JigsawStructureAccessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasBinding;

//? >=1.21.5 {
import net.minecraft.world.level.levelgen.structure.pools.alias.RandomGroupPoolAlias;
import net.minecraft.world.level.levelgen.structure.pools.alias.RandomPoolAlias;
import net.minecraft.util.random.WeightedList;
//?} else {
/*import net.minecraft.world.level.levelgen.structure.pools.alias.Random;
import net.minecraft.world.level.levelgen.structure.pools.alias.RandomGroup;
import net.minecraft.util.random.SimpleWeightedRandomList;
*///?}

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class VariantsAndVenturesStructurePoolAliases
{
	public static void init(MinecraftServer server) {
		updateTrialChamberSpawners(server);
	}

	private static void updateTrialChamberSpawners(MinecraftServer server) {
		var config = VariantsAndVentures.getConfig();
		/*? >=1.21.3 {*/
		var structureRegistry = server.registryAccess().lookupOrThrow(Registries.STRUCTURE);
		var structure = (JigsawStructure) structureRegistry.getValue(VariantsAndVentures.makeNamespacedId("minecraft:trial_chambers"));
		/*?} else {*/
		/*var structureRegistry = server.registryAccess().registryOrThrow(Registries.STRUCTURE);
		var structure = (JigsawStructure) structureRegistry.get(VariantsAndVentures.makeNamespacedId("minecraft:trial_chambers"));
		*//*?}*/
		var structureAccessor = ((JigsawStructureAccessor) (Object) structure);

		List<PoolAliasBinding> originalPoolAliasBindings = structureAccessor.getPoolAliasBindings();
		List<PoolAliasBinding> newPoolAliasBindings = new ArrayList<>();

		for (PoolAliasBinding originalPoolAliasBinding : originalPoolAliasBindings) {
			//? >=1.21.5 {
			if (originalPoolAliasBinding instanceof RandomGroupPoolAlias randomGroupStructurePoolAliasBinding)
			//?} else {
			/*if (originalPoolAliasBinding instanceof RandomGroup randomGroupStructurePoolAliasBinding)
			*///?}
			{
				//? >=1.21.5 {
				RandomGroupPoolAlias newRandomGroupStructurePoolAliasBinding;
				var dataPoolBuilder = WeightedList.<List<PoolAliasBinding>>builder();
				//?} else {
				/*RandomGroup newRandomGroupStructurePoolAliasBinding;
				var dataPoolBuilder = SimpleWeightedRandomList.<List<PoolAliasBinding>>builder();
				*///?}
				var groups = randomGroupStructurePoolAliasBinding.groups().unwrap();

				for (var group : groups) {
					//? >=1.21.5 {
					dataPoolBuilder.add(group.value());
					//?} else {
					/*dataPoolBuilder.add(group.data());
					 *///?}
				}

				if (config.getModMobsConfig().enableMurkSpawnersInTrialChambers) {
					dataPoolBuilder.add(List.of(
						PoolAliasBinding.direct(
							"trial_chambers/spawner/contents/ranged",
							"trial_chambers/spawner/ranged/murk"
						),
						PoolAliasBinding.direct(
							"trial_chambers/spawner/contents/slow_ranged",
							"trial_chambers/spawner/slow_ranged/murk"
						)
					));
				}

				if (config.getModMobsConfig().enableVerdantSpawnersInTrialChambers) {
					dataPoolBuilder.add(List.of(
						PoolAliasBinding.direct(
							"trial_chambers/spawner/contents/ranged",
							"trial_chambers/spawner/ranged/verdant"
						),
						PoolAliasBinding.direct(
							"trial_chambers/spawner/contents/slow_ranged",
							"trial_chambers/spawner/slow_ranged/verdant"
						)
					));
				}

				newRandomGroupStructurePoolAliasBinding = PoolAliasBinding.randomGroup(
					dataPoolBuilder.build()
				);

				newPoolAliasBindings.add(newRandomGroupStructurePoolAliasBinding);
			}
			//? >=1.21.5 {
			else if (originalPoolAliasBinding instanceof RandomPoolAlias randomStructurePoolAliasBinding)
			//?} else {
			/*else if (originalPoolAliasBinding instanceof Random randomStructurePoolAliasBinding)
			*///?}
			{
				//? >=1.21.5 {
				RandomPoolAlias newRandomStructurePoolAliasBinding;
				//?} else {
				/*Random newRandomStructurePoolAliasBinding;
				*///?}
				var alias = randomStructurePoolAliasBinding.alias().location().getPath();
				var registryKeys = randomStructurePoolAliasBinding.allTargets().toList();

				if (Objects.equals(alias, "trial_chambers/spawner/contents/melee")) {
					//? >=1.21.5 {
					var dataPoolBuilder = WeightedList.<String>builder();
					//?} else {
					/*var dataPoolBuilder = SimpleWeightedRandomList.<String>builder();
					*///?}

					registryKeys.forEach(registryKey -> {
						var value = registryKey.location().getPath();
						dataPoolBuilder.add(value);
					});

					if(config.getModMobsConfig().enableGelidSpawnersInTrialChambers) {
						dataPoolBuilder.add("trial_chambers/spawner/melee/gelid");
					}

					if(config.getModMobsConfig().enableThicketSpawnersInTrialChambers) {
						dataPoolBuilder.add("trial_chambers/spawner/melee/thicket");
					}

					newRandomStructurePoolAliasBinding = PoolAliasBinding.random(
						alias, dataPoolBuilder.build()
					);
				} else {
					newRandomStructurePoolAliasBinding = randomStructurePoolAliasBinding;
				}

				newPoolAliasBindings.add(newRandomStructurePoolAliasBinding);
			} else {
				newPoolAliasBindings.add(originalPoolAliasBinding);
			}
		}

		structureAccessor.setPoolAliasBindings(newPoolAliasBindings);
	}

	private VariantsAndVenturesStructurePoolAliases() {
	}
}
