
package pl.virtual.api.listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;


import org.bukkit.event.Listener;

public class EntityDamageListener implements Listener
{
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamage(final EntityDamageEvent event) {
        final Entity en = event.getEntity();
        if (en instanceof Player) {
            final Player player = (Player)en;
            final User u = UserManager.getUser(player.getName());
            if (u != null && u.isGod()) {
                event.setCancelled(true);
            }
        }
    }
}
