package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.entity.pose.SkeletonEntityPose;
import com.faboslav.variantsandventures.common.entity.pose.ZombieEntityPose;
import net.minecraft.entity.EntityPose;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(EntityPose.class)
@SuppressWarnings({"ShadowTarget", "InvokerTarget"})
public final class AddCustomEntityPoseMixin
{
	@Invoker("<init>")
	private static EntityPose newEntityPose(
		String internalName,
		int internalId
	) {
		throw new AssertionError();
	}

	@Shadow
	private static @Final
	@Mutable
	EntityPose[] field_18083;

	@Inject(
		method = "<clinit>",
		at = @At(
			value = "FIELD",
			opcode = Opcodes.PUTSTATIC,
			target = "Lnet/minecraft/entity/EntityPose;field_18083:[Lnet/minecraft/entity/EntityPose;",
			shift = At.Shift.AFTER
		)
	)
	private static void variantsandventures$addCustomEntityPoses(CallbackInfo ci) {
		var entityPoses = new ArrayList<>(Arrays.asList(field_18083));
		var lastEntityPose = entityPoses.get(entityPoses.size() - 1);
		var nextEntityPoseIndex = lastEntityPose.ordinal();

		for (SkeletonEntityPose entityPose : SkeletonEntityPose.values()) {
			var newEntityPose = newEntityPose(
				entityPose.getName(),
				++nextEntityPoseIndex
			);

			entityPoses.add(newEntityPose);
		}

		for (ZombieEntityPose entityPose : ZombieEntityPose.values()) {
			var newEntityPose = newEntityPose(
				entityPose.getName(),
				++nextEntityPoseIndex
			);

			entityPoses.add(newEntityPose);
		}

		field_18083 = entityPoses.toArray(new EntityPose[0]);
	}
}