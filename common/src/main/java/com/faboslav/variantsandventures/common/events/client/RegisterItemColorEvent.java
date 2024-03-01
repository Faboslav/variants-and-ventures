package com.faboslav.variantsandventures.common.events.client;

import com.faboslav.variantsandventures.common.events.base.EventHandler;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public record RegisterItemColorEvent(BiConsumer<ItemColorProvider, ItemConvertible[]> colors,
									 BlockColorProvider blockColors)
{

	public static final EventHandler<RegisterItemColorEvent> EVENT = new EventHandler<>();

	public void register(ItemColorProvider color, ItemConvertible... items) {
		colors.accept(color, items);
	}


	@FunctionalInterface
	public interface BlockColorProvider
	{

		int getColor(
			BlockState blockState,
			@Nullable BlockRenderView blockRenderView,
			@Nullable BlockPos blockPos,
			int i
		);
	}
}
