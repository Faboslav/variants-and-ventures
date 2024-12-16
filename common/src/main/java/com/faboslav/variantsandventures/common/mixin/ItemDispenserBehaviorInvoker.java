package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DefaultDispenseItemBehavior.class)
public interface ItemDispenserBehaviorInvoker
{
	@Invoker("dispense")
	ItemStack invokeDispense(BlockSource pointer, ItemStack stack);
}

