package com.faboslav.variantsandventures.common.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
//? if >= 1.21.9 {
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.model.geom.LayerDefinitions;
//?}

//? if >=1.21.3 {
import com.faboslav.variantsandventures.common.client.render.entity.state.MurkEntityRenderState;
//?} else {
/*import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
*///?}

//? if >= 1.21.3 {
public class MurkEntityModel extends SkeletonModel<MurkEntityRenderState>
//?} else {
/*public class MurkEntityModel extends SkeletonModel<MurkEntity>
*///?}
{
	private final ModelPart corals;

	public MurkEntityModel(ModelPart root) {
		super(root);
		this.corals = root.getChild("head").getChild("corals");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition modelData = createDefaultMesh(CubeDeformation.NONE);
		return LayerDefinition.create(modelData, 64, 64);
	}

	private static MeshDefinition createDefaultMesh(CubeDeformation cubeDeformation) {
		MeshDefinition modelData = HumanoidModel.createMesh(cubeDeformation, 0.0F);
		PartDefinition root = modelData.getRoot();
		//? >= 1.21.3 {
		SkeletonModel.createDefaultSkeletonMesh(root);
		 //?} else {
		/*root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
		root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(5.0F, 2.0F, 0.0F));
		root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
		root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(2.0F, 12.0F, 0.0F));
		*///?}

		PartDefinition head = root.getChild("head");
		PartDefinition corals = head.addOrReplaceChild("corals", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));
		corals.addOrReplaceChild("coral1", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, -15.0F, -4.0F, 9.0F, 9.0F, 0.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 8.0F, 0.0F));
		corals.addOrReplaceChild("coral2", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -6.0F, 5.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		return modelData;
	}

	//? if >= 1.21.3 {
	@Override
	public void setupAnim(MurkEntityRenderState murkRenderState) {
		this.corals.visible = !murkRenderState.sheared;
		super.setupAnim(murkRenderState);
	}
	//?} else {
	/*@Override
	public void prepareMobModel(MurkEntity murk, float f, float g, float h) {
		this.corals.visible = !murk.isSheared();
		super.prepareMobModel(murk, f, g, h);
	}
	*///?}

	//? if >=1.21.9 {
	public static ArmorModelSet<LayerDefinition> createArmorLayers() {
		ArmorModelSet<MeshDefinition> mesh = HumanoidModel.createArmorMeshSet(LayerDefinitions.INNER_ARMOR_DEFORMATION, LayerDefinitions.OUTER_ARMOR_DEFORMATION);
		return mesh.map((meshDefinition) -> LayerDefinition.create(meshDefinition, 64, 64));
	}
	//?} else {
	/*public static LayerDefinition createInnerArmorLayer() {
		MeshDefinition mesh = createDefaultMesh(new CubeDeformation(0.5F));
		return LayerDefinition.create(mesh, 64, 64);
	}

	public static LayerDefinition createOuterArmorLayer() {
		MeshDefinition mesh = createDefaultMesh(new CubeDeformation(1.0F));
		return LayerDefinition.create(mesh, 64, 64);
	}
	*///?}
}