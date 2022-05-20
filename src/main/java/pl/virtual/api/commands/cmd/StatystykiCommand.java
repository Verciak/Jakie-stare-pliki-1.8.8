// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.gui.StatystykiGui;

import org.bukkit.entity.Player;

public class StatystykiCommand extends PlayerCommand
{
    public StatystykiCommand() {
        super("statystyki", "statystyki", "/statystyki", "core.cmd.user", new String[] { "staty" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        StatystykiGui.show(p);
        return true;
    }
}
