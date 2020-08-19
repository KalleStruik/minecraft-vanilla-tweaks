package nl.kallestruik.vanillatweaks.tweaks.craftingtweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class AlternativeDispenserRecipe implements Tweak {
    private JavaPlugin plugin;

    @Override
    public String getIdentifier() {
        return "AlternativeDispenserRecipe";
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
        NamespacedKey dispenserKey = new NamespacedKey(plugin, "dispenser");
        ShapedRecipe dispenserRecipe = new ShapedRecipe(dispenserKey, new ItemStack(Material.DISPENSER));
        dispenserRecipe.shape(" TS","TDS"," TS");
        dispenserRecipe.setIngredient('T', Material.STICK);
        dispenserRecipe.setIngredient('D', Material.DROPPER);
        dispenserRecipe.setIngredient('S', Material.STRING);
    }

    @Override
    public void onDisable() {

    }
}
