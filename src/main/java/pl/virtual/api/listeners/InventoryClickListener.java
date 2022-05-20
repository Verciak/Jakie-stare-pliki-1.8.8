package pl.virtual.api.listeners;

import org.bukkit.inventory.*;
import org.bukkit.event.*;
import org.bukkit.event.block.Action;
import org.bukkit.*;
import org.bukkit.command.*;

import org.bukkit.enchantments.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.data.base.user.*;
import pl.virtual.api.gui.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.trade.TradeOptions;
import pl.virtual.api.utils.*;

import org.bukkit.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;

public class InventoryClickListener implements Listener
	{
	    private static final HashMap<UUID, Long> times;
	    public static final HashMap<UUID, String> sejf;
	    public static final HashMap<UUID, Boolean> sejf1;
	    
	    static {
	        times = new HashMap<UUID, Long>();
	        sejf = new HashMap<UUID, String>();
	        sejf1 = new HashMap<UUID, Boolean>();
	    }
	    
	    private static ItemStack n;
	    private static ItemStack y;
	    
	    
	    public static boolean isInSpawn(final Location loc) {
	        return loc.getBlockX() <= Config.REGION_SPAWN && loc.getBlockX() >= -Config.REGION_SPAWN - 1 && loc.getBlockZ() <= Config.REGION_SPAWN && loc.getBlockZ() >= -Config.REGION_SPAWN - 1;
	    }
	    
	    @EventHandler
	    public void onInventoryClickEvent(final InventoryClickEvent e) {
	        final User tradeOption = UserManager.getUser(e.getWhoClicked().getName());
	        TradeOptions tradeOptions = tradeOption.getTradeOptions();
	        if (tradeOptions.isTrading()) {
	            if (tradeOptions.isReady() && e.getSlot() != 49) {
	                ChatUtil.sendMessage((Player)e.getWhoClicked(), "&9&lERROR: &7Nie mozesz tego zrobic podczas gdy wymiana jest w toku");
	                ChatUtil.sendMessage((Player)e.getWhoClicked(), "&9&lERROR: &7Aby zabrac przedmiot anuluj wymiane");
	                e.setCancelled(true);
	            }
	            if (e.getSlot() == 49) {
	                if (!tradeOptions.isReady()) {
	                    ChatUtil.sendMessage((Player)e.getWhoClicked(), "&8[&4&lWYMIANA&8] &7Zaakceptowales wymiane");
	                    if (tradeOptions.getTrade().getU1().equals(UserManager.getUser((Player)e.getWhoClicked()))) {
	                        tradeOptions.getTrade().getI2().setItem(4, InventoryClickListener.y);
	                        tradeOptions.getTrade().getI1().setItem(49, InventoryClickListener.y);
	                    }
	                    else if (tradeOptions.getTrade().getU2().equals(UserManager.getUser((Player)e.getWhoClicked()))) {
	                        tradeOptions.getTrade().getI1().setItem(4, InventoryClickListener.y);
	                        tradeOptions.getTrade().getI2().setItem(49, InventoryClickListener.y);
	                    }
	                    tradeOptions.setReady(true);
	                }
	                else {
	                    ChatUtil.sendMessage((Player)e.getWhoClicked(), "&8[&4&lWYMIANA&8] &7Anulowales wymiane");
	                    if (tradeOptions.getTrade().getU1().equals(UserManager.getUser((Player)e.getWhoClicked()))) {
	                        tradeOptions.getTrade().getI2().setItem(4, InventoryClickListener.n);
	                        tradeOptions.getTrade().getI1().setItem(49, InventoryClickListener.n);
	                    }
	                    else if (tradeOptions.getTrade().getU2().equals(UserManager.getUser((Player)e.getWhoClicked()))) {
	                        tradeOptions.getTrade().getI1().setItem(4, InventoryClickListener.n);
	                        tradeOptions.getTrade().getI2().setItem(49, InventoryClickListener.n);
	                    }
	                    tradeOptions.setReady(false);
	                }
	                if (tradeOptions.getTrade().getU2().getTradeOptions().isReady() && tradeOptions.getTrade().getU1().getTradeOptions().isReady()) {
	                    ChatUtil.sendMessage((Player)e.getWhoClicked(), "&8[&4&lWYMIANA&8] &7Wymiana w toku...");
	                    TradeUtil.playTradeAcceptAnimation(tradeOptions);
	                }
	                e.setCancelled(true);
	                return;
	            }
	            if (e.getAction().equals((Object)InventoryAction.COLLECT_TO_CURSOR) || e.getAction().equals((Object)InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
	                e.setCancelled(true);
	                ChatUtil.sendMessage((Player)e.getWhoClicked(), "&9&lERROR: &7Nie mozesz tego zrobic");
	                return;
	            }
	            if (e.getView() != null && e.getView().getTitle().contains(ChatUtil.fixColor("&4&lWymiana:")) && !TradeUtil.isLeftSide(e.getSlot())) {
	                e.setCancelled(true);
	                ChatUtil.sendMessage((Player)e.getWhoClicked(), "&9&lERROR: &7Nie mozesz modyfikowac tego miejsca");
	            }
	            TradeUtil.updateTrade(tradeOptions);
	        }
	        if (e.getCurrentItem() == null) {
	            return;
	        }
	        final UUID p = e.getWhoClicked().getUniqueId();
	    }
	    
	    static {
	        InventoryClickListener.n = new ItemBuilder(Material.RED_STAINED_GLASS_PANE, 1, (short)14).setTitle(ChatUtil.fixColor("&c&lBRAK GOTOWOSCI DO WYMIANY")).build();
	        InventoryClickListener.y = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE, 1, (short)5).setTitle(ChatUtil.fixColor("&a&cGOTOWOSC DO WYMIANY")).build();
	    }

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onClick(final InventoryClickEvent e) {
		final Player p = (Player) e.getWhoClicked();
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lITEMY"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			return;
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lStatystyki"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			return;
		}
		if (e.getView().getTitle().equalsIgnoreCase("&6")) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final int slot = e.getSlot();
			final User u = UserManager.getUser(p);
			if (slot == 11) {
				if (u.getCoins() < 500) {	
					p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Nie posiadasz 500 coins"));
					return;
				}
				if (u.getCoins() > 499) {
					u.removeCoins(500);
					u.setKills(0);
					u.save();
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Zresetowano twoje zabojstwa.");
				}
			}
			if (slot == 12) {
				if (u.getCoins() < 500) {	
					p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Nie posiadasz 500 coins"));
					return;
				}
				if (u.getCoins() > 499) {
					u.removeCoins(500);
					u.setDeaths(0);
					u.save();
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Zresetowano twoje smierci.");
				}
			}
			if (slot == 14) {
				if (u.getCoins() < 500) {	
					p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Nie posiadasz 500 coins"));
					return;
				}
				if (u.getCoins() > 499) {
					u.removeCoins(500);
					u.setAsyst(0);
					u.save();
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7&cSukces Zresetowano twoje asysty.");
				}
			}
			if (slot == 15) {
				if (u.getCoins() < 500) {	
					p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Nie posiadasz 500 coins"));
					return;
				}
				if (u.getCoins() > 499) {
					u.removeCoins(500);
					u.setPoints(500);
					u.save();
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Zresetowano twoje punkty.");
				}
			}
			return;
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lCrafting GUI"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final int slot = e.getSlot();
			if (slot == 14) {
				CraftingUtil.openEndStone(p);
				return;
			}
			if (slot == 15) {
				CraftingUtil.openEnderchest(p);
				return;
			}
			if (slot == 12) {
				CraftingUtil.openBoy(p);
				return;
			}
			if (slot == 13) {
				CraftingUtil.openSand(p);
				return;
			}
			if (slot == 11) {
				CraftingUtil.openKop(p);
				return;
			}
			if (slot == 22) {
				CraftingUtil.openBea(p);
				return;
			}
		}
		if (e.getView().getTitle().equals(ChatUtil.fixColor("&4&lEventy"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
		}
		if (e.getView().getTitle().equals(ChatUtil.fixColor("&4&lNagroda"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lDepozyt"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final User u2 = UserManager.getUser(p);
			int koxw = 0;
			int refw = 0;
			int perlaw = 0;
			int snow = 0;
			int arrow = 0;
			final int i = ItemStackUtil.getAmountOfItem(Material.ENCHANTED_GOLDEN_APPLE, p, (short) 0);
			final int ii = ItemStackUtil.getAmountOfItem(Material.GOLDEN_APPLE, p, (short) 0);
			final int iii = ItemStackUtil.getAmountOfItem(Material.ENDER_PEARL, p, (short) 0);
			final int iiii = ItemStackUtil.getAmountOfItem(Material.SNOWBALL, p, (short) 0);
			final int iiiiii = ItemStackUtil.getAmountOfItem(Material.ARROW, p, (short) 0);
			final int slot2 = e.getSlot();
			if (slot2 == 13) {
				if (u2.getKox() <= 0) {
					ChatUtil.error(p, "&9&lERROR: &7Nie posiadasz koxow do wyplacenia");
					return;
				}
				if (!isInSpawn(p.getLocation()) && i >= Config.LIMIT_KOX) {
					ChatUtil.error(p, "&9&lERROR: &7Osiagneles juz limit koxow");
					return;
				}
				if(isInSpawn(p.getLocation())){
					koxw = u2.getKox();
					u2.removeKox(1);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, 1, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplacono &c" + 1 + " &7koxy");
					SchowekGui.show(p);
				}
				if (u2.getKox() <= Config.LIMIT_KOX) {
					koxw = u2.getKox();
					u2.removeKox(koxw);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, koxw, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplacono &c" + koxw + " &7koxy");
					SchowekGui.show(p);
				}
				if (i < Config.LIMIT_KOX) {
					final int ref = i - Config.LIMIT_KOX;
					koxw = ref * -1;
					u2.removeKox(koxw);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, koxw, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplacono &c" + koxw + " &7koxy");
					SchowekGui.show(p);
				}
				u2.save();
			}
			if (slot2 == 12) {
				if (u2.getRefil() <= 0) {
					ChatUtil.error(p, "&9&lERROR: &7Nie posiadasz refow do wyplacenia");
					return;
				}
				if (!isInSpawn(p.getLocation()) && ii >= Config.LIMIT_REFILE) {
					ChatUtil.error(p, "&9&lERROR: &7Osiagneles juz limit refili");
					return;
				}
				if(isInSpawn(p.getLocation())){
					refw = u2.getRefil();
					u2.removeRefil(1);
					ChatUtil.giveItems(p, new ItemBuilder(Material.GOLDEN_APPLE, 1, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplacono &c" + 1 + " &7refile");
					SchowekGui.show(p);
				}
				if (u2.getRefil() <= Config.LIMIT_REFILE) {
					refw = u2.getRefil();
					u2.removeRefil(refw);
					ChatUtil.giveItems(p, new ItemBuilder(Material.GOLDEN_APPLE, refw, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplacono &c" + refw + " &7refile");
					SchowekGui.show(p);
				}
				if (ii < Config.LIMIT_REFILE) {
					final int ref = ii - Config.LIMIT_REFILE;
					refw = ref * -1;
					u2.removeRefil(refw);
					ChatUtil.giveItems(p, new ItemBuilder(Material.GOLDEN_APPLE, refw, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + refw + " &7refile");
					SchowekGui.show(p);
				}
				u2.save();
			}
			if (slot2 == 14) {
				if (u2.getPerly() <= 0) {
					ChatUtil.error(p, "&9&lERROR: &7Nie posiadasz perel do wyplacenia");
					return;
				}
				if (!isInSpawn(p.getLocation()) && iii >= Config.LIMIT_PEARL) {
					ChatUtil.error(p, "&9&lERROR: &7Osiagneles juz limit perel");
					return;
				}
				if(isInSpawn(p.getLocation())){
					perlaw = u2.getPerly();
					u2.removePerly(1);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ENDER_PEARL, 1, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + 1 + " &7perly");
					SchowekGui.show(p);
				}
				if (u2.getPerly() <= Config.LIMIT_PEARL) {
					perlaw = u2.getPerly();
					u2.removePerly(perlaw);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ENDER_PEARL, perlaw, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + perlaw + " &7perly");
					SchowekGui.show(p);
				}
				if (iii < Config.LIMIT_PEARL) {
					final int ref = iii - Config.LIMIT_PEARL;
					perlaw = ref * -1;
					u2.removePerly(perlaw);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ENDER_PEARL, perlaw, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + perlaw + " &7perly");
					SchowekGui.show(p);
				}
				u2.save();
			}
			if (slot2 == 30) {
				if (u2.getSnow() <= 0) {
					ChatUtil.error(p, "&9&lERROR: &7Nie posiadasz sniezek do wyplacenia");
					return;
				}
				if (!isInSpawn(p.getLocation()) && iiii >= Config.LIMIT_SNOW) {
					ChatUtil.error(p, "&9&lERROR: &7Osiagneles juz limit sniezek");
					return;
				}
				if(isInSpawn(p.getLocation())){
					snow = u2.getSnow();
					u2.removeSnow(1);
					ChatUtil.giveItems(p, new ItemBuilder(Material.SNOWBALL, 1, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + 1 + " &7sniezek");
					SchowekGui.show(p);
				}
				if (u2.getSnow() <= Config.LIMIT_SNOW) {
					snow = u2.getSnow();
					u2.removeSnow(snow);
					ChatUtil.giveItems(p, new ItemBuilder(Material.SNOWBALL, snow, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + snow + " &7sniezek");
					SchowekGui.show(p);
				}
				if (iiii < Config.LIMIT_SNOW) {
					final int sno = iiii - Config.LIMIT_SNOW;
					snow = sno * -1;
					u2.removeSnow(snow);
					ChatUtil.giveItems(p, new ItemBuilder(Material.SNOWBALL, snow, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + snow + " &7sniezek");
					SchowekGui.show(p);
				}
				u2.save();
			}
			if (slot2 == 32) {
				if (u2.getArrow() <= 0) {
					ChatUtil.error(p, "&9&lERROR: &7Nie posiadasz strzal do wyplacenia");
					return;
				}
				if (!isInSpawn(p.getLocation()) && iiiiii >= Config.LIMIT_ARROW) {
					ChatUtil.error(p, "&9&lERROR: &7Osiagneles juz limit strzal");
					return;
				}
				if(isInSpawn(p.getLocation())){
					arrow = u2.getArrow();
					u2.removeArrow(1);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ARROW, 1, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + 1 + " &7strzal");
					SchowekGui.show(p);
				}
				if (u2.getArrow() <= Config.LIMIT_ARROW) {
					arrow = u2.getArrow();
					u2.removeArrow(arrow);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ARROW, arrow, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + arrow + " &7strzal");
					SchowekGui.show(p);
				}
				if (iiiiii < Config.LIMIT_ARROW) {
					final int arr = iiiiii - Config.LIMIT_ARROW;
					arrow = arr * -1;
					u2.removeArrow(arrow);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ARROW, arrow, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + arrow + " &7strzal");
					SchowekGui.show(p);
				}
				u2.save();
			}
			if (slot2 == 31) {
				if (u2.getKox() > 0 && i < Config.LIMIT_KOX && u2.getKox() <= Config.LIMIT_KOX) {
					koxw = u2.getKox();
					u2.removeKox(koxw);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, refw, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplacono &c" + koxw + " &7koxy");
					SchowekGui.show(p);
				}
				if (u2.getKox() > 0 && i < Config.LIMIT_KOX && i < Config.LIMIT_KOX) {
					final int ref = i - Config.LIMIT_KOX;
					koxw = ref * -1;
					u2.removeKox(koxw);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE, koxw, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplacono &c" + koxw + " &7koxy");
					SchowekGui.show(p);
				}
				if (ii < Config.LIMIT_REFILE && u2.getRefil() > 0 && u2.getRefil() <= Config.LIMIT_REFILE) {
					refw = u2.getRefil();
					u2.removeRefil(refw);
					ChatUtil.giveItems(p, new ItemBuilder(Material.GOLDEN_APPLE, refw, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplacono &c" + refw + " &7refile");
					SchowekGui.show(p);
				}
				if (ii < Config.LIMIT_REFILE && u2.getRefil() > 0 && ii < Config.LIMIT_REFILE) {
					final int ref = ii - Config.LIMIT_REFILE;
					refw = ref * -1;
					u2.removeRefil(refw);
					ChatUtil.giveItems(p, new ItemBuilder(Material.GOLDEN_APPLE, refw, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + refw + " &7refile");
					SchowekGui.show(p);
				}
				if (u2.getPerly() > 0 && iii < Config.LIMIT_PEARL && u2.getPerly() <= Config.LIMIT_PEARL) {
					perlaw = u2.getPerly();
					u2.removePerly(perlaw);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ENDER_PEARL, refw, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + refw + " &7perly");
					SchowekGui.show(p);
				}
				if (u2.getPerly() > 0 && iii < Config.LIMIT_PEARL && iii < Config.LIMIT_PEARL) {
					final int ref = iii - Config.LIMIT_PEARL;
					perlaw = ref * -1;
					u2.removePerly(perlaw);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ENDER_PEARL, perlaw, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + perlaw + " &7perly");
					SchowekGui.show(p);
				}
				if (iiii < Config.LIMIT_SNOW && u2.getSnow() > 0 && u2.getSnow() <= Config.LIMIT_SNOW) {
					snow = u2.getSnow();
					u2.removeSnow(snow);
					ChatUtil.giveItems(p, new ItemBuilder(Material.SNOWBALL, snow, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + snow + " &7sniezek");
					SchowekGui.show(p);
				}
				if (iiii < Config.LIMIT_SNOW && u2.getSnow() > 0 && iiii < Config.LIMIT_SNOW) {
					final int sno = iiii - Config.LIMIT_SNOW;
					snow = sno * -1;
					u2.removeSnow(snow);
					ChatUtil.giveItems(p, new ItemBuilder(Material.SNOWBALL, snow, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + snow + " &7sniezek");
					SchowekGui.show(p);
				}
				if (iiiiii < Config.LIMIT_ARROW && u2.getArrow() > 0 && u2.getArrow() <= Config.LIMIT_ARROW) {
					arrow = u2.getArrow();
					u2.removeArrow(arrow);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ARROW, arrow, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + arrow + " &7strzal");
					SchowekGui.show(p);
				}
				if (iiiiii < Config.LIMIT_ARROW && u2.getArrow() > 0 && iiiiii < Config.LIMIT_ARROW) {
					final int arr = iiiiii - Config.LIMIT_ARROW;
					arrow = arr * -1;
					u2.removeArrow(arrow);
					ChatUtil.giveItems(p, new ItemBuilder(Material.ARROW, arrow, (short) 0).build());
					ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Wyplaciles &c" + arrow + " &7strzal");
					SchowekGui.show(p);
				}
				u2.save();
			}
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lZestawy"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final int slot3 = e.getSlot();
			if (slot3 == 10) {
				KitGui.show1(p);
				return;
			}
			if (Config.ENABLE_DIAMOND && slot3 == 12) {
				KitGui.show2(p);
				return;
			}
			if (!Config.ENABLE_DIAMOND && slot3 == 12) {
				KitGui.show2iron(p);
				return;
			}
			if (Config.ENABLE_DIAMOND && slot3 == 14) {
				KitGui.show3(p);
				return;
			}
			if (!Config.ENABLE_DIAMOND && slot3 == 14) {
				KitGui.show3iron(p);
				return;
			}
			if (Config.ENABLE_DIAMOND && slot3 == 16) {
				KitGui.show4(p);
				return;
			}
			if (!Config.ENABLE_DIAMOND && slot3 == 16) {
				KitGui.show4iron(p);
				return;
			}
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lKit Start"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final User u2 = UserManager.getUser(p);
			final int slot3 = e.getSlot();
			if (slot3 == 52) {
				if (u2.isKitStart() && !p.hasPermission("core.cmd.admin")) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Nastepny raz mozesz to zrobic za: &c" + DataUtil.secondsToString(u2.getKit_start()));
					return;
				}
				u2.setKit_start(System.currentTimeMillis() + TimeUtil.MINUTE.getTime(5));
				ChatUtil.giveItems(p, new ItemStack(Material.IRON_PICKAXE));
				ChatUtil.giveItems(p, new ItemStack(Material.ENDER_CHEST));
				ChatUtil.giveItems(p, new ItemStack(Material.COOKED_BEEF, 64));
				ChatUtil.giveItems(p, new ItemStack(Material.OAK_LOG, 48));
				ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Otrzymales kita");
				p.closeInventory();
				u2.save();
				return;
			}
			if (slot3 == 53) {
				KitGui.show(p);
				return;
			}
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lKit VIP"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final User u2 = UserManager.getUser(p);
			final int slot3 = e.getSlot();
			if (Config.ENABLE_DIAMOND && slot3 == 52) {
				if (!p.hasPermission("core.cmd.admin") && !Config.ENABLE_KIT) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Zestawy sa tymczasowo wylaczone");
					return;
				}
				if (!p.hasPermission("core.cmd.admin") && !p.hasPermission("core.kit.vip")) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Aby posiadac dostep, Zakup range VIP");
					return;
				}
				if (u2.isKitVip() && !p.hasPermission("core.cmd.admin")) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Nastepny raz mozesz to zrobic za: &c" + DataUtil.secondsToString(u2.getKit_vip()));
					return;
				}
				u2.setKit_vip(System.currentTimeMillis() + TimeUtil.HOUR.getTime(12));
				ChatUtil.giveItems(p, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 4)
						.addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build());
				ChatUtil.giveItems(p, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1, (short)0));
				ChatUtil.giveItems(p, new ItemStack(Material.GOLDEN_APPLE, 12));
				ChatUtil.giveItems(p, new ItemStack(Material.ENDER_PEARL, 3));
				ChatUtil.giveItems(p, new ItemStack(Material.ARROW, 64));
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_CHESTPLATE)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_LEGGINGS)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5)
								.addEnchantment(Enchantment.DURABILITY, 3)
								.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 3)
								.addEnchantment(Enchantment.ARROW_FIRE, 1).addEnchantment(Enchantment.DURABILITY, 3).build());
				ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Otrzymales kita");
				p.closeInventory();
				u2.save();
				return;
			}
			if (slot3 == 53) {
				KitGui.show(p);
				return;
			}
			if (!Config.ENABLE_DIAMOND && slot3 == 52) {
				if (!p.hasPermission("core.cmd.admin") && !Config.ENABLE_KIT) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Zestawy sa tymczasowo wylaczone");
					return;
				}
				if (!p.hasPermission("core.cmd.admin") && !p.hasPermission("core.kit.vip")) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Aby posiadac dostep, Zakup range VIP");
					return;
				}
				if (u2.isKitVip() && !p.hasPermission("core.cmd.admin")) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Nastepny raz mozesz to zrobic za: &c" + DataUtil.secondsToString(u2.getKit_vip()));
					return;
				}
				u2.setKit_vip(System.currentTimeMillis() + TimeUtil.HOUR.getTime(12));
				ChatUtil.giveItems(p, new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 3)
						.addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build());
				ChatUtil.giveItems(p, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1, (short)0));
				ChatUtil.giveItems(p, new ItemStack(Material.GOLDEN_APPLE, 12));
				ChatUtil.giveItems(p, new ItemStack(Material.ENDER_PEARL, 3));
				ChatUtil.giveItems(p, new ItemStack(Material.ARROW, 64));
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_CHESTPLATE)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_LEGGINGS)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5)
								.addEnchantment(Enchantment.DURABILITY, 3)
								.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 3)
								.addEnchantment(Enchantment.ARROW_FIRE, 1).addEnchantment(Enchantment.DURABILITY, 3).build());
				ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Otrzymales kita");
				p.closeInventory();
				u2.save();
				return;
			}
			if (slot3 == 53) {
				KitGui.show(p);
				return;
			}
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lKit SVIP"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final User u2 = UserManager.getUser(p);
			final int slot3 = e.getSlot();
			if (Config.ENABLE_DIAMOND && slot3 == 52) {
				if (!p.hasPermission("core.cmd.admin") && !Config.ENABLE_KIT) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Zestawy sa tymczasowo wylaczone");
					return;
				}
				if (!p.hasPermission("core.cmd.admin") && !p.hasPermission("core.kit.svip")) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Aby posiadac dostep Zakup range SVIP");
					return;
				}
				if (u2.isKitSvip() && !p.hasPermission("core.cmd.admin")) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Nastepny raz mozesz to zrobic za: &c" + DataUtil.secondsToString(u2.getKit_svip()));
					return;
				}
				u2.setKit_svip(System.currentTimeMillis() + TimeUtil.HOUR.getTime(12));
				ChatUtil.giveItems(p, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 4)
						.addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 4)
						.addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build());
				ChatUtil.giveItems(p, new ItemStack(Material.GOLDEN_APPLE, 2, (short)1));
				ChatUtil.giveItems(p, new ItemStack(Material.GOLDEN_APPLE, 24));
				ChatUtil.giveItems(p, new ItemStack(Material.ENDER_PEARL, 6));
				ChatUtil.giveItems(p, new ItemStack(Material.ARROW, 64));
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_CHESTPLATE)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_LEGGINGS)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5)
								.addEnchantment(Enchantment.DURABILITY, 3)
								.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_CHESTPLATE)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_LEGGINGS)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5)
								.addEnchantment(Enchantment.DURABILITY, 3)
								.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 3)
								.addEnchantment(Enchantment.ARROW_FIRE, 1).addEnchantment(Enchantment.DURABILITY, 3).build());
				ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Otrzymales kita");
				p.closeInventory();
				u2.save();
				return;
			}
			if (slot3 == 53) {
				KitGui.show(p);
				return;
			}
			if (!Config.ENABLE_DIAMOND && slot3 == 52) {
				if (!p.hasPermission("core.cmd.admin") && !Config.ENABLE_KIT) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Zestawy sa tymczasowo wylaczone");
					return;
				}
				if (!p.hasPermission("core.cmd.admin") && !p.hasPermission("core.kit.svip")) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Aby posiadac dostep Zakup range SVIP");
					return;
				}
				if (u2.isKitSvip() && !p.hasPermission("core.cmd.admin")) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Nastepny raz mozesz to zrobic za: &c" + DataUtil.secondsToString(u2.getKit_svip()));
					return;
				}
				u2.setKit_svip(System.currentTimeMillis() + TimeUtil.HOUR.getTime(12));
				ChatUtil.giveItems(p, new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 3)
						.addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 3)
						.addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build());
				ChatUtil.giveItems(p, new ItemStack(Material.GOLDEN_APPLE, 2, (short)1));
				ChatUtil.giveItems(p, new ItemStack(Material.GOLDEN_APPLE, 24));
				ChatUtil.giveItems(p, new ItemStack(Material.ENDER_PEARL, 6));
				ChatUtil.giveItems(p, new ItemStack(Material.ARROW, 64));
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_CHESTPLATE)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_LEGGINGS)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5)
								.addEnchantment(Enchantment.DURABILITY, 3)
								.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_CHESTPLATE)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_LEGGINGS)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5)
								.addEnchantment(Enchantment.DURABILITY, 3)
								.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 3)
								.addEnchantment(Enchantment.ARROW_FIRE, 1).addEnchantment(Enchantment.DURABILITY, 3).build());
				ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Otrzymales kita");
				p.closeInventory();
				u2.save();
				return;
			}
			if (slot3 == 53) {
				KitGui.show(p);
				return;
			}
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lKit SPONSOR"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final User u2 = UserManager.getUser(p);
			final int slot3 = e.getSlot();
			if (Config.ENABLE_DIAMOND && slot3 == 52) {
				if (!p.hasPermission("core.cmd.admin") && !Config.ENABLE_KIT) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Zestawy sa tymczasowo wylaczone");
					return;
				}
				if (!p.hasPermission("core.cmd.admin") && !p.hasPermission("core.kit.mvip")) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Aby posiadac dostep, Zakup range DARK");
					return;
				}
				if (u2.isKit_tw() && !p.hasPermission("core.cmd.admin")) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Nastepny raz mozesz to zrobic za: &c" + DataUtil.secondsToString(u2.getKit_tw()));
					return;
				}
				u2.setKit_tw(System.currentTimeMillis() + TimeUtil.HOUR.getTime(12));
				ChatUtil.giveItems(p, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 4)
						.addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 4)
						.addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 4)
						.addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.DIAMOND_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build());
				ChatUtil.giveItems(p, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 3, (short)0));
				ChatUtil.giveItems(p, new ItemStack(Material.GOLDEN_APPLE, 36));
				ChatUtil.giveItems(p, new ItemStack(Material.ENDER_PEARL, 9));
				ChatUtil.giveItems(p, new ItemStack(Material.ARROW, 64));
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_CHESTPLATE)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_LEGGINGS)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5)
								.addEnchantment(Enchantment.DURABILITY, 3)
								.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_CHESTPLATE)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_LEGGINGS)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5)
								.addEnchantment(Enchantment.DURABILITY, 3)
								.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_CHESTPLATE)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_LEGGINGS)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5)
								.addEnchantment(Enchantment.DURABILITY, 3)
								.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 3)
								.addEnchantment(Enchantment.ARROW_FIRE, 1).addEnchantment(Enchantment.DURABILITY, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 3)
								.addEnchantment(Enchantment.ARROW_FIRE, 1).addEnchantment(Enchantment.DURABILITY, 3).build());
				ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Otrzymales kita");
				p.closeInventory();
				u2.save();
				return;
			}
			if (slot3 == 53) {
				KitGui.show(p);
				return;
			}
			if (!Config.ENABLE_DIAMOND && slot3 == 52) {
				if (!p.hasPermission("core.cmd.admin") && !Config.ENABLE_KIT) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Zestawy sa tymczasowo wylaczone");
					return;
				}
				if (!p.hasPermission("core.cmd.admin") && !p.hasPermission("core.kit.mvip")) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Aby posiadac dostep, Zakup range DARK");
					return;
				}
				if (u2.isKit_tw() && !p.hasPermission("core.cmd.admin")) {
					ChatUtil.sendMessage((CommandSender) p, "&9&lERROR: &7Nastepny raz mozesz to zrobic za: &c" + DataUtil.secondsToString(u2.getKit_tw()));
					return;
				}
				u2.setKit_tw(System.currentTimeMillis() + TimeUtil.HOUR.getTime(12));
				ChatUtil.giveItems(p, new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 3)
						.addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 3)
						.addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 3)
						.addEnchantment(Enchantment.DURABILITY, 3).addEnchantment(Enchantment.FIRE_ASPECT, 1).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build());
				ChatUtil.giveItems(p, new ItemBuilder(Material.IRON_SWORD).addEnchantment(Enchantment.KNOCKBACK, 2).build());
				ChatUtil.giveItems(p, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 3, (short)0));
				ChatUtil.giveItems(p, new ItemStack(Material.GOLDEN_APPLE, 36));
				ChatUtil.giveItems(p, new ItemStack(Material.ENDER_PEARL, 9));
				ChatUtil.giveItems(p, new ItemStack(Material.ARROW, 64));
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_CHESTPLATE)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_LEGGINGS)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5)
								.addEnchantment(Enchantment.DURABILITY, 3)
								.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_CHESTPLATE)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_LEGGINGS)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5)
								.addEnchantment(Enchantment.DURABILITY, 3)
								.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_HELMET).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_CHESTPLATE)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_LEGGINGS)
								.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.IRON_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
								.addEnchantment(Enchantment.DURABILITY, 2).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchantment(Enchantment.DIG_SPEED, 5)
								.addEnchantment(Enchantment.DURABILITY, 3)
								.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 3)
								.addEnchantment(Enchantment.ARROW_FIRE, 1).addEnchantment(Enchantment.DURABILITY, 3).build());
				ChatUtil.giveItems(p,
						new ItemBuilder(Material.BOW).addEnchantment(Enchantment.ARROW_DAMAGE, 3)
								.addEnchantment(Enchantment.ARROW_FIRE, 1).addEnchantment(Enchantment.DURABILITY, 3).build());
				ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Otrzymales kita");
				p.closeInventory();
				u2.save();
				return;
			}
			if (slot3 == 53) {
				KitGui.show(p);
				return;
			}
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lCrafting 1/6"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final int slot = e.getSlot();
			if (slot == 49) {
				CraftingUtil.openMenu(p);
				return;
			}
			if (slot == 19) {
				final String cost = "OBSIDIAN:0-6:Obsidian;GOLD_BLOCK:0-2:Gold Block;DIAMOND_BLOCK:0-1:Diamond Block";
				if (!ItemUtil.checkItems(p, "OBSIDIAN:0-6:Obsidian;GOLD_BLOCK:0-2:Gold Block;DIAMOND_BLOCK:0-1:Diamond Block", 1)) {
					ItemUtil.getItem(p, "OBSIDIAN:0-6:Obsidian;GOLD_BLOCK:0-2:Gold Block;DIAMOND_BLOCK:0-1:Diamond Block", 1);
					return;
				}
				ItemUtil.removeItems(p, "OBSIDIAN:0-6:Obsidian;GOLD_BLOCK:0-2:Gold Block;DIAMOND_BLOCK:0-1:Diamond Block", 1);
				ChatUtil.giveItems(p, new ItemBuilder(Material.SEA_LANTERN, 4)
						.setTitle(ChatUtil.fixColor("&a&lKopaczFosy")).addEnchantment(Enchantment.DURABILITY, 10).build());
				ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Utworzyles kopaczfosy");
				return;
			}
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lCrafting 2/6"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final int slot = e.getSlot();
			if (slot == 49) {
				CraftingUtil.openMenu(p);
				return;
			}
			if (slot == 19) {
				final String cost = "OBSIDIAN:0-6:Obsidian;GOLD_BLOCK:0-2:Gold Block;LAVA_BUCKET:0-1:Lava Bucket";
				if (!ItemUtil.checkItems(p, "OBSIDIAN:0-6:Obsidian;GOLD_BLOCK:0-2:Gold Block;LAVA_BUCKET:0-1:Lava Bucket", 1)) {
					ItemUtil.getItem(p, "OBSIDIAN:0-6:Obsidian;GOLD_BLOCK:0-2:Gold Block;LAVA_BUCKET:0-1:Lava Bucket", 1);
					return;
				}
				ItemUtil.removeItems(p, "OBSIDIAN:0-6:Obsidian;GOLD_BLOCK:0-2:Gold Block;LAVA_BUCKET:0-1:Lava Bucket", 1);
				ChatUtil.giveItems(p, new ItemBuilder(Material.getMaterial(Material.PRISMARINE.name()), 4, (short)2)
						.setTitle(ChatUtil.fixColor("&a&lBoyFarmer")).addEnchantment(Enchantment.DURABILITY, 10).build());
				ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Utworzyles boyfarmer");
				return;
			}
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lCrafting 3/6"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final int slot = e.getSlot();
			if (slot == 49) {
				CraftingUtil.openMenu(p);
				return;
			}
			if (slot == 19) {
				final String cost = "OBSIDIAN:0-6:Obsidian;GOLD_BLOCK:0-2:Gold Block;SAND:0-1:Sand";
				if (!ItemUtil.checkItems(p, "OBSIDIAN:0-6:Obsidian;GOLD_BLOCK:0-2:Gold Block;SAND:0-1:Sand", 1)) {
					ItemUtil.getItem(p, "OBSIDIAN:0-6:Obsidian;GOLD_BLOCK:0-2:Gold Block;SAND:0-1:Sand", 1);
					return;
				}
				ItemUtil.removeItems(p, "OBSIDIAN:0-6:Obsidian;GOLD_BLOCK:0-2:Gold Block;SAND:0-1:Sand", 1);
				ChatUtil.giveItems(p, new ItemBuilder(Material.PRISMARINE, 4)
						.setTitle(ChatUtil.fixColor("&a&lSandFarmer")).addEnchantment(Enchantment.DURABILITY, 10).build());
				ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Utworzyles sandfarmer");
				return;
			}
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lCrafting 4/6"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final int slot = e.getSlot();
			if (slot == 49) {
				CraftingUtil.openMenu(p);
				return;
			}
			if (slot == 19) {
				final String cost = "COBBLESTONE:0-6:Cobblestone;REDSTONE:0-2:Redstone;ENDER_PEARL:0-1:Perla";
				if (!ItemUtil.checkItems(p, "COBBLESTONE:0-6:Cobblestone;REDSTONE:0-2:Redstone;ENDER_PEARL:0-1:Perla", 1)) {
					ItemUtil.getItem(p, "COBBLESTONE:0-6:Cobblestone;REDSTONE:0-2:Redstone;ENDER_PEARL:0-1:Perla", 1);
					return;
				}
				ItemUtil.removeItems(p, "COBBLESTONE:0-6:Cobblestone;REDSTONE:0-2:Redstone;ENDER_PEARL:0-1:Perla", 1);
				ChatUtil.giveItems(p, new ItemBuilder(Material.END_STONE, 1)
						.setTitle(ChatUtil.fixColor("&a&lStoniarka")).addEnchantment(Enchantment.DURABILITY, 10).build());
				ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Utworzyles stoniarke");
				return;
			}
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lCrafting 5/6"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final int slot = e.getSlot();
			if (slot == 49) {
				CraftingUtil.openMenu(p);
				return;
			}
			if (slot == 19) {
				final String cost = "OBSIDIAN:0-8:Obsidian;ENDER_PEARL:0-1:Ender Pearl";
				if (!ItemUtil.checkItems(p, "OBSIDIAN:0-8:Obsidian;ENDER_PEARL:0-1:Ender Pearl", 1)) {
					ItemUtil.getItem(p, "OBSIDIAN:0-8:Obsidian;ENDER_PEARL:0-1:Ender Pearl", 1);
					return;
				}
				ItemUtil.removeItems(p, "OBSIDIAN:0-8:Obsidian;ENDER_PEARL:0-1:Ender Pearl", 1);
				ChatUtil.giveItems(p, new ItemBuilder(Material.ENDER_CHEST, 1).build());
				ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Utworzyles enderchest");
				return;
			}
		}
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lCrafting 6/6"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
//			final int slot = e.getSlot();
//			if (slot == 49) {
//				CraftingUtil.openMenu(p);
//				return;
//			}
//			if (slot == 19) {
//				final String cost = "OBSIDIAN:0-3:Obsidian;397:1-3:Czaszka mrocznego szkieleta;20:0-3:Szklo";
//				if (!ItemUtil.checkItems(p, "OBSIDIAN:0-3:Obsidian;397:1-3:Czaszka mrocznego szkieleta;20:0-3:Szklo", 1)) {
//					ItemUtil.getItem(p, "OBSIDIAN:0-3:Obsidian;397:1-3:Czaszka mrocznego szkieleta;20:0-3:Szklo", 1);
//					return;
//				}
//				ItemUtil.removeItems(p, "OBSIDIAN:0-3:Obsidian;397:1-3:Czaszka mrocznego szkieleta;20:0-3:Szklo", 1);
//				ChatUtil.giveItems(p, new ItemBuilder(Material.BEACON, 1).build());
//				ChatUtil.sendMessage((CommandSender) p, "&a&LSukces &7Utworzyles beacon");
//				return;
//			}
		}
	}
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void enable(final InventoryClickEvent e) {
    	if (e.getView().getTitle().contains(ChatUtil.fixColor("&4&lUprawnienia&8:&7 "))) {
    		String nick = e.getView().getTitle().substring(21);
    		Player gracz = Bukkit.getPlayer(nick);
            e.setCancelled(true);
            final int j = e.getSlot();
            final Player p = (Player) e.getWhoClicked();
            final User u = UserManager.getUser(p);
	        final Guild g = GuildManager.getGuild(p);
	        Upr perms = UprManager.getUser(gracz);
            if (j == 11) {
            	if (perms.getPlace() == 0) {
            		perms.setPlace(1);
            		perms.save();
					PermsGui.show(p, gracz);
            		return;
            		}else if(perms.getPlace() == 1){
                		perms.setPlace(0);
                		perms.save(); 
    					PermsGui.show(p, gracz);
                		return;
            		}
            	}
            if (j == 10) {
            	if (perms.getBreak() == 0) {
            		perms.setBreak(1);
            		perms.save();
					PermsGui.show(p, gracz);
            		return;
            		}else if(perms.getBreak() == 1){
                		perms.setBreak(0);
                		perms.save();  
    					PermsGui.show(p, gracz);
                		return;
            		}
            	}
            if (j == 12) {
            	if (perms.getPlacetnt() == 0) {
            		perms.setPlacetnt(1);
            		perms.save();
					PermsGui.show(p, gracz);
            		return;
            		}else if(perms.getPlacetnt() == 1){
                		perms.setPlacetnt(0);
                		perms.save();  
    					PermsGui.show(p, gracz);
                		return;
            		}
            	}
            if (j == 13) {
            	if (perms.getBreakbeacon() == 0) {
            		perms.setBreakbeacon(1);
            		perms.save();
					PermsGui.show(p, gracz);
            		return;
            		}else if(perms.getBreakbeacon() == 1){
                		perms.setBreakbeacon(0);
                		perms.save();   
    					PermsGui.show(p, gracz);
                		return;
            		}
            	}
            if (j == 14) {
            	if (perms.getTeleportplayers() == 0) {
            		perms.setTeleportplayers(1);
            		perms.save();
					PermsGui.show(p, gracz);
            		return;
            		}else if(perms.getTeleportplayers() == 1){
                		perms.setTeleportplayers(0);
                		perms.save();  
    					PermsGui.show(p, gracz);
                		return;
            		}
            	}
            if (j == 15) {
            	if (perms.getOpenchest() == 0) {
            		perms.setOpenchest(1);
            		perms.save();
					PermsGui.show(p, gracz);
            		return;
            		}else if(perms.getOpenchest() == 1){
                		perms.setOpenchest(0);
                		perms.save();   
    					PermsGui.show(p, gracz);
                		return;
            		}
            	}
            if (j == 16) {
            	if (perms.getWater() == 0) {
            		perms.setWater(1);
            		perms.save();
					PermsGui.show(p, gracz);
            		return;
            		}else if(perms.getWater() == 1){
                		perms.setWater(0);
                		perms.save();
    					PermsGui.show(p, gracz);
                		return;
            		}
            	}
            if (j == 21) {
            	if (perms.getInvite() == 0) {
            		perms.setInvite(1);
            		perms.save();
					PermsGui.show(p, gracz);
            		return;
            		}else if(perms.getInvite() == 1){
                		perms.setInvite(0);
                		perms.save();
    					PermsGui.show(p, gracz);
                		return;
            		}
            	}
            if (j == 22) {
            	if (perms.getEnlarge() == 0) {
            		perms.setEnlarge(1);
            		perms.save();
					PermsGui.show(p, gracz);
            		return;
            		}else if(perms.getEnlarge() == 1){
                		perms.setEnlarge(0);
                		perms.save();
    					PermsGui.show(p, gracz);
                		return;
            		}
            	}
            if (j == 23) {
            	if (perms.getProlong() == 0) {
            		perms.setProlong(1);
            		perms.save();
					PermsGui.show(p, gracz);
            		return;
            		}else if(perms.getProlong() == 1){
                		perms.setProlong(0);
                		perms.save();
    					PermsGui.show(p, gracz);
                		return;
            		}
            	}
            if (j == 31) {
            	if (perms.getPvp() == 0) {
            		perms.setPvp(1);
            		perms.save();
					PermsGui.show(p, gracz);
            		return;
            		}else if(perms.getPvp() == 1){
                		perms.setPvp(0);
                		perms.save();
    					PermsGui.show(p, gracz);
                		return;
            		}
            	}
            if (j == 40 && !g.isOwner(p)) {
				p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7To uprawnienie moze nadac lub zabrac tylko zalozyciel"));
				p.closeInventory();
				return;
            }
            	if (perms.getPerms() == 0) {
            		perms.setPerms(1);
            		perms.save();
					PermsGui.show(p, gracz);
            		return;
            		}else if(perms.getPerms() == 1){
                		perms.setPerms(0);
                		perms.save();
    					PermsGui.show(p, gracz);
                		return;
            		}
            	}
        	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onClickCc(final InventoryClickEvent e) {
		if (!ChatUtil.fixColor("&4&LCHAT MANAGER").equalsIgnoreCase(e.getView().getTitle())) {
			return;
		}
		e.setCancelled(true);
		final ItemStack item = e.getCurrentItem();
		final Player p = (Player) e.getWhoClicked();
		final User u = UserManager.getUser(p);
		if (item != null) {
			final ItemMeta meta = item.getItemMeta();
			if (meta != null) {
				if (meta.getDisplayName() != null
						&& meta.getDisplayName().equals(ChatUtil.fixColor("&6&lAutomatyczne wiadomosci"))) {
					u.setAutoMessages(!u.isAutoMessages());
					p.closeInventory();
					WiadomosciGui.show(p);
				} else if (meta.getDisplayName() != null
						&& meta.getDisplayName().equals(ChatUtil.fixColor("&6&lPrywatne wiadomosci"))) {
					u.setPrivateMessages(!u.isPrivateMessages());
					p.closeInventory();
					WiadomosciGui.show(p);
				} else if (meta.getDisplayName() != null
						&& meta.getDisplayName().equals(ChatUtil.fixColor("&6&lWiadomosci o smierciach"))) {
					u.setDeathMessages(!u.isDeathMessages());
					p.closeInventory();
					WiadomosciGui.show(p);
				} else if (meta.getDisplayName() != null
						&& meta.getDisplayName().equals(ChatUtil.fixColor("&6&lWiadomosci z sklepu"))) {
					u.setShopMessages(!u.isShopMessages());
					p.closeInventory();
					WiadomosciGui.show(p);
				} else if (meta.getDisplayName() != null
						&& meta.getDisplayName().equals(ChatUtil.fixColor("&aWlacz wszystkie wiadomosci"))) {
					u.setShopMessages(true);
					u.setScratchesMessages(true);
					u.setCratesMessages(true);
					u.setDeathMessages(true);
					u.setPrivateMessages(true);
					u.setAutoMessages(true);
					p.closeInventory();
					WiadomosciGui.show(p);
				} else if (meta.getDisplayName() != null
						&& meta.getDisplayName().equals(ChatUtil.fixColor("&cWylacz wszystkie wiadomosci"))) {
					u.setShopMessages(false);
					u.setScratchesMessages(false);
					u.setCratesMessages(false);
					u.setDeathMessages(false);
					u.setPrivateMessages(false);
					u.setAutoMessages(false);
					p.closeInventory();
					WiadomosciGui.show(p);
				}
			}
		}
	}

	private int swich(final InventoryClickEvent e) {
		if (e.getClick() == ClickType.LEFT) {
			return 0;
		}
		if (e.getClick() == ClickType.RIGHT) {
			return 1;
		}
		if (e.getClick() == ClickType.SHIFT_LEFT) {
			return 2;
		}
		return 0;
	}
}
