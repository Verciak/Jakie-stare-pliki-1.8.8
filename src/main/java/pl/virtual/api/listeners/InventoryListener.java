// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import java.util.List;

import org.bukkit.ChatColor;

import pl.virtual.api.API.Config;
import pl.virtual.api.commands.cmd.StoneCommand;
import pl.virtual.api.data.base.drops.Drop;
import pl.virtual.api.data.base.drops.RandomDropData;
import pl.virtual.api.gui.SklepGui;
import pl.virtual.api.gui.WiadomosciGui;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;

public class InventoryListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onClickStone(final InventoryClickEvent e) {
        if (!ChatUtil.fixColor("&9&lDrop").equalsIgnoreCase(e.getView().getTitle())) {
            return;
        }
        e.setCancelled(true);
        final ItemStack item = e.getCurrentItem();
        if (item != null) {
            final ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                final Drop d = RandomDropData.getDropByName(ChatColor.stripColor(ChatUtil.fixColor(meta.getDisplayName())));
                if (d != null) {
                    d.changeStatus(e.getWhoClicked().getUniqueId());
                    final List<String> string = (List<String>)meta.getLore();
                    string.set(4, ChatUtil.fixColor(" &8» &7Drop: " + (d.isDisabled(e.getWhoClicked().getUniqueId()) ? "&cOFF" : "&aON")));
                    meta.setLore((List)string);
                    item.setItemMeta(meta);
                }
                e.setCancelled(true);
                final ItemStack item1 = e.getCurrentItem();
                if (item1 != null) {
                    final ItemMeta meta1 = item1.getItemMeta();
                    if (meta1 != null) {
                        if (meta1.getDisplayName() != null && meta1.getDisplayName().equals(ChatUtil.fixColor("&9&lWiadomosci z Dropu"))) {
                            RandomDropData.changeNoMsg(e.getWhoClicked().getUniqueId());
                            final List<String> string = (List<String>)meta.getLore();
                            string.set(0, ChatUtil.fixColor(" &8» &7Wiadomosci: &" + (RandomDropData.isDropMessages(e.getWhoClicked().getUniqueId()) ? "cOFF" : "aON")));
                            meta.setLore((List)string);
                            item.setItemMeta(meta);
                        }
                            if (meta1.getDisplayName() != null && meta1.getDisplayName().equals(ChatUtil.fixColor("&9&lCobbleStone"))) {
                            RandomDropData.changeNoCobble(e.getWhoClicked().getUniqueId());
                            final List<String> string = (List<String>)meta1.getLore();
                            string.set(0, ChatUtil.fixColor(" &8» &7Drop: &" + (RandomDropData.isNoCobble(e.getWhoClicked().getUniqueId()) ? "cOFF" : "aON")));
                            meta1.setLore((List)string);
                            item1.setItemMeta(meta1);
                        }
                    }
                }
                final Player p = (Player)e.getWhoClicked();
                if (meta != null && meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&cWroc do poprzedniej strony"))) {
                    p.closeInventory();
                    StoneCommand.show1(p);
                }
                else if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&aWlacz Wszystkie Dropy"))) {
                    for (final Drop drop : RandomDropData.getDrops()) {
                        drop.setStatus(e.getWhoClicked().getUniqueId(), true);
                    }
                    StoneCommand.show(p);
                }
                else if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&cWylacz Wszystkie Dropy"))) {
                    for (final Drop drop : RandomDropData.getDrops()) {
                        drop.setStatus(e.getWhoClicked().getUniqueId(), false);
                    }
                    StoneCommand.show(p);
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onClickMenu(final InventoryClickEvent e) {
        if (!ChatUtil.fixColor("&4&lDrop menu").equalsIgnoreCase(e.getView().getTitle())) {
            return;
        }
        e.setCancelled(true);
        final ItemStack item = e.getCurrentItem();
        if (item != null) {
            final ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                if (meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&4&lDrop"))) {
                    final Player p = (Player)e.getWhoClicked();
                    StoneCommand.show(p);
                }
                else if (Config.ENABLE_DIAMOND && meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&4&lDrop z DarkCase"))) {
                    final Player p = (Player)e.getWhoClicked();
                    p.closeInventory();
                    StoneCommand.show4(p);
                }
                else if (!Config.ENABLE_DIAMOND && meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&4&lDrop z DarkCase"))) {
                    final Player p = (Player)e.getWhoClicked();
                    p.closeInventory();
                    StoneCommand.show5(p);
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onClickc(final InventoryClickEvent e) {
        if (!ChatUtil.fixColor("&4&lDrop z DarkCase").equalsIgnoreCase(e.getView().getTitle())) {
            return;
        }
        final Player p = (Player)e.getWhoClicked();
        e.setCancelled(true);
        final ItemStack item = e.getCurrentItem();
        if (item != null) {
            final ItemMeta meta = item.getItemMeta();
            if (meta != null && meta != null && meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&cWroc do poprzedniej strony"))) {
                p.closeInventory();
                StoneCommand.show1(p);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onClicdk1ss1(final InventoryClickEvent e) {
        if (!ChatUtil.fixColor("&4&lDrop z DarkCase").equalsIgnoreCase(e.getView().getTitle())) {
            return;
        }
        final Player p = (Player)e.getWhoClicked();
        e.setCancelled(true);
        final ItemStack item = e.getCurrentItem();
        if (item != null) {
            final ItemMeta meta = item.getItemMeta();
            if (meta != null && meta != null && meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&cWroc do poprzedniej strony"))) {
                p.closeInventory();
                StoneCommand.show1(p);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onClicdk1ss2(final InventoryClickEvent e) {
        if (!ChatUtil.fixColor("&4&lDrop z DarkCase").equalsIgnoreCase(e.getView().getTitle())) {
            return;
        }
        final Player p = (Player)e.getWhoClicked();
        e.setCancelled(true);
        final ItemStack item = e.getCurrentItem();
        if (item != null) {
            final ItemMeta meta = item.getItemMeta();
            if (meta != null && meta != null && meta.getDisplayName() != null && meta.getDisplayName().equals(ChatUtil.fixColor("&4&l6/3/3"))) {
                p.closeInventory();
            }
        }
    }
}
