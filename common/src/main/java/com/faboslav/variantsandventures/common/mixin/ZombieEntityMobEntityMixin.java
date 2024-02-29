package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MobEntity.class)
public abstract class ZombieEntityMobEntityMixin extends ZombieEntityLivingEntityMixin
{
	@Shadow
	public abstract boolean spawnsTooManyForEachTry(int count);

	@Shadow
	protected LookControl lookControl;

	@Shadow
	protected MoveControl moveControl;
}
