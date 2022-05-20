package pl.virtual.api.commands.guild;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.virtual.api.API.Config;
import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.Upr;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.NameTagManager;
import pl.virtual.api.managers.UprManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemUtil;

public class JoinCommand extends PlayerCommand
{
    public JoinCommand() {
        super("dolacz", "dolacza do gildii", "/dolacz <tag>", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild guild = GuildManager.getGuild(p);
        if (guild != null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Posiadasz juz gildie");
        }
        if (args.length != 1) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final Guild g = GuildManager.getGuild(args[0]);
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gildia o takim tagu nie istnieje");
        }
        if (!g.getInvites().contains(p)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz zaproszenia do gildii &c" + g.getTag());
        }
        final int mod = g.getMembers().size();
        final String it = Config.COST_JOIN_NORMAL;
        if (!ItemUtil.checkItems(p, it, mod)) {
            ItemUtil.getItem(p, it, mod);
            return true;
        }
        ItemUtil.removeItems(p, it, mod);
        g.getInvites().remove(p);
        g.addMember(p);
        NameTagManager.joinToGuild(g, p);
        return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &7Gracz &c" + p.getName() + " &7dolaczyl do gildii &8[&c" + g.getTag() + "&8]");
    }
}
