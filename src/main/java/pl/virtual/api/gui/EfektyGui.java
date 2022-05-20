package pl.virtual.api.gui;

import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryType;

import java.util.List;

import org.bukkit.*;
import org.bukkit.inventory.*;

import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemBuilder;

public class EfektyGui
{
    public static InventoryView show(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, InventoryType.HOPPER, ChatUtil.fixColor("&4&lEfekty"));
        final ItemBuilder silka = new ItemBuilder(Material.GOLDEN_SWORD).setTitle(ChatUtil.fixColor("&4&lSTRENGHT I")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Koszt: &6700 coinsow")).addLore(ChatUtil.fixColor("&8» &7Czas Trwania: &c2 min&8")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder ogien = new ItemBuilder(Material.DIAMOND_SWORD).setTitle(ChatUtil.fixColor("&4&lZESTAW NAPIE#DALACZA")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&C&LDODAJE EFEKTY")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8- &4&lSPEED II")).addLore(ChatUtil.fixColor("&8- &4&lSTRENGHT I")).addLore(ChatUtil.fixColor("&8- &4&lFIRE RESISTANCE")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Koszt: &61200 coinsow")).addLore(ChatUtil.fixColor("&8» &7Czas Trwania: &c2 min&8")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder niewi = new ItemBuilder(Material.CARROT).setTitle(ChatUtil.fixColor("&4&lJUMP BOOST III")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Koszt: &6400 coinsow")).addLore(ChatUtil.fixColor("&8» &7Czas Trwania: &c2 min&8")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder haste = new ItemBuilder(Material.GOLDEN_PICKAXE).setTitle(ChatUtil.fixColor("&4&lHASTE II")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Koszt: &61000 coinsow")).addLore(ChatUtil.fixColor("&8» &7Czas Trwania: &c2 min&8")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder spe = new ItemBuilder(Material.GOLDEN_BOOTS).setTitle(ChatUtil.fixColor("&4&lSPEED II")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Koszt: &6500 coinsow")).addLore(ChatUtil.fixColor("&8» &7Czas Trwania: &c2 min&8")).addLore(ChatUtil.fixColor(""));
        inv.setItem(inv.getSize() - 1, haste.build());
        inv.setItem(inv.getSize() - 2, silka.build());
        inv.setItem(inv.getSize() - 3, ogien.build());
        inv.setItem(inv.getSize() - 4, niewi.build());
        inv.setItem(inv.getSize() - 5, spe.build());
        return p.openInventory(inv);
    }
}