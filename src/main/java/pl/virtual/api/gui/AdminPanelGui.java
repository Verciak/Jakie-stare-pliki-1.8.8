package pl.virtual.api.gui;

import org.bukkit.entity.*;

import pl.virtual.api.API.Config;
import pl.virtual.api.managers.BanManager;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.MuteManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.*;

import org.bukkit.*;
import org.bukkit.inventory.*;

public class AdminPanelGui
{
    public static InventoryView show(final Player p) {
        final ItemBuilder S3 = new ItemBuilder(Material.RED_BANNER).setTitle(ChatUtil.fixColor("&c&lWylacz bezpiecznie Serwer &8(&fMain&8)")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&cWylacza bezpiecznie serwer &fMain")).addLore(ChatUtil.fixColor("&cDzieki temu gracz ktory jest podczas walki nie zginie")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder S4 = new ItemBuilder(Material.GREEN_BANNER).setTitle(ChatUtil.fixColor("&c&lZrestartuj bezpiecznie &f&lDarkHard_Core")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&cRestartuje config &fDarkHard_Core")).addLore(ChatUtil.fixColor("&cBez naruszania innych pluginow")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder status = new ItemBuilder(Material.PAPER, 1, (short)0).setTitle(ChatUtil.fixColor("&4&lStatus Serwera")).addLore("").addLore("&7Zarejestrowanych graczy &c" + UserManager.getUsers().size()).addLore("&7Stworzonych gildii &c" + GuildManager.getGuilds().size()).addLore("&7Zbanowanych graczy &c" + BanManager.getBans().size()).addLore("&7Wyciszonych graczy &c" + MuteManager.getMutes().size()).addLore("").addLore("&7TPS: &c" + (Ticking.getTPS())).addLore("&7Gracze: &c" + Bukkit.getOnlinePlayers().size()).addLore("&7Dostepne Rdzenie: &c" + Runtime.getRuntime().availableProcessors()).addLore("");
        final ItemBuilder air = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setTitle(ChatUtil.fixColor("&8#"));
        final ItemBuilder itemy = new ItemBuilder(Material.DIAMOND_BLOCK, 1, (short)0).setTitle(ChatUtil.fixColor("&8» &7Status: &4&lITEMY")).addLore("").addLore(ChatUtil.fixColor((Config.ENABLE_ITEMY == true) ? "&8» &7Itemy sa obecnie &2&lWLACZONE" : ("&8» &7Itemy sa obecnie &c&lWYLACZONE"))).addLore("");
        final ItemBuilder wedka = new ItemBuilder(Material.FISHING_ROD, 1, (short)0).setTitle(ChatUtil.fixColor("&8» &7Status: &4&lWEDKA")).addLore("").addLore(ChatUtil.fixColor((Config.ENABLE_WEDKA == true) ? "&8» &7Wedki sa obecnie &2&lWLACZONE" : ("&8» &7Wedki sa obecnie &c&lWYLACZONE"))).addLore("");
        final ItemBuilder dcase = new ItemBuilder(Material.ENDER_CHEST, 1, (short)0).setTitle(ChatUtil.fixColor("&8» &7Status: &4&lDCASE")).addLore("").addLore(ChatUtil.fixColor((Config.ENABLE_CASE == true) ? "&8» &7DarkCase'y sa obecnie &2&lWLACZONE" : ("&8» &7DarkCase'y sa obecnie &c&lWYLACZONE"))).addLore("");
        final ItemBuilder efekty = new ItemBuilder(Material.POTION, 1, (short)0).setTitle(ChatUtil.fixColor("&8» &7Status: &4&lEFEKTY")).addLore("").addLore(ChatUtil.fixColor((Config.ENABLE_EFEKT == true) ? "&8» &7Efekty sa obecnie &2&lWLACZONE" : ("&8» &7Efekty sa obecnie &c&lWYLACZONE"))).addLore("");
        final ItemBuilder panel = new ItemBuilder(Material.OAK_SIGN, 1, (short)0).setTitle(ChatUtil.fixColor("&8» &7Status: &4&lPANEL")).addLore("").addLore(ChatUtil.fixColor((Config.ENABLE_PANEL == true) ? "&8» &7Panel jest obecnie &2&lWLACZONY" : ("&8» &7Panel jest obecnie &c&lWYLACZONY"))).addLore("");
        final ItemBuilder kit = new ItemBuilder(Material.DIAMOND_SWORD, 1, (short)0).setTitle(ChatUtil.fixColor("&8» &7Status: &4&lKITY")).addLore("").addLore(ChatUtil.fixColor((Config.ENABLE_KIT == true) ? "&8» &7Kity sa obecnie &2&lWLACZONE" : ("&8» &7Kity sa obecnie &c&lWYLACZONE")));
        final ItemBuilder diamond = new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1, (short)0).setTitle(ChatUtil.fixColor("&8» &7Status: &4&lDIAMOND")).addLore("").addLore(ChatUtil.fixColor((Config.ENABLE_DIAMOND == true) ? "&8» &7Diamentowe itemy sa obecnie &2&lWLACZONE" : ("&8» &7Diamentowe itemy sa obecnie &c&lWYLACZONE"))).addLore("");
        final ItemBuilder create = new ItemBuilder(Material.SPONGE, 1, (short)0).setTitle(ChatUtil.fixColor("&8» &7Status: &4&lCREATE")).addLore("").addLore(ChatUtil.fixColor((Config.ENABLE_CREATE == true) ? "&8» &7Zakladanie gildii jest obecnie &2&lWLACZONE" : ("&8» &7Zakladanie gildii jest obecnie &c&lWYLACZONE"))).addLore("");
        final ItemBuilder wiad = new ItemBuilder(Material.BOOK, 1, (short)0).setTitle(ChatUtil.fixColor("&8» &7Status: &4&lWIADOMOSCI")).addLore("").addLore(ChatUtil.fixColor((Config.ENABLE_WIADOMOSCI == true) ? "&8» &7Wiadomosci o statusach sa obecnie &2&lWLACZONE" : ("&8» &7Wiadomosci o statusach sa obecnie &c&lWYLACZONE"))).addLore("");
        final ItemBuilder garda = new ItemBuilder(Material.IRON_SWORD, 1, (short)0).setTitle(ChatUtil.fixColor("&8» &7Status: &4&lGARDA")).addLore("").addLore(ChatUtil.fixColor((Config.ENABLE_GARDA == true) ? "&8» &7Wiadomosci o statusach sa obecnie &2&lWLACZONE" : ("&8» &7Wiadomosci o statusach sa obecnie &c&lWYLACZONE"))).addLore("");
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.fixColor("&4&lAdmin Panel"));
        inv.setItem(inv.getSize() - 54, air.build());
        inv.setItem(inv.getSize() - 53, air.build());
        inv.setItem(inv.getSize() - 52, air.build());
        inv.setItem(inv.getSize() - 51, air.build());
        inv.setItem(inv.getSize() - 50, air.build());
        inv.setItem(inv.getSize() - 49, air.build());
        inv.setItem(inv.getSize() - 48, air.build());
        inv.setItem(inv.getSize() - 47, air.build());
        inv.setItem(inv.getSize() - 46, air.build());
        inv.setItem(inv.getSize() - 45 , air.build());
        inv.setItem(inv.getSize() - 44, air.build());
        inv.setItem(inv.getSize() - 43, air.build());
        inv.setItem(inv.getSize() - 42, air.build());
        inv.setItem(inv.getSize() - 41, garda.build());
        inv.setItem(inv.getSize() - 40, air.build());
        inv.setItem(inv.getSize() - 39, air.build());
        inv.setItem(inv.getSize() - 38, air.build());
        inv.setItem(inv.getSize() - 37, air.build());
        inv.setItem(inv.getSize() - 36, air.build());
        inv.setItem(inv.getSize() - 35, kit.build());
        inv.setItem(inv.getSize() - 34, create.build());
        inv.setItem(inv.getSize() - 33, panel.build());
        inv.setItem(inv.getSize() - 32, efekty.build());
        inv.setItem(inv.getSize() - 31, dcase.build());
        inv.setItem(inv.getSize() - 30, wedka.build());
        inv.setItem(inv.getSize() - 29, itemy.build());
        inv.setItem(inv.getSize() - 28, air.build());
        inv.setItem(inv.getSize() - 27, air.build());
        inv.setItem(inv.getSize() - 26, air.build());
        inv.setItem(inv.getSize() - 25, air.build());
        inv.setItem(inv.getSize() - 24, air.build());
        inv.setItem(inv.getSize() - 23, diamond.build());
        inv.setItem(inv.getSize() - 22, air.build());
        inv.setItem(inv.getSize() - 21, air.build());
        inv.setItem(inv.getSize() - 20, air.build());
        inv.setItem(inv.getSize() - 19, air.build());
        inv.setItem(inv.getSize() - 18, air.build());
        inv.setItem(inv.getSize() - 17, air.build());
        inv.setItem(inv.getSize() - 16, air.build());
        inv.setItem(inv.getSize() - 15, air.build());
        inv.setItem(inv.getSize() - 14, wiad.build());
        inv.setItem(inv.getSize() - 13, air.build());
        inv.setItem(inv.getSize() - 12, air.build());
        inv.setItem(inv.getSize() - 11, air.build());
        inv.setItem(inv.getSize() - 10, air.build());
        inv.setItem(inv.getSize() - 9, status.build());
        inv.setItem(inv.getSize() - 8, air.build());
        inv.setItem(inv.getSize() - 7, air.build());
        inv.setItem(inv.getSize() - 6, air.build());
        inv.setItem(inv.getSize() - 5, air.build());
        inv.setItem(inv.getSize() - 4, air.build());
        inv.setItem(inv.getSize() - 3, air.build());
        inv.setItem(inv.getSize() - 2, S4.build());
        inv.setItem(inv.getSize() - 1, S3.build());
        return p.openInventory(inv);
    }
}
