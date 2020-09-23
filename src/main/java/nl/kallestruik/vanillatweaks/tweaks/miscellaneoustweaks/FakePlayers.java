package nl.kallestruik.vanillatweaks.tweaks.miscellaneoustweaks;

import nl.kallestruik.vanillatweaks.core.Tweak;
import nl.kallestruik.vanillatweaks.fakeplayer.FakePlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FakePlayers implements Tweak {

    @Override
    public String getIdentifier() {
        return "FakePlayers";
    }

    @Override
    public void onRegister(JavaPlugin pluginInstance) {

    }

    @Override
    public void onUnRegister() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        FakePlayerManager.killAllFakePlayers();
    }
}
