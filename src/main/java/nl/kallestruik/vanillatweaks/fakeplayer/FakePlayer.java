package nl.kallestruik.vanillatweaks.fakeplayer;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_16_R2.*;

import nl.kallestruik.vanillatweaks.util.ReflectionUtil;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;

import javax.annotation.Nullable;
import java.util.List;

public class FakePlayer extends EntityPlayer {
    private boolean hasStartingPos;
    private double startingX, startingY, startingZ;
    private float startingYaw, startingPitch;

    public FakePlayer(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile, PlayerInteractManager playerinteractmanager) {
        super(minecraftserver, worldserver, gameprofile, playerinteractmanager);
        this.hasStartingPos = false;
    }

    private FakePlayer(MinecraftServer server, WorldServer worldIn, GameProfile profile, PlayerInteractManager interactionManagerIn, double x, double y, double z, float yaw, float pitch) {
        super(server, worldIn, profile, interactionManagerIn);
        this.hasStartingPos = true;
        this.startingX = x;
        this.startingY = y;
        this.startingZ = z;
        this.startingYaw = yaw;
        this.startingPitch = pitch;
    }

    @Nullable
    public static FakePlayer atLocation(Location location, String name) {
        // Split the location into its parts for use later.
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        double yaw = location.getYaw();
        double pitch = location.getPitch();

        try {
            // Get the minecraft server instance.
            MinecraftServer minecraftServer = MinecraftServer.getServer();
            // Create an empty variable for the world server.
            WorldServer worldServer = ((CraftWorld) location.getWorld()).getHandle();
            // Get the game profile from the cache (or retrieve it if it is not cached)
            GameProfile gameProfile = minecraftServer.getUserCache().getProfile(name);

            if (gameProfile == null) {
                return null;
            }
            if (gameProfile.getProperties().containsKey("textures")) {
                gameProfile = TileEntitySkull.b(gameProfile, null, true).get();
            }

            // Create a player interact manager using the world server.
            PlayerInteractManager playerInteractManager = new PlayerInteractManager(worldServer);

            // Create an instance of our fake player.
            FakePlayer instance = new FakePlayer(minecraftServer, worldServer, gameProfile, playerInteractManager, x, y, z, (float) yaw, (float) pitch);
            // Create a fake connection for our fake player.
            FakeConnection connection = new FakeConnection(EnumProtocolDirection.SERVERBOUND);

            // Set access to connected channels so we can add our own connection.
            ServerConnection serverConnection = minecraftServer.getServerConnection();
            ((List<NetworkManager>) ReflectionUtil.getValueFromField(serverConnection, "connectedChannels")).add(connection);

            // Connect the fake player to the server using the fake connection.
            FakePlayerList.a(minecraftServer.getPlayerList(), connection, instance);

            // Check if the fake player is not yet in the correct world.
            if (!instance.world.getDimensionKey().equals(worldServer.getDimensionKey())) {
                // Store the old world of the fake player.
                WorldServer old_world = (WorldServer) instance.world;
                // Remove the fake player from the old world.
                old_world.removePlayer(instance);
                // Make the fake player not be dead.
                instance.dead = false;
                // Create the fake player in the new world.
                worldServer.addEntity(instance);
                // Spawn the fake player in the new world.
                instance.spawnIn(worldServer);
                // Move the fake player to the new world for the server.
                minecraftServer.getPlayerList().moveToWorld(instance, old_world, true, null, false);
                // requestTeleport(x, y, z, yaw, pitch)
                // Request the teleport from the fake player.
                instance.playerConnection.a(x, y, z, (float) yaw, (float) pitch);
                // Set the fake player's world to the new world.
                instance.playerInteractManager.world = worldServer;
                instance.teleportTo(worldServer, new BlockPosition(x, y, z));
            }

            /// Set the fake players health to max.
            instance.setHealth(20.0F);
            // Make the fake player not be dead.
            instance.dead = false;
            // a == requestTeleport
            // Request the teleport from the fake player.
            instance.playerConnection.a(x, y, z, (float) yaw, (float) pitch);
            // G == stepHeight
            // Set the fake players step height to 0.6 (The normal value).
            instance.G = 0.6F;
            // Set the fake players gamemode to survival.
            playerInteractManager.setGameMode(EnumGamemode.SURVIVAL);
            // Tell everyone in the world about the fake player and where he is.
            minecraftServer.getPlayerList().a(new PacketPlayOutEntityHeadRotation(instance, (byte) (instance.yaw * 256 / 360)), instance.world.getDimensionKey());
            minecraftServer.getPlayerList().a(new PacketPlayOutEntityTeleport(instance), instance.world.getDimensionKey());
            // Move the fake player for the worlds chunk provider.
            instance.getWorldServer().getChunkProvider().movePlayer(instance);
            // bp == PLAYER_MODEL_PARTS
            instance.datawatcher.set(bj, (byte) 0x7f); // show all model layers (incl. capes)

            return instance;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void applyStartingPosition() {
        if (hasStartingPos) {
            this.setPositionRotation(startingX, startingY, startingZ, startingYaw, startingPitch);
            this.setMot(new Vec3D(0, 0, 0));
        }
    }



    @Override
    public void killEntity() {
        this.server.a(new TickTask(this.server.ah(), () -> {
            this.playerConnection.a(new ChatComponentText("Killed"));
        }));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.H()) {
            this.I();
        }
        this.movementTick();
        if (this.server.ah() % 10 == 0) {
            this.playerConnection.syncPosition();
            this.getWorldServer().getChunkProvider().movePlayer(this);
        }
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
        setHealth(20);
        this.foodData = new FoodMetaData(this);
        killEntity();
    }
}
