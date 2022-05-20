// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemBuilder;

import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;

public class MarmurDajCommand extends Command
{
    
    public MarmurDajCommand() {
        super("givemarmur", "giveMarmur", "/givemarmur <all/gracz> <ilosc>", "core.cmd.paczka", new String[] { "gmarmur", "dajmarmur" });
    }
    
    public boolean onExecute(final CommandSender p, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(p, Lang.USE(this.getUsage()));
        }
        if (!ChatUtil.isInteger(args[1])) {
            return ChatUtil.sendMessage(p, "&9&lERROR: &cTo nie liczba");
        }
        final int amout = Integer.parseInt(args[1]);
        if (args[0].equalsIgnoreCase("all")) {
            for (final Player all : Bukkit.getOnlinePlayers()) {
                this.give(all, amout);
            }
            return ChatUtil.sendMessage(p, "&7Rozdales calemu serwerowi &f&lMarmur &7(&fx" + amout + "&7)");
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage(p, "&9&lERROR: &7Gracz jest offline");
        }
        this.give(o, amout);
        return ChatUtil.sendMessage(p, "&7Dales graczowi &c" + o.getName() + " &f&lMarmur &7(&fx" + amout + "&7)");
    }
    
    private void give(final Player p, final int amout) {
        ChatUtil.giveItems(p, new ItemBuilder(Material.MOSSY_STONE_BRICKS, amout).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &f&lCobbleX &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Jest to magiczna skala")).addLore(ChatUtil.fixColor("&8» &7Ktora skrywa w sobie cenne itemy")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("             &f&lAby otworzyc, kliknij PPM             ")).addLore(ChatUtil.fixColor("")).build());
        p.updateInventory();
        ChatUtil.sendMessage((CommandSender)p, "&7Otrzymales &f&lCobbleX &7(&fx" + amout + "&7)");
    }
}
