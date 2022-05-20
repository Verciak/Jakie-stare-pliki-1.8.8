// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.Command;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.World;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class DayCommand extends Command
{
    public DayCommand() {
        super("day", "day", "/day", "core.cmd.day", new String[] { "dzien" });
    }
    
    @Override
    public boolean onExecute(final CommandSender p, final String[] args) {
        for (final World w : Bukkit.getWorlds()) {
            w.setTime(0L);
        }
        return ChatUtil.sendMessage(p, "&7Na serwerze zmieniono czas na &9DZIEN");
    }
}
