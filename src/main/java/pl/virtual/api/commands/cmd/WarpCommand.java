// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.Warp;
import pl.virtual.api.managers.WarpManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.TimerUtil;

import java.util.Collection;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand extends PlayerCommand
{
    public WarpCommand() {
        super("warp", "warp", "/warp <name>", "core.cmd.warp", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 1) {
            if (WarpManager.getWarpByGroup(p).size() == 0) {
                return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Brak warpow");
            }
            return ChatUtil.sendMessage((CommandSender)p, "&7Warpy: &c" + StringUtils.join((Collection)WarpManager.getWarpByGroup(p), "&8, &c"));
        }
        else {
            final Warp w = WarpManager.getWarp(args[0]);
            if (w == null) {
                return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Warp nie istnieje");
            }
            TimerUtil.teleport(p, w.getLocation(), 10);
            return true;
        }
    }
}
