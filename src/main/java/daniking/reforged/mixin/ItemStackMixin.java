package daniking.reforged.mixin;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import daniking.reforged.ItemStackModifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract Item getItem();

    @Inject(method = "getAttributeModifiers", at = @At("RETURN"), cancellable = true)
    private void getAttributeModifiers(EquipmentSlot equipmentSlot, CallbackInfoReturnable<Multimap<EntityAttribute, EntityAttributeModifier>> info) {
        if (this.getItem() instanceof ItemStackModifier modifier) {
            final Multimap<EntityAttribute, EntityAttributeModifier> multimap = ArrayListMultimap.create(info.getReturnValue());
            modifier.getAttributeModifiers(equipmentSlot, (ItemStack) (Object) this, multimap);
            info.setReturnValue(ImmutableMultimap.copyOf(multimap));
        }
    }
}
