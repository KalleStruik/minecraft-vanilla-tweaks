package nl.kallestruik.vanillatweaks.SeedDropPlanting;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;


public class SeedDropPlanting {

    public static void init(JavaPlugin plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (World world : plugin.getServer().getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity instanceof Item) {
                        Item item = ((Item) entity);
                        Block b = world.getBlockAt(item.getLocation());
                        Block above = world.getBlockAt(item.getLocation().add(0, 1, 0));
                        if (b.getType() == Material.FARMLAND && above.getType() == Material.AIR) {
                            switch (item.getItemStack().getType()) {
                                case WHEAT_SEEDS:
                                    above.setType(Material.WHEAT);
                                    world.spawnParticle(Particle.VILLAGER_HAPPY, item.getLocation(), 10);
                                    entity.remove();
                                    break;
                                case BEETROOT_SEEDS:
                                    above.setType(Material.BEETROOTS);
                                    world.spawnParticle(Particle.VILLAGER_HAPPY, item.getLocation(), 10);
                                    entity.remove();
                                    break;
                                case MELON_SEEDS:
                                    above.setType(Material.MELON_STEM);
                                    world.spawnParticle(Particle.VILLAGER_HAPPY, item.getLocation(), 10);
                                    entity.remove();
                                    break;
                                case PUMPKIN_SEEDS:
                                    above.setType(Material.PUMPKIN_STEM);
                                    world.spawnParticle(Particle.VILLAGER_HAPPY, item.getLocation(), 10);
                                    entity.remove();
                                    break;
                                case POTATO:
                                    above.setType(Material.POTATOES);
                                    world.spawnParticle(Particle.VILLAGER_HAPPY, item.getLocation(), 10);
                                    entity.remove();
                                    break;
                                case CARROT:
                                    above.setType(Material.CARROTS);
                                    world.spawnParticle(Particle.VILLAGER_HAPPY, item.getLocation(), 10);
                                    entity.remove();
                                    break;
                            }
                        }

                    }
                }
            }
        }, 20 * 10, 20 * 10);
    }
}
