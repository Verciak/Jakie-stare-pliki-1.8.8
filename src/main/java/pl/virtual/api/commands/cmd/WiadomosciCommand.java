// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.gui.WiadomosciGui;

import org.bukkit.entity.Player;

public class WiadomosciCommand extends PlayerCommand
{
    public WiadomosciCommand() {
        super("wiadomosci", "wiadomosci", "/wiadomosci", "core.cmd.user", new String[] { "cc" });
    }
    
    @Override
    public boolean onCommand(final Player sender, final String[] args) {
        WiadomosciGui.show(sender);
        return true;
    }
}
