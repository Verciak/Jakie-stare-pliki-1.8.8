// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class WalkaCommand extends PlayerCommand
{
	private static HashMap<UUID, Long> times;
    
    static {
        times = new HashMap<UUID, Long>();
    }
    public WalkaCommand() {
        super("walka", "walka", "/walka", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
    	final Long t = WalkaCommand.times.get(p.getUniqueId());
    	if (t != null && System.currentTimeMillis() - t < 60000L) {
    		return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Tej komendy mozesz uzywac co 1 min");
    	}
    	WalkaCommand.times.put(p.getUniqueId(), System.currentTimeMillis());
    	Bukkit.broadcastMessage(ChatUtil.fixColor(""));
    	Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&lWALKA&8] &7Gracz &9" + p.getName() + " &7Chcialby sie poklepac jego kordy to"));
    	Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&9&lWALKA&8] &7X: &9" + p.getLocation().getBlockX() + "&7, &7Y: &9" + p.getLocation().getBlockY() + "&7, &7Z: &9 " + p.getLocation().getBlockZ() + ""));
    	Bukkit.broadcastMessage(ChatUtil.fixColor(""));
    	return true;
    }
}
