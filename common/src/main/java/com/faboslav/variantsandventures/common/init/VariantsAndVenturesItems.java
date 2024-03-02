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
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;

/**
 * @see Items
 */
public final class VariantsAndVenturesItems
{
	public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(Registries.ITEM, VariantsAndVentures.MOD_ID);

	public final static RegistryEntry<Item> GELID_SPAWN_EGG = ITEMS.register("gelid_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityTypes.GELID, 0x108E9C, 0xDEF7F7, new Item.Settings().maxCount(64)));
	public final static RegistryEntry<Item> THICKET_SPAWN_EGG = ITEMS.register("thicket_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityTypes.THICKET, 0x005B57, 0x2B4913, new Item.Settings().maxCount(64)));
	public final static RegistryEntry<Item> VERDANT_SPAWN_EGG = ITEMS.register("verdant_spawn_egg", () -> new DispenserAddedSpawnEgg(VariantsAndVenturesEntityTypes.VERDANT, 0x8B8A75, 0x4A5D21, new Item.Settings().maxCount(64)));
	public final static RegistryEntry<Item> GELID_HEAD = ITEMS.register("gelid_head", () -> new VerticallyAttachableBlockItem(VariantsAndVenturesBlocks.GELID_HEAD.get(), VariantsAndVenturesBlocks.GELID_WALL_HEAD.get(), (new Item.Settings()).rarity(Rarity.UNCOMMON), Direction.DOWN));
	public final static RegistryEntry<Item> THICKET_HEAD = ITEMS.register("thicket_head", () -> new VerticallyAttachableBlockItem(VariantsAndVenturesBlocks.THICKET_HEAD.get(), VariantsAndVenturesBlocks.THICKET_WALL_HEAD.get(), (new Item.Settings()).rarity(Rarity.UNCOMMON), Direction.DOWN));
	public final static RegistryEntry<Item> VERDANT_SKULL = ITEMS.register("verdant_skull", () -> new VerticallyAttachableBlockItem(VariantsAndVenturesBlocks.VERDANT_SKULL.get(), VariantsAndVenturesBlocks.VERDANT_WALL_SKULL.get(), (new Item.Settings()).rarity(Rarity.UNCOMMON), Direction.DOWN));

	private VariantsAndVenturesItems() {
	}
}
