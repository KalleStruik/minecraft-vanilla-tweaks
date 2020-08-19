package nl.kallestruik.vanillatweaks.tweaks.craftingtweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftableDragonsBreath implements Tweak {
    private JavaPlugin plugin;

    @Override
    public String getIdentifier() {
        return "CraftableDragonsBreath";
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
        NamespacedKey dragonBreathKey = new NamespacedKey(plugin, "dragon_breath");
        ShapedRecipe dragonBreathRecipe = new ShapedRecipe(dragonBreathKey, new ItemStack(Material.DRAGON_BREATH, 3));
        dragonBreathRecipe.shape("GCG", " G ");
        dragonBreathRecipe.setIngredient('G', Material.GLASS);
        dragonBreathRecipe.setIngredient('C', Material.POPPED_CHORUS_FRUIT);
    }

    @Override
    public void onDisable() {

    }
}
