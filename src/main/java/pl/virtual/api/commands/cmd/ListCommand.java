
package pl.virtual.api.commands.cmd;

import org.bukkit.command.CommandSender;

import pl.virtual.api.API.Config;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ListCommand extends PlayerCommand {
	public ListCommand() {
		super("list", "Wyrzucanie wszystkich graczy z serwera", "/list", "core.cmd.list", new String[0]);
	}

	@Override
	public boolean onCommand(final Player sender, final String[] args) {
		return ChatUtil.sendMessage((CommandSender) sender,
				"&7Ilosc graczy na serwerze: &9"
						+ Bukkit.getOnlinePlayers().size() + "/" + Config.SLOT);
	}
}
