package pl.virtual.api.data.base.user;

import org.bukkit.entity.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.trade.TradeOptions;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemSerializer;

import java.util.*;
import java.sql.*;
import org.bukkit.*;

public class User
{
    private String name;
    private final TradeOptions tradeOptions;
    private int points;
    private int kills;
    private int deaths;
    private int coins;
    private int asyst;
    private int wykstone;
    private int logout;
    private int kox;
    private int snow;
    private int ice;
    private int arrow;
    private int koxeat;
    private int refil;
    private int refileat;
    private int perly;
    private int perlycyk;
    private int strzaly;
    private int jajopen;
    private int zdrapkaopen;
    private int cobblexopen;
    private int join;
    private long lastChat;
    private String firstIP;
    private String lastIP;
    private long firstJoin;
    private long kit_mieso;
    private long kit_start;
    private long kit_tw;
    private long kit_yt;
    private long kit_vip;
    private long kit_svip;
    private long turboDrop;
    private long turboExp;
    private List<Player> ignoreTell;
    private List<Player> ignoreTpa;
    private List<Player> tpa;
    private List<Player> tpahere;
    private String home;
    private boolean helpop;
    private long lastHelpop;
    private long lastPearl;
    private boolean vanish;
    private boolean ring;
    private boolean sidebar;
    private boolean autoMessages;
    private boolean dropMessages;
    private boolean privateMessages;
    private boolean tp;
    private boolean eggsMessages;
    private boolean cratesMessages;
    private boolean scratchesMessages;
    private boolean DeathMessages;
    private boolean ShopMessages;
    private boolean EventMessages;
    private String lastKill;
    private long lastKillTime;
    private boolean god;
    private int lvl;
    private int exp;
    private long time;
    private long timelast;
    private Integer antyLogout;
	private int lod;
	
    public User(final Player p) {
        this.name = p.getName();
        this.tradeOptions = new TradeOptions(this);
        this.points = 500;
        this.kills = 0;
        this.deaths = 0;
        this.coins = 0;
        this.asyst = 0;
        this.wykstone = 0;
        this.logout = 0;
        this.kox = 0;
        this.snow = 0;
        this.arrow = 0;
        this.ice = 0;
        this.koxeat = 0;
        this.refil = 0;
        this.refileat = 0;
        this.perly = 0;
        this.perlycyk = 0;
        this.strzaly = 0;
        this.strzaly = 0;
        this.jajopen = 0;
        this.zdrapkaopen = 0;
        this.cobblexopen = 0;
        this.join = 0;
        this.firstIP = p.getAddress().getAddress().getHostAddress();
        this.lastIP = p.getAddress().getAddress().getHostAddress();
        this.firstJoin = System.currentTimeMillis();
        this.kit_mieso = 0L;
        this.kit_start = 0L;
        this.kit_tw = 0L;
        this.kit_yt = 0L;
        this.kit_vip = 0L;
        this.kit_svip = 0L;
        this.home = ChatUtil.locToString(0.0, 0.0, 0.0);
        this.lastKill = "-";
        this.lastKillTime = 0L;
        this.lastChat = 0L;
        this.god = false;
        this.lvl = 1;
        this.exp = 0;
        this.turboDrop = 0L;
        this.turboExp = 0L;
        this.ignoreTell = new ArrayList<Player>();
        this.ignoreTpa = new ArrayList<Player>();
        this.tpa = new ArrayList<Player>();
        this.tpahere = new ArrayList<Player>();
        this.vanish = false;
        this.ring = true;
        this.sidebar = true;
        this.autoMessages = true;
        this.dropMessages = true;
        this.privateMessages = true;
        this.tp = false;
        this.eggsMessages = true;
        this.cratesMessages = true;
        this.scratchesMessages = true;
        this.DeathMessages = true;
        this.ShopMessages = true;
        this.EventMessages = true;
        this.helpop = true;
        this.lastHelpop = 0L;
        this.lastPearl = 0L;
        this.antyLogout = 0;
        this.time = System.currentTimeMillis();
        this.timelast = System.currentTimeMillis();
        this.insert();
    }
    
   
    public User(final String p) {
        this.name = p;
        this.tradeOptions = new TradeOptions(this);
        this.points = 500;
        this.kills = 0;
        this.deaths = 0;
        this.coins = 0;
        this.asyst = 0;
        this.wykstone = 0;
        this.logout = 0;
        this.kox = 0;
        this.snow = 0;
        this.arrow = 0;
        this.ice = 0;
        this.koxeat = 0;
        this.refil = 0;
        this.refileat = 0;
        this.perly = 0;
        this.perlycyk = 0;
        this.strzaly = 0;
        this.jajopen = 0;
        this.zdrapkaopen = 0;
        this.cobblexopen = 0;
        this.join = 0;
        this.firstIP = "-";
        this.lastIP = "-";
        this.firstJoin = 0L;
        this.kit_mieso = 0L;
        this.kit_start = 0L;
        this.kit_tw = 0L;
        this.kit_yt = 0L;
        this.kit_vip = 0L;
        this.kit_svip = 0L;
        this.home = ChatUtil.locToString(0.0, 0.0, 0.0);
        this.lastKill = "-";
        this.lastKillTime = 0L;
        this.lastChat = 0L;
        this.god = false;
        this.lvl = 1;
        this.exp = 0;
        this.turboDrop = 0L;
        this.turboExp = 0L;
        this.ignoreTell = new ArrayList<Player>();
        this.ignoreTpa = new ArrayList<Player>();
        this.tpa = new ArrayList<Player>();
        this.tpahere = new ArrayList<Player>();
        this.vanish = false;
        this.ring = true;
        this.sidebar = true;
        this.helpop = true;
        this.autoMessages = true;
        this.dropMessages = true;
        this.privateMessages = true;
        this.tp = false;
        this.eggsMessages = true;
        this.cratesMessages = true;
        this.scratchesMessages = true;
        this.DeathMessages = true;
        this.ShopMessages = true;
        this.EventMessages = true;
        this.lastHelpop = 0L;
        this.lastPearl = 0L;
        this.antyLogout = 0;
        this.time = System.currentTimeMillis();
        this.timelast = System.currentTimeMillis();
        this.insert();
    }
   
