package nl.kallestruik.vanillatweaks.LilypadGrowing;

import nl.kallestruik.vanillatweaks.util;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class LilypadGrowingHandler implements Listener {

    @EventHandler
    public void onRightClickBlock(PlayerInteractEvent event) {
        Player player  = event.getPlayer();
        if (event.getHand() != EquipmentSlot.HAND)
            return;

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if (event.getClickedBlock() == null)
            return;

        if (event.getClickedBlock().getType() != Material.LILY_PAD)
            return;

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand.getType() != Material.BONE_MEAL)
            return;

        World world = event.getClickedBlock().getWorld();
        Location origin = event.getClickedBlock().getLocation();
        for (int i = 0; i <= 5; i++) {
            Location newPos = origin.clone().add(util.getRandomLocationOffset(0, 3, false));
            Block newBlock = world.getBlockAt(newPos);
            if (newBlock.getType() != Material.AIR)
                continue;

            if (newBlock.getRelative(0, -1, 0).getType() != Material.WATER)
                continue;

            newBlock.setType(Material.LILY_PAD);
            world.spawnParticle(Particle.VILLAGER_HAPPY, newPos, 5, 0.5, 0.5, 0.5);
        }

        itemInHand.setAmount(itemInHand.getAmount() - 1);
    }
}
