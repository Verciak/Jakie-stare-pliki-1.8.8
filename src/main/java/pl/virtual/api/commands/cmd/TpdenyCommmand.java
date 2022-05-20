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

public class TpdenyCommmand extends PlayerCommand
{
    public TpdenyCommmand() {
        super("tpdeny", "tpdeny", "/tpdeny", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz jest offline");
        }
        final User u = UserManager.getUser(p);
        if (u == null) {
            return true;
        }
        if (!u.getTpahere().contains(o) && !u.getTpa().contains(o)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie masz zaproszenia do teleportacji od tego gracza");
        }
        if (u.getTpa().contains(o)) {
            u.getTpa().remove(p);
        }
        if (u.getTpahere().contains(o)) {
            u.getTpahere().remove(o);
        }
        ChatUtil.sendMessage((CommandSender)p, "&7Odrzuciles prosbe o teleport od gracza &9" + o.getName() + "");
        return ChatUtil.sendMessage((CommandSender)o, "&7Gracz &9" + p.getName() + " &7odrzucil twoja prosbe o teleport");
    }
}
