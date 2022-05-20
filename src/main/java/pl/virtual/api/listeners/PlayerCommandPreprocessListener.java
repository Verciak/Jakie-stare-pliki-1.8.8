// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.help.HelpTopic;

import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.Listener;

public class PlayerCommandPreprocessListener implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerCommandPreprocess2(
			final PlayerCommandPreprocessEvent event) {
		final Player p = event.getPlayer();
		if (!event.isCancelled()) {
			final String command = event.getMessage().split(" ")[0];
			final HelpTopic htopic = Bukkit.getServer().getHelpMap()
					.getHelpTopic(command);
			if (htopic == null) {
				String s = "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej";
				s = s.replace("{KOMENDA}", event.getMessage());
				s = s.replace("{COMMAND}", event.getMessage());
				p.sendMessage(ChatUtil.fixColor(s));
				event.setCancelled(true);
				return;
			}
		}
		final String msg = event.getMessage();
		if (msg.startsWith("ncp") || msg.startsWith("verus")) {
			event.setCancelled(true);
			String s2 = "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej";
			s2 = s2.replace("{KOMENDA}", event.getMessage());
			s2 = s2.replace("{COMMAND}", event.getMessage());
			p.sendMessage(ChatUtil.fixColor(s2));
		}
		final String msg1 = event.getMessage();
		if (msg1.startsWith("ac") || msg1.startsWith("ac")) {
			event.setCancelled(true);
			String s2 = "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej";
			s2 = s2.replace("{KOMENDA}", event.getMessage());
			s2 = s2.replace("{COMMAND}", event.getMessage());
			p.sendMessage(ChatUtil.fixColor(s2));
		}
	}
}
