// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.data.base.guild;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import java.sql.ResultSet;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.API.Config;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.TimeUtil;
import java.util.HashSet;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.Set;

public class Guild
{
    private final String tag;
    private String description;
    private final String name;
    private final Region region;
    private int life;
    private int hp;
    private int sojusz;
    private long hpLastAttack;
    private long lifeLastAttack;
    private long prolong;
    private final Set<String> members;
    private final Set<Player> invites;
    private final Set<String> ally;
    private String owner;
    private String leader;
    private Location home;
    private boolean pvp;
    private boolean preDeleted;
    private long createTime;
    private long lastExplodeTime;
    private Set<Guild> allyInvites;
    private int points;
    private int kills;
    private int deaths;
    private int coins;
    private long turboDrop;
    private long turboExp;
    private int exp;
    private boolean pvpAlly;
    
    public Guild(final String tag, final String name, final Player owner, final Location home) {
        this.description = "Brak";
        this.members = new HashSet<String>();
        this.invites = new HashSet<Player>();
        this.ally = new HashSet<String>();
        this.allyInvites = new HashSet<Guild>();
        this.preDeleted = false;
        this.lastExplodeTime = 0L;
        this.tag = tag;
        this.name = name;
        this.owner = owner.getName();
        this.leader = "Brak";
        this.region = new Region(owner.getLocation().getBlockX(), owner.getLocation().getBlockZ(), Config.CUBOID_SIZE_START);
        this.life = 3;
        this.lifeLastAttack = System.currentTimeMillis() + TimeUtil.HOUR.getTime(24);
        this.prolong = System.currentTimeMillis() + TimeUtil.DAY.getTime(Config.PROLONG_START);
        this.hp = 100;
        this.hpLastAttack = System.currentTimeMillis() + TimeUtil.HOUR.getTime(24);
        this.home = home;
        this.createTime = System.currentTimeMillis();
        this.pvp = false;
        this.addMember(owner);
        this.points = 500;
        this.kills = 0;
        this.deaths = 0;
        this.coins = 0;
        this.sojusz = 2;
        this.turboDrop = 0L;
        this.turboExp = 0L;
        this.exp = 0;
        this.pvpAlly = false;
        this.insert();
    }
    
    public Guild(final ResultSet rs) throws SQLException {
        this.description = rs.getString("description");
        this.members = new HashSet<String>();
        this.invites = new HashSet<Player>();
        this.ally = new HashSet<String>();
        this.allyInvites = new HashSet<Guild>();
        this.preDeleted = false;
        this.lastExplodeTime = 0L;
        this.tag = rs.getString("tag");
        this.name = rs.getString("name");
        this.owner = rs.getString("owner");
        this.leader = rs.getString("leader");
        this.region = new Region(rs.getInt("cuboidX"), rs.getInt("cuboidZ"), rs.getInt("cuboidSize"));
        this.life = rs.getInt("life");
        this.hp = rs.getInt("hp");
        this.hpLastAttack = rs.getLong("hpLastAttack");
        this.lifeLastAttack = rs.getLong("lifeLastAttack");
        this.prolong = rs.getLong("prolong");
        this.home = new Location(Bukkit.getWorld("world"), rs.getDouble("homeX"), rs.getDouble("homeY"), rs.getDouble("homeZ"));
        this.createTime = rs.getLong("createTime");
        this.pvp = (rs.getInt("pvp") == 1);
        this.points = rs.getInt("points");
        this.kills = rs.getInt("kills");
        this.deaths = rs.getInt("deaths");
        this.sojusz = rs.getInt("sojusz");
        this.coins = rs.getInt("coins");
        this.turboDrop = rs.getInt("turboDrop");
        this.turboExp = rs.getInt("turboExp");
        this.exp = rs.getInt("exp");
        this.pvpAlly = (rs.getInt("pvpAlly") == 1);
        final ResultSet r = ServerPlugin.getStore().query("SELECT * FROM `{P}members` WHERE `tag` = '" + this.tag + "';");
        while (r.next()) {
            this.members.add(r.getString("name"));
        }
        r.close();
        if (rs.getString("ally").equals("")) {
            return;
        }
        String[] split;
        for (int length = (split = rs.getString("ally").split(", ")).length, i = 0; i < length; ++i) {
            final String s = split[i];
            this.ally.add(s);
        }
    }
    
    public int getHp() {
        return this.hp;
    }
    
    public void setHp(final int hp) {
        this.hp = hp;
    }
    
    public long getHpLastAttack() {
        return this.hpLastAttack;
    }
    
    public void setHpLastAttack(final long hpLastAttack) {
        this.hpLastAttack = hpLastAttack;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `hpLastAttack` = '" + this.getHpLastAttack() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `description` = '" + this.getDescription() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public void addMember(final Player p) {
        this.members.add(p.getName());
        ServerPlugin.getStore().update(false, "INSERT INTO `{P}members` (`id`,`name`,`tag`) VALUES(NULL, '" + p.getName() + "', '" + this.getTag() + "');");
    }
    
