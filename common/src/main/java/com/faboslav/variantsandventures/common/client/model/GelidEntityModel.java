package com.faboslav.variantsandventures.common.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.*;
//? if >= 1.21.9 {
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.model.geom.LayerDefinitions;
//?} else {
/*import net.minecraft.world.entity.monster.Zombie;
*///?}

//? if >= 1.21.3 {
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
//?}

//? if >= 1.21.3 {
public class GelidEntityModel<T extends ZombieRenderState> extends ZombieModel<T>
//? } else {
/*public class GelidEntityModel<T extends Zombie> extends ZombieModel<T>
*///?}
{
	public GelidEntityModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		return LayerDefinition.create(modelData, 64, 64);
	}

	public static LayerDefinition createOuterLayer() {
		MeshDefinition modelData = HumanoidModel.createMesh(new CubeDeformation(0.25F), 0.0F);
		return LayerDefinition.create(modelData, 64, 64);
	}

	//? if >=1.21.9 {
	public static ArmorModelSet<LayerDefinition> createArmorLayers() {
		ArmorModelSet<MeshDefinition> mesh = HumanoidModel.createArmorMeshSet(LayerDefinitions.INNER_ARMOR_DEFORMATION, LayerDefinitions.OUTER_ARMOR_DEFORMATION);
		return mesh.map((meshDefinition) -> LayerDefinition.create(meshDefinition, 64, 32));
	}
	//?} else {
	/*public static LayerDefinition createInnerArmorLayer() {
		MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.5F), 0.0F);
		return LayerDefinition.create(mesh, 64, 64);
	}

	public static LayerDefinition createOuterArmorLayer() {
		MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(1.0F), 0.0F);
		return LayerDefinition.create(mesh, 64, 64);
	}
	*///?}
}
