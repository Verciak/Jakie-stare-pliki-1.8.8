package pl.virtual.api.listeners;

import org.bukkit.event.inventory.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.entity.*;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.API.Config;
import pl.virtual.api.gui.AdminPanelGui;
import pl.virtual.api.managers.DropFile;
import pl.virtual.api.utils.ChatUtil;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.*;

public class EnableListener implements Listener
{
	private static HashMap<UUID, Long> times;
    
    static {
        times = new HashMap<UUID, Long>();
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void enable(final InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lAdmin Panel"))) {
            e.setCancelled(true);
            final int j = e.getSlot();
            final Player p = (Player) e.getWhoClicked();
            if (Config.ENABLE_CREATE && j == 20) {
                final boolean check = Config.ENABLE_CREATE = Boolean.parseBoolean("false");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Gildie zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (!Config.ENABLE_CREATE && j == 20) {
                final boolean check = Config.ENABLE_CREATE = Boolean.parseBoolean("true");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Gildie zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (Config.ENABLE_CASE && j == 23) {
                final boolean check = Config.ENABLE_CASE = Boolean.parseBoolean("false");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Case zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (!Config.ENABLE_CASE && j == 23) {
                final boolean check = Config.ENABLE_CASE = Boolean.parseBoolean("true");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Case zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (Config.ENABLE_ITEMY && j == 25) {
                final boolean check = Config.ENABLE_ITEMY = Boolean.parseBoolean("false");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Itemy zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (!Config.ENABLE_ITEMY && j ==25) {
                final boolean check = Config.ENABLE_ITEMY = Boolean.parseBoolean("true");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Itemy zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (Config.ENABLE_KIT && j == 19) {
                final boolean check = Config.ENABLE_KIT = Boolean.parseBoolean("false");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Kity zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (!Config.ENABLE_KIT && j == 19) {
                final boolean check = Config.ENABLE_KIT = Boolean.parseBoolean("true");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Kity zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (Config.ENABLE_PANEL && j == 21) {
                final boolean check = Config.ENABLE_PANEL = Boolean.parseBoolean("false");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Panel zostal " + (check ? "&awlaczony" : "&cwylaczony"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (!Config.ENABLE_PANEL && j == 21) {
                final boolean check = Config.ENABLE_PANEL = Boolean.parseBoolean("true");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Panel zostal " + (check ? "&awlaczony" : "&cwylaczony"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (Config.ENABLE_WEDKA && j == 24) {
                final boolean check = Config.ENABLE_WEDKA = Boolean.parseBoolean("false");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Wedki zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (!Config.ENABLE_WEDKA && j == 24) {
                final boolean check = Config.ENABLE_WEDKA = Boolean.parseBoolean("true");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Wedki zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (Config.ENABLE_DIAMOND && j == 31) {
                final boolean check = Config.ENABLE_DIAMOND = Boolean.parseBoolean("false");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Diamentowe itemy zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (!Config.ENABLE_DIAMOND && j == 31) {
                final boolean check = Config.ENABLE_DIAMOND = Boolean.parseBoolean("true");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Diamentowe itemy zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (Config.ENABLE_GARDA && j == 13) {
                final boolean check = Config.ENABLE_GARDA = Boolean.parseBoolean("false");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Anty Gardowanie zostalo " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (!Config.ENABLE_GARDA && j == 13) {
                final boolean check = Config.ENABLE_GARDA = Boolean.parseBoolean("true");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Anty Gardowanie zostalo " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (Config.ENABLE_EFEKT && j == 22) {
                final boolean check = Config.ENABLE_EFEKT = Boolean.parseBoolean("false");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Efekty zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (!Config.ENABLE_EFEKT && j == 22) {
                final boolean check = Config.ENABLE_EFEKT = Boolean.parseBoolean("true");
                Config.saveConfig();
                for (final Player po : Bukkit.getOnlinePlayers()) {
                    String msg5 = "";
                    msg5 = (msg5 = "&7Efekty zostaly " + (check ? "&awlaczone" : "&cwylaczone"));
                    final String msg6 = "";
                    if (!Config.ENABLE_WIADOMOSCI) {
                    	AdminPanelGui.show(p);
                    	return;
                    }
                    ChatUtil.sendTitleMessage(po, msg5, msg6, 30, 70, 40);
                }
                AdminPanelGui.show(p);
                return;
            }
            if (Config.ENABLE_WIADOMOSCI && j == 40) {
                final boolean check = Config.ENABLE_WIADOMOSCI = Boolean.parseBoolean("false");
                Config.saveConfig();
                AdminPanelGui.show(p);
                return;
            }
            if (!Config.ENABLE_WIADOMOSCI && j == 40) {
                final boolean check = Config.ENABLE_WIADOMOSCI = Boolean.parseBoolean("true");
                Config.saveConfig();
                AdminPanelGui.show(p);
                return;
            }
			if (j == 52) {
                Config.reloadConfig();{
                DropFile.reloadConfig();
				p.closeInventory();
				p.sendMessage(
						ChatUtil.fixColor("&a&lSukces &7Config zostal zrestartowany"));
			}
		}
		if (j == 53) {
			p.sendMessage(
					ChatUtil.fixColor("&a&lSukces &7Serwer zostal zatrzymany"));{
			p.closeInventory();
			Bukkit.getPluginManager().disablePlugin((Plugin)ServerPlugin.getPlugin());
			Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
					"stop");
					}
				}
            }
        }
    }
