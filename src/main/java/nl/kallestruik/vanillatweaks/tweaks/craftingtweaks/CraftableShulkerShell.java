package nl.kallestruik.vanillatweaks.tweaks.craftingtweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftableShulkerShell implements Tweak {
    private JavaPlugin plugin;

    @Override
    public String getIdentifier() {
        return "CraftableShulkerShell";
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
        NamespacedKey shulkerShellKey = new NamespacedKey(plugin, "shulker_shell");
        ShapedRecipe shulkerShellRecipe = new ShapedRecipe(shulkerShellKey, new ItemStack(Material.SHULKER_SHELL));
        shulkerShellRecipe.shape("BBB","F F");
        shulkerShellRecipe.setIngredient('B', Material.PURPUR_SLAB);
        shulkerShellRecipe.setIngredient('F', Material.POPPED_CHORUS_FRUIT);
    }

    @Override
    public void onDisable() {

    }
}
