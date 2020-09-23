package nl.kallestruik.vanillatweaks.tweaks.craftingtweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class IceDecompression implements Tweak {
    private JavaPlugin plugin;

    @Override
    public String getIdentifier() {
        return "IceDecompression";
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
        // Blue Ice -> Packed Ice
        NamespacedKey packedIceKey = new NamespacedKey(plugin, "packed_ice");
        ShapelessRecipe packedIceRecipe = new ShapelessRecipe(packedIceKey, new ItemStack(Material.PACKED_ICE, 9));
        packedIceRecipe.addIngredient(Material.BLUE_ICE);

        Bukkit.getServer().addRecipe(packedIceRecipe);

        // Packed Ice -> Ice
        NamespacedKey iceKey = new NamespacedKey(plugin, "ice");
        ShapelessRecipe iceRecipe = new ShapelessRecipe(iceKey, new ItemStack(Material.ICE, 9));
        iceRecipe.addIngredient(Material.PACKED_ICE);

        Bukkit.getServer().addRecipe(iceRecipe);
    }

    @Override
    public void onDisable() {

    }
}
