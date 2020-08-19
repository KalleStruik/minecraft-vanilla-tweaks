package nl.kallestruik.vanillatweaks.tweaks.dispensertweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class DispensersCanPlantSaplings implements Tweak, Listener {
    private JavaPlugin plugin;
    private static List<Material> saplings = new ArrayList<>();
    private static List<Material> treePlantable = new ArrayList<>();

    public DispensersCanPlantSaplings() {
        saplings.add(Material.OAK_SAPLING);
        saplings.add(Material.BIRCH_SAPLING);
        saplings.add(Material.JUNGLE_SAPLING);
        saplings.add(Material.SPRUCE_SAPLING);
        saplings.add(Material.DARK_OAK_SAPLING);
        saplings.add(Material.ACACIA_SAPLING);

        treePlantable.add(Material.DIRT);
        treePlantable.add(Material.GRASS_BLOCK);
        treePlantable.add(Material.COARSE_DIRT);
        treePlantable.add(Material.PODZOL);
    }

    @Override
    public String getIdentifier() {
        return "DispensersCanPlantSaplings";
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
    public void onDispenserDispense(BlockDispenseEvent event) {
        if (event.getBlock().getType() != Material.DISPENSER)
            return;

        if (saplings.contains(event.getItem().getType())) {
            Dispenser dispenser = ((Dispenser) event.getBlock().getBlockData());
            BlockFace facing = dispenser.getFacing();
            Block targetBlock;
            if (facing == BlockFace.UP) {
                targetBlock = event.getBlock().getWorld().getBlockAt(event.getBlock().getLocation().add(0, 2, 0));
            } else {
                targetBlock = event.getBlock().getWorld().getBlockAt(event.getBlock().getLocation().add(facing.getModX(), facing.getModY(), facing.getModZ()));
            }
            if (targetBlock.getType() == Material.AIR
                    && treePlantable.contains(event.getBlock().getWorld().getBlockAt(targetBlock.getLocation().add(0, -1, 0)).getType())) {
                targetBlock.setType(event.getItem().getType());

                event.setCancelled(true);
                Inventory dispenserInventory = ((org.bukkit.block.Dispenser) event.getBlock().getState()).getInventory();
                int slot = 0;
                for (ItemStack is : dispenserInventory.getContents()) {
                    if (is != null && is.getType() == event.getItem().getType())
                        break;
                    slot++;
                }
                ItemStack newItemStack = dispenserInventory.getItem(slot);
                newItemStack.setAmount(newItemStack.getAmount() - 1);
                dispenserInventory.setItem(slot, newItemStack);

                event.getBlock().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, targetBlock.getLocation(), 5);
            }
        }
    }
}
