// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;

public class SVipCommand extends PlayerCommand
{
    public SVipCommand() {
        super("svip", "svip", "/svip", "core.cmd.user", new String[0]);
    }
    
	@Override
	public boolean onCommand(final Player p, final String[] args) {
		ChatUtil.sendMessage((CommandSender) p, "&8&m--------- &8[ &9SVIP &8]&8&m---------");
		ChatUtil.sendMessage((CommandSender) p, "");
		ChatUtil.sendMessage((CommandSender) p, "&8&l» &7Ranga: &9SVIP:");
		ChatUtil.sendMessage((CommandSender) p,
				" &8&l» &9-50% itemow na gildie");
		ChatUtil.sendMessage((CommandSender) p,
				" &8&l» &91.5 do dropu z kamienia");
		ChatUtil.sendMessage((CommandSender) p, "");
		ChatUtil.sendMessage((CommandSender) p,
				"&8&l» &7Dostep do komend takich jak:");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &6Prefix przed nickiem");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &6Rezerwacja slota");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &6/repair");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &6/ec");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &6/workbench");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &6/kit vip, /kit svip");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &6Rezerwacja slota");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &6Darmowy turbo drop na 10minut");
		ChatUtil.sendMessage((CommandSender) p, "");
		ChatUtil.sendMessage((CommandSender) p, " &8&l» &7Koszt rangi: od: &610.00");
		ChatUtil.sendMessage((CommandSender) p,
				" &8&l» &7Zakupisz na: &9nomenhc.eu");
		ChatUtil.sendMessage((CommandSender) p, "");
		ChatUtil.sendMessage((CommandSender) p, "&8&m--------- &8[ &9SVIP &8]&8&m---------");
		return ChatUtil.sendMessage((CommandSender) p, "");
	}
}