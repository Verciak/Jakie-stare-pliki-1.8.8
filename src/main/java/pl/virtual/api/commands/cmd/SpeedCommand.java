// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.command.CommandSender;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.entity.Player;

public class SpeedCommand extends PlayerCommand
{
    public SpeedCommand() {
        super("speed", "speed", "/speed <1-10>", "core.cmd.speed", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final float speed = Float.parseFloat(args[0]);
        if (speed > 10.0f) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Predkosc speed musi wynosic 1-10");
        }
        if (speed < 1.0f) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Predkosc speed musi wynosic 1-10");
        }
		if (!ChatUtil.isInteger(args[0])) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7To nie jest liczba");
		}
        final float finalSpeed = speed / 10.0f;
        p.setFlySpeed(finalSpeed);
        return ChatUtil.sendMessage((CommandSender)p, "&7Ustawile predkosc latania na &c" + speed + "");
    }
}
