/*? if >=1.21.3 {*/
package com.faboslav.variantsandventures.common.client.render.entity.state;

import com.faboslav.variantsandventures.common.entity.mob.MurkEntity;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;

public class MurkEntityRenderState extends SkeletonRenderState
{
	public MurkEntity.Variant variant;
	public boolean sheared;

	public MurkEntityRenderState() {
	}
}
/*?}*/