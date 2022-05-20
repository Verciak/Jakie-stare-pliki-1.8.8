// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

import pl.virtual.api.API.Config;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

public class BlockCraftingListener implements Listener
{
    @EventHandler
    public void onCraftItem(final CraftItemEvent e) {
        final ItemStack item = e.getRecipe().getResult();
        final Player p = (Player)e.getWhoClicked();
        if (!Config.ENABLE_DIAMOND && (item.getType() == Material.DIAMOND_AXE || item.getType() == Material.BEACON || item.getType() == Material.DIAMOND_HELMET || item.getType() == Material.DIAMOND_CHESTPLATE || item.getType() == Material.DIAMOND_LEGGINGS || item.getType() == Material.DIAMOND_BOOTS ||item.getType() == Material.DIAMOND_AXE || item.getType() == Material.DIAMOND_SWORD)) {
            ChatUtil.error(p, "&9&lERROR: &7Crafting jest tymczasowo zablokowany");
            e.setCancelled(true);
        }
    }
    
    
    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            return;
        }
        if (!Config.ENABLE_WEDKA && e.getPlayer().getInventory().getItemInHand().getType().equals((Object)Material.FISHING_ROD)) {
                e.setCancelled(true);
            }
        }
}
