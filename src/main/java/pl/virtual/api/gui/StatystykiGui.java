package pl.virtual.api.gui;

import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.*;

import pl.virtual.api.data.base.user.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;
import org.bukkit.inventory.*;

public class StatystykiGui
{
    public static InventoryView show(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, InventoryType.HOPPER, ChatUtil.fixColor("&4&lStatystyki"));
        final User u = UserManager.getUser(p);
        final long now = System.currentTimeMillis();
        final long join = u.getTime();
        final long date = now - join;
        final long seconds = date / 1000L % 60L;
        final long minutes = date / 60000L % 60L;
        final long hours = date / 3600000L % 24L;
        final long days = date / 86400000L;
        final String spedzonyczas = String.valueOf(days) + " dni " + hours + " godzin " + minutes + " minut " + seconds + " sekund";
        final ItemBuilder info = new ItemBuilder(Material.NETHER_STAR).setTitle(ChatUtil.fixColor("&4&lINFORMACJE"));
        info.addLore("");
        info.addLore(ChatUtil.fixColor("&8» &7Nick: &4&l" + u.getName()));
        info.addLore("");
        info.addLore(ChatUtil.fixColor("&8» &7Punkty: &c" + Integer.toString(u.getPoints())));
        info.addLore(ChatUtil.fixColor("&8» &7Zabojstwa: &c" + Integer.toString(u.getKills())));
        info.addLore(ChatUtil.fixColor("&8» &7Smierci: &c" + Integer.toString(u.getDeaths())));
        info.addLore(ChatUtil.fixColor("&8» &7Coinsy: &c" + Integer.toString(u.getCoins())));
        info.addLore(ChatUtil.fixColor("&8» &7Poziom kopania: &c" + Integer.toString(u.getLvl())));
        info.addLore(ChatUtil.fixColor("&8» &7Punkty kopania: &c" + Integer.toString(u.getExp())));
        final ItemBuilder staty = new ItemBuilder(Material.NETHER_STAR).setTitle(ChatUtil.fixColor("&4&lSTATYSTYKI"));
        staty.addLore("");
        staty.addLore(ChatUtil.fixColor("&8» &7Wykopany kamien: &c" + u.getWykStone()));
        staty.addLore(ChatUtil.fixColor("&8» &7Otworzone skrzyneczki: &c" + Integer.toString(u.getJajopen())));
        staty.addLore(ChatUtil.fixColor("&8» &7Otworzone marmury: &c" + Integer.toString(u.getCobblexopen())));
        staty.addLore(ChatUtil.fixColor("&8» &7Rzucone perly: &c" + Integer.toString(u.getPerlycyk())));
        staty.addLore(ChatUtil.fixColor("&8» &7Wejscia na serwer: &c" + Integer.toString(u.getJoin())));
        staty.addLore(ChatUtil.fixColor("&8» &7Zjedzone koxy: &c" + Integer.toString(u.getKoxeat())));
        staty.addLore(ChatUtil.fixColor("&8» &7Zjedzone refile: &c" + Integer.toString(u.getRefileat())));
        staty.addLore("");
        final ItemBuilder air = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setTitle(ChatUtil.fixColor("&8#"));
        inv.setItem(inv.getSize() - 1, air.build());
        inv.setItem(inv.getSize() - 2, info.build());
        inv.setItem(inv.getSize() - 3, air.build());
        inv.setItem(inv.getSize() - 4, staty.build());
        inv.setItem(inv.getSize() - 5, air.build());
        return p.openInventory(inv);
    }
}
