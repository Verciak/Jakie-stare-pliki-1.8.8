// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import pl.virtual.api.API.Config;
import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.commands.CommandManager;
import pl.virtual.api.commands.cmd.*;
import pl.virtual.api.commands.guild.AllyCommand;
import pl.virtual.api.commands.guild.CreateCommand;
import pl.virtual.api.commands.guild.DeleteCommand;
import pl.virtual.api.commands.guild.EnlargeCommand;
import pl.virtual.api.commands.guild.GuildAdminCommand;
import pl.virtual.api.commands.guild.GuildCoordinateCommand;
import pl.virtual.api.commands.guild.GuildHelpCommand;
import pl.virtual.api.commands.guild.GuildHomeCommand;
import pl.virtual.api.commands.guild.GuildSetHomeCommand;
import pl.virtual.api.commands.guild.InfoCommand;
import pl.virtual.api.commands.guild.InviteCommand;
import pl.virtual.api.commands.guild.ItemyCommand;
import pl.virtual.api.commands.guild.JoinCommand;
import pl.virtual.api.commands.guild.LeaderCommand;
import pl.virtual.api.commands.guild.LeaveCommand;
import pl.virtual.api.commands.guild.OwnerCommand;
import pl.virtual.api.commands.guild.ProlongCommand;
import pl.virtual.api.commands.guild.PvpCommand;
import pl.virtual.api.commands.guild.WyrzucCommand;
import pl.virtual.api.data.base.Combat;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.gui.PermsGui;
import pl.virtual.api.listeners.AnvilListener;
import pl.virtual.api.listeners.AppleListener;
import pl.virtual.api.listeners.AsyncPlayerChatListener;
import pl.virtual.api.listeners.BlockCraftingListener;
import pl.virtual.api.listeners.BlockDebilListener;
import pl.virtual.api.listeners.BlokiListener;
import pl.virtual.api.listeners.BlokowanieListener;
import pl.virtual.api.listeners.BorderMapListener;
import pl.virtual.api.listeners.BoyListener;
import pl.virtual.api.listeners.CenterExplodeListener;
import pl.virtual.api.listeners.ChatGuildsListener;
import pl.virtual.api.listeners.CheckLoginListener;
import pl.virtual.api.listeners.DebilCrashListener;
import pl.virtual.api.listeners.DropBlockBreakListener;
import pl.virtual.api.listeners.EfektListener;
import pl.virtual.api.listeners.EnableListener;
import pl.virtual.api.listeners.EnchantyListener;
import pl.virtual.api.listeners.EntityDamageByEntityListener;
import pl.virtual.api.listeners.EntityDamageListener;
import pl.virtual.api.listeners.EntityDeathListener;
import pl.virtual.api.listeners.EventyListener;
import pl.virtual.api.listeners.GardaListener;
import pl.virtual.api.listeners.GrupoweListener;
import pl.virtual.api.listeners.GuildExplodeListener;
import pl.virtual.api.listeners.InCommbatInGuildListener;
import pl.virtual.api.listeners.InventoryClickListener;
import pl.virtual.api.listeners.InventoryCloseListener;
import pl.virtual.api.listeners.InventoryDragListener;
import pl.virtual.api.listeners.InventoryListener;
import pl.virtual.api.listeners.KilofListener;
import pl.virtual.api.listeners.MarmurListener;
import pl.virtual.api.listeners.Nether;
import pl.virtual.api.listeners.PermsListener;
import pl.virtual.api.listeners.PhysicsWaterAndLavaListener;
import pl.virtual.api.listeners.PlayerCommandPreprocessListener;
import pl.virtual.api.listeners.PlayerDeathListener;
import pl.virtual.api.listeners.PlayerInteractEntityListener;
import pl.virtual.api.listeners.PlayerInteractListener;
import pl.virtual.api.listeners.PlayerJoinListener;
import pl.virtual.api.listeners.PlayerMoveListener;
import pl.virtual.api.listeners.PlayerQuitJoinListener;
import pl.virtual.api.listeners.RegionListener;
import pl.virtual.api.listeners.SignChangeListener;
import pl.virtual.api.listeners.TakeCrystalListener;
import pl.virtual.api.listeners.TntEvent;
import pl.virtual.api.listeners.TurboDropListener;
import pl.virtual.api.listeners.VillagerListener;
import pl.virtual.api.listeners.ZwojListener;
import pl.virtual.api.listeners.action.BlockBreakListener;
import pl.virtual.api.listeners.action.BlockPlaceListener;
import pl.virtual.api.listeners.action.BorderBreakListener;
import pl.virtual.api.listeners.action.BorderPlaceListener;
import pl.virtual.api.listeners.action.PlayerBucketEmptyListener;
import pl.virtual.api.listeners.action.PlayerBucketFillListener;
import pl.virtual.api.managers.BanIPManager;
import pl.virtual.api.managers.BanManager;
import pl.virtual.api.managers.CombatManager;
import pl.virtual.api.managers.DropFile;
import pl.virtual.api.managers.DropManager;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.MuteManager;
import pl.virtual.api.managers.NameTagManager;
import pl.virtual.api.managers.QuestManager;
import pl.virtual.api.managers.TimerManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.managers.UprManager;
import pl.virtual.api.managers.WarpManager;
import pl.virtual.api.mysql.store.Store;
import pl.virtual.api.mysql.store.modes.StoreMode;
import pl.virtual.api.mysql.store.modes.StoreMySQL;
import pl.virtual.api.mysql.store.modes.StoreSQLITE;
import pl.virtual.api.tasks.AbbysTask;
import pl.virtual.api.tasks.AutoMsgTask;
import pl.virtual.api.tasks.CheckValidityTask;
import pl.virtual.api.tasks.CombatTask;
import pl.virtual.api.tasks.LimitTask;
import pl.virtual.api.tasks.PlayerTask;
import pl.virtual.api.tasks.TabFreshRunnable;
import pl.virtual.api.tasks.WeatherTask;
import pl.virtual.api.utils.CraftingUtil;
import pl.virtual.api.utils.Logger;
import pl.virtual.api.utils.Ticking;

