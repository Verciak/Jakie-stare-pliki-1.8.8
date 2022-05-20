package pl.virtual.api.rank.variable.user;

import codecrafter47.bungeetablistplus.api.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class OnlineVariable extends Variable
{
    public OnlineVariable(final String name) {
        super(name);
    }
    
    public String getReplacement(final Player o) {
        final int online = Bukkit.getOnlinePlayers().size();
        return Integer.toString(online);
    }
}
