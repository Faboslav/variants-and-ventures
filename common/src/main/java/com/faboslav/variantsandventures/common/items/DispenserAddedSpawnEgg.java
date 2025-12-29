//? if < 1.21.1 {
/*package com.faboslav.variantsandventures.common.items;

import com.faboslav.variantsandventures.common.versions.VersionedEntitySpawnReason;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.DispenserBlock;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;
import net.minecraft.core.BlockSource;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.flag.FeatureFlagSet;

public class DispenserAddedSpawnEgg extends SpawnEggItem
{
    private final Supplier<? extends EntityType<? extends Mob>> entityType;

    public DispenserAddedSpawnEgg(Supplier<? extends EntityType<? extends Mob>> typeIn, int primaryColorIn, int secondaryColorIn, Properties builder) {
		super(null, primaryColorIn, secondaryColorIn, builder);
        this.entityType = typeIn;

        setupDispenserBehavior();
    }

    protected void setupDispenserBehavior() {
        DispenserBlock.registerBehavior(
                this,
                new DefaultDispenseItemBehavior() {
                    public ItemStack execute(@NotNull BlockSource source, @NotNull ItemStack stack) {
						Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
						EntityType<?> entitytype = ((SpawnEggItem)stack.getItem()).getType(stack.getTag());
                        entitytype.spawn(source.getLevel(), stack, null, source.getPos().relative(direction), VersionedEntitySpawnReason.DISPENSER, direction != Direction.UP, false);
						stack.shrink(1);

                        return stack;
                    }
                });
    }

	@Override
	public EntityType<?> getType(@Nullable CompoundTag compoundTag) {
        if (compoundTag != null && compoundTag.contains("EntityTag", 10)) {
            CompoundTag compoundTag2 = compoundTag.getCompound("EntityTag");
            if (compoundTag2.contains("id", 8)) {
                return EntityType.byString(compoundTag2.getString("id")).orElseGet(this.entityType);
            }
        }

        return this.entityType.get();
    }

    @Override
    public FeatureFlagSet requiredFeatures() {
        return getType(null).requiredFeatures();
    }
}
*///?}