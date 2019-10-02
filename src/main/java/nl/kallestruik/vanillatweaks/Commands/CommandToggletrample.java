package nl.kallestruik.vanillatweaks.Commands;

import nl.kallestruik.vanillatweaks.Handlers.TrampleHandler;
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

        if (TrampleHandler.trampleEnabled.remove(player.getUniqueId())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Crop Trampling: &2&lEnabled"));
        } else {
            TrampleHandler.trampleEnabled.add(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Crop Trampling: &4&lDisabled"));
        }
        return true;
    }
}