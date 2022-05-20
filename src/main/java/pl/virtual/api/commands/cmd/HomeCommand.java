
package pl.virtual.api.commands.cmd;

import pl.virtual.api.API.Config;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.TimerUtil;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class HomeCommand extends PlayerCommand {
	public HomeCommand() {
		super("home", "home", "/home", "core.cmd.user", new String[0]);
	}

	@Override
	public boolean onCommand(final Player p, final String[] args) {
		final User u = UserManager.getUser(p);
		if (u == null) {
			return true;
		}
		if (!p.hasPermission("tools.cmd.admin") && !Config.ENABLE_HOME) {
			return ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Ta komenda jest tymczasowo wylaczona");
		}
		if (u.getHomeLocation().getBlockX() == 0
				&& u.getHomeLocation().getBlockY() == 0
				&& u.getHomeLocation().getBlockZ() == 0) {
			return ChatUtil.sendMessage((CommandSender) p,
					"&7Hej, nie posiadasz domu ustaw go &9/sethome");
		}
		final Guild g = GuildManager.getGuild(u.getHomeLocation());
		if (g != null && !g.isMember(p) && !p.hasPermission("tools.cmd.admin")) {
			return ChatUtil.sendMessage((CommandSender) p,
					"&9&lERROR: &7Dom jest na terenie wrogiej gildii");
		}
		if (!p.hasPermission("tools.cmd.mod")) {
			TimerUtil.teleportSpawn(p, u.getHomeLocation(), 10);
		} else {
			ChatUtil.sendMessage((CommandSender) p, "&7Rozgrzewam teleport Poczekaj &910 &7sekund");
			p.teleport(u.getHomeLocation());
		}
		return true;
	}
}
