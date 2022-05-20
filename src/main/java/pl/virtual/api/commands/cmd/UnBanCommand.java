// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.data.base.user.Ban;
import pl.virtual.api.managers.BanManager;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class UnBanCommand extends Command
{
    public UnBanCommand() {
        super("unban", "Odbanowywanie graczy", "/unban <gracz>", "core.cmd.unban", new String[0]);
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        if (!(sender instanceof Player) && args[0].equalsIgnoreCase("all")) {
            BanManager.unbanAll();
            return ChatUtil.sendMessage(sender, "&7Odbanowales wszystkich graczy");
        }
        final Ban b = BanManager.getBan(args[0]);
        if (b == null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Ten gracz nie ma bana");
        }
        if (!sender.hasPermission("core.cmd.admin") && !b.getAdmin().equalsIgnoreCase(sender.getName())) {
            return ChatUtil.sendMessage(sender, "&7Ban nalezy do &4" + b.getAdmin());
        }
        BanManager.unban(b);
        return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&9&lBAN&8] &7Gracz &9" + b.getName() + " &7zostal odbanowany przez &9" + sender.getName());
    }
}
