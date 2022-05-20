package pl.virtual.api.listeners;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import pl.virtual.api.gui.BlokiGui;
import pl.virtual.api.managers.BlokiManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.*;
import org.bukkit.event.*;

public class BlokiListener implements Listener
{
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        if (!ChatUtil.fixColor("&4&lBloki").equalsIgnoreCase(e.getView().getTitle())) {
            return;
        }
        e.setCancelled(true);
        if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.EMERALD_BLOCK && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.fixColor("&c&lZmien sztabki emeraldow na bloki"))) {
            BlokiManager.replacer(p, Material.EMERALD, Material.EMERALD_BLOCK);
        }
        if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.GOLD_BLOCK && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.fixColor("&c&lZmien sztabki zlota na bloki"))) {
            BlokiManager.replacer(p, Material.GOLD_INGOT, Material.GOLD_BLOCK);
        }
        if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.IRON_BLOCK && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.fixColor("&c&lZmien sztabki zelaza na bloki"))) {
            BlokiManager.replacer(p, Material.IRON_INGOT, Material.IRON_BLOCK);
        }
        if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.DIAMOND_BLOCK && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.fixColor("&c&lZmien sztabki diamentow na bloki"))) {
            BlokiManager.replacer(p, Material.DIAMOND, Material.DIAMOND_BLOCK);
        }
        if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.HOPPER && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatUtil.fixColor("&c&lZmien wszystko na bloki"))) {
            BlokiManager.replacer(p, Material.IRON_INGOT, Material.IRON_BLOCK);
            BlokiManager.replacer(p, Material.GOLD_INGOT, Material.GOLD_BLOCK);
            BlokiManager.replacer(p, Material.DIAMOND, Material.DIAMOND_BLOCK);
            BlokiManager.replacer(p, Material.EMERALD, Material.EMERALD_BLOCK);
        }
        BlokiGui.show(p);
    }
}
