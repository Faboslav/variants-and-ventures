package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.init.registry.RegistryEntry;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistries;
import com.faboslav.variantsandventures.common.init.registry.ResourcefulRegistry;
import com.faboslav.variantsandventures.common.items.DispenserAddedSpawnEgg;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;

/**
 * @see Items
 */
public final class VariantsAndVenturesItems
{
	public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(Registries.ITEM, VariantsAndVentures.MOD_ID);

	public final static RegistryEntry<Item> GELID_SPAWN_EGG = ITEMS.register("gelid_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityTypes.GELID, 0xFF108E9C, 0xFFDEF7F7, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, VariantsAndVentures.makeID("gelid_spawn_egg")))));
	public final static RegistryEntry<Item> MURK_SPAWN_EGG = ITEMS.register("murk_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityTypes.MURK, 0xFFC1B1E1, 0xFFA276A9, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, VariantsAndVentures.makeID("murk_spawn_egg")))));
	public final static RegistryEntry<Item> THICKET_SPAWN_EGG = ITEMS.register("thicket_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityTypes.THICKET, 0xFF005B57, 0xFF2B4913, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, VariantsAndVentures.makeID("thicket_spawn_egg")))));
	public final static RegistryEntry<Item> VERDANT_SPAWN_EGG = ITEMS.register("verdant_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityTypes.VERDANT, 0xFF8B8A75, 0xFF4A5D21, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, VariantsAndVentures.makeID("verdant_spawn_egg")))));

	private VariantsAndVenturesItems() {
	}
}
