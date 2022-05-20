// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base.user;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.sql.ResultSet;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.DataUtil;
import pl.virtual.api.utils.TimeUtil;

public class DeathBan
{
    private String name;
    private int mode;
    private long time;
    
    public DeathBan(final String name) {
        this.name = name;
        this.mode = 1;
        this.time = System.currentTimeMillis() + TimeUtil.MINUTE.getTime(5);
        this.insert();
    }
    
    public DeathBan(final ResultSet rs) throws SQLException {
        this.name = rs.getString("name");
        this.mode = rs.getInt("mode");
        this.time = rs.getLong("time");
    }
    
    private void insert() {
        ServerPlugin.getStore().update(false, "INSERT INTO `{P}deathbans`(`id`, `name`, `mode`, `time`) VALUES (NULL, '" + this.getName() + "','" + this.getMode() + "','" + this.getTime() + "');");
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(final long time) {
        this.time = time;
    }
    
    public int getMode() {
        return this.mode;
    }
    
    public boolean isBaned() {
        return this.getTime() > System.currentTimeMillis();
    }
    
    public void setMode(final int mode) {
        this.mode = mode;
    }
    
    public void ban(final Player p) {
        if (p.hasPermission("core.deathban")) {
            return;
        }
        if (this.getMode() >= 3) {
            this.setMode(1);
        }
        else {
            this.setMode(this.getMode() + 1);
        }
        this.setTime(System.currentTimeMillis() + TimeUtil.MINUTE.getTime(5 * this.getMode()));
        ServerPlugin.getStore().update(false, "UPDATE `{P}deathbans` SET `time` ='" + this.getTime() + "', `mode` ='" + this.getMode() + "' WHERE `name` ='" + this.getName() + "'");
        if (p.isOnline()) {
            new BukkitRunnable() {
                public void run() {
                    final String reason = "&8####################\n&4Umarles &c:(\n&4Masz bana do: &e" + DataUtil.getDate(DeathBan.this.getTime()) + "\n&4Wygasa: &e" + DataUtil.secondsToString(DeathBan.this.getTime()) + "\n&8####################";
                    p.kickPlayer(ChatUtil.fixColor(reason));
                }
            }.runTaskLater((Plugin)ServerPlugin.getPlugin(), 1L);
        }
    }
}
