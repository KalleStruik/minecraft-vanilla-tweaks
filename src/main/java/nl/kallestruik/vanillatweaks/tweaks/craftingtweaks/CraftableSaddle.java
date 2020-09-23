package nl.kallestruik.vanillatweaks.tweaks.craftingtweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftableSaddle implements Tweak {
    private JavaPlugin plugin;

    @Override
    public String getIdentifier() {
        return "CraftableSaddle";
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
        NamespacedKey saddleKey = new NamespacedKey(plugin, "saddle");
        ShapedRecipe saddleRecipe = new ShapedRecipe(saddleKey, new ItemStack(Material.SADDLE));
        saddleRecipe.shape("LLL", "S S", "I I");
        saddleRecipe.setIngredient('L', Material.LEATHER);
        saddleRecipe.setIngredient('S', Material.STRING);
        saddleRecipe.setIngredient('I', Material.IRON_INGOT);

        Bukkit.getServer().addRecipe(saddleRecipe);
    }

    @Override
    public void onDisable() {

    }
}
