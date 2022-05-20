// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.API.Config;
import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.listeners.BorderMapListener;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class BorderCommand extends Command
{
    public BorderCommand() {
        super("border", "ustawianie liczby slotow", "/border <world/nether> <liczba>", "core.cmd.border", new String[] { "borderset" });
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length != 2) {
            String msg = Lang.MSG_USAGE;
            msg = msg.replace("{USAGE}", this.getUsage());
            return ChatUtil.sendMessage(sender, msg);
        }
        if (!ChatUtil.isInteger(args[1])) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7To nie liczba");
        }
        if (args[0].equalsIgnoreCase("world")) {
            final int border = Config.BORDER_WORLD_RADIUS = Integer.parseInt(args[1]);
            Config.saveConfig();
            BorderMapListener.setBorder();
            return ChatUtil.sendMessage(sender, "&7Ustawiles border &cWORLD &7na &c" + args[1] + "");
        }
        if (args[0].equalsIgnoreCase("nether")) {
            final int netherborder = Config.BORDER_NETHERWORLD_RADIUS = Integer.parseInt(args[1]);
            Config.saveConfig();
            return ChatUtil.sendMessage(sender, "&7Ustawiles border &cWORLD &7na &c" + args[1] + "");
        }
        return false;
    }
}
