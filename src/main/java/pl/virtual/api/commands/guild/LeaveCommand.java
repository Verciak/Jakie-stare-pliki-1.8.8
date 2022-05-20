package pl.virtual.api.commands.guild;

import org.bukkit.entity.*;

import pl.virtual.api.commands.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.data.base.user.Upr;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.command.*;
import org.bukkit.*;

public class LeaveCommand extends PlayerCommand
{
    public LeaveCommand() {
        super("opusc", "opuszcza gildie", "/opusc", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
        }
        if (g.isOwner(p)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Jestes zalozycielem gildii");
        }
        if (g.isLeader(p.getName())) {
            g.setLeader("Brak");
        }
        g.removeMember(p);
        NameTagManager.leaveFromGuild(g, (OfflinePlayer)p);
        return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &7Gracz &c" + p.getName() + " &7opuscil gildie &8[&c" + g.getTag() + "&8]");
    }
}