    public User(final ResultSet rs) throws SQLException {
        this.name = rs.getString("name");
        this.tradeOptions = new TradeOptions(this);
        this.points = rs.getInt("points");
        this.kills = rs.getInt("kills");
        this.deaths = rs.getInt("deaths");
        this.coins = rs.getInt("coins");
        this.asyst = rs.getInt("asyst");
        this.wykstone = rs.getInt("wykstone");
        this.logout = rs.getInt("logout");
        this.kox = rs.getInt("kox");
        this.snow = rs.getInt("snow");
        this.ice = rs.getInt("ice");
        this.arrow = rs.getInt("arrow");
        this.koxeat = rs.getInt("koxeat");
        this.refil = rs.getInt("refil");
        this.refileat = rs.getInt("refileat");
        this.perly = rs.getInt("perly");
        this.perlycyk = rs.getInt("perlycyk");
        this.strzaly = rs.getInt("strzaly");
        this.jajopen = rs.getInt("jajopen");
        this.zdrapkaopen = rs.getInt("zdrapkaopen");
        this.cobblexopen = rs.getInt("cobblexopen");
        this.join = rs.getInt("join");
        this.strzaly = rs.getInt("strzaly");
        this.strzaly = rs.getInt("strzaly");
        this.strzaly = rs.getInt("strzaly");
        this.firstIP = rs.getString("firstIP");
        this.lastIP = rs.getString("lastIP");
        this.firstJoin = rs.getLong("firstJoin");
        this.kit_mieso = 0L;
        this.kit_start = rs.getLong("kit_start");
        this.kit_tw = rs.getLong("kit_tw");
        this.kit_yt = rs.getLong("kit_yt");
        this.kit_vip = rs.getLong("kit_vip");
        this.kit_svip = rs.getLong("kit_svip");
        this.turboDrop = rs.getLong("turboDrop");
        this.turboExp = rs.getLong("turboExp");
        this.home = rs.getString("home");
        this.lastKill = rs.getString("lastKill");
        this.lastKillTime = rs.getLong("lastKillTime");
        this.lastChat = 0L;
        this.god = (rs.getInt("god") == 1);
        this.lvl = rs.getInt("lvl");
        this.exp = rs.getInt("exp");
        this.ignoreTell = new ArrayList<Player>();
        this.ignoreTpa = new ArrayList<Player>();
        this.tpa = new ArrayList<Player>();
        this.tpahere = new ArrayList<Player>();
        this.vanish = false;
        this.ring = true;
        this.sidebar = true;
        this.autoMessages = true;
        this.dropMessages = true;
        this.privateMessages = true;
        this.tp = false;
        this.eggsMessages = true;
        this.cratesMessages = true;
        this.scratchesMessages = true;
        this.DeathMessages = true;
        this.ShopMessages = true;
        this.EventMessages = true;
        this.helpop = true;
        this.lastHelpop = 0L;
        this.lastPearl = 0L;
        this.antyLogout = 0;
        this.time = rs.getLong("time");
        this.timelast = rs.getLong("time");
        
    }
    
