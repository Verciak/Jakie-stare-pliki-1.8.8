// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IsCommand extends Command
{
    public IsCommand() {
        super("is", "Wiadomosc o zakupie", "/is <gracz/gildia> <co kupil>", "core.cmd.sklep", new String[0]);
        
    }
   
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        final String name = args[0];
        final String s;
        Player pa = Bukkit.getPlayer(args[0]);
        switch (s = args[1]) {
        
            case "TURBOD": {
                final User u = UserManager.getUser(args[0]);
                if (u == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie istnieje");
                }
                if (args.length < 3) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/is <gildia> turbod <ilosc>"));
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Wartosc nie jest liczba");
                }
                final int amout = Integer.parseInt(args[2]);
                final String msg_prepared = ChatUtil.fixColor("&8» &7Gracz &9" + name + "");
                final String msg_prepared2 = ChatUtil.fixColor("&8» &7zakupil &9&lTurboDrop &7na &9" + amout + "&7m");
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared);
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared2);
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Dziekujemy za wsparcie"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7TurboDrop mozesz zakupic na &9NOMENHC.EU"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo drop " + name + " " + amout + "m");
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo exp " + name + " " + amout + "m");
                return true;
            }
            case "TURBODALL": {
                final User u = UserManager.getUser(args[0]);
                if (u == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie istnieje");
                }
                if (args.length < 3) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/is <gildia> turbod <ilosc>"));
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Wartosc nie jest liczba");
                }
                final int amout = Integer.parseInt(args[2]);
                final String msg_prepared = ChatUtil.fixColor("&8» &7Gracz &9" + name + "");
                final String msg_prepared2 = ChatUtil.fixColor("&8» &7zakupil &9&lTurboDrop &7dla serwera na &9" + amout + "&7m");
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared);
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared2);
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Dziekujemy za wsparcie"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7TurboDrop mozesz zakupic na &9NOMENHC.EU"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo drop all players " + amout + "m");
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo exp all players " + amout + "m");
                return true;
            }
            case "TURBOE": {
                final User u = UserManager.getUser(args[0]);
                if (u == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie istnieje");
                }
                if (args.length < 3) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/is <gracz> turboe <ilosc>"));
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Wartosc nie jest liczba");
                }
                final int amout = Integer.parseInt(args[2]);
                final String msg_prepared = ChatUtil.fixColor("&8» &7Gracz &9" + name + "");
                final String msg_prepared2 = ChatUtil.fixColor("&8» &7zakupil &9&lTurboExp &7na &9" + amout + "&7m");
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared);
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared2);
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Dziekujemy za wsparcie"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7TurboExp mozesz zakupic na &9NOMENHC.EU"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo exp " + name + " " + amout + "m");
                return true;
            }
            case "VIP": {
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    return ChatUtil.sendMessage((CommandSender)sender, "&9&lERROR: &7Gracz jest offline");
                }
            	if(pa.hasPermission("admin")) {
            		return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz posiada wieksza range");
            	}
            	if(pa.hasPermission("vip")) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz posiada wieksza range");
            	}
                final String msg_prepared = ChatUtil.fixColor("&8» &7Gracz &9" + name + "");
                final String msg_prepared2 = ChatUtil.fixColor("&8» &7zakupil range &9&lVIP");
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared);
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared2);
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Dziekujemy za wsparcie"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Range mozesz zakupic na &9NOMENHC.EU"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + name + " permission set vip");
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + name + " parent set VIP");
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo drop " + name + " 10m");
                return true;
            }
            case "SVIP": {
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    return ChatUtil.sendMessage((CommandSender)sender, "&9&lERROR: &7Gracz jest offline");
                }
                
            	if(pa.hasPermission("admin")) {
            		return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz posiada wieksza range");
            	}
            	if(pa.hasPermission("svip")) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz posiada wieksza range");
            	}
                final String msg_prepared = ChatUtil.fixColor("&8» &7Gracz &9" + name + "");
                final String msg_prepared2 = ChatUtil.fixColor("&8» &7zakupil range &9&lSVIP");
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared);
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared2);
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Dziekujemy za wsparcie"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Range mozesz zakupic na &9NOMENHC.EU"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + name + " permission set vip");
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + name + " permission set svip");
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + name + " parent set SVIP");
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo drop " + name + " 10m");
                return true;
            }
            case "DARK": {
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    return ChatUtil.sendMessage((CommandSender)sender, "&9&lERROR: &7Gracz jest offline");
                }
            	if(pa.hasPermission("admin")) {
            		return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz posiada wieksza range");
            	}
            	if(pa.hasPermission("dark")) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz posiada wieksza range");
            	}
                final User u = UserManager.getUser(args[0]);
                final String msg_prepared = ChatUtil.fixColor("&8» &7Gracz &9" + name + "");
                final String msg_prepared2 = ChatUtil.fixColor("&8» &7zakupil range &9&lSPONSOR");
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared);
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared2);
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Dziekujemy za wsparcie"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Range mozesz zakupic na &9NOMENHC.EU"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + name + " permission set vip");
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + name + " permission set svip");
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + name + " permission set sponsor");
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + name + " parent set sponsor");
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo drop " + name + " 10m");
                return true;
            }
            case "CASE": {
                if (args.length < 3) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/is <gracz> pcase <ilosc>"));
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Wartosc nie jest liczba");
                }
                final int amout2 = Integer.parseInt(args[2]);
                final String msg_prepared = ChatUtil.fixColor("&8» &7Gracz &9" + name + "");
                final String msg_prepared2 = ChatUtil.fixColor("&8» &7zakupil &9&lCase &fx&7" + amout2 + "");
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared);
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared2);
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Dziekujemy za wsparcie"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7PremiumCase mozesz zakupic na &9NOMENHC.EU"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "dcase " + name + " " + amout2);
                return true;
            }
            case "SLOT": {
                final String msg_prepared = ChatUtil.fixColor("&8» &7Gracz &9" + name + "");
                final String msg_prepared2 = ChatUtil.fixColor("&8» &7zakupil &9&lRezerwacje Slota");
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared);
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared2);
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Dziekujemy za wsparcie"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Rezerwacje Slota mozesz zakupic na &9NOMENHC.EU"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + name + " permission set core.join.bypas");
                return true;
            }
            case "UNBAN": {
            	final User u = UserManager.getUser(args[0]);
                if (u == null) {
                    return ChatUtil.sendMessage((CommandSender)sender, "&9&lERROR: &7Gracz nie istnieje");
                }
                final String msg_prepared = ChatUtil.fixColor("&8» &7Gracz &9" + name + "");
                final String msg_prepared2 = ChatUtil.fixColor("&8» &7zakupil &9&lUnbana");
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared);
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared2);
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Mamy nadzieje ze bedziesz grzeczny :)"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Unbana mozesz zakupic na &9NOMENHC.EU"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "cunban " + name);
                return true;
            }
            case "GTURBOD": {
                final User u = UserManager.getUser(args[0]);
                final Guild g = GuildManager.getGuild(args[0]);
                if (u == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie istnieje");
                }
                if (args.length < 3) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/is <gildia> turbod <ilosc>"));
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Wartosc nie jest liczba");
                }
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    return ChatUtil.sendMessage((CommandSender)sender, "&9&lERROR: &7Gracz jest offline");
                }
                final int amout = Integer.parseInt(args[2]);
                final String msg_prepared = ChatUtil.fixColor("&8» &7Gildia &9" + u.getGuild().getTag() + "");
                final String msg_prepared2 = ChatUtil.fixColor("&8» &7zakupil &9&lTurboDrop &7na &9" + amout + "&7m");
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared);
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared2);
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Dziekujemy za wsparcie"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7TurboDrop dla gildii mozesz zakupic na &9NOMENHC.EU"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo drop guild " + u.getGuild().getTag() + " " + amout + "m");
                return true;
                }
            case "GTURBOE": {
                final User u = UserManager.getUser(args[0]);
                if (u == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie istnieje");
                }
                if (args.length < 3) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/is <gildia> turboe <ilosc>"));
                }
                if (!ChatUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Wartosc nie jest liczba");
                }
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    return ChatUtil.sendMessage((CommandSender)sender, "&9&lERROR: &7Gracz jest offline");
                }
                final Guild g = GuildManager.getGuild(args[0]);
                if (g == null) {
                    return ChatUtil.sendMessage((CommandSender)sender, "&9&lERROR: &7Gracz nie posiada gildii");
                }
                final int amout = Integer.parseInt(args[2]);
                final String msg_prepared = ChatUtil.fixColor("&8» &7Gildia &9" + name + "");
                final String msg_prepared2 = ChatUtil.fixColor("&8» &7zakupil &9&lTurboExp &7na &9" + amout + "&7m");
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared);
                Bukkit.broadcastMessage(ChatUtil.fixColor("") + msg_prepared2);
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7Dziekujemy za wsparcie"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8» &7TurboExp dla gildii mozesz zakupic na &9NOMENHC.EU"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8&m------------- &8[ &a&lINFORMACJA &8]&8&m-------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor(""));
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo exp guild " + u.getGuild().getTag() + " " + amout + "m");
                return true;
            }
            default:
                break;
        }
        return ChatUtil.sendMessage(sender, "&7Dostepne uslugi: &9VIP, SVIP, SPONSOR, UNBAN, SLOT, CASE, TURBOD, TURBOE, GTURBOD, GTURBOE");
    }
}
