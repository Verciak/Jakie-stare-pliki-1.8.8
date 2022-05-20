// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.managers;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.data.base.user.DeathBan;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.DataUtil;
import pl.virtual.api.utils.Logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;
import java.util.concurrent.ConcurrentHashMap;

public class DeathBanManager
{
    private static ConcurrentHashMap<String, DeathBan> bans;
    
    static {
        DeathBanManager.bans = new ConcurrentHashMap<String, DeathBan>();
    }
    
    public static DeathBan getBan(final String name) {
        for (final DeathBan ban : DeathBanManager.bans.values()) {
            if (ban.getName().equalsIgnoreCase(name)) {
                return ban;
            }
        }
        return null;
    }
    
    public static DeathBan getBan(final Player player) {
        for (final DeathBan ban : DeathBanManager.bans.values()) {
            if (ban.getName().equalsIgnoreCase(player.getName())) {
                return ban;
            }
        }
        return null;
    }
    
    public static void addBan(final Player p) {
        if (p.hasPermission("core.cmd.mod")) {
            return;
        }
        final DeathBan ban = getBan(p.getName());
        if (ban != null) {
            ban.ban(p);
            return;
        }
        final DeathBan b = new DeathBan(p.getName());
        DeathBanManager.bans.put(p.getName(), b);
        if (p.isOnline()) {
            return;
        }
        new BukkitRunnable() {
            public void run() {
                final String reason = "&8&m------===------\n&4Umarles &c:(\n&4Masz bana do: &c" + DataUtil.getDate(b.getTime()) + "\n&4Wygasa: &c" + DataUtil.secondsToString(b.getTime()) + "\n&8&m------===------";
                p.getPlayer().kickPlayer(ChatUtil.fixColor(reason));
            }
        }.runTaskLater((Plugin)ServerPlugin.getPlugin(), 1L);
    }
    
    public static void unban(final DeathBan ban) {
        ban.setTime(0L);
        ServerPlugin.getStore().update(false, "UPDATE `{P}deathbans` SET `time` ='" + ban.getTime() + "' WHERE `name` ='" + ban.getName() + "'");
    }
    
    public static void unbanAll() {
        for (final DeathBan ban : getBans().values()) {
            unban(ban);
        }
    }
    
    public static void loadDeathsBans() {
        try {
            final ResultSet rs = ServerPlugin.getStore().query("SELECT * FROM `{P}deathbans`");
            while (rs.next()) {
                final DeathBan b = new DeathBan(rs);
                DeathBanManager.bans.put(b.getName(), b);
            }
            rs.close();
            Logger.info("Loaded " + DeathBanManager.bans.size() + " deathsbans");
        }
        catch (SQLException e) {
            Logger.info("Can not load players Error " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static ConcurrentHashMap<String, DeathBan> getBans() {
        return DeathBanManager.bans;
    }
}
