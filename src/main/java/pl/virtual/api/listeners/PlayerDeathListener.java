package pl.virtual.api.listeners;

import org.bukkit.plugin.*;
import org.bukkit.scheduler.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.command.*;
import org.bukkit.*;

import org.bukkit.event.*;

import pl.virtual.api.*;
import pl.virtual.api.data.base.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.data.base.user.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.rank.tops.*;
import pl.virtual.api.utils.*;

import org.bukkit.event.entity.*;

public class PlayerDeathListener implements Listener
{
	
    public static ItemStack head;
    
    @EventHandler
    public void onDeaths(final PlayerDeathEvent e) {
        e.setDeathMessage((String)null);
        final Player p = e.getEntity();
        final Combat pC = CombatManager.getCombat(p);
        final User user = UserManager.getUser(p);
        Bukkit.getScheduler().runTask((Plugin)ServerPlugin.getPlugin(), () -> DeathUtil.strike(p.getLocation()));
        new BukkitRunnable() {
            public void run() {
                if (p.isInsideVehicle()) {
                    p.leaveVehicle();
                }
                p.spigot().respawn();
                ChatUtil.giveItems(p, new ItemStack(Material.STONE_AXE));
                ChatUtil.giveItems(p, new ItemStack(Material.ENDER_CHEST));
                ChatUtil.giveItems(p, new ItemStack(Material.COOKED_BEEF, 32));
            }
        }.runTaskLater((Plugin)ServerPlugin.getPlugin(), 5L);
        Player k = p.getKiller();
        if (k == null && pC != null && pC.wasFight()) {
            k = pC.getLastAttactkPlayer();
        }
        if (k != null) {
            final User kUser = UserManager.getUser(k);
            if (kUser == null) {
                return;
            }
            if (p.equals(k)) {
                return;
            }
            if (DeathUtil.isLastKill(kUser, p)) {
                ChatUtil.sendMessage((CommandSender)k, "&9&lERROR: &7Zabiles tego samego gracza w ciagu 10m");
                if (DeathUtil.isAsyst(pC)) {
                    this.assyst(pC, user);
                }
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    final User u = UserManager.getUser(po);
                    if (u.isDeathMessages()) {
                        String msg5 = null;
                        kUser.addCoins(5);
                        final String msg6 = "&c" + user.getName() + " &7Zabiles tego samego gracza w ciagu 10 min ";
                        ChatUtil.sendTitleMessage(k, msg5, msg6, 30, 70, 40);
                        ChatUtil.sendMessage((CommandSender)po, DeathUtil.deathsMessage(0, 0, p, k));
                    }
                }
            }
            else {
                int add = (int)(50.0 + (kUser.getPoints() - ((user != null) ? user.getPoints() : 0)) * -0.25);
                if (add <= 1) {
                    add = 1;
                }
                final int remove = add / 4 * 3;
                if (user != null) {
                    user.removePoints(remove);
                }
                kUser.setLastKillTime(System.currentTimeMillis() + TimeUtil.MINUTE.getTime(10));
                kUser.setLastKill(p.getName());
                kUser.addPoints(add);
                kUser.addKills(1);
                kUser.addCoins(50);             
                k.playSound(k.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0f, 1.0f);
                String msg7 = null;
                final String msg8 = "&c" + user.getName() + " &8[&f+" + add + "&8]";
                PlayerDeathListener.head = new ItemBuilder(Material.SKELETON_SKULL).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &7Glowa gracza &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Z tym przedmiotem zwiazany jest event.")).addLore(ChatUtil.fixColor("&8� &7Na spawnie mozna spotkac golema")).addLore(ChatUtil.fixColor("&8� &7Ktory zbiera glowy graczy.")).addLore(ChatUtil.fixColor("&8� &7Po oddaniu odpowiedniej ilosci glowek")).addLore(ChatUtil.fixColor("&8� &7Caly serwer dostanie nagrode")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("           &f&lAby postawic, kliknij PPM")).addLore(ChatUtil.fixColor("")).build();
                ChatUtil.giveItems(k, PlayerDeathListener.head);
                ChatUtil.sendTitleMessage(k, msg7, msg8, 30, 70, 40);
                for (final Player po2 : Bukkit.getOnlinePlayers()) {
                    final User u2 = UserManager.getUser(po2);
                    if (u2.isDeathMessages()) {
                        ChatUtil.sendMessage((CommandSender)po2, DeathUtil.deathsMessage(add, remove, p, k));
                    }
                }
                if (DeathUtil.isAsyst(pC)) {
                    this.assyst(pC, user);
                }
                final Guild g = GuildManager.getGuild(k);
                if (g != null) {
                    g.addPoints(GuildManager.addPoints(k, p));
                    g.addKills(1);
                }
                final Guild o = GuildManager.getGuild(p);
                if (o != null) {
                    o.removePoints(GuildManager.removePoints(p, k));
                    o.addDeaths(1);
                }
                RankingManager.sortGuildRankings();
            }
        }
        else if (user != null) {
            user.removePoints(5);
        }
        new Backup(p, this.desc(p));
        if (user != null) {
            user.addDeaths(1);
        }
        DeathUtil.remove(pC);
        RankingManager.sortUserRankings();
        DeathManager.sortUserDeaths();
        KillManager.sortUserKills();
        CoinsManager.sortUserCoins();
        AssistManager.sortUserAssists();
    }  
    private void assyst(final Combat pC, final User pU) {
        final User asysta = UserManager.getUser(pC.getLastAsystPlayer());
        int asyst = (int)((64.0 + (((asysta != null) ? asysta.getPoints() : 0) - pU.getPoints()) * -0.25) / 3.0);
        if (asyst <= 1) {
            asyst = 1;
        }
        if (asysta != null) {
            asysta.addAsyst(1);
            asysta.addPoints(asyst);
            asysta.addCoins(25);
            asysta.save();
        }
        for (final Player p : Bukkit.getOnlinePlayers()) {
            final User u = UserManager.getUser(p);
            if (u.isDeathMessages()) {
                ChatUtil.sendMessage((CommandSender)p, DeathUtil.asystaMessage(asyst, pC.getLastAsystPlayer()));
            }
        }
    }
    
    private String desc(final Player p) {
        final EntityDamageEvent e = p.getLastDamageCause();
        String cause = "-";
        if (e == null) {
            cause = "logouth";
        }
        else if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            if (p.getKiller() != null) {
                cause = p.getKiller().getName();
            }
            else {
                cause = "mob";
            }
        }
        else {
            cause = p.getLastDamageCause().getCause().name().toLowerCase();
        }
        return cause;
    }
}
