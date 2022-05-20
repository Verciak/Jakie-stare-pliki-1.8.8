// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.entity.Player;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.gui.AdminPanelGui;

public class EnableCommand extends PlayerCommand
{
    public EnableCommand() {
        super("enable", "komenda do enable", "/enable", "core.cmd.enable", new String[] { "adminpanel" });
    }
    
    public boolean onCommand(final Player sender, final String[] args) {
        AdminPanelGui.show(sender);
        return true;
    }
}
