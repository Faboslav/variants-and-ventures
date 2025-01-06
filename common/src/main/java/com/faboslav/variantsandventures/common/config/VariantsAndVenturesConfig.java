package com.faboslav.variantsandventures.common.config;

public final class VariantsAndVenturesConfig
{
	public static ModMobsConfig modMobs = ModMobsConfig.HANDLER.instance();
	public static VanillaMobsConfig vanillaMobs = VanillaMobsConfig.HANDLER.instance();

	public static final int MIN_PERCENT_VALUE = 0;
	public static final int MAX_PERCENT_VALUE = 100;
	public static final int PERCENT_STEP = 1;
	public static final String PERCENT_FORMAT = "%.0f%%";


	public void load() {
		ModMobsConfig.HANDLER.load();
		VanillaMobsConfig.HANDLER.load();
	}
}
