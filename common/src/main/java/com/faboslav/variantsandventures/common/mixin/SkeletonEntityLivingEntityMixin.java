package com.faboslav.variantsandventures.common.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
public abstract class SkeletonEntityLivingEntityMixin extends SkeletonEntityEntityMixin
{
}
