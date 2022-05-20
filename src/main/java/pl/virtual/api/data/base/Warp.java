// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base;

import java.sql.SQLException;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.utils.ChatUtil;

import java.sql.ResultSet;
import org.bukkit.Location;

public class Warp
{
    private String name;
    private Location location;
    private String pex;
    
    public Warp(final String name, final Location location, final String pex) {
        this.name = name;
        this.location = location;
        this.pex = pex;
        this.insert();
    }
    
    public Warp(final ResultSet rs) throws SQLException {
        this.name = rs.getString("name");
        this.location = ChatUtil.locFromString(rs.getString("location"));
        this.pex = rs.getString("pex");
    }
    
    private void insert() {
        ServerPlugin.getStore().update(false, "INSERT INTO `{P}warp`(`id`, `name`, `location`, `pex`) VALUES (NULL, '" + this.getName() + "','" + ChatUtil.locToString(this.getLocation()) + "','" + this.getPex() + "');");
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public void setLocation(final Location location) {
        this.location = location;
    }
    
    public String getPex() {
        return this.pex;
    }
    
    public void setPex(final String pex) {
        this.pex = pex;
    }
}
