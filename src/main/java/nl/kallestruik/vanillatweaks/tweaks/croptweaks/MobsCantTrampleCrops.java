package nl.kallestruik.vanillatweaks.tweaks.croptweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MobsCantTrampleCrops implements Tweak, Listener {
    private JavaPlugin plugin;

    @Override
    public String getIdentifier() {
        return "MobsCantTrampleCrops";
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
    public void onBlockBreak(EntityChangeBlockEvent event) {
        if (event.getBlock().getType() != Material.FARMLAND)
            return;

        if (event.getEntity() instanceof Player)
            return;

        event.setCancelled(true);
    }
}
