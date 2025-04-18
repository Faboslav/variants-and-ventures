package com.faboslav.variantsandventures.common.config;

public final class VariantsAndVenturesConfig
{
	public static final int MIN_PERCENT_VALUE = 0;
	public static final int MAX_PERCENT_VALUE = 100;
	public static final int PERCENT_STEP = 1;
	public static final String PERCENT_FORMAT = "%.0f%%";

	public ModMobsConfig getModMobsConfig() {
		return ModMobsConfig.HANDLER.instance();
	}

	public VanillaMobsConfig getVanillaMobsConfig() {
		return VanillaMobsConfig.HANDLER.instance();
	}

	public void load() {
		this.getModMobsConfig().load();
		this.getVanillaMobsConfig().load();
	}
}
