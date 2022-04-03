package daniking.reforged.item;

import daniking.reforged.entity.DynamiteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DynamiteItem extends Item {

    public DynamiteItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        final ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient()) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            if (!user.isCreative()) {
                stack.decrement(1);
            }
            final DynamiteEntity entity = new DynamiteEntity(user, world);
            entity.setItem(stack);
            entity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 0.0F);
            world.spawnEntity(entity);
        }
        return TypedActionResult.success(stack, world.isClient());
    }
}
