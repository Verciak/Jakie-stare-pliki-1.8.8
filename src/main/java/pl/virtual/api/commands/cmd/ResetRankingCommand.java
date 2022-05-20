// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.gui.ResetRankGui;

import org.bukkit.entity.Player;

public class ResetRankingCommand extends PlayerCommand
{
    public ResetRankingCommand() {
        super("/resetranking", "Resetowanie statow za oplata", "resetranking", "core.cmd.user", new String[] { "rr", "resetrank", "resetujranking" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        ResetRankGui.show(p);
        return true;
    }
}
