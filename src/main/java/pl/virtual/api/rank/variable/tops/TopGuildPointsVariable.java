// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.variable.tops;

import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.rank.tops.RankingManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;
import codecrafter47.bungeetablistplus.api.bukkit.Variable;

public class TopGuildPointsVariable extends Variable
{
    private int i;
    
    public TopGuildPointsVariable(final String name, final int i) {
        super(name);
        this.i = i;
    }
    
    public String getReplacement(final Player player) {
        if (RankingManager.getGuildRankings().size() >= this.i) {
            final Guild guild = RankingManager.getGuildRankings().get(this.i - 1);
            String s = "&f" + this.i + ". &7";
            if (this.i > 9) {
                s = "&f" + this.i + ". &7";
            }
            return ChatUtil.fixColor(String.valueOf(String.valueOf(s)) + guild.getTag() + " &8[&f" + guild.getPoints() + "&8]");
        }
        return "&f" + this.i + ". ";
    }
}
