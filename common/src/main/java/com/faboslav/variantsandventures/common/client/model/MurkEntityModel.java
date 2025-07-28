package com.faboslav.variantsandventures.common.client.model;

/*? >=1.21.3 {*/
import com.faboslav.variantsandventures.common.client.render.entity.state.MurkEntityRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class MurkEntityModel extends SkeletonModel<MurkEntityRenderState>
{
	private final ModelPart corals;

	public MurkEntityModel(ModelPart root) {
		super(root);
		this.corals = this.getHead().getChild("corals");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition root = modelData.getRoot();
		SkeletonModel.createDefaultSkeletonMesh(root);

		PartDefinition head = root.getChild("head");
		PartDefinition corals = head.addOrReplaceChild("corals", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));
		corals.addOrReplaceChild("coral1", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, -15.0F, -4.0F, 9.0F, 9.0F, 0.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 8.0F, 0.0F));
		corals.addOrReplaceChild("coral2", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -6.0F, 5.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	public void setupAnim(MurkEntityRenderState murkRenderState) {
		this.corals.visible = !murkRenderState.sheared;
		super.setupAnim(murkRenderState);
	}
}
/*?} else {*/
/*import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class MurkEntityModel extends SkeletonModel<MurkEntity>
{
	private final ModelPart corals;

	public MurkEntityModel(ModelPart root) {
		super(root);
		this.corals = this.getHead().getChild("corals");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition root = modelData.getRoot();
		SkeletonModel.createDefaultSkeletonMesh(root);

		PartDefinition head = root.getChild("head");
		PartDefinition corals = head.addOrReplaceChild("corals", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));
		corals.addOrReplaceChild("coral1", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, -15.0F, -4.0F, 9.0F, 9.0F, 0.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 8.0F, 0.0F));
		corals.addOrReplaceChild("coral2", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -6.0F, 5.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	public void prepareMobModel(MurkEntity murk, float f, float g, float h) {
		this.corals.visible = !murk.isSheared();
		super.prepareMobModel(murk, f, g, h);
	}
}
*//*?}*/