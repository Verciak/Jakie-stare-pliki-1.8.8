// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.Upr;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.gui.PermsGui;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.UprManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;

public class aPermsCommand extends PlayerCommand
{    
	public aPermsCommand() {
        super("auprawnienia", "auprawnienia", "/auprawnienia [gracz]", "core.cmd.admin", new String[0]);
    }
    
	@Override
	public boolean onCommand(final Player player, final String[] args) {
        if (args.length != 1) {
            return ChatUtil.sendMessage(player, Lang.USE(this.getUsage()));
        }
        final Guild g = GuildManager.getGuild(player);
        final User o = UserManager.getUser(args[0]);
        Upr perms = UprManager.getUser(player);
        Upr perms2 = UprManager.getUser(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)player, "&9&lERROR: &7Gracz nie istnieje");
        }
        if (g != null && perms == null) {
            return ChatUtil.sendMessage(player, "&9&lERROR: &7Poczekaj gracz nie zostal jeszcze zaladowany");
        }
        final Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            return ChatUtil.sendMessage(player, "&9&lERROR: &7Gracz jest offline");
        }
        if (g.isOwner(args[0])) {
            return ChatUtil.sendMessage(player, "&9&lERROR: &7Nie mozesz zarzadzac uprawnieniami zalozyciela");
        }     
        PermsGui.show(player, targetPlayer);
        return true;
    }
}