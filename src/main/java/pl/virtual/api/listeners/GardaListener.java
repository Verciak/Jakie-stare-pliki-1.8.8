 package pl.virtual.api.listeners;

import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import pl.virtual.api.API.Config;

import org.bukkit.entity.*;

import org.bukkit.*;
import org.bukkit.event.*;

public class GardaListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void on(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        final Location to = e.getTo();
        final Location from = e.getFrom();
        if (Config.ENABLE_GARDA && (to.getBlockX() != from.getBlockX() || to.getBlockY() != from.getBlockY() || to.getBlockZ() != from.getBlockZ()) && p.isBlocking()) {
            final ItemStack s = p.getItemInHand();
            p.setItemInHand(s);
        }
    }
}
