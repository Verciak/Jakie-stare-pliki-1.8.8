package pl.virtual.api.listeners.action;

import org.bukkit.event.block.*;

import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.command.*;
import org.bukkit.event.*;

public class BlockPlaceListener implements Listener
{
	
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlace(final BlockPlaceEvent event) {
        final Guild g = GuildManager.getGuild(event.getBlock().getLocation());
        if (!event.getPlayer().hasPermission("core.cmd.mod") && g != null && g.isMember(event.getPlayer().getName()) && g.getLastExplodeTime() > System.currentTimeMillis()) {
            ChatUtil.sendMessage((CommandSender)event.getPlayer(), "&9&lERROR: &7Na terenie gildii wybuchlo tnt Budowac mozesz dopiero za " + DataUtil.secondsToString(g.getLastExplodeTime()));
            event.setCancelled(true);
            return;
             }
        	event.setCancelled(BlockBreakListener.cancelAction(event.getPlayer(), event.getBlock().getLocation(), "&9&lERROR: &7Nie mozesz edytowac terenu gildii"));
    	}
	}
