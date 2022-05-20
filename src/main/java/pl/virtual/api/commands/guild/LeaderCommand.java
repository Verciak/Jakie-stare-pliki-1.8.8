package pl.virtual.api.commands.guild;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.*;

import pl.virtual.api.API.Config;
import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.data.base.user.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

public class LeaderCommand extends PlayerCommand
{
    public LeaderCommand() {
        super("zastepca", "zmienia zastepce gildii", "/zastepca <gracz>", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
        }
        if (!g.isOwner(p)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie jestes liderem gildii");
        }
        if (args.length != 1) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final User u = UserManager.getUser(args[0]);
        if (u == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz nie istnieje");
        }
        if (!g.isMember(u.getName())) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz nie jest w twojej gildii");
        }
        if (g.isOwner(u.getName())) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie mozesz wyrzucic zalozyciela");
        }
        if (g.isLeader(u.getName())) {
            g.setLeader("Brak");
            return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &7Gracz &c" + u.getName() + " &7nie jest juz zastepca gildii &8[&c" + g.getTag() + "&8]");
        }
        final String it = Config.COST_LEADER_NORMAL;
        if (!ItemUtil.checkItems(p, it, 1)) {
            ItemUtil.getItem(p, it, 1);
            return true;
        }
        ItemUtil.removeItems(p, it, 1);
        g.setLeader(u.getName());
        return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &7Gracz &c" + u.getName() + " &7zostal nowym zastepca gildii &8[&c" + g.getTag() + "&8]");
    }
}
