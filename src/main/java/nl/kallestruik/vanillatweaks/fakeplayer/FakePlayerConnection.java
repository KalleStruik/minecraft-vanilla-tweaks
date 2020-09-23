package nl.kallestruik.vanillatweaks.fakeplayer;

import net.minecraft.server.v1_16_R2.*;
import nl.kallestruik.vanillatweaks.util.ReflectionUtil;

import java.lang.reflect.Field;

public class FakePlayerConnection extends PlayerConnection {

    public FakePlayerConnection(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void sendPacket(final Packet<?> packet) {
        if (packet instanceof PacketPlayOutKeepAlive) {
            PacketPlayOutKeepAlive ping = (PacketPlayOutKeepAlive) packet;
            PacketPlayInKeepAlive pong = new PacketPlayInKeepAlive();
            try {
                Field pingId = ReflectionUtil.getField(PacketPlayOutKeepAlive.class, "a");
                Field pongId = ReflectionUtil.getField(PacketPlayInKeepAlive.class, "a");

                pingId.setAccessible(true);
                pongId.setAccessible(true);

                pongId.set(pong, pingId.get(ping));
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.a(pong);
        }
    }

    @Override
    public void disconnect(String message) {
        player.killEntity();
    }

    @Override
    public void a(IChatBaseComponent text_1) {
        super.a(text_1);
        ((FakeConnection) this.a()).open = false;
    }
}
