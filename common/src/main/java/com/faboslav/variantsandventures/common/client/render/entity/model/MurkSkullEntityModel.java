package com.faboslav.variantsandventures.common.client.render.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SkullEntityModel;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public final class MurkSkullEntityModel extends SkullEntityModel
{
	private final ModelPart root;
	private final ModelPart head;

	public MurkSkullEntityModel(ModelPart root) {
		super(root);
		this.root = root;
		this.head = root.getChild("head");
	}

	public static ModelData getModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), ModelTransform.NONE);
		ModelPartData head = modelPartData.getChild("head");
		ModelPartData corals = head.addChild("corals", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -8.0F, 0.0F));
		corals.addChild("coral1", ModelPartBuilder.create().uv(0, 36).cuboid(-1.0F, -15.0F, -4.0F, 9.0F, 9.0F, 0.0F, new Dilation(-0.01F)), ModelTransform.pivot(0.0F, 8.0F, 0.0F));
		corals.addChild("coral2", ModelPartBuilder.create().uv(0, 32).cuboid(-1.0F, -6.0F, 5.0F, 6.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		return modelData;
	}

	public static TexturedModelData getHeadTexturedModelData() {
		ModelData modelData = getModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.getChild("head").addChild("hat", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.25F)), ModelTransform.NONE);
		return TexturedModelData.of(modelData, 64, 64);
	}

	public static TexturedModelData getSkullTexturedModelData() {
		ModelData modelData = getModelData();
		return TexturedModelData.of(modelData, 64, 64);
	}

	public void setHeadRotation(float animationProgress, float yaw, float pitch) {
		this.head.yaw = yaw * 0.017453292F;
		this.head.pitch = pitch * 0.017453292F;
	}
}
