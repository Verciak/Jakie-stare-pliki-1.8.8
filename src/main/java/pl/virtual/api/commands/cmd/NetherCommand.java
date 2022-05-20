// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class NetherCommand extends Command
{
    public NetherCommand() {
        super("nether", "nether", "/nether <true/false>", "core.cmd.nether", new String[0]);
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        final boolean b = Boolean.parseBoolean(args[0]);
        return ChatUtil.sendMessage(sender, "&7Nether zostal " + (b ? "&awlaczony" : "&cwylaczony"));
    }
}
