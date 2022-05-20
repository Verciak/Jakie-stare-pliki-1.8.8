// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank;

import java.util.Comparator;

import pl.virtual.api.data.base.guild.Guild;

public class GuildComparator implements Comparator<Guild>
{
    @Override
    public int compare(final Guild g0, final Guild g1) {
        final Integer p0 = g0.getPoints();
        final Integer p2 = g1.getPoints();
        return p2.compareTo(p0);
    }
}
