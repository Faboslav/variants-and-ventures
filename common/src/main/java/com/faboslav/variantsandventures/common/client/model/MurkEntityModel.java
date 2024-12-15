package com.faboslav.variantsandventures.common.client.model;

import com.faboslav.variantsandventures.common.client.render.entity.state.MurkEntityRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;

@Environment(EnvType.CLIENT)
public class MurkEntityModel extends SkeletonEntityModel<MurkEntityRenderState>
{
	private final ModelPart corals;

	public MurkEntityModel(ModelPart root) {
		super(root);
		this.corals = this.getHead().getChild("corals");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0.0F);
		ModelPartData root = modelData.getRoot();
		SkeletonEntityModel.addLimbs(root);

		ModelPartData head = root.getChild("head");
		ModelPartData corals = head.addChild("corals", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -8.0F, 0.0F));
		corals.addChild("coral1", ModelPartBuilder.create().uv(0, 36).cuboid(-1.0F, -15.0F, -4.0F, 9.0F, 9.0F, 0.0F, new Dilation(-0.01F)), ModelTransform.pivot(0.0F, 8.0F, 0.0F));
		corals.addChild("coral2", ModelPartBuilder.create().uv(0, 32).cuboid(-1.0F, -6.0F, 5.0F, 6.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(MurkEntityRenderState murkRenderState) {
		this.corals.visible = !murkRenderState.sheared;
		super.setAngles(murkRenderState);
	}
}
