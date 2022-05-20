package pl.virtual.api.listeners;

import org.bukkit.event.block.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import pl.virtual.api.ServerPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.*;
import org.bukkit.event.*;

public class DebilCrashListener implements Listener
{
    public static List<String> redstone;
    public static List<String> c;
    
    static {
    	DebilCrashListener.redstone = new ArrayList<String>();
    	DebilCrashListener.c = new ArrayList<String>();
    }
    
    @EventHandler
    public void onLever(final PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.LEVER) {
            if (!DebilCrashListener.c.contains(e.getPlayer().getName())) {
            	DebilCrashListener.c.add(e.getPlayer().getName());
                Bukkit.getScheduler().runTaskLater((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
                    @Override
                    public void run() {
                    	DebilCrashListener.c.remove(e.getPlayer().getName());
                    }
                }, 20L);
                return;
            }
            e.setCancelled(true);
            if (Math.random() * 400.0 <= 9.0) {
            }
        }
    }
}