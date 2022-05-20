package pl.virtual.api.listeners;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.*;
import org.bukkit.event.*;

public class EfektListener implements Listener
{
	private static HashMap<UUID, Long> times;
    
    static {
        times = new HashMap<UUID, Long>();
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void efekty(final InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&4&lEfekty"))) {
            e.setCancelled(true);
            final int j = e.getSlot();
            final Player p = (Player) e.getWhoClicked();
            final User u = UserManager.getUser(p);
            if (j == 0) {
                if (!Config.ENABLE_EFEKT) {
                    ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Ten efekt jest tymczasowo wylaczony");
                    e.setCancelled(true);
                    return;
                }
				if (u.getCoins() < 500) {
					p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Nie posiadasz 500 coins"));
					return;
				}
				if (u.getCoins() > 499) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2400, 1));
                    u.removeCoins(500);
                    ChatUtil.sendMessage((CommandSender)p, "&8[&4&lEFEKTY&8] &7Pomyslnie zakupiono efekt &c&lSPEED II");
                    return;
                }
            }
            else if (j == 4) {
                if (!Config.ENABLE_EFEKT) {
                    ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Ten efekt jest tymczasowo wylaczony");
                    e.setCancelled(true);
                    return;
                }
				if (u.getCoins() < 1000) {
					p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Nie posiadasz 1000 coins"));
					return;
				}
		    	final Long t = EfektListener.times.get(p.getUniqueId());
		    	if (t != null && System.currentTimeMillis() - t < 900000L) {
		    		ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Tego Boosta mozna uzyc co 15m");
		    		return;
		    	}
				if (u.getCoins() > 999) {
			    	EfektListener.times.put(p.getUniqueId(), System.currentTimeMillis());
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 2400, 1));
                    u.removeCoins(1000);
                    ChatUtil.sendMessage((CommandSender)p, "&8[&4&lEFEKTY&8] &7Pomyslnie zakupiono efekt &c&lHASTE II");
                    return;
                }
            }
            else if (j == 1) {
                if (!Config.ENABLE_EFEKT) {
                    ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Ten efekt jest tymczasowo wylaczony");
                    e.setCancelled(true);
                    return;
                }
				if (u.getCoins() < 400) {
					p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Nie posiadasz 400 coins"));
					return;
				}
				if (u.getCoins() > 399) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2400, 2));
                    u.removeCoins(400);
                    ChatUtil.sendMessage((CommandSender)p, "&8[&4&lEFEKTY&8] &7Pomyslnie zakupiono efekt &c&lJUMP BOOST");
                    return;
                }
            }
            else if (j == 2) {
                if (!Config.ENABLE_EFEKT) {
                    ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Ten efekt jest tymczasowo wylaczony");
                    e.setCancelled(true);
                    return;
                }
				if (u.getCoins() < 1200) {
					p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Nie posiadasz 1200 coins"));
					return;
				}
				if (u.getCoins() > 1199) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2400, 0));
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2400, 1));
					p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 2400, 0));
                    u.removeCoins(1200);
                    ChatUtil.sendMessage((CommandSender)p, "&8[&4&lEFEKTY&8] &7Pomyslnie zakupiono efekt &c&lZESTAW NAPIE#DALACZA");
                    return;
                }
            }
            else if (j == 3) {
                if (!Config.ENABLE_EFEKT) {
                    ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Ten efekt jest tymczasowo wylaczony");
                    e.setCancelled(true);
                    return;
                }
				if (u.getCoins() < 700) {
					p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Nie posiadasz 700 coins"));
					return;
				}
				if (u.getCoins() > 699) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2400, 0));
                    u.removeCoins(700);
                    ChatUtil.sendMessage((CommandSender)p, "&8[&4&lEFEKTY&8] &7Pomyslnie zakupiono efekt &c&lSTRENGHT I");
                    return;
                }
            }
        }
    }
}
