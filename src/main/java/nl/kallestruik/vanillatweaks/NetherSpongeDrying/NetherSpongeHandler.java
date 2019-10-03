package nl.kallestruik.vanillatweaks.NetherSpongeDrying;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class NetherSpongeHandler implements Listener {

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
