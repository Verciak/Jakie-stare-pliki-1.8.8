
package pl.virtual.api.utils;

import pl.virtual.api.data.base.Combat;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.CombatManager;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.TimerManager;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class TimerUtil
{
		public static void teleport(final Player p, final Location location, final int delay) {
			final Combat combat = CombatManager.getCombat(p);
			if (combat != null && combat.hasFight() && !p.hasPermission("core.cmd.mod")) {
				ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Teleportacja przerwana");
				p.removePotionEffect(PotionEffectType.SLOW);
				p.removePotionEffect(PotionEffectType.BLINDNESS);
				return;
			}
			final Guild g = GuildManager.getGuild(p.getLocation());
			if (g != null && !g.isMember(p) && !p.hasPermission("core.cmd.mod")) {
				ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Teleportacja przerwana");
				p.removePotionEffect(PotionEffectType.SLOW);
				p.removePotionEffect(PotionEffectType.BLINDNESS);
				return;
			}
			if (!p.hasPermission("core.cmd.mod")) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 220, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 220, 1));
				ChatUtil.sendMessage((CommandSender) p, "&7Zostaniesz przeniesiony za &c" + delay + " &7sekund, nie ruszaj sie");

			}
			TimerManager.addTask(p, new TimerCallback<Player>() {
				@Override
				public void success(final Player SPAWN) {
					p.teleport(location);
					ChatUtil.sendMessage((CommandSender) p, "&7Zostales poprawnie przeniesiony");
					p.removePotionEffect(PotionEffectType.SLOW);
					p.removePotionEffect(PotionEffectType.BLINDNESS);
				}

				@Override
				public void error(final Player player) {
					ChatUtil.sendMessage((CommandSender) player, "&9&lERROR: &7Przerwano");
					player.removePotionEffect(PotionEffectType.SLOW);
					player.removePotionEffect(PotionEffectType.BLINDNESS);
				}
			}, delay);
		}

		public static void teleportSpawn(final Player p, final Location location, int delay) {
			final Combat combat = CombatManager.getCombat(p);
			if (combat != null && combat.hasFight() && !p.hasPermission("core.cmd.mod")) {
				ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Teleportacja przerwana");
				p.removePotionEffect(PotionEffectType.SLOW);
				p.removePotionEffect(PotionEffectType.BLINDNESS);
				return;
			}
			if (!p.hasPermission("core.cmd.mod")) {
				final Guild g = GuildManager.getGuild(p.getLocation());
				if (!p.hasPermission("core.cmd.mod") && g != null && !g.isMember(p)) {
					delay = 60;
				}
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1320, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1320, 1));
				ChatUtil.sendMessage((CommandSender) p,
						"&7Teleportacja nastapi za &c" + delay + " &7sekund, nie ruszaj sie");
			}
			TimerManager.addTask(p, new TimerCallback<Player>() {
				@Override
				public void success(final Player player) {
					player.teleport(location);
					ChatUtil.sendMessage((CommandSender) player, "&7Zostales poprawnie przeniesiony");
					player.removePotionEffect(PotionEffectType.SLOW);
					player.removePotionEffect(PotionEffectType.BLINDNESS);
				}

				@Override
				public void error(final Player player) {
					ChatUtil.sendMessage((CommandSender) player, "&9&lERROR: &7Teleportacja przerwana");
					player.removePotionEffect(PotionEffectType.SLOW);
					player.removePotionEffect(PotionEffectType.BLINDNESS);
				}
			}, delay);
		}
	}