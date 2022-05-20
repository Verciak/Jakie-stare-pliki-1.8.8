// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;

public class GodCommand extends PlayerCommand
{
    public GodCommand() {
        super("god", "zarzadzanie trybem goda graczy", "/god [gracz]", "core.cmd.god", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            final User u = UserManager.getUser(p.getName());
            if (u == null) {
                return true ;
            }
            u.setGod(!u.isGod());
            return ChatUtil.sendMessage((CommandSender)p, "&7God zostal " + (u.isGod() ? "&awlaczony" : "&cwylaczony"));
        }
        else {
            if (!p.hasPermission("core.cmd.admin")) {
                return ChatUtil.sendMessage((CommandSender)p, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
            }
            final Player o = Bukkit.getPlayer(args[0]);
            if (o == null) {
                return ChatUtil.sendMessage((CommandSender)p, "&7Gracz jest offline");
            }
            final User user = UserManager.getUser(o);
            if (user == null) {
                return true;
            }
            user.setGod(!user.isGod());
            ChatUtil.sendMessage((CommandSender)o, "&7God zostal " + (user.isGod() ? "&awlaczony" : "&cwylaczony") + " &7przez &9" + p.getName());
            return ChatUtil.sendMessage((CommandSender)p, "&7God zostal " + (user.isGod() ? "&awlaczony" : "&cwylaczony") + " &7dla &9" + o.getName());
        }
    }
}
