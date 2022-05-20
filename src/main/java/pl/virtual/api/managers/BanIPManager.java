// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.managers;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.data.base.user.BanIP;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.DataUtil;
import pl.virtual.api.utils.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.concurrent.ConcurrentHashMap;

public class BanIPManager
{
    private static ConcurrentHashMap<String, BanIP> bans;
    
    static {
        BanIPManager.bans = new ConcurrentHashMap<String, BanIP>();
    }
    
    public static BanIP getBan(final String name) {
        for (final BanIP ban : BanIPManager.bans.values()) {
            if (ban.getIp().equalsIgnoreCase(name)) {
                return ban;
            }
        }
        return null;
    }
    
    public static BanIP getBan(final Player player) {
        for (final BanIP ban : BanIPManager.bans.values()) {
            if (ban.getIp().equalsIgnoreCase(player.getName())) {
                return ban;
            }
        }
        return null;
    }
    
    public static void addBan(final String name, final BanIP ban, final String player) {
        getBans().put(name, ban);
        final Player p = Bukkit.getPlayer(player);
        if (p != null) {
            final String reason = "&8&m------&8[ &4&lBANIP &8]&8&m------\n\n&7Zostales zbanowany przez Administratora: \n&c&l" + ban.getAdmin() + "\n\n&7Powod Blokady: &c&l" + ban.getReason() + "\n&7Blokada wygasnie: &c&l" + ((ban.getTime() == 0L) ? "&c&lNigdy!" : ("&7za &c&l" + DataUtil.secondsToString(ban.getTime()))) + "\n\n&8&m------&8[ &4&lBANIP &8]&8&m------";
            p.kickPlayer(ChatUtil.fixColor(reason));
        }
    }
    
    public static void unban(final BanIP ban) {
        getBans().remove(ban.getIp());
        ServerPlugin.getStore().update(false, "DELETE FROM `{P}bansip` WHERE `ip` ='" + ban.getIp() + "'");
    }
    
    public static void unbanAll() {
        for (final BanIP ban : getBans().values()) {
            unban(ban);
        }
    }
    
    public static void loadBans() {
        try {
            final ResultSet rs = ServerPlugin.getStore().query("SELECT * FROM `{P}bansip`");
            while (rs.next()) {
                final BanIP b = new BanIP(rs);
                if (b.getTime() != 0L && b.getTime() < System.currentTimeMillis()) {
                    ServerPlugin.getStore().update(false, "DELETE FROM `{P}bansip` WHERE `ip` ='" + b.getIp() + "'");
                }
                else {
                    BanIPManager.bans.put(b.getIp(), b);
                }
            }
            rs.close();
            Logger.info("Loaded " + BanIPManager.bans.size() + " bansip");
        }
        catch (SQLException e) {
            Logger.info("Can not load players Error " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static ConcurrentHashMap<String, BanIP> getBans() {
        return BanIPManager.bans;
    }
}
