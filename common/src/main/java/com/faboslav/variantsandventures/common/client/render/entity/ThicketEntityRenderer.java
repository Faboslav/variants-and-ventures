package com.faboslav.variantsandventures.common.client.render.entity;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.render.entity.feature.ThicketOverlayFeatureRenderer;
import com.faboslav.variantsandventures.common.entity.mob.ThicketEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import com.faboslav.variantsandventures.common.init.VariantsAndVenturesModelLayers;
import com.faboslav.variantsandventures.common.client.model.ThicketEntityModel;
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
public class ThicketEntityRenderer extends AbstractZombieRenderer<ThicketEntity, ZombieRenderState, ThicketEntityModel<ZombieRenderState>>
 //? } else {
/*public class ThicketEntityRenderer extends AbstractZombieRenderer<ThicketEntity, ThicketEntityModel<ThicketEntity>>
*///?}
{
	public static final Identifier TEXTURE = VariantsAndVentures.makeID("textures/entity/thicket/thicket.png");

	public ThicketEntityRenderer(EntityRendererProvider.Context context) {
		//? if >= 1.21.9 {
		super(
			context,
			new ThicketEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.THICKET)),
			new ThicketEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.THICKET_BABY)),
			ArmorModelSet.bake(VariantsAndVenturesModelLayers.THICKET_ARMOR, context.getModelSet(), ThicketEntityModel::new),
			ArmorModelSet.bake(VariantsAndVenturesModelLayers.THICKET_BABY_ARMOR, context.getModelSet(), ThicketEntityModel::new)
		);
		//? } else if >= 1.21.3 {
		/*super(
			context,
			new ThicketEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.THICKET)),
			new ThicketEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.THICKET_BABY)),
			new ThicketEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.THICKET_INNER_ARMOR)),
			new ThicketEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.THICKET_OUTER_ARMOR)),
			new ThicketEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.THICKET_BABY_INNER_ARMOR)),
			new ThicketEntityModel(context.bakeLayer(VariantsAndVenturesModelLayers.THICKET_BABY_OUTER_ARMOR))
		);
		*///?} else {
		/*super(
			context,
			new ThicketEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.THICKET)),
			new ThicketEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.THICKET_INNER_ARMOR)),
			new ThicketEntityModel<>(context.bakeLayer(VariantsAndVenturesModelLayers.THICKET_OUTER_ARMOR))
		);
		*///?}

		this.addLayer(new ThicketOverlayFeatureRenderer(this, context.getModelSet()));
	}

	//? if >= 1.21.3 {
	public ZombieRenderState createRenderState() {
		return new ZombieRenderState();
	}

	@Override
	public Identifier getTextureLocation(ZombieRenderState state) {
		return TEXTURE;
	}
	//?} else {
	/*@Override
	public ResourceLocation getTextureLocation(ThicketEntity thicket) {
		return TEXTURE;
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
		ThicketEntity thicket,
		PoseStack matrices,
		float animationProgress,
		float bodyYaw,
		float tickDelta
		//? if >= 1.21 {
		, float scale
		 //?}
	) {
		super.setupRotations(
			thicket,
			matrices,
			animationProgress,
			bodyYaw,
			tickDelta
			//? if >= 1.21 {
			, scale
			 //?}
		);

		float h = thicket.getSwimAmount(tickDelta);
		if (h > 0.0F) {
			float angle = Mth.lerp(h, thicket.getXRot(), -10.0F - thicket.getXRot());
			matrices.mulPose(Axis.XP.rotationDegrees(angle));
		}
	}
	*///?}
}