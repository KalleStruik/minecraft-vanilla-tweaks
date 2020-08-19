package nl.kallestruik.vanillatweaks.tweaks.croptweaks;

import nl.kallestruik.vanillatweaks.util.Util;
import nl.kallestruik.vanillatweaks.c;
import nl.kallestruik.vanillatweaks.core.Tweak;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class PlayersCantTrampleCrops implements Tweak, Listener {
    private JavaPlugin plugin;
    public static HashSet<UUID> trampleEnabled = new HashSet<>();


    @Override
    public String getIdentifier() {
        return "PlayersCantTrampleCrops";
    }

    @Override
    public void onRegister(JavaPlugin pluginInstance) {
        this.plugin = pluginInstance;
        try {
            loadTrampleEnabled(new File(this.plugin.getDataFolder(), c.TRAMPLE_ENABLED_FILE_NAME));
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUnRegister() {
        try {
            saveTrampleEnabled(new File(this.plugin.getDataFolder(), c.TRAMPLE_ENABLED_FILE_NAME));
        } catch (IOException | InvalidConfigurationException e) {
            Util.printException(e);
        }
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

        if (!(event.getEntity() instanceof Player))
            return;

        event.setCancelled(true);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void loadTrampleEnabled(File file) throws IOException, InvalidConfigurationException {
        file.getParentFile().mkdirs();
        file.createNewFile();

        YamlConfiguration config = new YamlConfiguration();
        config.load(file);

        for (String item : config.getStringList("enabled"))
            trampleEnabled.add(UUID.fromString(item));

    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void saveTrampleEnabled(File file) throws IOException, InvalidConfigurationException {
        file.getParentFile().mkdirs();
        file.createNewFile();

        YamlConfiguration config = new YamlConfiguration();
        config.load(file);

        List<String> list = new ArrayList<>();
        for (UUID entry : trampleEnabled)
            list.add(entry.toString());

        config.set("enabled", list);

        config.save(file);

    }
}
