package com.faboslav.variantsandventures.common.block;

import net.minecraft.block.SkullBlock;

public enum SkullBlockType
{
	GELID,
	THICKET,
	VERDANT;

	public SkullBlock.Type get() {
		return SkullBlock.Type.valueOf(this.name());
	}

	static {
		SkullBlock.Type.values();
	}
}