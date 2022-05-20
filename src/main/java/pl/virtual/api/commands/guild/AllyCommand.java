package pl.virtual.api.commands.guild;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.*;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

public class AllyCommand extends PlayerCommand
{
    public AllyCommand() {
        super("sojusz", "sojusz manager", "/sojusz <zawrzyj/zerwij> <gildia>", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
        }
        if (!g.isOwner(p)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie jest wlascicielem gildii");
        }
        if (args.length < 2) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final Guild o = GuildManager.getGuild(args[1]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gildia o takim tagu nie istnieje");
        }
        if (g.equals(o)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie mozesz zawrzec sojuszu sam ze soba");
        }
        final String s;
        switch (s = args[0]) {
            case "zerwij": {
                if (!g.getAlly().contains(o.getTag())) {
                    return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz sojuszu z gildia " + o.getTag());
                }
                g.removeAlly(o.getTag());
                o.removeAlly(g.getTag());
                try {
                    NameTagManager.removeAlliance(g, o);
                }
                catch (Exception e1) {
                    Logger.warning("Blad podczas wczytywania tagu ", e1.getMessage());
                }
                return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &7Gildia &8[&c" + g.getTag() + "&8] &7zerwala sojusz z gildia &8[&c" + o.getTag() + "&8]");
            }
            case "zawrzyj": {
                if (g.getAlly().contains(o.getTag())) {
                    return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Posiadasz juz sojusz z gildia " + o.getTag());
                }
                if (g.getAllyinvites().contains(o)) {
                    g.getAllyinvites().remove(o);
                    ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Zaproszenie do sojuszu z gildia &8[&c" + o.getTag() + "&8] &7zostalo cofniete");
                    final Player op = Bukkit.getPlayer(o.getOwner());
                    return op == null || ChatUtil.sendMessage((CommandSender)op, "&8[&4&lGILDIE&8] &7Gildia &8[&c" + g.getTag() + "&8] &7cofnela zaproszenie do sojuszu");
                }
                if (g.getAlly().size() >= g.getSojusz()) {
                    return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Posiadasz maksymalna liczbe sojuszy (&f" + g.getSojusz() + "&7)");
                }
                if (o.getAlly().size() >= o.getSojusz()) {
                    return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gildia " + o.getTag() + "posiada maksymalna liczbe sojuszy (&f" + o.getSojusz() + "&7)");
                }
                final Player op = Bukkit.getPlayer(o.getOwner());
                if (op == null) {
                    return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Lider gildii " + o.getTag() + " jest offline");
                }
                if (!o.getAllyinvites().contains(g)) {
                    g.getAllyinvites().add(o);
                    try {
                        NameTagManager.createAlliance(g, o);
                    }
                    catch (Exception e2) {
                        Logger.warning("Blad podczas wczytywania tagu ", e2.getMessage());
                    }
                    ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Wyslano zaproszenie do sojuszu &8[&c" + o.getTag() + "&8]");
                    ChatUtil.sendMessage((CommandSender)op, "&8&m-------------&8[ &4&lGILDIE &8]&8&m-------------" + g.getTag() + "");
                    ChatUtil.sendMessage((CommandSender)op, "&7Twoja gildia otrzymala zaproszenie do sojuszu z gildia &8[&c" + g.getTag() + "&8]");
                    ChatUtil.sendMessage((CommandSender)op, "&7Wpisz &c/sojusz zawrzyj " + g.getTag() + "&7, aby zaakceptowac");
                    return ChatUtil.sendMessage((CommandSender)op, "&8&m-------------&8[ &4&lGILDIE &8]&8&m-------------c" + g.getTag() + "");
                }
                final String cost = "GOLD_BLOCK:0-0:Bloki Zlota";
                if (!ItemUtil.checkItems(p, "GOLD_BLOCK:0-0:Bloki Zlota", 1)) {
                    ItemUtil.getItem(p, "GOLD_BLOCK:0-0:Bloki Zlota", 1);
                    g.getAllyinvites().remove(o);
                    o.getAllyinvites().remove(g);
                    return true;
                }
                if (!ItemUtil.checkItems(op, "GOLD_BLOCK:0-0:Bloki Zlota", 1)) {
                    ItemUtil.getItem(op, "GOLD_BLOCK:0-0:Bloki Zlota", 1);
                    g.getAllyinvites().remove(o);
                    o.getAllyinvites().remove(g);
                    return true;
                }
                ItemUtil.removeItems(p, "GOLD_BLOCK:0-0:Bloki Zlota", 1);
                ItemUtil.removeItems(op, "GOLD_BLOCK:0-0:Bloki Zlota", 1);
                g.addAlly(o.getTag());
                o.addAlly(g.getTag());
                g.getAllyinvites().remove(o);
                o.getAllyinvites().remove(g);
                return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &7Gildia &8[&c" + g.getTag() + "&8] &7zawarla sojusz z gildia &8[&c" + o.getTag() + "&8]");
            }
            default:
                break;
        }
        return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
    }
}
