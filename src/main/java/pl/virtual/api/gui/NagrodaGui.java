package pl.virtual.api.gui;

import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.*;

import pl.virtual.api.data.base.user.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.inventory.*;

public class NagrodaGui
{    
    public static void show(final Player p) {
        final User u = UserManager.getUser(p);
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, InventoryType.HOPPER, ChatUtil.fixColor("&4&lNagroda"));
        final ItemBuilder enderchest = new ItemBuilder(Material.ENDER_CHEST).setTitle(ChatUtil.fixColor("&6&lNagroda")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&c&lChcesz otrzymac darmowe DarkCase ?")).addLore(ChatUtil.fixColor("&c&lJedyne co musisz zrobic to:")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor(" &8- &c&lPolubic naszego Fanpage")).addLore(ChatUtil.fixColor(" &8- &c&lNapisac w wiadomosci prywatnej &f&lNagroda-(Dokladny Nick)")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&c&lNp. &f&lNagroda-Virtual343")).addLore(ChatUtil.fixColor("&c&lNasz Fanpage: &f&lwww.fb.com/darkhardpl")).addLore(ChatUtil.fixColor("&c&lMaksymalny czas oczekiwania: &f&lDo 5 min"));
        final ItemBuilder chest = new ItemBuilder(Material.CHEST).setTitle(ChatUtil.fixColor("&6&lNagroda")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&c&lChcesz otrzymac darmowe DarkCase ?")).addLore(ChatUtil.fixColor("&c&lJedyne co musisz zrobic to:")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor(" &8- &c&lDolaczyc do naszego Discorda")).addLore(ChatUtil.fixColor(" &8- &c&lNapisac na kanale nagroda komend !Nagroda dokladny nick")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&c&lNp. &f&l!Nagroda Virtual343")).addLore(ChatUtil.fixColor("&c&lNasz Discord: &f&lhttps://discord.gg/N7e4dms")).addLore(ChatUtil.fixColor("&c&lMaksymalny czas oczekiwania: &f&lAutomatycznie"));
        final ItemBuilder air = new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setTitle(ChatUtil.fixColor("&8�"));
        inv.setItem(inv.getSize() - 5, air.build());
        inv.setItem(inv.getSize() - 4, enderchest.build());
        inv.setItem(inv.getSize() - 3, air.build());
        inv.setItem(inv.getSize() - 2, chest.build());
        inv.setItem(inv.getSize() - 1, air.build());
        p.openInventory(inv);
    }
}