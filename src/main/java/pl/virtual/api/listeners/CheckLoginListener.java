package pl.virtual.api.listeners;

import org.bukkit.event.player.*;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.user.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class CheckLoginListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onLogin(final PlayerLoginEvent e) {
        final Player p = e.getPlayer();
        final Ban ban = BanManager.getBan(p);
        if (ban != null) {
            if (ban.getTime() != 0L && ban.getTime() <= System.currentTimeMillis()) {
                BanManager.unban(ban);
                return;
            }
            final String reason = "\n&8&m------&8[ &4&lBAN &8]&8&m------\n\n&7Zostales zbanowany przez Administratora: \n&c&l" + ban.getAdmin() + "\n\n&7Powod Blokady: &c&l" + ban.getReason() + "\n&7Blokada wygasnie: &c&l" + ((ban.getTime() == 0L) ? "&c&lNigdy" : ("&7za &c&l" + DataUtil.secondsToString(ban.getTime()))) + "\n\n     &7UnBana mozesz zakupic na: \n&c&lhttp://nomenhc.eu/\n\n&8&m------&8[ &4&lBAN &8]&8&m------";
            e.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatUtil.fixColor(reason));
        }
        else {
            final BanIP banip = BanIPManager.getBan(e.getAddress().getHostAddress());
            if (banip != null) {
                if (banip.getTime() != 0L && banip.getTime() <= System.currentTimeMillis()) {
                    BanIPManager.unban(banip);
                    return;
                }
                final String reason2 = "\n&8&m------&8[ &4&lBANIP &8]&8&m------\n\n&7Zostales zbanowany przez Administratora: \n&c&l" + banip.getAdmin() + "\n\n&7Powod Blokady: &c&l" + banip.getReason() + "\n&7Blokada wygasnie: &c&l" + ((banip.getTime() == 0L) ? "&c&lNigdy!" : ("&7za &c&l" + DataUtil.secondsToString(banip.getTime()))) + "\n\n&8&m------&8[ &4&lBANIP &8]&8&m------";
                e.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatUtil.fixColor(reason2));
            }
            else {
                if (Config.WL_ENABLE && !Config.WL_LIST.contains(p.getName())) {
                    e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "\n" + ChatUtil.fixColor(Config.WL_REASON));
                    return;
                }
                if (!p.hasPermission("core.join.bypass") && Bukkit.getOnlinePlayers().size() >= Config.SLOT) {
                    e.disallow(PlayerLoginEvent.Result.KICK_FULL, ChatUtil.fixColor("&cSerwer jest zapelniony"));
                }
            }
        }
    }
}
