// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.variable.user;

import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;

import org.bukkit.entity.Player;
import codecrafter47.bungeetablistplus.api.bukkit.Variable;

public class AssistsVariable extends Variable
{
    public AssistsVariable(final String name) {
        super(name);
    }
    
    public String getReplacement(final Player player) {
        final User u = UserManager.getUser(player);
        int points = 0;
        if (u != null) {
            points = u.getAsyst();
        }
        return Integer.toString(points);
    }
}
