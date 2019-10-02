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

        // Add recipes
        if (config.CRAFTING_TWEAKS_BETTER_CHEST) plugin.getServer().addRecipe(chestRecipe);
        if (config.CRAFTING_TWEAKS_NAME_TAG) plugin.getServer().addRecipe(nametagRecipe);
        if (config.CRAFTING_TWEAKS_WOOL_TO_STRING) plugin.getServer().addRecipe(stringRecipe);
        if (config.CRAFTING_TWEAKS_SADDLE) plugin.getServer().addRecipe(saddleRecipe);
    }
}
