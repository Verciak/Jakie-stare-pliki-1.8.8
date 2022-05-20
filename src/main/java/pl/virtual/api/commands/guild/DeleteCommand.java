package pl.virtual.api.commands.guild;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.scheduler.*;
import org.bukkit.plugin.*;

import pl.virtual.api.*;
import pl.virtual.api.commands.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.*;

public class DeleteCommand extends PlayerCommand
{
    public DeleteCommand() {
        super("zamknij", "usuwa gildie", "/zamknij", "core.cmd.user", new String[] { "usun" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
        }
        if (!g.isOwner(p)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie jestes zalozycielem gildii");
        }
        if (!g.isPreDeleted()) {
            g.setPreDeleted(true);
            ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Potwierdz usuniecie gildii: &c/usun");
            new BukkitRunnable() {
                public void run() {
                    if (g.isPreDeleted()) {
                        g.setPreDeleted(false);
                    }
                }
            }.runTaskLater((Plugin)ServerPlugin.getPlugin(), (long)TimeUtil.MINUTE.getTime(1));
            return true;
        }
        GuildManager.deleteGuild(g);
        try {
            NameTagManager.createGuild(g, p);
        }
        catch (Exception e1) {
            Logger.warning("Blad podczas wczytywania tagu ", e1.getMessage());
        }
        ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &7Gracz &c" + p.getName() + " &7rozwiazal gildie &8[&c" + g.getTag() + "&8]");
        return false;
    }
}
