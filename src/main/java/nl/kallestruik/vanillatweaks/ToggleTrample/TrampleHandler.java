package nl.kallestruik.vanillatweaks.ToggleTrample;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class TrampleHandler implements Listener {

    static HashSet<UUID> trampleEnabled = new HashSet<>();

    @EventHandler
    public void onBlockBreak(EntityChangeBlockEvent event) {
        if (event.getBlock().getType() != Material.FARMLAND)
            return;

        if (event.getEntity() instanceof Villager) {
            event.setCancelled(true);
            return;
        }

        if (event.getEntity() instanceof IronGolem) {
            event.setCancelled(true);
            return;
        }

        if (!(event.getEntity() instanceof Player))
            return;

        if (trampleEnabled.contains(event.getEntity().getUniqueId()))
            event.setCancelled(true);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void loadTrampleEnabled(File file) throws IOException, InvalidConfigurationException {
        file.getParentFile().mkdirs();
        file.createNewFile();

        YamlConfiguration config = new YamlConfiguration();
        config.load(file);

        for (String item : config.getStringList("enabled"))
            trampleEnabled.add(UUID.fromString(item));

    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void saveTrampleEnabled(File file) throws IOException, InvalidConfigurationException {
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