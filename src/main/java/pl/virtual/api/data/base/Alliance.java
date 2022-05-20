// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base;

import java.sql.SQLException;
import java.sql.ResultSet;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.mysql.store.Entry;

public class Alliance implements Entry
{
    private final Guild guild1;
    private final Guild guild2;
    
    public Alliance(final Guild guild1, final Guild guild2) {
        this.guild1 = guild1;
        this.guild2 = guild2;
    }
    
    public Alliance(final ResultSet rs) throws SQLException {
        this.guild1 = GuildManager.getGuild(rs.getString("guild_1"));
        this.guild2 = GuildManager.getGuild(rs.getString("guild_2"));
    }
    
    @Override
    public void insert() {
        ServerPlugin.getStore().update(true, "INSERT INTO `{P}alliances` (`id`,`guild_1`,`guild_2`) VALUES(NULL, '" + this.guild1.getTag() + "', '" + this.guild2.getTag() + "')");
    }
    
    @Override
    public void update(final boolean paramBoolean) {
        throw new RuntimeException("Can not update this object!");
    }
    
    @Override
    public void delete() {
        ServerPlugin.getStore().update(true, "DELETE FROM `{P}alliances` WHERE `guild_1` = '" + this.guild1.getTag() + "' AND `guild_2` ='" + this.guild2.getTag() + "'");
        ServerPlugin.getStore().update(true, "DELETE FROM `{P}alliances` WHERE `guild_1` = '" + this.guild2.getTag() + "' AND `guild_2` ='" + this.guild1.getTag() + "'");
    }
    
    public Guild getGuild1() {
        return this.guild1;
    }
    
    public Guild getGuild2() {
        return this.guild2;
    }
}
