// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.NameTag.modes;

import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.OfflinePlayer;
import org.bukkit.Bukkit;

import pl.virtual.api.API.Config;
import pl.virtual.api.NameTag.NameTag;
import pl.virtual.api.NameTag.NameTagMode;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;

public class ScoreBoardNameTag implements NameTag
{
    private static String parse(final String color, final Guild g, final Player p) {
        if (g == null) {
            return ChatUtil.fixColor(color);
        }
        String msg = Config.TAG_FORMAT;
        msg = msg.replace("{TAG}", g.getTag());
        msg = msg.replace("{COLOR}", color);
        return ChatUtil.fixColor(msg);
    }
    
    @Override
    public void initPlayer(final Player p) {
        final Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        final Guild g = GuildManager.getGuild(p);
        for (final Guild o : GuildManager.getGuilds().values()) {
            Team t = sb.getTeam(o.getTag());
            if (t == null) {
                t = sb.registerNewTeam(o.getTag());
            }
            if (g == null) {
                t.setPrefix(parse(Config.TAG_COLOR_ENEMY, o, p));
            }
            if (g == null) {
                t.setPrefix(parse(Config.TAG_COLOR_ENEMY, o, p));
            }
            else if (g.getTag().equalsIgnoreCase(o.getTag())) {
                t.setPrefix(parse(Config.TAG_COLOR_FRIEND, o, p));
            }
            else if (o.getAlly().contains(g.getTag())) {
                t.setPrefix(parse(Config.TAG_COLOR_ALLIANCE, o, p));
            }
            else {
                t.setPrefix(parse(Config.TAG_COLOR_ENEMY, o, p));
            }
        }
        Team noguild = sb.getTeam("noguild");
        if (noguild == null) {
            noguild = sb.registerNewTeam("noguild");
            noguild.setAllowFriendlyFire(true);
            noguild.setCanSeeFriendlyInvisibles(false);
            noguild.setPrefix(parse(Config.TAG_COLOR_NOGUILD, null, p));
        }
        p.setScoreboard(sb);
        for (final Player online : Bukkit.getOnlinePlayers()) {
            online.getScoreboard().getTeam((g != null) ? g.getTag() : "noguild").addPlayer((OfflinePlayer)p);
            final Guild onlineguild = GuildManager.getGuild(online);
            p.getScoreboard().getTeam((onlineguild != null) ? onlineguild.getTag() : "noguild").addPlayer((OfflinePlayer)online);
        }
    }
    
    @Override
    public void createGuild(final Guild g, final Player p) {
        for (final Player o : Bukkit.getOnlinePlayers()) {
            final Scoreboard sb = o.getScoreboard();
            final Team t = sb.registerNewTeam(g.getTag());
            if (o == p) {
                t.setPrefix(parse(Config.TAG_COLOR_FRIEND, g, o));
            }
            else {
                t.setPrefix(parse(Config.TAG_COLOR_ENEMY, g, o));
            }
            t.addPlayer((OfflinePlayer)p);
        }
    }
    
    @Override
    public void removeGuild(final Guild g) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            final Scoreboard sb = p.getScoreboard();
            sb.getTeam(g.getTag()).unregister();
            final Team noguild = sb.getTeam("noguild");
            for (final Player guildplayer : g.getOnlineMembers()) {
                noguild.addPlayer((OfflinePlayer)guildplayer);
            }
        }
    }
    
    @Override
    public void joinToGuild(final Guild g, final Player p) {
        for (final Player o : Bukkit.getOnlinePlayers()) {
            o.getScoreboard().getTeam(g.getTag()).addPlayer((OfflinePlayer)p);
        }
        p.getScoreboard().getTeam(g.getTag()).setPrefix(parse(Config.TAG_COLOR_FRIEND, g, p));
    }
    
    @Override
    public void leaveFromGuild(final Guild g, final OfflinePlayer p) {
        for (final Player o : Bukkit.getOnlinePlayers()) {
            o.getScoreboard().getTeam("noguild").addPlayer(p);
        }
        if (p.isOnline()) {
            p.getPlayer().getScoreboard().getTeam(g.getTag()).setPrefix(parse(Config.TAG_COLOR_ENEMY, g, null));
        }
    }
    
    @Override
    public void createAlliance(final Guild g, final Guild o) {
        for (final Player p : g.getOnlineMembers()) {
            final Team t = p.getScoreboard().getTeam(o.getTag());
            if (t != null) {
                t.setPrefix(parse(Config.TAG_COLOR_ALLIANCE, o, p));
            }
        }
        for (final Player p : o.getOnlineMembers()) {
            final Team t = p.getScoreboard().getTeam(g.getTag());
            if (t != null) {
                t.setPrefix(parse(Config.TAG_COLOR_ALLIANCE, g, p));
            }
        }
    }
    
    @Override
    public void removeAlliance(final Guild g, final Guild o) {
        for (final Player p : g.getOnlineMembers()) {
            final Team t = p.getScoreboard().getTeam(o.getTag());
            if (t != null) {
                t.setPrefix(parse(Config.TAG_COLOR_ENEMY, o, p));
            }
        }
        for (final Player p : o.getOnlineMembers()) {
            final Team t = p.getScoreboard().getTeam(g.getTag());
            if (t != null) {
                t.setPrefix(parse(Config.TAG_COLOR_ENEMY, g, p));
            }
        }
    }
    
    @Override
    public NameTagMode getNameTagMode() {
        return NameTagMode.SCOREBOARD;
    }
}
