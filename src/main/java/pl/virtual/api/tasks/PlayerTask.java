package pl.virtual.api.tasks;

import org.bukkit.scheduler.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.Upr;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.UprManager;

public class PlayerTask extends BukkitRunnable
{
    private final ServerPlugin plugin;
    
    public PlayerTask(final ServerPlugin plugin) {
        this.plugin = plugin;
    }
    
    public void run() {
        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Upr perms = UprManager.getUser(onlinePlayer);
            final Guild g = GuildManager.getGuild(onlinePlayer);
            if (g != null && perms == null) {
            	UprManager.createrUser(onlinePlayer);
            }
            if (perms != null && g == null) {
        		Upr upr = UprManager.getUser(onlinePlayer);
        		UprManager.deleteUser(upr);
            }
        }
    }
}
