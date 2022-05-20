// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import java.util.Calendar;

import org.bukkit.event.entity.EntityExplodeEvent;

import pl.virtual.api.API.Config;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.Listener;

public class TntEvent implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace4(final BlockPlaceEvent e) {
        if (e.getBlockPlaced().getType() == Material.TNT) {
            final Block b = e.getBlockPlaced();
            final Player p = e.getPlayer();
            if (b.getLocation().getBlockY() > 50) {
                ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7TnT mozna stawiac od &c50 &7poziomu Y");
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onExplode(final EntityExplodeEvent e) {
        final Calendar c = Calendar.getInstance();
        final int hr = c.get(11);
        if ((hr >= Config.TNT_OD || hr < Config.TNT_DO) && e.getEntity().getType().equals((Object)EntityType.CREEPER)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onExplode11(final EntityExplodeEvent e) {
        final Calendar c = Calendar.getInstance();
        final int hr = c.get(11);
        if ((hr >= Config.TNT_OD || hr < Config.TNT_DO) && e.getEntity().getType().equals((Object)EntityType.PRIMED_TNT)) {
            e.setCancelled(true);
        }
    }    @EventHandler
    public void onExplode1(final EntityExplodeEvent e) {
        final Calendar c = Calendar.getInstance();
        final int hr = c.get(11);
        if ((hr >= Config.TNT_OD || hr < Config.TNT_DO) && e.getEntity().getType().equals((Object)EntityType.MINECART_TNT)) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onTNTPlace(final BlockPlaceEvent e) {
        final Material m = e.getBlockPlaced().getType();
        final Player p = e.getPlayer();
        final Calendar c = Calendar.getInstance();
        final int hr = c.get(11);
        if ((hr >= Config.TNT_OD || hr < Config.TNT_DO) && m == Material.TNT) {
            e.setCancelled(true);
            ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7TnT dziala od &c" + Config.TNT_DO + " &7do &c" + Config.TNT_OD + "");

        }
    }
}
  