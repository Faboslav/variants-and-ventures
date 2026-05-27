package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.init.VariantsAndVenturesEntityTypes;
import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.sensing.VillagerHostilesSensor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(VillagerHostilesSensor.class)
public final class VillagerHostilesSensorMixin
{
	@Unique
	private static final ImmutableMap<EntityType<?>, Float> VARIANTSANDVENTURES_SQUARED_DISTANCES_FOR_DANGER = ImmutableMap.<EntityType<?>, Float>builder().put(VariantsAndVenturesEntityTypes.GELID.get(), 8.0F).put(VariantsAndVenturesEntityTypes.THICKET.get(), 8.0F).build();

	@WrapMethod(
		method = "isClose"
	)
	private boolean variantsandventures$isCloseEnoughForDanger(
		LivingEntity attacker,
		LivingEntity target,
		Operation<Boolean> original
	) {
		var entityType = target.getType();

		if (VARIANTSANDVENTURES_SQUARED_DISTANCES_FOR_DANGER.containsKey(entityType)) {
			var distance = VARIANTSANDVENTURES_SQUARED_DISTANCES_FOR_DANGER.get(entityType);

			if(distance == null) {
				return false;
			}

			return target.distanceToSqr(attacker) <= (double) (distance * distance);
		}

		return original.call(attacker, target);
	}

	@WrapMethod(
		method = "isHostile"
	)
	private boolean variantsandventures$isHostile(
		LivingEntity entity, Operation<Boolean> original
	) {
		if(original.call(entity)) {
			return true;
		}

		return VARIANTSANDVENTURES_SQUARED_DISTANCES_FOR_DANGER.containsKey(entity.getType());
	}
}
