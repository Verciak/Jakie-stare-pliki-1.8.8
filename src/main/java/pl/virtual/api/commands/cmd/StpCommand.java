// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.entity.Player;

public class StpCommand extends PlayerCommand
{
    public StpCommand() {
        super("stp", "stp", "/stp <gracz>", "core.cmd.stp", new String[] { "tphere", "s" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final String nickja = args[0];
        if (nickja.equalsIgnoreCase(p.getName())) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie mozesz przeteleportowac sie sam do siebie");
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz jest offline");
        }
        o.teleport(p.getLocation());
        ChatUtil.sendMessage((CommandSender)p, "&7Przeteleportowales gracza &9" + o.getName() + " &7do siebie");
        return ChatUtil.sendMessage((CommandSender)o, "&7Zostales przeteleportowany do gracza &9" + p.getName());
    }
}
