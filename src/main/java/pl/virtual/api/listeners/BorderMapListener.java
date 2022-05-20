package pl.virtual.api.listeners;

import pl.virtual.api.API.Config;
import pl.virtual.api.utils.*;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.*;

public class BorderMapListener implements Listener
{
    public static void setBorder() {
        final World w = Bukkit.getWorld("world");
        final WorldBorder wb = w.getWorldBorder();
        final Location loc = w.getSpawnLocation();
        wb.setCenter(loc);
        wb.setSize((double)(Config.BORDER_WORLD_RADIUS * 2));
    }
    
	@EventHandler
    public static void onMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        final Location to = event.getTo();
        if (event.getTo().getBlockX() > Config.BORDER_WORLD_RADIUS || event.getTo().getBlockX() < -Config.BORDER_WORLD_RADIUS || event.getTo().getBlockZ() > Config.BORDER_WORLD_RADIUS || event.getTo().getBlockZ() < -Config.BORDER_WORLD_RADIUS) {
            event.setTo(event.getFrom());
            if (to.getBlockX() != -58081 && to.getBlockY() != -58083 && to.getBlockZ() != -58081) {
                player.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Osiagnales granice swiata" + ChatColor.GRAY + " (" + Config.BORDER_WORLD_RADIUS + " kratek)"));
                player.playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
            }
        }
    }
    
//    @EventHandler
//    public void onPlayerTeleport(final PlayerTeleportEvent e) {
//        final Player p = e.getPlayer();
//        if (p.getWorld().getName().toLowerCase().equals("world") && e.getCause().equals((Object)PlayerTeleportEvent.TeleportCause.ENDER_PEARL) && (e.getTo().getX() > Config.BORDER_WORLD_RADIUS || e.getTo().getX() < -Config.BORDER_WORLD_RADIUS || e.getTo().getZ() > Config.BORDER_WORLD_RADIUS || e.getTo().getZ() < -Config.BORDER_WORLD_RADIUS)) {
//            e.setCancelled(true);
//            p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Granica swiata osiagnieta " + ChatColor.GRAY + " (" + Config.BORDER_WORLD_RADIUS + " kratek)"));
//            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 5.0f, 0.75f);
//        }
//    }
    
    @EventHandler
    public static void onMove1(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        final Location to = event.getTo();
        if (player.getWorld().getName().toLowerCase().equals("world_nether") && (event.getTo().getBlockX() > Config.BORDER_NETHERWORLD_RADIUS || event.getTo().getBlockX() < -Config.BORDER_NETHERWORLD_RADIUS || event.getTo().getBlockZ() > Config.BORDER_NETHERWORLD_RADIUS || event.getTo().getBlockZ() < -Config.BORDER_NETHERWORLD_RADIUS)) {
            event.setTo(event.getFrom());
            if (to.getBlockX() != -58081 && to.getBlockY() != -58083 && to.getBlockZ() != -58081) {
                player.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Osiagnales granice swiata" + ChatColor.GRAY + " (" + Config.BORDER_NETHERWORLD_RADIUS + " kratek)"));
                player.playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
            }
        }
    }
    
    @EventHandler
    public void onPlayerTeleport1(final PlayerTeleportEvent e) {
        final Player p = e.getPlayer();
        if (p.getWorld().getName().toLowerCase().equals("world_nether") && e.getCause().equals((Object)PlayerTeleportEvent.TeleportCause.ENDER_PEARL) && (e.getTo().getX() > Config.BORDER_NETHERWORLD_RADIUS - 10.0 || e.getTo().getX() < -Config.BORDER_NETHERWORLD_RADIUS - 10.0 || e.getTo().getZ() > Config.BORDER_NETHERWORLD_RADIUS - 10.0 || e.getTo().getZ() < -Config.BORDER_NETHERWORLD_RADIUS - 10.0)) {
            e.setCancelled(true);
            p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Osiagnales granice netheru" + ChatColor.GRAY + " (" + Config.BORDER_NETHERWORLD_RADIUS + " kratek)"));
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 5.0f, 0.75f);
        }
    }
}
