// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.Command;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.World;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class NightCommand extends Command
{
    public NightCommand() {
        super("night", "night", "/night", "core.cmd.noc", new String[] { "noc" });
    }
    
    @Override
    public boolean onExecute(final CommandSender p, final String[] args) {
        for (final World w : Bukkit.getWorlds()) {
            w.setTime(18000L);
        }
        return ChatUtil.sendMessage(p, "&7Na serwerze zmieniono czas na &9NOC");
    }
}
