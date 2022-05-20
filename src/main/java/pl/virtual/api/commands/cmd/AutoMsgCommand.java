// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.apache.commons.lang.StringUtils;

import pl.virtual.api.API.Config;
import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class AutoMsgCommand extends Command
{
    public AutoMsgCommand() {
        super("automsg", "Komenda do oddwania auto", "/automsg <add/remove/list>", "core.cmd.auto", new String[0]);
    }
    
    @Override
    public boolean onExecute(final CommandSender p, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(p, Lang.USE(this.getUsage()));
        }
        final String s2;
        switch (s2 = args[0]) {
            case "remove": {
                if (args.length < 2) {
                    return ChatUtil.sendMessage(p, Lang.USE("/automsg remove <id>"));
                }
                if (!ChatUtil.isInteger(args[1])) {
                    return ChatUtil.sendMessage(p, "&9&lERROR: &7To nie jest id");
                }
                if (Config.AUTOMSG.size() == 0) {
                    return ChatUtil.sendMessage(p, "&9&lERROR: &7Brak automsg");
                }
                final int i = Integer.parseInt(args[1]);
                if (Config.AUTOMSG.size() <= i) {
                    return ChatUtil.sendMessage(p, "&9&lERROR: &7Zle id");
                }
                Config.AUTOMSG.remove(i);
                Config.saveConfig();
                return true;
            }
            case "add": {
                final String msg = StringUtils.join((Object[])args, " ", 1, args.length);
                Config.AUTOMSG.add(msg);
                Config.saveConfig();
                return ChatUtil.sendMessage(p, "&7Dodales do auto msg &c" + msg);
            }
            case "list": {
                if (Config.AUTOMSG.size() == 0) {
                    return ChatUtil.sendMessage(p, "&9&lERROR: &7Brak automsg");
                }
                int id = 0;
                ChatUtil.sendMessage(p, "&8 &7Automsg \n");
                for (final String s : Config.AUTOMSG) {
                    ChatUtil.sendMessage(p, " &8(" + id + "&8) &r" + s + "\n");
                    ++id;
                }
                return true;
            }
            default:
                break;
        }
        return ChatUtil.sendMessage(p, Lang.USE(this.getUsage()));
    }
}
