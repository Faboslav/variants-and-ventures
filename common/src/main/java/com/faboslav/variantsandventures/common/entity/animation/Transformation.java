package com.faboslav.variantsandventures.common.entity.animation;

import net.minecraft.util.math.MathHelper;
import org.joml.Vector3f;

public record Transformation(
	Transformation.Type type,
	Keyframe... keyframes
)
{
	public Transformation(
		Transformation.Type type,
		Keyframe... keyframes
	) {
		this.type = type;
		this.keyframes = keyframes;
	}

	public Transformation.Type type() {
		return this.type;
	}

	public Keyframe[] keyframes() {
		return this.keyframes;
	}

	public enum Type
	{
		TRANSLATE,
		ROTATE,
		SCALE
	}

	public static class Interpolations
	{
		public static final Transformation.Interpolation LINEAR = (vec3f, delta, keyframes, start, end, f) -> {
			Vector3f vec3f2 = keyframes[start].target();
			Vector3f vec3f3 = keyframes[end].target();
			vec3f.set(MathHelper.lerp(delta, vec3f2.x(), vec3f3.x()) * f, MathHelper.lerp(delta, vec3f2.y(), vec3f3.y()) * f, MathHelper.lerp(delta, vec3f2.z(), vec3f3.z()) * f);
			return vec3f;
		};
		public static final Transformation.Interpolation CUBIC = (vec3f, delta, keyframes, start, end, f) -> {
			Vector3f vec3f2 = keyframes[Math.max(0, start - 1)].target();
			Vector3f vec3f3 = keyframes[start].target();
			Vector3f vec3f4 = keyframes[end].target();
			Vector3f vec3f5 = keyframes[Math.min(keyframes.length - 1, end + 1)].target();
			vec3f.set(catmullRom(delta, vec3f2.x(), vec3f3.x(), vec3f4.x(), vec3f5.x()) * f, catmullRom(delta, vec3f2.y(), vec3f3.y(), vec3f4.y(), vec3f5.y()) * f, catmullRom(delta, vec3f2.z(), vec3f3.z(), vec3f4.z(), vec3f5.z()) * f);
			return vec3f;
		};

		public static float catmullRom(float delta, float p0, float p1, float p2, float p3) {
			return 0.5F * (2.0F * p1 + (p2 - p0) * delta + (2.0F * p0 - 5.0F * p1 + 4.0F * p2 - p3) * delta * delta + (3.0F * p1 - p0 - 3.0F * p2 + p3) * delta * delta * delta);
		}

		public Interpolations() {
		}
	}

	public interface Interpolation
	{
		Vector3f apply(Vector3f vec3f, float delta, Keyframe[] keyframes, int start, int end, float f);
	}
}
