package com.faboslav.variantsandventures.common.client.render.entity.feature;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.model.ThicketEntityModel;
import com.faboslav.variantsandventures.common.entity.mob.ThicketEntity;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

//? if >= 1.21.9 {
import net.minecraft.client.renderer.SubmitNodeCollector;
//?} else {

//?}

//? if >= 1.21.3 {
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
//?}

//? if >= 1.21.3 {
public class ThicketOverlayFeatureRenderer extends RenderLayer<ZombieRenderState, ThicketEntityModel<ZombieRenderState>>
//?} else {
/*public class ThicketOverlayFeatureRenderer<T extends ThicketEntity> extends RenderLayer<T, ThicketEntityModel<T>>
*///?}
{
	private static final ResourceLocation OVERLAY_TEXTURE = VariantsAndVentures.makeID("textures/entity/thicket/thicket_overlay.png");
	private final ThicketEntityModel model;
	//? if >=1.21.3 {
	private final ThicketEntityModel babyModel;
	//?}

	//? if >=1.21.3 {
	public ThicketOverlayFeatureRenderer(RenderLayerParent<ZombieRenderState, ThicketEntityModel<ZombieRenderState>> renderer, EntityModelSet modelSet) {
		super(renderer);
		this.model = new ThicketEntityModel(modelSet.bakeLayer(VariantsAndVenturesModelLayers.THICKET_OUTER));
		this.babyModel = new ThicketEntityModel(modelSet.bakeLayer(VariantsAndVenturesModelLayers.THICKET_BABY_OUTER));
	}
	//?} else {
	/*public ThicketOverlayFeatureRenderer(
		RenderLayerParent<T, ThicketEntityModel<T>> context,
		EntityModelSet loader
	) {
		super(context);
		this.model = new ThicketEntityModel<>(loader.bakeLayer(VariantsAndVenturesModelLayers.THICKET_OUTER));
	}
	*///?}
	
	//? if >= 1.21.9 {
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, ZombieRenderState renderState, float yRot, float xRot)
	//?} else if >= 1.21.3 {
	/*public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ZombieRenderState renderState, float yRot, float xRot)
	*///?} else {
	/*public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T thicket, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch)
	*///?}
	{
		//? if >= 1.21.3 {
		ThicketEntityModel thicketModel = renderState.isBaby ? this.babyModel : this.model;
		//?}

		//? if >= 1.21.9 {
		coloredCutoutModelCopyLayerRender(thicketModel, OVERLAY_TEXTURE, poseStack, submitNodeCollector, packedLight, renderState, -1, 1);
		//?} else if >= 1.21.3 {
		/*coloredCutoutModelCopyLayerRender(thicketModel, OVERLAY_TEXTURE, poseStack, bufferSource, packedLight, renderState, -1);
		*///?} else if >= 1.21.1 {
		/*coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, OVERLAY_TEXTURE, poseStack, bufferSource, packedLight, thicket, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, -1);
		*///?} else {
		/*coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, OVERLAY_TEXTURE, poseStack, bufferSource, packedLight, thicket, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, 1.0F, 1.0F, 1.0F);
		*///?}
	}
}