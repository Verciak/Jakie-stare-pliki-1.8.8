package pl.virtual.api.listeners;

import org.bukkit.entity.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.*;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.RandomUtil;

import org.bukkit.event.*;
import org.bukkit.event.block.BlockBreakEvent;

public class AppleListener implements Listener
{    
	
    public static boolean isInNoBuild(final Location loc) {
        return loc.getBlockX() <= Config.REGION_PVP && loc.getBlockX() >= -Config.REGION_PVP - 1 && loc.getBlockZ() <= Config.REGION_PVP && loc.getBlockZ() >= -Config.REGION_PVP - 1;
    }
	
	@EventHandler
    public void onBreak(final BlockBreakEvent e) {
        final Player player = e.getPlayer();
        final Guild g = GuildManager.getGuild(e.getBlock().getLocation());
        if (e.getBlock().getType() == Material.LEGACY_LEAVES) {
        	if (g != null && !g.isMember(e.getPlayer().getName()) && !e.getPlayer().hasPermission("core.cmd.mod")) {
        		return;
        	}
        	if (isInNoBuild(e.getPlayer().getLocation())) {
        		return;
        	}
            if (RandomUtil.getChance(8.0)) {
                final int r = RandomUtil.getRandInt(1, 3);
                final ItemStack apple = new ItemStack(Material.APPLE, r);
                player.getInventory().addItem(new ItemStack[] { apple });
                ChatUtil.sendActionBar(player, ChatUtil.fixColor("&7Trafiles na: &6Jabko &7(" + r + "szt)"));
            }
        }
    }
}

