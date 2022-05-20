// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.managers;

import java.sql.ResultSet;
import java.sql.SQLException;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.data.base.user.Quest;
import pl.virtual.api.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import java.util.concurrent.ConcurrentHashMap;

public class QuestManager
{
    public static ConcurrentHashMap<String, Quest> quests;
    
    static {
        QuestManager.quests = new ConcurrentHashMap<String, Quest>();
    }
    
    public static Quest getQuest(final String name) {
        for (final Quest w : QuestManager.quests.values()) {
            if (name.equalsIgnoreCase(w.getName())) {
                return w;
            }
        }
        return null;
    }
    
    public static void addQuest(final String name) {
        QuestManager.quests.put(name, new Quest(name));
    }
    
    public static void deleteQuest(final String name) {
        QuestManager.quests.remove(name);
        ServerPlugin.getStore().update(false, "DELETE FROM `{P}quest` WHERE `name` ='" + name + "';");
    }
    
    public static List<String> getQuestByGroup(final Player p) {
        final List<String> Quest = new ArrayList<String>();
        for (final Quest w : QuestManager.quests.values()) {
                Quest.add(w.getName());
        }
        return Quest;
    }
    
    public static void loadQuest() {
        try {
            final ResultSet rs = ServerPlugin.getStore().query("SELECT * FROM `{P}quest`");
            while (rs.next()) {
                final Quest w = new Quest(rs);
                QuestManager.quests.put(w.getName(), w);
            }
            rs.close();
            Logger.info("Loaded " + QuestManager.quests.size() + " quests");
        }
        catch (SQLException e) {
            Logger.info("Can not load quests Error " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static ConcurrentHashMap<String, Quest> getquests() {
        return QuestManager.quests;
    }
}