	public void decreaseLogout(){
		this.antyLogout = this.antyLogout - 1;
	}
    
    public boolean isCombat() {
        return this.getLogoutTime() > 0;
    }
    
    public void hit() {
        this.antyLogout = 30;
    }
    
    public int getLogoutTime() {
        return this.antyLogout;
    }
    
    public void hit(final int hit) {
        this.antyLogout = hit;
    }
    
    public void resetLogout() {
        this.antyLogout = 0;
        
    }
    
    public TradeOptions getTradeOptions() {
        return this.tradeOptions;
    }
   
    public boolean isLastHelpop() {
        return this.getLastHelpop() > System.currentTimeMillis();
    }
   
    public boolean isLastPearl() {
        return this.getLastPearl() > System.currentTimeMillis();
    }
   
    public long getLastHelpop() {
        return this.lastHelpop;
    }
   
    public void setLastHelpop(final long lastHelpop) {
        this.lastHelpop = lastHelpop;
    }
   
    public long getTime() {
        return this.time;
    }
   
    public void setTime(final long time) {
        this.time = time;
    }
   
    public long getTimeLast() {
        return this.timelast;
    }
   
    public void setTimeLast(final long timelast) {
        this.timelast = timelast;
    }
   
    public long getLastPearl() {
        return this.lastPearl;
    }
   
    public boolean isEventMessages() {
        return this.EventMessages;
    }
   
    public void setEventMessages(final boolean ShopMessages) {
        this.EventMessages = ShopMessages;
    }
   
    public void setLastPearl(final long lastPearl) {
        this.lastPearl = lastPearl;
    }
   
    public List<Player> getIgnoreTell() {
        return this.ignoreTell;
    }
   
    public boolean isIgnoreTell(final Player p) {
        return this.ignoreTell.contains(p);
    }
   
    public void setIgnoreTell(final List<Player> ignoreTell) {
        this.ignoreTell = ignoreTell;
    }
   
    public void addIgnoreTell(final Player p) {
        this.ignoreTell.add(p);
    }
   
    public void removeIgnoreTell(final Player p) {
        this.ignoreTell.remove(p);
    }
   
    public List<Player> getIgnoreTpa() {
        return this.ignoreTpa;
    }
   
    public boolean isIgnoreTpa(final Player p) {
        return this.ignoreTpa.contains(p);
    }
   
    public void setIgnoreTpa(final List<Player> ignoreTpa) {
        this.ignoreTpa = ignoreTpa;
    }
   
    public void addIgnoreTpa(final Player p) {
        this.ignoreTpa.add(p);
    }
   
    public void removeIgnoreTpa(final Player p) {
        this.ignoreTpa.remove(p);
    }
   
    public boolean isSidebar() {
        return this.sidebar;
    }
   
    public void setSidebar(final boolean scoreboard) {
        this.sidebar = scoreboard;
    }
   
    public boolean isAutoMessages() {
        return this.autoMessages;
    }
    
    public boolean isDropMessages() {
        return this.dropMessages;
    }
   
    public boolean isRing() {
        return this.ring;
    }
   
    public void setRing(final boolean ring) {
        this.ring = ring;
    }
   
    public void setAutoMessages(final boolean autoMessages) {
        this.autoMessages = autoMessages;
    }
    
    public void setDropMessages(final boolean dropMessages) {
        this.dropMessages = dropMessages;
    }
   
