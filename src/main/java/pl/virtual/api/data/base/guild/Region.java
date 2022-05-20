// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base.guild;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Region
{
    private int x;
    private int z;
    private int size;
    
    public Region(final int x, final int z, final int size) {
        this.x = x;
        this.z = z;
        this.size = size;
    }
    
    public boolean isInCuboid(final Location loc) {
        if (!loc.getWorld().equals(Bukkit.getWorld("world"))) {
            return false;
        }
        final int distancex = Math.abs(loc.getBlockX() - this.x);
        final int distancez = Math.abs(loc.getBlockZ() - this.z);
        return distancex <= this.getSize() && distancez <= this.getSize();
    }
    
    public boolean isInCuboidByLoc(final Location loc) {
        if (!loc.getWorld().equals(Bukkit.getWorld("world"))) {
            return false;
        }
        final int distancex = Math.abs(loc.getBlockX() - this.getX());
        final int distancez = Math.abs(loc.getBlockZ() - this.getZ());
        return distancex - 1 <= this.getSize() && distancez - 1 <= this.getSize();
    }
    
    public boolean isInCentrum(final Location loc, final int top, final int down, final int wall) {
        final Location c = this.getLocation().clone();
        return c.getBlockY() - down <= loc.getBlockY() && c.getBlockY() + top >= loc.getBlockY() && loc.getBlockX() <= c.getBlockX() + wall && loc.getBlockX() >= c.getBlockX() - wall && loc.getBlockZ() <= c.getBlockZ() + wall && loc.getBlockZ() >= c.getBlockZ() - wall;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public int getZ() {
        return this.z;
    }
    
    public void setZ(final int z) {
        this.z = z;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public Location getLocation() {
        return new Location(Bukkit.getWorld("world"), (double)this.getX(), 39.0, (double)this.getZ());
    }
    
    public void setSize(final int size) {
        this.size = size;
    }
    
    public void addSize(final int size) {
        this.size += size;
    }
}
