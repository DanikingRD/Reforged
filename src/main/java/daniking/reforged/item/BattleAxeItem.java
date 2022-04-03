package daniking.reforged.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;

public class BattleAxeItem extends AxeItem {

    public BattleAxeItem(ToolMaterial material, float attackDamage, Settings settings) {
        super(material, attackDamage, -3.0F, settings);
    }
}
