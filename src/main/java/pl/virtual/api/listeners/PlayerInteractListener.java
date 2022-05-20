// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import java.util.Random;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.CraftItemEvent;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.event.block.Action;

import org.bukkit.Sound;
import org.bukkit.Effect;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.Combat;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.CombatManager;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.PaczuszkaUtil;
import pl.virtual.api.utils.PolishItemNames;
import pl.virtual.api.utils.RandomUtil;

import org.bukkit.command.CommandSender;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.Material;

import java.util.UUID;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class PlayerInteractListener implements Listener
{
    public static final HashMap<UUID, String> chuj;
    
    static {
        chuj = new HashMap<UUID, String>();
        }
    @EventHandler(priority = EventPriority.MONITOR)
    public void interact(final PlayerInteractEvent e) {
        if (e.isCancelled()) {
            return;
        }
        final Guild g = GuildManager.getGuild(e.getClickedBlock().getLocation());
        if (g != null && e.getClickedBlock().getType() == Material.SPONGE) {
            e.setCancelled(true);
            return;
        }
        if (g != null && e.getPlayer().getItemInHand().getType() == Material.LEGACY_BOAT) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onJajo(final PlayerInteractEvent event) {
        final ItemStack s = event.getItem();
        final Player p = event.getPlayer();
        final Combat combat = CombatManager.getCombat(p);
        if (!Config.ENABLE_DIAMOND) {
            return;
        }
        if (s == null) {
            return;
        }
        if (s.getType() != Material.ENDER_CHEST || !s.getItemMeta().equals(PaczuszkaUtil.drop.build().getItemMeta())) {
            return;
        }
        if (!p.hasPermission("core.cmd.admin") && combat.hasFight()) {
            ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie mozesz otwierac case podcas walki");
            event.setCancelled(true);
            return;
        }
        if (!p.hasPermission("core.cmd.admin") && !Config.ENABLE_CASE) {
            ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7DarkCase sa tymczasowo wylaczone");
            event.setCancelled(true);
            return;
        }
        event.setCancelled(true);
        final ItemStack is = PaczuszkaUtil.drops.get(RandomUtil.getRandInt(0, PaczuszkaUtil.drops.size() - 1));
        ChatUtil.giveItems(p, is);
        event.setCancelled(true);
        p.getInventory().removeItem(new ItemStack[] { PaczuszkaUtil.drop.build() });
        p.updateInventory();
        p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
        p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 20.0f, 20.0f);
        final User user = UserManager.getUser(p);
        user.addCoins(25);
        user.addJajopen(1);
        user.save();
        ChatUtil.sendMessage((CommandSender)p, "&8[&4&lCASE&8] &7Otworzyles &8&lDarkCase &7(&f" + PolishItemNames.getPolishName(is.getType(), is.getData().getData()) + ((is.getAmount() <= 1) ? "" : ("&8 &fx" + is.getAmount())) + "&7)");
    }
    
    @EventHandler
    public void onJajoIron(final PlayerInteractEvent event) {
        final ItemStack s = event.getItem();
        final Player p = event.getPlayer();
        final Combat combat = CombatManager.getCombat(p);
        if (Config.ENABLE_DIAMOND) {
            return;
        }
        if (s == null) {
            return;
        }
        if (s.getType() != Material.ENDER_CHEST || !s.getItemMeta().equals(PaczuszkaUtil.dropiron.build().getItemMeta())) {
            return;
        }
        if (!p.hasPermission("core.cmd.admin") && combat.hasFight()) {
            ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie mozesz otwierac case podcas walki");
            event.setCancelled(true);
            return;
        }
        if (!p.hasPermission("core.cmd.admin") && !Config.ENABLE_CASE) {
            ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7DarkCase sa tymczasowo wylaczone");
            event.setCancelled(true);
            return;
        }
        event.setCancelled(true);
        final ItemStack is = PaczuszkaUtil.dropsiron.get(RandomUtil.getRandInt(0, PaczuszkaUtil.dropsiron.size() - 1));
        ChatUtil.giveItems(p, is);
        event.setCancelled(true);
        p.getInventory().removeItem(new ItemStack[] { PaczuszkaUtil.dropiron.build() });
        p.updateInventory();
        p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
        p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 20.0f, 20.0f);
        final User user = UserManager.getUser(p);
        user.addCoins(25);
        user.addJajopen(1);
        user.save();
        ChatUtil.sendMessage((CommandSender)p, "&8[&4&lCASE&8] &7Otworzyles &8&lDarkCase &7(&f" + PolishItemNames.getPolishName(is.getType(), is.getData().getData()) + ((is.getAmount() <= 1) ? "" : ("&8 &fx" + is.getAmount())) + "&7)");
    }
    
    @EventHandler
    public void onPerla(final PlayerInteractEvent event) {
        final ItemStack s = event.getItem();
        final Player p = event.getPlayer();
        final User u = UserManager.getUser(p);
        if (s == null) {
            return;
        }
        if (event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK) && s.getType() == Material.ENDER_PEARL) {
            u.addPerlycyk(1);
            u.save();
        }
        if (event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) && s.getType() == Material.ENDER_PEARL) {
            u.addPerlycyk(1);
            u.save();
        }
    }
    
    @EventHandler
    public void onPickup(final PlayerPickupItemEvent e) {
        final Player p = e.getPlayer();
        if (UserManager.isVanish(p)) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onVanish(final PlayerDropItemEvent e) {
        final Player p = e.getPlayer();
        final User u = UserManager.getUser(p);
        if (UserManager.isVanish(p)) {
            e.setCancelled(true);
        }
    }
    
    public List<Player> getPlayersInRadius(final Location location, final int size) {
        final List<Player> players = new ArrayList<Player>();
        for (final Player p : location.getWorld().getPlayers()) {
            if (location.distance(p.getLocation()) <= size) {
                players.add(p);
            }
        }
        return players;
    }
    
    @EventHandler
    public void onCraft(final CraftItemEvent e) {
        if (e.getInventory().getType().equals((Object)InventoryType.WORKBENCH) && e.getSlotType().toString().equalsIgnoreCase("RESULT") && e.getCurrentItem().getType().name().equalsIgnoreCase("JUKEBOX")) {
            e.setCancelled(true);
        }
        if (e.getInventory().getType().equals((Object)InventoryType.WORKBENCH) && e.getSlotType().toString().equalsIgnoreCase("RESULT") && e.getCurrentItem().getType().name().equalsIgnoreCase("NOTE_BLOCK")) {
            e.setCancelled(true);
        }
    }
    
    public static int randInt() {
        final Random r = new Random();
        return r.nextInt(14) + 1;
    }
}
