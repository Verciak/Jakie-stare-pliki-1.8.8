// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.managers;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.data.base.Warp;
import pl.virtual.api.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import java.util.concurrent.ConcurrentHashMap;

public class WarpManager
{
    public static ConcurrentHashMap<String, Warp> warps;
    
    static {
        WarpManager.warps = new ConcurrentHashMap<String, Warp>();
    }
    
    public static Warp getWarp(final String name) {
        for (final Warp w : WarpManager.warps.values()) {
            if (name.equalsIgnoreCase(w.getName())) {
                return w;
            }
        }
        return null;
    }
    
    public static void addWarp(final String name, final Location location, final String pex) {
        WarpManager.warps.put(name, new Warp(name, location, pex));
    }
    
    public static void deleteWarp(final String name) {
        WarpManager.warps.remove(name);
        ServerPlugin.getStore().update(false, "DELETE FROM `{P}warp` WHERE `name` ='" + name + "';");
    }
    
    public static List<String> getWarpByGroup(final Player p) {
        final List<String> warp = new ArrayList<String>();
        for (final Warp w : WarpManager.warps.values()) {
            if (p.hasPermission(w.getPex())) {
                warp.add(w.getName());
            }
        }
        return warp;
    }
    
    public static void loadWarp() {
        try {
            final ResultSet rs = ServerPlugin.getStore().query("SELECT * FROM `{P}warp`");
            while (rs.next()) {
                final Warp w = new Warp(rs);
                WarpManager.warps.put(w.getName(), w);
            }
            rs.close();
            Logger.info("Loaded " + WarpManager.warps.size() + " warps");
        }
        catch (SQLException e) {
            Logger.info("Can not load warps Error " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static ConcurrentHashMap<String, Warp> getWarps() {
        return WarpManager.warps;
    }
}
