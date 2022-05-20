// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.managers;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.rank.tops.AssistManager;
import pl.virtual.api.rank.tops.CoinsManager;
import pl.virtual.api.rank.tops.DeathManager;
import pl.virtual.api.rank.tops.KillManager;
import pl.virtual.api.rank.tops.RankingManager;
import pl.virtual.api.utils.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager
{
    private static final ConcurrentHashMap<String, User> users;
    private static List<Player> vanish;
    
    static {
        users = new ConcurrentHashMap<String, User>();
        UserManager.vanish = new ArrayList<Player>();
    }
    
    public static User getUser(final String name) {
        for (final User u : UserManager.users.values()) {
            if (u.getName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        return null;
    }
    
    public static User getUser(final Player p) {
        for (final User u : UserManager.users.values()) {
            if (u.getName().equalsIgnoreCase(p.getName())) {
                return u;
            }
        }
        return null;
    }
    
    public static void createrUser(final Player p) {
        final User u = new User(p);
        UserManager.users.put(p.getName(), u);
        RankingManager.addRanking(u);
        KillManager.addKill(u);
        DeathManager.addDeath(u);
        AssistManager.addAssist(u);
        CoinsManager.addCoins(u);
    }
    
    public static void createrUser(final String p) {
        final User u = new User(p);
        UserManager.users.put(p, u);
        RankingManager.addRanking(u);
        KillManager.addKill(u);
        DeathManager.addDeath(u);
        AssistManager.addAssist(u);
        CoinsManager.addCoins(u);
    }
    
    public static void deleteUser(final User u) {
        UserManager.users.remove(u.getName());
        RankingManager.removeRanking(u);
        KillManager.removeKill(u);
        DeathManager.removeDeath(u);
        AssistManager.removeAssist(u);
        CoinsManager.removeCoins(u);
        ServerPlugin.getStore().update(false, "DELETE FROM `{P}users` WHERE `name` = '" + u.getName() + "'");
    }
    
    public static boolean canPlaceByBorder(Player player, final Location loc, String string) {
        return Math.abs(Config.BORDER_WORLD_RADIUS - loc.getBlockX()) >= 10 && Math.abs(Config.BORDER_WORLD_RADIUS - loc.getBlockZ()) >= 10 && Math.abs(-Config.BORDER_WORLD_RADIUS - loc.getBlockX()) >= 10 && Math.abs(-Config.BORDER_WORLD_RADIUS - loc.getBlockZ()) >= 10 && Math.abs(-Config.BORDER_WORLD_RADIUS - loc.getBlockX()) >= 10 && Math.abs(Config.BORDER_WORLD_RADIUS - loc.getBlockZ()) >= 10 && Math.abs(Config.BORDER_WORLD_RADIUS - loc.getBlockX()) >= 10 && Math.abs(-Config.BORDER_WORLD_RADIUS - loc.getBlockZ()) >= 10;
    }
    
    public static boolean canPlaceByCenter(Player player, final Location loc, String string) {
        final Guild g = GuildManager.getGuild(loc.getBlock().getLocation());
        return g.getRegion().isInCentrum(loc.getBlock().getLocation(), 16, 16, 16);
    }
    
    public static void enableVanish(final Player player) {
        if (!UserManager.vanish.contains(player)) {
            UserManager.vanish.add(player);
            for (final Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (online.hasPermission("core.vanish.see")) {
                    continue;
                }
                online.hidePlayer(player);
            }
        }
    }
    
    public static void disableVanish(final Player player) {
        if (UserManager.vanish.contains(player)) {
            UserManager.vanish.remove(player);
            for (final Player online : Bukkit.getServer().getOnlinePlayers()) {
                online.showPlayer(player);
            }
        }
    }
    
    public static boolean isVanish(final Player player) {
        return UserManager.vanish.contains(player);
    }
    
    public static List<Player> getVanished() {
        return new ArrayList<Player>(UserManager.vanish);
    }
    
    public static void loadUsers() {
        try {
            final ResultSet rs = ServerPlugin.getStore().query("SELECT * FROM `{P}users`");
            while (rs.next()) {
                final User u = new User(rs);
                UserManager.users.put(u.getName(), u);
                RankingManager.addRanking(u);
                KillManager.addKill(u);
                DeathManager.addDeath(u);
                AssistManager.addAssist(u);
                CoinsManager.addCoins(u);
            }
            rs.close();
            Logger.info("Loaded " + UserManager.users.size() + " users");
        }
        catch (SQLException e) {
            Logger.info("Can not load players Error " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static ConcurrentHashMap<String, User> getUsers() {
        return UserManager.users;
    }
}
