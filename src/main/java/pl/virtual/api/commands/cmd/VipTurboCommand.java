// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.gui.TurboGui;

import org.bukkit.entity.Player;

public class VipTurboCommand extends PlayerCommand
{
    public VipTurboCommand() {
        super("freeturbo", "freeturbo", "/freeturbo", "core.cmd.freeturbo", new String[] { "ft" });
    }
    
    @Override
    public boolean onCommand(final Player sender, final String[] args) {
        TurboGui.show(sender);
        return true;
    }
}
