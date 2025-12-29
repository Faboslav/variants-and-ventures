package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

//? if >= 1.21.5 {
import net.minecraft.world.entity.ConversionParams;
//?}

@Mixin(Mob.class)
public abstract class ZombieMobMixin extends ZombieLivingEntityMixin
{
	//? if >= 1.21.5 {
	@Shadow
	@Nullable
	public abstract <T extends Mob> T convertTo(
		EntityType<T> entityType,
		ConversionParams conversionParams,
		ConversionParams.AfterConversion<T> afterConversion
	);
	//?} else {
	/*@Shadow
	@Nullable
	public abstract <T extends Mob> T convertTo(EntityType<T> entityType, boolean transferInventory);
	*///?}

	@Shadow
	public abstract boolean isNoAi();

	public ZombieMobMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}
}
