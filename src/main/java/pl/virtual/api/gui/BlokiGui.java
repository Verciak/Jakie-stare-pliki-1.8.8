package pl.virtual.api.gui;

import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.*;

import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemBuilder;

import org.bukkit.inventory.*;

public class BlokiGui
{
    public static InventoryView show(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, InventoryType.HOPPER, ChatUtil.fixColor("&4&lBloki"));
        final ItemBuilder zloto = new ItemBuilder(Material.GOLD_BLOCK).setTitle(ChatUtil.fixColor("&c&lZmien sztabki zlota na bloki"));
        final ItemBuilder diax = new ItemBuilder(Material.DIAMOND_BLOCK).setTitle(ChatUtil.fixColor("&c&lZmien sztabki diamentow na bloki"));
        final ItemBuilder iron = new ItemBuilder(Material.IRON_BLOCK).setTitle(ChatUtil.fixColor("&c&lZmien sztabki zelaza na bloki"));
        final ItemBuilder eme = new ItemBuilder(Material.EMERALD_BLOCK).setTitle(ChatUtil.fixColor("&c&lZmien sztabki emeraldow na bloki"));
        final ItemBuilder all = new ItemBuilder(Material.HOPPER).setTitle(ChatUtil.fixColor("&c&lZmien wszystko na bloki"));
        inv.setItem(0, zloto.build());
        inv.setItem(1, iron.build());
        inv.setItem(2, all.build());
        inv.setItem(3, eme.build());
        inv.setItem(4, diax.build());
        return p.openInventory(inv);
    }
}
