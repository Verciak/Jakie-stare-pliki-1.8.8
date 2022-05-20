// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.gui.KitGui;

import org.bukkit.entity.Player;

public class KitCommand extends PlayerCommand
{
    public KitCommand() {
        super("kit", "kity", "/kit", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        KitGui.show(p);
        return true;
    }
}
