// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.gui.SchowekGui;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SchowekCommand extends PlayerCommand
{
    public SchowekCommand() {
        super("schowek", "schowek", "/schowek", "core.cmd.user", new String[] { "depozyt" });
    }
    
    @Override
    public boolean onCommand(final Player sender, final String[] args) {
        SchowekGui.show(sender);
        return true;
    }
}
