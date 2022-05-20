// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.variable.tops;

import pl.virtual.api.rank.tops.DeathManager;

import org.bukkit.entity.Player;
import codecrafter47.bungeetablistplus.api.bukkit.Variable;

public class TopDeathsVariable extends Variable
{
    private int i;
    
    public TopDeathsVariable(final String name, final int i) {
        super(name);
        this.i = i;
    }
    
    public String getReplacement(final Player p) {
        if (DeathManager.getDeaths().size() >= this.i) {
            String s = "&f" + this.i + ". &7";
            if (this.i > 1) {
                s = "&f" + this.i + ". &7";
            }
            return String.valueOf(String.valueOf(s)) + DeathManager.getDeaths().get(this.i - 1).getName() + " &8[&2" + DeathManager.getDeaths().get(this.i - 1).getDeaths() + "&8]";
        }
        return "&f" + this.i + ". ";
    }
}
