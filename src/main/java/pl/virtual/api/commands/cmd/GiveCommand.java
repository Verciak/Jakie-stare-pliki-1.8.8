// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class GiveCommand extends Command
{
    public GiveCommand() {
        super("give", "dawanie przemiotow graczom", "/give <gracz> <id[:base]> [ilosc]", "core.cmd.give", new String[] { "giveitem" });
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        final Player p = Bukkit.getPlayer(args[0]);
        final String[] datas = args[1].split(":");
        final Material m = ChatUtil.getMaterial(datas[0]);
        Short data = 0;
        if (datas.length > 1) {
            data = Short.valueOf(datas[1]);
        }
        ItemStack item = null;
        if (p == null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz jest offline");
        }
        if (m == null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Nazwa lub ID przedmiotu jest bledne");
        }
        if (args.length == 2) {
            item = new ItemStack(m, 1, (short)data);
        }
        else if (args.length == 3) {
            item = new ItemStack(m, ChatUtil.isInteger(args[2]) ? Integer.parseInt(args[2]) : 1, (short)data);
        }
        if (item == null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Wystapil blad podczas dawania przedmiotu");
        }
        ChatUtil.giveItems(p, item);
        p.updateInventory();
        ChatUtil.sendMessage(sender, "&7Dales &c" + m.name() + "&8:&c" + data + " &7(&f" + item.getAmount() + "&7) graczowi &c" + p.getName() + "");
        return ChatUtil.sendMessage(sender, "&7Otrzymales &c" + m.name() + "&8:&c" + data + " &7(&f" + item.getAmount() + "&7)");
    }
}
