package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.events.lifecycle.SetupEvent;
import com.faboslav.variantsandventures.common.mixin.SpawnEggItemAccessor;
import com.mojang.datafixers.util.Pair;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

//? if >=1.21.3 {
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
//?}

//? if < 1.21.1 {
/*import com.faboslav.variantsandventures.common.items.DispenserAddedSpawnEgg;
*///?}

/**
 * @see Items
 */
@SuppressWarnings({"deprecation"})
public final class VariantsAndVenturesItems
{
	public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, VariantsAndVentures.MOD_ID);
	private static final List<Pair<Supplier<? extends EntityType<? extends Mob>>, SpawnEggItem>> SPAWN_EGGS = new ArrayList<>();

	public final static RegistryEntry<Item> GELID_SPAWN_EGG = registerSpawnEgg("gelid_spawn_egg", VariantsAndVenturesEntityTypes.GELID, 0xFF108E9C, 0xFFDEF7F7);
	public final static RegistryEntry<Item> MURK_SPAWN_EGG = registerSpawnEgg("murk_spawn_egg", VariantsAndVenturesEntityTypes.MURK, 0xFFC1B1E1, 0xFFA276A9);
	public final static RegistryEntry<Item> THICKET_SPAWN_EGG = registerSpawnEgg("thicket_spawn_egg", VariantsAndVenturesEntityTypes.THICKET, 0xFF005B57, 0xFF2B4913);
	public final static RegistryEntry<Item> VERDANT_SPAWN_EGG = registerSpawnEgg("verdant_spawn_egg", VariantsAndVenturesEntityTypes.VERDANT, 0xFF8B8A75, 0xFF4A5D21);

	private static RegistryEntry<Item> registerSpawnEgg(
		String id,
		Supplier<? extends EntityType<? extends Mob>> typeIn,
		int primaryColorIn,
		int secondaryColorIn
	) {
		return ITEMS.register(id, () -> {
			//? if >= 1.21.9 {
			var spawnEgg = new SpawnEggItem(new Item.Properties().spawnEgg(typeIn.get()).stacksTo(64).setId(ResourceKey.create(Registries.ITEM, VariantsAndVentures.makeID(id))));
			//?} else if >=1.21.4 {
			/*var spawnEgg = new SpawnEggItem(typeIn.get(), new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, VariantsAndVentures.makeID(id))));
			*///?} else =1.21.3 {
			/*var spawnEgg = new SpawnEggItem(typeIn.get(), primaryColorIn, secondaryColorIn, new Item.Properties().stacksTo(64).setId(ResourceKey.create(Registries.ITEM, VariantsAndVentures.makeId(id))));
			/*///?} else >=1.21.1 {
			/*var spawnEgg = new SpawnEggItem(typeIn.get(), primaryColorIn, secondaryColorIn, new Item.Properties().stacksTo(64));
			*///?} else {
			/*var spawnEgg = new DispenserAddedSpawnEgg(typeIn, primaryColorIn, secondaryColorIn, new Item.Properties().stacksTo(64));
			*///?}

			SPAWN_EGGS.add(new Pair<>(typeIn, spawnEgg));

			return spawnEgg;
		});
	}

	public static void registerSpawnEggs(SetupEvent event) {
		var spawnEggMap = SpawnEggItemAccessor.variantsandventures$getSpawnEggs();

		for (var entry : VariantsAndVenturesItems.SPAWN_EGGS) {
			spawnEggMap.put(entry.getFirst().get(), entry.getSecond());
		}
	}

	private VariantsAndVenturesItems() {
	}
}
