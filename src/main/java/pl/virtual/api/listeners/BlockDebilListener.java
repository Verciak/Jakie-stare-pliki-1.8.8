package pl.virtual.api.listeners;

import org.bukkit.event.player.*;

import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import java.util.*;

public class BlockDebilListener implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerCommandPreprocess(
			final PlayerCommandPreprocessEvent event) {
		final Player player = event.getPlayer();
		if (!player.hasPermission("core.plugins")) {
			final String message = event.getMessage();
			final String[] splittedMessage = message.split(" ");
			final String[] pluginCommands = { "/permissionex", "/pex", "/pex",
					"//calc", "//calculate", "//eval", "//evaluate", "//solve",
					"/?",
					"/help", "/bukkit:?", "/bukkit:help", "/me", "/version",
					"/ver", "/about", "/bukkit:about", "/bukkit:version",
					"/bukkit:ver", "/minecraft:me", "/minecraft:tell", "/tell", "/minelogin", "/seed", "/ping" };
			if (containsIgnoreCase(pluginCommands, splittedMessage[0])) {
				event.setCancelled(true);
		         ChatUtil.sendMessage(player, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
			}
		}
	}

	public static boolean containsIgnoreCase(final String[] board,
			final String string) {
		for (final String otherstring : board) {
			if (otherstring.equalsIgnoreCase(string)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsIgnoreCase(final List<String> board,
			final String string) {
		for (final String otherstring : board) {
			if (otherstring.equalsIgnoreCase(string)) {
				return true;
			}
		}
		return false;
	}
}
