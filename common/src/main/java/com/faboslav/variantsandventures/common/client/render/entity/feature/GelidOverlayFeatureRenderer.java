package com.faboslav.variantsandventures.common.client.render.entity.feature;


import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.model.GelidEntityModel;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.Identifier;
import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import net.minecraft.client.renderer.MultiBufferSource;

//? if >= 1.21.9 {
import net.minecraft.client.renderer.SubmitNodeCollector;
//?} else {
/*import net.minecraft.client.renderer.MultiBufferSource;
*///?}

//? if >= 1.21.3 {
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
//?}

//? if >= 1.21.3 {
public class GelidOverlayFeatureRenderer extends RenderLayer<ZombieRenderState, GelidEntityModel<ZombieRenderState>>
//?} else {
/*public class GelidOverlayFeatureRenderer<T extends GelidEntity> extends RenderLayer<T, GelidEntityModel<T>>
*///?}
{
	private static final Identifier OVERLAY_TEXTURE = VariantsAndVentures.makeID("textures/entity/gelid/gelid_overlay.png");
	private final GelidEntityModel model;
	//? if >=1.21.3 {
	private final GelidEntityModel babyModel;
	//?}

	//? if >=1.21.3 {
	public GelidOverlayFeatureRenderer(RenderLayerParent<ZombieRenderState, GelidEntityModel<ZombieRenderState>> renderer, EntityModelSet modelSet) {
		super(renderer);
		this.model = new GelidEntityModel(modelSet.bakeLayer(VariantsAndVenturesModelLayers.GELID_OUTER));
		this.babyModel = new GelidEntityModel(modelSet.bakeLayer(VariantsAndVenturesModelLayers.GELID_BABY_OUTER));
	}
	//?} else {
	/*public GelidOverlayFeatureRenderer(
		RenderLayerParent<T, GelidEntityModel<T>> context,
		EntityModelSet loader
	) {
		super(context);
		this.model = new GelidEntityModel<>(loader.bakeLayer(VariantsAndVenturesModelLayers.GELID_OUTER));
	}
	*///?}

	//? if >= 1.21.9 {
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, ZombieRenderState renderState, float yRot, float xRot)
	//?} else if >= 1.21.3 {
	/*public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ZombieRenderState renderState, float yRot, float xRot)
	*///?} else {
	/*public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T gelid, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch)
	*///?}
	{
		//? if >= 1.21.3 {
		GelidEntityModel gelidModel = renderState.isBaby ? this.babyModel : this.model;
		//?}

		//? if >= 1.21.9 {
		coloredCutoutModelCopyLayerRender(gelidModel, OVERLAY_TEXTURE, poseStack, submitNodeCollector, packedLight, renderState, -1, 1);
		 //?} else if >= 1.21.3 {
		/*coloredCutoutModelCopyLayerRender(gelidModel, OVERLAY_TEXTURE, poseStack, bufferSource, packedLight, renderState, -1);
		 *///?} else if >= 1.21.1 {
		/*coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, OVERLAY_TEXTURE, poseStack, bufferSource, packedLight, gelid, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, -1);
		*///?} else {
		/*coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, OVERLAY_TEXTURE, poseStack, bufferSource, packedLight, gelid, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, 1.0F, 1.0F, 1.0F);
		 *///?}
	}
}