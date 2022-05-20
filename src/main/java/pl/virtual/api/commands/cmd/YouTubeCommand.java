// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class YouTubeCommand extends PlayerCommand
{
    public YouTubeCommand() {
        super("yt", "yt", "/yt", "core.cmd.user", new String[0]);
    }
    
	@Override
	public boolean onCommand(final Player p, final String[] args) {
		ChatUtil.sendMessage((CommandSender) p, "&8&m--------- &8[ &cY&fT &8]&8&m---------");
		ChatUtil.sendMessage((CommandSender) p, "");
		ChatUtil.sendMessage((CommandSender) p, "&8&l» &7Ranga: &cY&fT:");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &9-50% itemow na gildie!");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &9+30% do dropu z kamienia!");
		ChatUtil.sendMessage((CommandSender) p, "");
		ChatUtil.sendMessage((CommandSender) p, "&8&l» &7Dostep do komend takich jak:");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &6/repair");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &6/ec");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &6/workbench");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &6/kit vip, /kit svip");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &6Rezerwacja slota");
		ChatUtil.sendMessage((CommandSender) p, "");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &7Wymagane: &6350 Subskrybcji");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &7Po range zglos sie na discord: &6dc.nomenhc.eu");
		ChatUtil.sendMessage((CommandSender) p, "");
		ChatUtil.sendMessage((CommandSender) p, "&8&m--------- &8[ &cY&fT &8]&8&m---------");
		return ChatUtil.sendMessage((CommandSender) p, "");
	}
}