package com.faboslav.variantsandventures.common.client.render.entity;

/*? >=1.21.3 {*/
import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.VerdantEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public final class VerdantEntityRenderer extends AbstractSkeletonRenderer<VerdantEntity, SkeletonRenderState>
{
	private static final ResourceLocation TEXTURE = VariantsAndVentures.makeID("textures/entity/verdant/verdant.png");

	public VerdantEntityRenderer(EntityRendererProvider.Context context) {
		super(context, ModelLayers.SKELETON, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
	}

	public ResourceLocation getTextureLocation(SkeletonRenderState skeletonRenderState) {
		return TEXTURE;
	}

	public SkeletonRenderState createRenderState() {
		return new SkeletonRenderState();
	}
}
/*?} else {*/
/*import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.entity.mob.VerdantEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public final class VerdantEntityRenderer extends HumanoidMobRenderer<VerdantEntity, SkeletonModel<VerdantEntity>>
{
	public static final ResourceLocation TEXTURE = VariantsAndVentures.makeID("textures/entity/verdant/verdant.png");

	public VerdantEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new SkeletonModel<>(context.bakeLayer(ModelLayers.SKELETON)), 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, new SkeletonModel(context.bakeLayer(ModelLayers.SKELETON_INNER_ARMOR)), new SkeletonModel(context.bakeLayer(ModelLayers.SKELETON_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(VerdantEntity verdant) {
		return TEXTURE;
	}
}
*//*?}*/