package nl.kallestruik.vanillatweaks.tweaks.craftingtweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class LogsToChests implements Tweak {
    private JavaPlugin plugin;

    @Override
    public String getIdentifier() {
        return "LogsToChests";
    }

    @Override
    public void onRegister(JavaPlugin pluginInstance) {
        this.plugin = pluginInstance;

    }

    @Override
    public void onUnRegister() {

    }

    @Override
    public void onEnable() {
        NamespacedKey chestKey = new NamespacedKey(plugin, "chest");
        ShapedRecipe chestRecipe = new ShapedRecipe(chestKey, new ItemStack(Material.CHEST, 4));
        chestRecipe.shape("WWW", "W W", "WWW");
        chestRecipe.setIngredient('W', MaterialGroups.ALL_LOG);
    }

    @Override
    public void onDisable() {

    }
}
