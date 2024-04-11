package com.faboslav.variantsandventures.common.block;

import net.minecraft.block.SkullBlock;

public enum SkullBlockType
{
	GELID("gelid"),
	MURK("murk"),
	THICKET("thicket"),
	VERDANT("verdant");

	private final String id;

	private SkullBlockType(String id) {
		this.id = id;
	}

	public String asString() {
		return this.id;
	}

	public SkullBlock.Type get() {
		return SkullBlock.Type.valueOf(this.name());
	}

	static {
		SkullBlock.Type.values();
	}
}