package pl.virtual.api.commands.guild;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.apache.commons.lang.*;

import pl.virtual.api.API.Config;
import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.data.base.user.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

public class GuildAdminCommand extends Command
{
    public GuildAdminCommand() {
        super("guildadmin", "Zarzadzanie gildiami", "/guildadmin", "core.cmd.guildadmin", new String[] { "ga" });
    }
    
    private boolean usage(final CommandSender sender) {
        if (sender.hasPermission("core.cmd.admin")) {
            ChatUtil.sendMessage(sender, "&8&m----------&8[ &a&lGUILD ADMIN &8]&8&m----------");
            ChatUtil.sendMessage(sender, "&8» &4/ga tp &7<gildia>");
            ChatUtil.sendMessage(sender, "&8» &4/ga ban &7<gildia> <czas> <powod>");
            ChatUtil.sendMessage(sender, "&8» &4/ga unban &7<gildia>");
            ChatUtil.sendMessage(sender, "&8» &4/ga usun &7<gildia>");
            ChatUtil.sendMessage(sender, "&8» &4/ga zapros &7<gildia> <gracz>");
            ChatUtil.sendMessage(sender, "&8» &4/ga wyrzuc &7<gildia> <gracz>");
            ChatUtil.sendMessage(sender, "&8» &4/ga dolacz &7<gildia> <gracz>");
            ChatUtil.sendMessage(sender, "&8» &4/ga powieksz &7<gildia> <rozmiar>");
            ChatUtil.sendMessage(sender, "&8» &4/ga lider &7<gildia> <gracz>");
            ChatUtil.sendMessage(sender, "&8» &4/ga zalozyciel &7<gildia> <gracz>");
            ChatUtil.sendMessage(sender, "&8» &4/ga odnow &7<gildia>");
            ChatUtil.sendMessage(sender, "&8» &4/ga pvp &7<gildia>");
            ChatUtil.sendMessage(sender, "&8» &4/ga points &7<gildia> <pkt>");
            ChatUtil.sendMessage(sender, "&8» &4/ga kills &7<gildia> <pkt>");
            ChatUtil.sendMessage(sender, "&8» &4/ga deaths &7<gildia> <pkt>");
            ChatUtil.sendMessage(sender, "&8» &4/ga coins &7<gildia> <ilosc>");
            ChatUtil.sendMessage(sender, "&8&m----------&8[ &a&lGUILD ADMIN &8]&8&m----------");
        }
        return true;
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return this.usage(sender);
        }
        final Guild g = GuildManager.getGuild(args[1]);
        if (g == null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gildia nie istnieje");
        }
        final String s3;
        switch (s3 = args[0]) {
            case "deaths": {
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                if (args.length != 3) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga deaths <gildia> <pkt>");
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7To nie liczba");
                }
                final int pkt = Integer.parseInt(args[2]);
                g.setDeaths(pkt);
                return ChatUtil.sendMessage(sender, "&8[&4&lGILDIE&8] &7Ustawiles smierci gildii &c" + g.getTag() + " &7na &c" + pkt);
            }
            case "dolacz": {
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                if (args.length != 3) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga dolacz <gildia> <gracz>");
                }
                if (args.length < 2) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/ga dolacz <gildia> <gracz>"));
                }
                final Player p = Bukkit.getPlayer(args[2]);
                if (p == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz jest offline");
                }
                final Guild og = GuildManager.getGuild(p);
                if (og != null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz posiada juz gildie");
                }
                if (g.getInvites().contains(p)) {
                    g.getInvites().remove(p);
                }
                g.addMember(p);
                NameTagManager.joinToGuild(g, p);
                return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &7Gracz &c" + p.getName() + " &7dolaczyl do gildii &8[&c" + g.getTag() + "&8]");
            }
            case "points": {
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                if (args.length != 3) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga points <gildia> <pkt>");
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7To nie liczba");
                }
                final int pkt = Integer.parseInt(args[2]);
                g.setPoints(pkt);
                return ChatUtil.sendMessage(sender, "&8[&4&lGILDIE&8] &7Ustawiles ranking gildii &c" + g.getTag() + " &7na &c" + pkt);
            }
            case "wyrzuc": {
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                if (args.length != 3) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga wyrzuc <gildia> <gracz>");
                }
                if (args.length < 2) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/ga wyrzuc <gildia> <gracz>"));
                }
                final User p2 = UserManager.getUser(args[2]);
                final Guild og = GuildManager.getGuildByNamePlayer(p2.getName());
                if (og == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie posiada gildii");
                }
                if (!g.isMember(p2.getName())) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie nalezy do tej gildii");
                }
                if (g.isOwner(p2.getName())) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Nie mozesz wyrzucic wlasciciela gildii");
                }
                if (g.isLeader(p2.getName())) {
                    g.setLeader("Brak");
                }
                g.removeMember(p2.getName());
                NameTagManager.leaveFromGuild(g, p2.getOfflinePlayer());
                return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &7Gracz &c" + p2.getName() + " &7zostal wyrzucony z gildii &8[&c" + g.getTag() + "&8]");
            }
            case "zapros": {
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                if (args.length != 3) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga zapros <gildia> <gracz>");
                }
                if (args.length < 2) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/ga zapros <gildia> <gracz>"));
                }
                final Player p = Bukkit.getPlayer(args[2]);
                if (p == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz jest offline");
                }
                final Guild og = GuildManager.getGuild(p);
                if (og != null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz posiada juz gildie");
                }
                if (g.getInvites().contains(p)) {
                    g.getInvites().remove(p);
                    ChatUtil.sendMessage(sender, "&8[&4&lGILDIE&8] &7Cofnales  zaproszenie dla gracza &c" + p.getName());
                    return ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Twoje zaproszenie do gildii &8[&c" + g.getTag() + "&8] &7zostalo cofniete");
                }
                g.getInvites().add(p);
                ChatUtil.sendMessage(sender, "&8[&4&lGILDIE&8] &7Wyslales zaproszenie dla gracza &c" + p.getName() + "");
                ChatUtil.sendMessage((CommandSender)p, "&8&m-------------&8[ &4&lGILDIE &8]&8&m-------------");
                ChatUtil.sendMessage((CommandSender)p, "&7Zostales zaproszony do gildii &8[&c" + g.getTag() + "&8]");
                ChatUtil.sendMessage((CommandSender)p, "&7Wpisz &c/dolacz " + g.getTag() + " aby dolaczyc");
                return ChatUtil.sendMessage((CommandSender)p, "&8&m-------------&8[ &4&lGILDIE &8]&8&m-------------");
            }
            case "tp": {
                if (args.length < 2) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/ga tp <gildia>"));
                }
                if (args.length != 2) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga tp <gildia>");
                }
                final Player p = (Player)sender;
                final double y = Bukkit.getWorlds().get(0).getHighestBlockYAt(g.getRegion().getX(), g.getRegion().getZ()) + 1.5f;
                p.teleport(new Location((World)Bukkit.getWorlds().get(0), g.getRegion().getLocation().getX(), y, g.getRegion().getLocation().getZ()));
                ChatUtil.sendMessage(sender, "&8[&4&lGILDIE&8] &7Zostales przeteleportowany do gildii &8[&c" + g.getTag() + "&8]");
                String msg5 = "";
                msg5 = msg5.replace("{G}", g.getTag());
                String msg6 = "";
                msg6 = msg6.replace("{PLAYER}", p.getName());
                msg6 = msg6.replace("{G}", g.getName());
                ChatUtil.sendTitleMessage(p, msg5, msg6, 30, 70, 40);
                return false;
            }
            case "ban": {
                if (args.length < 3) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/ga ban <gildia> <czas> <powod>"));
                }
                if (args.length != 4) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga ban <gildia> <czas> <powod>");
                }
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                String reason = "Brak!";
                if (args.length > 3) {
                    reason = StringUtils.join((Object[])args, " ", 3, args.length);
                }
                for (final String s : g.getMembers()) {
                    final Ban b = BanManager.getBan(s);
                    if (b == null) {
                        final long time = DataUtil.parseDateDiff(args[2], true);
                        final Ban ban = new Ban(s, sender.getName(), "(GILDIA) " + reason, time);
                        BanManager.addBan(s, ban);
                    }
                }
                final long time2 = DataUtil.parseDateDiff(args[2], true);
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&4&lGILDIE&8] &7Gildia &8[&c" + g.getTag() + "&8] &7zostala tymczasowo zbanowana przez &c" + sender.getName() + " &7do: &c" + DataUtil.getDate(time2) + " &7powod: &c" + reason));
                return false;
            }
            case "pvp": {
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                if (args.length != 2) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga pvp <gildia>");
                }
                if (args.length == 2) {
                    g.setPvp(!g.isPvp());
                    for (final Player o : g.getOnlineMembers()) {
                        ChatUtil.sendMessage((CommandSender)o, "&8[&4&lGILDIE&8] &7Gracz &c" + sender.getName() + (g.isPvp() ? " &awlaczyl" : " &cwylaczyl") + " &7pvp w gildii");
                    }
                    return true;
                }
                final String s4;
                switch ((s4 = args[2]).hashCode()) {
                    case -896830130: {
                        if (!s4.equals("sojusz")) {
                            return true;
                        }
                        break;
                    }
                    case 2996984: {
                        if (!s4.equals("ally")) {
                            return true;
                        }
                        break;
                    }
                    case 1806944311: {
                        if (!s4.equals("alliance")) {
                            return true;
                        }
                        break;
                    }
                }
                g.setPvpAlly(!g.isPvpAlly());
                for (final Player o2 : g.getOnlineMembers()) {
                    ChatUtil.sendMessage((CommandSender)o2, "&8[&4&lGILDIE&8] &7Gracz &c" + sender.getName() + (g.isPvpAlly() ? " &awlaczyl" : " &cwylaczyl") + " &7pvp w sojuszu");
                }
                return true;
            }
            case "usun": {
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                if (args.length != 2) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga usun <gildia>");
                }
                GuildManager.deleteGuild(g);
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&4&lGILDIE&8] &7Gildia &8[&c" + g.getTag() + "&8] &7zostala usunieta przez &c" + sender.getName() + ""));
                return false;
            }
            case "coins": {
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                if (args.length != 3) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga coins <gildia> <ilosc>");
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7To nie liczba");
                }
                final int pkt = Integer.parseInt(args[2]);
                g.addCoins(pkt);
                return ChatUtil.sendMessage(sender, "&8[&4&lGILDIE&8] &7Ustawiles coinsy gildii &c" + g.getTag() + " &7na &c" + pkt);
            }
            case "kills": {
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                if (args.length != 3) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga kills <gildia> <ilosc>");
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7To nie liczba");
                }
                final int pkt = Integer.parseInt(args[2]);
                g.setKills(pkt);
                return ChatUtil.sendMessage(sender, "&8[&4&lGILDIE&8] &7Ustawiles zabojstwa gildii &8[&c" + g.getTag() + "&8] &7na &c" + pkt);
            }
            case "lider": {
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                if (args.length != 3) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga lider <gildia> <gracz>");
                }
                if (args.length < 2) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/ga lider <gildia> <gracz>"));
                }
                final Player p = Bukkit.getPlayer(args[2]);
                if (p == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz jest offline");
                }
                final Guild og = GuildManager.getGuild(p);
                if (og == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie posiada gildii");
                }
                if (!g.isMember(p.getName())) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie jest w tej gildii");
                }
                if (g.isOwner(p.getName())) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz jest wlascicielem gildii");
                }
                if (g.isLeader(p.getName())) {
                    g.setLeader("Brak");
                    ChatUtil.sendMessage(sender, "&8[&4&lGILDIE&8] &7Zdegradowales &c" + p.getName() + " &7z zastepcy gildii");
                    return ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Zostales zdegradowany z zastepcy gildii");
                }
                g.setLeader(p.getName());
                ChatUtil.sendMessage(sender, "&8[&4&lGILDIE&8] &7Awansowales &c" + p.getName() + " &7na zastepce gildii");
                return ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &aZostales awansowany na zastepce gildii");
            }
            case "odnow": {
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                if (args.length != 2) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga odnow <gildia>");
                }
                if (args.length < 2) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/ga odnow <gildia>"));
                }
                if (g.getProlong() >= System.currentTimeMillis() + 86400 * Config.PROLONG_ADD * 1000) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gildia jest juz odnowiona na maksymalny okres &8(&f" + Config.PROLONG_MAX + " dni&8)");
                }
                final long prolong = g.getProlong() + 86400000L;
                g.setProlong(prolong);
                return ChatUtil.sendMessage(sender, "&8[&4&lGILDIE&8] &7Odnowiles gildie o 1 dzien");
            }
            case "unban": {
                if (args.length < 1) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/ga unban <gildia>"));
                }
                if (args.length != 2) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga unban <gildia>");
                }
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                for (final String s2 : g.getMembers()) {
                    final Ban b2 = BanManager.getBan(s2);
                    if (b2 != null && b2.getReason().contains("(GILDIA)")) {
                        BanManager.unban(b2);
                    }
                }
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&4&lGILDIE&8] &7Gildia &8[&c" + g.getTag() + "&8] &7zostala odbanowan przez &c" + sender.getName()));
                return false;
            }
            case "zalozyciel": {
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                if (args.length != 3) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga zalozyciel <gildia> <gracz>");
                }
                if (args.length < 2) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/ga zalozyciel <gildia> <gracz>"));
                }
                final Player p = Bukkit.getPlayer(args[2]);
                if (p == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz jest offline");
                }
                final Guild og = GuildManager.getGuild(p);
                if (og == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie posiada gildii");
                }
                if (!g.isMember(p.getName())) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie jest w tej gildii");
                }
                if (g.isOwner(p.getName())) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz jest wlascicielem gildii");
                }
                g.setOwner(p.getName());
                ChatUtil.sendMessage(sender, "&8[&4&lGILDIE&8] &7Awansowales &c" + p.getName() + " &7na wlasciciela gildii");
                return ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Zostales awansowany na wlasciciela gildii");
            }
            case "powieksz": {
                if (!sender.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                if (args.length != 3) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7/ga powieksz <gildia> <rozmiar>");
                }
                if (args.length < 2) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/ga powieksz <gildia> <rozmiar>"));
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7To nie jest liczba");
                }
                final int i = Integer.parseInt(args[2]);
                g.getRegion().setSize(i);
                final int size = i * 2 + 1;
                return ChatUtil.sendMessage(sender, "&8[&4&lGILDIE&8] &7Ustawiles rozmiar cuboida gildii na PROMIEN &c" + i + " " + size + "&7x&c" + size + "");
            }
            default:
                break;
        }
        return this.usage(sender);
    }
}
