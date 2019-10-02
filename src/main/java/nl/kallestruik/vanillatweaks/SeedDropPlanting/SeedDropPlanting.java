package nl.kallestruik.vanillatweaks.SeedDropPlanting;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;


public class SeedDropPlanting {

    public static void init(JavaPlugin plugin) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            for (World world : plugin.getServer().getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity instanceof Item) {
                        Item item = ((Item) entity);
                        Block b = world.getBlockAt(item.getLocation());
                        Block below = world.getBlockAt(item.getLocation().add(0, -1, 0));
                        if (b.getType() == Material.AIR && below.getType() == Material.FARMLAND) {
                            switch (item.getItemStack().getType()) {
                                case WHEAT_SEEDS:
                                    b.setType(Material.WHEAT);
                                    world.spawnParticle(Particle.VILLAGER_HAPPY, item.getLocation(), 10);
                                    break;
                                case BEETROOT_SEEDS:
                                    b.setType(Material.BEETROOT);
                                    world.spawnParticle(Particle.VILLAGER_HAPPY, item.getLocation(), 10);
                                    break;
                                case MELON_SEEDS:
                                    b.setType(Material.MELON_STEM);
                                    world.spawnParticle(Particle.VILLAGER_HAPPY, item.getLocation(), 10);
                                    break;
                                case PUMPKIN_SEEDS:
                                    b.setType(Material.PUMPKIN_STEM);
                                    world.spawnParticle(Particle.VILLAGER_HAPPY, item.getLocation(), 10);
                                    break;
                                case POTATO:
                                    b.setType(Material.POTATOES);
                                    world.spawnParticle(Particle.VILLAGER_HAPPY, item.getLocation(), 10);
                                    break;
                                case CARROT:
                                    b.setType(Material.CARROTS);
                                    world.spawnParticle(Particle.VILLAGER_HAPPY, item.getLocation(), 10);
                                    break;
                            }
                        }

                    }
                }
            }
        }, 20 * 10, 20 * 10);
    }
}
