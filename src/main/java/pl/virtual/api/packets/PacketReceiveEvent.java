// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.packets;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class PacketReceiveEvent extends Event implements Cancellable
{
    private static final HandlerList handlers;
    private final Player player;
    private Object packet;
    private boolean cancelled;
    
    static {
        handlers = new HandlerList();
    }
    
    public PacketReceiveEvent(final Object packet, final Player player) {
        super(true);
        this.packet = packet;
        this.player = player;
        this.cancelled = false;
    }
    
    public static HandlerList getHandlerList() {
        return PacketReceiveEvent.handlers;
    }
    
    public String getPacketName() {
        return this.packet.getClass().getSimpleName();
    }
    
    public HandlerList getHandlers() {
        return PacketReceiveEvent.handlers;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public Object getPacket() {
        return this.packet;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setPacket(final Object packet) {
        this.packet = packet;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
}
