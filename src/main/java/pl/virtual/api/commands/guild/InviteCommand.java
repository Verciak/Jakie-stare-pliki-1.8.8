package pl.virtual.api.commands.guild;

import org.bukkit.entity.*;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.data.base.user.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.command.*;

public class InviteCommand extends PlayerCommand
{
    public InviteCommand() {
        super("zapros", "zaprasza gracza do gildii", "/zapros <gracz>", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
        }
        Upr perms = UprManager.getUser(p);
        if (g != null && perms.getInvite() == 0 && !g.isLeader(p.getName())) {
            return ChatUtil.sendMessage(p, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
        }
        if (args.length != 1) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final User o = UserManager.getUser(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz nie istnieje");
        }
        if (o.getPlayer() == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz jest offline");
        }
        final Guild go = GuildManager.getGuild(o.getPlayer());
        if (go != null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz posiada juz gildie");
        }
        if (g.getInvites().contains(o.getPlayer())) {
            g.getInvites().remove(o.getPlayer());
            ChatUtil.sendMessage((CommandSender)o.getPlayer(), "&8[&4&lGILDIE&8] &7Zaproszenie do gildii &c" + g.getTag() + " &7zostalo cofniete");
            return ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Cofnales zaproszenie do gildii dla gracza &c" + o.getName() + "");
        }
        g.getInvites().add(o.getPlayer());
        ChatUtil.sendMessage((CommandSender)o.getPlayer(), "&8&m-------------&8[ &4&lGILDIE &8]&8&m-------------");
        ChatUtil.sendMessage((CommandSender)o.getPlayer(), "&7Zostales zaproszony do gildii &8[&c" + g.getTag() + "&8]");
        ChatUtil.sendMessage((CommandSender)o.getPlayer(), "&7Wpisz &c/dolacz " + g.getTag() + " &7aby dolaczyc do gildii");
        ChatUtil.sendMessage((CommandSender)o.getPlayer(), "&8&m-------------&8[ &4&lGILDIE &8]&8&m-------------");
        return ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Wyslales zaproszenie do gildii dla gracza &c" + o.getName() + "");
    }
}
