package pl.virtual.api.listeners.action;

import org.bukkit.event.player.*;

import pl.virtual.api.managers.UserManager;

import org.bukkit.event.*;

public class PlayerBucketEmptyListener implements Listener
{
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerBucketEmpty(final PlayerBucketEmptyEvent event) {
        event.setCancelled(BlockBreakListener.cancelAction(event.getPlayer(), event.getBlockClicked().getLocation(), "&9&lERROR: &7Nie mozesz edytowac terenu gildii"));
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerBucketEmpty1(final PlayerBucketEmptyEvent event) {
        event.setCancelled(!UserManager.canPlaceByBorder(event.getPlayer(), event.getBlockClicked().getLocation(), "&9&lERROR: &7Nie mozesz edytowac terenu przy borderze"));
    }
}
