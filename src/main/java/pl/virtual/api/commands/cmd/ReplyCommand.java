// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.entity.Player;
import java.util.UUID;
import java.util.HashMap;

public class ReplyCommand extends PlayerCommand
{
    private static final HashMap<UUID, Long> times;
    
    static {
        times = new HashMap<UUID, Long>();
    }
    
    public ReplyCommand() {
        super("reply", "odpowiedz na prywatna wiadomosc", "/reply <wiadomosc>", "core.cmd.user", new String[] { "r" });
    }
    
    @Override
    public boolean onCommand(final Player player, final String[] args) {
        if (args.length < 1) {
            String msg = Lang.MSG_USAGE;
            msg = msg.replace("{USAGE}", this.getUsage());
            return ChatUtil.sendMessage((CommandSender)player, msg);
        }
        final UUID last = TellCommand.getLastMsg().get(player.getUniqueId());
        if (last == null) {
            return ChatUtil.sendMessage((CommandSender)player, "&9&lERROR: &7Nie masz komu odpisac");
        }
        final Player o = Bukkit.getPlayer(last);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)player, "&9&lERROR: &7Gracz nie jest online");
        }
        final Long t = ReplyCommand.times.get(player.getUniqueId());
        if (t != null && System.currentTimeMillis() - t < 3000L) {
            return ChatUtil.sendMessage((CommandSender)player, "&cNie spamuj");
        }
        ReplyCommand.times.put(player.getUniqueId(), System.currentTimeMillis());
        final String message = ChatColor.stripColor(ChatUtil.fixColor(StringUtils.join((Object[])args, " ")));
        TellCommand.getLastMsg().put(player.getUniqueId(), o.getUniqueId());
        TellCommand.getLastMsg().put(o.getUniqueId(), player.getUniqueId());
        ChatUtil.sendMessage((CommandSender)player, "&9Ja -> " + o.getName() + "&9: &7" + message);
        ChatUtil.sendMessage(o, "&9" + player.getName() + " -> Ja: &7" + message);
        return false;
    }
    
    public static HashMap<UUID, Long> getTimes() {
        return ReplyCommand.times;
    }
}
