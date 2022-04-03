package daniking.reforged;

import daniking.reforged.item.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ObjectRegistry {

    // Fire Rod
    public static final Item FIRE_ROD = create("fire_rod", new FireRodItem(settings()));
    // Battle Axe
    public static final Item WOODEN_BATTLE_AXE = create("wooden_battle_axe", new BattleAxeItem(ToolMaterials.WOOD, 6.0F, settings()));
    public static final Item STONE_BATTLE_AXE = create("stone_battle_axe", new BattleAxeItem(ToolMaterials.STONE, 3.5F, settings()));
    public static final Item GOLDEN_BATTLE_AXE = create("golden_battle_axe", new BattleAxeItem(ToolMaterials.GOLD, 2.0F, settings()));
    public static final Item IRON_BATTLE_AXE = create("iron_battle_axe", new BattleAxeItem(ToolMaterials.IRON, 5.0F, settings()));
    public static final Item DIAMOND_BATTLE_AXE = create("diamond_battle_axe", new BattleAxeItem(ToolMaterials.DIAMOND, 6.0F, settings()));
    // Knife
    public static final Item WOODEN_KNIFE = create("wooden_knife", new KnifeItem(ToolMaterials.WOOD, 3, -2.4F, settings()));
    public static final Item STONE_KNIFE = create("stone_knife", new KnifeItem(ToolMaterials.STONE, 3, -2.4F, settings()));
    public static final Item GOLDEN_KNIFE = create("golden_knife", new KnifeItem(ToolMaterials.GOLD, 3, -2.4F, settings()));
    public static final Item IRON_KNIFE = create("iron_knife", new KnifeItem(ToolMaterials.IRON, 3, -2.4F, settings()));
    public static final Item DIAMOND_KNIFE = create("diamond_knife", new KnifeItem(ToolMaterials.DIAMOND, 3, -2.4F, settings()));
    // Javelin
    public static final Item JAVELIN = create("javelin", new JavelinItem(settings().maxCount(16).maxDamage(32)));
    // Keris
    public static final Item KERIS_ITEM = create("keris", new KerisItem(ToolMaterials.GOLD, 3, -2.4F, settings()));
    // Dynamite
    public static final Item DYNAMITE = create("dynamite", new DynamiteItem(settings()));

    static FabricItemSettings settings() {
        return new FabricItemSettings().group(Reforged.CREATIVE_TAB);
    }

    static <T extends Item> T create(final String id, final T item) {
        final Identifier resource = new Identifier(Reforged.ID, id);
        Registry.register(Registry.ITEM, resource, item);
        return item;
    }

    public static void register() {

    }
}
