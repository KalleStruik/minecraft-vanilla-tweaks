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
                                    plantSeed(item, world, above, Material.WHEAT);
                                    break;
                                case BEETROOT_SEEDS:
                                    plantSeed(item, world, above, Material.BEETROOTS);
                                    break;
                                case MELON_SEEDS:
                                    plantSeed(item, world, above, Material.MELON_STEM);
                                    break;
                                case PUMPKIN_SEEDS:
                                    plantSeed(item, world, above, Material.PUMPKIN_STEM);
                                    break;
                                case POTATO:
                                    plantSeed(item, world, above, Material.POTATOES);
                                    break;
                                case CARROT:
                                    plantSeed(item, world, above, Material.CARROTS);
                                    break;
                            }
                        }

                    }
                }
            }
        }, 20 * 10, 20 * 10);
    }

    private static void plantSeed(Item entity, World world, Block block, Material material) {
        block.setType(material);
        world.spawnParticle(Particle.VILLAGER_HAPPY, entity.getLocation(), 10);
        if (entity.getItemStack().getAmount() == 1) {
            entity.remove();
        } else {
            entity.getItemStack().setAmount(entity.getItemStack().getAmount() - 1);
        }
    }
}
