package pl.virtual.api.commands.cmd;

import org.bukkit.entity.*;
import org.bukkit.*;

import pl.virtual.api.commands.*;
import pl.virtual.api.utils.*;

import org.bukkit.command.*;

public class SpawnCommand extends PlayerCommand
{
    public SpawnCommand() {
        super("spawn", "Teleport na spawn mapy", "/spawn", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length != 1) {
            TimerUtil.teleportSpawn(p, Bukkit.getWorlds().get(0).getSpawnLocation(), 10);
            return true;
        }
        if (!p.hasPermission("core.cmd.admin")) {
            return ChatUtil.sendMessage((CommandSender)p, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz offline");
        }
        TimerUtil.teleportSpawn(o, o.getWorld().getSpawnLocation(), 10);
        return ChatUtil.sendMessage((CommandSender)p, "&7Przeteleportowales gracza &9" + o.getName() + " &7na spawn");
    }
}
