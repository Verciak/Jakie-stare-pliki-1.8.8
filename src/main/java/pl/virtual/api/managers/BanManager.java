// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.managers;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.data.base.user.Ban;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.DataUtil;
import pl.virtual.api.utils.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.concurrent.ConcurrentHashMap;

public class BanManager
{
    private static ConcurrentHashMap<String, Ban> bans;
    
    static {
        BanManager.bans = new ConcurrentHashMap<String, Ban>();
    }
    
    public static Ban getBan(final String name) {
        for (final Ban ban : BanManager.bans.values()) {
            if (ban.getName().equalsIgnoreCase(name)) {
                return ban;
            }
        }
        return null;
    }
    
    public static Ban getBan(final Player player) {
        for (final Ban ban : BanManager.bans.values()) {
            if (ban.getName().equalsIgnoreCase(player.getName())) {
                return ban;
            }
        }
        return null;
    }
    
    public static void addBan(final String name, final Ban ban) {
        getBans().put(name, ban);
        final Player p = Bukkit.getPlayer(name);
        if (p != null) {
            final String reason = "&8&m------&8[ &4&lBAN &8]&8&m------\n\n&7Zostales zbanowany przez Administratora: \n&c&l" + ban.getAdmin() + "\n\n&7Powod Blokady: &c&l" + ban.getReason() + "\n&7Blokada wygasnie: &c&l" + ((ban.getTime() == 0L) ? "&c&lNigdy" : ("&7za &c&l" + DataUtil.secondsToString(ban.getTime()))) + "\n\n     &7UnBana mozesz zakupic na: \n&c&lhttp://nomenhc.eu/\n\n&8&m------&8[ &4&lBAN &8]&8&m------";
            p.kickPlayer(ChatUtil.fixColor(reason));
        }
    }
    
    public static void unban(final Ban ban) {
        getBans().remove(ban.getName());
        ServerPlugin.getStore().update(false, "DELETE FROM `{P}bans` WHERE `name` ='" + ban.getName() + "'");
    }
    
    public static void unbanAll() {
        for (final Ban ban : getBans().values()) {
            unban(ban);
        }
    }
    
    public static void loadBans() {
        try {
            final ResultSet rs = ServerPlugin.getStore().query("SELECT * FROM `{P}bans`");
            while (rs.next()) {
                final Ban b = new Ban(rs);
                if (b.getTime() != 0L && b.getTime() < System.currentTimeMillis()) {
                    ServerPlugin.getStore().update(false, "DELETE FROM `{P}bans` WHERE `name` ='" + b.getName() + "'");
                }
                else {
                    BanManager.bans.put(b.getName(), b);
                }
            }
            rs.close();
            Logger.info("Loaded " + BanManager.bans.size() + " bans");
        }
        catch (SQLException e) {
            Logger.info("Can not load players Error " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static ConcurrentHashMap<String, Ban> getBans() {
        return BanManager.bans;
    }
}
