package pl.virtual.api.commands.guild;

import java.util.*;
import org.bukkit.entity.*;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.command.*;

public class GuildCoordinateCommand extends PlayerCommand
{
    public static Map<Guild, Long> gt;
    
    static {
        GuildCoordinateCommand.gt = new HashMap<Guild, Long>();
    }
    
    public GuildCoordinateCommand() {
        super("kordy", "Kupno kordynatow gildii", "/kordy <tag>", "core.cmd.user", new String[0]);
    }
    
    @SuppressWarnings("unused")
	@Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length != 1) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final Guild g1 = GuildManager.getGuild(args[0]);
        final Guild g2 = GuildManager.getGuild(p);
        final User user = UserManager.getUser(p);
        if (g2 == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
        }
        if (g2.getTag() == g1.getTag()) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie mozesz kupic kordow swojej gildii");
        }
        if (g1 == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gildia o takim tagu nie istnieje");
        }
        if (user.getCoins() < 2000) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz &c2000 &7coinsow");
        }
        final Long t = GuildCoordinateCommand.gt.get(g2);
        if (t != null && System.currentTimeMillis() - t < 36000L) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Kupowac kordynaty innej gildi mozna co 30m");
        }
        GuildCoordinateCommand.gt.put(g2, System.currentTimeMillis());
        user.removeCoins(2000);
        ChatUtil.sendMessage((CommandSender)p, "");
        ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Poprawnie zakupiono kordy gildii &c" + g1.getTag());
        ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Koordynaty: &7X: &c" + g1.getRegion().getX() + " &7Z: &c" + g1.getRegion().getZ());
        ChatUtil.sendMessage((CommandSender)p, "");
        return false;
    }
}
