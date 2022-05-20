package pl.virtual.api.data.base.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.virtual.api.ServerPlugin;


import java.sql.*;

public class Upr
{
    private String name;
    private int break_blocks;
    private int place_blocks;
    private int place_tnt;
    private int break_beacon;
    private int teleport_players;
    private int open_chest;
    private int pvp;
    private int spilling_water;
    private int enlarge;
    private int invite;
    private int prolong;
    private int perms;
   
   
    public Upr(final Player p) {
        this.name = p.getName();
        this.break_blocks = 0;
        this.place_blocks = 0;
        this.place_tnt = 0;
        this.break_beacon = 0;
        this.teleport_players = 0;
        this.open_chest = 0;
        this.pvp = 0;
        this.spilling_water = 0;
        this.enlarge = 0;
        this.invite = 0;
        this.prolong = 0;
        this.perms = 0;
        this.insert();
    }
   
    public Upr(final String p) {
        this.name = p;
        this.break_blocks = 0;
        this.place_blocks = 0;
        this.place_tnt = 0;
        this.break_beacon = 0;
        this.teleport_players = 0;
        this.open_chest = 0;
        this.pvp = 0;
        this.spilling_water = 0;
        this.enlarge = 0;
        this.invite = 0;
        this.prolong = 0;
        this.perms = 0;
        this.insert();
    }
    
    
  
   
    public Upr(final ResultSet rs) throws SQLException {
    	this.name = rs.getString("name");
        this.break_blocks = rs.getInt("break_blocks");
        this.place_blocks = rs.getInt("place_blocks");
        this.place_tnt = rs.getInt("place_tnt");
        this.break_beacon = rs.getInt("break_beacon");
        this.teleport_players = rs.getInt("teleport_players");
        this.open_chest = rs.getInt("open_chest");
        this.pvp = rs.getInt("pvp");
        this.spilling_water = rs.getInt("spilling_water");
        this.enlarge = rs.getInt("enlarge");
        this.invite = rs.getInt("invite");
        this.prolong = rs.getInt("prolong");
        this.perms = rs.getInt("perms");
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
        ServerPlugin.getStore().update(false,"INSERT INTO `{P}perms` (`id`, `name`, `break_blocks`, `place_blocks`, `place_tnt`, `break_beacon`, `teleport_players`, `open_chest`, `spilling_water`, `pvp`, `enlarge`, `invite`, `prolong`, `perms`) VALUES (NULL, '" + this.getName() + "','" + this.getBreak() + "','" + this.getPlace() + "','" + this.getPlacetnt() + "','" + this.getBreakbeacon() + "','" + this.getTeleportplayers() + "','" + this.getOpenchest() + "','" + this.getWater() + "','" + this.getPvp() + "','" + this.getEnlarge() + "','" + this.getInvite() + "','" + this.getProlong() + "','" + this.getPerms() + "')");
    }
    
    public int getBreak() {
        return this.break_blocks;
    }
    
    public void setBreak(final int break_blocks) {
        this.break_blocks = break_blocks;
    }
    
    public int getPlace() {
        return this.place_blocks;
    }
    
    public void setPlace(final int place_blocks) {
        this.place_blocks = place_blocks;
    }
    
    public int getPlacetnt() {
        return this.place_tnt;
    }
    
    public void setPlacetnt(final int place_tnt) {
        this.place_tnt = place_tnt;
    }
    
    public int getBreakbeacon() {
        return this.break_beacon;
    }
    
    public void setBreakbeacon(final int break_beacon) {
        this.break_beacon = break_beacon;
    }
    
    public int getTeleportplayers() {
        return this.teleport_players;
    }
    
    public void setTeleportplayers(final int teleport_players) {
        this.teleport_players = teleport_players;
    }
    
    public int getOpenchest() {
        return this.open_chest;
    }
    
    public void setOpenchest(final int open_chest) {
        this.open_chest = open_chest;
    }
    
    public int getWater() {
        return this.spilling_water;
    }
    
    public void setWater(final int spilling_water) {
        this.spilling_water = spilling_water;
    }
    
    public int getPvp() {
        return this.pvp;
    }
    
    public void setPvp(final int pvp) {
        this.pvp = pvp;
    }
    
    public int getEnlarge() {
        return this.enlarge;
    }
    
    public void setEnlarge(final int enlarge) {
        this.enlarge = enlarge;
    }
    
    public int getInvite() {
        return this.invite;
    }
    
    public void setInvite(final int invite) {
        this.invite = invite;
    }
    
    public int getProlong() {
        return this.prolong;
    }
    
    public void setProlong(final int prolong) {
        this.prolong = prolong;
    }
    
    public int getPerms() {
        return this.perms;
    }
    
    public void setPerms(final int perms) {
        this.perms = perms;
    }
    
    public void save() {
        ServerPlugin.getStore().update(false,"UPDATE `{P}perms` SET `break_blocks` = '" + this.getBreak() + "', `place_blocks` = '" + this.getPlace() + "', `place_tnt` = '" + this.getPlacetnt() + "', `break_beacon` = '" + this.getBreakbeacon() + "', `teleport_players` = '" + this.getTeleportplayers() + "', `open_chest` = '" + this.getOpenchest() + "', `spilling_water` = '" + this.getWater() + "', `pvp` = '" + this.getPvp() + "', `enlarge` = '" + this.getEnlarge() + "', `invite` = '" + this.getInvite() + "', `prolong` = '" + this.getProlong() + "', `perms` = '" + this.getPerms() + "' WHERE `name` ='" + this.getName() + "';");
    }  
}

