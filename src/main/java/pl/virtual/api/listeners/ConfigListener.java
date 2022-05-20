package pl.virtual.api.listeners;

import org.bukkit.event.*;
import org.bukkit.*;
import org.bukkit.command.*;

import pl.virtual.api.*;
import pl.virtual.api.API.Config;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.*;

public class ConfigListener implements Listener {
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onClickBackup(final InventoryClickEvent e) {
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lConfig"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final Player p = (Player) e.getWhoClicked();
			final int slot4 = e.getSlot();
			if (slot4 == 4) {
	                Config.reloadConfig();{
	                DropFile.reloadConfig();
					p.closeInventory();
					p.sendMessage(
							ChatUtil.fixColor("&a&lSukces &7Config zostal zrestartowany"));
				}
			}
			if (slot4 == 0) {
				p.closeInventory();{
				p.sendMessage(
						ChatUtil.fixColor("&a&lSukces &7Plugin &fDarkHard_Core &7zostal zatrzymany"));
				Bukkit.getPluginManager().disablePlugin((Plugin)ServerPlugin.getPlugin());
                }
			}
			if (slot4 == 2) {
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

