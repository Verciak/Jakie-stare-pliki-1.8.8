// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.packets;

import org.bukkit.entity.Player;

import pl.virtual.api.utils.PacketUtil;
import pl.virtual.api.utils.Reflection;

public abstract class Packet
{
    protected Object packet;
    
    public void send(final Player player) {
        if (this.packet == null) {
            throw new IllegalArgumentException("Packet cannot be null!");
        }
        PacketUtil.sendPacket(player, this.packet);
    }
    
    public void setValue(final String fieldName, final Object value) {
        if (this.packet == null) {
            throw new IllegalArgumentException("Packet cannot be null!");
        }
        Reflection.getSimpleField(this.packet.getClass(), fieldName).set(this.packet, value);
    }
    
    public Object getValue(final String fieldName) {
        if (this.packet == null) {
            throw new IllegalArgumentException("Packet cannot be null!");
        }
        return Reflection.getSimpleField(this.packet.getClass(), fieldName).get(this.packet);
    }
    
    public Object getPacket() {
        return this.packet;
    }
    
    public void setPacket(final Object packet) {
        this.packet = packet;
    }
}