public class ServerPlugin extends JavaPlugin
{
	
    public static List<String> names;
    
    static {
    	ServerPlugin.names = new ArrayList<String>();
    }
    
	
    private static ServerPlugin plugin;
    private static Store store;
    private static PluginManager pluginManager;
	private static ServerPlugin inst;
	private PermsGui permsgui;
	private UprManager uprManager;

    public void onLoad() {
        ServerPlugin.plugin = this;
    }
    
    public static ServerPlugin getInst() {
        return ServerPlugin.inst;
    }
    
    
    public void onEnable() {

        for (final Player p : Bukkit.getOnlinePlayers()) {
            final Combat c = CombatManager.getCombat(p);
            if (c == null) {
                CombatManager.createCombat(p);
            }
        }
        Config.reloadConfig();
        Lang.reloadLang();
        BorderMapListener.setBorder();
        new Ticking().start();
        this.registerDatabase();
        this.registerManager();
        this.registerListener();
        this.registerTasks();
        registerCommand();
        CraftingUtil.registerRecipe();
    }		
    
    
    public void onDisable() { 
        Bukkit.getScheduler().cancelTasks((Plugin)this);
        final ResultSet rs = getStore().query("SELECT * FROM `{P}guilds`");
        try {
            while (rs.next()) {
                final Guild g = GuildManager.getGuild(rs.getNString(0));
                getStore().update(false, "UPDATE `{P}guilds` SET `hp` = '" + g.getHp() + "' WHERE `tag` ='" + g.getTag() + "';");
            }
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        for (final Player p : Bukkit.getOnlinePlayers()) {
            CombatManager.removeCombat(p);
        }
        Bukkit.savePlayers();
        for (final World w : Bukkit.getWorlds()) {
            w.save();
        }
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        if (ServerPlugin.store != null && ServerPlugin.store.isConnected()) {
            ServerPlugin.store.disconnect();
        }
        ServerPlugin.plugin = null;
    }
    
    
    
    
    public static ServerPlugin getPlugin() {
        return ServerPlugin.plugin;
    }
    
    protected boolean registerDatabase() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("______   ___  ______  _   __ _   _   ___  ______ ______    ______  _    ");
        System.out.println("|  _  \\ / _ \\ | ___ \\| | / /| | | | / _ \\ | ___ \\|  _  \\   | ___ \\| | ");
        System.out.println("| | | |/ /_\\ \\| |_/ /| |/ / | |_| |/ /_\\ \\| |_/ /| | | |   | |_/ /| |    ");
        System.out.println("| | | ||  _  ||    / |    \\ |  _  ||  _  ||    / | | | |   |  __/ | |    ");
        System.out.println("| |/ / | | | || |\\ \\ | |\\  \\| | | || | | || |\\ \\ | |/ /  _ | |    | |____");
        System.out.println("|___/  \\_| |_/\\_| \\_|\\_| \\_/\\_| |_/\\_| |_/\\_| \\_||___/  (_)\\_|    \\_____/");
        System.out.println(" ");
        switch (StoreMode.getByName(Config.DATABASE_MODE)) {
            case MYSQL: {
                ServerPlugin.store = new StoreMySQL(Config.DATABASE_MYSQL_HOST, Config.DATABASE_MYSQL_PORT, Config.DATABASE_MYSQL_USER, Config.DATABASE_MYSQL_PASS, Config.DATABASE_MYSQL_NAME, Config.DATABASE_TABLEPREFIX);
                break;
            }
            case SQLITE: {
                ServerPlugin.store = new StoreSQLITE(Config.DATABASE_SQLITE_NAME, Config.DATABASE_TABLEPREFIX);
                break;
            }
            default: {
                Logger.warning("Value of databse mode is not valid! Using SQLITE as database!");
                ServerPlugin.store = new StoreSQLITE(Config.DATABASE_SQLITE_NAME, Config.DATABASE_TABLEPREFIX);
                break;
            }
        }
        final boolean conn = ServerPlugin.store.connect();
        if (conn) {  
        	ServerPlugin.store.update(true, "CREATE TABLE IF NOT EXISTS `{P}quest` (" + ((ServerPlugin.store.getStoreMode() == StoreMode.MYSQL) ? "`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + "`name` varchar(32) NOT NULL,`heads` int(11) NOT NULL);");
        	ServerPlugin.store.update(true, "CREATE TABLE IF NOT EXISTS `{P}perms` (" + ((ServerPlugin.store.getStoreMode() == StoreMode.MYSQL) ? "`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + "`name` varchar(32) NOT NULL,`break_blocks` int(1) NOT NULL,`place_blocks` int(1) NOT NULL,`place_tnt` int(1) NOT NULL,`break_beacon` int(1) NOT NULL,`teleport_players` int(1) NOT NULL,`open_chest` int(1) NOT NULL,`spilling_water` int(1) NOT NULL,`pvp` int(1) NOT NULL,`enlarge` int(1) NOT NULL,`invite` int(1) NOT NULL,`prolong` int(1) NOT NULL,`perms` int(1) NOT NULL);");
            ServerPlugin.store.update(true, "CREATE TABLE IF NOT EXISTS `{P}users` (" + ((ServerPlugin.store.getStoreMode() == StoreMode.MYSQL) ? "`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + "`name` varchar(32) NOT NULL,`points` int(11) NOT NULL,`coins` int(32) NOT NULL, `kills` int(11) NOT NULL, `deaths` int(11) NOT NULL, `asyst` int(11) NOT NULL, `wykstone` int(32) NOT NULL, `logout` int(11) NOT NULL, `kox` int(11) NOT NULL, `snow` int(11) NOT NULL, `ice` int(11) NOT NULL, `arrow` int(11) NOT NULL, `koxeat` int(11) NOT NULL, `refil` int(11) NOT NULL, `refileat` int(11) NOT NULL, `perly` int(11) NOT NULL, `perlycyk` int(11) NOT NULL, `strzaly` int(11) NOT NULL, `jajopen` int(11) NOT NULL, `zdrapkaopen` int(11) NOT NULL, `cobblexopen` int(11) NOT NULL, `join` int(11) NOT NULL, `time` bigint(32) NOT NULL, `timelast` bigint(22) NOT NULL, " + "`firstIP` varchar(64) NOT NULL, `lastIP` varchar(64) NOT NULL, `firstJoin` bigint(32) NOT NULL, `kit_start` bigint(64) NOT NULL, `kit_yt` bigint(22) NOT NULL, `kit_tw` bigint(22) NOT NULL, " + "`kit_vip` bigint(22) NOT NULL, `kit_svip` bigint(22) NOT NULL, `turboDrop` bigint(32) NOT NULL, `turboExp` bigint(32) NOT NULL, `home` varchar(255) NOT NULL, `lastKill` varchar(32) NOT NULL, `lastKillTime` bigint(22) NOT NULL, `god` int(1) NOT NULL, `lvl` int(11) NOT NULL, `exp` int(32) NOT NULL);");            
            ServerPlugin.store.update(true, "CREATE TABLE IF NOT EXISTS `{P}guilds` (" + ((ServerPlugin.store.getStoreMode() == StoreMode.MYSQL) ? "`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + "`tag` varchar(5) NOT NULL, `name` varchar(32) NOT NULL, `description` varchar(64) NOT NULL, `owner` varchar(64) NOT NULL, `leader` varchar(64) NOT NULL, `cuboidX` int(11) NOT NULL, `cuboidZ` int(11) NOT NULL, `cuboidSize` int(11) NOT NULL, `hp` int(11) NOT NULL, " + "`hpLastAttack` bigint(22) NOT NULL, `life` int(11) NOT NULL, " + "`lifeLastAttack` bigint(22) NOT NULL, `prolong` bigint(22) NOT NULL, `pvp` int(2) NOT NULL, `createTime` bigint(22) NOT NULL, `homeX` double NOT NULL, `homeY` double NOT NULL, `homeZ` double NOT NULL, `ally` varchar(255) NOT NULL, `points` int(11) NOT NULL, `sojusz` int(11) NOT NULL, `coins` int(11) NOT NULL, `kills` int(11) NOT NULL, `deaths` int(11) NOT NULL, `turboDrop` bigint(22) NOT NULL, `turboExp` bigint(22) NOT NULL, `exp` int(11) NOT NULL, `pvpAlly` int(1) NOT NULL);");
            ServerPlugin.store.update(true, "CREATE TABLE IF NOT EXISTS `{P}guilds` (" + ((ServerPlugin.store.getStoreMode() == StoreMode.MYSQL) ? "`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + "`tag` varchar(5) NOT NULL, `name` varchar(32) NOT NULL, `description` varchar(64) NOT NULL, `owner` varchar(64) NOT NULL, `leader` varchar(64) NOT NULL, `cuboidX` int(11) NOT NULL, `cuboidZ` int(11) NOT NULL, `cuboidSize` int(11) NOT NULL, `hp` int(11) NOT NULL, " + "`hpLastAttack` bigint(22) NOT NULL, `life` int(11) NOT NULL, " + "`lifeLastAttack` bigint(22) NOT NULL, `prolong` bigint(22) NOT NULL, `pvp` int(2) NOT NULL, `createTime` bigint(22) NOT NULL, `homeX` double NOT NULL, `homeY` double NOT NULL, `homeZ` double NOT NULL, `ally` varchar(255) NOT NULL, `points` int(11) NOT NULL, `sojusz` int(11) NOT NULL, `coins` int(11) NOT NULL, `kills` int(11) NOT NULL, `deaths` int(11) NOT NULL, `turboDrop` bigint(22) NOT NULL, `turboExp` bigint(22) NOT NULL, `exp` int(11) NOT NULL, `pvpAlly` int(1) NOT NULL);");
            ServerPlugin.store.update(true, "CREATE TABLE IF NOT EXISTS `{P}members` (" + ((ServerPlugin.store.getStoreMode() == StoreMode.MYSQL) ? "`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + "`name` varchar(32) NOT NULL,`tag` varchar(5) NOT NULL);");
            ServerPlugin.store.update(true, "CREATE TABLE IF NOT EXISTS `{P}bans` (" + ((ServerPlugin.store.getStoreMode() == StoreMode.MYSQL) ? "`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + "`name` varchar(32) NOT NULL,`time` bigint(22) NOT NULL, `reason` text NOT NULL, `admin` varchar(32) NOT NULL, `start` BIGINT(22) NOT NULL);");
            ServerPlugin.store.update(true, "CREATE TABLE IF NOT EXISTS `{P}bansip` (" + ((ServerPlugin.store.getStoreMode() == StoreMode.MYSQL) ? "`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + "`ip` varchar(32) NOT NULL,`time` bigint(22) NOT NULL, `reason` text NOT NULL, `admin` varchar(32) NOT NULL, `start` BIGINT(22) NOT NULL);");
            ServerPlugin.store.update(true, "CREATE TABLE IF NOT EXISTS `{P}mutes` (" + ((ServerPlugin.store.getStoreMode() == StoreMode.MYSQL) ? "`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + "`name` varchar(32) NOT NULL,`time` bigint(22) NOT NULL, `reason` text NOT NULL, `admin` varchar(32) NOT NULL, `start` BIGINT(22) NOT NULL);");
            ServerPlugin.store.update(true, "CREATE TABLE IF NOT EXISTS `{P}backups` (" + ((ServerPlugin.store.getStoreMode() == StoreMode.MYSQL) ? "`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + "`name` varchar(32) NOT NULL,`time` bigint(22) NOT NULL, `killer` varchar(32) NOT NULL, `ping` int(11) NOT NULL, `inventory` text NOT NULL, `armor` text NOT NULL, `enderchest` text NOT NULL);");
            ServerPlugin.store.update(true, "CREATE TABLE IF NOT EXISTS `{P}warp` (" + ((ServerPlugin.store.getStoreMode() == StoreMode.MYSQL) ? "`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + "`name` varchar(32) NOT NULL,`location` text NOT NULL, `pex` varchar(32) NOT NULL NOT NULL);");
            ServerPlugin.store.update(true, "CREATE TABLE IF NOT EXISTS `{P}deathbans` (" + ((ServerPlugin.store.getStoreMode() == StoreMode.MYSQL) ? "`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT," : "`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,") + "`name` varchar(32) NOT NULL,`mode` int(1) NOT NULL, `time` bigint(11) NOT NULL NOT NULL);");
            return conn;
        }
        return conn;
    }
    
    public static void registerCommand(final Command command) {
        CommandManager.register(command);
    }
    
    public static void registerListener(final Plugin plugin, final Listener... listeners) {
        if (ServerPlugin.pluginManager == null) {
            ServerPlugin.pluginManager = Bukkit.getPluginManager();
        }
        for (final Listener listener : listeners) {
            ServerPlugin.pluginManager.registerEvents(listener, plugin);
        }
    }
    
    public static Store getStore() {
        return ServerPlugin.store;
    }
    
    public static void registerCommand() {
        registerCommand(new AEventCommand());
        registerCommand(new ListCommand());
        registerCommand(new RankingCommand());
        registerCommand(new BlokiCommand());
        registerCommand(new GodCommand());
        registerCommand(new BanIPCommand());
        registerCommand(new BanCommand());
        registerCommand(new UnBanCommand());
        registerCommand(new MoreCommand());
        registerCommand(new UnBanIpCommand());
        registerCommand(new ChatCommand());
        registerCommand(new GamemodeCommand());
        registerCommand(new FlyCommand());
        registerCommand(new SpawnCommand());
        registerCommand(new SetSpawnCommand());
        registerCommand(new KitCommand());
        registerCommand(new TpacceptCommand());
        registerCommand(new TpdenyCommmand());
        registerCommand(new ClearCommand());
        registerCommand(new EnchantCommand());
        registerCommand(new PotkiCommand());
        registerCommand(new GcCommand());
        registerCommand(new SocialSpyCommand());
        registerCommand(new ItemCommand());
        registerCommand(new GiveCommand());
        registerCommand(new HealCommand());
        registerCommand(new HelpOpCommand());
        registerCommand(new GuildCoordinateCommand());
        registerCommand(new HomeCommand());
        registerCommand(new KickAllCommand());
        registerCommand(new KickCommand());
        registerCommand(new WyrzucCommand());
        registerCommand(new GCommand());
        registerCommand(new SetHomeCommand());
        registerCommand(new WalkaCommand());
        registerCommand(new RepairCommand());
        registerCommand(new ResetRankingCommand());
        registerCommand(new TellCommand());
        registerCommand(new ReplyCommand());
        registerCommand(new SlotCommand());
        registerCommand(new OtchlanCommand());
        registerCommand(new StpCommand());
        registerCommand(new VipCommand());
        registerCommand(new SVipCommand());
        registerCommand(new MVipCommand());
        registerCommand(new YouTubeCommand());
        registerCommand(new CraftCommand());
        registerCommand(new SmietnikCommand());
        registerCommand(new BroadcastCommand());
        registerCommand(new ZwojCommand());
        registerCommand(new TpaCommand());
        registerCommand(new WhiteListCommand());
        registerCommand(new VipTurboCommand());
        registerCommand(new SchowekCommand());
        registerCommand(new StoneCommand());
        registerCommand(new VanishCommand());
        registerCommand(new NaprawKilofCommand());
        registerCommand(new TeleportCommand());
        registerCommand(new RenameCommand());
        registerCommand(new IsCommand());
        registerCommand(new MarmurCommand());
        registerCommand(new MarmurDajCommand());
        registerCommand(new WorkBenchCommand());
        registerCommand(new SpeedCommand());
        registerCommand(new LevelCommand());
        registerCommand(new TurboCommand());
        registerCommand(new EnableCommand());
        registerCommand(new StatsCommand());
        registerCommand(new DayCommand());
        registerCommand(new NightCommand());
        registerCommand(new NetherCommand());
        registerCommand(new GuildAdminCommand());
        registerCommand(new AllyCommand());
        registerCommand(new CreateCommand());
        registerCommand(new DeleteCommand());
        registerCommand(new EnlargeCommand());
        registerCommand(new InfoCommand());
        registerCommand(new InviteCommand());
        registerCommand(new JoinCommand());
        registerCommand(new KickCommand());
        registerCommand(new ItemyCommand());
        registerCommand(new LeaderCommand());
        registerCommand(new OwnerCommand());
        registerCommand(new ProlongCommand());
        registerCommand(new PvpCommand());
        registerCommand(new AutoMsgCommand());
        registerCommand(new LeaveCommand());
        registerCommand(new HelpCommand());
        registerCommand(new GuildHelpCommand());
        registerCommand(new PaczkaCommand());
        registerCommand(new InvCommand());
        registerCommand(new BorderCommand());
        registerCommand(new WiadomosciCommand());
        registerCommand(new IgnoreCommand());
        registerCommand(new MuteCommand());
        registerCommand(new SetWarpCommand());
        registerCommand(new GuildSetHomeCommand());
        registerCommand(new GuildHomeCommand());
        registerCommand(new WarpCommand());
        registerCommand(new UnMuteCommand());
        registerCommand(new AnticheatCommand());
        registerCommand(new StatystykiCommand());
        registerCommand(new ZnakiCommand());
        registerCommand(new TpahereCommand());
        registerCommand(new ZrobrangiCommand());
        registerCommand(new PermsCommand());
        registerCommand(new aPermsCommand());
    }
    
    public void registerTasks() {
        new CheckValidityTask().runTaskTimer((Plugin)this, 2400L, 2400L);
        new CombatTask().runTaskTimerAsynchronously((Plugin)this, 40L, 20L);
        new LimitTask().runTaskTimer((Plugin)this, 200L, 200L);
        new WeatherTask().runTaskTimer((Plugin)this, 60L, 60L);
        new AutoMsgTask().runTaskTimerAsynchronously((Plugin)this, 1200L, 1200L);
        new TabFreshRunnable().runTaskTimerAsynchronously((Plugin)this, 500L, 500L);
        new PlayerTask(this).runTaskTimerAsynchronously((Plugin)this, 200L, 100L);
        new AbbysTask();
        AbbysTask.startTask();
    }
    
    public static long getTicks(final String type) {
        if (type.equalsIgnoreCase("regenerate")) {
            final long tick = 35;
            return tick;
        }
        if (type.equalsIgnoreCase("generate")) {
            final long tick = 35;
            return tick;
        }
        final long error = 0L;
        return error;
    }
    
    public void registerManager() {
    	System.out.println(" ");
        NameTagManager.enable();
        DropFile.saveDefaultConfig();
        DropManager.setup();
        UserManager.loadUsers();
        GuildManager.loadGuilds();
        BanManager.loadBans();
        BanIPManager.loadBans();
        MuteManager.loadMutes();
        WarpManager.loadWarp();
        UprManager.loadUsers();
        QuestManager.loadQuest();
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
    }
    
    public PermsGui getpermsgui() {
        return this.permsgui;
    }
    
    public UprManager getUprManager() {
        return this.uprManager;
    }
    
    public void registerListener() {
        registerListener((Plugin)this,
                new InventoryClickListener(),
                new VillagerListener(),
                new InventoryDragListener(),
                new PlayerInteractEntityListener(),
                new InventoryCloseListener(),


                new AppleListener(),
                new PermsListener(),
                new BlokiListener(),
                new RegionListener(),
                new EnableListener(),
                new GardaListener(),
                new TurboDropListener(),
                new ZwojListener(),
                new CenterExplodeListener(),
                new EfektListener(),
                new AnvilListener(),
                new KilofListener(),
                new EnchantyListener(),


                new BlockDebilListener(),
                new DebilCrashListener(),
                new PlayerCommandPreprocessListener(),
                new PlayerQuitJoinListener(),
                new PlayerJoinListener(),
                new TimerManager(),
                new AsyncPlayerChatListener(),
                new ChatGuildsListener(),
                new PlayerMoveListener(),
                new GrupoweListener(),
                new BlockBreakListener(),
                new BlockPlaceListener(),
                new PlayerBucketEmptyListener(),
                new PlayerBucketFillListener(),
                new GuildExplodeListener(),
                new EntityDamageByEntityListener(),
                new EntityDamageListener(),
                new PlayerDeathListener(),
                new InCommbatInGuildListener(),
                new TakeCrystalListener(),
                new CheckLoginListener(),
                new Nether(),
                new BoyListener(),
                new PlayerInteractListener(),
                new DropBlockBreakListener(),
                new EntityDeathListener(),
                new BorderMapListener(),
                new TntEvent(),
                new PhysicsWaterAndLavaListener(),
                new BlokowanieListener(),
                new EventyListener(),
                new BorderBreakListener(),
                new BorderPlaceListener(),
                new BlockCraftingListener(),
                new InventoryListener(),
                new SignChangeListener(),
                new MarmurListener());
    }
}
