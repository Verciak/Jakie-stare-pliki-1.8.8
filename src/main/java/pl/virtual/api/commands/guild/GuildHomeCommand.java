package pl.virtual.api.commands.guild;

import org.bukkit.entity.*;
import org.bukkit.command.*;

import pl.virtual.api.commands.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

public class GuildHomeCommand extends PlayerCommand
{
    public GuildHomeCommand() {
        super("baza", "teleportacja do bazy gildii", "/baza", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
        }
        final Guild o = GuildManager.getGuild(p.getLocation());
        if (o != null && !o.isMember(p)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie mozesz teleportowac sie na terenie wrogiej gildii");
        }
        TimerUtil.teleport(p, g.getHome(), 10);
        return true;
    }
}
