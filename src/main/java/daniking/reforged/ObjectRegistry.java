package daniking.reforged;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ObjectRegistry {

    public static final Item WOODEN_BATTLE_AXE = create("wooden_battle_axe", new BattleAxeItem(ToolMaterials.WOOD, 6.0F, settings()));
    public static final Item STONE_BATTLE_AXE = create("stone_battle_axe", new BattleAxeItem(ToolMaterials.STONE, 3.5F, settings()));
    public static final Item GOLDEN_BATTLE_AXE = create("golden_battle_axe", new BattleAxeItem(ToolMaterials.GOLD, 2.0F, settings()));
    public static final Item IRON_BATTLE_AXE = create("iron_battle_axe", new BattleAxeItem(ToolMaterials.IRON, 5.0F, settings()));
    public static final Item DIAMOND_BATTLE_AXE = create("diamond_battle_axe", new BattleAxeItem(ToolMaterials.DIAMOND, 6.0F, settings()));

    static FabricItemSettings settings() {
        return new FabricItemSettings().group(Reforged.CREATIVE_TAB);
    }

    static <T extends Item> T create(final String id, T item) {
        final Identifier resource = new Identifier(Reforged.ID, id);
        Registry.register(Registry.ITEM, resource, item);
        return item;
    }

    public static void register() {

    }
}
