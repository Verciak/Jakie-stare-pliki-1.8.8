// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.API.Config;
import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class SlotCommand extends Command
{
    public SlotCommand() {
        super("slot", "ustawianie liczby slotow", "/slot <liczba>", "core.cmd.slot", new String[] { "fala" });
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        if (!ChatUtil.isInteger(args[0])) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7To nie liczba");
        }
        final int slot = Config.SLOT = Integer.parseInt(args[0]);
        Config.saveConfig();
        return ChatUtil.sendMessage(sender, "&7Sloty zostaaly ustawione na &c" + slot);
    }
}
