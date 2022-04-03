package daniking.reforged.item;

import com.google.common.collect.Multimap;
import daniking.reforged.ItemStackModifier;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class FireRodItem extends Item implements ItemStackModifier {

    public static final int FIRE_DURATION_IN_SECONDS = 10;

    public FireRodItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.isFireImmune()) {
            return false;
        }
        if (target instanceof CreeperEntity creeper) {
            creeper.ignite();
        } else {
            target.setOnFireFor(FIRE_DURATION_IN_SECONDS);
        }
        if (attacker instanceof PlayerEntity player) {
            if (!player.isCreative()) {
                stack.decrement(1);
            }
        } else {
            stack.decrement(1);
        }
        return true;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getHand() == Hand.MAIN_HAND) {
            final World world = context.getWorld();
            final BlockPos originPos = context.getBlockPos();
            final BlockPos target = originPos.offset(context.getSide());
            final Block block = world.getBlockState(originPos).getBlock();
            if (block == Blocks.FIRE) {
                return ActionResult.FAIL;
            }
            if (world.isAir(target)) {
                final PlayerEntity player = Objects.requireNonNull(context.getPlayer());
                world.setBlockState(target, Blocks.FIRE.getDefaultState());
                if (!player.isCreative()) {
                    context.getStack().decrement(1);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void getAttributeModifiers(EquipmentSlot slot, ItemStack stack, Multimap<EntityAttribute, EntityAttributeModifier> builder) {
        if (slot == EquipmentSlot.MAINHAND) {
            builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon Modifier", 1.5, EntityAttributeModifier.Operation.ADDITION));
            builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon Modifier", -3.0, EntityAttributeModifier.Operation.ADDITION));
        }
    }
}
