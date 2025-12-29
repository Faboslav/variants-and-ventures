package com.faboslav.variantsandventures.common.client.model;

import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;

//? if >=1.21.3 {
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
//?} else {
/*import com.faboslav.variantsandventures.common.entity.mob.VerdantEntity;
 *///?}

//? if >= 1.21.3 {
public class VerdantEntityModel extends SkeletonModel<SkeletonRenderState>
//?} else {
/*public class VerdantEntityModel extends SkeletonModel<VerdantEntity>
*///?}
{
	public VerdantEntityModel(ModelPart modelPart) {
		super(modelPart);
	}
}