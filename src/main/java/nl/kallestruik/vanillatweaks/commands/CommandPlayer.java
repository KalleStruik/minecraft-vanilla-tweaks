package nl.kallestruik.vanillatweaks.commands;

import nl.kallestruik.vanillatweaks.fakeplayer.FakePlayerManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPlayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length < 2)
            return false;

        Player player = ((Player) sender);
        Location loc = player.getLocation();

        switch (args[1]) {
            case "spawn":
                FakePlayerManager.spawnFakePlayer(loc, args[0]);
                break;
            case "kill":
                FakePlayerManager.killFakePlayer(args[0]);
                break;
        }

        return true;
    }
}
