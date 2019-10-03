package nl.kallestruik.vanillatweaks.ArmorStandSwapping;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class ArmorStandSwappingHandler implements Listener {

    @EventHandler
    public void onClickEntity(PlayerInteractAtEntityEvent event) {
        if (event.getPlayer().isSneaking()) {
            if (event.getRightClicked() instanceof ArmorStand) {
                ArmorStand armorStand = ((ArmorStand) event.getRightClicked());
                EntityEquipment standEquipment = armorStand.getEquipment();
                Player player = event.getPlayer();
                EntityEquipment playerEquipment = player.getEquipment();

                if (standEquipment != null && playerEquipment != null) {
                    ItemStack playerHelmet = playerEquipment.getHelmet();
                    ItemStack playerChestpalte = playerEquipment.getChestplate();
                    ItemStack playerLeggings = playerEquipment.getLeggings();
                    ItemStack playerBoots = playerEquipment.getBoots();

                    player.getInventory().setHelmet(standEquipment.getHelmet());
                    player.getInventory().setChestplate(standEquipment.getChestplate());
                    player.getInventory().setLeggings(standEquipment.getLeggings());
                    player.getInventory().setBoots(standEquipment.getBoots());

                    armorStand.setHelmet(playerHelmet);
                    armorStand.setChestplate(playerChestpalte);
                    armorStand.setLeggings(playerLeggings);
                    armorStand.setBoots(playerBoots);
                }

                event.setCancelled(true);

            }
        }
    }
}
