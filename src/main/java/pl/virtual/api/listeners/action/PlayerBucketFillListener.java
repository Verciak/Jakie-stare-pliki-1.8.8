package pl.virtual.api.listeners.action;

import org.bukkit.event.player.*;

import pl.virtual.api.managers.UserManager;

import org.bukkit.event.*;

public class PlayerBucketFillListener implements Listener
{
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerBucketFill(final PlayerBucketFillEvent event) {
        event.setCancelled(BlockBreakListener.cancelAction(event.getPlayer(), event.getBlockClicked().getLocation(), "&9&lERROR: &7Nie mozesz edytowac terenu gildii"));
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerBucketFill1(final PlayerBucketFillEvent event) {
        event.setCancelled(!UserManager.canPlaceByBorder(event.getPlayer(), event.getBlockClicked().getLocation(), "&9&lERROR: &7Nie mozesz edytowac terenu przy borderze"));
    }
}
