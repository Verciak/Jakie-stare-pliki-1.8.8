// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.variable.tops;

import pl.virtual.api.rank.tops.RankingManager;

import org.bukkit.entity.Player;
import codecrafter47.bungeetablistplus.api.bukkit.Variable;

public class TopPointsVariable extends Variable
{
    private int i;
    
    public TopPointsVariable(final String name, final int i) {
        super(name);
        this.i = i;
    }
    
    public String getReplacement(final Player p) {
        if (RankingManager.getRankings().size() >= this.i) {
            String s = "&f" + this.i + ". &7";
            if (this.i > 9) {
                s = "&f" + this.i + ". &7";
            }
            return String.valueOf(String.valueOf(s)) + RankingManager.getRankings().get(this.i - 1).getName() + " &8[&f" + RankingManager.getRankings().get(this.i - 1).getPoints() + "&8]";
        }
        return "&f" + this.i + ". ";
    }
}
