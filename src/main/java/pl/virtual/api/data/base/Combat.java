// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base;

import org.bukkit.entity.Player;

public class Combat
{
    private Player player;
    private long lastAttactTime;
    private Player lastAttactkPlayer;
    private long lastAsystTime;
    private Player lastAsystPlayer;
    
    public Combat(final Player p) {
        this.player = p;
        this.lastAttactTime = 0L;
        this.lastAttactkPlayer = null;
        this.lastAsystPlayer = null;
        this.lastAsystTime = 0L;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void setPlayer(final Player player) {
        this.player = player;
    }
    
    public long getLastAttactTime() {
        return this.lastAttactTime;
    }
    
    public void setLastAttactTime(final long lastAttactTime) {
        this.lastAttactTime = lastAttactTime;
    }
    
    public Player getLastAttactkPlayer() {
        return this.lastAttactkPlayer;
    }
    
    public void setLastAttactkPlayer(final Player lastAttactkPlayer) {
        this.lastAttactkPlayer = lastAttactkPlayer;
    }
    
    public long getLastAsystTime() {
        return this.lastAsystTime;
    }
    
    public void setLastAsystTime(final long lastAsystTime) {
        this.lastAsystTime = lastAsystTime;
    }
    
    public Player getLastAsystPlayer() {
        return this.lastAsystPlayer;
    }
    
    public void setLastAsystPlayer(final Player lastAsystPlayer) {
        this.lastAsystPlayer = lastAsystPlayer;
    }
    
    public boolean hasFight() {
        return this.getLastAttactTime() > System.currentTimeMillis();
    }
    
    public boolean wasFight() {
        return this.getLastAttactkPlayer() != null;
    }
}
