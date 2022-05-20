package pl.virtual.api.gui;

import org.bukkit.entity.*;
import org.bukkit.*;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.data.base.user.Upr;
import pl.virtual.api.managers.UprManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemBuilder;

import org.bukkit.inventory.*;

public final class PermsGui
{
    private ServerPlugin plugin;
    
    public void ServerPlugin(final ServerPlugin plugin) {
        this.plugin = plugin;
    }
    
    public static InventoryView show(final Player lider, Player gracz) {
        Upr perms = UprManager.getUser(gracz);
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.fixColor("&4&lUprawnienia&8:&7 " + perms.getName()));
        final ItemBuilder air = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setTitle(ChatUtil.fixColor("&8#"));
        final ItemBuilder break_blocks = new ItemBuilder(Material.DIAMOND_PICKAXE, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &4&lNISZCZENIE BLOKOW &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Uprawnienie to pozwala graczowi")).addLore(ChatUtil.fixColor("&8» &7Swobodnie niszczyc bloki &f(&7Poza BEACON&f)")).addLore(ChatUtil.fixColor("&8» &7Na terenie twojej gildii")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Kliknij PPM, aby")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor((perms.getBreak() == 1) ? "                  &c&nZABRAC UPRAWNIENIE&c     " : ("                  &a&nNADAC UPRAWNIENIE&a     ")));
        final ItemBuilder place_blocks = new ItemBuilder(Material.STONE, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &4&lSTAWIANIE BLOKOW &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Uprawnienie to pozwala graczowi")).addLore(ChatUtil.fixColor("&8» &7Swobodnie stawiac bloki &f(&7Poza TNT&f)")).addLore(ChatUtil.fixColor("&8» &7Na terenie twojej gildii")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Kliknij PPM, aby")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor((perms.getPlace() == 1) ? "               &c&nZABRAC UPRAWNIENIE&c     " : ("               &a&nNADAC UPRAWNIENIE&a     "))).addLore(ChatUtil.fixColor(""));
        final ItemBuilder place_tnt = new ItemBuilder(Material.TNT, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &4&lSTAWIANIE TNT &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Uprawnienie to pozwala graczowi")).addLore(ChatUtil.fixColor("&8» &7Stawiac TNT ponizej 50 kratki")).addLore(ChatUtil.fixColor("&8» &7Na terenie twojej gildii")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Kliknij PPM, aby")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor((perms.getPlacetnt() == 1) ? "             &c&nZABRAC UPRAWNIENIE&c     " : ("             &a&nNADAC UPRAWNIENIE&a     "))).addLore(ChatUtil.fixColor(""));
        final ItemBuilder break_beacon = new ItemBuilder(Material.BEACON, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &4&lNISZCZENIE BEACONA &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Uprawnienie to pozwala graczowi")).addLore(ChatUtil.fixColor("&8» &7Niszczyc BEACONA")).addLore(ChatUtil.fixColor("&8» &7Na terenie twojej gildii")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Kliknij PPM, aby")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor((perms.getBreakbeacon() == 1) ? "               &c&nZABRAC UPRAWNIENIE&c     " : ("               &a&nNADAC UPRAWNIENIE&a     "))).addLore(ChatUtil.fixColor(""));
        final ItemBuilder teleport_players = new ItemBuilder(Material.LEGACY_WATCH, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &4&lTELEPORTOWANIE OSOB &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Uprawnienie to pozwala graczowi")).addLore(ChatUtil.fixColor("&8� &7Teleportowac osoby")).addLore(ChatUtil.fixColor("&8� &7Na terenie twojej gildii")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Kliknij PPM, aby")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor((perms.getTeleportplayers() == 1) ? "               &c&nZABRAC UPRAWNIENIE&c     " : ("               &a&nNADAC UPRAWNIENIE&a     "))).addLore(ChatUtil.fixColor(""));
        final ItemBuilder open_chest = new ItemBuilder(Material.CHEST, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &4&lOTWIERANIE SKRZYNEK &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Uprawnienie to pozwala graczowi")).addLore(ChatUtil.fixColor("&8� &7Otwierac skrzynki")).addLore(ChatUtil.fixColor("&8� &7Na terenie twojej gildii")).addLore(ChatUtil.fixColor("&8� &f(&7Enderchest mozna otwierac bez uprawnien&f)")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Kliknij PPM, aby")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor((perms.getOpenchest() == 1) ? "                     &c&nZABRAC UPRAWNIENIE&c     " : ("                     &a&nNADAC UPRAWNIENIE&a     "))).addLore(ChatUtil.fixColor(""));
        final ItemBuilder spilling_water = new ItemBuilder(Material.BUCKET, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &4&lWYLEWANIE WODY I LAWY &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Uprawnienie to pozwala graczowi na")).addLore(ChatUtil.fixColor("&8� &7Wylewanie wody i lawy ponizej 50 kratki")).addLore(ChatUtil.fixColor("&8� &7Na terenie twojej gildii")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Kliknij PPM, aby")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor((perms.getWater() == 1) ? "                 &c&nZABRAC UPRAWNIENIE&c     " : ("                 &a&nNADAC UPRAWNIENIE&a     "))).addLore(ChatUtil.fixColor(""));
        final ItemBuilder pvp = new ItemBuilder(Material.DIAMOND_SWORD, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &4&lUSTAWIANIE PVP &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Uprawnienie to pozwala graczowi")).addLore(ChatUtil.fixColor("&8� &7Wlaczyc lub wylaczyc pvp w gildii")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Kliknij PPM, aby")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor((perms.getPvp() == 1) ? "             &c&nZABRAC UPRAWNIENIE&c     " : ("             &a&nNADAC UPRAWNIENIE&a     "))).addLore(ChatUtil.fixColor(""));
        final ItemBuilder enlarge = new ItemBuilder(Material.EMERALD, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &4&lPOWIEKSZANIE GILDII &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Uprawnienie to pozwala graczowi")).addLore(ChatUtil.fixColor("&8� &7Zakupic powiekszenie terenu gildii")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Kliknij PPM, aby")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor((perms.getEnlarge() == 1) ? "              &c&nZABRAC UPRAWNIENIE&c     " : ("              &a&nNADAC UPRAWNIENIE&a     "))).addLore(ChatUtil.fixColor(""));
        final ItemBuilder prolong = new ItemBuilder(Material.PAPER, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &4&lODNOWIENIE GILDII &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Uprawnienie to pozwala graczowi")).addLore(ChatUtil.fixColor("&8� &7Zakupic odnowienie twojej gildii")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Kliknij PPM, aby")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor((perms.getProlong() == 1) ? "             &c&nZABRAC UPRAWNIENIE&c     " : ("             &a&nNADAC UPRAWNIENIE&a     "))).addLore(ChatUtil.fixColor(""));
        final ItemBuilder invite = new ItemBuilder(Material.APPLE, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &4&lZAPRASZANIE OSOB &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Uprawnienie to pozwala graczowi")).addLore(ChatUtil.fixColor("&8� &7Zapraszac osoby do twojej gildii")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Kliknij PPM, aby")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor((perms.getInvite() == 1) ? "             &c&nZABRAC UPRAWNIENIE&c     " : ("             &a&nNADAC UPRAWNIENIE&a     "))).addLore(ChatUtil.fixColor(""));
        final ItemBuilder perms1 = new ItemBuilder(Material.DIAMOND_HELMET, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &4&lEDYTOWANIE UPRAWNIEN &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Uprawnienie to pozwala graczowi")).addLore(ChatUtil.fixColor("&8� &7Edytowac uprawnienia graczom")).addLore(ChatUtil.fixColor("&8� &7Ktorzy sa w twojej gildii")).addLore(ChatUtil.fixColor("&8� &f(&7Gracze ktorzy maja to uprawnienie")).addLore(ChatUtil.fixColor("&8� &7Nie moga edytowac uprawnien innym")).addLore(ChatUtil.fixColor("&8� &7Gracza ktorzy tez maja to uprawnienie&f)")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Kliknij PPM, aby")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor((perms.getPerms() == 1) ? "                  &c&nZABRAC UPRAWNIENIE&c     " : ("                  &a&nNADAC UPRAWNIENIE&a     "))).addLore(ChatUtil.fixColor(""));
        inventory.setItem(10, break_blocks.build());
        inventory.setItem(11, place_blocks.build());
        inventory.setItem(12, place_tnt.build());
        inventory.setItem(13, break_beacon.build());
        inventory.setItem(14, teleport_players.build());
        inventory.setItem(15, open_chest.build());
        inventory.setItem(16, spilling_water.build());
        inventory.setItem(17, air.build());
        inventory.setItem(0, air.build());
        inventory.setItem(1, air.build());
        inventory.setItem(2, air.build());
        inventory.setItem(3, air.build());
        inventory.setItem(4, air.build());
        inventory.setItem(5, air.build());
        inventory.setItem(6, air.build());
        inventory.setItem(7, air.build());
        inventory.setItem(8, air.build());
        inventory.setItem(9, air.build());
        inventory.setItem(18, air.build());
        inventory.setItem(19, air.build());
        inventory.setItem(20, air.build());
        inventory.setItem(21, invite.build());
        inventory.setItem(22, enlarge.build());
        inventory.setItem(23, prolong.build());
        inventory.setItem(24, air.build());
        inventory.setItem(25, air.build());
        inventory.setItem(26, air.build());
        inventory.setItem(26, air.build());
        inventory.setItem(27, air.build());
        inventory.setItem(28, air.build());
        inventory.setItem(29, air.build());
        inventory.setItem(30, air.build());
        inventory.setItem(31, pvp.build());
        inventory.setItem(32, air.build());
        inventory.setItem(33, air.build());
        inventory.setItem(34, air.build());
        inventory.setItem(35, air.build());
        inventory.setItem(36, air.build());
        inventory.setItem(37, air.build());
        inventory.setItem(38, air.build());
        inventory.setItem(39, air.build());
        inventory.setItem(40, perms1.build());
        inventory.setItem(41, air.build());
        inventory.setItem(42, air.build());
        inventory.setItem(43, air.build());
        inventory.setItem(44, air.build());
        inventory.setItem(45, air.build());
        inventory.setItem(46, air.build());
        inventory.setItem(47, air.build());
        inventory.setItem(48, air.build());
        inventory.setItem(49, air.build());
        inventory.setItem(50, air.build());
        inventory.setItem(51, air.build());
        inventory.setItem(52, air.build());
        inventory.setItem(53, air.build());
        return lider.openInventory(inventory);
    }
}
