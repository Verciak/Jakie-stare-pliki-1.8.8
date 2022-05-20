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
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.entity.Player;
import java.util.UUID;
import java.util.HashMap;

public class TellCommand extends PlayerCommand
{
    private static final HashMap<UUID, UUID> lastMsg;
    private static final HashMap<UUID, Long> times;
    
    static {
        lastMsg = new HashMap<UUID, UUID>();
        times = new HashMap<UUID, Long>();
    }
    
    public TellCommand() {
        super("msg", "prywatne wiadomosci do graczy", "/msg [gracz] <tresc>", "core.cmd.user", new String[] { "whisper", "t", "m", "msg" });
    }
    
    @Override
    public boolean onCommand(final Player player, final String[] args) {
        if (args.length < 2) {
            String msg = Lang.MSG_USAGE;
            msg = msg.replace("{USAGE}", this.getUsage());
            return ChatUtil.sendMessage((CommandSender)player, msg);
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)player, "&9&lERROR: &7Grasz jest offline");
        }
        if (o.hasPermission("core.tell.nomsg") && !player.hasPermission("core.tell.nomsg")) {
            return ChatUtil.sendMessage((CommandSender)player, "&7Do tego gracza nie mozesz wyslac wiadomosci");
        }
        final User user = UserManager.getUser(args[0]);
        if (!user.isPrivateMessages()) {
            return ChatUtil.sendMessage((CommandSender)player, "&7Ten gracz ma wylaczone prywatne wiadomosci");
        }
        if (user.isIgnoreTell(player) && !player.hasPermission("core.tell.ignore")) {
            return ChatUtil.sendMessage((CommandSender)player, "&7Ten gracz zablokowal od Ciebie prywatne wiadomosci");
        }
        final String nickja = args[0];
        if (nickja.equalsIgnoreCase(player.getName())) {
            return ChatUtil.sendMessage((CommandSender)player, "&9&lERROR: &7Nie mozesz pisac sam do siebie");
        }
        final Long t = TellCommand.times.get(player.getUniqueId());
        if (t != null && System.currentTimeMillis() - t < 3000L) {
            return ChatUtil.sendMessage((CommandSender)player, "&9&lERROR: &7Nie spamuj");
        }
        final String message = ChatColor.stripColor(ChatUtil.fixColor(StringUtils.join((Object[])args, " ", 1, args.length)));
        TellCommand.lastMsg.put(player.getUniqueId(), o.getUniqueId());
        TellCommand.lastMsg.put(o.getUniqueId(), player.getUniqueId());
        TellCommand.times.put(player.getUniqueId(), System.currentTimeMillis());
        ChatUtil.sendMessage((CommandSender)player, "&9Ja -> " + o.getName() + "&9: &7" + message);
        ChatUtil.sendMessage(o, "&9" + player.getName() + " -> Ja: &7" + message, "&8(&bKliknij, aby odpisac&8)");
        for (final Player plr : Bukkit.getOnlinePlayers()) {
            if (SocialSpyCommand.socialspy.contains(plr.getName())) {
                plr.sendMessage(ChatUtil.fixColor("&8[&4&lSZPIEG&8] &7" + player.getName() + " &9-> &7" + o.getName() + ": &7" + message));
            }
        }
        return false;
    }
    
    public static HashMap<UUID, UUID> getLastMsg() {
        return TellCommand.lastMsg;
    }
    
    public static HashMap<UUID, Long> getTimes() {
        return TellCommand.times;
    }
}






