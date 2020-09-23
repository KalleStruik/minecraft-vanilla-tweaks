package nl.kallestruik.vanillatweaks.fakeplayer;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.server.v1_16_R2.EnumProtocolDirection;
import net.minecraft.server.v1_16_R2.NetworkManager;
import net.minecraft.server.v1_16_R2.Packet;

import javax.annotation.Nullable;

public class FakeConnection extends NetworkManager {
    public boolean open = true;

    public FakeConnection(EnumProtocolDirection enumprotocoldirection) {
        super(enumprotocoldirection);
    }

    // isOpen()
    @Override
    public boolean isConnected() {
        return this.open;
    }

    // hasChannel()
    @Override
    public boolean i() {
        return false;
    }

    @Override
    public void sendPacket(Packet<?> packet, @Nullable GenericFutureListener<? extends Future<? super Void>> genericfuturelistener) {}

    // disableAutoRead()
    @Override
    public void stopReading() {}

    @Override
    public void handleDisconnection() {}
}
