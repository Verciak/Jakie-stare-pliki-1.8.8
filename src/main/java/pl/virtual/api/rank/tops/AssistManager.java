// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.tops;

import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.rank.GuildComparator;
import pl.virtual.api.rank.UserAssistsComparator;

import java.util.LinkedList;
import java.util.List;

public class AssistManager
{
    private static List<User> assists;
    private static List<Guild> guildAssists;
    
    static {
        AssistManager.assists = new LinkedList<User>();
        AssistManager.guildAssists = new LinkedList<Guild>();
    }
    
    public static List<User> getAssists() {
        return AssistManager.assists;
    }
    
    public static List<Guild> getGuildAssists() {
        return AssistManager.guildAssists;
    }
    
    public static void addAssist(final User assist) {
        AssistManager.assists.add(assist);
        sortUserAssists();
    }
    
    public static void addAssist(final Guild assist) {
        AssistManager.guildAssists.add(assist);
        sortGuildAssists();
    }
    
    public static void removeAssist(final User assist) {
        AssistManager.assists.remove(assist);
        sortUserAssists();
    }
    
    public static void removeAssist(final Guild assist) {
        AssistManager.guildAssists.remove(assist);
        sortGuildAssists();
    }
    
    public static void sortUserAssists() {
        AssistManager.assists.sort(new UserAssistsComparator());
    }
    
    public static void sortGuildAssists() {
        AssistManager.guildAssists.sort(new GuildComparator());
    }
    
    public static int getPlaceUser(final User user) {
        for (int num = 0; num < AssistManager.assists.size(); ++num) {
            if (AssistManager.assists.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }
    
    public static int getPlaceGuild(final Guild guild) {
        for (int num = 0; num < AssistManager.assists.size(); ++num) {
            if (AssistManager.guildAssists.get(num).equals(guild)) {
                return num + 1;
            }
        }
        return 0;
    }
}
