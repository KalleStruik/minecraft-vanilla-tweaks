package nl.kallestruik.vanillatweaks;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class config {

    public static boolean TOGGLE_TRAMPLE_ENABLED;

    public static void load(File file) throws IOException, InvalidConfigurationException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (!file.exists())
            util.ExportResource(c.CONFIG_FILE_NAME, file);
        YamlConfiguration config = new YamlConfiguration();
        config.load(file);

        TOGGLE_TRAMPLE_ENABLED = config.getBoolean("toggle-trample.enabled");

    }
}
