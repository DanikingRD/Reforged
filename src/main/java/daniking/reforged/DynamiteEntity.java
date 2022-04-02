package daniking.reforged;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

public class DynamiteEntity extends ThrownItemEntity {

    public DynamiteEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public DynamiteEntity(LivingEntity livingEntity, World world) {
        super(EntityTypeRegistry.DYNAMITE_ENTITY_TYPE, livingEntity, world);
    }

    protected Item getDefaultItem() {
        return ObjectRegistry.DYNAMITE;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        final Explosion explosion = super.getWorld().createExplosion(this, DamageSource.explosion((Explosion) null), new ExplosionBehavior(), getX(), getY(), getZ(), 2.0F, false, Explosion.DestructionType.BREAK);
        if (explosion != null) {
            discard();
        }
    }
}
