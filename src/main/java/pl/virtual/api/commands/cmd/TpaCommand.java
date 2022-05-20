// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.entity.Player;

public class TpaCommand extends PlayerCommand
{
    public TpaCommand() {
        super("tpa", "Teleport do gracza", "&7/tpa <player>", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz jest offline");
        }
        final User u = UserManager.getUser(o);
        if (u == null) {
            return true;
        }
        final String yourself = args[0];
        if (yourself.equalsIgnoreCase(p.getName())) {
  
        }
        final String nickja = args[0];
        if (nickja.equalsIgnoreCase(p.getName())) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie teleportowac sie sam do siebie");
        }
        if (u.getTpa().contains(p)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Wyslales juz prosbe o teleport do gracza &9" + o.getName() + "");
        }
        if (u.isIgnoreTpa(p) && !p.hasPermission("core.tpa.ignore")) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Ten gracz zablokowal prosby o teleportacje");
        }
        if (u.getTpahere().contains(p)) {
            u.getTpahere().remove(p);
        }
        u.getTpa().add(p);
        ChatUtil.sendMessage((CommandSender)p, "&7Wyslales prosbe o teleport do gracza &9" + o.getName());
        ChatUtil.sendMessage((CommandSender)o, "&8&m-------------&8[ &a&lTELEPORTACJA &8]&8&m-------------");
        ChatUtil.sendMessage((CommandSender)o, "");
        ChatUtil.sendMessage((CommandSender)o, "&8» &7Gracz &6" + p.getName() + " &7chce sie przeteleportowac do Ciebie");
        ChatUtil.sendMessage((CommandSender)o, "&8» &7Wpisz &a/tpaccept " + p.getName() + " &7aby zaakceptowac");
        ChatUtil.sendMessage((CommandSender)o, "&8» &7Wpisz &c/tpdeny " + p.getName() + " &7aby odrzucic");
        ChatUtil.sendMessage((CommandSender)o, "");
        return ChatUtil.sendMessage((CommandSender)o, "&8&m-------------&8[ &a&lTELEPORTACJA &8]&8&m-------------");
    }
}
