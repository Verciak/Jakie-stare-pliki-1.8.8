package pl.virtual.api.commands.guild;

import org.bukkit.entity.*;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.data.base.user.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.command.*;

import org.bukkit.*;

public class WyrzucCommand extends PlayerCommand
{
    public WyrzucCommand() {
        super("wyrzuc", "wyrzuca gracza z gildii", "/wyrzuc <gracz>", "core.cmd.user", new String[0]);
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
        if (args.length != 1) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final User o = UserManager.getUser(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz nie istnieje");
        }
        if (!g.isMember(o.getName())) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz nie jest w twojej gildii");
        }
        if (g.isOwner(o.getName())) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie mozesz wyrzuci zalozyciela");
        }
        if (p.getName().equals(o.getName())) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie mozesz wyrzucic samego siebie");
        }
        if (g.isLeader(o.getName())) {
            g.setLeader("Brak");
        }
        g.removeMember(o.getName());
        NameTagManager.leaveFromGuild(g, o.getOfflinePlayer());
        return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &7Gracz &c" + o.getName() + " &7zostal wyrzucony z gildii &8[&c" + g.getTag() + "&8]");
    }
}
