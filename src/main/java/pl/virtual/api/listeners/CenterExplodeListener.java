// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.utils.TimeUtil;

public class CenterExplodeListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void explode(final EntityExplodeEvent e) {
        Guild g = GuildManager.getGuildByLoc(e.getEntity().getLocation());
        List<Block> blocks = e.blockList();
        if(g != null) {
        blocks.removeIf(block -> g.getRegion().isInCentrum(block.getLocation(), 3, 1, 2));
        g.setLastExplodeTime(System.currentTimeMillis() + TimeUtil.MINUTE.getTime(2));
        }
	}
    
    
    
    
    	@EventHandler
        public void onBlockPistonRetractEvent(BlockPistonRetractEvent e) {
            BlockFace bf = e.getDirection();
            Block b = e.getBlock();
            for (int i = 1; i <= 15; ++i) {
                if (b.getRelative(bf, i).getType() == Material.SPONGE) {
                    e.setCancelled(true);
                    break;
                }
            }
            for (int i = 1; i <= 15; ++i) {
                final Guild g = GuildManager.getGuild(e.getBlock().getLocation());
                if (g != null && g.getRegion().isInCentrum(e.getBlock().getLocation(), 16, 16, 16)) {
                	if (b.getRelative(bf, i).getType() == Material.AIR) {
                		e.setCancelled(true);
                    	break;
                	}
                }
            }
        }
    	
        
        @EventHandler(priority = EventPriority.HIGHEST)
        public void onPlayerBucketFill2(final BlockFromToEvent e) {
            final Location loc = e.getBlock().getLocation();
            final Block b = e.getBlock();
            final Guild g = GuildManager.getGuild(e.getBlock().getLocation());
            if (g != null && g.getRegion().isInCentrum(e.getBlock().getLocation(), 5, 2, 2) && b.getType() != Material.LAVA && b.getType() != Material.WATER) {
            	e.setCancelled(true);
                loc.getBlock().setType(Material.AIR);
            }
        }
        
    	@EventHandler
        public void onBlockPistonExtendEvent(BlockPistonExtendEvent e) {
            BlockFace bf = e.getDirection();
            Block b = e.getBlock();
            for (int i = 1; i <= 15; ++i) {
                if (b.getRelative(bf, i).getType() == Material.SPONGE) {
                    e.setCancelled(true);
                    break;
                }
            }
            for (int i = 1; i <= 15; ++i) {
                final Guild g = GuildManager.getGuildByLoc(e.getBlock().getLocation());
                if (g != null && g.getRegion().isInCentrum(e.getBlock().getLocation(), 16, 16, 16)) {
                	if (b.getRelative(bf, i).getType() == Material.AIR) {
                		e.setCancelled(true);
                    	break;
                	}
                }
            }
        }
    }