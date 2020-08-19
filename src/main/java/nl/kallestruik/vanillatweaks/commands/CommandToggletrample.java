package nl.kallestruik.vanillatweaks.commands;

import nl.kallestruik.vanillatweaks.tweaks.croptweaks.PlayersCantTrampleCrops;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandToggletrample implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players!");
            return true;
        }

        Player player = (Player) sender;

        if (PlayersCantTrampleCrops.trampleEnabled.remove(player.getUniqueId())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Crop Trampling: &2&lEnabled"));
        } else {
            PlayersCantTrampleCrops.trampleEnabled.add(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Crop Trampling: &4&lDisabled"));
        }
        return true;
    }
}