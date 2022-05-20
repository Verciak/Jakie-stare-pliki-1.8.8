// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.entity.Player;

public class IgnoreCommand extends PlayerCommand
{
    public IgnoreCommand() {
        super("ignore", "ignore", "&7/ignore msg/tpa <player>", "core.cmd.user", new String[] { "ing" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final User u = UserManager.getUser(p);
        final Player arg = Bukkit.getPlayer(args[1]);
        final String s;
        switch (s = args[0]) {
            case "msg": {
                if (u.isIgnoreTell(arg)) {
                    u.removeIgnoreTell(arg);
                    return ChatUtil.sendMessage((CommandSender)p, "&7Przestales ignorowac prywatne wiadomosci od gracza &9" + args[1] + "");
                }
                u.addIgnoreTell(arg);
                return ChatUtil.sendMessage((CommandSender)p, "&7Od teraz ignorujesz prywatne wiadomosci od &9" + args[1] + "");
            }
            case "tpa": {
                if (u.isIgnoreTpa(arg)) {
                    u.removeIgnoreTpa(arg);
                    return ChatUtil.sendMessage((CommandSender)p, "&7Przestales ignorowac prosby o teleportacje od gracza &9" + args[1] + "");
                }
                u.addIgnoreTpa(arg);
                return ChatUtil.sendMessage((CommandSender)p, "&7Od teraz ignorujesz prosby o teleportacje od gracza &9" + args[1] + "");
            }
            default:
                break;
        }
        return false;
    }
}
