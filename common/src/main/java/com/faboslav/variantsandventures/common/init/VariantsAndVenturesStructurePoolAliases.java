package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.mixin.JigsawStructureAccessor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.random.SimpleWeightedRandomList.Builder;
import net.minecraft.util.random.WeightedEntry.Wrapper;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasBinding;
import net.minecraft.world.level.levelgen.structure.pools.alias.Random;
import net.minecraft.world.level.levelgen.structure.pools.alias.RandomGroup;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class VariantsAndVenturesStructurePoolAliases
{
	public static void init(MinecraftServer server) {
		updateTrialChamberSpawners(server);
	}

	private static void updateTrialChamberSpawners(MinecraftServer server) {
		Registry<Structure> structureRegistry = server.registryAccess().registryOrThrow(Registries.STRUCTURE);
		JigsawStructure structure = (JigsawStructure) structureRegistry.get(VariantsAndVentures.makeNamespacedId("minecraft:trial_chambers"));
		var structureAccessor = ((JigsawStructureAccessor) (Object) structure);

		List<PoolAliasBinding> originalPoolAliasBindings = structureAccessor.getPoolAliasBindings();
		List<PoolAliasBinding> newPoolAliasBindings = new ArrayList<>();

		for (PoolAliasBinding originalPoolAliasBinding : originalPoolAliasBindings) {
			if(originalPoolAliasBinding instanceof RandomGroup randomGroupStructurePoolAliasBinding) {
				RandomGroup newRandomGroupStructurePoolAliasBinding;
				var dataPoolBuilder = SimpleWeightedRandomList.<List<PoolAliasBinding>>builder();
				var groups = randomGroupStructurePoolAliasBinding.groups().unwrap();

				for(var group : groups) {
					dataPoolBuilder.add(group.data());
				}

				dataPoolBuilder.add(List.of(
						PoolAliasBinding.direct(
							"trial_chambers/spawner/contents/ranged",
							"trial_chambers/spawner/ranged/murk"
						),
						PoolAliasBinding.direct(
							"trial_chambers/spawner/contents/slow_ranged",
							"trial_chambers/spawner/slow_ranged/murk"
						)
					))
					.add(List.of(
						PoolAliasBinding.direct(
							"trial_chambers/spawner/contents/ranged",
							"trial_chambers/spawner/ranged/verdant"
						),
						PoolAliasBinding.direct(
							"trial_chambers/spawner/contents/slow_ranged",
							"trial_chambers/spawner/slow_ranged/verdant"
						)
					));


				newRandomGroupStructurePoolAliasBinding = PoolAliasBinding.randomGroup(
					dataPoolBuilder.build()
				);

				newPoolAliasBindings.add(newRandomGroupStructurePoolAliasBinding);
			} else if(originalPoolAliasBinding instanceof Random randomStructurePoolAliasBinding) {
				Random newRandomStructurePoolAliasBinding;
				var alias = randomStructurePoolAliasBinding.alias().location().getPath();
				var registryKeys = randomStructurePoolAliasBinding.allTargets().toList();

				if(Objects.equals(alias, "trial_chambers/spawner/contents/melee")) {
					var dataPoolBuilder = SimpleWeightedRandomList.<String>builder();

					registryKeys.forEach(registryKey -> {
						var value = registryKey.location().getPath();
						dataPoolBuilder.add(value);
					});

					dataPoolBuilder.add("trial_chambers/spawner/melee/gelid")
						.add("trial_chambers/spawner/melee/thicket");

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
