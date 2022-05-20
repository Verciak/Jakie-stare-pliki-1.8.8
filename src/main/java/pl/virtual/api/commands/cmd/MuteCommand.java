// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.data.base.user.Mute;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.MuteManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.DataUtil;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.bukkit.command.CommandSender;

public class MuteCommand extends Command
{
    public MuteCommand() {
        super("mute", "Mutowanie uzytkownikow", "/mute <gracz> [czas] [powod]", "core.cmd.mute", new String[0]);
    }
    
    
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        final Mute m = MuteManager.getMute(args[0]);
        if (m != null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Podany gracz jest juz wyciszony");
        }
        final User u = UserManager.getUser(sender.getName());
        if (sender instanceof Player && u == null) {
            return true;
        }
        final String nick = args[0];
        if (nick.equalsIgnoreCase("Virtual343")) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Nie mozesz wyciszyc wlasciciela");
        }
        if (nick.equalsIgnoreCase("qlemens")) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Nie mozesz wyciszyc wlasciciela");
        }
        if (nick.equalsIgnoreCase("XertTV")) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Nie mozesz wyciszyc wlasciciela");
        }
        final String admin = sender.getName().equals("CONSOLE") ? "konsola" : sender.getName();
        String reason = "Brak";
        if (args.length > 2) {
            reason = StringUtils.join((Object[])args, " ", 2, args.length);
        }
        final long time = DataUtil.parseDateDiff(args[1], true);
        if (time > System.currentTimeMillis()) {
            final Mute mute = new Mute(args[0], admin, reason, time);
            MuteManager.addMute(args[0], mute);
            return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&9&lMUTE&8] &7Admin &9" + sender.getName() + " &7Wyciszyl &9" + args[0] + " &7(&f" + reason + "&7)");
        }
        final Mute mute = new Mute(args[0], admin, reason, 0L);
        MuteManager.addMute(args[0], mute);
        return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&9&lMUTE&8] &7Admin &9" + sender.getName() + " &7Wyciszyl &9" + args[0] + " &7(&f" + reason + "&7)");
    }
}
