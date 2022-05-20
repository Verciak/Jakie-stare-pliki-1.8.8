// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base.user;

import java.sql.SQLException;

import pl.virtual.api.ServerPlugin;

import java.sql.ResultSet;

public class Mute
{
    private String name;
    private String admin;
    private String reason;
    private long time;
    private long start;
    
    public Mute(final String name, final String admin, final String reason, final long time) {
        this.name = name;
        this.admin = admin;
        this.reason = reason;
        this.time = time;
        this.start = System.currentTimeMillis();
        this.insert();
    }
    
    public Mute(final ResultSet rs) throws SQLException {
        this.name = rs.getString("name");
        this.admin = rs.getString("admin");
        this.reason = rs.getString("reason");
        this.time = rs.getLong("time");
        this.start = rs.getLong("start");
    }
    
    private void insert() {
        ServerPlugin.getStore().update(false, "INSERT INTO `{P}mutes`(`id`, `name`, `admin`, `reason`, `time`, `start`) VALUES (NULL, '" + this.getName() + "','" + this.getAdmin() + "','" + this.getReason() + "','" + this.getTime() + "','" + this.getStart() + "');");
    }
    
    public long getStart() {
        return this.start;
    }
    
    public void setStart(final long start) {
        this.start = start;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(final long time) {
        this.time = time;
    }
    
    public String getReason() {
        return this.reason;
    }
    
    public void setReason(final String reason) {
        this.reason = reason;
    }
    
    public String getAdmin() {
        return this.admin;
    }
    
    public void setAdmin(final String admin) {
        this.admin = admin;
    }
}
