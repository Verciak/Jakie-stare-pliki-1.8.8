// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.tasks;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.Combat;
import pl.virtual.api.managers.CombatManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.DataUtil;

public class CombatTask extends BukkitRunnable
{
    
    public void run() {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            final Combat u = CombatManager.getCombat(p);
            if (u == null) {
                continue;
            }
            if (Config.TURBO_DROP > System.currentTimeMillis()) {
                ChatUtil.sendActionBar(p, ChatUtil.fixColor("&c&l TURBO-DROP &8( &f" + DataUtil.secondsToString(Config.TURBO_DROP) + " &8)"));
            }
            if (u.hasFight()) {
                ChatUtil.sendActionBar(p, String.valueOf(ChatUtil.fixColor(new StringBuilder("&4&lAnty-Logout &c&l").append(DataUtil.secondsToString(u.getLastAttactTime()).replace("s", "").replace(" ", "0")).append(" &7[&c").toString())) + DataUtil.secondsToString(u.getLastAttactTime()).replace("30s", "||||||||||||||||||||||||||||||").replace("29s", "|||||||||||||||||||||||||||||").replace("28s", "||||||||||||||||||||||||||||").replace("27s", "|||||||||||||||||||||||||||").replace("26s", "||||||||||||||||||||||||||").replace("25s", "|||||||||||||||||||||||||").replace("24s", "||||||||||||||||||||||||").replace("23s", "|||||||||||||||||||||||").replace("22s", "||||||||||||||||||||||").replace("21s", "|||||||||||||||||||||").replace("20s", "||||||||||||||||||||").replace("19s", "|||||||||||||||||||").replace("18s", "||||||||||||||||||").replace("17s", "|||||||||||||||||").replace("16s", "|||||||||||||||||").replace("15s", "||||||||||||||||").replace("14s", "|||||||||||||||").replace("13s", "||||||||||||||").replace("12s", "||||||||||||").replace("11s", "||||||||||||").replace("10s", "||||||||||").replace("9s", "|||||||||").replace("8s", "||||||||").replace("7s", "|||||||").replace("6s", "||||||").replace("5s", "|||||").replace("4s", "||||").replace("3s", "|||").replace("2s", "||").replace("1s", "|").replace(" ", "0") + "&7]");
            }
            if (UserManager.isVanish(p)) {
                ChatUtil.sendActionBar(p, ChatUtil.fixColor("&4&l&nJESTES NIEWIDOCZNY"));
            }
            else {
                if (!u.wasFight()) {
                    continue;
                }
                if (u.hasFight()) {
                    continue;
                }
                ChatUtil.sendActionBar(p, ChatUtil.fixColor("&aMozesz sie bezpiecznie wylogowac"), 200);
                ChatUtil.sendMessage((CommandSender)p, ChatUtil.fixColor("&cSkonczyles walczyc Mozesz sie bezpiecznie wylogowac"));
                u.setLastAttactkPlayer(null);
                u.setLastAsystPlayer(null);
            }
        }
    }
}
