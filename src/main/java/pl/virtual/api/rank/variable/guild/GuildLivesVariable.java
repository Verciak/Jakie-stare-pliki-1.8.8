package pl.virtual.api.rank.variable.guild;

import codecrafter47.bungeetablistplus.api.bukkit.*;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.GuildManager;

import org.bukkit.entity.*;

public class GuildLivesVariable extends Variable
{
    public GuildLivesVariable(final String name) {
        super(name);
    }
    
    public String getReplacement(final Player player) {
        final Guild g = GuildManager.getGuild(player);
        return (g == null) ? "&7Zycie: &fBrak" : ("&7Zycie: &f" + g.getLife());
    }
}