    public boolean isPrivateMessages() {
        return this.privateMessages;
    }
   
    public void setPrivateMessages(final boolean privateMessages) {
        this.privateMessages = privateMessages;
    }
    
    public boolean isTp() {
        return this.tp;
    }
   
    public void setTp(final boolean tp) {
        this.tp = tp;
    }
   
    public boolean isEggsMessages() {
        return this.eggsMessages;
    }
   
    public void setEggsMessages(final boolean eggsMessages) {
        this.eggsMessages = eggsMessages;
    }
   
    public boolean isCratesMessages() {
        return this.cratesMessages;
    }
   
    public void setCratesMessages(final boolean cratesMessages) {
        this.cratesMessages = cratesMessages;
    }
   
    public boolean isScratchesMessages() {
        return this.scratchesMessages;
    }
   
    public void setScratchesMessages(final boolean DeathMessages) {
        this.scratchesMessages = DeathMessages;
    }
   
    public boolean isDeathMessages() {
        return this.DeathMessages;
    }
   
    public void setDeathMessages(final boolean DeathMessages) {
        this.DeathMessages = DeathMessages;
    }
   
    public boolean isShopMessages() {
        return this.ShopMessages;
    }
   
    public void setShopMessages(final boolean ShopMessages) {
        this.ShopMessages = ShopMessages;
    }
   
    public boolean isHelpop(final boolean b) {
        return this.helpop;
    }
   
    public void setHelpop(final boolean helpop) {
        this.helpop = helpop;
    }
   
    public List<Player> getTpahere() {
        return this.tpahere;
    }
   
    public void setTpahere(final List<Player> tpahere) {
        this.tpahere = tpahere;
    }
   
    public List<Player> getTpa() {
        return this.tpa;
    }
   
    public void setTpa(final List<Player> tpa) {
        this.tpa = tpa;
    }
   
    public String getName() {
        return this.name;
    }
   
    public void setName(final String name) {
        this.name = name;
    }
   
    public int getPoints() {
        return this.points;
    }
   
    public void setPoints(final int points) {
        this.points = points;
    }
   
    public int getKills() {
        return this.kills;
    }
   
    public void setKills(final int kills) {
        this.kills = kills;
    }
   
    public int getDeaths() {
        return this.deaths;
    }
   
    public void setDeaths(final int deaths) {
        this.deaths = deaths;
    }
   
    public int getAsyst() {
        return this.asyst;
    }
   
    public void setAsyst(final int asyst) {
        this.asyst = asyst;
    }
   
    public int getWykStone() {
        return this.wykstone;
    }
   
    public void setWykstone(final int wykstone) {
        this.wykstone = wykstone;
    }
    
    public int getKox() {
      return this.kox;
    }
    
    public int getSnow() {
        return this.snow;
    }
    
    public int getIce() {
        return this.ice;
    }
   
    public int getArrow() {
        return this.arrow;
    }
    
    public int getKoxeat() {
        return this.koxeat;
    }
   
    public void setKox(final int kox) {
        this.kox = kox;
    }
    
    public void setSnow(final int snow) {
        this.snow = snow;
    }
    
    public void setIce(final int ice) {
        this.ice = ice;
    }
    
    public void setArrow(final int arrow) {
        this.arrow = arrow;
    }
   
    public void setRefil(final int refil) {
        this.refil = refil;
    }
   
    public int getRefil() {
        return this.refil;
    }
    
    public int getlod() {
        return this.lod;
        
    }
    public int getRefileat() {
        return this.refileat;
    }
   
    public int getPerly() {
        return this.perly;
    }
   
    public int getPerlycyk() {
        return this.perlycyk;
    }
   
    public void setPerly(final int perly) {
        this.perly = perly;
    }
   
    public int getStrzaly() {
        return this.strzaly;
    }
   
    public int getJajopen() {
        return this.jajopen;
    }
   
    public int getZdrapkaopen() {
        return this.zdrapkaopen;
    }
   
    public int getCobblexopen() {
        return this.cobblexopen;
    }
   
    public int getLogout() {
        return this.logout;
    }
   
    public int getJoin() {
        return this.join;
    }
   
    public void setLogout(final int logout) {
        this.logout = logout;
    }
   
