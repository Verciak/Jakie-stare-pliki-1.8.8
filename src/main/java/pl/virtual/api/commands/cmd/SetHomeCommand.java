
package pl.virtual.api.commands.cmd;

import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SetHomeCommand extends PlayerCommand {
	public SetHomeCommand() {
		super("sethome", "sethome", "/home", "core.cmd.user", new String[0]);
	}

	@Override
	public boolean onCommand(final Player p, final String[] args) {
		if (p.getWorld() != Bukkit.getWorlds().get(0)) {
			return ChatUtil.sendMessage((CommandSender) p,
					"&9&lERROR: &cNie mozesz ustawic domu w tym swiecie!");
		}
		final User u = UserManager.getUser(p);
		if (u == null) {
			return true;
		}
		u.setHome(p.getLocation());
		return ChatUtil.sendMessage((CommandSender) p, "&7Poprawnie Ustawiles dom");
	}
}
