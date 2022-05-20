package pl.virtual.api.commands.guild;

import org.bukkit.entity.*;

import pl.virtual.api.commands.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.data.base.user.Upr;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.command.*;

public class PvpCommand extends PlayerCommand
{
    public PvpCommand() {
        super("pvp", "znienia tryb pvp", "/pvp", "core.cmd.user", new String[] { "gpvp", "ff" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &cNie posiadasz gildii");
        }
        Upr perms = UprManager.getUser(p);
        if (g != null && perms.getPvp() == 0 && !g.isLeader(p.getName())) {
            return ChatUtil.sendMessage(p, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
        }
        if (args.length == 0) {
            g.setPvp(!g.isPvp());
            for (final Player o : g.getOnlineMembers()) {
                ChatUtil.sendMessage((CommandSender)o, "&8[&4&lGILDIE&8] &7Gracz &c" + p.getName() + (g.isPvp() ? " &8- &cwlaczyl" : " &8- &awylaczyl") + " &7ogien sojuszniczy w twojej gildii");
            }
            return true;
        }
        final String s;
        switch ((s = args[0]).hashCode()) {
            case -896830130: {
                if (!s.equals("sojusz")) {
                    return true;
                }
                break;
            }
            case 2996984: {
                if (!s.equals("ally")) {
                    return true;
                }
                break;
            }
            case 1806944311: {
                if (!s.equals("alliance")) {
                    return true;
                }
                break;
            }
        }
        g.setPvpAlly(!g.isPvpAlly());
        for (final Player o2 : g.getOnlineMembers()) {
            ChatUtil.sendMessage((CommandSender)o2, "&8[&4&lGILDIE&8] &7Gracz &c" + p.getName() + (g.isPvpAlly() ? " &8- &awlaczyl" : " &8- &cwylaczyl") + " &7ogien sojuszniczy w sojuszu");
        }
        return true;
    }
}
