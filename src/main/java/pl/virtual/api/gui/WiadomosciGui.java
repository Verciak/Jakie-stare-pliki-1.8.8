package pl.virtual.api.gui;

import org.bukkit.entity.*;
import org.bukkit.*;

import pl.virtual.api.data.base.user.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.inventory.*;

public class WiadomosciGui
{
    public static void show(final Player p) {
        final User u = UserManager.getUser(p);
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 18, ChatUtil.fixColor("&4&LCHAT MANAGER"));
        final ItemBuilder autoMessages = new ItemBuilder(Material.PAPER).setTitle(ChatUtil.fixColor("&6&lAutomatyczne wiadomosci")).addLore(ChatUtil.fixColor("&8» &7Kliknij na przedmiot, aby zmienic status!"));
        final ItemBuilder privateMessages = new ItemBuilder(Material.PAPER).setTitle(ChatUtil.fixColor("&6&lPrywatne wiadomosci")).addLore(ChatUtil.fixColor("&8» &7Kliknij na przedmiot, aby zmienic status!"));
        final ItemBuilder DeathMessages = new ItemBuilder(Material.PAPER).setTitle(ChatUtil.fixColor("&6&lWiadomosci o smierciach")).addLore(ChatUtil.fixColor("&8» &7Kliknij na przedmiot, aby zmienic status!"));
        final ItemBuilder cratesMessages = new ItemBuilder(Material.PAPER).setTitle(ChatUtil.fixColor("&6&lWiadomosci z magicznych skrzynek")).addLore(ChatUtil.fixColor("&8» &7Kliknij na przedmiot, aby zmienic status!"));
        final ItemBuilder ShopMessages = new ItemBuilder(Material.PAPER).setTitle(ChatUtil.fixColor("&6&lWiadomosci z sklepu")).addLore(ChatUtil.fixColor("&8» &7Kliknij na przedmiot, aby zmienic status!"));
        final ItemBuilder air = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setTitle(ChatUtil.fixColor("&8#"));
        final Material shortAutoMessage = u.isAutoMessages()  ?  Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE;
        final String titleAutoMessage = u.isAutoMessages() ? "&a&lON" : "&c&lOFF";
        final ItemBuilder statusAutoMessage = new ItemBuilder(shortAutoMessage, 1).setTitle(ChatUtil.fixColor(titleAutoMessage));
        final Material shortPrivateMessage = u.isPrivateMessages() ? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE;
        final String titlePrivateMessage = u.isPrivateMessages() ? "&a&lON" : "&c&lOFF";
        final ItemBuilder statusPrivateMessage = new ItemBuilder(shortPrivateMessage, 1).setTitle(ChatUtil.fixColor(titlePrivateMessage));
        final Material shortDeathMessage = u.isDeathMessages() ? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE;
        final String titleDeathMessage = u.isDeathMessages() ? "&a&lON" : "&c&lOFF";
        final ItemBuilder statusDeathMessage = new ItemBuilder(shortDeathMessage, 1).setTitle(ChatUtil.fixColor(titleDeathMessage));
        final Material shortEggsMessage = u.isEggsMessages() ? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE;
        final String titleEggsMessage = u.isEggsMessages() ? "&a&lON" : "&c&lOFF";
        final ItemBuilder statusEggsMessage = new ItemBuilder(shortEggsMessage,1).setTitle(ChatUtil.fixColor(titleEggsMessage));
        final Material shortCratesMessage = u.isCratesMessages() ? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE;
        final String titleCratesMessage = u.isCratesMessages() ? "&a&lON" : "&c&lOFF";
        final ItemBuilder statusCratesMessage = new ItemBuilder(shortCratesMessage, 1).setTitle(ChatUtil.fixColor(titleCratesMessage));
        final Material shortScratchesMessage = u.isScratchesMessages() ? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE;
        final String titleScratchesMessage = u.isScratchesMessages() ? "&a&lON" : "&c&lOFF";
        final ItemBuilder statusScratchesMessage = new ItemBuilder(shortScratchesMessage, 1).setTitle(ChatUtil.fixColor(titleScratchesMessage));
        inv.setItem(inv.getSize() - 18, air.build());
        inv.setItem(inv.getSize() - 17, autoMessages.build());
        inv.setItem(inv.getSize() - 16, privateMessages.build());
        inv.setItem(inv.getSize() - 15, DeathMessages.build());
        inv.setItem(inv.getSize() - 14, air.build());
        inv.setItem(inv.getSize() - 13, air.build());
        inv.setItem(inv.getSize() - 12, air.build());
        inv.setItem(inv.getSize() - 11, air.build());
        inv.setItem(inv.getSize() - 10, air.build());
        inv.setItem(inv.getSize() - 9, air.build());
        inv.setItem(inv.getSize() - 8, statusAutoMessage.build());
        inv.setItem(inv.getSize() - 7, statusPrivateMessage.build());
        inv.setItem(inv.getSize() - 6, statusDeathMessage.build());
        inv.setItem(inv.getSize() - 5, air.build());
        inv.setItem(inv.getSize() - 4, air.build());
        inv.setItem(inv.getSize() - 3, air.build());
        inv.setItem(inv.getSize() - 2, air.build());
        inv.setItem(inv.getSize() - 1, air.build());
        p.openInventory(inv);
    }
}