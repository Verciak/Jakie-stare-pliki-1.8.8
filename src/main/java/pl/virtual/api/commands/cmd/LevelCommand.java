// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;

public class LevelCommand extends PlayerCommand
{
    public LevelCommand() {
        super("level", "komenda do sprawdzania lvl", "/lvl [gracz]", "core.cmd.user", new String[] { "pkt", "poziom", "lvl" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        User u = null;
        if (args.length == 0) {
            u = UserManager.getUser(p);
        }
        else {
            u = UserManager.getUser(args[0]);
        }
        if (u == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz nie istnieje");
        }
        return ChatUtil.sendMessage((CommandSender)p, "&7Aktualnie posiadasz &9" + u.getExp() + " &7pkt czyli &9" + u.getLvl() + " &7poziom");
    }
}
