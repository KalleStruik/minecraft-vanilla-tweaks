package nl.kallestruik.vanillatweaks.ArmorStandSwapping;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EntityEquipment;

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
                    armorStand.setHelmet(playerEquipment.getHelmet());
                    armorStand.setChestplate(playerEquipment.getChestplate());
                    armorStand.setLeggings(playerEquipment.getLeggings());
                    armorStand.setBoots(playerEquipment.getBoots());

                    player.getInventory().setArmorContents(standEquipment.getArmorContents());
                }

                event.setCancelled(true);

            }
        }
    }
}
