// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.managers;

import io.netty.channel.ChannelHandler;

import org.bukkit.event.Event;
import org.bukkit.Bukkit;

import io.netty.channel.ChannelPromise;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelDuplexHandler;
import org.bukkit.entity.Player;

import pl.virtual.api.packets.PacketReceiveEvent;
import pl.virtual.api.packets.PacketSendEvent;
import pl.virtual.api.utils.Reflection;
import io.netty.channel.Channel;
import java.util.UUID;
import java.util.HashMap;

public class PacketManager
{
    private static final HashMap<UUID, Channel> channels;
    private static Reflection.FieldAccessor<Channel> clientChannel;
    private static Reflection.FieldAccessor<Object> playerConnection;
    private static Reflection.FieldAccessor<Object> networkManager;
    private static Reflection.MethodInvoker handleMethod;
    
    static {
        channels = new HashMap<UUID, Channel>();
        try {
            PacketManager.clientChannel = Reflection.getField(Reflection.getMinecraftClass("NetworkManager"), Channel.class, 0);
            PacketManager.playerConnection = Reflection.getSimpleField(Reflection.getMinecraftClass("EntityPlayer"), "playerConnection");
            PacketManager.networkManager = Reflection.getSimpleField(Reflection.getMinecraftClass("PlayerConnection"), "networkManager");
            PacketManager.handleMethod = Reflection.getMethod(Reflection.getCraftBukkitClass("entity.CraftEntity"), "getHandle", (Class<?>[])new Class[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static Channel getChannel(final Player p) {
        final Object eP = PacketManager.handleMethod.invoke(p, new Object[0]);
        return PacketManager.clientChannel.get(PacketManager.networkManager.get(PacketManager.playerConnection.get(eP)));
    }
    
    public static void registerPlayer(final Player p) {
        final Channel c = getChannel(p);
        final ChannelHandler handler = (ChannelHandler)new ChannelDuplexHandler() {
            public void write(final ChannelHandlerContext ctx, final Object msg, final ChannelPromise promise) throws Exception {
                final PacketSendEvent event = new PacketSendEvent(msg, p);
                Bukkit.getPluginManager().callEvent((Event)event);
                if (event.isCancelled() || event.getPacket() == null) {
                    return;
                }
                super.write(ctx, event.getPacket(), promise);
            }
            
            public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
                final PacketReceiveEvent event = new PacketReceiveEvent(msg, p);
                Bukkit.getPluginManager().callEvent((Event)event);
                if (event.isCancelled() || event.getPacket() == null) {
                    return;
                }
                super.channelRead(ctx, event.getPacket());
            }
        };
        c.pipeline().addBefore("packet_handler", "RevoGuild", handler);
        PacketManager.channels.put(p.getUniqueId(), c);
    }
    
    public static void unregisterPlayer(final Player p) {
        PacketManager.channels.remove(p.getUniqueId()).pipeline().remove("RevoGuild");
    }
    
    public static HashMap<UUID, Channel> getChannels() {
        return PacketManager.channels;
    }
}
