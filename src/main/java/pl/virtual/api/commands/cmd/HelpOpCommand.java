// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.entity.Player;
import java.util.UUID;
import java.util.HashMap;

public class HelpOpCommand extends PlayerCommand
{
    private static final HashMap<UUID, Long> times;
    
    static {
        times = new HashMap<UUID, Long>();
    }
    
    public static HashMap<UUID, Long> getTimes() {
        return HelpOpCommand.times;
    }
    
    public HelpOpCommand() {
        super("helpop", "helpop", "&7/helpop <tresc pomocy>", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player sender, final String[] args) {
        if (args.length < 1) {
            String msg = Lang.MSG_USAGE;
            msg = msg.replace("{USAGE}", this.getUsage());
            return ChatUtil.sendMessage((CommandSender)sender, msg);
        }
        final Long t = HelpOpCommand.times.get(sender.getUniqueId());
        if (t != null && System.currentTimeMillis() - t < 10000L) {
            return ChatUtil.sendMessage((CommandSender)sender, "&9&lERROR: &7Odczekaj 10 sekund.");
        }
        final String message = ChatColor.stripColor(ChatUtil.fixColor(StringUtils.join((Object[])args, " ")));
        for (final Player po : Bukkit.getOnlinePlayers()) {
            final User u2 = UserManager.getUser(po);
            if (po.hasPermission("core.cmd.helpop.view") && u2.isHelpop(true)) {
                ChatUtil.sendMessage(po, "&8[&9&lHELPOP&7&8] &9&l" + sender.getName() + " &8-> &f&n" + message);
            }
        }
        HelpOpCommand.times.put(sender.getUniqueId(), System.currentTimeMillis());
        return ChatUtil.sendMessage((CommandSender)sender, "&7Twoja wiadomosc zostala wyslana &8(&f" + message + "&8)");
    }
}
