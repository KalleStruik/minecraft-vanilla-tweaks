package nl.kallestruik.vanillatweaks;

import nl.kallestruik.vanillatweaks.ArmorStandSwapping.ArmorStandSwappingHandler;
import nl.kallestruik.vanillatweaks.CraftingTweaks.CraftingTweaks;
import nl.kallestruik.vanillatweaks.HoeHarvesting.HoeHarvestingHandler;
import nl.kallestruik.vanillatweaks.LilypadGrowing.LilypadGrowingHandler;
import nl.kallestruik.vanillatweaks.NetherSpongeDrying.NetherSpongeHandler;
import nl.kallestruik.vanillatweaks.SeedDropPlanting.SeedDropPlanting;
import nl.kallestruik.vanillatweaks.ToggleTrample.CommandToggletrample;
import nl.kallestruik.vanillatweaks.ToggleTrample.TrampleHandler;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

@SuppressWarnings("ConstantConditions")
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

            if (config.SEED_DROP_PLANTING_ENABLED) {
                SeedDropPlanting.init(this);
            }

            if (config.ARMOR_STAND_SWAPPING_ENABLED) {
                getServer().getPluginManager().registerEvents(new ArmorStandSwappingHandler(), this);
            }

            if (config.NETHER_SPONGE_DRYING_ENABLED) {
                getServer().getPluginManager().registerEvents(new NetherSpongeHandler(), this);
            }

            if (config.HOE_HARVESTING_ENABLED) {
                getServer().getPluginManager().registerEvents(new HoeHarvestingHandler(), this);
            }

            if (config.LILYPAD_GROWING_ENABLED) {
                getServer().getPluginManager().registerEvents(new LilypadGrowingHandler(), this);
            }



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
