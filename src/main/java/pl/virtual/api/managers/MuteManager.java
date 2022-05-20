// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.managers;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.data.base.user.Mute;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.concurrent.ConcurrentHashMap;

public class MuteManager
{
    public static ConcurrentHashMap<String, Mute> mutes;
    
    static {
        MuteManager.mutes = new ConcurrentHashMap<String, Mute>();
    }
    
    public static Mute getMute(final String name) {
        for (final Mute mute : MuteManager.mutes.values()) {
            if (mute.getName().equalsIgnoreCase(name)) {
                return mute;
            }
        }
        return null;
    }
    
    public static Mute getMute(final Player player) {
        for (final Mute mute : MuteManager.mutes.values()) {
            if (mute.getName().equalsIgnoreCase(player.getName())) {
                return mute;
            }
        }
        return null;
    }
    
    public static void addMute(final String name, final Mute mute) {
        getMutes().put(name, mute);
        final Player p = Bukkit.getPlayer(name);
        if (p != null) {
            ChatUtil.sendMessage((CommandSender)p, "&8[&4&lMUTE&8] &cZostales wyciszony przez &4" + mute.getAdmin() + " &7(&f" + mute.getReason() + "&7)");
        }
    }
    
    public static void unmute(final Mute mute) {
        getMutes().remove(mute.getName());
        ServerPlugin.getStore().update(false, "DELETE FROM `{P}mutes` WHERE `name` ='" + mute.getName() + "'");
    }
    
    public static void unmuteAll() {
        for (final Mute mute : getMutes().values()) {
            unmute(mute);
        }
    }
    
    public static void loadMutes() {
        try {
            final ResultSet rs = ServerPlugin.getStore().query("SELECT * FROM `{P}mutes`");
            while (rs.next()) {
                final Mute m = new Mute(rs);
                if (m.getTime() != 0L && m.getTime() < System.currentTimeMillis()) {
                    ServerPlugin.getStore().update(false, "DELETE FROM `{P}mutes` WHERE `name` ='" + m.getName() + "'");
                }
                else {
                    MuteManager.mutes.put(m.getName(), m);
                }
            }
            rs.close();
            Logger.info("Loaded " + MuteManager.mutes.size() + " mutes");
        }
        catch (SQLException e) {
            Logger.info("&9&lERROR: &7Nie mozna zaladowac wyciszonych graczy " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static ConcurrentHashMap<String, Mute> getMutes() {
        return MuteManager.mutes;
    }
}
