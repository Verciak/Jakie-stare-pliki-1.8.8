// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.variable.user;

import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;

import org.bukkit.entity.Player;
import codecrafter47.bungeetablistplus.api.bukkit.Variable;

public class KDVariable extends Variable
{
    public KDVariable(final String name) {
        super(name);
    }
    
    public String getReplacement(final Player player) {
        final User u = UserManager.getUser(player);
        return (u == null) ? "" : new StringBuilder().append(u.getKd()).toString();
    }
}
