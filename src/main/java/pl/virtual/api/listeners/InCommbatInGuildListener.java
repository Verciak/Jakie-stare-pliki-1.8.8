// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;

import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.Combat;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.CombatManager;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.Listener;

public class InCommbatInGuildListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onCommand(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        final String pcmd = e.getMessage();
        final Guild g = GuildManager.getGuild(p.getLocation());
        final Combat combat = CombatManager.getCombat(p);
        if (!p.hasPermission("core.combat.bypass") && combat != null && combat.hasFight()) {
            for (final String cmd : Config.BLOCKED_CMD_INCOMBAT) {
                if (pcmd.toLowerCase().contains("/" + cmd)) {
                    e.setCancelled(true);
                    ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Ta komenda jest zablokowana podczas walki");
                    return;
                }
            }
        }
        if (g != null && !g.isMember(p.getName()) && !p.hasPermission("core.combat.bypass")) {
            for (final String cmd2 : Config.BLOCKED_CMD_INGUILD) {
                if (pcmd.toLowerCase().contains("/" + cmd2)) {
                    e.setCancelled(true);
                    ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Ta komenda jest zablokowana na terenie gildii");
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void Linia(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        final Combat combat = CombatManager.getCombat(p);
        if (p.getWorld().getName().equalsIgnoreCase("world")) {
            if (!p.hasPermission("core.cmd.helper") && e.getTo().getBlockX() <= Config.LINIA_SIZE_WORLD + 4 && e.getTo().getBlockX() >= -(Config.LINIA_SIZE_WORLD + 4) && e.getTo().getBlockZ() <= Config.LINIA_SIZE_WORLD + 4 && e.getTo().getBlockZ() >= -(Config.LINIA_SIZE_WORLD + 4) && combat != null && combat.hasFight()) {
                final Location l = p.getLocation().subtract(p.getWorld().getSpawnLocation());
                final double distance = p.getLocation().distance(p.getWorld().getSpawnLocation());
                final Vector v = l.toVector().multiply(1.0 / distance);
                p.setVelocity(v);
            }
            if (!p.hasPermission("core.cmd.helper") && e.getTo().getBlockX() <= Config.LINIA_SIZE_WORLD && e.getTo().getBlockX() >= -Config.LINIA_SIZE_WORLD && e.getTo().getBlockZ() <= Config.LINIA_SIZE_WORLD && e.getTo().getBlockZ() >= -Config.LINIA_SIZE_WORLD && combat != null && combat.hasFight()) {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlace1(final BlockPlaceEvent e) {
        final Player p = e.getPlayer();
        final Guild g = GuildManager.getGuild(p.getLocation());
        if (g != null && g.isMember(p) && g.getRegion().isInCentrum(e.getBlock().getLocation(), 5, 1, 2)) {
            e.setCancelled(true);
            ChatUtil.error(p, "&9&lERROR: &7Nie mozesz budowac w centrum");
        }
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onBrek(final BlockBreakEvent e) {
        final Player p = e.getPlayer();
        final Guild g = GuildManager.getGuild(p.getLocation());
        if (g != null && g.isMember(p) && g.getRegion().isInCentrum(e.getBlock().getLocation(), 5, 1, 2)) {
            e.setCancelled(true);
            ChatUtil.error(p, "&9&lERROR: &7Nie mozesz niszczyc w centrum");
        }
    }
}
