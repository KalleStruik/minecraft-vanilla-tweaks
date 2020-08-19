package nl.kallestruik.vanillatweaks.core;


import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TweakManager {

    // A HashMap containing the tweak identifier mapped to the instance of the tweak.
    private static final ConcurrentHashMap<String, Tweak> tweaks = new ConcurrentHashMap<>();
    // A HashMap containing categories and the tweak identifiers that belong to them.
    private static final HashMap<String, List<String>> tweakCategories = new HashMap<>();

    /**
     * Register a new tweak.
     *
     * @param tweak          An instance of the tweak.
     * @param pluginInstance An instance of the plugin.
     * @return True if the tweak did not yet exist false otherwise.
     */
    public static boolean registerTweak(Tweak tweak, JavaPlugin pluginInstance) {
        if (tweaks.containsKey(tweak.getIdentifier()))
            return false;

        tweaks.put(tweak.getIdentifier(), tweak);
        tweak.onRegister(pluginInstance);
        if (TweakStates.get(tweak.getIdentifier()))
            tweak.onEnable();
        return true;
    }

    /**
     * Unregister a tweak.
     *
     * @param identifier The identifier of the tweak to unregister.
     * @return True if the tweak was successfully unregistered false otherwise.
     */
    public static boolean unRegisterTweak(String identifier) {
        if (!tweaks.containsKey(identifier))
            return false;

        Tweak tweak = tweaks.get(identifier);
        tweak.onDisable();
        tweak.onUnRegister();
        tweaks.remove(identifier);
        return true;
    }

    /**
     * Unregister every tweak that is currently registered.
     */
    public static void unRegisterAllTweaks() {
        for (String identifier : tweaks.keySet()) {
            unRegisterTweak(identifier);
        }
    }

    /**
     * Enable a tweak.
     *
     * @param identifier The identifier of the tweak.
     * @return True if the tweak exists false otherwise.
     */
    public static boolean enableTweak(String identifier) {
        if (!tweaks.containsKey(identifier))
            return false;

        TweakStates.set(identifier, true);
        tweaks.get(identifier).onEnable();
        return true;
    }

    /**
     * Disable a tweak.
     *
     * @param identifier The identifier of the tweak.
     * @return True if the tweak exists false otherwise.
     */
    public static boolean disableTweak(String identifier) {
        if (!tweaks.containsKey(identifier))
            return false;

        TweakStates.set(identifier, false);
        tweaks.get(identifier).onDisable();
        return true;
    }
}
