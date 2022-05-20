// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;

import pl.virtual.api.data.base.Combat;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.CombatManager;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.TimeUtil;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.Listener;

public class EntityDamageByEntityListener implements Listener
{
    public static Integer counter;
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void EntityDamageByEntity(final EntityDamageByEntityEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        final Player d = ChatUtil.getDamager(e);
        if (d == null) {
            return;
        }
        final Player p = (Player)e.getEntity();
        if (p.equals(d)) {
            return;
        }
        final Combat u = CombatManager.getCombat(p);
        if (u == null) {
            return;
        }
        if (this.is(p, d, e)) {
            return;
        }
        if (!u.hasFight()) {
            ChatUtil.sendMessage((CommandSender)p, "&cZostales zaatakowany nie mozesz sie wylogowac");
        }
        u.setLastAttactTime(System.currentTimeMillis() + TimeUtil.SECOND.getTime(30));
        if (u.getLastAttactkPlayer() != d) {
            u.setLastAsystPlayer(u.getLastAttactkPlayer());
            u.setLastAsystTime(System.currentTimeMillis() + TimeUtil.SECOND.getTime(30));
        }
        u.setLastAttactkPlayer(d);
    }
    
    private boolean is(final Player p, final Player d, final EntityDamageByEntityEvent e) {
        final Guild g = GuildManager.getGuild(p);
        final Guild o = GuildManager.getGuild(d);
        if (g == null || o == null) {
            return false;
        }
        if (g.equals(o)) {
            if (g.isPvp()) {
                e.setDamage(0.0);
            }
            else {
                e.setCancelled(true);
            }
            return true;
        }
        if (g.getAlly().contains(o.getTag())) {
            if (!g.isPvpAlly() || !o.isPvpAlly()) {
                e.setCancelled(true);
            }
            else {
                e.setDamage(0.0);
            }
            return true;
        }
        return false;
    }
}

