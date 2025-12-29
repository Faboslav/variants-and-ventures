package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.mixin.SpawnEggItemAccessor;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Supplier;

//? if >=1.21.3 {
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
//?}

/**
 * @see Items
 */
@SuppressWarnings({"deprecation"})
public final class VariantsAndVenturesItems
{
	public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, VariantsAndVentures.MOD_ID);

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
			/*///?} else <=1.21.1 {
			/*var spawnEgg = new SpawnEggItem(typeIn.get(), primaryColorIn, secondaryColorIn, new Item.Properties().stacksTo(64));
			*///?}
			var spawnEggMap = SpawnEggItemAccessor.variantsandventures$getSpawnEggs();
			spawnEggMap.put(typeIn.get(), spawnEgg);

			return spawnEgg;
		});
	}

	private VariantsAndVenturesItems() {
	}
}
