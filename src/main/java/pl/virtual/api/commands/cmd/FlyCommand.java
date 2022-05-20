// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;

public class FlyCommand extends PlayerCommand
{
    public FlyCommand() {
        super("fly", "zarzadzanie trybem latania graczy", "/fly [gracz]", "core.cmd.fly", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            p.setAllowFlight(!p.getAllowFlight());
            return ChatUtil.sendMessage((CommandSender)p, "&7Tryb latania zostal " + (p.getAllowFlight() ? "&awlaczony" : "&cwylaczony"));
        }
        if (!p.hasPermission("core.cmd.admin")) {
            return ChatUtil.sendMessage((CommandSender)p, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz jest offline");
        }
        o.setAllowFlight(!o.getAllowFlight());
        ChatUtil.sendMessage((CommandSender)o, "&7Tryb latania zostal " + (o.getAllowFlight() ? "&awlaczony" : "&cwylaczony") + " &7przez &9" + p.getName());
        return ChatUtil.sendMessage((CommandSender)p, "&7Tryb latania zostal " + (o.getAllowFlight() ? "&awlaczony" : "&cwylaczony") + " &7dla &9" + o.getName());
    }
}
