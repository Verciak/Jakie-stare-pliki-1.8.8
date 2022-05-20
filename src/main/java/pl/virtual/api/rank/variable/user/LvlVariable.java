// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.variable.user;

import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;

import org.bukkit.entity.Player;
import codecrafter47.bungeetablistplus.api.bukkit.Variable;

public class LvlVariable extends Variable
{
    public LvlVariable(final String name) {
        super(name);
    }
    
    public String getReplacement(final Player player) {
        final User u = UserManager.getUser(player);
        int lvl = 1;
        if (u != null) {
            lvl = u.getLvl();
        }
        return Integer.toString(lvl);
    }
}
