// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class RenameCommand extends PlayerCommand
{
    public RenameCommand() {
        super("rename", "komenda do rename", "/rename <nazwa>", "core.cmd.rename", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final ItemStack is = p.getItemInHand();
        final ItemMeta meta = is.getItemMeta();
        if (is == null || is.getType() == Material.AIR) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie masz nic w rece do nazwania");
        }
        if (is.getType() == Material.CHEST && meta.hasEnchant(Enchantment.DURABILITY)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie mozesz zmienic nazwy sejfu");
        }
        final String name = StringUtils.join((Object[])args, " ");
        meta.setDisplayName(ChatUtil.fixColor(name));
        is.setItemMeta(meta);
        return ChatUtil.sendMessage((CommandSender)p, "&7Zmieniles nazwe przedmiotu na &c" + name);
    }
}
