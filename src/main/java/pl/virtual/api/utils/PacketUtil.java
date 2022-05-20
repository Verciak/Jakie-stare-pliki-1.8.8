// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import org.bukkit.entity.Player;

public final class PacketUtil
{
    private static Reflection.MethodInvoker entityHandleMethod;
    private static Reflection.MethodInvoker sendPacket;
    private static Reflection.FieldAccessor<Object> networkManager;
    private static Reflection.FieldAccessor<Object> playerConnection;
    private static Reflection.FieldAccessor<Integer> ping;
    
    static {
        PacketUtil.entityHandleMethod = null;
        PacketUtil.sendPacket = null;
        PacketUtil.networkManager = null;
        PacketUtil.playerConnection = null;
        PacketUtil.ping = null;
        PacketUtil.entityHandleMethod = Reflection.getMethod(Reflection.getCraftBukkitClass("entity.CraftEntity"), "getHandle", (Class<?>[])new Class[0]);
        PacketUtil.sendPacket = Reflection.getMethod(Reflection.getMinecraftClass("PlayerConnection"), "sendPacket", Reflection.getMinecraftClass("Packet"));
        PacketUtil.playerConnection = Reflection.getSimpleField(Reflection.getMinecraftClass("EntityPlayer"), "playerConnection");
        PacketUtil.networkManager = Reflection.getSimpleField(Reflection.getMinecraftClass("PlayerConnection"), "networkManager");
        PacketUtil.ping = Reflection.getField(Reflection.getMinecraftClass("EntityPlayer"), "ping", Integer.TYPE);
    }
    
    public static void sendPacket(final Player player, final Object... objects) {
        final Object handle = getHandle(player);
        for (final Object o : objects) {
            PacketUtil.sendPacket.invoke(PacketUtil.playerConnection.get(handle), o);
        }
    }
    
    public static int getPing(final Player p) {
        return PacketUtil.ping.get(PacketUtil.entityHandleMethod.invoke(p, new Object[0]));
    }
    
    public static Object getHandle(final Player p) {
        if (PacketUtil.entityHandleMethod == null) {
            throw new IllegalArgumentException("HandleMethod can not be null!");
        }
        return PacketUtil.entityHandleMethod.invoke(p, new Object[0]);
    }
    
    public static GameProfile getGameProfile(final String name) {
        return new GameProfile(UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8)), name);
    }
    
    private static final class PACKET1S
    {
        private static Class<?> packetClass;
        private static Class<?> packetClassPlayer;
        private static Reflection.ConstructorInvoker con;
        private static Reflection.ConstructorInvoker conPlayer;
        private static Reflection.FieldAccessor<String> a;
        private static Reflection.FieldAccessor<String> b;
        private static Reflection.FieldAccessor<String> c;
        private static Reflection.FieldAccessor<String> d;
        private static Reflection.FieldAccessor<Collection> e;
        private static Reflection.FieldAccessor<Integer> f;
        private static Reflection.FieldAccessor<Integer> g;
        
        static {
            PACKET1S.packetClass = Reflection.getMinecraftClass("PacketPlayOutScoreboardTeam");
            PACKET1S.packetClassPlayer = Reflection.getMinecraftClass("PacketPlayOutPlayerInfo");
            PACKET1S.con = Reflection.getConstructor(PACKET1S.packetClass, (Class<?>[])new Class[0]);
            PACKET1S.conPlayer = Reflection.getConstructor(PACKET1S.packetClassPlayer, String.class, Boolean.TYPE, Integer.TYPE);
            PACKET1S.a = Reflection.getField(PACKET1S.packetClass, "a", String.class);
            PACKET1S.b = Reflection.getField(PACKET1S.packetClass, "b", String.class);
            PACKET1S.c = Reflection.getField(PACKET1S.packetClass, "c", String.class);
            PACKET1S.d = Reflection.getField(PACKET1S.packetClass, "d", String.class);
            PACKET1S.e = (Reflection.FieldAccessor<Collection>)Reflection.getField(PACKET1S.packetClass, "e", Collection.class);
            PACKET1S.f = Reflection.getField(PACKET1S.packetClass, "f", Integer.TYPE);
            PACKET1S.g = Reflection.getField(PACKET1S.packetClass, "g", Integer.TYPE);
        }
        
        public static Object createTeamPacket(final String name, final String display, final String prefix, final String suffix, final int flag, final String... members) {
            final Object packet = PACKET1S.con.invoke(new Object[0]);
            PACKET1S.a.set(packet, (name.length() > 16) ? name.substring(0, 16) : name);
            PACKET1S.f.set(packet, flag);
            if (flag == 0 || flag == 2) {
                PACKET1S.b.set(packet, (display == null) ? "" : display.substring(0, Math.min(display.length(), 16)));
                PACKET1S.c.set(packet, (prefix == null) ? "" : prefix.substring(0, Math.min(prefix.length(), 16)));
                PACKET1S.d.set(packet, (suffix == null) ? "" : suffix.substring(0, Math.min(suffix.length(), 16)));
                PACKET1S.g.set(packet, 0);
                if (flag == 0) {
                    PACKET1S.e.set(packet, Arrays.asList(members));
                }
            }
            else if (flag == 3 || flag == 4) {
                PACKET1S.g.set(packet, 0);
                PACKET1S.e.set(packet, Arrays.asList(members));
            }
            return packet;
        }
        
        public static Object createPlayerPacket(final String name, final boolean visible, final int ping) {
            return PACKET1S.conPlayer.invoke(name, visible, ping);
        }
    }
}
