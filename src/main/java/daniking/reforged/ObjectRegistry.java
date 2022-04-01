package daniking.reforged;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ObjectRegistry {

    public static final Item IRON_BATTLE_AXE = create("iron_battle_axe", new Item(settings()));

    static FabricItemSettings settings() {
        return new FabricItemSettings();
    }

    static <T extends Item> T create(final String id, T item) {
        final Identifier resource = new Identifier(Reforged.ID, id);
        Registry.register(Registry.ITEM, resource, item);
        System.out.println("New item created!");
        return item;
    }

    public static void register() {

    }
}
