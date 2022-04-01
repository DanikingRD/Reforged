package daniking.reforged;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Reforged implements ModInitializer {

    public static final String NAME = "Reforged";
    public static final String ID = "reforged";
    public static final String VERSION;
    public static final Logger LOGGER = LoggerFactory.getLogger(Reforged.class);
    public static final ItemGroup CREATIVE_TAB = FabricItemGroupBuilder.build(new Identifier(ID, "tab"), () -> new ItemStack(ObjectRegistry.IRON_BATTLE_AXE));

    static {
        final ModMetadata mod = FabricLoader.getInstance()
                .getModContainer(ID)
                .orElseThrow(() -> new RuntimeException(NAME + " is not loaded, cannot continue"))
                .getMetadata();
        VERSION = mod.getVersion().toString();
        LOGGER.info("Loading {} version {}", NAME, VERSION);
    }

    @Override
    public void onInitialize() {
        ObjectRegistry.register();
        LOGGER.info("{} V{} Initialized", NAME, VERSION);
    }

}
