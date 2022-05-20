
package pl.virtual.api.commands.cmd;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.data.base.user.BanIP;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.BanIPManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanIPCommand extends Command
{
    public BanIPCommand() {
        super("banip", "permamentne banowanie adressu ip", "/banip [nick] [czas, jesli perm to wpisz 0] [powod]", "core.cmd.banip", new String[0]);
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        String ip = args[0];
        final User u = UserManager.getUser(ip);
        if (u != null && (u.getLastIP() != null || !u.getLastIP().equalsIgnoreCase("null"))) {
            ip = u.getLastIP();
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)sender, "&9&lERROR: &7Gracz jest nie istnieje");
        }
        final BanIP b = BanIPManager.getBan(ip);
        if (b != null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7" + args[0] + " ma juz bana na ip");
        }
        final String nick = args[0];
        final Player p = Bukkit.getPlayer(args[0]);
        if (nick.equalsIgnoreCase(sender.getName())) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Nie mozesz zbanowac sam siebie");
        }
        else {
            final String admin = sender.getName().equals("CONSOLE") ? "konsola" : sender.getName();
            String reason = "Brak";
            if (args.length > 2) {
                reason = StringUtils.join((Object[])args, " ", 2, args.length);
            }
            final long time = DataUtil.parseDateDiff(args[1], true);
            if (time > System.currentTimeMillis()) {
                final BanIP ban = new BanIP(ip, admin, reason, time);
                BanIPManager.addBan(ip, ban, (u == null) ? null : u.getName());
                if (u != null && (!u.getFirstIP().equalsIgnoreCase("null") || u.getFirstIP() != null)) {
                    final BanIP ban2 = new BanIP(u.getFirstIP(), admin, reason, time);
                    BanIPManager.addBan(u.getFirstIP(), ban2, u.getName());
                }
                return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&9&lBAN&8] &7Admin &9" + sender.getName() + " &7zablokowal adres ip " + ((u != null) ? ("gracza &9" + u.getName()) : "&7**.**.***.***") + "&7(&f" + reason + "&7)");
            }
            final BanIP ban = new BanIP(ip, sender.getName(), reason.equalsIgnoreCase("") ? " Brak" : reason, 0L);
            BanIPManager.addBan(ip, ban, (u == null) ? null : u.getName());
            if (u != null && (!u.getFirstIP().equalsIgnoreCase("null") || u.getFirstIP() != null)) {
                final BanIP ban3 = new BanIP(u.getFirstIP(), admin, reason, 0L);
                BanIPManager.addBan(u.getFirstIP(), ban3, u.getName());
            }
            return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&9&lBAN&8] &7Admin &9" + sender.getName() + " &7zablokowal ip " + ((u != null) ? ("gracza &9" + u.getName()) : "&7**.**.***.***") + "&7(&f" + reason + "&7)");
        }
    }
}
