package pl.virtual.api.listeners;

import org.bukkit.event.player.*;

import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.command.*;
import org.bukkit.entity.*;

import org.bukkit.event.*;

public class ChatGuildsListener implements Listener
{
    @EventHandler(priority = EventPriority.LOWEST)
    public void onAsyncchat(final AsyncPlayerChatEvent e) {
        final String message = e.getMessage();
        final Player p = e.getPlayer();
        if (message.startsWith("!help")) {
            e.setCancelled(true);
            final Guild g = GuildManager.getGuild(p);
            if (g == null) {
                ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
                return;
            }
            g.message("&a&lGILDIA&8 -> &7 &c" + p.getName() + "&8: &4POMOCY!! Moje kordy to X: " + (int)p.getLocation().getX() + " Y: " + (int)p.getLocation().getY() + " Z: " + (int)p.getLocation().getZ());
            return;
        }
        if (message.startsWith("!!")) {
            e.setCancelled(true);
            final Guild g = GuildManager.getGuild(p);
            if (g == null) {
                ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
                return;
            }
            if (message.equals("!!")) {
                ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie podales wiadomosci");
                return;
            }
            final String msg = message.replaceFirst("!!", "").replace("&", "");
            g.message("&e&lDO SOJUSZY&8 -> &8[&c" + g.getTag() + "&8] &7" + p.getName() + "&8: &4" + msg);
            for (final String s : g.getAlly()) {
                final Guild o = GuildManager.getGuild(s);
                if (o != null) {
                    o.message("&e&lDO SOJUSZY&8 -> &8[&c" + g.getTag() + "&8] &7" + p.getName() + "&8: &4" + msg);
                }
            }
        }
        else if (message.startsWith("!")) {
            final Guild g = GuildManager.getGuild(p);
            if (g == null) {
                ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
                return;
            }
            if (message.equals("!")) {
                ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie podales wiadomosci");
                return;
            }
            final String msg = message.replaceFirst("!", "").replace("&", "");
            g.message("&a&lGILDIA&8 -> &c" + p.getName() + "&8: &4" + msg);
        }
    }
}
