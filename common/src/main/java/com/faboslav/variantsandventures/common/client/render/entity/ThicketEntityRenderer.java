package com.faboslav.variantsandventures.common.client.render.entity;

//? if >= 1.21.9 {
import net.minecraft.client.renderer.entity.ArmorModelSet;
 //?}

//? if >=1.21.3 {
import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.render.entity.feature.ThicketOverlayFeatureRenderer;
import com.faboslav.variantsandventures.common.entity.mob.ThicketEntity;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;

public class ThicketEntityRenderer extends AbstractZombieRenderer<ThicketEntity, ZombieRenderState, DrownedModel>
{
	private static final ResourceLocation TEXTURE = VariantsAndVentures.makeID("textures/entity/thicket/thicket.png");

	public ThicketEntityRenderer(EntityRendererProvider.Context context) {
		//? if >= 1.21.9 {
		super(context, new DrownedModel(context.bakeLayer(ModelLayers.DROWNED)), new DrownedModel(context.bakeLayer(ModelLayers.DROWNED_BABY)), ArmorModelSet.bake(ModelLayers.DROWNED_ARMOR, context.getModelSet(), DrownedModel::new), ArmorModelSet.bake(ModelLayers.DROWNED_BABY_ARMOR, context.getModelSet(), DrownedModel::new));
		//?} else {
		/*super(context, new DrownedModel(context.bakeLayer(ModelLayers.DROWNED)), new DrownedModel(context.bakeLayer(ModelLayers.DROWNED_BABY)), new DrownedModel(context.bakeLayer(ModelLayers.DROWNED_INNER_ARMOR)), new DrownedModel(context.bakeLayer(ModelLayers.DROWNED_OUTER_ARMOR)), new DrownedModel(context.bakeLayer(ModelLayers.DROWNED_BABY_INNER_ARMOR)), new DrownedModel(context.bakeLayer(ModelLayers.DROWNED_BABY_OUTER_ARMOR)));
		*///?}
		this.addLayer(new ThicketOverlayFeatureRenderer(this, context.getModelSet()));
	}

	@Override
	public ZombieRenderState createRenderState() {
		return new ZombieRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(ZombieRenderState renderState) {
		return TEXTURE;
	}
}
/*?} else {*/
/*import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.render.entity.feature.ThicketOverlayFeatureRenderer;
import com.faboslav.variantsandventures.common.entity.mob.ThicketEntity;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ThicketEntityRenderer extends AbstractZombieRenderer<ThicketEntity, DrownedModel<ThicketEntity>>
{
	public static final ResourceLocation TEXTURE = VariantsAndVentures.makeID("textures/entity/thicket/thicket.png");

	public ThicketEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new DrownedModel<>(context.bakeLayer(ModelLayers.DROWNED)), new DrownedModel<>(context.bakeLayer(ModelLayers.DROWNED_INNER_ARMOR)), new DrownedModel<>(context.bakeLayer(ModelLayers.DROWNED_OUTER_ARMOR)));
		this.addLayer(new ThicketOverlayFeatureRenderer<>(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(ThicketEntity thicket) {
		return TEXTURE;
	}
}
*//*?}*/