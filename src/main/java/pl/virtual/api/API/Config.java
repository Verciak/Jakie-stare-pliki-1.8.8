package pl.virtual.api.API;

import java.util.*;
import org.bukkit.configuration.file.*;

import pl.virtual.api.ServerPlugin;

import java.lang.reflect.*;

public class Config
{
    public static String DATABASE_MODE;
    public static String DATABASE_TABLEPREFIX;
    public static String DATABASE_MYSQL_HOST;
    public static int DATABASE_MYSQL_PORT;
    public static String DATABASE_MYSQL_USER;
    public static String DATABASE_MYSQL_PASS;
    public static String DATABASE_MYSQL_NAME;
    public static String DATABASE_SQLITE_NAME;
    public static String COST_CREATE_NORMAL;
    public static String COST_CREATE_VIP;
    public static String COST_CREATE_SVIP;
    public static String COST_CREATE_DARK;
    public static String COST_JOIN_NORMAL;
    public static String COST_LEADER_NORMAL;
    public static String COST_OWNER_NORMAL;
    public static String COST_ENLARGE_NORMAL;
    public static String COST_PROLONG_NORMAL;
    public static int CUBOID_SIZE_START;
    public static int CUBOID_SIZE_MAX;
    public static int CUBOID_SIZE_ADD;
    public static int CUBOID_SIZE_BETWEEN;
    public static int CUBOID_SPAWN_DISTANCE;
    public static int LIFE_START;
    public static int LIFE_COOLDOWN;
    public static int PROLONG_START;
    public static int PROLONG_MAX;
    public static int PROLONG_ADD;
    public static int SLOT;
    public static String CHAT_FORMAT_GLOBAL;
    public static String CHAT_FORMAT_ADMIN;
    public static String CHAT_FORMAT_GUILD;
	public static int CHAT_SLOWMODE;
    public static String TAG_FORMAT;
    public static String TAG_COLOR_NOGUILD;
    public static String TAG_COLOR_FRIEND;
    public static String TAG_COLOR_ENEMY;
    public static String TAG_COLOR_ALLIANCE;
    public static boolean TNT_CUBOID_PROTECTION_ENABLED;
    public static int TNT_CUBOID_PROTECTION_HOWHOUR;
    public static int TNT_CUBOID_EXPLODETIME;
    public static int BORDER_WORLD_RADIUS;
    public static int BORDER_NETHERWORLD_RADIUS;
    public static boolean BLOCKED_BUILT_INCOMBAT;
    public static int LINIA_SIZE_WORLD;
    public static int LIMIT_PEARL;
    public static int LIMIT_REFILE;
    public static int LIMIT_KOX;
    public static int LIMIT_SNOW;
    public static int LIMIT_ARROW;
    public static int LIMIT_ICE;
    public static int TNT_OD;
    public static int TNT_DO;
    public static int LVL;
    public static long STONE;
    public static long LOWIENIE;
    public static long OWCA;
    public static long KILL;
    public static long BEACON;
    public static boolean ENABLE_KIT;
    public static boolean ENABLE_CREATE;
    public static boolean ENABLE_CASE;
    public static boolean ENABLE_EFEKT;
    public static boolean ENABLE_ITEMY;
    public static boolean ENABLE_WYMIANA;
    public static boolean ENABLE_PANEL;
    public static boolean ENABLE_DIAMOND;
    public static boolean ENABLE_NETHER;
    public static boolean ENABLE_WEDKA;
    public static boolean ENABLE_HOME;
    public static boolean ENABLE_GARDA;
    public static boolean ENABLE_WIADOMOSCI;
    public static String REGION_MASSAGE;
    public static String REGION_DAMAGE;
    public static int REGION_SPAWN;
    public static int REGION_PVP;
    public static boolean WL_ENABLE;
    public static String WL_REASON;
    public static long TURBO_DROP;
    public static long TURBO_EXP;
    public static List<String> BLOCKED_CMD_INCOMBAT;
    public static List<String> BLOCKED_CMD_INGUILD;
    public static List<String> BLOCKED_CMD_PERMS;
    public static List<String> WL_LIST;
    public static List<String> AUTOMSG;
    
