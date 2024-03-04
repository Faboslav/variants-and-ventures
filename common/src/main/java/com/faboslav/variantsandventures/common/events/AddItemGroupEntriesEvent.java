package com.faboslav.variantsandventures.common.events;

import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.function.Consumer;

/**
 * Event related is code based on The Bumblezone/Resourceful Lib mods with permissions from the authors
 *
 * @author TelepathicGrunt
 * <a href="https://github.com/TelepathicGrunt/Bumblezone">https://github.com/TelepathicGrunt/Bumblezone</a>
 * @author ThatGravyBoat
 * <a href="https://github.com/Team-Resourceful/ResourcefulLib">https://github.com/Team-Resourceful/ResourcefulLib</a>
 */
public record AddItemGroupEntriesEvent(ItemGroup itemGroup, boolean hasPermission, Consumer<ItemStack> adder) {

    public static final EventHandler<AddItemGroupEntriesEvent> EVENT = new EventHandler<>();

    public void add(ItemStack stack) {
        adder.accept(stack);
    }

    public void add(ItemConvertible item) {
        adder.accept(new ItemStack(item));
    }
}
