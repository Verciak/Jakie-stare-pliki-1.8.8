// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.tasks;

import org.bukkit.command.CommandSender;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoMsgTask extends BukkitRunnable
{
    int index;
    
    public AutoMsgTask() {
        this.index = 0;
    }
    
    public void run() {
        if (Config.AUTOMSG.size() == 0) {
            return;
        }
        for (final Player p : Bukkit.getOnlinePlayers()) {
            final User u = UserManager.getUser(p);
            if (u.isAutoMessages()) {
                ChatUtil.sendMessage((CommandSender)p, Config.AUTOMSG.get(this.index));
            }
        }
        ++this.index;
        if (this.index >= Config.AUTOMSG.size()) {
            this.index = 0;
        }
    }
}
