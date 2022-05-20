// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.cmd.MarmurCommand;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemBuilder;
import pl.virtual.api.utils.PolishItemNames;
import pl.virtual.api.utils.RandomUtil;

import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.Listener;

public class MarmurListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void s(final BlockPlaceEvent e) {
        if (e.isCancelled()) {
            return;
        }
        final Player p = e.getPlayer();
        final Block b = e.getBlock();
        final User u = UserManager.getUser(p);
        final ItemStack cx = new ItemBuilder(Material.MOSSY_STONE_BRICKS, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &f&lCobbleX &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Jest to magiczna skala")).addLore(ChatUtil.fixColor("&8» &7Ktora skrywa w sobie cenne itemy")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("             &f&lAby otworzyc, kliknij PPM             ")).addLore(ChatUtil.fixColor("")).build();
        if (b.getType() == cx.getType() && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().equals(cx.getItemMeta())) {
            final ItemStack drop = MarmurCommand.drops.get(RandomUtil.getRandInt(0, MarmurCommand.drops.size() - 1));
            ChatUtil.giveItems(p, drop);
            u.addCobblexopen(1);
            u.save();
            p.updateInventory();
            b.setType(Material.AIR);
            ChatUtil.sendMessage((CommandSender)p, "&8[&9&lCobbleX&8] &7Otworzyles &9CobbleX &7i otrzymales &7(&f" + PolishItemNames.getPolishName(drop.getType()) + "&7)");
        }
    }
}
