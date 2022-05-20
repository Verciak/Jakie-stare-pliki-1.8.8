package pl.virtual.api.listeners;

import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.*;
import org.bukkit.material.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.RandomUtil;

import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.event.player.*;
import org.bukkit.event.inventory.*;
import java.util.*;
import org.bukkit.command.*;

public class GrupoweListener implements Listener {
	
    public static boolean isInSpawn(final Location loc) {
        return loc.getBlockX() <= Config.REGION_SPAWN && loc.getBlockX() >= -Config.REGION_SPAWN - 1 && loc.getBlockZ() <= Config.REGION_SPAWN && loc.getBlockZ() >= -Config.REGION_SPAWN - 1;
    }
    
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamage(final EntityDamageEvent event) {
        final Entity en = event.getEntity();
        if (en instanceof Player) {
            final Player player = (Player)en;
            final User u = UserManager.getUser(player.getName());
            if (u != null && u.isTp()) {
                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onCommand(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        final String pcmd = e.getMessage();
        final User u = UserManager.getUser(p.getName());
        if (u != null && u.isTp()) {
            for (final String cmd : Config.BLOCKED_CMD_INCOMBAT) {
                if (pcmd.toLowerCase().contains("/" + cmd)) {
                    e.setCancelled(true);
                    ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie mozesz uzywac tej komendy po przeteleportowaniu");
                    return;
                }
            }
        }
    }
    
		@EventHandler
		public void PlayerItemConsume(final PlayerItemConsumeEvent e) {
			final Player p = e.getPlayer();
			final User u = UserManager.getUser(p);
			final ItemStack is = e.getItem();
			if (!is.getType().equals((Object) Material.GOLDEN_APPLE)) {
				return;
			}
			if (is.getDurability() == 1) {
				p.removePotionEffect(PotionEffectType.ABSORPTION);
				p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2410, 1));
				p.removePotionEffect(PotionEffectType.REGENERATION);
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 310, 5));
				u.addKoxeat(1);
				u.save();
			} else{
				p.removePotionEffect(PotionEffectType.REGENERATION);
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 110, 2));
				p.removePotionEffect(PotionEffectType.ABSORPTION);
				p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2410, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 120, 0));
				u.addRefileat(1);
				u.save();}
			}

	@EventHandler
	public void onRandomTp(final PlayerInteractEvent event) {
		if (!event.hasBlock()) {
			return;
		}
		final Block clicked = event.getClickedBlock();
		if (isInSpawn(event.getPlayer().getLocation()) && clicked.getType() == Material.STONE_BUTTON) {
			final Button btn = (Button) clicked.getState().getData();
			final Block base = clicked.getRelative(btn.getAttachedFace());
			if (base.getType() != Material.JUKEBOX) {
				return;
			}
			randomTp(event.getPlayer());
		}
	}

	public static boolean randomTp(final Player player) {
		final int x = RandomUtil.getRandInt(-Config.BORDER_WORLD_RADIUS+ 5, Config.BORDER_WORLD_RADIUS - 5);
		final int z = RandomUtil.getRandInt(-Config.BORDER_WORLD_RADIUS+ 5, Config.BORDER_WORLD_RADIUS - 5);
		final double y = player.getWorld().getHighestBlockYAt(x, z) + 1.5f;
		final Location loc = new Location(player.getWorld(), (double) x, y, (double) z);
		final Biome biome = loc.getBlock().getBiome();
		if (biome == Biome.OCEAN || biome == Biome.DEEP_OCEAN || GuildManager.getGuild(loc) != null) {
			return randomTp(player);
		}
		player.teleport(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
		ChatUtil.giveItems(player, new ItemStack(Material.COOKED_BEEF, 32));
		player.updateInventory();
		return true;
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
		if (e.getInventory().getType().equals((Object) InventoryType.WORKBENCH) && e.getSlotType().toString().equalsIgnoreCase("RESULT") && e.getCurrentItem().getType().name().equalsIgnoreCase("JUKEBOX")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onCraft1(final CraftItemEvent e) {
		if (e.getInventory().getType().equals((Object) InventoryType.WORKBENCH) && e.getSlotType().toString().equalsIgnoreCase("RESULT") && e.getCurrentItem().getType().name().equalsIgnoreCase("BOAT")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInteract(final PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.OAK_BUTTON) {
			final Location block = e.getClickedBlock().getLocation().add(1.0, 0.0, 0.0);
			final Location block2 = e.getClickedBlock().getLocation().add(-1.0, 0.0, 0.0);
			final Location block3 = e.getClickedBlock().getLocation().add(0.0, 0.0, 1.0);
			final Location block4 = e.getClickedBlock().getLocation().add(0.0, 0.0, -1.0);
			if (isInSpawn(e.getPlayer().getLocation()) && block.getBlock().getType() == Material.JUKEBOX || block2.getBlock().getType() == Material.JUKEBOX || block3.getBlock().getType() == Material.JUKEBOX || block4.getBlock().getType() == Material.JUKEBOX) {
				final Random rand = new Random();
				final int x = RandomUtil.getRandInt(-1500, 1500);
				final int z = RandomUtil.getRandInt(-1500, 1500);
				for (final Player players : this.getPlayersInRadius(e.getClickedBlock().getLocation(), 3)) {
					final Location loc = new Location(e.getPlayer().getWorld(),(double) x, (double) e.getPlayer().getWorld().getHighestBlockYAt(x, z), (double) z);
					if (GuildManager.getGuild(loc) != null) {
						return;
					}
					final User u = UserManager.getUser(players);
					e.getPlayer().teleport(loc);
					final Location ploc = new Location(e.getPlayer().getWorld(), e.getPlayer().getLocation().getX(), e.getPlayer().getLocation().getY(), e.getPlayer().getLocation().getZ());
					ploc.setY(e.getPlayer().getLocation().getY() + 5.0);
					e.getPlayer().teleport(ploc);
					players.teleport(e.getPlayer().getLocation());
					players.removePotionEffect(PotionEffectType.REGENERATION);
					players.removePotionEffect(PotionEffectType.SPEED);
					players.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
					players.removePotionEffect(PotionEffectType.INVISIBILITY);
					players.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
					players.removePotionEffect(PotionEffectType.ABSORPTION);
					players.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					players.updateInventory();
					u.setTp(true);
			        Bukkit.getServer().getScheduler().runTaskLaterAsynchronously((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
			            @Override
			            public void run() {
			            	u.setTp(false);
			            }
			        }, 60L);
			    }
			}
		}
	}

	@EventHandler
	public void onInteract1(final PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.OAK_BUTTON) {
			final Location block = e.getClickedBlock().getLocation().add(1.0, 0.0, 0.0);
			final Location block2 = e.getClickedBlock().getLocation().add(-1.0, 0.0, 0.0);
			final Location block3 = e.getClickedBlock().getLocation().add(0.0, 0.0, 1.0);
			final Location block4 = e.getClickedBlock().getLocation().add(0.0, 0.0, -1.0);
			final int x = RandomUtil.getRandInt(-Config.BORDER_WORLD_RADIUS + 5, Config.BORDER_WORLD_RADIUS - 5);
			final int z = RandomUtil.getRandInt(-Config.BORDER_WORLD_RADIUS + 5, Config.BORDER_WORLD_RADIUS - 5);
			final Location loc = new Location(e.getPlayer().getWorld(),(double) x, (double) e.getPlayer().getWorld().getHighestBlockYAt(x, z), (double) z);
			final Location ploc = new Location(e.getPlayer().getWorld(), loc.getX(), loc.getY(), loc.getZ());
			if (GuildManager.getGuild(loc) != null) {
				return;
			}
			if (isInSpawn(e.getPlayer().getLocation()) && block.getBlock().getType() == Material.NOTE_BLOCK || block2.getBlock().getType() == Material.NOTE_BLOCK || block3.getBlock().getType() == Material.NOTE_BLOCK || block4.getBlock().getType() == Material.NOTE_BLOCK) {
				if(this.getPlayersInRadius(e.getClickedBlock().getLocation(), 4).size() < 2) {
					ChatUtil.sendTitle(e.getPlayer(), "", "&cPoczekaj na innych graczy aby sie teleportowac");
					return;
				}
				Player p1 = this.getPlayersInRadius(e.getClickedBlock().getLocation(), 4).get(0).getPlayer();
				Player p2 = this.getPlayersInRadius(e.getClickedBlock().getLocation(), 4).get(1).getPlayer();
				final User u = UserManager.getUser(p1);
				final User u1 = UserManager.getUser(p2);
				p1.teleport(ploc);
				p1.removePotionEffect(PotionEffectType.REGENERATION);
				p1.removePotionEffect(PotionEffectType.SPEED);
				p1.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
				p1.removePotionEffect(PotionEffectType.INVISIBILITY);
				p1.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
				p1.removePotionEffect(PotionEffectType.ABSORPTION);
				p1.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
				p1.updateInventory();
				p2.teleport(ploc);
				p2.removePotionEffect(PotionEffectType.REGENERATION);
				p2.removePotionEffect(PotionEffectType.SPEED);
				p2.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
				p2.removePotionEffect(PotionEffectType.INVISIBILITY);
				p2.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
				p2.removePotionEffect(PotionEffectType.ABSORPTION);
				p2.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
				p2.updateInventory();
				u.setTp(true);
				u1.setTp(true);
		        Bukkit.getServer().getScheduler().runTaskLaterAsynchronously((Plugin)ServerPlugin.getPlugin(), (Runnable)new Runnable() {
		            @Override
		            public void run() {
						u.setTp(false);
						u1.setTp(false);
		            }
		        }, 60L);
		    }
		}
	}
}