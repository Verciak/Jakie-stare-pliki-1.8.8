package pl.virtual.api.listeners;

import org.bukkit.event.player.*;

import pl.virtual.api.API.Config;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class Nether implements Listener
{
    @EventHandler
    public void portal(final PlayerPortalEvent e) {
        if (e.isCancelled()) {
            return;
        }
        e.setCancelled(true);
        final Location l = e.getFrom();
        final Player p = e.getPlayer();
        if (CombatManager.getCombat(p).hasFight()) {
            ChatUtil.error(p, "&9&lERROR: &7Jestes podczas walki nie mozesz przejsc przez portal");
            return;
        }
        final World w = l.getWorld();
        if (w.getName().equals("world")) {
            if (!Config.ENABLE_NETHER) {
                e.setCancelled(true);
                return;
            }
            ChatUtil.sendMessage((CommandSender)p, "&aWitaj w piekle");
            final double y = e.getTo().getWorld().getHighestBlockYAt(e.getTo().getBlockX(), e.getTo().getBlockX()) + 1.5f;
            final Location loc = new Location(Bukkit.getWorld("world_nether"), l.getX() / 5.0, y, l.getZ() / 5.0);
            p.teleport(loc);
            e.setCancelled(true);
        }
        else if (w.getName().equals("world_nether")) {
            ChatUtil.sendMessage((CommandSender)p, "&aWitaj w normalnym Swiecie");
            final double y = e.getTo().getWorld().getHighestBlockYAt(e.getTo().getBlockX(), e.getTo().getBlockX()) + 1.5f;
            final Location loc = new Location(Bukkit.getWorld("world"), l.getX() * 5.0, y, l.getZ() * 5.0);
            p.teleport(loc);
            e.setCancelled(true);
        }
    }
}
