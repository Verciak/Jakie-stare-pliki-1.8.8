package pl.virtual.api.gui;

import org.bukkit.entity.*;
import pl.virtual.api.utils.*;

import org.bukkit.*;
import org.bukkit.inventory.*;

public class ResetRankGui
{
    public static InventoryView show(final Player p) {
        final ItemBuilder air = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setTitle(ChatUtil.fixColor("&8#"));
        final ItemBuilder kills = new ItemBuilder(Material.SNOWBALL).setTitle(ChatUtil.fixColor("&4&lResetowanie Zabojstw")).addLore("").addLore(ChatUtil.fixColor("&8» &7Koszt zresetowania: &c500 &7coins")).addLore("");
        final ItemBuilder deaths = new ItemBuilder(Material.SNOWBALL).setTitle(ChatUtil.fixColor("&4&lResetowanie Smierci")).addLore("").addLore(ChatUtil.fixColor("&8» &7Koszt zresetowania: &c500 &7coins")).addLore("");
        final ItemBuilder assist = new ItemBuilder(Material.SNOWBALL).setTitle(ChatUtil.fixColor("&4&lResetowanie Asyst")).addLore("").addLore(ChatUtil.fixColor("&8» &7Koszt zresetowania: &c500 &7coins")).addLore("");
        final ItemBuilder points = new ItemBuilder(Material.SNOWBALL).setTitle(ChatUtil.fixColor("&4&lResetowanie Punktow")).addLore("").addLore(ChatUtil.fixColor("&8» &7Koszt zresetowania: &c500 &7coins")).addLore("");
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, "�6");
        inv.setItem(inv.getSize() - 27, air.build());
        inv.setItem(inv.getSize() - 26, air.build());
        inv.setItem(inv.getSize() - 25, air.build());
        inv.setItem(inv.getSize() - 24, air.build());
        inv.setItem(inv.getSize() - 23, air.build());
        inv.setItem(inv.getSize() - 22, air.build());
        inv.setItem(inv.getSize() - 21, air.build());
        inv.setItem(inv.getSize() - 20, air.build());
        inv.setItem(inv.getSize() - 19, air.build());
        inv.setItem(inv.getSize() - 18, air.build());
        inv.setItem(inv.getSize() - 17, air.build());
        inv.setItem(inv.getSize() - 16, kills.build());
        inv.setItem(inv.getSize() - 15, deaths.build());
        inv.setItem(inv.getSize() - 14, air.build());
        inv.setItem(inv.getSize() - 13, assist.build());
        inv.setItem(inv.getSize() - 12, points.build());
        inv.setItem(inv.getSize() - 11, air.build());
        inv.setItem(inv.getSize() - 10, air.build());
        inv.setItem(inv.getSize() - 9, air.build());
        inv.setItem(inv.getSize() - 8, air.build());
        inv.setItem(inv.getSize() - 7, air.build());
        inv.setItem(inv.getSize() - 6, air.build());
        inv.setItem(inv.getSize() - 5, air.build());
        inv.setItem(inv.getSize() - 4, air.build());
        inv.setItem(inv.getSize() - 3, air.build());
        inv.setItem(inv.getSize() - 2, air.build());
        inv.setItem(inv.getSize() - 1, air.build());
        return p.openInventory(inv);
    }
}
