// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.CraftingUtil;

import org.bukkit.entity.Player;

public class CraftCommand extends PlayerCommand
{
    public CraftCommand() {
        super("craftingi", "craft", "/craft", "core.cmd.user", new String[] { "crafting" });
    }
    
    @Override
    public boolean onCommand(final Player sender, final String[] args) {
        CraftingUtil.openMenu(sender);
        return true;
    }
}
