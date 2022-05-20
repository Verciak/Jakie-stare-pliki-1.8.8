package pl.virtual.api.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.entity.Player;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.data.base.user.Upr;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.rank.tops.AssistManager;
import pl.virtual.api.rank.tops.CoinsManager;
import pl.virtual.api.rank.tops.DeathManager;
import pl.virtual.api.rank.tops.KillManager;
import pl.virtual.api.rank.tops.RankingManager;
import pl.virtual.api.utils.Logger;

public class UprManager
{
    private static final ConcurrentHashMap<String, Upr> users;
    
    static {
        users = new ConcurrentHashMap<String, Upr>();
    }
    
    public static Upr getUser(final String name) {
        for (final Upr u : UprManager.users.values()) {
            if (u.getName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        return null;
    }
    
    public static Upr getUser(final Player p) {
        for (final Upr u : UprManager.users.values()) {
            if (u.getName().equalsIgnoreCase(p.getName())) {
                return u;
            }
        }
        return null;
    }
    
    public static void createrUser(final Player p) {
        final Upr u = new Upr(p);
        UprManager.users.put(p.getName(), u);
    }
    
    public static void createrUser(final String p) {
        final Upr u = new Upr(p);
        UprManager.users.put(p, u);
    }
    
    public static void deleteUser(final Upr u) {
        UprManager.users.remove(u.getName());
        ServerPlugin.getStore().update(false, "DELETE FROM `{P}perms` WHERE `name` = '" + u.getName() + "'");
    }
    
    public static void loadUsers() {
        try {
            final ResultSet rs = ServerPlugin.getStore().query("SELECT * FROM `{P}perms`");
            while (rs.next()) {
                final Upr u = new Upr(rs);
                UprManager.users.put(u.getName(), u);
            }
            rs.close();
            Logger.info("Loaded " + UprManager.users.size() + " perms");
        }
        catch (SQLException e) {
            Logger.info("Can not load players Error " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static ConcurrentHashMap<String, Upr> getUsers() {
        return UprManager.users;
    }
}