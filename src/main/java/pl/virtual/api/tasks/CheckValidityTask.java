// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.tasks;

import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckValidityTask extends BukkitRunnable
{
    public void run() {
        for (final Guild g : GuildManager.getGuilds().values()) {
            if (g.getProlong() < System.currentTimeMillis()) {
                GuildManager.deleteGuild(g);
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&7Gildia &8[&c" + g.getTag() + "&8] &7wygasla");
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&7Jej kordy to X: &c" + g.getRegion().getX() + "&7, &7Z: &c" + g.getRegion().getZ());
            }
        }
    }
}

