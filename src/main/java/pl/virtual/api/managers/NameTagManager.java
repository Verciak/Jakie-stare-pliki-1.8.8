// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.managers;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.NameTag.NameTag;
import pl.virtual.api.NameTag.NameTagMode;
import pl.virtual.api.NameTag.modes.ScoreBoardNameTag;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.utils.Logger;

import org.bukkit.scheduler.BukkitRunnable;

public class NameTagManager
{
    private static NameTag nameTag;
    
    public static void enable() {
        new BukkitRunnable() {
            public void run() {
                NameTagManager.access$1(new ScoreBoardNameTag());
                Logger.info("Using '" + NameTagManager.nameTag.getClass().getSimpleName().replace("NameTag", "") + "' to support nametags!");
            }
        }.runTask((Plugin)ServerPlugin.getPlugin());
    }
    
    public static void initPlayer(final Player p) {
        if (NameTagManager.nameTag == null) {
            return;
        }
        NameTagManager.nameTag.initPlayer(p);
    }
    
    public static void createGuild(final Guild g, final Player p) {
        if (NameTagManager.nameTag == null) {
            return;
        }
        NameTagManager.nameTag.createGuild(g, p);
    }
    
    public static void removeGuild(final Guild g) {
        if (NameTagManager.nameTag == null) {
            return;
        }
        NameTagManager.nameTag.removeGuild(g);
    }
    
    public static void joinToGuild(final Guild g, final Player p) {
        if (NameTagManager.nameTag == null) {
            return;
        }
        NameTagManager.nameTag.joinToGuild(g, p);
    }
    
    public static void leaveFromGuild(final Guild g, final OfflinePlayer p) {
        if (NameTagManager.nameTag == null) {
            return;
        }
        NameTagManager.nameTag.leaveFromGuild(g, p);
    }
    
    public static void createAlliance(final Guild g, final Guild o) {
        if (NameTagManager.nameTag == null) {
            return;
        }
        NameTagManager.nameTag.createAlliance(g, o);
    }
    
    public static void removeAlliance(final Guild g, final Guild o) {
        if (NameTagManager.nameTag == null) {
            return;
        }
        NameTagManager.nameTag.removeAlliance(g, o);
    }
    
    public static NameTagMode getNameTagMode() {
        if (NameTagManager.nameTag == null) {
            return null;
        }
        return NameTagManager.nameTag.getNameTagMode();
    }
    
    public static NameTag getNameTag() {
        return NameTagManager.nameTag;
    }
    
    static /* synthetic */ void access$1(final NameTag nameTag) {
        NameTagManager.nameTag = nameTag;
    }
}
