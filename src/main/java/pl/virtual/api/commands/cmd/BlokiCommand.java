// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.gui.BlokiGui;

import org.bukkit.entity.Player;

public class BlokiCommand extends PlayerCommand
{
    public BlokiCommand() {
        super("bloki", "bloki", "/bloki", "core.cmd.user", new String[] { "bloki" });
    }
    
    @Override
    public boolean onCommand(final Player sender, final String[] args) {
        BlokiGui.show(sender);
        return true;
    }
}
