package pl.virtual.api.listeners.action;

import org.bukkit.event.block.*;

import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.command.*;
import org.bukkit.event.*;

public class BorderPlaceListener implements Listener
{
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlace(final BlockPlaceEvent event) {
        if (!UserManager.canPlaceByBorder(null, event.getBlock().getLocation(), null)) {
            event.setCancelled(true);
            ChatUtil.sendMessage((CommandSender)event.getPlayer(), "&9&lERROR: &7Nie mozesz edytowac terenu przy borderze");
        }
    }
}
