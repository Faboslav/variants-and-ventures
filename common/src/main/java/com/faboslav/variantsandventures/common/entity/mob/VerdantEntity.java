package com.faboslav.variantsandventures.common.entity.mob;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesSoundEvents;
import com.faboslav.variantsandventures.common.util.AdvancementHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

//? if >=1.21 {
import org.jetbrains.annotations.Nullable;
//?}

//? if >=1.21.3 {
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.VisibleForTesting;
//?}

public final class VerdantEntity extends Skeleton
{
	public VerdantEntity(EntityType<? extends Skeleton> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_VERDANT_AMBIENT.get();
	}

	@Override
	public void playAmbientSound() {
		SoundEvent soundEvent = this.getAmbientSound();
		if (soundEvent != null) {
			this.playSound(soundEvent, 0.25F, this.getVoicePitch());
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return VariantsAndVenturesSoundEvents.ENTITY_VERDANT_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VariantsAndVenturesSoundEvents.ENTITY_VERDANT_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos blockPos, BlockState blockState) {
		this.playSound(VariantsAndVenturesSoundEvents.ENTITY_VERDANT_STEP.get(), 0.15F, 1.0F);
	}

	@Override
	public void tick() {
		if (!VariantsAndVentures.getConfig().enableVerdant) {
			this.discard();
		}

		super.tick();
	}

	@Override
	public void performRangedAttack(LivingEntity target, float velocity) {
		ItemStack possibleBow = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, Items.BOW));

		if(!possibleBow.is(Items.BOW)) {
			return;
		}

		ItemStack possibleProjectile = this.getProjectile(possibleBow);

		if(possibleProjectile == ItemStack.EMPTY) {
			return;
		}

		AbstractArrow abstractArrow = this.getArrow(
			possibleProjectile,
			velocity
			//? if >= 1.21 {
			, possibleBow
			//?}
		);
		double d = target.getX() - this.getX();
		double e = target.getY(0.3333333333333333) - abstractArrow.getY();
		double f = target.getZ() - this.getZ();
		double g = Math.sqrt(d * d + f * f);

		//? if >=1.21.3 {
		Level var15 = this.level();
		if (var15 instanceof ServerLevel serverLevel) {
			Projectile.spawnProjectileUsingShoot(abstractArrow, serverLevel, possibleProjectile, d, e + g * 0.20000000298023224, f, 1.6F, (float)(14 - serverLevel.getDifficulty().getId() * 4));
		}
		//?} else {
		/*abstractArrow.shoot(d, e + g * 0.20000000298023224, f, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
		this.level().addFreshEntity(abstractArrow);
		*///?}

		this.playSound(VariantsAndVenturesSoundEvents.ENTITY_VERDANT_ATTACK.get(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
	}

	@Override
	protected AbstractArrow getArrow(
		ItemStack arrow,
		float damageModifier
		//? if >= 1.21 {
		, @Nullable ItemStack shotFrom
		//?}
	) {
		AbstractArrow persistentProjectileEntity = super.getArrow(
			arrow,
			damageModifier
			//? if >= 1.21 {
			, shotFrom
			//?}
		);

		if (persistentProjectileEntity instanceof Arrow) {
			((Arrow) persistentProjectileEntity).addEffect(new MobEffectInstance(MobEffects.POISON, 100));
		}

		return persistentProjectileEntity;
	}

	@Override
	public boolean isFreezeConverting() {
		return false;
	}

	@Override
	public boolean isShaking() {
		return false;
	}

	@Override
	protected void doFreezeConversion() {
	}

	@Override
	public boolean canFreeze() {
		return false;
	}

	@Override
	public void die(DamageSource damageSource) {
		super.die(damageSource);
		AdvancementHelper.triggerMonsterHunter(this.level(), damageSource);
	}
}