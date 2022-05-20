// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.tops;

import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.DeathBan;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.rank.GuildComparator;
import pl.virtual.api.rank.UserDeathsComparator;

import java.util.LinkedList;
import java.util.List;

public class DeathManager
{
    private static List<User> deaths;
    private static List<Guild> guildDeaths;
    
    static {
        DeathManager.deaths = new LinkedList<User>();
        DeathManager.guildDeaths = new LinkedList<Guild>();
    }
    
    public static List<User> getDeaths() {
        return DeathManager.deaths;
    }
    
    public static List<Guild> getGuildDeaths() {
        return DeathManager.guildDeaths;
    }
    
    public static void addDeath(final User death) {
        DeathManager.deaths.add(death);
        sortUserDeaths();
    }
    
    public static void addDeath(final Guild death) {
        DeathManager.guildDeaths.add(death);
        sortGuildDeaths();
    }
    
    public static void removeDeath(final User death) {
        DeathManager.deaths.remove(death);
        sortUserDeaths();
    }
    
    public static void removeDeath(final Guild death) {
        DeathManager.guildDeaths.remove(death);
        sortGuildDeaths();
    }
    
    public static void sortUserDeaths() {
        DeathManager.deaths.sort(new UserDeathsComparator());
    }
    
    public static void sortGuildDeaths() {
        DeathManager.guildDeaths.sort(new GuildComparator());
    }
    
    public static int getPlaceUser(final User user) {
        for (int num = 0; num < DeathManager.deaths.size(); ++num) {
            if (DeathManager.deaths.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }
    
    public static int getPlaceGuild(final Guild guild) {
        for (int num = 0; num < DeathManager.deaths.size(); ++num) {
            if (DeathManager.guildDeaths.get(num).equals(guild)) {
                return num + 1;
            }
        }
        return 0;
    }
}
