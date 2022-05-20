package pl.virtual.api.listeners;
import org.bukkit.event.entity.*;

import pl.virtual.api.data.base.drops.DropData;
import pl.virtual.api.data.base.drops.DropType;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.DropManager;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.utils.RandomUtil;
import pl.virtual.api.utils.SpaceUtil;
import pl.virtual.api.utils.TimeUtil;

import org.bukkit.block.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.event.*;

public class GuildExplodeListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void explode(final EntityExplodeEvent e) {
        Guild g = GuildManager.getGuildByLoc(e.getEntity().getLocation());
        if (g == null) {
            for (final Block b : e.blockList()) {
                if (g != null) {
                    continue;
                }
                final Guild o = GuildManager.getGuild(b.getLocation());
                if (o == null) {
                    continue;
                }
                g = o;
            }
        }
        if (g == null) {
            e.setCancelled(true);
            return;
        }
        g.setLastExplodeTime(System.currentTimeMillis() + TimeUtil.MINUTE.getTime(1));
        g.message("");
        g.message("&8[&4&lTNT&8] &7Na Terenie wybuchlo TNT");
        g.message("");
        if (g.getRegion().isInCentrum(e.getLocation(), 3, 1, 2)) {
            e.setCancelled(true);
            return;
        }
        if (g.getCreateTime() + TimeUtil.HOUR.getTime(24) > System.currentTimeMillis()) {
            e.setCancelled(true);
            return;
        }
        final List<Location> sphere = SpaceUtil.sphere(e.getLocation(), 5, 5, false, true, 0);
        for (final Location location : sphere) {
            if (location.getBlock().getType() == Material.OBSIDIAN) {
                if (!RandomUtil.getChance(5.0)) {
                    continue;
                }
                location.getBlock().setType(Material.AIR);
            }
            else if (location.getBlock().getType() == Material.LEGACY_STATIONARY_WATER) {
                if (!RandomUtil.getChance(10.0)) {
                    continue;
                }
                location.getBlock().setType(Material.AIR);
            }
            else if (location.getBlock().getType() == Material.LEGACY_STATIONARY_LAVA) {
                if (!RandomUtil.getChance(10.0)) {
                    continue;
                }
                location.getBlock().setType(Material.AIR);
            }
            else if (location.getBlock().getType() == Material.WATER) {
                if (!RandomUtil.getChance(50.0)) {
                    continue;
                }
                location.getBlock().setType(Material.AIR);
            }
            else if (location.getBlock().getType() == Material.LAVA) {
                if (!RandomUtil.getChance(50.0)) {
                    continue;
                }
                location.getBlock().setType(Material.AIR);
            }
            else if (location.getBlock().getType() == Material.ENDER_CHEST) {
                if (!RandomUtil.getChance(50.0)) {
                    continue;
                }
                location.getBlock().setType(Material.AIR);
            }
            else {
                if (location.getBlock().getType() != Material.ENCHANTING_TABLE) {
                    continue;
                }
                location.getBlock().setType(Material.AIR);
            }
        }
    }
    
  @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityExplode312(final EntityExplodeEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final Iterator<Block> blocks = event.blockList().iterator();
        Block b = null;
        while (blocks.hasNext()) {
            b = blocks.next();
            final DropData d = DropManager.getDropData(b.getType());
            if (d.getDropType() == DropType.CANCEL_DROP) {
                b.setType(Material.AIR);
            }
        }
    }
}
