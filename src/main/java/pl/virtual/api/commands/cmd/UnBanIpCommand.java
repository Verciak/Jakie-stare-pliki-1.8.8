// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.entity.Player;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.data.base.user.BanIP;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.BanIPManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class UnBanIpCommand extends Command
{
    public UnBanIpCommand() {
        super("unbanip", "odbanowywanie adresow ip", "/unbanip <gracz/adres IP>", "core.cmd.unbanip", new String[0]);
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        if (!(sender instanceof Player) && args[0].equalsIgnoreCase("all")) {
            BanIPManager.unbanAll();
            return ChatUtil.sendMessage(sender, "&7Odbanowales wszystkich graczy");
        }
        String ip = args[0];
        final User u = UserManager.getUser(ip);
        if (u != null && (u.getLastIP() != null || !u.getLastIP().equalsIgnoreCase("null"))) {
            ip = u.getLastIP();
        }
        final BanIP ban = BanIPManager.getBan(ip);
        if (ban == null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Ban nie istnieje");
        }
        if (u != null && (!u.getFirstIP().equalsIgnoreCase("null") || u.getFirstIP() != null)) {
            final BanIP ban2 = BanIPManager.getBan(u.getFirstIP());
            if (ban2 != null) {
                BanIPManager.unban(ban2);
            }
        }
        BanIPManager.unban(ban);
        return ChatUtil.sendMessage(sender, "&7Odbanowales IP gracza &c" + args[0]);
    }
}
