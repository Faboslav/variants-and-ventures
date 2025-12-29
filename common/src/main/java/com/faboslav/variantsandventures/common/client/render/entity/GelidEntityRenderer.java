package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.render.entity.feature.GelidOverlayFeatureRenderer;
import com.faboslav.variantsandventures.common.entity.mob.GelidEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import com.faboslav.variantsandventures.common.client.model.GelidEntityModel;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;

//? if >= 1.21.3 {
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
//?} else {
//?}

//? if >= 1.21.9 {
import net.minecraft.client.renderer.entity.ArmorModelSet;
//?} else {
//?}

//? if >= 1.21.3 {
public class GelidEntityRenderer extends AbstractZombieRenderer<GelidEntity, ZombieRenderState, GelidEntityModel<ZombieRenderState>>
//? } else {
/*public class GelidEntityRenderer extends AbstractZombieRenderer<GelidEntity, GelidEntityModel<GelidEntity>>
*///?}
{
	public static final ResourceLocation TEXTURE = VariantsAndVentures.makeID("textures/entity/gelid/gelid.png");

	public GelidEntityRenderer(EntityRendererProvider.Context context) {
		//? if >= 1.21.9 {
		super(
			context,
			new GelidEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.GELID)),
			new GelidEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.GELID_BABY)),
			ArmorModelSet.bake(VariantsAndVenturesModelLayers.GELID_ARMOR, context.getModelSet(), GelidEntityModel::new),
			ArmorModelSet.bake(VariantsAndVenturesModelLayers.GELID_BABY_ARMOR, context.getModelSet(), GelidEntityModel::new)
		);
		//? } else if >= 1.21.3 {
		/*super(
			context,
			new GelidEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.GELID)),
			new GelidEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.GELID_BABY)),
			new GelidEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.GELID_INNER_ARMOR)),
			new GelidEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.GELID_OUTER_ARMOR)),
			new GelidEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.GELID_BABY_INNER_ARMOR)),
			new GelidEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.GELID_BABY_OUTER_ARMOR))
		);
		*///?} else {
		/*super(
			context,
			new GelidEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.GELID)),
			new GelidEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.GELID_INNER_ARMOR)),
			new GelidEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.GELID_OUTER_ARMOR))
		);
		*///?}

		this.addLayer(new GelidOverlayFeatureRenderer(this, context.getModelSet()));
	}

	//? if >= 1.21.3 {
	public ZombieRenderState createRenderState() {
		return new ZombieRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(ZombieRenderState state) {
		return TEXTURE;
	}
	//?} else {
	/*@Override
	public ResourceLocation getTextureLocation(GelidEntity gelid) {
		return TEXTURE;
	}
	*///?}

	//? if < 1.21.3 {
	/*@Override
	protected void scale(GelidEntity gelid, PoseStack matrixStack, float partialTick) {
		matrixStack.scale(1.0625F, 1.0625F, 1.0625F);
		super.scale(gelid, matrixStack, partialTick);
	}
	*///?}

	//? if >= 1.21.3 {
	@Override
	protected void setupRotations(ZombieRenderState state, PoseStack poseStack, float bodyRot, float scale) {
		super.setupRotations(state, poseStack, bodyRot, scale);
		float h = state.swimAmount;
		if (h > 0.0F) {
			float target = -10.0F - state.xRot;
			float angle = Mth.lerp(h, 0.0F, target);
			poseStack.rotateAround(Axis.XP.rotationDegrees(angle), 0.0F, state.boundingBoxHeight / 2.0F / scale, 0.0F);
		}
	}
	//?} else {
	/*@Override
	protected void setupRotations(
		GelidEntity gelid,
		PoseStack matrices,
		float animationProgress,
		float bodyYaw,
		float tickDelta
		//? if >= 1.21 {
		, float scale
		//?}
	) {
		super.setupRotations(
			gelid,
			matrices,
			animationProgress,
			bodyYaw,
			tickDelta
			//? if >= 1.21 {
			, scale
			//?}
		);

		float h = gelid.getSwimAmount(tickDelta);
		if (h > 0.0F) {
			float angle = Mth.lerp(h, gelid.getXRot(), -10.0F - gelid.getXRot());
			matrices.mulPose(Axis.XP.rotationDegrees(angle));
		}
	}
	*///?}
}