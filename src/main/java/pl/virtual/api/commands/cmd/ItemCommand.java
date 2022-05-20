// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.command.CommandSender;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.entity.Player;

public class ItemCommand extends PlayerCommand
{
    public ItemCommand() {
        super("i", "dawanie przemiotow graczom", "/item <id> <ilosc>", "core.cmd.i", new String[] { "i" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length != 1 && args.length != 2) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final String[] idAndData = args[0].split(":");
        final Material material = ChatUtil.getMaterial(idAndData[0]);
        if (material == null) {
            return ChatUtil.error(p, "&9&lERROR: &7Nie rozpoznano nazwy przedmiotu");
        }
        Short data = 0;
        if (idAndData.length > 1) {
            data = Short.valueOf(idAndData[1]);
        }
        int amount = 64;
        if (args.length == 2 && ChatUtil.isInteger(args[1])) {
            amount = Integer.parseInt(args[1]);
        }
        final ItemStack item = new ItemStack(material, amount, (short)data);
        ChatUtil.giveItems(p, item);
        p.updateInventory();
        ChatUtil.sendMessage((CommandSender)p, "&7Otrzymales &9" + material.name() + "&8:&9" + data + " &7(&f" + amount + "&7)");
        return true;
    }
}
