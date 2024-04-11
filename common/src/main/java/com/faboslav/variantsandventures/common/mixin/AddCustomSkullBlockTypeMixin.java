package com.faboslav.variantsandventures.common.mixin;

import com.faboslav.variantsandventures.common.block.SkullBlockType;
import net.minecraft.block.SkullBlock;
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

@Mixin(SkullBlock.Type.class)
@SuppressWarnings({"ShadowTarget", "InvokerTarget"})
public final class AddCustomSkullBlockTypeMixin
{
	@Invoker("<init>")
	public static SkullBlock.Type newSkullBlockType(
		String internalName,
		int internalId,
		String name
	) {
		throw new AssertionError();
	}

	@Shadow
	private static @Final
	@Mutable
	SkullBlock.Type[] field_11509;

	@Inject(
		method = "<clinit>",
		at = @At(
			value = "FIELD",
			opcode = Opcodes.PUTSTATIC,
			target = "Lnet/minecraft/block/SkullBlock$Type;field_11509:[Lnet/minecraft/block/SkullBlock$Type;",
			shift = At.Shift.AFTER
		)
	)
	private static void variantsandventures$addCustomSkullBlockType(CallbackInfo ci) {
		var skullBlockTypes = new ArrayList<>(Arrays.asList(field_11509));
		var lastSkullBlockTypeIndex = skullBlockTypes.get(skullBlockTypes.size() - 1);
		var nextSkullBlockTypeIndex = lastSkullBlockTypeIndex.ordinal();

		for (SkullBlockType skullBlockType : SkullBlockType.values()) {
			var newSkullBlockType = newSkullBlockType(
				skullBlockType.name(),
				++nextSkullBlockTypeIndex,
				skullBlockType.asString()
			);

			skullBlockTypes.add(newSkullBlockType);
		}

		field_11509 = skullBlockTypes.toArray(new SkullBlock.Type[0]);
	}
}