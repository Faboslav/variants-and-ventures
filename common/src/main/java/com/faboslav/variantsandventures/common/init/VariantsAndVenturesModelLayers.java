package com.faboslav.variantsandventures.common.init;

import com.faboslav.variantsandventures.common.VariantsAndVentures;
import com.faboslav.variantsandventures.common.client.model.GelidEntityModel;
import com.faboslav.variantsandventures.common.client.model.MurkEntityModel;
import com.faboslav.variantsandventures.common.client.model.ThicketEntityModel;
import com.faboslav.variantsandventures.common.client.model.VerdantEntityModel;
import com.faboslav.variantsandventures.common.events.client.RegisterEntityLayersEvent;
import com.faboslav.variantsandventures.common.util.LayerDefinitionFactory;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;

//? if >=1.21.9 {
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.model.geom.builders.LayerDefinition;
//?}

//? if >= 1.21.3 {
import net.minecraft.client.model.ZombieModel;
//?}

/**
 * @see ModelLayers
 */
public final class VariantsAndVenturesModelLayers
{
	private static final String GELID_ID = "gelid";
	private static final String THICKET_ID = "thicket";
	private static final String MURK_ID = "murk";
	private static final String VERDANT_ID = "verdant";

	//? if >= 1.21.3 {
	private static final String GELID_BABY_ID = "gelid_baby";
	private static final String THICKET_BABY_ID = "thicket_baby";
	//?}

	public static final ModelLayerLocation GELID = new ModelLayerLocation(VariantsAndVentures.makeID(GELID_ID), "main");
	public static final ModelLayerLocation GELID_OUTER = new ModelLayerLocation(VariantsAndVentures.makeID(GELID_ID), "outer");
	public static final ModelLayerLocation MURK = new ModelLayerLocation(VariantsAndVentures.makeID(MURK_ID), "main");
	public static final ModelLayerLocation THICKET = new ModelLayerLocation(VariantsAndVentures.makeID(THICKET_ID), "main");
	public static final ModelLayerLocation THICKET_OUTER = new ModelLayerLocation(VariantsAndVentures.makeID(THICKET_ID), "outer");
	public static final ModelLayerLocation VERDANT = new ModelLayerLocation(VariantsAndVentures.makeID(VERDANT_ID), "main");

	//? if >= 1.21.3 {
	public static final ModelLayerLocation GELID_BABY = new ModelLayerLocation(VariantsAndVentures.makeID(GELID_BABY_ID), "main");
	public static final ModelLayerLocation GELID_BABY_OUTER = new ModelLayerLocation(VariantsAndVentures.makeID(GELID_BABY_ID), "outer");
	public static final ModelLayerLocation THICKET_BABY = new ModelLayerLocation(VariantsAndVentures.makeID(THICKET_BABY_ID), "main");
	public static final ModelLayerLocation THICKET_BABY_OUTER = new ModelLayerLocation(VariantsAndVentures.makeID(THICKET_BABY_ID), "outer");
	//?}

	//? if >= 1.21.3 && < 1.21.9 {
	/*public static final ModelLayerLocation GELID_BABY_INNER_ARMOR = new ModelLayerLocation(VariantsAndVentures.makeID(GELID_BABY_ID), "inner_armor");
	public static final ModelLayerLocation GELID_BABY_OUTER_ARMOR = new ModelLayerLocation(VariantsAndVentures.makeID(GELID_BABY_ID), "outer_armor");
	public static final ModelLayerLocation THICKET_BABY_INNER_ARMOR = new ModelLayerLocation(VariantsAndVentures.makeID(THICKET_BABY_ID), "inner_armor");
	public static final ModelLayerLocation THICKET_BABY_OUTER_ARMOR = new ModelLayerLocation(VariantsAndVentures.makeID(THICKET_BABY_ID), "outer_armor");
	*///?}

	//? if >=1.21.9 {
	public static final ArmorModelSet<ModelLayerLocation> GELID_ARMOR = ModelLayers.registerArmorSet(GELID_ID);
	public static final ArmorModelSet<ModelLayerLocation> GELID_BABY_ARMOR = ModelLayers.registerArmorSet(GELID_BABY_ID);
	public static final ArmorModelSet<ModelLayerLocation> MURK_ARMOR = ModelLayers.registerArmorSet(MURK_ID);
	public static final ArmorModelSet<ModelLayerLocation> THICKET_ARMOR = ModelLayers.registerArmorSet(THICKET_ID);
	public static final ArmorModelSet<ModelLayerLocation> THICKET_BABY_ARMOR = ModelLayers.registerArmorSet(THICKET_BABY_ID);
	public static final ArmorModelSet<ModelLayerLocation> VERDANT_ARMOR = ModelLayers.registerArmorSet(VERDANT_ID);
	//?} else {
	/*public static final ModelLayerLocation GELID_INNER_ARMOR = new ModelLayerLocation(VariantsAndVentures.makeID(GELID_ID), "inner_armor");
	public static final ModelLayerLocation GELID_OUTER_ARMOR = new ModelLayerLocation(VariantsAndVentures.makeID(GELID_ID), "outer_armor");
	public static final ModelLayerLocation MURK_INNER_ARMOR = new ModelLayerLocation(VariantsAndVentures.makeID(MURK_ID), "inner_armor");
	public static final ModelLayerLocation MURK_OUTER_ARMOR = new ModelLayerLocation(VariantsAndVentures.makeID(MURK_ID), "outer_armor");
	public static final ModelLayerLocation THICKET_INNER_ARMOR = new ModelLayerLocation(VariantsAndVentures.makeID(THICKET_ID), "inner_armor");
	public static final ModelLayerLocation THICKET_OUTER_ARMOR = new ModelLayerLocation(VariantsAndVentures.makeID(THICKET_ID), "outer_armor");
	public static final ModelLayerLocation VERDANT_INNER_ARMOR = new ModelLayerLocation(VariantsAndVentures.makeID(VERDANT_ID), "inner_armor");
	public static final ModelLayerLocation VERDANT_OUTER_ARMOR = new ModelLayerLocation(VariantsAndVentures.makeID(VERDANT_ID), "outer_armor");
	*///?}

