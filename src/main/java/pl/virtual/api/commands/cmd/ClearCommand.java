// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

public class ClearCommand extends PlayerCommand
{
    public ClearCommand() {
        super("clearinv", "Czyszczenie ekwipunku graczy", "/clearinv [gracz]", "core.cmd.clear", new String[] { "clear", "clearinventory", "ci" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            p.getInventory().setHelmet((ItemStack)null);
            p.getInventory().setChestplate((ItemStack)null);
            p.getInventory().setLeggings((ItemStack)null);
            p.getInventory().setBoots((ItemStack)null);
            p.getInventory().clear();
            return ChatUtil.sendMessage((CommandSender)p, "&7Twoj ekwipunek zostal Wyczyszczony");
        }
        if (!p.hasPermission("core.cmd.admin")) {
            return ChatUtil.sendMessage((CommandSender)p, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz jest offline");
        }
        o.getInventory().clear();
        o.getInventory().setHelmet((ItemStack)null);
        o.getInventory().setChestplate((ItemStack)null);
        o.getInventory().setLeggings((ItemStack)null);
        o.getInventory().setBoots((ItemStack)null);
        ChatUtil.sendMessage((CommandSender)o, "&7Twoj ekwipunek zostal wyczyszczony przez &9" + p.getName());
        return ChatUtil.sendMessage((CommandSender)p, "&7Wyczyszczono ekwipunek dla gracza &9" + o.getName());
    }
}
