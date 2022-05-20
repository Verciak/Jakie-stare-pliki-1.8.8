// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.entity.Player;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.data.base.Combat;
import pl.virtual.api.managers.CombatManager;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class KickCommand extends Command
{
    public KickCommand() {
        super("kick", "wyrzucanie graczy z serwera", "/kick <gracz> [powod]", "core.cmd.kick", new String[0]);
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            String msg = Lang.MSG_USAGE;
            msg = msg.replace("{USAGE}", this.getUsage());
            return ChatUtil.sendMessage(sender, msg);
        }
        final Player p = Bukkit.getPlayer(args[0]);
        if (p == null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie jest online");
        }
        if (p.hasPermission("core.cmd.admin")) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Nie mozesz wyrzucic tego gracza");
        }
        if (sender.getName().equalsIgnoreCase(p.getName())) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Nie mozesz wyrzucic sam siebie");
        }
        final String nick = args[0];
        String reason = "Brak";
        if (args.length > 1) {
            reason = StringUtils.join((Object[])args, " ", 1, args.length);
        }
        final String kick = "\n&8&m------&8[ &9&lKICK &8]&8&m------\n\n&7Zostales wyrzucony przez Administratora \n&9&l" + sender.getName() + "\n\n&7Powod Wyrzucenia: &9&l" + reason + "\n\n&8&m------&8[ &9&lKICK &8]&8&m------";
        final Combat combat = CombatManager.getCombat(p);
        combat.setLastAsystTime(0L);
        combat.setPlayer(null);
        combat.setLastAsystPlayer(null);
        p.kickPlayer(ChatUtil.fixColor(kick));
        return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&9&lKICK&8] &7Admin &9" + sender.getName() + " &7Wyrzucil z serwera &9" + args[0] + " &7(&f" + reason + "&7)");
    }
}
