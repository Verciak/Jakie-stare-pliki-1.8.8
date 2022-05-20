package pl.virtual.api.commands.cmd;

import org.bukkit.entity.*;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.gui.EfektyGui;

public class PotkiCommand extends PlayerCommand
{
    public PotkiCommand() {
        super("efekty", "efekty", "/efekty", "core.cmd.user", new String[] { "efekt" });
    }
    
    @Override
    public boolean onCommand(final Player sender, final String[] args) {
    	EfektyGui.show(sender);
        return true;
    }
}