    static {
    	Config.DATABASE_MODE = "mysql";
    	Config.DATABASE_TABLEPREFIX = "darkhard_core_";
    	Config.DATABASE_MYSQL_HOST = "mysql.titanaxe.com";
    	Config.DATABASE_MYSQL_PORT = 3306;
    	Config.DATABASE_MYSQL_USER = "srv106678";
    	Config.DATABASE_MYSQL_PASS = "8j37JBwp";
    	Config.DATABASE_MYSQL_NAME = "srv106678";
    	Config.DATABASE_SQLITE_NAME = "minecraft.db";
        Config.COST_CREATE_NORMAL = "DIAMOND_BLOCK:0-48:&4&lBLOKI DIAMENTOW;42:0-48:&4&lBLOKI ZELAZA;133:0-48:&4&lBLOKI EMERALDOW;152:0-48:&4&lBLOKI REDSTONE;46:0-64:&4&lTNT;47:0-64:&4&lBIBLIOTECZKI;170:0-8:&4&lSNOPY SIANA;";
        Config.COST_JOIN_NORMAL = "264:0-32:diamenty;265:0-32:zelazo;";
        Config.COST_LEADER_NORMAL = "388:0-16:emeraldy;";
        Config.COST_OWNER_NORMAL = "388:0-64:emeraldy";
        Config.COST_ENLARGE_NORMAL = "264:0-32:diamenty;";
        Config.COST_PROLONG_NORMAL = "264:0-64:diamenty;";
        Config.COST_CREATE_VIP = "46:0-28:&4&lTNT;47:0-41:&4&lBIBLIOTECZKI;341:0-14:&4&lKULE SZLAMU;DIAMOND_BLOCK:0-56:&4&lBLOKI DIAXOW;145:0-41:&4&lKOWADLA;GOLD_BLOCK:0-56:&4&lBLOKI ZLOTA;170:0-56:&4&lSNOPY SIANA;133:0-56:&4&lBLOKI EMERALDOW;152:0-56:&4&lBLOKI REDSTONE";
        Config.COST_CREATE_SVIP = "46:0-24:&4&lTNT;47:0-36:&4&lBIBLIOTECZKI;341:0-12:&4&lKULE SZLAMU;DIAMOND_BLOCK:0-48:&4&lBLOKI DIAXOW;145:0-36:&4&lKOWADLA;GOLD_BLOCK:0-48:&4&lBLOKI ZLOTA;170:0-48:&4&lSNOPY SIANA;133:0-48:&4&lBLOKI EMERALDOW;152:0-48:&4&lBLOKI REDSTONE;";
        Config.COST_CREATE_DARK = "46:0-16:&4&lTNT;47:0-24:&4&lBIBLIOTECZKI;341:0-8:&4&lKULE SZLAMU;DIAMOND_BLOCK:0-32:&4&lBLOKI DIAXOW;145:0-24:&4&lKOWADLA;GOLD_BLOCK:0-32:&4&lBLOKI ZLOTA;170:0-32:&4&lSNOPY SIANA;133:0-32:&4&lBLOKI EMERALDOW;152:0-32:&4&lBLOKI REDSTONE;";
        Config.CUBOID_SIZE_START = 20;
        Config.CUBOID_SIZE_MAX = 40;
        Config.CUBOID_SIZE_ADD = 2;
        Config.CUBOID_SIZE_BETWEEN = 20;
        Config.CUBOID_SPAWN_DISTANCE = 350;
        Config.REGION_SPAWN = 79;
        Config.REGION_PVP = 250;
        Config.REGION_MASSAGE = "&9&lERROR: &7Nie mozesz edytowac terenu spawna";
        Config.REGION_DAMAGE = "&9&lERROR: &7Nie mozesz atakowac garczy na spawnie";
        Config.PROLONG_START = 7;
        Config.PROLONG_MAX = 1;
        Config.PROLONG_ADD = 7;
        Config.SLOT = 500;
        Config.CHAT_FORMAT_GLOBAL = "{GUILD}{PREFIX}{PLAYER}&8: &r{SUFFIX}{MESSAGE}";
        Config.CHAT_FORMAT_ADMIN = "{PREFIX}{PLAYER} &8\u2192 &r{SUFFIX}{MESSAGE}";
        Config.CHAT_FORMAT_GUILD = "&8[&c{TAG}&8] ";
        Config.TAG_FORMAT = "&8[{COLOR}{TAG}&8] {COLOR}";
        Config.CHAT_SLOWMODE = 5;
        Config.TAG_COLOR_NOGUILD = "&r";
        Config.TAG_COLOR_FRIEND = "&a";
        Config.TAG_COLOR_ENEMY = "&c";
        Config.TAG_COLOR_ALLIANCE = "&6";
        Config.TNT_CUBOID_PROTECTION_ENABLED = true;
        Config.TNT_CUBOID_PROTECTION_HOWHOUR = 24;
        Config.TNT_CUBOID_EXPLODETIME = 120;
        Config.BORDER_WORLD_RADIUS = 2500;
        Config.BORDER_NETHERWORLD_RADIUS = 350;
        Config.TNT_OD = 23;
        Config.TNT_DO = 12;
        Config.BLOCKED_BUILT_INCOMBAT = true;
        Config.LVL = 1;
        Config.STONE = 0L;
        Config.LOWIENIE = 0L;
        Config.OWCA = 0L;
        Config.KILL = 0L;
        Config.BEACON = 0L;
        Config.TURBO_DROP = 0L;
        Config.TURBO_EXP = 0L;
        Config.LIFE_START = 3;
        Config.LIFE_COOLDOWN = 24;
        Config.LINIA_SIZE_WORLD = 76;
        Config.LIMIT_KOX = 1;
        Config.LIMIT_PEARL = 3;
        Config.LIMIT_REFILE = 16;
        Config.LIMIT_SNOW = 16;
        Config.LIMIT_ICE = 16;
        Config.LIMIT_ARROW = 24;
        Config.ENABLE_CREATE = true;
        Config.ENABLE_KIT = true;
        Config.ENABLE_CASE = true;
        Config.ENABLE_EFEKT = true;
        Config.ENABLE_ITEMY = true;
        Config.ENABLE_WYMIANA = true;
        Config.ENABLE_PANEL = true;
        Config.ENABLE_DIAMOND = true;
        Config.ENABLE_NETHER = false;
        Config.ENABLE_WEDKA = false;
        Config.ENABLE_HOME = true;
        Config.ENABLE_GARDA = true;
        Config.ENABLE_WIADOMOSCI = true;
        Config.WL_ENABLE = true;
        Config.BLOCKED_CMD_INCOMBAT = Arrays.asList("spawn", "home", "depozyt", "sethome", "tpa", "tpaccept", "tpdeny", "repair", "workbench", "ec", "baza", "ustawbaza", "sklep", "kit", "repair all", "repairall", "crafting", "craftingi", "schowek", "stone", "marmur", "staty", "statystyki", "sethome", "wb", "enderchest");
        Config.BLOCKED_CMD_INGUILD = Arrays.asList("home", "sethome", "tpa", "tpaccept", "tpdeny", "baza", "ustawbaza");
        Config.BLOCKED_CMD_PERMS = Arrays.asList("tpaccept", "tpahere");
        Config.WL_LIST = Arrays.asList("Virtual343");
        Config.WL_REASON = "Whitelist on";
        Config.AUTOMSG = new ArrayList<String>();
    }
    
    public static void loadConfig() {
        try {
            ServerPlugin.getPlugin().saveDefaultConfig();
            final FileConfiguration c = ServerPlugin.getPlugin().getConfig();
            Field[] fields;
            for (int length = (fields = Config.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                if (c.isSet("config." + f.getName().toLowerCase().replace("_", "."))) {
                    f.set(null, c.get("config." + f.getName().toLowerCase().replace("_", ".")));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void saveConfig() {
        try {
            final FileConfiguration c = ServerPlugin.getPlugin().getConfig();
            Field[] fields;
            for (int length = (fields = Config.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                c.set("config." + f.getName().toLowerCase().replace("_", "."), f.get(null));
            }
            ServerPlugin.getPlugin().saveConfig();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void reloadConfig() {
    	ServerPlugin.getPlugin().reloadConfig();
        loadConfig();
        saveConfig();
    }
}