	public static void registerEntityLayers(RegisterEntityLayersEvent event) {
		event.register(GELID, GelidEntityModel::createBodyLayer);
		event.register(GELID_OUTER, GelidEntityModel::createOuterLayer);
		event.register(MURK, MurkEntityModel::createBodyLayer);
		event.register(THICKET, ThicketEntityModel::createBodyLayer);
		event.register(THICKET_OUTER, ThicketEntityModel::createOuterLayer);
		event.register(VERDANT, VerdantEntityModel::createBodyLayer);

		//? if >=1.21.9 {
		registerArmorLayers(event, GELID_ARMOR, GelidEntityModel.createArmorLayers());
		registerArmorLayers(event, GELID_BABY_ARMOR, GelidEntityModel.createArmorLayers().map((layerDefinition) -> layerDefinition.apply(ZombieModel.BABY_TRANSFORMER)));
		registerArmorLayers(event, MURK_ARMOR, MurkEntityModel.createArmorLayers());
		registerArmorLayers(event, THICKET_ARMOR, ThicketEntityModel.createArmorLayers());
		registerArmorLayers(event, THICKET_BABY_ARMOR, ThicketEntityModel.createArmorLayers().map((layerDefinition) -> layerDefinition.apply(ZombieModel.BABY_TRANSFORMER)));
		registerArmorLayers(event, VERDANT_ARMOR, LayerDefinitionFactory.createArmorLayers());
		//?} else {
		/*event.register(GELID_INNER_ARMOR, GelidEntityModel::createInnerArmorLayer);
		event.register(GELID_OUTER_ARMOR, GelidEntityModel::createOuterArmorLayer);
		event.register(MURK_INNER_ARMOR, MurkEntityModel::createInnerArmorLayer);
		event.register(MURK_OUTER_ARMOR, MurkEntityModel::createOuterArmorLayer);
		event.register(THICKET_INNER_ARMOR, ThicketEntityModel::createInnerArmorLayer);
		event.register(THICKET_OUTER_ARMOR, ThicketEntityModel::createOuterArmorLayer);
		event.register(VERDANT_INNER_ARMOR, LayerDefinitionFactory::createInnerArmorLayer);
		event.register(VERDANT_OUTER_ARMOR, LayerDefinitionFactory::createOuterArmorLayer);
		*///?}
		

		//? if >= 1.21.3 && < 1.21.9 {
		/*event.register(GELID_BABY_INNER_ARMOR, () -> GelidEntityModel.createInnerArmorLayer().apply(ZombieModel.BABY_TRANSFORMER));
		event.register(GELID_BABY_OUTER_ARMOR, () -> GelidEntityModel.createOuterArmorLayer().apply(ZombieModel.BABY_TRANSFORMER));
		event.register(THICKET_BABY_INNER_ARMOR, () -> ThicketEntityModel.createInnerArmorLayer().apply(ZombieModel.BABY_TRANSFORMER));
		event.register(THICKET_BABY_OUTER_ARMOR, () -> ThicketEntityModel.createOuterArmorLayer().apply(ZombieModel.BABY_TRANSFORMER));
		*///?}

		//? if >= 1.21.3 {
		event.register(GELID_BABY, () -> GelidEntityModel.createBodyLayer().apply(ZombieModel.BABY_TRANSFORMER));
		event.register(GELID_BABY_OUTER, () -> GelidEntityModel.createOuterLayer().apply(ZombieModel.BABY_TRANSFORMER));
		event.register(THICKET_BABY, () -> ThicketEntityModel.createBodyLayer().apply(ZombieModel.BABY_TRANSFORMER));
		event.register(THICKET_BABY_OUTER, () -> ThicketEntityModel.createOuterLayer().apply(ZombieModel.BABY_TRANSFORMER));
		//?}
	}

	//? if >=1.21.9 {
	public static void registerArmorLayers(
		RegisterEntityLayersEvent event,
		ArmorModelSet<ModelLayerLocation> armorModelSet,
		ArmorModelSet<LayerDefinition> armorLayers
	) {
		event.register(armorModelSet.head(), armorLayers::head);
		event.register(armorModelSet.chest(), armorLayers::chest);
		event.register(armorModelSet.legs(), armorLayers::legs);
		event.register(armorModelSet.feet(), armorLayers::feet);
	}
	//?}

	private VariantsAndVenturesModelLayers() {
	}
}
