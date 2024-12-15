package com.faboslav.variantsandventures.common.client.render.entity.state;

import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.SkeletonEntityRenderState;

@Environment(EnvType.CLIENT)
public class MurkEntityRenderState extends SkeletonEntityRenderState
{
	public MurkEntity.Variant variant;
	public boolean sheared;

	public MurkEntityRenderState() {
	}
}
