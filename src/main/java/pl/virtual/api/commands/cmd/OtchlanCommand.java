// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.entity.Player;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.tasks.AbbysTask;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class OtchlanCommand extends PlayerCommand
{
    public OtchlanCommand() {
        super("otchlan", "Otchlan serwerowa", "/otchlan", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (AbbysTask.open) {
            p.openInventory(AbbysTask.inv);
            return false;
        }
        ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Otchlan jest zamknieta");
        return true;
    }
}
