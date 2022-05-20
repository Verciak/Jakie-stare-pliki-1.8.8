// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.variable.guild;

import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.GuildManager;

import org.bukkit.entity.Player;
import codecrafter47.bungeetablistplus.api.bukkit.Variable;

public class GuildPointsVariable extends Variable
{
    public GuildPointsVariable(final String name) {
        super(name);
    }
    
    public String getReplacement(final Player player) {
        final Guild g = GuildManager.getGuild(player);
        return (g == null) ? "&7Punkty: &fBrak" : ("&7Punkty: &f" + g.getPoints());
    }
}
