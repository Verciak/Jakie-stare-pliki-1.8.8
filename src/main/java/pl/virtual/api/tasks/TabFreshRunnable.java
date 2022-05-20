// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import pl.virtual.api.rank.tops.KillManager;
import pl.virtual.api.rank.tops.RankingManager;

public class TabFreshRunnable extends BukkitRunnable
{
    public void run() {
        KillManager.sortUserKills();
        RankingManager.sortUserRankings();
        RankingManager.sortGuildRankings();
    }
}
