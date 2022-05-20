package pl.virtual.api.listeners;

import org.bukkit.event.*;
import org.bukkit.*;
import org.bukkit.command.*;

import java.util.*;

import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.*;

import org.bukkit.entity.*;
import org.bukkit.event.inventory.*;

public class TurboDropListener implements Listener {
		private static HashMap<UUID, Long> times;
	    
	    static {
	        times = new HashMap<UUID, Long>();
	    }
		private static HashMap<UUID, Long> times1;
	    
	    static {
	        times1 = new HashMap<UUID, Long>();
	    }
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onClickBackup(final InventoryClickEvent e) {
		if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lTurbo"))) {
			e.setCancelled(true);
			e.setResult(Event.Result.DENY);
			final Player p = (Player) e.getWhoClicked();
			final User u2 = UserManager.getUser(p);
			final int slot4 = e.getSlot();
			if (slot4 == 3) {
		    	final Long t = TurboDropListener.times.get(p.getUniqueId());
		    	if (t != null && System.currentTimeMillis() - t < 43200000L) {
		    		ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Tego Boosta mozna uzyc co 12h");
		    		p.closeInventory();
		    		return;
		    	}
		    	TurboDropListener.times.put(p.getUniqueId(), System.currentTimeMillis());
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo drop " + u2.getName() + " 30m");
	    		p.closeInventory();
			}
			if (slot4 == 1) {
		    	final Long t = TurboDropListener.times1.get(p.getUniqueId());
		    	if (t != null && System.currentTimeMillis() - t < 43200000L) {
		    		ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Tego Boosta mozna uzyc co 12h");
		    		p.closeInventory();
		    		return;
		    	}
		    	TurboDropListener.times1.put(p.getUniqueId(), System.currentTimeMillis());
                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo exp " + u2.getName() + " 30m");
	    		p.closeInventory();
			}
		}
	}
}

