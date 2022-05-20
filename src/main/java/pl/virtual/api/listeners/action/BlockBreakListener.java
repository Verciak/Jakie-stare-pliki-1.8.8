package pl.virtual.api.listeners.action;

import org.bukkit.event.block.*;
import org.bukkit.scheduler.*;

import org.bukkit.block.*;
import org.bukkit.plugin.*;
import org.bukkit.inventory.*;
import org.bukkit.command.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.*;

import pl.virtual.api.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.*;

public class BlockBreakListener implements Listener
{
    @EventHandler(priority = EventPriority.LOWEST)
    public void BlockBreak(final BlockBreakEvent event) {
        final Player p = event.getPlayer();
        final Block b = event.getBlock();
        final Block under = b.getLocation().subtract(0.0, 1.0, 0.0).getBlock();
        if (event.getBlock().getType() == Material.SPONGE) {
            if (under.getType() == Material.BEDROCK) {
                new BukkitRunnable() {
                    public void run() {
                        b.setType(Material.SPONGE);
                        this.cancel();
                    }
                }.runTaskTimer((Plugin)ServerPlugin.getPlugin(), 2L, 2L);
                new BukkitRunnable() {
                    public void run() {
                        ItemUtil.removeItems(event.getPlayer(), new ItemStack(Material.SPONGE));
                        this.cancel();
                    }
                }.runTaskTimer((Plugin)ServerPlugin.getPlugin(), 1L, 1L);
            }
        }
        else {
            event.setCancelled(cancelAction(event.getPlayer(), event.getBlock().getLocation(), "&9&lERROR: &7Nie mozesz edytowac terenu gildii"));
        }
        if (event.getBlock().getType() == Material.SPONGE && !p.getGameMode().equals((Object)GameMode.SURVIVAL) && under.getType() != Material.BEDROCK) {
            b.setType(Material.AIR);
            ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Gratulacje Zniszczyles &c&lSERCE GILDII");
            return;
        }
        if (event.getBlock().getType() == Material.SPONGE && under.getType() != Material.BEDROCK) {
            b.setType(Material.AIR);
            ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Gratulacje Zniszczyles &c&lSERCE GILDII");
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreak(final BlockBreakEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (e.getBlock().getType().equals((Object)Material.ENDER_CHEST)) {
            e.setCancelled(true);
            e.getBlock().getLocation().getBlock().setType(Material.AIR);
            if (e.getPlayer().getGameMode().equals((Object)GameMode.SURVIVAL)) {
				ChatUtil.giveItems(e.getPlayer(), new ItemStack(Material.ENDER_CHEST, 1));
            }
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreak1(final BlockBreakEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (e.getBlock().getType().equals((Object)Material.END_STONE)) {
            e.setCancelled(true);
            e.getBlock().getLocation().getBlock().setType(Material.AIR);
            if (e.getPlayer().getGameMode().equals((Object)GameMode.SURVIVAL)) {
				ChatUtil.giveItems(e.getPlayer(), new ItemBuilder(Material.END_STONE, 1).setTitle(ChatUtil.fixColor("&a&lStoniarka")).addEnchantment(Enchantment.DURABILITY, 10).build());
            }
        }
    }

    
    public static boolean cancelAction(final Player p, final Location l, final String message) {
        if (p.hasPermission("core.cmd.admin")) {
            return false;
        }
        final Guild g = GuildManager.getGuild(l);
        if (g == null) {
            return false;
        }
        if (g.isMember(p.getName())) {
            return false;
        }
        ChatUtil.sendMessage((CommandSender)p, message);
        return true;
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void BlockBreak2(final BlockBreakEvent event) {
        final Player p = event.getPlayer();
        final Block b = event.getBlock();
        if (event.isCancelled()) {
            return;
        }
        if (b == null) {
            return;
        }
        final Guild g = GuildManager.getGuild(b.getLocation());
        final Guild o = GuildManager.getGuild(p);
        if (b.getType() != Material.SPONGE) {
            return;
        }
        if (g == null) {
            return;
        }
        if (g.isMember(p)) {
            return;
        }
        if (o == null) {
            ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Musisz posiadac gildie aby podbic inna gildie");
            return;
        }
        if (g.getAlly().contains(o.getTag())) {
            ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie mozesz atakowac sojuszy");
            return;
        }
        if (g.getHpLastAttack() > System.currentTimeMillis()) {
            ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gildie mozesz podbic za " + DataUtil.secondsToString(g.getHpLastAttack()));
            return;
        }
        if (g.getLife() <= 3) {
            if (g.getHp() == 1) {
                g.setHpLastAttack(System.currentTimeMillis() + TimeUtil.HOUR.getTime(24));
                g.setLife(g.getLife() - 1);
                g.setHp(1000);
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &8[&c" + g.getTag() + "&8] &7zostala podbita przez &8[&c" + o.getTag() + "&8] &c" + p.getName());
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &7Zostalo jej &c" + g.getLife() + " &7zyc");
            }
            else {
                g.setHp(g.getHp() - 1);
                ChatUtil.sendActionBar(p, ChatUtil.fixColor("&8[&4&lGILDIE&8] &7Podbijanie gildii &c" + g.getTag() + " &8(&7" + Integer.toString(g.getHp()) + "&8/1000 HP&8)"));
            }
            if (g.getLife() < 1) {
                new BukkitRunnable() {
                    public void run() {
                        GuildManager.deleteGuild(g);
                    }
                }.runTask((Plugin)ServerPlugin.getPlugin());
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &8[&c" + g.getTag() + "&8] &7zostala zniszczona przez &8[&c" + o.getTag() + "&8] &c" + p.getName());
            }
        }
    }
}
