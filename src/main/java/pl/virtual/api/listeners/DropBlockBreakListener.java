
package pl.virtual.api.listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.DropManager;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.UserManager;

import org.bukkit.GameMode;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.Listener;

public class DropBlockBreakListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(final BlockBreakEvent e) {
        final Player p = e.getPlayer();
        final Block b = e.getBlock();
        if (e.isCancelled()) {
            return;
        }
        if (!p.getGameMode().equals((Object)GameMode.SURVIVAL)) {
            return;
        }
        int exp = DropManager.getExp(b.getType(), p);
        final User u = UserManager.getUser(p);
        final Guild g = GuildManager.getGuild(p);
        if (Config.TURBO_EXP > System.currentTimeMillis() || (u != null && u.getTurboExp() > System.currentTimeMillis()) || (g != null && g.getTurboExp() > System.currentTimeMillis())) {
            exp *= 2;
        }
        p.giveExp(exp);
        DropManager.getDropData(b.getType()).breakBlock(b, p, p.getItemInHand());
        e.setCancelled(true);
    }
}
