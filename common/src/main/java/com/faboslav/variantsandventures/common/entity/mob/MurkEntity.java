package com.faboslav.variantsandventures.common.entity.mob;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.ai.goal.LeaveWaterGoal;
import com.faboslav.variantsandventures.common.entity.ai.goal.TargetAboveWaterGoal;
import com.faboslav.variantsandventures.common.entity.ai.goal.WanderAroundOnSurfaceGoal;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesItems;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesSoundEvents;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.Predicate;

public final class MurkEntity extends AbstractSkeletonEntity implements Shearable
{
	private static final TrackedData<Integer> VARIANT;
	private static final TrackedData<Boolean> SHEARED;
	public static final String VARIANT_NBT_KEY = "Variant";
	public static final String SHEARED_NBT_KEY = "Sheared";

	private boolean targetingUnderwater;
	private final SwimNavigation waterNavigation;
	private final MobNavigation landNavigation;
	private final Predicate<LivingEntity> PLAYER_FILTER = (LivingEntity entity) -> {
		if (entity != null) {
			return !this.getWorld().isDay() || entity.isTouchingWater();
		} else {
			return false;
		}
	};

	public MurkEntity(EntityType<? extends AbstractSkeletonEntity> entityType, World world) {
		super(entityType, world);
		this.stepHeight = 1.0F;
		this.moveControl = new MurkMoveControl(this);
		this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
		this.waterNavigation = new SwimNavigation(this, world);
		this.landNavigation = new MobNavigation(this, world);
	}

	@Override
	public EntityData initialize(
		ServerWorldAccess world,
		LocalDifficulty difficulty,
		SpawnReason spawnReason,
		@Nullable EntityData entityData,
		@Nullable NbtCompound entityNbt
	) {
		this.setVariant(Variant.getRandom(random));

		return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
	}

	public static boolean canSpawn(
		EntityType<MurkEntity> type,
		ServerWorldAccess world,
		SpawnReason spawnReason,
		BlockPos pos,
		Random random
	) {
		return world.getFluidState(pos.down()).isIn(FluidTags.WATER)
			   && isValidSpawnDepth(world, pos)
			   && random.nextInt(40) == 0;
	}

