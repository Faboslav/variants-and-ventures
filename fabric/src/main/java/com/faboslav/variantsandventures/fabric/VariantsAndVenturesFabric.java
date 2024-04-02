package com.faboslav.variantsandventures.fabric;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.lifecycle.AddSpawnBiomeModificationEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.RegisterEntityAttributesEvent;
import com.faboslav.variantsandventures.common.events.lifecycle.SetupEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.tag.BiomeTags;

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
		AddSpawnBiomeModificationEvent.EVENT.invoke(new AddSpawnBiomeModificationEvent((tag, spawnGroup, entityType, weight, minGroupSize, maxGroupSize) -> {
			BiomeModifications.addSpawn(biomeSelector -> biomeSelector.hasTag(tag) && biomeSelector.hasTag(BiomeTags.IS_OVERWORLD), spawnGroup, entityType, weight, minGroupSize, maxGroupSize);
		}));
		SetupEvent.EVENT.invoke(new SetupEvent(Runnable::run));
	}
}
