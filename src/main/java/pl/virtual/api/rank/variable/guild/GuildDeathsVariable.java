// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.variable.guild;

import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.GuildManager;

import org.bukkit.entity.Player;
import codecrafter47.bungeetablistplus.api.bukkit.Variable;

public class GuildDeathsVariable extends Variable
{
    public GuildDeathsVariable(final String name) {
        super(name);
    }
    
    public String getReplacement(final Player player) {
        final Guild g = GuildManager.getGuild(player);
        return (g == null) ? "&7Smierci: &fBrak" : ("&7Smierci: &f" + g.getDeaths());
    }
}
