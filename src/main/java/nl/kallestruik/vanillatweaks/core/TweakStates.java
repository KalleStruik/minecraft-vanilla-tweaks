package nl.kallestruik.vanillatweaks.core;

import nl.kallestruik.vanillatweaks.util.Util;
import nl.kallestruik.vanillatweaks.c;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TweakStates {

    private static final HashMap<String, Boolean> states = new HashMap<>();

    public static void load(File file) {
        try {
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            if (!file.exists())
                Util.exportResource(c.TWEAK_STATE_FILE_NAME, file);
            YamlConfiguration config = new YamlConfiguration();
            config.load(file);

            for (String key : config.getKeys(false)) {
                states.put(key, config.getBoolean(key));
            }
        } catch (IOException | InvalidConfigurationException e) {
            Util.printException(e);
        }
    }

    public static void save(File file) {
        try {
            YamlConfiguration config = new YamlConfiguration();

            for (Map.Entry<String, Boolean> entry : states.entrySet()) {
                config.set(entry.getKey(), entry.getValue());
            }

            config.save(file);
        } catch (IOException e) {
            Util.printException(e);
        }
    }

    public static void set(String identifier, boolean state) {
        states.put(identifier, state);
    }

    public static boolean get(String identifier) {
        if (!states.containsKey(identifier))
            states.put(identifier, false);

        return states.get(identifier);
    }
}
