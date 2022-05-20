// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import pl.virtual.api.commands.PlayerCommand;

public class WorkBenchCommand extends PlayerCommand
{
    public WorkBenchCommand() {
        super("workbench", "workbench", "/workbench", "core.cmd.crafting", new String[] { "wb" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        p.openWorkbench((Location)null, true);
        return true;
    }
}
