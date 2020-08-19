package nl.kallestruik.vanillatweaks.core;

import org.bukkit.plugin.java.JavaPlugin;

public interface Tweak {

    String getIdentifier();

    void onRegister(JavaPlugin pluginInstance);

    void onUnRegister();

    void onEnable();

    void onDisable();
}
