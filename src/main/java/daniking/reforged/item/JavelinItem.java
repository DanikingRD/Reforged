package daniking.reforged.item;

import com.google.common.collect.Multimap;
import daniking.reforged.ItemStackModifier;
import daniking.reforged.entity.JavelinEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class JavelinItem extends Item implements ItemStackModifier {

    public JavelinItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        boolean bl;
        final ItemStack stack = user.getStackInHand(hand);
        boolean bl2 = bl = !user.getArrowType(stack).isEmpty();
        if (user.getAbilities().creativeMode || bl) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(stack);
        }
        return TypedActionResult.fail(stack);
    }


    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        return stack;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient()) {
            final JavelinEntity entity = new JavelinEntity(user, world, stack);
            entity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 0.0F);
            world.spawnEntity(entity);
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 7200;
    }

    @Override
    public void getAttributeModifiers(EquipmentSlot slot, ItemStack stack, Multimap<EntityAttribute, EntityAttributeModifier> builder) {
        if (slot == EquipmentSlot.MAINHAND) {
            builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon Modifier", 3.0, EntityAttributeModifier.Operation.ADDITION));
            builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon Modifier", -3.0, EntityAttributeModifier.Operation.ADDITION));
        }
    }
}
