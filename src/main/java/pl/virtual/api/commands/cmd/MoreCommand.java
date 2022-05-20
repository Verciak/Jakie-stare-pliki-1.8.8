// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.inventory.ItemStack;
import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MoreCommand extends PlayerCommand
{
    public MoreCommand() {
        super("more", "komenda do more", "/more", "core.cmd.more", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final ItemStack is = p.getItemInHand();
        if (is == null || is.getType() == null || is.getType() == Material.AIR) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Muszisz miec cos w rece");
        }
        is.setAmount(is.getMaxStackSize());
        return true;
    }
}
