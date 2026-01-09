//? if < 1.21.4 {
/*package com.faboslav.variantsandventures.common.events.item;

import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;

import java.util.function.BiConsumer;

public record RegisterItemColorEvent(BiConsumer<ItemColor, ItemLike[]> colors)
{
    public static final EventHandler<RegisterItemColorEvent> EVENT = new EventHandler<>();

    public void register(ItemColor color, ItemLike... items) {
        colors.accept(color, items);
    }
}
*///?}
