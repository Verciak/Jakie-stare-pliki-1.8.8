// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.Location;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.TimeUtil;

import org.bukkit.command.CommandSender;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class PlayerMoveListener implements Listener
{
    private static HashMap<Player, Long> enemysTo;
    private static HashMap<Player, Long> enemysFrom;
    
    static {
        PlayerMoveListener.enemysTo = new HashMap<Player, Long>();
        PlayerMoveListener.enemysFrom = new HashMap<Player, Long>();
    }
    
    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        final Location to = e.getTo();
        final Location from = e.getFrom();
        final int xto = to.getBlockX();
        final int yto = to.getBlockY();
        final int zto = to.getBlockZ();
        final int xfrom = from.getBlockX();
        final int yfrom = from.getBlockY();
        final int zfrom = from.getBlockZ();
        final Guild toGuild = GuildManager.getGuild(to);
        final Guild fromGuild = GuildManager.getGuild(from);
        if(p.getPotionEffect(PotionEffectType.NIGHT_VISION) == null){
            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 99999, 1));
        }
        if ((xto != xfrom || yto != yfrom || zto != zfrom) && fromGuild == null && toGuild != null) {
            if (toGuild.isMember(p)) {
                ChatUtil.sendTitleMessage(p, "&8[&6&lWKROCZYLES&8]", "&8&m------&8[ &a&l" + toGuild.getTag() + " &8]&8&m------", 20, 60, 20);
                ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Wkroczyles na teren swojej gildii &8[&a" + toGuild.getTag() + "&8]");
            }
            else {
            	ChatUtil.sendTitleMessage(p, "&8[&6&lWKROCZYLES&8]", "&8&m------&8[ &4&l" + toGuild.getTag() + " &8]&8&m------", 20, 60, 20);
                ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Wkroczyles na teren wrogiej gildii &8[&c" + toGuild.getTag() + "&8]");
            }
            final Long time = PlayerMoveListener.enemysTo.get(p);
            if (!toGuild.isMember(p) && (time == null || time < System.currentTimeMillis()) && !p.hasPermission("core.cmd.mod")) {
                toGuild.message("&8[&4&lGILDIE&8] &7Intruz &c" + p.getName() + " &7wkroczyl na teren twojej gildii");
                PlayerMoveListener.enemysTo.put(p, System.currentTimeMillis() + TimeUtil.SECOND.getTime(30));
            }
        }
        else if ((xto != xfrom || yto != yfrom || zto != zfrom) && toGuild == null && fromGuild != null) {
            if (fromGuild.isMember(p)) {
                ChatUtil.sendTitleMessage(p, "&8[&6&lOPUSCILES&8]", "&8&m------&8[ &a&l" + fromGuild.getTag() + " &8]&8&m------", 20, 60, 20);
                ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Opusciles teren swojej gildii &8[&a" + fromGuild.getTag() + "&8]");
            }
            else {
            	ChatUtil.sendTitleMessage(p, "&8[&6&lOPUSCILES&8]", "&8&m------&8[ &4&l" + fromGuild.getTag() + " &8]&8&m------", 20, 60, 20);
                ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Opusciles teren wrogiej gildii &8[&c" + fromGuild.getTag() +"&8]");
            }
            final Long time = PlayerMoveListener.enemysFrom.get(p);
            if (!fromGuild.isMember(p) && (time == null || time < System.currentTimeMillis()) && !p.hasPermission("core.cmd.mod")) {
                fromGuild.message("&8[&4&lGILDIE&8] &7Intruz &c" + p.getName() + " &7opuscil teren twojej gildii");
                PlayerMoveListener.enemysFrom.put(p, System.currentTimeMillis() + TimeUtil.SECOND.getTime(30));
            }
        }
    }
}
