package nl.kallestruik.vanillatweaks;

import nl.kallestruik.vanillatweaks.commands.CommandPlayer;
import nl.kallestruik.vanillatweaks.commands.CommandToggletrample;
import nl.kallestruik.vanillatweaks.core.TweakManager;
import nl.kallestruik.vanillatweaks.core.TweakStates;
import nl.kallestruik.vanillatweaks.tweaks.craftingtweaks.*;
import nl.kallestruik.vanillatweaks.tweaks.croptweaks.MobsCantTrampleCrops;
import nl.kallestruik.vanillatweaks.tweaks.croptweaks.PlayersCantTrampleCrops;
import nl.kallestruik.vanillatweaks.tweaks.dispensertweaks.DispensersCanPlantSaplings;
import nl.kallestruik.vanillatweaks.tweaks.miscellaneoustweaks.ArmorStandArmorSwapping;
import nl.kallestruik.vanillatweaks.tweaks.miscellaneoustweaks.HoesHarvestArea;
import nl.kallestruik.vanillatweaks.tweaks.miscellaneoustweaks.LilypadBonemealing;
import nl.kallestruik.vanillatweaks.tweaks.miscellaneoustweaks.SeedDropPlanting;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@SuppressWarnings("ConstantConditions")
public final class VanillaTweaks extends JavaPlugin {

    @Override
    public void onEnable() {
        /*
         * Setup
         */
        TweakStates.load(new File(this.getDataFolder(), c.TWEAK_STATE_FILE_NAME));

        /*
         * Commands
         */
        getCommand("toggletrample").setExecutor(new CommandToggletrample());

        /*
         * Crafting Tweaks
         */
        TweakManager.registerTweak(new AlternativeDispenserRecipe(), this);
        TweakManager.registerTweak(new CraftableDragonsBreath(), this);
        TweakManager.registerTweak(new CraftableNametag(), this);
        TweakManager.registerTweak(new CraftableSaddle(), this);
        TweakManager.registerTweak(new CraftableShulkerShell(), this);
        TweakManager.registerTweak(new CraftableSponge(), this);
        TweakManager.registerTweak(new IceDecompression(), this);
        TweakManager.registerTweak(new LogsToChests(), this);
        TweakManager.registerTweak(new WoolToString(), this);

        /*
         * Crop Tweaks
         */
        TweakManager.registerTweak(new MobsCantTrampleCrops(), this);
        TweakManager.registerTweak(new PlayersCantTrampleCrops(), this);

        /*
         * Dispenser Tweaks
         */
        TweakManager.registerTweak(new DispensersCanPlantSaplings(), this);

        /*
         * Miscellaneous Tweaks
         */
        TweakManager.registerTweak(new ArmorStandArmorSwapping(), this);
        TweakManager.registerTweak(new HoesHarvestArea(), this);
        TweakManager.registerTweak(new LilypadBonemealing(), this);
        TweakManager.registerTweak(new SeedDropPlanting(), this);
        TweakManager.registerTweak(new DispensersCanPlantSaplings(), this);
    }

    @Override
    public void onDisable() {
        TweakManager.unRegisterAllTweaks();

        /*
         * Finishing up
         */
        TweakStates.save(new File(this.getDataFolder(), c.TWEAK_STATE_FILE_NAME));
    }
}
