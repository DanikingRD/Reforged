package daniking.reforged.entity;

import daniking.reforged.EntityTypeRegistry;
import daniking.reforged.ObjectRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class JavelinEntity extends PersistentProjectileEntity {

    private ItemStack javelinStack = new ItemStack(ObjectRegistry.JAVELIN);

    public JavelinEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public JavelinEntity(LivingEntity owner, World world, ItemStack stack) {
        super(EntityTypeRegistry.JAVELIN_ENTITY_TYPE, owner, world);
        this.javelinStack = stack.copy();
    }

    public ItemStack getJavelinStack() {
        return javelinStack;
    }

    @Override
    protected ItemStack asItemStack() {
        return this.javelinStack.copy();
    }
}
