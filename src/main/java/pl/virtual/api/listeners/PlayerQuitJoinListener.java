// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.Location;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.Combat;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.CombatManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.RandomUtil;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

public class PlayerQuitJoinListener implements Listener
{
    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(final PlayerJoinEvent e) {
        e.setJoinMessage((String)null);
        final Player p = e.getPlayer();
        final User u = UserManager.getUser(p);
        if (u == null) {
            UserManager.createrUser(p);
            p.getInventory().clear();
            p.setLevel(0);
            p.getEnderChest().clear();
            p.getInventory().setHelmet((ItemStack)null);
            p.getInventory().setChestplate((ItemStack)null);
            p.getInventory().setLeggings((ItemStack)null);
            p.getInventory().setBoots((ItemStack)null);
            ChatUtil.giveItems(p, new ItemStack(Material.STONE_AXE));
            ChatUtil.giveItems(p, new ItemStack(Material.ENDER_CHEST));
            ChatUtil.giveItems(p, new ItemStack(Material.COOKED_BEEF, 32));
            final int x = RandomUtil.getRandInt(-Config.BORDER_WORLD_RADIUS + 5, Config.BORDER_WORLD_RADIUS - 5);
            final int z = RandomUtil.getRandInt(-Config.BORDER_WORLD_RADIUS + 5, Config.BORDER_WORLD_RADIUS - 5);
            final double y = p.getWorld().getHighestBlockYAt(x, z) + 1.5f;
            final Location loc = new Location(p.getWorld(), (double)x, y, (double)z);
            p.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
            p.updateInventory();
        }
        else {
            u.addJoin(1);
            u.save();
            if (u.isGod() && !p.hasPermission("core.cmd.mod")) {
                u.setGod(false);
            }
        }
        if (p.getWorld().getName().equals("world_nether") && !Config.ENABLE_NETHER) {
            p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            return;
        }
        final Combat combat = CombatManager.getCombat(p);
        if (combat == null) {
            CombatManager.createCombat(p);
        }
        if (p.hasPermission("core.vanish.see")) {
            return;
        }
        for (final Player vanishPlayer : UserManager.getVanished()) {
            p.hidePlayer(vanishPlayer);
        }
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onKick(final PlayerKickEvent e) {
        quitGame(e.getPlayer());
        e.setLeaveMessage((String)null);
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(final PlayerQuitEvent e) {
        quitGame(e.getPlayer());
        e.setQuitMessage((String)null);
    }
    
    public static void quitGame(final Player p) {
        final User user = UserManager.getUser(p);
        user.setTimeLast(System.currentTimeMillis());
        user.save();
        final Combat combat = CombatManager.getCombat(p);
        if (!combat.hasFight()) {
            return;
        }
        p.setHealth(0.0);
        user.addLogouts(1);
    }
}
