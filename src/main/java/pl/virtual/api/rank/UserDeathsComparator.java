// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank;

import java.util.Comparator;

import pl.virtual.api.data.base.user.User;

public class UserDeathsComparator implements Comparator<User>
{
    @Override
    public int compare(final User g0, final User g1) {
        final Integer p0 = g0.getDeaths();
        final Integer p2 = g1.getDeaths();
        return p2.compareTo(p0);
    }
}
