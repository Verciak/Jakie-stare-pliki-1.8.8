// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.data.base.Combat;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.CombatManager;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.DataUtil;
import pl.virtual.api.utils.TimeUtil;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.command.CommandSender;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.Listener;

public class BoyListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlace(final BlockPlaceEvent e) {
        final Block b = e.getBlock();
        final Player p = e.getPlayer();
        if (p.getItemInHand() == null) {
            return;
        }
        if (!p.getItemInHand().hasItemMeta()) {
            return;
        }
        final Guild g = GuildManager.getGuild(b.getLocation());
        final Combat combat = CombatManager.getCombat(e.getPlayer());
        if (g != null && g.isMember(e.getPlayer().getName()) && combat != null && combat.hasFight() && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&a&lBoyFarmer"))) {
            ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&9&lERROR: &7Jestes podczas walki nie mozesz postawic farmera");
            e.setCancelled(true);
            return;
        }
        if (g == null && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&a&lBoyFarmer"))) {
            ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&9&lERROR: &7Nie mozesz postawic farmera poza gildia");
            e.setCancelled(true);
            return;
        }
        for (int i = 1; i <= 15; ++i) {
            final Guild g1 = GuildManager.getGuild(e.getBlock().getLocation());
            if (g1 != null && g.getRegion().isInCentrum(e.getBlock().getLocation(), 300, 2, 2) && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&a&lBoyFarmer"))) {
            		e.setCancelled(true);
                    ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&9&lERROR: &7Farmer nie moze trafic na srodek gildii");
            		return;
            	}
            }
        if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&a&lBoyFarmer"))) {
                final Location loc = e.getBlockPlaced().getLocation();
                for (int i = 0; i < 100 && !loc.getBlock().getType().equals((Object) Material.BEDROCK); ++i) {
                    if (loc.getBlock().getType().equals((Object) Material.BEDROCK)) {
                        break;
                    }
                    loc.getBlock().setType(Material.OBSIDIAN);
                    loc.subtract(0.0, 1.0, 0.0);
                }
            }
        }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlace1(final BlockPlaceEvent e) {
        final Block b = e.getBlock();
        final Player p = e.getPlayer();
        if (p.getItemInHand() == null) {
            return;
        }
        if (!p.getItemInHand().hasItemMeta()) {
            return;
        }
        final Guild g = GuildManager.getGuild(b.getLocation());
        final Combat combat = CombatManager.getCombat(e.getPlayer());
        if (g != null && g.isMember(e.getPlayer().getName()) && combat != null && combat.hasFight() && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&a&lSandFarmer"))) {
            ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&9&lERROR: &7Jestes podczas walki nie mozesz postawic farmera");
            e.setCancelled(true);
            return;
        }
        if (g == null && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&a&lSandFarmer"))) {
            ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&9&lERROR: &7Nie mozesz postawic farmera poza gildia");
            e.setCancelled(true);
            return;
        }
        for (int i = 1; i <= 15; ++i) {
            final Guild g1 = GuildManager.getGuild(e.getBlock().getLocation());
            if (g1 != null && g.getRegion().isInCentrum(e.getBlock().getLocation(), 300, 2, 2) && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&a&lSandFarmer"))) {
            		e.setCancelled(true);
                    ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&9&lERROR: &7Farmer nie moze trafic na srodek gildii");
            		return;
            	}
            }
        if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&a&lSandFarmer"))) {
                final Location loc = e.getBlockPlaced().getLocation();
                for (int i = 0; i < 100
                        && !loc.getBlock().getType()
                        .equals((Object) Material.BEDROCK); ++i) {
                    if (loc.getBlock().getType()
                            .equals((Object) Material.BEDROCK)) {
                        break;
                    }
                    loc.getBlock().setType(Material.SAND);
                    loc.subtract(0.0, 1.0, 0.0);
            }
            }
        }

    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlace2(final BlockPlaceEvent e) {
        final Block b = e.getBlock();
        final Player p = e.getPlayer();
        if (p.getItemInHand() == null) {
            return;
        }
        if (!p.getItemInHand().hasItemMeta()) {
            return;
        }
        final Guild g = GuildManager.getGuild(b.getLocation());
        final Combat combat = CombatManager.getCombat(e.getPlayer());
        if (g != null && g.isMember(e.getPlayer().getName()) && combat != null && combat.hasFight() && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&a&lKopaczFosy"))) {
            ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&9&lERROR: &7Jestes podczas walki nie mozesz postawic kopacza");
            e.setCancelled(true);
            return;
        }
        if (g == null && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&a&lKopaczFosy"))) {
            ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&9&lERROR: &7Nie mozesz postawic farmera poza gildia");
            e.setCancelled(true);
            return;
        }
        for (int i = 1; i <= 15; ++i) {
            final Guild g1 = GuildManager.getGuild(e.getBlock().getLocation());
            if (g1 != null && g.getRegion().isInCentrum(e.getBlock().getLocation(), 300, 2, 2) && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&a&lKopaczFosy"))) {
            		e.setCancelled(true);
                    ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&9&lERROR: &7Farmer nie moze trafic na srodek gildii");
            		return;
            	}
            }
        if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&a&lKopaczFosy"))) {
                final Location loc = e.getBlockPlaced().getLocation();
                for (int i = 0; i < 100
                        && !loc.getBlock().getType()
                        .equals((Object) Material.BEDROCK); ++i) {
                    if (loc.getBlock().getType()
                            .equals((Object) Material.BEDROCK)) {
                        break;
                    }
                    loc.getBlock().setType(Material.AIR);
                    loc.subtract(0.0, 1.0, 0.0);
            }
            }
        }
    
    @EventHandler
    public void onBreak(final BlockPlaceEvent e) {
        final Block blok = e.getBlock();
        final Location loc = blok.getLocation();
        final Block b = e.getBlock();
        final Guild g = GuildManager.getGuild(b.getLocation());
        final Combat combat = CombatManager.getCombat(e.getPlayer());
        final Location loc2 = new Location(loc.getWorld(), loc.getX(), loc.getY() + 1.0, loc.getZ());
        if (blok.getType() == Material.END_STONE && loc2.getBlock().getType() == Material.AIR) {
            if (g != null && g.isMember(e.getPlayer().getName()) && combat != null && combat.hasFight()) {
                ChatUtil.sendMessage((CommandSender)e.getPlayer(), " &9&lERROR: &7Jestes podczas walki nie mozesz postawic stoniarki");
                e.setCancelled(true);
                return;
            }
            if (g != null && g.isMember(e.getPlayer().getName()) && g.getLastExplodeTime() + TimeUtil.SECOND.getTime(120) > System.currentTimeMillis()) {
                ChatUtil.sendMessage((CommandSender)e.getPlayer(), "&8[&4&lTNT&8] &7Na terenie gildii wybuchlo tnt &7(&f" + DataUtil.secondsToString(g.getLastExplodeTime()) + "&7)");
                e.setCancelled(true);
                return;
            }
            Bukkit.getScheduler().runTaskLater(ServerPlugin.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    if (loc.getBlock().getType() == Material.END_STONE) {
                        loc2.getBlock().setType(Material.STONE);
                    }
                }
            }, ServerPlugin.getTicks("generate"));
        }
    }


    
    @EventHandler
    public void onBreak(final BlockBreakEvent e) {
        final Block blok = e.getBlock();
        final Location loc = blok.getLocation();
        final Location loc2 = new Location(loc.getWorld(), loc.getX(), loc.getY() - 1.0, loc.getZ());
        if (blok.getType() == Material.STONE && loc2.getBlock().getType() == Material.END_STONE) {
            Bukkit.getScheduler().runTaskLater(ServerPlugin.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    if (loc2.getBlock().getType() == Material.END_STONE) {
                        blok.setType(Material.STONE);
                    }
                }
            }, ServerPlugin.getTicks("regenerate"));
        }
    }
}
