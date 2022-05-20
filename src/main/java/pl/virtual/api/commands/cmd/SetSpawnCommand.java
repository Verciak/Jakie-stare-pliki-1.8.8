// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends PlayerCommand
{
    public SetSpawnCommand() {
        super("setspawn", "Ustawia spawn mapy", "/setspawn", "core.cmd.setspawn", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        Bukkit.getWorlds().get(0).setSpawnLocation(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
        return ChatUtil.sendMessage((CommandSender)p, "&7Spawn zostal ustawiony");
    }
}
