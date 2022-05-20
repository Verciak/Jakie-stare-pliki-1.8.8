package pl.virtual.api.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.Upr;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.UprManager;
import pl.virtual.api.utils.ChatUtil;

public class PermsListener implements Listener
{
	    @EventHandler
	    public void onBreak(final BlockBreakEvent event) {
	        final Player player = event.getPlayer();
	        final Guild g = GuildManager.getGuild(player);
	        Upr perms = UprManager.getUser(player);
	        final Guild region = GuildManager.getGuild(player.getLocation());
	        if (region == null) {
	            return;
	        }
	        if ((g != null) && region != null && perms == null && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	        if (g != null && region != null && perms.getBreak() == 0 && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	    }
	    
	    @EventHandler
	    public void onPlace(final BlockPlaceEvent event) {
	        final Player player = event.getPlayer();
	        Upr perms = UprManager.getUser(player);
	        final Guild g = GuildManager.getGuild(player);
	        final Guild region = GuildManager.getGuild(player.getLocation());
	        if (region == null) {
	            return;
	        }
	        if ((g != null) && region != null && perms == null && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	        if ((g != null) && region != null && perms.getPlace() == 0 && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	    }
	    
	    @EventHandler
	    public void onPlaceTnT(final BlockPlaceEvent event) {
	        final Player player = event.getPlayer();
	        final Guild g = GuildManager.getGuild(player);
	        Upr perms = UprManager.getUser(player);
	        final Guild region = GuildManager.getGuild(player.getLocation());
	        if (region == null) {
	            return;
	        }
	        if ((g != null) && region != null && perms == null && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	        if ((g != null) && region != null && event.getBlock().getType() == Material.TNT && perms.getPlacetnt() == 0 && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	    }
	    
	    @EventHandler
	    public void onChestOpen(final PlayerInteractEvent event) {
	        final Player player = event.getPlayer();
	        final Guild g = GuildManager.getGuild(player);
	        Upr perms = UprManager.getUser(player);
	        final Guild region = GuildManager.getGuild(player.getLocation());
	        if (region == null) {
	            return;
	        }
	        if ((g != null) && region != null && perms == null && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.CHEST && (g != null) && region != null && perms.getOpenchest() == 0 && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	    }
	    
	    @EventHandler
	    public void onBreakBeacon(final BlockBreakEvent event) {
	        final Player player = event.getPlayer();
	        final Guild g = GuildManager.getGuild(player);
	        Upr perms = UprManager.getUser(player);
	        final Guild region = GuildManager.getGuild(player.getLocation());
	        if (region == null) {
	            return;
	        }
	        if ((g != null) && region != null && perms == null && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	        if ((g != null) && region != null && event.getBlock().getType() == Material.BEACON && perms.getBreakbeacon() == 0 && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	    }
	    
	    @EventHandler
	    public void onBucketFill(final PlayerBucketFillEvent event) {
	        final Player player = event.getPlayer();
	        final Guild g = GuildManager.getGuild(player);
	        Upr perms = UprManager.getUser(player);
	        final Guild region = GuildManager.getGuild(player.getLocation());
	        if (region == null) {
	            return;
	        }
	        if ((g != null) && region != null && perms == null && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	        if ((g != null) && region != null && perms.getWater() == 0 && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	    }
	    
	    @EventHandler
	    public void onBucketEmpty(final PlayerBucketEmptyEvent event) {
	        final Player player = event.getPlayer();
	        Upr perms = UprManager.getUser(player);
	        final Guild g = GuildManager.getGuild(player);
	        final Guild region = GuildManager.getGuild(player.getLocation());
	        if (region == null) {
	            return;
	        }
	        if ((g != null) && region != null && perms == null && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	        if ((g != null) && region != null && perms.getWater() == 0 && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	    }
	    
	    @EventHandler
	    public void onPlayerCommandPreprocessEvent(final PlayerCommandPreprocessEvent event) {
	        final Player player = event.getPlayer();
	        final Guild g = GuildManager.getGuild(player);
	        Upr perms = UprManager.getUser(player);
	        final String command = event.getMessage().toLowerCase().split(" ")[0];
	        final Guild region = GuildManager.getGuild(player.getLocation());
	        if (region == null) {
	            return;
	        }
	        if ((g != null) && region != null && perms == null && !g.isLeader(player.getName())) {
	            event.setCancelled(true);
	            ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	        }
	        if ((g != null) && region != null) {
	            for (final String string : Config.BLOCKED_CMD_PERMS) {
	                if (command.equalsIgnoreCase("/" + string) && perms.getTeleportplayers() == 0 && !g.isLeader(player.getName())) {
	                    event.setCancelled(true);
	                    ChatUtil.sendMessage(player, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
	                }
	            }            
	        }
	    }
	}
