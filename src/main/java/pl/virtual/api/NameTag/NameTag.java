// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.NameTag;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import pl.virtual.api.data.base.guild.Guild;

public interface NameTag
{
    void initPlayer(final Player p0);
    
    void createGuild(final Guild p0, final Player p1);
    
    void removeGuild(final Guild p0);
    
    void joinToGuild(final Guild p0, final Player p1);
    
    void leaveFromGuild(final Guild p0, final OfflinePlayer p1);
    
    void createAlliance(final Guild p0, final Guild p1);
    
    void removeAlliance(final Guild p0, final Guild p1);
    
    NameTagMode getNameTagMode();

}
