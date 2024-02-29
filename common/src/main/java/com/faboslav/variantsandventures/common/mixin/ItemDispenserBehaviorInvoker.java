package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ItemDispenserBehavior.class)
public interface ItemDispenserBehaviorInvoker
{
	@Invoker("dispense")
	ItemStack invokeDispense(BlockPointer pointer, ItemStack stack);
}

