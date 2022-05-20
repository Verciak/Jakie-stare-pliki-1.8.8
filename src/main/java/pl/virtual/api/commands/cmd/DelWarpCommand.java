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

public class DelWarpCommand extends PlayerCommand
{
    public DelWarpCommand() {
        super("delwarp", "delwarp", "/delwarp <name>", "core.cmd.delwarp", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 1) {
            String msg = Lang.MSG_USAGE;
            msg = msg.replace("{USAGE}", this.getUsage());
            return ChatUtil.sendMessage((CommandSender)p, msg);
        }
        final Warp w = WarpManager.getWarp(args[0]);
        if (w == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Istnieje juz warp");
        }
        WarpManager.deleteWarp(w.getName());
        return ChatUtil.sendMessage((CommandSender)p, "&7Poprawnie usuneles warp &9" + w.getName());
    }
}