    public String getFirstIP() {
        return this.firstIP;
    }
    
    public void setFirstIP(final String firstIP) {
        this.firstIP = firstIP;
    }
   
    public String getLastIP() {
        return this.lastIP;
    }
   
    public void setLastIP(final String lastIP) {
        this.lastIP = lastIP;
    }
   
    public long getFirstJoin() {
        return this.firstJoin;
    }
   
    public void setFirstJoin(final long firstJoin) {
        this.firstJoin = firstJoin;
    }
   
    public long getKit_mieso() {
        return this.kit_mieso;
    }
   
    public void setKit_mieso(final long kit_mieso) {
        this.kit_mieso = kit_mieso;
    }
   
    public long getKit_start() {
        return this.kit_start;
    }
   
    public void setKit_start(final long kit_start) {
        this.kit_start = kit_start;
        ServerPlugin.getStore().update(false, "UPDATE `{P}users` SET `kit_start`='" + this.getKit_start() + "' WHERE `name`='" + this.getName() + "'");
    }
   
    public long getKit_tw() {
        return this.kit_tw;
    }
   
    public void setKit_tw(final long kit_tw) {
        this.kit_tw = kit_tw;
        ServerPlugin.getStore().update(false, "UPDATE `{P}users` SET `kit_tw` ='" + this.getKit_tw() + "' WHERE `name` ='" + this.getName() + "'");
    }
   
    public long getKit_yt() {
        return this.kit_yt;
    }
   
    public void setKit_yt(final long kit_yt) {
        this.kit_yt = kit_yt;
        ServerPlugin.getStore().update(false, "UPDATE `{P}users` SET `kit_yt` ='" + this.getKit_yt() + "' WHERE `name` ='" + this.getName() + "'");
    }
   
    public boolean isKit_yt() {
        return this.getKit_yt() > System.currentTimeMillis();
    }
   
    public boolean isKit_tw() {
        return this.getKit_tw() > System.currentTimeMillis();
    }
   
    public long getKit_vip() {
        return this.kit_vip;
    }
   
    public void setKit_vip(final long kit_vip) {
        this.kit_vip = kit_vip;
        ServerPlugin.getStore().update(false, "UPDATE `{P}users` SET `kit_vip`='" + this.getKit_vip() + "' WHERE `name`='" + this.getName() + "'");
    }
   
    public long getKit_svip() {
        return this.kit_svip;
    }
   
    public void setKit_svip(final long kit_svip) {
        this.kit_svip = kit_svip;
        ServerPlugin.getStore().update(false, "UPDATE `{P}users` SET `kit_svip`='" + this.getKit_svip() + "' WHERE `name`='" + this.getName() + "'");
    }
   
    public String getHome() {
        return this.home;
    }
   
    public Location getHomeLocation() {
        return ChatUtil.locFromString(this.getHome());
    }
   
    public void setHome(final Location home) {
        this.home = ChatUtil.locToString(home);
        ServerPlugin.getStore().update(false, "UPDATE `{P}users` SET `home` ='" + this.getHome() + "' WHERE `name` ='" + this.getName() + "'");
    }
   
    public Player getPlayer() {
        return Bukkit.getPlayer(this.getName());
    }
   
    public boolean isOnline() {
        return this.getPlayer() != null;
    }
   
    public String getLastKill() {
        return this.lastKill;
    }
   
    public void setLastKill(final String lastKill) {
        this.lastKill = lastKill;
    }
   
    public long getLastKillTime() {
        return this.lastKillTime;
    }
   
    public void setLastKillTime(final long lastKillTime) {
        this.lastKillTime = lastKillTime;
    }
   
    public boolean isChat() {
        return System.currentTimeMillis() > this.lastChat;
    }
   
    public void setLastChat(final long lastChat) {
        this.lastChat = lastChat;
    }
   
    public long getLastChat() {
        return this.lastChat;
    }
   
    public void addPoints(final int index) {
        this.points += index;
    }
   
    public void addKills(final int index) {
        this.kills += index;
    }
   
    public void addDeaths(final int index) {
        this.deaths += index;
    }
   
    public void addCoins(final int paramInt) {
        this.coins += paramInt;
    }
   
    public void addAsyst(final int index) {
        this.asyst += index;
    }
   
