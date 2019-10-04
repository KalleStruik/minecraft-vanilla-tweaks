package nl.kallestruik.vanillatweaks.HoeHarvesting;

import nl.kallestruik.vanillatweaks.config;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class HoeHarvestingHandler implements Listener {

    @EventHandler
    public void onHoeBreaksBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        World world = block.getWorld();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        int range;

        if (itemInHand.getType() == Material.WOODEN_HOE)
            range = config.HOE_HARVESTING_RANGE_WOOD;
        else if (itemInHand.getType() == Material.STONE_HOE)
            range = config.HOE_HARVESTING_RANGE_STONE;
        else if (itemInHand.getType() == Material.IRON_HOE)
            range = config.HOE_HARVESTING_RANGE_IRON;
        else if (itemInHand.getType() == Material.GOLDEN_HOE)
            range = config.HOE_HARVESTING_RANGE_GOLD;
        else if (itemInHand.getType() == Material.DIAMOND_HOE)
            range = config.HOE_HARVESTING_RANGE_DIAMOND;
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