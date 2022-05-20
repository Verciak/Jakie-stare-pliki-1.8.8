// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.variable.tops;

import pl.virtual.api.rank.tops.AssistManager;

import org.bukkit.entity.Player;
import codecrafter47.bungeetablistplus.api.bukkit.Variable;

public class TopAssistsVariable extends Variable
{
    private int i;
    
    public TopAssistsVariable(final String name, final int i) {
        super(name);
        this.i = i;
    }
    
    public String getReplacement(final Player p) {
        if (AssistManager.getAssists().size() >= this.i) {
            String s = "&f" + this.i + ". &7";
            if (this.i > 9) {
                s = "&f" + this.i + ". &7";
            }
            return String.valueOf(String.valueOf(s)) + AssistManager.getAssists().get(this.i - 1).getName() + " &8[&f" + AssistManager.getAssists().get(this.i - 1).getAsyst() + "&8]";
        }
        return "&f" + this.i + ". ";
    }
}
