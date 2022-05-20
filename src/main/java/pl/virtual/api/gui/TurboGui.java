package pl.virtual.api.gui;

import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.*;
import org.bukkit.inventory.*;

import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemBuilder;

public class TurboGui
{
    public static InventoryView show(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, InventoryType.HOPPER, ChatUtil.fixColor("&4&lTurbo"));
        final ItemBuilder drop = new ItemBuilder(Material.DIAMOND_PICKAXE).setTitle(ChatUtil.fixColor("&4&lTurboDrop na 30min")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Boost ten mozesz aktywowac 1 dziennie")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder exp = new ItemBuilder(Material.EXPERIENCE_BOTTLE).setTitle(ChatUtil.fixColor("&4&lTurboExp na 30min")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Boost ten mozesz aktywowac 1 dziennie")).addLore(ChatUtil.fixColor(""));
        inv.setItem(inv.getSize() - 2, drop.build());
        inv.setItem(inv.getSize() - 4, exp.build());
        return p.openInventory(inv);
    }
}
