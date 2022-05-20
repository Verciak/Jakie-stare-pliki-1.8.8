// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.guild;

import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;

public class GuildSetHomeCommand extends PlayerCommand
{
    public GuildSetHomeCommand() {
        super("ustawbaza", "ustawia baze gildii ", "/ustawbaza", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
        }
        if (!g.isLeader(p.getName())) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie jestes liderem gildii");
        }
        final Guild o = GuildManager.getGuild(p.getLocation());
        if (!g.equals(o)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Baze gildii mozesz ustawic tylko na terenie gildii");
        }
        g.setHome(p.getLocation());
        return ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Ustawiles baze gildii");
    }
}
