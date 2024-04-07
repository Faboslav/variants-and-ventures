package com.faboslav.variantsandventures.fabric;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.AddItemGroupEntriesEvent;
import com.faboslav.variantsandventures.common.events.RegisterItemGroupsEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.AddSpawnBiomeModificationsEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntityAttributesEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntitySpawnRestrictionsEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.SetupEvent;
import com.google.common.collect.Lists;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BiomeTags;

import java.util.List;

public final class VariantsAndVenturesFabric implements ModInitializer
{
	@Override
	public void onInitialize() {
		VariantsAndVentures.init();

		initEvents();

		VariantsAndVentures.lateInit();
	}

	private void initEvents() {
		RegisterEntityAttributesEvent.EVENT.invoke(new RegisterEntityAttributesEvent(FabricDefaultAttributeRegistry::register));
		RegisterEntitySpawnRestrictionsEvent.EVENT.invoke(new RegisterEntitySpawnRestrictionsEvent(VariantsAndVenturesFabric::registerPlacement));
		AddSpawnBiomeModificationsEvent.EVENT.invoke(new AddSpawnBiomeModificationsEvent((tag, spawnGroup, entityType, weight, minGroupSize, maxGroupSize) -> {
			BiomeModifications.addSpawn(biomeSelector -> biomeSelector.hasTag(tag) && biomeSelector.hasTag(BiomeTags.IS_OVERWORLD), spawnGroup, entityType, weight, minGroupSize, maxGroupSize);
		}));
		SetupEvent.EVENT.invoke(new SetupEvent(Runnable::run));

		RegisterItemGroupsEvent.EVENT.invoke(new RegisterItemGroupsEvent((id, initializer, initialDisplayItems) -> {
			ItemGroup.Builder builder = FabricItemGroup.builder(id);
			initializer.accept(builder);
			builder.entries((flags, output) -> {
				List<ItemStack> stacks = Lists.newArrayList();
				initialDisplayItems.accept(stacks);
				output.addAll(stacks);
			});
			builder.build();
		}));

		ItemGroupEvents.MODIFY_ENTRIES_ALL.register((itemGroup, entries) ->
			AddItemGroupEntriesEvent.EVENT.invoke(
				new AddItemGroupEntriesEvent(
					AddItemGroupEntriesEvent.Type.toType(itemGroup),
					itemGroup,
					itemGroup.hasStacks(),
					entries::add
				)
			)
		);
	}

	private static <T extends MobEntity> void registerPlacement(
		EntityType<T> type,
		RegisterEntitySpawnRestrictionsEvent.Placement<T> placement
	) {
		SpawnRestriction.register(type, placement.location(), placement.heightmap(), placement.predicate());
	}
}
