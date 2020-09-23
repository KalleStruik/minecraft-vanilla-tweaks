package nl.kallestruik.vanillatweaks.tweaks.croptweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class HoesHarvestArea implements Tweak, Listener {
    private JavaPlugin plugin;

    @Override
    public String getIdentifier() {
        return "HoesHarvestArea";
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
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void onHoeBreaksBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        World world = block.getWorld();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        int range;

        if (itemInHand.getType() == Material.WOODEN_HOE)
            range = 1;
        else if (itemInHand.getType() == Material.STONE_HOE)
            range = 1;
        else if (itemInHand.getType() == Material.IRON_HOE)
            range = 1;
        else if (itemInHand.getType() == Material.GOLDEN_HOE)
            range = 1;
        else if (itemInHand.getType() == Material.DIAMOND_HOE)
            range = 2;
        else if (itemInHand.getType() == Material.NETHERITE_HOE)
            range = 2;
        else
            return;


        if (!isCrop(block.getType()))
            return;

        Location startLocation = block.getLocation();

        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                Block b = world.getBlockAt(startLocation.clone().add(x, 0, z));
                if (isCrop(b.getType())) {
                    Ageable ageable = (Ageable) b.getBlockData();
                    if (ageable.getAge() == ageable.getMaximumAge()) {
                        b.breakNaturally();
                    }
                }
            }
        }

    }


    private static boolean isCrop(Material material) {
        return material == Material.WHEAT ||
                material == Material.BEETROOTS ||
                material == Material.CARROTS ||
                material == Material.POTATOES;

    }
}