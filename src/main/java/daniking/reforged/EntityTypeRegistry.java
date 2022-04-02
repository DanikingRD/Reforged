package daniking.reforged;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class EntityTypeRegistry {

    public static final EntityType<DynamiteEntity> DYNAMITE_ENTITY_TYPE = create("dynamite", FabricEntityTypeBuilder.<DynamiteEntity>create(SpawnGroup.MISC, DynamiteEntity::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    static <T extends EntityType<?>> T create (final String id, final T type) {
        final Identifier resource = new Identifier(Reforged.ID, id);
        Registry.register(Registry.ENTITY_TYPE, resource, type);
        return type;
    }

    public static void register() {

    }
}
