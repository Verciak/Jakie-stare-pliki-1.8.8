// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.entity.Player;

import pl.virtual.api.commands.PlayerCommand;

public class EnderchestCommand extends PlayerCommand
{
    public EnderchestCommand() {
        super("enderchest", "enderchest", "/enderchest", "core.cmd.ec", new String[] { "ec" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        p.openInventory(p.getEnderChest());
        return true;
    }
}
