package nl.kallestruik.vanillatweaks.tweaks.craftingtweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftableSponge implements Tweak {
    private JavaPlugin plugin;

    @Override
    public String getIdentifier() {
        return "CraftableSponge";
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
        NamespacedKey spongeKey = new NamespacedKey(plugin, "sponge");
        ShapedRecipe spongeRecipe = new ShapedRecipe(spongeKey, new ItemStack(Material.SPONGE));
        spongeRecipe.shape("KKK","KDK","KKK");
        spongeRecipe.setIngredient('K', Material.KELP);
        spongeRecipe.setIngredient('D', Material.YELLOW_DYE);

        Bukkit.getServer().addRecipe(spongeRecipe);
    }

    @Override
    public void onDisable() {

    }
}
