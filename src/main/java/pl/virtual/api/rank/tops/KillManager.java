// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.tops;

import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.rank.GuildComparator;
import pl.virtual.api.rank.UserKillsComparator;

import java.util.LinkedList;
import java.util.List;

public class KillManager
{
    private static List<User> kills;
    private static List<Guild> guildKills;
    
    static {
        KillManager.kills = new LinkedList<User>();
        KillManager.guildKills = new LinkedList<Guild>();
    }
    
    public static List<User> getKills() {
        return KillManager.kills;
    }
    
    public static List<Guild> getGuildKills() {
        return KillManager.guildKills;
    }
    
    public static void addKill(final User kill) {
        KillManager.kills.add(kill);
        sortUserKills();
    }
    
    public static void addKill(final Guild kill) {
        KillManager.guildKills.add(kill);
        sortGuildKills();
    }
    
    public static void removeKill(final User kill) {
        KillManager.kills.remove(kill);
        sortUserKills();
    }
    
    public static void removeKill(final Guild kill) {
        KillManager.guildKills.remove(kill);
        sortGuildKills();
    }
    
    public static void sortUserKills() {
        KillManager.kills.sort(new UserKillsComparator());
    }
    
    public static void sortGuildKills() {
        KillManager.guildKills.sort(new GuildComparator());
    }
    
    public static int getPlaceUser(final User user) {
        for (int num = 0; num < KillManager.kills.size(); ++num) {
            if (KillManager.kills.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }
    
    public static int getPlaceGuild(final Guild guild) {
        for (int num = 0; num < KillManager.kills.size(); ++num) {
            if (KillManager.guildKills.get(num).equals(guild)) {
                return num + 1;
            }
        }
        return 0;
    }
}
