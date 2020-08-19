package nl.kallestruik.vanillatweaks.tweaks.craftingtweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftableNametag implements Tweak {
    private JavaPlugin plugin;

    @Override
    public String getIdentifier() {
        return "CraftableNametag";
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
        NamespacedKey nametagKey = new NamespacedKey(plugin, "nametag");
        ShapedRecipe nametagRecipe = new ShapedRecipe(nametagKey, new ItemStack(Material.NAME_TAG));
        nametagRecipe.shape("  I", " P ", "P  ");
        nametagRecipe.setIngredient('I', Material.IRON_INGOT);
        nametagRecipe.setIngredient('P', Material.PAPER);
    }

    @Override
    public void onDisable() {

    }
}
