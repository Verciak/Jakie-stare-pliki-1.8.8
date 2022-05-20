package pl.virtual.api.commands.cmd;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.*;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;

public class TpahereCommand extends PlayerCommand
{
    public TpahereCommand() {
        super("tpahere", "Teleportowanie gracza do siebie", "/tpahere <gracz>", "core.cmd.tpahere", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage((CommandSender)p, "&cPoprawne uzycie: &7/tpahere <gracz>");
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz jest offline");
        }
        final User u = UserManager.getUser(o);
        if (u == null) {
            return true;
        }
        final String nickja = args[0];
        if (nickja.equalsIgnoreCase(p.getName())) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie teleportowac sie sam do siebie");
        }
        if (u.getTpahere().contains(p)) {
        	return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Wyslales juz prosbe o teleport do gracza &c" + o.getName());
        }
        if (u.getTpa().contains(p)) {
            u.getTpa().remove(p);
        }
        u.getTpahere().add(p);
        ChatUtil.sendMessage((CommandSender)p, "&7Wyslales prosbe o teleport gracza &9" + o.getName() + " &7do siebie");
        ChatUtil.sendMessage((CommandSender)o, "&8&m-------------&8[ &9&lTELEPORTACJA &8]&8&m-------------");
        ChatUtil.sendMessage((CommandSender)o, "");
        ChatUtil.sendMessage((CommandSender)o, "&8» &7Gracz &6" + p.getName() + " &7chce cie przeteleportowac do siebie");
        ChatUtil.sendMessage((CommandSender)o, "&8» &7Wpisz &a/tpaccept " + p.getName() + " &7aby zaakceptowac");
        ChatUtil.sendMessage((CommandSender)o, "&8» &7Wpisz &c/tpdeny " + p.getName() + " &7aby odrzucic");
        ChatUtil.sendMessage((CommandSender)o, "");
        return ChatUtil.sendMessage((CommandSender)o, "&8&m-------------&8[ &9&lTELEPORTACJA &8]&8&m-------------");
    }
}
