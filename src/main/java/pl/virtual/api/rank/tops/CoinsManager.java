// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.rank.tops;

import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.rank.GuildComparator;
import pl.virtual.api.rank.UserCoinsComparator;

import java.util.LinkedList;
import java.util.List;

public class CoinsManager
{
    private static List<User> coins;
    private static List<Guild> guildcoins;
    
    static {
        CoinsManager.coins = new LinkedList<User>();
        CoinsManager.guildcoins = new LinkedList<Guild>();
    }
    
    public static List<User> getcoins() {
        return CoinsManager.coins;
    }
    
    public static List<Guild> getGuildcoins() {
        return CoinsManager.guildcoins;
    }
    
    public static void addCoins(final User Coins) {
        CoinsManager.coins.add(Coins);
        sortUserCoins();
    }
    
    public static void addCoins(final Guild Coins) {
        CoinsManager.guildcoins.add(Coins);
        getGuildcoins();
    }
    
    public static void removeCoins(final User Coins) {
        CoinsManager.coins.remove(Coins);
        sortUserCoins();
    }
    
    public static void removeCoins(final Guild Coins) {
        CoinsManager.guildcoins.remove(Coins);
        getGuildcoins();
    }
    
    public static void sortUserCoins() {
        CoinsManager.coins.sort(new UserCoinsComparator());
    }
    
    public static void sortGuildcoins() {
        CoinsManager.guildcoins.sort(new GuildComparator());
    }
    
    public static int getPlaceUser(final User user) {
        for (int num = 0; num < CoinsManager.coins.size(); ++num) {
            if (CoinsManager.coins.get(num).equals(user)) {
                return num + 1;
            }
        }
        return 0;
    }
    
    public static int getPlaceGuild(final Guild guild) {
        for (int num = 0; num < CoinsManager.coins.size(); ++num) {
            if (CoinsManager.guildcoins.get(num).equals(guild)) {
                return num + 1;
            }
        }
        return 0;
    }
}
