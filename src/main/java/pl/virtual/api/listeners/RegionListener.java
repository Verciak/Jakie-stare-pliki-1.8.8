package pl.virtual.api.listeners;

import java.util.*;
import org.bukkit.block.*;
import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.plugin.*;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.event.hanging.*;

public class RegionListener implements Listener
{
    final Random rand;
    
    public RegionListener() {
        this.rand = new Random();
    }
    
    @EventHandler
    public void onPearl(final PlayerTeleportEvent e) {
        if (!e.getCause().equals((Object)PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
            return;
        }
        if (!isInSpawn(e.getFrom()) && !isInSpawn(e.getTo())) {
            return;
        }
        e.setCancelled(true);
        e.setTo(e.getFrom());
    }
    
    @EventHandler
    public void onPiston(final BlockPistonExtendEvent e) {
        if (!isInNoPiston(e.getBlock().getLocation())) {
            return;
        }
        e.setCancelled(true);
    }
    
    @EventHandler
    public void onPiston2(final BlockPistonRetractEvent e) {
        if (!isInNoPiston(e.getBlock().getLocation())) {
            return;
        }
        e.setCancelled(true);
    }
    
    @EventHandler
    public void onDropItem(final PlayerDropItemEvent e) {
        if (e.getPlayer().hasPermission("core.region.drop")) {
            return;
        }
        if (isInSpawn(e.getPlayer().getLocation())) {
            e.setCancelled(true);
        }
    }
    
//    @EventHandler
//    public void onTeleport(final PlayerTeleportEvent e) {
//        if (e.getPlayer().hasPermission("core.region.build")) {
//            return;
//        }
//        if (!isInNoBuild(e.getFrom())) {
//            return;
//        }
//    }
    
    @EventHandler
    public void onHit(final PlayerFishEvent e) {
        final Player player = e.getPlayer();
        if (e.getState() == PlayerFishEvent.State.FISHING && isInSpawn(player.getLocation())) {
            e.setCancelled(true);
            return;
        }
        if (e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
            final Entity caught = e.getCaught();
            if (isInSpawn(caught.getLocation())) {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        if (e.getPlayer().hasPermission("core.region.build")) {
            return;
        }
        final Player player = e.getPlayer();
        final Location loc = player.getEyeLocation();
        if (!isInNoBuild(loc)) {
            return;
        }
        if (player.getItemInHand() != null && (player.getItemInHand().getType().equals((Object)Material.OAK_BOAT) || player.getItemInHand().getType().equals((Object)Material.PAINTING))) {
            e.setCancelled(true);
        }
        if (e.getClickedBlock() != null && e.getClickedBlock().getType().equals((Object)Material.ITEM_FRAME)) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onFill(final PlayerBucketFillEvent e) {
        if (e.getPlayer().hasPermission("core.region.build")) {
            return;
        }
        if (!isInNoBuild(e.getBlockClicked().getLocation())) {
            return;
        }
        e.setCancelled(true);
        sendMessage((CommandSender)e.getPlayer());
    }
    
    @EventHandler
    public void onEmpty(final PlayerBucketEmptyEvent e) {
        if (e.getPlayer().hasPermission("core.region.build")) {
            return;
        }
        if (!isInNoBuild(e.getBlockClicked().getLocation())) {
            return;
        }
        e.setCancelled(true);
        sendMessage((CommandSender)e.getPlayer());
    }
    
    @EventHandler
    public void onPlace(final BlockPlaceEvent e) {
        if (e.getPlayer().hasPermission("core.region.build")) {
            return;
        }
        if (!isInNoBuild(e.getBlockPlaced().getLocation())) {
            return;
        }
        e.setCancelled(true);
        sendMessage((CommandSender)e.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(final BlockBreakEvent e) {
        final Block block = e.getBlock();
        if (e.getPlayer().hasPermission("core.region.build")) {
            return;
        }
        if (!isInNoBuild(block.getLocation())) {
            return;
        }
        if (block.getType().equals((Object)Material.STONE)) {
            final Block blockUnder = block.getLocation().subtract(0.0, 1.0, 0.0).getBlock();
            if (blockUnder.getType().equals((Object)Material.END_STONE)) {
                return;
            }
        }
        e.setCancelled(true);
        sendMessage((CommandSender)e.getPlayer());
    }
    
    @EventHandler
    public void onInteractEntity(final PlayerInteractEntityEvent e) {
        if (e.getPlayer().hasPermission("core.region.build")) {
            return;
        }
        final Entity entity = e.getRightClicked();
        if (!isInNoBuild(entity.getLocation())) {
            return;
        }
        if (entity.getType().equals((Object)EntityType.ITEM_FRAME)) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onBlockIgnite(final BlockIgniteEvent event) {
        if (!isInNoBuild(event.getBlock().getLocation())) {
            return;
        }
        final BlockIgniteEvent.IgniteCause ic = event.getCause();
        if (ic.equals((Object)BlockIgniteEvent.IgniteCause.SPREAD) || ic.equals((Object)BlockIgniteEvent.IgniteCause.LAVA)) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onExplode(final EntityExplodeEvent e) {
        if (isInNoBuild(e.getLocation())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onDamageByEntity(final EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (isInSpawn(e.getEntity().getLocation())) {
            e.setCancelled(true);
        }
        if (e.getDamager() instanceof Player) {
            final Player player = (Player)e.getDamager();
            if (isInSpawn(player.getLocation())) {
                sendMessageDamage((CommandSender)player);
                e.setCancelled(true);
            }
        }
        else if (e.getDamager() instanceof Projectile) {
            final Projectile projectile = (Projectile)e.getDamager();
            if (isInSpawn(e.getEntity().getLocation())) {
                e.setCancelled(true);
                return;
            }
            if (projectile.getShooter() instanceof Player) {
                final Player player2 = (Player)projectile.getShooter();
                if (isInSpawn(player2.getLocation())) {
                    sendMessageDamage((CommandSender)player2);
                    e.setCancelled(true);
                }
            }
        }
    }
    
    @EventHandler
    public void onDamage(final EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (isInSpawn(e.getEntity().getLocation())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onDamageGolem(final EntityDamageEvent e) {
        if (!e.getEntityType().equals((Object)EntityType.IRON_GOLEM)) {
            return;
        }
        final Player p = (Player)((EntityDamageByEntityEvent) e).getDamager();
        if (!p.hasPermission("core.cmd.golem") && isInSpawn(e.getEntity().getLocation())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onDamageVillager(final EntityDamageEvent e) {
        if (!e.getEntityType().equals((Object)EntityType.VILLAGER)) {
            return;
        }
        final Player p = (Player)((EntityDamageByEntityEvent) e).getDamager();
        if (!p.hasPermission("core.cmd.villager") && isInSpawn(e.getEntity().getLocation())) {
            e.setCancelled(true);
        }
    }
    
    
    @EventHandler
    public void onFoodLevel(final FoodLevelChangeEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (!isInSpawn(e.getEntity().getLocation())) {
            return;
        }
        final Player player = (Player)e.getEntity();
        player.setFoodLevel(20);
        e.setCancelled(true);
    }
    
    @EventHandler
    public void onSpawnWorld(final EntitySpawnEvent e) {
        if (isInNoBuild(e.getLocation())) {
            return;
        }
    	if (e.getEntityType().equals((Object)EntityType.LIGHTNING)) {
            return;
        }
        if (e.getEntityType().equals((Object)EntityType.COW) || e.getEntityType().equals((Object)EntityType.SHEEP) || e.getEntityType().equals((Object)EntityType.PIG) || e.getEntityType().equals((Object)EntityType.CHICKEN)) {
            return;
        }
        if (e.getEntityType().equals((Object)EntityType.IRON_GOLEM)) {
        	e.setCancelled(true);
            return;
        }
        if (e.getEntityType().equals((Object)EntityType.VILLAGER)) {
        	e.setCancelled(true);
            return;
        }
        if (e.getEntityType().equals((Object)EntityType.SKELETON)) {
            final Skeleton skeleton = (Skeleton)e.getEntity();
            if (skeleton.getSkeletonType().equals((Object)Skeleton.SkeletonType.WITHER)) {
                e.setCancelled(true);
                return;
            }
        }
        if (e.getEntityType().equals((Object)EntityType.WITHER) || e.getEntityType().equals((Object)EntityType.WITHER_SKULL)) {
            e.setCancelled(true);
            return;
        }
        if (e.getEntityType().equals((Object)EntityType.DROPPED_ITEM)) {
            return;
        }
        if (isInNoWorld(e.getLocation())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onSpawn(final EntitySpawnEvent e) {
    	if (e.getEntityType().equals((Object)EntityType.LIGHTNING)) {
            return;
        }
        if (isInNoBuild(e.getLocation()) && e.getEntityType().equals((Object)EntityType.COW) || e.getEntityType().equals((Object)EntityType.HORSE) || e.getEntityType().equals((Object)EntityType.SHEEP) || e.getEntityType().equals((Object)EntityType.PIG) || e.getEntityType().equals((Object)EntityType.CHICKEN)) {
        	e.setCancelled(true);
        	return;
        }
        if (e.getEntityType().equals((Object)EntityType.VILLAGER)) {
            return;
        }
        if (e.getEntityType().equals((Object)EntityType.IRON_GOLEM)) {
            return;
        }
        if (e.getEntityType().equals((Object)EntityType.SKELETON)) {
            final Skeleton skeleton = (Skeleton)e.getEntity();
            if (skeleton.getSkeletonType().equals((Object)Skeleton.SkeletonType.WITHER)) {
                e.setCancelled(true);
                return;
            }
        }
        if (e.getEntityType().equals((Object)EntityType.WITHER) || e.getEntityType().equals((Object)EntityType.WITHER_SKULL)) {
            e.setCancelled(true);
            return;
        }
        if (e.getEntityType().equals((Object)EntityType.DROPPED_ITEM)) {
            return;
        }
        if (isInNoBuild(e.getLocation())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onPotion(final PotionSplashEvent e) {
        if (isInSpawn(e.getPotion().getLocation())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onItemFrame(final EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof ItemFrame)) {
            return;
        }
        Player damager = null;
        if (e.getDamager() instanceof Projectile) {
            final Projectile projectile = (Projectile)e.getDamager();
            if (projectile.getShooter() instanceof Player) {
                damager = (Player)projectile.getShooter();
            }
        }
        else if (e.getDamager() instanceof Player) {
            damager = (Player)e.getDamager();
        }
        if (damager == null) {
            return;
        }
        if (damager.hasPermission("core.region.build")) {
            return;
        }
        if (isInNoBuild(e.getEntity().getLocation())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onHangingPlace(final HangingPlaceEvent e) {
        if (e.getPlayer().hasPermission("core.region.build")) {
            return;
        }
        if (!isInNoBuild(e.getBlock().getLocation())) {
            return;
        }
        if (e.getEntity() instanceof ItemFrame) {
            e.setCancelled(true);
        }
    }
    
    public static boolean sendMessage(final CommandSender sender) {
        sender.sendMessage(ChatUtil.fixColor(Config.REGION_MASSAGE));
        return true;
    }
    
    public static boolean sendMessageDamage(final CommandSender sender) {
        sender.sendMessage(ChatUtil.fixColor(Config.REGION_DAMAGE));
        return true;
    }
    
    public static boolean isInSpawn(final Location loc) {
        return loc.getBlockX() <= Config.REGION_SPAWN && loc.getBlockX() >= -Config.REGION_SPAWN - 1 && loc.getBlockZ() <= Config.REGION_SPAWN && loc.getBlockZ() >= -Config.REGION_SPAWN - 1;
    }
    
    public static boolean isInNoPiston(final Location loc) {
        return loc.getBlockX() <= Config.REGION_PVP + 13 && loc.getBlockX() >= -Config.REGION_PVP - 14 && loc.getBlockZ() <= Config.REGION_PVP + 13 && loc.getBlockZ() >= -Config.REGION_PVP - 14;
    }
    
    public static boolean isInNoWorld(final Location loc) {
        return loc.getBlockX() <= Config.BORDER_WORLD_RADIUS + 10 && loc.getBlockX() >= -Config.BORDER_WORLD_RADIUS - 10 && loc.getBlockZ() <= Config.BORDER_WORLD_RADIUS + 10 && loc.getBlockZ() >= -Config.BORDER_WORLD_RADIUS - 10;
    }
    
    public static boolean isInNoBuild(final Location loc) {
        return loc.getBlockX() <= Config.REGION_PVP && loc.getBlockX() >= -Config.REGION_PVP - 1 && loc.getBlockZ() <= Config.REGION_PVP && loc.getBlockZ() >= -Config.REGION_PVP - 1;
    }
    
    public static int getNoBuild() {
        return Config.REGION_PVP;
    }
}
