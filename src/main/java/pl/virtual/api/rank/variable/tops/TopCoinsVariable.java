// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.variable.tops;

import pl.virtual.api.rank.tops.CoinsManager;

import org.bukkit.entity.Player;
import codecrafter47.bungeetablistplus.api.bukkit.Variable;

public class TopCoinsVariable extends Variable
{
    private int i;
    
    public TopCoinsVariable(final String name, final int i) {
        super(name);
        this.i = i;
    }
    
    public String getReplacement(final Player p) {
        if (CoinsManager.getcoins().size() >= this.i) {
            String s = "&f" + this.i + ". &7";
            if (this.i > 9) {
                s = "&f" + this.i + ". &7";
            }
            return String.valueOf(String.valueOf(s)) + CoinsManager.getcoins().get(this.i - 1).getName() + " &8[&2" + CoinsManager.getcoins().get(this.i - 1).getCoins() + "&8]";
        }
        return "&f" + this.i + ". ";
    }
}
