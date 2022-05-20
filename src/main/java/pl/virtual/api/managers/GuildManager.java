package pl.virtual.api.managers;

import java.util.concurrent.*;

import org.bukkit.entity.*;

import java.util.*;

import org.bukkit.plugin.*;

import java.sql.*;

import org.bukkit.*;

import pl.virtual.api.*;
import pl.virtual.api.API.Config;
import pl.virtual.api.commands.cmd.WalkaCommand;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.rank.*;
import pl.virtual.api.rank.tops.RankingManager;
import pl.virtual.api.utils.*;

public class GuildManager
{
    private static final ConcurrentHashMap<String, Guild> guilds;
    
    static {
        guilds = new ConcurrentHashMap<String, Guild>();
    }
    
    public static Guild getGuild(final Player p) {
        for (final Guild g : GuildManager.guilds.values()) {
            if (g.isMember(p)) {
                return g;
            }
        }
        return null;
    }
    
    public static Guild getGuildByNamePlayer(final String name) {
        for (final Guild g : GuildManager.guilds.values()) {
            if (g.isMember(name)) {
                return g;
            }
        }
        return null;
    }
    
    public static int addPoints(final Player p, final Player other) {
        final Guild g = getGuild(p);
        if (g == null) {
            return 0;
        }
        final Guild o = getGuild(other);
        if (o == null) {
            return 10;
        }
        if (g.isMember(other)) {
            return 0;
        }
        if (g.getAlly().contains(o.getTag())) {
            return -10;
        }
        return 50;
    }
    
    public static int removePoints(final Player p, final Player other) {
        final Guild g = getGuild(p);
        if (g == null) {
            return 0;
        }
        final Guild o = getGuild(other);
        if (o == null) {
            return 10;
        }
        if (g.isMember(other)) {
            return 0;
        }
        if (g.getAlly().contains(o.getTag())) {
            return -10;
        }
        return 50;
    }
    
    public static Guild getGuild(final String name) {
        for (final Guild g : GuildManager.guilds.values()) {
            if (g.getName().equalsIgnoreCase(name) || g.getTag().equalsIgnoreCase(name)) {
                return g;
            }
        }
        return null;
    }
    
    public static Guild getGuild(final Location loc) {
        for (final Guild g : GuildManager.guilds.values()) {
            if (g.getRegion().isInCuboid(loc)) {
                return g;
            }
        }
        return null;
    }
    
    public static Guild createGuild(final String tag, final String name, final Player owner, final Location home) {
        final Guild g = new Guild(tag, name, owner, home);
        GuildManager.guilds.put(tag, g);
        final int x = g.getRegion().getX();
        final int z = g.getRegion().getZ();
        owner.teleport(new Location((World)Bukkit.getWorlds().get(0), (double)x, 41.0, (double)z));
        RankingManager.addRanking(g);
        Bukkit.getScheduler().runTask((Plugin)ServerPlugin.getPlugin(), () -> createRoomGuild(g));
        return g;
    }
    
    public static Guild getGuildByLoc(final Location loc) {
        for (final Guild g : GuildManager.guilds.values()) {
            if (g.getRegion().isInCuboidByLoc(loc)) {
                return g;
            }
        }
        return null;
    }
    
    public static void deleteGuild(final Guild g) {
        RankingManager.removeRanking(g);
        Bukkit.getScheduler().runTask((Plugin)ServerPlugin.getPlugin(), () -> deleteRoom(g));
        getGuilds().remove(g.getTag());
        ServerPlugin.getStore().update(false, "DELETE FROM `{P}guilds` WHERE `tag` = '" + g.getTag() + "'");
        ServerPlugin.getStore().update(false, "DELETE FROM `{P}members` WHERE `tag` = '" + g.getTag() + "'");
        for (final String aly : g.getAlly()) {
            final Guild a = getGuild(aly);
            if (a != null) {
                a.removeAlly(g.getTag());
            }
        }
    }
    