    public void addWykStone(final int index) {
        this.wykstone += index;
    }
   
    public void addKox(final int index) {
        this.kox += index;
    }
    
    public void addSnow(final int index) {
        this.snow += index;
    }
    
    public void addArrow(final int index) {
        this.arrow += index;
    }
    
    public void addIce(final int index) {
        this.ice += index;
    }
   
    public void addKoxeat(final int index) {
        this.koxeat += index;
    }
   
    public void addRefil(final int index) {
        this.refil += index;
    }
    
    public void addice(final int index) {
        this.refil += index;
    }
    
    public void addRefileat(final int index) {
        this.refileat += index;
    }
   
    public void addPerly(final int index) {
        this.perly += index;
    }
   
    public void addPerlycyk(final int index) {
        this.perlycyk += index;
    }
   
    public void addStrzaly(final int index) {
        this.strzaly += index;
    }
   
    public void addJajopen(final int index) {
        this.jajopen += index;
    }
   
    public void addZdrapkaopen(final int index) {
        this.zdrapkaopen += index;
    }
   
    public void addCobblexopen(final int index) {
        this.cobblexopen += index;
    }
   
    public void addJoin(final int index) {
        this.join += index;
    }
   
    public void addLogouts(final int index) {
        this.logout += index;
    }
   
    public void removePoints(final int index) {
        this.points -= index;
    }
   
    public void removeKills(final int index) {
        this.kills -= index;
    }
   
    public void removeDeaths(final int index) {
        this.deaths -= index;
    }
   
    public void removeCoins(final int paramInt) {
        this.coins -= paramInt;
    }
   
    public void removeAsyst(final int index) {
        this.asyst -= index;
    }
   
    public void removeWykStone(final int index) {
        this.wykstone -= index;
    }
   
    public void removeKox(final int index) {
        this.kox -= index;
    }
    
    public void removeSnow(final int index) {
        this.snow -= index;
    }
    
    public void removeIce(final int index) {
        this.ice -= index;
    }
    
    public void removeArrow(final int index) {
        this.arrow -= index;
    }
   
    public void removeRefil(final int index) {
        this.refil -= index;
    }
   
    public void removePerly(final int index) {
        this.perly -= index;
    }
    
    public void removelod(final int index) {
        this.perly -= index;    
        
    }
    public void removeLvl(final int index) {
        this.lvl -= index;
    }
   
    public boolean isGod() {
        return this.god;
    }
   
    public void setGod(final boolean god) {
        this.god = god;
    }
   
    public int getLvl() {
        return this.lvl;
    }
   
    public int getCoins() {
        return this.coins;
    }
   
    public void setCoins(final int coins) {
        this.coins = coins;
    }
   
    public void setLvl(final int lvl) {
        this.lvl = lvl;
        ServerPlugin.getStore().update(false, "UPDATE `{P}users` SET `lvl` ='" + this.getLvl() + "', `exp` ='" + this.getExp() + "' WHERE `name` ='" + this.getName() + "'");
    }
   
    public int getExp() {
        return this.exp;
    }
   
    public long getTurboDrop() {
        return this.turboDrop;
    }
   
    public void setTurboDrop(final long turboDrop) {
        this.turboDrop = turboDrop;
    }
   
    public long getTurboExp() {
        return this.turboExp;
    }
   
    public void setTurboExp(final long turboExp) {
        this.turboExp = turboExp;
    }
   
    public void setExp(final int exp) {
        this.exp = exp;
    }
   
    public void removeLogouts(final int index) {
        this.logout -= index;
    }
   
    public Guild getGuild() {
        return GuildManager.getGuild(this.getPlayer());
    }
   
