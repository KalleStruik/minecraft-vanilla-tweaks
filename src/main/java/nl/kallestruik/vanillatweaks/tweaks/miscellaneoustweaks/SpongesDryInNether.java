package nl.kallestruik.vanillatweaks.tweaks.miscellaneoustweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SpongesDryInNether implements Tweak, Listener {
    private JavaPlugin plugin;

    @Override
    public String getIdentifier() {
        return "SpongesDryInNether";
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
    public void onSpongePlaced(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        if (block.getType() == Material.WET_SPONGE &&
                block.getWorld().getEnvironment() == World.Environment.NETHER) {
            block.setType(Material.SPONGE);
            block.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, block.getLocation(), 3);
            block.getWorld().playEffect(block.getLocation(), Effect.EXTINGUISH, 1, 10);
        }
    }
}