    public static void loadGuilds() {
        try {
            final ResultSet rs = ServerPlugin.getStore().query("SELECT * FROM `{P}guilds`");
            while (rs.next()) {
                final Guild g = new Guild(rs);
                GuildManager.guilds.put(g.getTag(), g);
                RankingManager.addRanking(g);
            }
            rs.close();
            Logger.info("Loaded " + GuildManager.guilds.size() + " guilds");
        }
        catch (SQLException e) {
            Logger.info("Can not load guild ERROR: " + e.getMessage());
        }
    }
    
    public static boolean canCreateGuildBySpawn(final Location loc) {
        final int spawnX = loc.getWorld().getSpawnLocation().getBlockX();
        final int spawnZ = loc.getWorld().getSpawnLocation().getBlockZ();
        return Math.abs(loc.getBlockX() - spawnX) >= 400 || Math.abs(loc.getBlockZ() - spawnZ) >= 400;
    }
    
    public static boolean canCreateGuildByBorder(final Location loc) {
        return Math.abs(Config.BORDER_WORLD_RADIUS - loc.getBlockX()) >= 100 && Math.abs(Config.BORDER_WORLD_RADIUS - loc.getBlockZ()) >= 100 && Math.abs(-Config.BORDER_WORLD_RADIUS - loc.getBlockX()) >= 100 && Math.abs(-Config.BORDER_WORLD_RADIUS - loc.getBlockZ()) >= 100 && Math.abs(-Config.BORDER_WORLD_RADIUS - loc.getBlockX()) >= 100 && Math.abs(Config.BORDER_WORLD_RADIUS - loc.getBlockZ()) >= 100 && Math.abs(Config.BORDER_WORLD_RADIUS - loc.getBlockX()) >= 100 && Math.abs(-Config.BORDER_WORLD_RADIUS - loc.getBlockZ()) >= 100;
    }
    
    public static boolean canCreateGuildByGuild(final Location loc) {
        final int mindistance = Config.CUBOID_SIZE_MAX * 2 + Config.CUBOID_SIZE_BETWEEN;
        for (final Guild g : GuildManager.guilds.values()) {
            if (Math.abs(g.getRegion().getX() - loc.getBlockX()) <= mindistance && Math.abs(g.getRegion().getZ() - loc.getBlockZ()) <= mindistance) {
                return false;
            }
        }
        return true;
    }
    
    private static void deleteRoom(final Guild g) {
        final Location c = g.getRegion().getLocation().clone();
        c.setY(40.0);
        c.getBlock().setType(Material.AIR);
        c.setY(41.0);
        c.getBlock().setType(Material.AIR);
        c.setY(38.0);
        c.getBlock().setType(Material.AIR);
        c.setY(39.0);
        c.getBlock().setType(Material.AIR);
    }
    
    private static void createRoomGuild(final Guild g) {
        final Location c = g.getRegion().getLocation().clone();
        c.setY(38.0);
        for (final Location loc : SpaceUtil.getSquare(c, 4, 7)) {
            loc.getBlock().setType(Material.AIR);
        }
        for (final Location loc : SpaceUtil.getSquare(c, 3, 0)) {
            loc.getBlock().setType(Material.STONE_BRICKS);
        }
        for (final Location loc : SpaceUtil.getSquare(c, 2, 1)) {
            loc.getBlock().setType(Material.STONE_BRICKS);
        }
        for (final Location loc : SpaceUtil.getCorners(c, 2, 5)) {
            loc.getBlock().setType(Material.STONE_BRICKS);
        }
        for (final Location loc : SpaceUtil.getCorners(c, 0, 1)) {
            loc.getBlock().setType(Material.STONE_BRICKS);
        }
        c.setY(39.0);
        for (final Location loc : SpaceUtil.getSquare(c, 1, 0)) {
            loc.getBlock().setType(Material.STONE_BRICKS);
        }
        c.getBlock().setType(Material.BEDROCK);
        c.setY(40.0);
        c.getBlock().setType(Material.SPONGE);
        c.setY(41.0);
        c.getBlock().setType(Material.AIR);
        c.setY(44.0);
        for (final Location loc : SpaceUtil.getSquare(c, 2, 0)) {
            loc.getBlock().setType(Material.STONE_BRICKS);
        }
    }
    
    public static ConcurrentHashMap<String, Guild> getGuilds() {
        return GuildManager.guilds;
    }
}
