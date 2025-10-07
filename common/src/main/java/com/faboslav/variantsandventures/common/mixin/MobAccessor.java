package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;

//? if <=1.21.4 {
/*import net.minecraft.world.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.gen.Invoker;
*///?}

@Mixin(Mob.class)
public interface MobAccessor
{
	//? if <=1.21.4 {
	/*@Invoker("getEquipmentDropChance")
	float variantsandventures$getEquipmentDropChance(EquipmentSlot slot);
	*///?}
}
