package pl.virtual.api.gui;

import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;

import pl.virtual.api.API.Config;
import pl.virtual.api.utils.*;

import org.bukkit.inventory.*;

public class EventGui
{
    public static InventoryView show(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, InventoryType.HOPPER, ChatUtil.fixColor("&4&lEventy"));
        final ItemBuilder stone = new ItemBuilder(Material.NAME_TAG, 1).addEnchantment(Enchantment.THORNS, 10).setTitle(ChatUtil.fixColor("&6&LKlucz z stone")).addLore(ChatUtil.fixColor("&8» &bDostepny: " + ((Config.STONE > System.currentTimeMillis()) ? ("&a" + DataUtil.secondsToString(Config.STONE)) : "&cNie")));
        final ItemBuilder lowienie = new ItemBuilder(Material.FISHING_ROD, 1).setTitle(ChatUtil.fixColor("&a&lEvent Klucze z wody")).addLore(ChatUtil.fixColor("&8» &bDostepny: " + ((Config.LOWIENIE > System.currentTimeMillis()) ? ("&a" + DataUtil.secondsToString(Config.LOWIENIE)) : "&cNie"))).addLore(ChatUtil.fixColor("&8» &7Klucze dropia z wody przez wylowienie wedka!"));
        final ItemBuilder kill = new ItemBuilder(Material.DIAMOND_SWORD, 1).setTitle(ChatUtil.fixColor("&a&lEvent za zabicie gracza z gildii")).addLore(ChatUtil.fixColor("&8» &bDostepny: " + ((Config.KILL > System.currentTimeMillis()) ? ("&a" + DataUtil.secondsToString(Config.KILL)) : "&cNie"))).addLore(ChatUtil.fixColor("&8» &7Klucz dropi za zabicie gracza z OSOBNEJ GILDII"));
        final ItemBuilder air = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setTitle(ChatUtil.fixColor("&8"));
        inv.setItem(0, air.build());
        inv.setItem(1, air.build());
        inv.setItem(2, stone.build());
        inv.setItem(3, air.build());
        inv.setItem(4, air.build());
        return p.openInventory(inv);
    }
}
