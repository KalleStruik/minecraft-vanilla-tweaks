package nl.kallestruik.vanillatweaks;

import nl.kallestruik.vanillatweaks.CraftingTweaks.CraftingTweaks;
import nl.kallestruik.vanillatweaks.ToggleTrample.CommandToggletrample;
import nl.kallestruik.vanillatweaks.ToggleTrample.TrampleHandler;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public final class VanillaTweaks extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            // Config loading
            config.load(new File(this.getDataFolder(), c.CONFIG_FILE_NAME));

            if (config.TOGGLE_TRAMPLE_ENABLED) {
                TrampleHandler.loadTrampleEnabled(new File(this.getDataFolder(), c.TRAMPLE_ENABLED_FILE_NAME));
                getServer().getPluginCommand("toggletrample").setExecutor(new CommandToggletrample());
                getServer().getPluginManager().registerEvents(new TrampleHandler(), this);
            }

            if (config.CRAFTING_TWEAKS_ENABLED) {
                CraftingTweaks.init(this);
            }

            /**
             * Dynamite
             *
             * Throwable tnt with a smaller explosion.
             */

            /**
             * Seed drop planting
             *
             * Seeds dropped on tilled soil will plant themselves after a random amount of time.
             */

            /**
             * Sign editing
             *
             * Shift right-click on a sign to edit it.
             */

            /**
             * Armour stand swap
             *
             * Shift right-click on armour stand to swap armour with it.
             */

            /**
             * Sponges in nether dry instantly
             *
             * Kind of what is says.
             */


        } catch (IOException | InvalidConfigurationException | NullPointerException e) {
            util.printException(e);
        }
    }

    @Override
    public void onDisable() {
        try {
            TrampleHandler.saveTrampleEnabled(new File(this.getDataFolder(), c.TRAMPLE_ENABLED_FILE_NAME));
        } catch (IOException | InvalidConfigurationException | NullPointerException e) {
            util.printException(e);
        }
    }
}
