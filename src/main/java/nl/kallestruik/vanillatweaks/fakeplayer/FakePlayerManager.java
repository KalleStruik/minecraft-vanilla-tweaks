package nl.kallestruik.vanillatweaks.fakeplayer;

import net.minecraft.server.v1_16_R2.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class FakePlayerManager {

    public static void spawnFakePlayer(Location loc, String name) {
        Player player = Bukkit.getPlayer(name);
        if (player != null && player.isOnline())
            return;

        FakePlayer.atLocation(loc, name);
    }

    public static void killFakePlayer(String name) {
        Player player = Bukkit.getPlayer(name);
        if (player == null || !player.isOnline())
            return;

        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();

        if (!(entityPlayer instanceof FakePlayer))
            return;

        entityPlayer.killEntity();
    }

    public static void killAllFakePlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.isOnline())
                continue;

            EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
            if (!(entityPlayer instanceof FakePlayer))
                continue;

            entityPlayer.killEntity();
        }
    }
}
