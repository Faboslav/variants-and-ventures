package com.faboslav.variantsandventures.common.events.client;

import com.faboslav.variantsandventures.common.block.SkullBlockType;
import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.block.entity.SkullBlockEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLoader;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * Event related is code based on The Bumblezone/Resourceful Lib mods with permissions from the authors
 *
 * @author TelepathicGrunt
 * <a href="https://github.com/TelepathicGrunt/Bumblezone">https://github.com/TelepathicGrunt/Bumblezone</a>
 * @author ThatGravyBoat
 * <a href="https://github.com/Team-Resourceful/ResourcefulLib">https://github.com/Team-Resourceful/ResourcefulLib</a>
 */
public record RegisterSkullModelEvent(BiConsumer<SkullBlock.SkullType, SkullBlockEntityModel> registrar, EntityModelLoader entityModelLoader)
{
	public static final EventHandler<RegisterSkullModelEvent> EVENT = new EventHandler<>();

	public void register(SkullBlock.SkullType skullType, SkullBlockEntityModel model) {
		registrar.accept(skullType, model);
	}
}
