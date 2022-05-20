package pl.virtual.api.commands.guild;

import org.bukkit.entity.*;
import org.bukkit.command.*;

import pl.virtual.api.commands.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.rank.tops.*;
import pl.virtual.api.utils.*;
import org.apache.commons.lang.*;

import java.util.*;
import org.bukkit.*;

public class InfoCommand extends PlayerCommand
{
    public InfoCommand() {
        super("gildia", "informacje o gildii", "/gildia <tag>", "core.cmd.user", new String[] { "info", "ginfo" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        Guild g = null;
        if (args.length == 0) {
            g = GuildManager.getGuild(p);
        }
        else {
            g = GuildManager.getGuild(args[0]);
        }
        if (g == null && args.length == 0) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
        }
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gildia o takim tagu nie istnieje");
        }
        ChatUtil.sendMessage((CommandSender)p, "&8&m-----------&8[&r &a&l" + g.getTag() + " &8]&8&m-----------");
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Zalozyciel: &6" + g.getOwner());
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Zastepca: &6" + ((g.getLeader() == null || g.getLeader().equalsIgnoreCase("null")) ? "Brak" : g.getLeader()));
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Pozycja: &6" + RankingManager.getPlaceGuild(g));
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Punkty: &6" + g.getPoints());
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Zabojstwa: &6" + g.getKills());
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Smierci: &6" + g.getDeaths());
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Zycia: &6" + g.getLife());
        ChatUtil.sendMessage((CommandSender)p, "&8» &7HP: &6" + g.getHp());
        final int size = g.getRegion().getSize() * 2 + 1;
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Teren &c&l" + size + "&8x&c&l" + size);
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Czlonkow: &c" + g.getMembers().size() + "&8, &7Online: &c" + g.getOnlineMembers().size());
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Czlonkowie: " + StringUtils.join((Object[])getMemberList(g.getMembers()), "&8, "));
        ChatUtil.sendMessage((CommandSender)p, "&8&m-----------&8[&r &a&l" + g.getTag() + " &8]&8&m-----------");
        return false;
    }
    
    public static String[] getMemberList(final Set<String> members) {
        final String[] s = new String[members.size()];
        int i = 0;
        for (final String u : members) {
            final OfflinePlayer op = Bukkit.getOfflinePlayer(u);
            s[i] = "&7" + (op.isOnline() ? "&a" : "&7") + op.getName();
            ++i;
        }
        return s;
    }
}
