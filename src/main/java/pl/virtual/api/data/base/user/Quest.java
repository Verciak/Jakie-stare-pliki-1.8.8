package pl.virtual.api.data.base.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.virtual.api.ServerPlugin;


import java.sql.*;

public class Quest
{
    private String name;
    private int heads;
   
   
    public Quest(final Player p) {
        this.name = p.getName();
        this.heads = 0;
        this.insert();
    }
   
    public Quest(final String p) {
        this.name = p;
        this.heads = 0;
        this.insert();
    }
    
    public Quest(final ResultSet rs) throws SQLException {
    	this.name = rs.getString("name");
        this.heads = rs.getInt("heads");
    }
   
    public String getName() {
        return this.name;
    }
   
    public Player getPlayer() {
        return Bukkit.getPlayer(this.getName());
    }
   
    public boolean isOnline() {
        return this.getPlayer() != null;
    }
    
    public void insert() {
        ServerPlugin.getStore().update(false,"INSERT INTO `{P}quest` (`id`, `name`, `heads`) VALUES (NULL, '" + this.getName() + "','" + this.getHeads() + "')");
    }
    
    public int getHeads() {
        return this.heads;
    }
    
    public void setHeads(final int heads) {
        this.heads = heads;
    }
    
    public void addHeads(final int paramInt) {
        this.heads += paramInt;
    }
    
    public void save() {
        ServerPlugin.getStore().update(false,"UPDATE `{P}quest` SET `heads` = '" + this.getHeads() + "' WHERE `name` ='" + this.getName() + "';");
    }  
}

