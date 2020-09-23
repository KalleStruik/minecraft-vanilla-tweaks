package nl.kallestruik.vanillatweaks.tweaks.craftingtweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class WoolToString implements Tweak {
    private JavaPlugin plugin;

    @Override
    public String getIdentifier() {
        return "WoolToString";
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
        NamespacedKey stringKey = new NamespacedKey(plugin, "string");
        ShapelessRecipe stringRecipe = new ShapelessRecipe(stringKey, new ItemStack(Material.STRING, 4));
        stringRecipe.addIngredient(MaterialGroups.ALL_WOOL);

        Bukkit.getServer().addRecipe(stringRecipe);
    }

    @Override
    public void onDisable() {

    }
}
