package com.faboslav.variantsandventures.common.world.gen.trunk;


import com.faboslav.variantsandventures.common.init.VariantsAndVenturesTrunkPlacerType;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class ArchingTrunkPlacer extends TrunkPlacer
{
	public static final Codec<ArchingTrunkPlacer> CODEC = RecordCodecBuilder.create(instance -> ArchingTrunkPlacer.fillTrunkPlacerFields(instance).apply(instance, ArchingTrunkPlacer::new));

	public ArchingTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
		super(baseHeight, heightRandA, heightRandB);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return VariantsAndVenturesTrunkPlacerType.ARCHING_TRUNK_PLACER.get();
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(
		TestableWorld world,
		BiConsumer<BlockPos, BlockState> replacer,
		Random random,
		int height,
		BlockPos startPos,
		TreeFeatureConfig config
	) {
		setToDirt(world, replacer, random, startPos.down(), config);
		List<FoliagePlacer.TreeNode> treeNodes = Lists.newArrayList();
		Direction randomDirection = Direction.Type.HORIZONTAL.random(random);
		BlockPos.Mutable mutableBlockPos = startPos.mutableCopy();

		for (int currentHeight = 0; currentHeight < height; ++currentHeight) {
			if (currentHeight > 5 && random.nextFloat() <= 0.2F || currentHeight >= 8) {
				break;
			}

			if (currentHeight > 5 && random.nextFloat() <= (currentHeight * 0.1) + 0.1) {
				this.getAndSetState(world, replacer, random, mutableBlockPos, config);
				mutableBlockPos.move(randomDirection);
			}

			this.getAndSetState(world, replacer, random, mutableBlockPos, config);
			mutableBlockPos.move(Direction.UP);
		}

		var initialFirstBranchBlockPos = mutableBlockPos.mutableCopy();
		Direction firstBranchDirection = Direction.Type.HORIZONTAL.random(random);
		this.createBranch(initialFirstBranchBlockPos, firstBranchDirection, treeNodes, world, replacer, random, config);

		Direction secondBranchDirection = Direction.Type.HORIZONTAL.random(random);

		if (secondBranchDirection != firstBranchDirection) {
			var initialSecondBranchBlockPos = mutableBlockPos.mutableCopy();
			this.createBranch(initialSecondBranchBlockPos, secondBranchDirection, treeNodes, world, replacer, random, config);
		}

		return treeNodes;
	}

	private void createBranch(
		BlockPos.Mutable mutableBlockPos,
		Direction randomDirection,
		List<FoliagePlacer.TreeNode> treeNodes,
		TestableWorld world,
		BiConsumer<BlockPos, BlockState> replacer,
		Random random,
		TreeFeatureConfig config
	) {
		int finalBranchLength = random.nextBetween(3, 5);

		for (int currentBranchLength = 0; currentBranchLength <= finalBranchLength; ++currentBranchLength) {
			if (currentBranchLength >= 2 && random.nextBoolean()) {
				this.getAndSetState(world, replacer, random, mutableBlockPos, config);
				mutableBlockPos.move(Direction.DOWN);
			}

			this.getAndSetState(world, replacer, random, mutableBlockPos, config);

			if (currentBranchLength == finalBranchLength) {
				treeNodes.add(new FoliagePlacer.TreeNode(new BlockPos(mutableBlockPos.getX(), mutableBlockPos.getY(), mutableBlockPos.getZ()), 1, false));
			}

			mutableBlockPos.move(randomDirection);
		}
	}
}