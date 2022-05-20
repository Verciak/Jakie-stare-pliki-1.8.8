// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.TimerUtil;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

public class TpacceptCommand extends PlayerCommand
{
    public TpacceptCommand() {
        super("tpaccept", "Akceptowanie teleportacji", "/tpaccept <nick>", "core.cmd.user", new String[0]);
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
            TimerUtil.teleport(o, p.getLocation(), 10);
            u.getTpa().remove(o);
            ChatUtil.sendMessage((CommandSender)p, "&7Zaakceptowales prosbe o teleport do ciebie od gracza &9" + o.getName() + "");
            ChatUtil.sendMessage((CommandSender)o, "&7Gracz &9" + p.getName() + " &7zaakceptowal twoja prosbe o teleport do niego");
            return true;
        }
        if (u.getTpahere().contains(o)) {
            TimerUtil.teleport(p, o.getLocation(), 10);
            u.getTpahere().remove(o);
            ChatUtil.sendMessage((CommandSender)p, "&7Zaakceptowales prosbe o teleport do gracza &9" + o.getName() + "");
            ChatUtil.sendMessage((CommandSender)o, "&7Gracz &9" + p.getName() + " &7zaakceptowal twoja prosbe o teleport do ciebie");
            return true;
        }
        return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie masz zaproszenia do teleportacji od gracza " + o.getName() + "");
    }
}