    public void removeMember(final Player p) {
        this.members.remove(p.getName());
        ServerPlugin.getStore().update(false, "DELETE FROM `{P}members` WHERE `name` = '" + p.getName() + "' AND `tag` = '" + this.getTag() + "';");
    }
    
    public void removeMember(final String p) {
        this.members.remove(p);
        ServerPlugin.getStore().update(false, "DELETE FROM `{P}members` WHERE `name` = '" + p + "' AND `tag` = '" + this.getTag() + "';");
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Region getRegion() {
        return this.region;
    }
    
    public int getLife() {
        return this.life;
    }
    
    public void setLife(final int life) {
        this.life = life;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `life` = '" + this.getLife() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public long getLifeLastAttack() {
        return this.lifeLastAttack;
    }
    
    public void setLifeLastAttack(final long lifeLastAttack) {
        this.lifeLastAttack = lifeLastAttack;
        ServerPlugin.getStore().update("UPDATE `{P}guilds` SET `lifeLastAttack` = '" + this.getLifeLastAttack() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public void message(final String msg) {
        for (final Player p : this.getOnlineMembers()) {
            ChatUtil.sendMessage((CommandSender)p, msg);
        }
    }
    
    public long getProlong() {
        return this.prolong;
    }
    
    public Set<Guild> getAllyinvites() {
        return this.allyInvites;
    }
    
    public void setProlong(final long prolong) {
        this.prolong = prolong;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `prolong` = '" + this.getProlong() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public Set<String> getMembers() {
        return this.members;
    }
    
    public Set<Player> getInvites() {
        return this.invites;
    }
    
    public String getOwner() {
        return this.owner;
    }
    
    public void setOwner(final String owner) {
        this.owner = owner;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `owner` = '" + this.getOwner() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public String getLeader() {
        return this.leader;
    }
    
    public void setLeader(final String leader) {
        this.leader = leader;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `leader` = '" + this.getLeader() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public Location getHome() {
        return this.home;
    }
    
    public void setHome(final Location home) {
        this.home = home;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `homeX` = '" + this.getHome().getX() + "', `homeY` ='" + this.getHome().getY() + "', `homeZ` ='" + this.getHome().getZ() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public boolean isPvp() {
        return this.pvp;
    }
    
    public void setPvp(final boolean pvp) {
        this.pvp = pvp;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `pvp` = '" + (this.isPvp() ? 1 : 0) + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public boolean isPreDeleted() {
        return this.preDeleted;
    }
    
    public void setPreDeleted(final boolean preDeleted) {
        this.preDeleted = preDeleted;
    }
    
    public long getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(final long createTime) {
        this.createTime = createTime;
    }
    
    public long getLastExplodeTime() {
        return this.lastExplodeTime;
    }
    
    public void setLastExplodeTime(final long lastExplodeTime) {
        this.lastExplodeTime = lastExplodeTime;
    }
    
    public boolean isMember(final String name) {
        return this.getMembers().contains(name);
    }
    
    public boolean isMember(final Player p) {
        return this.getMembers().contains(p.getName());
    }
    
    public boolean isOwner(final Player p) {
        return this.getOwner().equalsIgnoreCase(p.getName());
    }
    
    public boolean isOwner(final String p) {
        return this.getOwner().equalsIgnoreCase(p);
    }
    
    public boolean isLeader(final String string) {
        return this.getOwner().equals(string) || this.getLeader().equals(string);
    }
    
    private void insert() {
        ServerPlugin.getStore().update(false, "INSERT INTO `{P}guilds`(`id`, `tag`, `name`, `owner`, `description`, `hp`, `hpLastAttack`, `leader`, `cuboidX`, `cuboidZ`, `cuboidSize`, `life`, `lifeLastAttack`, `prolong`, `pvp`, `createTime`, `homeX`, `homeY`, `homeZ`, `ally`, `points`, `kills`, `deaths`, `sojusz`, `coins`, `turboDrop`, `turboExp`, `exp`, `pvpAlly`) VALUES (NULL, '" + this.getTag() + "','" + this.getName() + "','" + this.getOwner() + "','" + this.getDescription() + "','" + this.getHp() + "','" + this.getHpLastAttack() + "','" + this.getLeader() + "','" + this.getRegion().getX() + "','" + this.getRegion().getZ() + "','" + this.getRegion().getSize() + "','" + this.getLife() + "','" + this.getLifeLastAttack() + "','" + this.getProlong() + "','" + (this.isPvp() ? 1 : 0) + "','" + this.getCreateTime() + "','" + this.getHome().getX() + "','" + this.getHome().getY() + "','" + this.getHome().getZ() + "','" + this.ally.toString().replace("[", "").replace("]", "") + "','" + this.getPoints() + "','" + this.getKills() + "','" + this.getDeaths() + "','" + this.getSojusz() + "','" + this.getCoins() + "','" + this.getTurboDrop() + "','" + this.getTurboExp() + "','" + this.getExp() + "','" + (this.pvpAlly ? 1 : 0) + "');");
    }
    
    public Set<String> getAlly() {
        return this.ally;
    }
    
    public int getPoints() {
        return this.points;
    }
    
    public int getCoins() {
        return this.coins;
    }
    
    public void removeCoins(final int paramInt) {
        this.coins -= paramInt;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `coins` = '" + this.getCoins() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public void addCoins(final int paramInt) {
        this.coins += paramInt;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `coins` = '" + this.getCoins() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public int getSojusz() {
        return this.sojusz;
    }
    
    public void setSojusz(final int sojusz) {
        this.sojusz = sojusz;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `sojusz` = '" + this.getSojusz() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public void setPoints(final int points) {
        this.points = points;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `points` = '" + this.getPoints() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public int getKills() {
        return this.kills;
    }
    
    public void setKills(final int kills) {
        this.kills = kills;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `kills` = '" + this.getKills() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public int getDeaths() {
        return this.deaths;
    }
    
    public void setDeaths(final int deaths) {
        this.deaths = deaths;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `deaths` = '" + this.getDeaths() + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public void addPoints(final int index) {
        this.setPoints(this.getPoints() + index);
    }
    
    public void removePoints(final int index) {
        this.setPoints(this.getPoints() - index);
    }
    
    public void addKills(final int index) {
        this.setKills(this.getKills() + index);
    }
    
    public void removeKills(final int index) {
        this.setKills(this.getKills() - index);
    }
    
    public void addDeaths(final int index) {
        this.setDeaths(this.getDeaths() + index);
    }
    
    public void removeDeaths(final int index) {
        this.setDeaths(this.getDeaths() + index);
    }
    
    public boolean isPvpAlly() {
        return this.pvpAlly;
    }
    
    public long getTurboDrop() {
        return this.turboDrop;
    }
    
    public void setTurboDrop(final long turboDrop) {
        this.turboDrop = turboDrop;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `turboDrop` = '" + this.turboDrop + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public long getTurboExp() {
        return this.turboExp;
    }
    
    public void setTurboExp(final long turboExp) {
        this.turboExp = turboExp;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `turboExp` = '" + this.turboExp + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public int getExp() {
        return this.exp;
    }
    
    public void setExp(final int exp) {
        this.exp = exp;
    }
    
    public void setPvpAlly(final boolean pvpAlly) {
        this.pvpAlly = pvpAlly;
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `pvpAlly` = '" + (this.isPvpAlly() ? 1 : 0) + "' WHERE `tag` ='" + this.getTag() + "';");
    }
    
    public double getKd() {
        if (this.getKills() == 0 && this.getDeaths() == 0) {
            return 0.0;
        }
        if (this.getKills() > 0 && this.getDeaths() == 0) {
            return this.getKills();
        }
        if (this.getDeaths() > 0 && this.getKills() == 0) {
            return -this.getDeaths();
        }
        return ChatUtil.round(this.getKills() / this.getDeaths(), 2);
    }
    
    public boolean isExits() {
        return this.getProlong() > System.currentTimeMillis();
    }
    
    public boolean isProtected() {
        return this.getCreateTime() + TimeUtil.HOUR.getTime(Config.TNT_CUBOID_PROTECTION_HOWHOUR) > System.currentTimeMillis();
    }
    
    public boolean isLastAtack() {
        return this.getLifeLastAttack() > System.currentTimeMillis();
    }
    
    public Set<Player> getOnlineMembers() {
        final Set<Player> online = new HashSet<Player>();
        for (final String u : this.members) {
            final OfflinePlayer op = Bukkit.getOfflinePlayer(u);
            if (op.isOnline()) {
                online.add(op.getPlayer());
            }
        }
        return online;
    }
    
    public String getALlyList() {
        String s = "&8» &7Sojusze: ";
        if (this.getAlly().size() == 0) {
            s = String.valueOf(s) + "&c\u00e2\u015b\u2013";
        }
        else {
            for (final String name : this.getAlly()) {
                s = String.valueOf(s) + "&8, &b" + name;
            }
        }
        return s;
    }
    
    public void addAlly(final String ally) {
        this.ally.add(ally);
        ServerPlugin.getStore().update("UPDATE `{P}guilds` SET `ally` ='" + this.getAlly().toString().replace("[", "").replace("]", "") + "' WHERE `tag` ='" + this.getTag() + "'");
    }
    
    public void removeAlly(final String ally) {
        this.ally.remove(ally);
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `ally` ='" + this.getAlly().toString().replace("[", "").replace("]", "") + "' WHERE `tag` ='" + this.getTag() + "'");
    }
    
    public void setSize(final int size) {
        this.getRegion().setSize(size);
        ServerPlugin.getStore().update(false, "UPDATE {P}guilds` SET `cuboidSize` = '" + this.getRegion().getSize() + "' WHERE tag = '" + this.getTag() + "';");
    }
    
    public void addSize(final int size) {
        this.getRegion().addSize(size);
        ServerPlugin.getStore().update(false, "UPDATE `{P}guilds` SET `cuboidSize` = '" + this.getRegion().getSize() + "' WHERE tag = '" + this.getTag() + "';");
    }
    
    public boolean isLeader(final Player player) {
        return this.getLeader().equalsIgnoreCase(player.getName());
    }
}
