package pl.virtual.api.commands.guild;

import org.bukkit.entity.*;
import org.bukkit.command.*;

import pl.virtual.api.API.Config;
import pl.virtual.api.commands.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.data.base.user.Upr;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

public class ProlongCommand extends PlayerCommand
{
    public ProlongCommand() {
        super("odnow", "przedluza waznosc gildii", "/odnow", "core.cmd.user", new String[] { "przedluz" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
        }
        Upr perms = UprManager.getUser(p);
        if (g != null && perms.getProlong() == 0 && !g.isLeader(p.getName())) {
            return ChatUtil.sendMessage(p, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
        }
        if (g.getProlong() + TimeUtil.DAY.getTime(Config.PROLONG_ADD) > System.currentTimeMillis() + TimeUtil.DAY.getTime(Config.PROLONG_MAX)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gildia jest przedluzona na maksymalny okres");
        }
        final String it = Config.COST_PROLONG_NORMAL;
        if (!ItemUtil.checkItems(p, it, 1)) {
            ItemUtil.getItem(p, it, 1);
            return true;
        }
        ItemUtil.removeItems(p, it, 1);
        g.setProlong(g.getProlong() + TimeUtil.DAY.getTime(Config.PROLONG_ADD));
        return ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Przedluzylesz waznosc gildii o &c" + Config.PROLONG_ADD + " &7dni");
    }
}
