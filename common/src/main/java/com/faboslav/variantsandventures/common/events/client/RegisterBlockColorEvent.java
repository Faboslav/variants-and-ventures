package com.faboslav.variantsandventures.common.events.client;

import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import java.util.function.BiConsumer;

/**
 * Event related is code based on The Bumblezone/Resourceful Lib mods with permissions from the authors
 *
 * @author TelepathicGrunt
 * <a href="https://github.com/TelepathicGrunt/Bumblezone">https://github.com/TelepathicGrunt/Bumblezone</a>
 * @author ThatGravyBoat
 * <a href="https://github.com/Team-Resourceful/ResourcefulLib">https://github.com/Team-Resourceful/ResourcefulLib</a>
 */
public record RegisterBlockColorEvent(BiConsumer<BlockColorProvider, Block[]> colors)
{

	public static final EventHandler<RegisterBlockColorEvent> EVENT = new EventHandler<>();

	public void register(BlockColorProvider color, Block... blocks) {
		colors.accept(color, blocks);
	}
}

