package com.faboslav.variantsandventures.common.client.render.entity.feature;

/*? >=1.21.3 {*/
import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class GelidOverlayFeatureRenderer extends RenderLayer<ZombieRenderState, DrownedModel>
{
	private static final ResourceLocation OVERLAY_TEXTURE = VariantsAndVentures.makeID("textures/entity/gelid/gelid_overlay.png");
	private final DrownedModel model;
	private final DrownedModel babyModel;

	public GelidOverlayFeatureRenderer(RenderLayerParent<ZombieRenderState, DrownedModel> renderer, EntityModelSet modelSet) {
		super(renderer);
		this.model = new DrownedModel(modelSet.bakeLayer(ModelLayers.DROWNED_OUTER_LAYER));
		this.babyModel = new DrownedModel(modelSet.bakeLayer(ModelLayers.DROWNED_BABY_OUTER_LAYER));
	}

	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ZombieRenderState renderState, float yRot, float xRot) {
		DrownedModel drownedModel = renderState.isBaby ? this.babyModel : this.model;
		coloredCutoutModelCopyLayerRender(drownedModel, OVERLAY_TEXTURE, poseStack, bufferSource, packedLight, renderState, -1);
	}
}
/*?} else {*/
/*import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class GelidOverlayFeatureRenderer<T extends GelidEntity> extends RenderLayer<T, DrownedModel<T>>
{
	private static final ResourceLocation OVERLAY_TEXTURE = VariantsAndVentures.makeID("textures/entity/gelid/gelid_overlay.png");
	private final DrownedModel<T> model;

	public GelidOverlayFeatureRenderer(
		RenderLayerParent<T, DrownedModel<T>> context,
		EntityModelSet loader
	) {
		super(context);
		this.model = new DrownedModel<>(loader.bakeLayer(ModelLayers.DROWNED_OUTER_LAYER));
	}

	public void render(
		PoseStack matrices,
		MultiBufferSource vertexConsumers,
		int light,
		T thicket,
		float limbAngle,
		float limbDistance,
		float tickDelta,
		float animationProgress,
		float headYaw,
		float headPitch
	) {
		coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, OVERLAY_TEXTURE, matrices, vertexConsumers, light, thicket, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, -1);
	}
}
*//*?}*/