	private static boolean isValidSpawnDepth(WorldAccess world, BlockPos pos) {
		return pos.getY() < world.getSeaLevel() - 5;
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new WanderAroundOnSurfaceGoal(this, 1.0));
		this.goalSelector.add(5, new LeaveWaterGoal(this, 1.0));
		this.goalSelector.add(6, new TargetAboveWaterGoal(this, 1.0, this.getWorld().getSeaLevel()));
		this.goalSelector.add(7, new WanderAroundGoal(this, 1.0));
		this.targetSelector.add(1, new RevengeGoal(this));
		this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, 10, true, false, PLAYER_FILTER));
		this.targetSelector.add(3, new ActiveTargetGoal(this, IronGolemEntity.class, true));
		this.targetSelector.add(3, new ActiveTargetGoal(this, AxolotlEntity.class, true, false));
		this.targetSelector.add(3, new ActiveTargetGoal(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));

		super.initGoals();
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(VARIANT, 0);
		this.dataTracker.startTracking(SHEARED, false);
	}

	@Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		nbt.putInt(VARIANT_NBT_KEY, this.getVariant().getId());
		nbt.putBoolean(SHEARED_NBT_KEY, this.isSheared());

	}

	@Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		this.setVariant(Variant.VARIANTS[nbt.getInt(VARIANT_NBT_KEY)]);
		this.setSheared(nbt.getBoolean(SHEARED_NBT_KEY));
	}

	@Override
	public boolean canSpawn(WorldView world) {
		return world.doesNotIntersectEntities(this);
	}

	public static DefaultAttributeContainer.Builder createMurkAttributes() {
		return AbstractSkeletonEntity.createAbstractSkeletonAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return this.isTouchingWater() ? VariantsAndVenturesSoundEvents.ENTITY_MURK_AMBIENT_WATER.get():VariantsAndVenturesSoundEvents.ENTITY_MURK_AMBIENT.get();
	}

	@Override
	public void playAmbientSound() {
		SoundEvent soundEvent = this.getAmbientSound();
		if (soundEvent != null) {
			this.playSound(soundEvent, this.isTouchingWater() ? 0.25F:this.getSoundVolume(), this.getSoundPitch());
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return this.isTouchingWater() ? VariantsAndVenturesSoundEvents.ENTITY_MURK_HURT_WATER.get():VariantsAndVenturesSoundEvents.ENTITY_MURK_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return this.isTouchingWater() ? VariantsAndVenturesSoundEvents.ENTITY_MURK_DEATH_WATER.get():VariantsAndVenturesSoundEvents.ENTITY_MURK_DEATH.get();
	}

	public SoundEvent getStepSound() {
		return this.isTouchingWater() ? VariantsAndVenturesSoundEvents.ENTITY_MURK_STEP.get():SoundEvents.ENTITY_SKELETON_STEP;
	}

	@Override
	public void tick() {
		if (VariantsAndVentures.getConfig().enableMurk == false) {
			this.discard();
		}

		super.tick();
	}

	@Override
	public void attack(LivingEntity target, float pullProgress) {
		ItemStack itemStack = this.getArrowType(this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW)));
		PersistentProjectileEntity persistentProjectileEntity = this.createArrowProjectile(itemStack, pullProgress);
		double d = target.getX() - this.getX();
		double e = target.getBodyY(0.3333333333333333) - persistentProjectileEntity.getY();
		double f = target.getZ() - this.getZ();
		double g = Math.sqrt(d * d + f * f);
		persistentProjectileEntity.setVelocity(d, e + g * 0.20000000298023224, f, 1.6F, (float) (14 - this.getWorld().getDifficulty().getId() * 4));
		this.playSound(VariantsAndVenturesSoundEvents.ENTITY_MURK_ATTACK.get(), 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
		this.getWorld().spawnEntity(persistentProjectileEntity);
	}

	@Override
	protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {
		super.dropEquipment(source, lootingMultiplier, allowDrops);
		Entity entity = source.getAttacker();
		if (entity instanceof CreeperEntity creeperEntity) {
			if (creeperEntity.shouldDropHead()) {
				creeperEntity.onHeadDropped();
				this.dropItem(VariantsAndVenturesItems.MURK_SKULL.get());
			}
		}
	}

	@Override
	public boolean canBreatheInWater() {
		return true;
	}

	@Override
	public boolean isPushedByFluids() {
		return !this.isSwimming();
	}

	@Override
	public boolean hasNoDrag() {
		return this.isSwimming();
	}

	private boolean isTargetingUnderwater() {
		if (this.targetingUnderwater) {
			return true;
		} else {
			LivingEntity livingEntity = this.getTarget();
			return livingEntity != null && livingEntity.isTouchingWater();
		}
	}

	public void setTargetingUnderwater(boolean targetingUnderwater) {
		this.targetingUnderwater = targetingUnderwater;
	}

	@Override
	public void travel(Vec3d movementInput) {
		if (this.canMoveVoluntarily() && this.isTouchingWater() && this.isTargetingUnderwater()) {
			this.updateVelocity(0.01F, movementInput);
			this.move(MovementType.SELF, this.getVelocity());
			this.setVelocity(this.getVelocity().multiply(0.9));
		} else {
			super.travel(movementInput);
		}
	}

	@Override
	public void updateSwimming() {
		if (!this.getWorld().isClient) {
			if (this.canMoveVoluntarily() && this.isTouchingWater() && this.isTargetingUnderwater()) {
				this.navigation = this.waterNavigation;
				this.setSwimming(true);
			} else {
				this.navigation = this.landNavigation;
				this.setSwimming(false);
			}
		}
	}

	public boolean hasFinishedCurrentPath() {
		Path path = this.getNavigation().getCurrentPath();
		if (path != null) {
			BlockPos blockPos = path.getTarget();
			if (blockPos != null) {
				double d = this.squaredDistanceTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
				return d < 4.0;
			}
		}

		return false;
	}

	public boolean isSheared() {
		return this.dataTracker.get(SHEARED);
	}

	public void setSheared(boolean sheared) {
		this.dataTracker.set(SHEARED, sheared);
	}

	@Override
	protected ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);

		if (itemStack.isOf(Items.SHEARS) && this.isShearable()) {
			this.sheared(SoundCategory.PLAYERS);
			this.emitGameEvent(GameEvent.SHEAR, player);

			if (this.getWorld().isClient() == false) {
				itemStack.damage(1, player, (p) -> p.sendToolBreakStatus(hand));
			}

			return ActionResult.success(this.getWorld().isClient());
		} else {
			return super.interactMob(player, hand);
		}
	}

	@Override
	public void sheared(SoundCategory shearedSoundCategory) {
		this.getWorld().playSoundFromEntity(null, this, VariantsAndVenturesSoundEvents.ENTITY_MURK_SHEAR.get(), shearedSoundCategory, 1.0F, 1.0F);
		this.dropShearedItems();
		this.setSheared(true);
	}

	private void dropShearedItems() {
		World world = this.getWorld();

		if (
			world instanceof ServerWorld == false
			|| world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT) == false
		) {
			return;
		}

		LootManager lootManager = world.getServer().getLootManager();

		if (lootManager == null) {
			return;
		}

		LootTable boggedShearingLootTable = lootManager.getTable(
			VariantsAndVentures.makeID(String.format(Locale.ROOT, "entities/murk_%s_shearing", this.getVariant().getName()))
		);
		LootContext lootContextParameterSet = new LootContext.Builder((ServerWorld) world)
			.parameter(LootContextParameters.ORIGIN, this.getPos())
			.parameter(LootContextParameters.THIS_ENTITY, this)
			.build(LootContextTypes.GIFT);
		ObjectArrayList<ItemStack> shearingDrops = boggedShearingLootTable.generateLoot(lootContextParameterSet);

		for (ItemStack shearingDrop : shearingDrops) {
			this.dropStack(shearingDrop);
		}
	}

	@Override
	public boolean isShearable() {
		return !this.isSheared() && this.isAlive();
	}

	private final class MurkMoveControl extends MoveControl
	{
		private final MurkEntity murk;

		public MurkMoveControl(MurkEntity murk) {
			super(murk);
			this.murk = murk;
		}

		public void tick() {
			LivingEntity livingEntity = this.murk.getTarget();
			if (this.murk.isTargetingUnderwater() && this.murk.isTouchingWater()) {
				if (livingEntity != null && livingEntity.getY() > this.murk.getY() || this.murk.targetingUnderwater) {
					this.murk.setVelocity(this.murk.getVelocity().add(0.0, 0.002, 0.0));
				}

				if (this.state != State.MOVE_TO || this.murk.getNavigation().isIdle()) {
					this.murk.setMovementSpeed(0.0F);
					return;
				}

				double d = this.targetX - this.murk.getX();
				double e = this.targetY - this.murk.getY();
				double f = this.targetZ - this.murk.getZ();
				double g = Math.sqrt(d * d + e * e + f * f);
				e /= g;
				float h = (float) (MathHelper.atan2(f, d) * 57.2957763671875) - 90.0F;
				this.murk.setYaw(this.wrapDegrees(this.murk.getYaw(), h, 90.0F));
				this.murk.bodyYaw = this.murk.getYaw();
				float i = (float) (this.speed * this.murk.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
				float j = MathHelper.lerp(0.125F, this.murk.getMovementSpeed(), i);
				this.murk.setMovementSpeed(j);
				this.murk.setVelocity(this.murk.getVelocity().add((double) j * d * 0.005, (double) j * e * 0.1, (double) j * f * 0.005));
			} else {
				if (!this.murk.isOnGround()) {
					this.murk.setVelocity(this.murk.getVelocity().add(0.0, -0.008, 0.0));
				}

				super.tick();
			}

		}
	}

	private void setNavigation(EntityNavigation navigation) {
		this.navigation = navigation;
	}

	public void setLandNavigation() {
		this.navigation = this.landNavigation;
	}

	public void setWaterNavigation() {
		this.navigation = this.waterNavigation;
	}

	public Variant getVariant() {
		return Variant.VARIANTS[this.dataTracker.get(VARIANT)];
	}

	private void setVariant(Variant variant) {
		this.dataTracker.set(VARIANT, variant.getId());
	}

	public enum Variant
	{
		PURPLE(0, "purple"),
		RED(1, "red"),
		YELLOW(2, "yellow");

		public static final MurkEntity.Variant[] VARIANTS = Arrays.stream(values()).sorted(Comparator.comparingInt(MurkEntity.Variant::getId)).toArray(Variant[]::new);
		private final int id;
		private final String name;

		Variant(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}

		private static Variant getRandom(Random random) {
			return Util.getRandom(Arrays.stream(VARIANTS).toArray(Variant[]::new), random);
		}
	}

	static {
		VARIANT = DataTracker.registerData(MurkEntity.class, TrackedDataHandlerRegistry.INTEGER);
		SHEARED = DataTracker.registerData(MurkEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	}
}