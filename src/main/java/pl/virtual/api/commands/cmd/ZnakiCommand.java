// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;

public class ZnakiCommand extends PlayerCommand
{
    public ZnakiCommand() {
        super("znaki", "znaki", "/znaki", "core.cmd.user", new String[0]);
    }
    
	@Override
	public boolean onCommand(final Player p, final String[] args) {
		ChatUtil.sendMessage((CommandSender) p, "&8&m--------- &8[ &9ZNAKI &8]&8&m---------");
		ChatUtil.sendMessage((CommandSender) p, "");
		ChatUtil.sendMessage((CommandSender) p, "&7#<3 &8- &4&l\u2764&f");
		ChatUtil.sendMessage((CommandSender) p, "&7#X  &8- &c\u2716&f");
		ChatUtil.sendMessage((CommandSender) p, "&7#>> &8- &e»&f");
		ChatUtil.sendMessage((CommandSender) p, "&7#<< &8- &e«&f");
		ChatUtil.sendMessage((CommandSender) p, "&7#V  &8- &2\u2714&f");
		ChatUtil.sendMessage((CommandSender) p, "");
		ChatUtil.sendMessage((CommandSender) p, "&8&m--------- &8[ &9ZNAKI &8]&8&m---------");
		return ChatUtil.sendMessage((CommandSender) p, "");
	}
}