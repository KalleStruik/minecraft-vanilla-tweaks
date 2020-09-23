package nl.kallestruik.vanillatweaks.fakeplayer;

import com.mojang.authlib.GameProfile;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_16_R2.*;
import nl.kallestruik.vanillatweaks.util.ReflectionUtil;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class FakePlayerList {

    public static void a(PlayerList playerList, NetworkManager networkmanager, EntityPlayer entityplayer) {
        MinecraftServer server = ((MinecraftServer) ReflectionUtil.getValueFromField(playerList, "server"));
        Logger LOGGER = ((Logger) ReflectionUtil.getValueFromField(playerList, "LOGGER"));

        GameProfile gameprofile = entityplayer.getProfile();
        UserCache usercache = server.getUserCache();
        GameProfile gameprofile1 = usercache.getProfile(gameprofile.getId());
        String s = gameprofile1 == null ? gameprofile.getName() : gameprofile1.getName();
        usercache.a(gameprofile);
        NBTTagCompound nbttagcompound = playerList.a(entityplayer);
        if (nbttagcompound != null && nbttagcompound.hasKey("bukkit")) {
            NBTTagCompound bukkit = nbttagcompound.getCompound("bukkit");
            s = bukkit.hasKeyOfType("lastKnownName", 8) ? bukkit.getString("lastKnownName") : s;
        }

        ResourceKey resourcekey;
        if (nbttagcompound != null) {
            DataResult dataresult = DimensionManager.a(new Dynamic(DynamicOpsNBT.a, nbttagcompound.get("Dimension")));
            Logger logger = LOGGER;
            logger.getClass();
            logger.getClass();
            resourcekey = (ResourceKey)dataresult.resultOrPartial(logger::error).orElse(World.OVERWORLD);
        } else {
            resourcekey = World.OVERWORLD;
        }

        WorldServer worldserver = server.getWorldServer(resourcekey);
        WorldServer[] worldserver1 = new WorldServer[1];
        if (worldserver == null) {
            LOGGER.warn("Unknown respawn dimension {}, defaulting to overworld", resourcekey);
            worldserver1[0] = server.E();
        } else {
            worldserver1[0] = worldserver;
        }

        entityplayer.spawnIn(worldserver1[0]);
        entityplayer.playerInteractManager.a((WorldServer)entityplayer.world);
        String s1 = "local";
        if (networkmanager.getSocketAddress() != null) {
            s1 = networkmanager.getSocketAddress().toString();
        }

        Player bukkitPlayer = entityplayer.getBukkitEntity();
        PlayerSpawnLocationEvent ev = new PlayerSpawnLocationEvent(bukkitPlayer, bukkitPlayer.getLocation());
        Bukkit.getPluginManager().callEvent(ev);
        Location loc = ev.getSpawnLocation();
        worldserver1[0] = ((CraftWorld)loc.getWorld()).getHandle();
        entityplayer.spawnIn(worldserver1[0]);
        entityplayer.playerInteractManager.a((WorldServer)entityplayer.world);
        entityplayer.setPosition(loc.getX(), loc.getY(), loc.getZ());

        //entityplayer.setYawPitch(loc.getYaw(), loc.getPitch());
        try {
            Method setYawPitch = Entity.class.getDeclaredMethod("setYawPitch", float.class, float.class);
            setYawPitch.setAccessible(true);
            setYawPitch.invoke(entityplayer, loc.getYaw(), loc.getPitch());
        } catch (Exception e) {
            e.printStackTrace();
        }

        WorldData worlddata = worldserver1[0].getWorldData();

        //playerList.a(entityplayer, (EntityPlayer)null, worldserver1);
        try {
            Method a = PlayerList.class.getDeclaredMethod("a", EntityPlayer.class, EntityPlayer.class, WorldServer.class);
            a.setAccessible(true);
            a.invoke(playerList, entityplayer, null, worldserver1[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //PlayerConnection playerconnection = new PlayerConnection(server, networkmanager, entityplayer);
        PlayerConnection playerconnection;
        if (entityplayer instanceof FakePlayer) {
            playerconnection = new FakePlayerConnection(server, networkmanager, entityplayer);
        } else {
            playerconnection = new PlayerConnection(server, networkmanager, entityplayer);
        }

        GameRules gamerules = worldserver1[0].getGameRules();
        boolean flag = gamerules.getBoolean(GameRules.DO_IMMEDIATE_RESPAWN);
        boolean flag1 = gamerules.getBoolean(GameRules.REDUCED_DEBUG_INFO);

        try {
            playerconnection.sendPacket(new PacketPlayOutLogin(entityplayer.getId(), entityplayer.playerInteractManager.getGameMode(), entityplayer.playerInteractManager.c(), BiomeManager.a(worldserver1[0].getSeed()), worlddata.isHardcore(), server.F(), ((IRegistryCustom.Dimension) ReflectionUtil.getValueFromField(playerList, "s")), worldserver1[0].getDimensionManager(), worldserver1[0].getDimensionKey(), playerList.getMaxPlayers(), worldserver1[0].spigotConfig.viewDistance, flag1, !flag, worldserver1[0].isDebugWorld(), worldserver1[0].isFlatWorld()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        entityplayer.getBukkitEntity().sendSupportedChannels();
        playerconnection.sendPacket(new PacketPlayOutCustomPayload(PacketPlayOutCustomPayload.a, (new PacketDataSerializer(Unpooled.buffer())).a(playerList.getServer().getServerModName())));
        playerconnection.sendPacket(new PacketPlayOutServerDifficulty(worlddata.getDifficulty(), worlddata.isDifficultyLocked()));
        playerconnection.sendPacket(new PacketPlayOutAbilities(entityplayer.abilities));
        playerconnection.sendPacket(new PacketPlayOutHeldItemSlot(entityplayer.inventory.itemInHandIndex));
        playerconnection.sendPacket(new PacketPlayOutRecipeUpdate(server.getCraftingManager().b()));
        playerconnection.sendPacket(new PacketPlayOutTags(server.getTagRegistry()));
        playerList.d(entityplayer);
        entityplayer.getStatisticManager().c();
        entityplayer.getRecipeBook().a(entityplayer);
        playerList.sendScoreboard(worldserver1[0].getScoreboard(), entityplayer);
        server.invalidatePingSample();
        ChatMessage chatmessage;
        if (entityplayer.getProfile().getName().equalsIgnoreCase(s)) {
            chatmessage = new ChatMessage("multiplayer.player.joined", new Object[]{entityplayer.getScoreboardDisplayName()});
        } else {
            chatmessage = new ChatMessage("multiplayer.player.joined.renamed", new Object[]{entityplayer.getScoreboardDisplayName(), s});
        }

        chatmessage.a(EnumChatFormat.YELLOW);
        String joinMessage = CraftChatMessage.fromComponent(chatmessage);
        playerconnection.a(entityplayer.locX(), entityplayer.locY(), entityplayer.locZ(), entityplayer.yaw, entityplayer.pitch);
        playerList.players.add(entityplayer);

        //playerList.playersByName.put(entityplayer.getName().toLowerCase(Locale.ROOT), entityplayer);
        ((Map<String, EntityPlayer>) ReflectionUtil.getValueFromField(playerList, "playersByName")).put(entityplayer.getName().toLowerCase(Locale.ROOT), entityplayer);

        //playerList.j.put(entityplayer.getUniqueID(), entityplayer);
        ((Map<UUID, EntityPlayer>) ReflectionUtil.getValueFromField(playerList, "j")).put(entityplayer.getUniqueID(), entityplayer);

        PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(((CraftServer) Bukkit.getServer()).getPlayer(entityplayer), joinMessage);
        Bukkit.getServer().getPluginManager().callEvent(playerJoinEvent);
        if (entityplayer.playerConnection.networkManager.isConnected()) {
            joinMessage = playerJoinEvent.getJoinMessage();
            int i;
            if (joinMessage != null && joinMessage.length() > 0) {
                IChatBaseComponent[] var27;
                int var26 = (var27 = CraftChatMessage.fromString(joinMessage)).length;

                for(i = 0; i < var26; ++i) {
                    IChatBaseComponent line = var27[i];
                    server.getPlayerList().sendAll(new PacketPlayOutChat(line, ChatMessageType.SYSTEM, SystemUtils.b));
                }
            }

            PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[]{entityplayer});

            for(i = 0; i < playerList.players.size(); ++i) {
                EntityPlayer entityplayer1 = (EntityPlayer)playerList.players.get(i);
                if (entityplayer1.getBukkitEntity().canSee(entityplayer.getBukkitEntity())) {
                    entityplayer1.playerConnection.sendPacket(packet);
                }

                if (entityplayer.getBukkitEntity().canSee(entityplayer1.getBukkitEntity())) {
                    entityplayer.playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[]{entityplayer1}));
                }
            }

            entityplayer.sentListPacket = true;
            entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityMetadata(entityplayer.getId(), ((DataWatcher) ReflectionUtil.getValueFromField(entityplayer, "datawatcher")), true));
            if (entityplayer.world == worldserver1[0] && !worldserver1[0].getPlayers().contains(entityplayer)) {
                worldserver1[0].addPlayerJoin(entityplayer);
                server.getBossBattleCustomData().a(entityplayer);
            }

            worldserver1[0] = entityplayer.getWorldServer();
            playerList.a(entityplayer, worldserver1[0]);
            if (!server.getResourcePack().isEmpty()) {
                entityplayer.setResourcePack(server.getResourcePack(), server.getResourcePackHash());
            }

            Iterator iterator = entityplayer.getEffects().iterator();

            while(iterator.hasNext()) {
                MobEffect mobeffect = (MobEffect)iterator.next();
                playerconnection.sendPacket(new PacketPlayOutEntityEffect(entityplayer.getId(), mobeffect));
            }

            if (nbttagcompound != null && nbttagcompound.hasKeyOfType("RootVehicle", 10)) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("RootVehicle");
                Entity entity = EntityTypes.a(nbttagcompound1.getCompound("Entity"), worldserver1[0], (entity1x) -> {
                    return !worldserver1[0].addEntitySerialized(entity1x) ? null : entity1x;
                });
                if (entity != null) {
                    UUID uuid;
                    if (nbttagcompound1.b("Attach")) {
                        uuid = nbttagcompound1.a("Attach");
                    } else {
                        uuid = null;
                    }

                    Iterator iterator1;
                    Entity entity1;
                    if (entity.getUniqueID().equals(uuid)) {
                        entityplayer.a(entity, true);
                    } else {
                        iterator1 = entity.getAllPassengers().iterator();

                        while(iterator1.hasNext()) {
                            entity1 = (Entity)iterator1.next();
                            if (entity1.getUniqueID().equals(uuid)) {
                                entityplayer.a(entity1, true);
                                break;
                            }
                        }
                    }

                    if (!entityplayer.isPassenger()) {
                        LOGGER.warn("Couldn't reattach entity to player");
                        worldserver1[0].removeEntity(entity);
                        iterator1 = entity.getAllPassengers().iterator();

                        while(iterator1.hasNext()) {
                            entity1 = (Entity)iterator1.next();
                            worldserver1[0].removeEntity(entity1);
                        }
                    }
                }
            }

            entityplayer.syncInventory();
            LOGGER.info("{}[{}] logged in with entity id {} at ([{}]{}, {}, {})", entityplayer.getDisplayName().getString(), s1, entityplayer.getId(), worldserver1[0].worldDataServer.getName(), entityplayer.locX(), entityplayer.locY(), entityplayer.locZ());
        }
    }
}
