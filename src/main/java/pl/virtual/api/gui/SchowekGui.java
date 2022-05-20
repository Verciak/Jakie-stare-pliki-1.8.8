
package pl.virtual.api.gui;

import org.bukkit.inventory.Inventory;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemBuilder;
import pl.virtual.api.utils.ItemStackUtil;

import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.InventoryView;
import org.bukkit.entity.Player;

public class SchowekGui
{
    public static InventoryView show(final Player p) {
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 54, ChatUtil.fixColor("&4&lDepozyt"));
        final User u = UserManager.getUser(p);
        int koxw = 0;
        int refw = 0;
        int perlaw = 0;
        int snow = 0;
        int arrow = 0;
        int ice = 0;
        final int i = ItemStackUtil.getAmountOfItem(Material.ENCHANTED_GOLDEN_APPLE, p, (short)0);
        if (i >= Config.LIMIT_KOX) {
            koxw = 0;
        }
        if (i < Config.LIMIT_KOX) {
            final int kox = i - Config.LIMIT_KOX;
            koxw = kox * -1;
        }
        if (u.getKox() < Config.LIMIT_KOX) {
            koxw = u.getKox();
        }
        final int ii = ItemStackUtil.getAmountOfItem(Material.GOLDEN_APPLE, p, (short)0);
        if (ii >= Config.LIMIT_REFILE) {
            refw = 0;
        }
        if (ii < Config.LIMIT_REFILE) {
            final int ref = ii - Config.LIMIT_REFILE;
            refw = ref * -1;
        }
        if (u.getRefil() < Config.LIMIT_REFILE) {
            refw = u.getRefil();
        }
        final int iii = ItemStackUtil.getAmountOfItem(Material.ENDER_PEARL, p, (short)0);
        if (iii >= Config.LIMIT_PEARL) {
            perlaw = 0;
        }
        if (iii < Config.LIMIT_PEARL) {
            final int perla = iii - Config.LIMIT_PEARL;
            perlaw = perla * -1;
        }
        if (u.getPerly() < Config.LIMIT_PEARL) {
            perlaw = u.getPerly();
        }
        final int iiii = ItemStackUtil.getAmountOfItem(Material.SNOWBALL, p, (short)0);
        if (iiii >= Config.LIMIT_SNOW) {
        	snow = 0;
        }
        if (iiii < Config.LIMIT_SNOW) {
            final int sno = iiii - Config.LIMIT_SNOW;
            snow = sno * -1;
        }
        if (u.getSnow() < Config.LIMIT_SNOW) {
            snow = u.getSnow();
        }
        final int iiiiii = ItemStackUtil.getAmountOfItem(Material.ARROW, p, (short)0);
        if (iiiiii >= Config.LIMIT_ARROW) {
        	arrow = 0;
        }
        if (iiiiii < Config.LIMIT_ARROW) {
            final int sno = iiiiii - Config.LIMIT_ARROW;
            arrow = sno * -1;
        }
        if (u.getArrow() < Config.LIMIT_ARROW) {
        	arrow = u.getArrow();
        }
        final ItemBuilder Kox = new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, 1, (short)0).setTitle(ChatUtil.fixColor("&4&lKoxy")).addLore(ChatUtil.fixColor("&8» &7Posiadasz: &c" + u.getKox()));
        final ItemBuilder Refil = new ItemBuilder(Material.GOLDEN_APPLE, 1, (short)0).setTitle(ChatUtil.fixColor("&4&lRefy")).addLore(ChatUtil.fixColor("&8» &7Posiadasz: &c" + u.getRefil()));
        final ItemBuilder Perla = new ItemBuilder(Material.ENDER_PEARL, 1).setTitle(ChatUtil.fixColor("&4&lPerly")).addLore(ChatUtil.fixColor("&8» &7Posiadasz: &c" + u.getPerly()));
        final ItemBuilder snown = new ItemBuilder(Material.SNOWBALL, 1).setTitle(ChatUtil.fixColor("&4&lSniezki")).addLore(ChatUtil.fixColor("&8» &7Posiadasz: &c" + u.getSnow()));
        final ItemBuilder ice1 = new ItemBuilder(Material.ICE, 1).setTitle(ChatUtil.fixColor("&4&lLOD")).addLore(ChatUtil.fixColor("&8» &7Posiadasz: &c" + u.getIce()));
        final ItemBuilder all = new ItemBuilder(Material.HOPPER).setTitle(ChatUtil.fixColor("&4&lDobieranie calego limitu"));
        final ItemBuilder arrow1 = new ItemBuilder(Material.ARROW, 1).setTitle(ChatUtil.fixColor("&4&lStrzaly")).addLore(ChatUtil.fixColor("&8» &7Posiadasz: &c" + u.getArrow()));
        final ItemBuilder air = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setTitle(ChatUtil.fixColor("&8&l#"));
        inv.setItem(0, air.build());
        inv.setItem(1, air.build());
        inv.setItem(2, air.build());
        inv.setItem(3, air.build());
        inv.setItem(4, air.build());
        inv.setItem(5, air.build());
        inv.setItem(6, air.build());
        inv.setItem(7, air.build());
        inv.setItem(8, air.build());
        inv.setItem(9, air.build());
        inv.setItem(10, air.build());
        inv.setItem(11, air.build());
        inv.setItem(12, Refil.build());
        inv.setItem(13, Kox.build());
        inv.setItem(14, Perla.build());
        inv.setItem(15, air.build());
        inv.setItem(16, air.build());
        inv.setItem(17, air.build());
        inv.setItem(18, air.build());
        inv.setItem(19, air.build());
        inv.setItem(20, air.build());
        inv.setItem(21, air.build());
        inv.setItem(22, air.build());
        inv.setItem(23, air.build());
        inv.setItem(24, air.build());
        inv.setItem(25, air.build());
        inv.setItem(26, air.build());
        inv.setItem(27, air.build());
        inv.setItem(28, air.build());
        inv.setItem(29, air.build());
        inv.setItem(30, snown.build());
        inv.setItem(31, all.build());
        inv.setItem(32, arrow1.build());
        inv.setItem(33, air.build());
        inv.setItem(34, air.build());
        inv.setItem(35, air.build());
        inv.setItem(36, air.build());
        inv.setItem(37, air.build());
        inv.setItem(38, air.build());
        inv.setItem(39, air.build());
        inv.setItem(40, air.build());
        inv.setItem(41, air.build());
        inv.setItem(42, air.build());
        inv.setItem(43, air.build());
        inv.setItem(44, air.build());
        inv.setItem(45, air.build());
        inv.setItem(46, air.build());
        inv.setItem(47, air.build());
        inv.setItem(48, air.build());
        inv.setItem(49, air.build());
        inv.setItem(50, air.build());
        inv.setItem(51, air.build());
        inv.setItem(52, air.build());
        inv.setItem(53, air.build());
        return p.openInventory(inv);
    }
}
