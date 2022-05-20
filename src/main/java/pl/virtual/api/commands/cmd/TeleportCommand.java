// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.entity.Player;

public class TeleportCommand extends PlayerCommand
{
    public TeleportCommand() {
        super("teleport", "Teleport do graczy lub koordynaty", "/teleport [kto] <do kogo>  lub  [kto] <x> <y> <z>", "core.cmd.tp", new String[] { "tp" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        switch (args.length) {
            case 0: {
                return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
            }
            case 1: {
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz jest offline");
                }
                p.teleport(o.getLocation());
                return ChatUtil.sendMessage((CommandSender)p, "&7Zostales przeteleportowany do gracza &9" + o.getName());
            }
            case 2: {
                if (!p.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie masz uprawnien");
                }
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz jest offline");
                }
                final Player o2 = Bukkit.getPlayer(args[1]);
                if (o2 == null) {
                    return ChatUtil.sendMessage((CommandSender)p, "&c&lBlad: &7Gracz jest offline");
                }
                o.teleport(o2.getLocation());
                ChatUtil.sendMessage((CommandSender)p, "&7Przeteleportowales gracza &9" + o.getName() + " &7do gracza &9" + o2.getName());
                return ChatUtil.sendMessage((CommandSender)o, "&7Zostales przeteleportowany do gracza &9" + o2.getName() + " &7przez &9" + p.getName());
            }
            case 3: {
                if (!p.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage((CommandSender)p, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                final Double x = Double.parseDouble(args[0]);
                final Double y = Double.parseDouble(args[1]);
                final Double z = Double.parseDouble(args[2]);
                if (x.isNaN() && y.isNaN() && z.isNaN()) {
                    return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Koordynaty musza byc liczbami");
                }
                p.teleport(new Location(p.getWorld(), (double)x, (double)y, (double)z));
                return ChatUtil.sendMessage((CommandSender)p, "&7Zostales przeteleportowany na kordy &8X: &9" + x + " &8Y: &9" + y + " &8Z: &9" + z);
            }
            case 4: {
                if (!p.hasPermission("core.cmd.admin")) {
                    return ChatUtil.sendMessage((CommandSender)p, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
                }
                final Player o = Bukkit.getPlayer(args[0]);
                if (o == null) {
                    return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz jest offline");
                }
                final Double x2 = Double.parseDouble(args[1]);
                final Double y2 = Double.parseDouble(args[2]);
                final Double z2 = Double.parseDouble(args[3]);
                if (x2.isNaN() && y2.isNaN() && z2.isNaN()) {
                    return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Koordynaty musza byc liczbami");
                }
                o.teleport(new Location(o.getWorld(), (double)x2, (double)y2, (double)z2));
                ChatUtil.sendMessage((CommandSender)o, "&7Zostales przeteleportowany na kordy &8X: &9" + x2 + " &7Y: &9" + y2 + " &7Z: &9" + z2 + " &7przez &9" + p.getName());
                return ChatUtil.sendMessage((CommandSender)p, "&7Przeteleportowales gracza &9" + o.getName() + " &7na kordy &7X: &9" + x2 + " &7Y: &9" + y2 + " &7Z: &9" + z2);
            }
            default: {
                return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
            }
        }
    }
}
