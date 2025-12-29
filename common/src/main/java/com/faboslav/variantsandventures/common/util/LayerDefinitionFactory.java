package com.faboslav.variantsandventures.common.util;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;

//? if >= 1.21.9 {
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.model.geom.LayerDefinitions;
//?}

public final class LayerDefinitionFactory
{
	//? if >=1.21.9 {
	public static ArmorModelSet<LayerDefinition> createArmorLayers() {
		ArmorModelSet<MeshDefinition> mesh = HumanoidModel.createArmorMeshSet(LayerDefinitions.INNER_ARMOR_DEFORMATION, LayerDefinitions.OUTER_ARMOR_DEFORMATION);
		return mesh.map((meshDefinition) -> LayerDefinition.create(meshDefinition, 64, 32));
	}
	//?} else {
	/*public static LayerDefinition createInnerArmorLayer() {
		MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.5F), 0.0F);
		return LayerDefinition.create(mesh, 64, 32);
	}

	public static LayerDefinition createOuterArmorLayer() {
		MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(1.0F), 0.0F);
		return LayerDefinition.create(mesh, 64, 32);
	}
	*///?}
}
