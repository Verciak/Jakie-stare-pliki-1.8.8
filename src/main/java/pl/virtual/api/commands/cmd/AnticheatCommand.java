// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.data.base.user.Ban;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.BanManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.bukkit.command.CommandSender;

public class AnticheatCommand extends Command
{
    public AnticheatCommand() {
        super("cheater", "permamentne banowanie uzytkownikow", "/cheater [nick] [czas, jesli perm to wpisz 0] [powod]", "core.cmd.cheater", new String[0]);
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        final Ban b = BanManager.getBan(args[0]);
        if (b != null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Ten gracz ma juz bana");
        }
        final User u = UserManager.getUser(sender.getName());
        if (sender instanceof Player && u == null) {
            return true;
        }
        final String nick = args[0];
        final Player p = Bukkit.getPlayer(args[0]);
        if (nick.equalsIgnoreCase(sender.getName())) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Nie mozesz zbanowac sam siebie");
        }
        if (p.hasPermission("core.cmd.admin")) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Nie mozesz zablokowac tego gracza");
        }       
        else {
            final String admin = sender.getName().equals("CONSOLE") ? "konsola" : sender.getName();
            String reason = "Brak";
            if (args.length > 2) {
                reason = StringUtils.join(args, " ", 2, args.length);
            }
            final long time = DataUtil.parseDateDiff(args[1], true);
            if (time > System.currentTimeMillis()) {
                final Ban ban = new Ban(args[0], admin, reason, time);
                BanManager.addBan(args[0], ban);
                return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&9&lAC&8] &7Gracz &9" + args[0] + " &7zostal zablokowany.");
            }
            final Ban ban = new Ban(args[0], admin, reason, 0L);
            BanManager.addBan(args[0], ban);
            return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&9&lAC&8] &7Gracz &9" + args[0] + " &7zostal zablokowany");
        }
    }
}
