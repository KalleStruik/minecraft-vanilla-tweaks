package nl.kallestruik.vanillatweaks.tweaks.miscellaneoustweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ArmorStandArmorSwapping implements Tweak, Listener {
    private JavaPlugin plugin;

    @Override
    public String getIdentifier() {
        return "ArmorSwapping";
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
    public void onClickEntity(PlayerInteractAtEntityEvent event) {
        if (event.getPlayer().isSneaking()) {
            if (event.getRightClicked() instanceof ArmorStand) {
                ArmorStand armorStand = ((ArmorStand) event.getRightClicked());
                EntityEquipment standEquipment = armorStand.getEquipment();
                Player player = event.getPlayer();
                EntityEquipment playerEquipment = player.getEquipment();

                if (standEquipment != null && playerEquipment != null) {
                    ItemStack playerHelmet = playerEquipment.getHelmet();
                    ItemStack playerChestplate = playerEquipment.getChestplate();
                    ItemStack playerLeggings = playerEquipment.getLeggings();
                    ItemStack playerBoots = playerEquipment.getBoots();

                    player.getInventory().setHelmet(standEquipment.getHelmet());
                    player.getInventory().setChestplate(standEquipment.getChestplate());
                    player.getInventory().setLeggings(standEquipment.getLeggings());
                    player.getInventory().setBoots(standEquipment.getBoots());

                    armorStand.setHelmet(playerHelmet);
                    armorStand.setChestplate(playerChestplate);
                    armorStand.setLeggings(playerLeggings);
                    armorStand.setBoots(playerBoots);
                }

                event.setCancelled(true);

            }
        }
    }
}
