// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.data.base.user.Mute;
import pl.virtual.api.managers.MuteManager;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class UnMuteCommand extends Command
{
    public UnMuteCommand() {
        super("unmute", "unmute", "/unmute <gracz>", "core.cmd.unmute", new String[0]);
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        if (!(sender instanceof Player) && args[0].equalsIgnoreCase("all")) {
            MuteManager.unmuteAll();
            return ChatUtil.sendMessage(sender, "&7Odciszyles wszystkich graczy");
        }
        final Mute m = MuteManager.getMute(args[0]);
        if (m == null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Ten gracz nie jest wyciszony");
        }
        if (!sender.hasPermission("core.cmd.admin") && !m.getAdmin().equalsIgnoreCase(sender.getName())) {
            return ChatUtil.sendMessage(sender, "&7Wyciszyl go administrator &c" + m.getAdmin());
        }
        MuteManager.unmute(m);
        final Player parg = Bukkit.getPlayer(args[0]);
        ChatUtil.sendMessage(sender, "&7Odciszyles gracza &4" + parg.getName() + "");
        if (parg != null) {
            ChatUtil.sendMessage((CommandSender)parg, "&7Zostales odciszony przez administatora &9" + sender.getName() + "");
        }
        return false;
    }
}
