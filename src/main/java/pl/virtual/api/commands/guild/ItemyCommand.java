package pl.virtual.api.commands.guild;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import pl.virtual.api.API.Config;
import pl.virtual.api.commands.*;
import pl.virtual.api.utils.*;

public class ItemyCommand extends PlayerCommand
{
    public ItemyCommand() {
        super("item", "itemy na gildie", "/itemy", "core.cmd.user", new String[] { "gitemy", "itemy" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        String it = Config.COST_CREATE_NORMAL;
        if (!p.hasPermission("core.cmd.admin") && !Config.ENABLE_ITEMY) {
            ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Itemy sa tymczasowo wylaczone");
            return true;
        }
        if (p.hasPermission("core.create.dark")) {
            it = Config.COST_CREATE_DARK;
        }
        if (p.hasPermission("core.create.svip")) {
            it = Config.COST_CREATE_SVIP;
        }
        else if (p.hasPermission("core.create.vip")) {
            it = Config.COST_CREATE_VIP;
        }
        ItemUtil.getItemGui(it, 1, p, 27, "&4&lITEMY");
        return true;
    }
}
