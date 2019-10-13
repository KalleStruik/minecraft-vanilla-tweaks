package nl.kallestruik.vanillatweaks.CraftingTweaks;

import nl.kallestruik.vanillatweaks.config;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftingTweaks {

    public static void init(JavaPlugin plugin) {
        RecipeChoice woodLog = new RecipeChoice.MaterialChoice(Material.OAK_LOG, Material.BIRCH_LOG, Material.SPRUCE_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG);
        RecipeChoice allWool = new RecipeChoice.MaterialChoice(Material.WHITE_WOOL, Material.BLACK_WOOL, Material.BLUE_WOOL, Material.BROWN_WOOL, Material.CYAN_WOOL, Material.GRAY_WOOL, Material.GREEN_WOOL, Material.LIGHT_BLUE_WOOL, Material.LIGHT_GRAY_WOOL, Material.LIME_WOOL, Material.MAGENTA_WOOL, Material.ORANGE_WOOL, Material.PINK_WOOL, Material.PURPLE_WOOL, Material.RED_WOOL, Material.YELLOW_WOOL);

        // Better chest recipe
        NamespacedKey chestKey = new NamespacedKey(plugin, "chest");
        ShapedRecipe chestRecipe = new ShapedRecipe(chestKey, new ItemStack(Material.CHEST, 4));
        chestRecipe.shape("WWW", "W W", "WWW");
        chestRecipe.setIngredient('W', woodLog);

        // Name tag recipe
        NamespacedKey nametagKey = new NamespacedKey(plugin, "nametag");
        ShapedRecipe nametagRecipe = new ShapedRecipe(nametagKey, new ItemStack(Material.NAME_TAG));
        nametagRecipe.shape("  I", " P ", "P  ");
        nametagRecipe.setIngredient('I', Material.IRON_INGOT);
        nametagRecipe.setIngredient('P', Material.PAPER);

        // Wool > String recipe
        NamespacedKey stringKey = new NamespacedKey(plugin, "string");
        ShapelessRecipe stringRecipe = new ShapelessRecipe(stringKey, new ItemStack(Material.STRING, 4));
        stringRecipe.addIngredient(allWool);

        // Saddle
        NamespacedKey saddleKey = new NamespacedKey(plugin, "saddle");
        ShapedRecipe saddleRecipe = new ShapedRecipe(saddleKey, new ItemStack(Material.SADDLE));
        saddleRecipe.shape("LLL", "S S", "I I");
        saddleRecipe.setIngredient('L', Material.LEATHER);
        saddleRecipe.setIngredient('S', Material.STRING);
        saddleRecipe.setIngredient('I', Material.IRON_INGOT);

        // Blue ice -> 9 packed ice
        NamespacedKey packedIceKey = new NamespacedKey(plugin, "packed_ice");
        ShapelessRecipe packedIceRecipe = new ShapelessRecipe(packedIceKey, new ItemStack(Material.PACKED_ICE, 9));
        packedIceRecipe.addIngredient(Material.BLUE_ICE);

        // Packed ice -> 9 ice
        NamespacedKey iceKey = new NamespacedKey(plugin, "ice");
        ShapelessRecipe iceRecipe = new ShapelessRecipe(iceKey, new ItemStack(Material.ICE, 9));
        iceRecipe.addIngredient(Material.PACKED_ICE);

        // Dragons breath
        NamespacedKey dragonBreathKey = new NamespacedKey(plugin, "dragon_breath");
        ShapedRecipe dragonBreathRecipe = new ShapedRecipe(dragonBreathKey, new ItemStack(Material.DRAGON_BREATH, 3));
        dragonBreathRecipe.shape("GCG", " G ");
        dragonBreathRecipe.setIngredient('G', Material.GLASS);
        dragonBreathRecipe.setIngredient('C', Material.POPPED_CHORUS_FRUIT);

        // Sponge Block
        NamespacedKey spongeKey = new NamespacedKey(plugin, "sponge");
        ShapedRecipe spongeRecipe = new ShapedRecipe(spongeKey, new ItemStack(Material.SPONGE));
        spongeRecipe.shape("KKK","KDK","KKK");
        spongeRecipe.setIngredient('K', Material.KELP);
        spongeRecipe.setIngredient('D', Material.YELLOW_DYE);

        // Dispenser Alternative Recipe
        NamespacedKey dispenserKey = new NamespacedKey(plugin, "dispenser");
        ShapedRecipe dispenserRecipe = new ShapedRecipe(dispenserKey, new ItemStack(Material.DISPENSER));
        dispenserRecipe.shape(" TS","TDS"," TS");
        dispenserRecipe.setIngredient('T', Material.STICK);
        dispenserRecipe.setIngredient('D', Material.DROPPER);
        dispenserRecipe.setIngredient('S', Material.STRING);



        // Add recipes
        if (config.CRAFTING_TWEAKS_BETTER_CHEST) plugin.getServer().addRecipe(chestRecipe);
        if (config.CRAFTING_TWEAKS_NAME_TAG) plugin.getServer().addRecipe(nametagRecipe);
        if (config.CRAFTING_TWEAKS_WOOL_TO_STRING) plugin.getServer().addRecipe(stringRecipe);
        if (config.CRAFTING_TWEAKS_SADDLE) plugin.getServer().addRecipe(saddleRecipe);
        if (config.CRAFTING_TWEAKS_PACKED_ICE) plugin.getServer().addRecipe(packedIceRecipe);
        if (config.CRAFTING_TWEAKS_ICE) plugin.getServer().addRecipe(iceRecipe);
        if (config.CRAFTING_TWEAKS_DRAGONS_BREATH) plugin.getServer().addRecipe(dragonBreathRecipe);
        if (config.CRAFTING_TWEAKS_SPONGE) plugin.getServer().addRecipe(spongeRecipe);
        if (config.CRAFTING_TWEAKS_DISPENSER) plugin.getServer().addRecipe((dispenserRecipe));
    }
}
