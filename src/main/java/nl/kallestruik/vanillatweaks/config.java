package nl.kallestruik.vanillatweaks;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class config {

    public static boolean TOGGLE_TRAMPLE_ENABLED;

    public static boolean CRAFTING_TWEAKS_ENABLED;
    public static boolean CRAFTING_TWEAKS_BETTER_CHEST;
    public static boolean CRAFTING_TWEAKS_NAME_TAG;
    public static boolean CRAFTING_TWEAKS_WOOL_TO_STRING;
    public static boolean CRAFTING_TWEAKS_SADDLE;
    public static boolean CRAFTING_TWEAKS_PACKED_ICE;
    public static boolean CRAFTING_TWEAKS_ICE;
    public static boolean CRAFTING_TWEAKS_DRAGONS_BREATH;

    public static boolean SEED_DROP_PLANTING_ENABLED;

    public static boolean ARMOR_STAND_SWAPPING_ENABLED;

    public static boolean NETHER_SPONGE_DRYING_ENABLED;

    public static boolean HOE_HARVESTING_ENABLED;
    public static int HOE_HARVESTING_RANGE_WOOD;
    public static int HOE_HARVESTING_RANGE_STONE;
    public static int HOE_HARVESTING_RANGE_IRON;
    public static int HOE_HARVESTING_RANGE_GOLD;
    public static int HOE_HARVESTING_RANGE_DIAMOND;

    public static void load(File file) throws IOException, InvalidConfigurationException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (!file.exists())
            util.ExportResource(c.CONFIG_FILE_NAME, file);
        YamlConfiguration config = new YamlConfiguration();
        config.load(file);

        TOGGLE_TRAMPLE_ENABLED = config.getBoolean("toggle-trample.enabled");

        CRAFTING_TWEAKS_ENABLED = config.getBoolean("crafting-tweaks.enabled");
        CRAFTING_TWEAKS_BETTER_CHEST = config.getBoolean("crafting-tweaks.better-chest");
        CRAFTING_TWEAKS_NAME_TAG = config.getBoolean("crafting-tweaks.name-tag");
        CRAFTING_TWEAKS_WOOL_TO_STRING = config.getBoolean("crafting-tweaks.wool-to-string");
        CRAFTING_TWEAKS_SADDLE = config.getBoolean("crafting-tweaks.saddle");
        CRAFTING_TWEAKS_PACKED_ICE = config.getBoolean("crafting-tweaks.packed-ice");
        CRAFTING_TWEAKS_ICE = config.getBoolean("crafting-tweaks.ice");
        CRAFTING_TWEAKS_DRAGONS_BREATH = config.getBoolean("crafting-tweaks.dragons-breath");

        SEED_DROP_PLANTING_ENABLED = config.getBoolean("seed-drop-planting.enabled");

        ARMOR_STAND_SWAPPING_ENABLED = config.getBoolean("armor-stand-swapping.enabled");

        NETHER_SPONGE_DRYING_ENABLED = config.getBoolean("nether-sponge-drying.enabled");

        HOE_HARVESTING_ENABLED = config.getBoolean("hoe-harvesting.enabled");
        HOE_HARVESTING_RANGE_WOOD = config.getInt("hoe-harvesting.ranges.wood");
        HOE_HARVESTING_RANGE_STONE = config.getInt("hoe-harvesting.ranges.stone");
        HOE_HARVESTING_RANGE_IRON = config.getInt("hoe-harvesting.ranges.iron");
        HOE_HARVESTING_RANGE_GOLD = config.getInt("hoe-harvesting.ranges.gold");
        HOE_HARVESTING_RANGE_DIAMOND = config.getInt("hoe-harvesting.ranges.diamond");


    }
}
