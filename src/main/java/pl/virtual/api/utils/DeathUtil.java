// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.utils;

import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.Sound;

import pl.virtual.api.data.base.Combat;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.GuildManager;

import org.bukkit.entity.Player;

public class DeathUtil
{
    public static String deathsMessage(final int plus, final int minus, final Player player, final Player killer) {
        final Guild u = GuildManager.getGuild(player);
        final Guild k = GuildManager.getGuild(killer);
        final String m = "" + ((u == null) ? "" : ("&8[&c" + u.getTag() + "&8] ")) + "&c" + player.getName() + " &8(&4" + ((minus >= 0) ? ("-" + minus) : minus) + "&8)"  + " &7zostal zajeb#ny przez " + ((k == null) ? "" : ("&8[&c" + k.getTag() + "&8] ")) + "&c" + killer.getName() + " &8(&2" + ((plus >= 0) ? ("+" + plus) : plus) + "&8)";
        return ChatUtil.fixColor(m);
    }
    
    public static String asystaMessage(final int plus, final Player player) {
        final Guild g = GuildManager.getGuild(player);
        final String m = "&7Asystowal: " + ((g == null) ? "" : ("&8[&c" + g.getTag() + "&8] ")) + "&c" + player.getName() + " &8(&7 " + ((plus >= 0) ? ("+" + plus) : plus) + "&8)";
        return ChatUtil.fixColor(m);
    }
    
    public static void strike(final Location l) {
        final World world = l.getWorld();
        world.strikeLightningEffect(l);
        world.setThundering(false);
        world.setStorm(false);
        world.setWeatherDuration(1000000);
    }
    
    public static void remove(final Combat combat) {
        if (combat == null) {
            return;
        }
        combat.setLastAttactTime(0L);
        combat.setLastAsystTime(0L);
        combat.setLastAsystPlayer(null);
        combat.setLastAttactkPlayer(null);
    }
    
    public static boolean isLastKill(final User u, final Player p) {
        return u.getLastKillTime() > System.currentTimeMillis() && u.getLastKill().equalsIgnoreCase(p.getName());
    }
    
    public static boolean killMulti(final Player p, final Player k) {
        return false;
    }
    
    public static boolean isAsyst(final Combat c) {
        return c.getLastAsystPlayer() != null && c.getLastAsystTime() > System.currentTimeMillis();
    }
}
