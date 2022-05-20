// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.Command;
import pl.virtual.api.data.base.Combat;
import pl.virtual.api.managers.CombatManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

public class KickAllCommand extends Command
{
    public KickAllCommand() {
        super("kickall", "Wyrzucanie wszystkich graczy z serwera", "/kickall", "core.cmd.kickall", new String[0]);
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        final String kick = "\n&8&m------&8[ &9&lKICK &8]&8&m------\n\n&7Zostales wyrzocony przez Administratora &9&l" + sender.getName() + "\n\n&7Powod Wyrzucenia: &9&l" + StringUtils.join((Object[])args, " ") + "\n\n&8&m------&8[ &9&lKICK &8]&8&m------";
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (!p.hasPermission("core.cmd.mod")) {
                final Combat combat = CombatManager.getCombat(p);
                combat.setLastAttactTime(0L);
                combat.setLastAttactkPlayer(null);
                combat.setLastAsystPlayer(null);
                p.kickPlayer(ChatUtil.fixColor(kick));
            }
        }
        return ChatUtil.sendMessage(sender, "&7Wyrzucono wszystkich graczy z serwera");
    }
}
