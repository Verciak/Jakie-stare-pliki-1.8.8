// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base.user;

import java.sql.SQLException;

import pl.virtual.api.ServerPlugin;

import java.sql.ResultSet;

public class BanIP
{
    private String ip;
    private String admin;
    private String reason;
    private long time;
    private long start;
    
    public BanIP(final String ip, final String admin, final String reason, final long time) {
        this.ip = ip;
        this.admin = admin;
        this.reason = reason;
        this.time = time;
        this.start = System.currentTimeMillis();
        this.insert();
    }
    
    public BanIP(final ResultSet rs) throws SQLException {
        this.ip = rs.getString("ip");
        this.admin = rs.getString("admin");
        this.reason = rs.getString("reason");
        this.time = rs.getLong("time");
        this.start = rs.getLong("start");
    }
    
    private void insert() {
        ServerPlugin.getStore().update(false, "INSERT INTO `{P}bansip`(`id`, `ip`, `admin`, `reason`, `time`, `start`) VALUES (NULL, '" + this.getIp() + "','" + this.getAdmin() + "','" + this.getReason() + "','" + this.getTime() + "','" + this.getStart() + "');");
    }
    
    public long getStart() {
        return this.start;
    }
    
    public void setStart(final long start) {
        this.start = start;
    }
    
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(final String ip) {
        this.ip = ip;
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
