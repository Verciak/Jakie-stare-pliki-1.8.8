// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.command.CommandSender;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.Warp;
import pl.virtual.api.managers.WarpManager;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.entity.Player;

public class SetWarpCommand extends PlayerCommand
{
    public SetWarpCommand() {
        super("setwarp", "setwarp", "/setwarp <name>", "core.cmd.setwarp", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 1) {
            String msg = Lang.MSG_USAGE;
            msg = msg.replace("{USAGE}", this.getUsage());
            return ChatUtil.sendMessage((CommandSender)p, msg);
        }
        final Warp w = WarpManager.getWarp(args[0]);
        if (w != null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Istnieje juz taki warp");
        }
        if (args.length == 1) {
            WarpManager.addWarp(args[0], p.getLocation(), "core.cmd.admin");
            return ChatUtil.sendMessage((CommandSender)p, "&7Warp &c" + args[0] + " &7zostal ustawiony");
        }
        if (args.length == 2) {
            final String rank = args[1];
            WarpManager.addWarp(args[0], p.getLocation(), rank.toLowerCase());
            return ChatUtil.sendMessage((CommandSender)p, "&7Warp &c" + args[0] + " &7zostal ustawiony z pex &c" + rank.toUpperCase());
        }
        return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
    }
}
