package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.mixin.JigsawStructureAccessor;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.structure.pool.alias.RandomStructurePoolAliasBinding;
import net.minecraft.structure.pool.alias.StructurePoolAliasBinding;
import net.minecraft.structure.pool.alias.RandomGroupStructurePoolAliasBinding;
import net.minecraft.util.collection.DataPool;
import net.minecraft.world.gen.structure.JigsawStructure;
import net.minecraft.world.gen.structure.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class VariantsAndVenturesStructurePoolAliases
{
	public static void init(MinecraftServer server) {
		updateTrialChamberSpawners(server);
	}

	private static void updateTrialChamberSpawners(MinecraftServer server) {
		Registry<Structure> structureRegistry = server.getRegistryManager().get(RegistryKeys.STRUCTURE);
		JigsawStructure structure = (JigsawStructure) structureRegistry.get(VariantsAndVentures.makeNamespacedId("minecraft:trial_chambers"));
		var structureAccessor = ((JigsawStructureAccessor) (Object) structure);

		List<StructurePoolAliasBinding> originalPoolAliasBindings = structureAccessor.getPoolAliasBindings();
		List<StructurePoolAliasBinding> newPoolAliasBindings = new ArrayList<>();

		for (StructurePoolAliasBinding originalPoolAliasBinding : originalPoolAliasBindings) {
			if(originalPoolAliasBinding instanceof RandomGroupStructurePoolAliasBinding randomGroupStructurePoolAliasBinding) {
				RandomGroupStructurePoolAliasBinding newRandomGroupStructurePoolAliasBinding;
				var dataPoolBuilder = DataPool.<List<StructurePoolAliasBinding>>builder();
				var groups = randomGroupStructurePoolAliasBinding.groups().getEntries();

				for(var group : groups) {
					dataPoolBuilder.add(group.data());
				}

				dataPoolBuilder.add(List.of(
						StructurePoolAliasBinding.direct(
							"trial_chambers/spawner/contents/ranged",
							"trial_chambers/spawner/ranged/murk"
						),
						StructurePoolAliasBinding.direct(
							"trial_chambers/spawner/contents/slow_ranged",
							"trial_chambers/spawner/slow_ranged/murk"
						)
					))
					.add(List.of(
						StructurePoolAliasBinding.direct(
							"trial_chambers/spawner/contents/ranged",
							"trial_chambers/spawner/ranged/verdant"
						),
						StructurePoolAliasBinding.direct(
							"trial_chambers/spawner/contents/slow_ranged",
							"trial_chambers/spawner/slow_ranged/verdant"
						)
					));


				newRandomGroupStructurePoolAliasBinding = StructurePoolAliasBinding.randomGroup(
					dataPoolBuilder.build()
				);

				newPoolAliasBindings.add(newRandomGroupStructurePoolAliasBinding);
			} else if(originalPoolAliasBinding instanceof RandomStructurePoolAliasBinding randomStructurePoolAliasBinding) {
				RandomStructurePoolAliasBinding newRandomStructurePoolAliasBinding;
				var alias = randomStructurePoolAliasBinding.alias().getValue().getPath();
				var registryKeys = randomStructurePoolAliasBinding.streamTargets().toList();

				if(Objects.equals(alias, "trial_chambers/spawner/contents/melee")) {
					var dataPoolBuilder = DataPool.<String>builder();

					registryKeys.forEach(registryKey -> {
						var value = registryKey.getValue().getPath();
						dataPoolBuilder.add(value);
					});

					dataPoolBuilder.add("trial_chambers/spawner/melee/gelid")
						.add("trial_chambers/spawner/melee/thicket");

					newRandomStructurePoolAliasBinding = StructurePoolAliasBinding.random(
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