    private void insert() {
        ServerPlugin.getStore().update(false, "INSERT INTO `{P}users`(`id`, `name`, `points`, `kills`, `deaths`, `coins`, `asyst`, `wykstone`, `logout`, `kox`, `snow`, `ice`, `arrow`, `koxeat`, `refil`, `refileat`, `perly`, `perlycyk`, `strzaly`, `jajopen`, `zdrapkaopen`, `cobblexopen`, `join`, `time`, `timelast`, `firstIP`, `lastIP`, `firstJoin`, `kit_start`, `kit_yt`, `kit_tw`, `kit_vip`, `kit_svip`, `turboDrop`, `turboExp`, `home`, `lastKill`, `lastKillTime`, `god`, `lvl`, `exp`) VALUES (NULL, '" + this.getName() + "','" + this.getPoints() + "','" + this.getKills() + "','" + this.getDeaths() + "','" + this.getCoins() + "','" + this.getAsyst() + "','" + this.getWykStone() + "','" + this.getLogout() + "','" + this.getKox() + "','" + this.getSnow() + "','" + this.getIce() + "','" + this.getArrow() + "','" + this.getKoxeat() + "','" + this.getRefil() + "','" + this.getRefileat() + "','" + this.getPerly() + "','" + this.getPerlycyk() + "','" + this.getStrzaly() + "','" + this.getJajopen() + "','" + this.getZdrapkaopen() + "','" + this.getCobblexopen() + "','" + this.getJoin() + "','" + this.getTime() + "','" + this.getTimeLast() + "','" + this.getFirstIP() + "','" + this.getLastIP() + "','" + this.getFirstJoin() + "','" + this.getKit_start() + "','" + this.getKit_yt() + "','" + this.getKit_tw() + "','" + this.getKit_vip() + "','" + this.getKit_svip() + "','" + this.getTurboDrop() + "','" + this.getTurboExp() + "','" + this.getHome() + "','" + this.getLastKill() + "','" + this.getLastKillTime() + "','" + (this.isGod() ? 1 : 0) + "','" + this.getLvl() + "','" + this.getExp() + "')");
    }
   
    public void save() {
        ServerPlugin.getStore().update(false, "UPDATE `{P}users` SET `points` = '" + this.getPoints() + "', `kills` = '" + this.getKills() + "', `deaths` = '" + this.getDeaths() + "', `coins` = '" + this.getCoins() + "', `asyst` = '" + this.getAsyst() + "', `wykstone` = '" + this.getWykStone() + "', `logout` = '" + this.getLogout() + "', `kox` = '" + this.getKox() + "',  `snow` = '" + this.getSnow() + "',  `ice` = '" + this.getIce() + "',  `arrow` = '" + this.getArrow() + "', `koxeat` = '" + this.getKoxeat() + "', `refil` = '" + this.getRefil() + "', `refileat` = '" + this.getRefileat() + "', `perly` = '" + this.getPerly() + "', `perlycyk` = '" + this.getPerlycyk() + "', `strzaly` = '" + this.getStrzaly() + "', `jajopen` = '" + this.getJajopen() + "', `zdrapkaopen` = '" + this.getZdrapkaopen() + "', `cobblexopen` = '" + this.getCobblexopen() + "', `join` = '" + this.getJoin() + "', `time` = '" + this.getTime() + "', `timelast` = '" + this.getTimeLast() + "', `firstIP` = '" + this.getFirstIP() + "', `lastIP` =' " + this.getLastIP() + "', `firstJoin` = '" + this.getFirstJoin() + "', `kit_start` =' " + this.getKit_start() + "', `kit_yt` = '" + this.getKit_yt() + "', `kit_tw` = '" + this.getKit_tw() + "', `kit_svip` = '" + this.getKit_svip() + "', `turboDrop` = '" + this.getTurboDrop() + "', `turboExp` = '" + this.getTurboExp() + "', `home` = '" + this.getHome() + "', `lastKill` = '" + this.getLastKill() + "', `lastKillTime` = '" + this.getLastKillTime() + "', `god` = '" + (this.isGod() ? 1 : 0) + "', `lvl` = '" + this.getLvl() + "', `exp` = '" + this.getExp() + "' WHERE `name` ='" + this.getName() + "';");
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
   
    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(this.getName());
    }
   
    public boolean isKitMieso() {
        return this.getKit_mieso() > System.currentTimeMillis();
    }
   
    public boolean isKitStart() {
        return this.getKit_start() > System.currentTimeMillis();
    }
   
    public boolean isKitVip() {
        return this.getKit_vip() > System.currentTimeMillis();
    }
   
    public boolean isKitSvip() {
        return this.getKit_svip() > System.currentTimeMillis();
    }
   
    public boolean isKittw() {
        return this.getKit_svip() > System.currentTimeMillis();
    }
    
}
