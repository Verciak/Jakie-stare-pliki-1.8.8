package pl.virtual.api.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.trade.Trade;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemBuilder;

public class PlayerInteractEntityListener implements Listener
{
    private static ItemStack separator;
    private static ItemStack n;
    
    public static boolean isInSpawn(final Location loc) {
        return loc.getBlockX() <= Config.REGION_SPAWN && loc.getBlockX() >= -Config.REGION_SPAWN - 1 && loc.getBlockZ() <= Config.REGION_SPAWN && loc.getBlockZ() >= -Config.REGION_SPAWN - 1;
    }
    
    @EventHandler
    public void onPlayerInteractEntityEvent(final PlayerInteractEntityEvent e) {
        final User u1 = UserManager.getUser(e.getPlayer());
        if(e.getRightClicked() instanceof Player) {
        	final User u2 = UserManager.getUser((Player)e.getRightClicked());
        if (e.getRightClicked() == null) {
            return;
        }
        if (!isInSpawn(e.getPlayer().getLocation())) {
        	return;
        }
        if (isInSpawn(e.getPlayer().getLocation()) && e.getPlayer().isSneaking()) {
            if (e.getRightClicked() instanceof Player) {
                if (u2.getTradeOptions().isTrading()) {
                    u1.getPlayer().sendMessage(ChatUtil.fixColor("&9&lERROR: &7Ten gracz aktualnie prowadzi handel z kims innym"));
                    return;
                }
                if (u1.getTradeOptions().getLastOffer() != null && u1.getTradeOptions().getLastOffer().equals(u2)) {
                    final Trade trade = new Trade(u1, u2);
                    final Inventory i1 = trade.getI1();
                    final Inventory i2 = trade.getI2();
                    u1.getTradeOptions().setTrade(trade);
                    u1.getTradeOptions().setIsTrading(true);
                    u2.getTradeOptions().setTrade(trade);
                    u2.getTradeOptions().setIsTrading(true);
                    i1.setItem(4, PlayerInteractEntityListener.n);
                    i1.setItem(13, PlayerInteractEntityListener.separator);
                    i1.setItem(22, PlayerInteractEntityListener.separator);
                    i1.setItem(31, PlayerInteractEntityListener.separator);
                    i1.setItem(40, PlayerInteractEntityListener.separator);
                    i1.setItem(49, PlayerInteractEntityListener.n);
                    i2.setItem(4, PlayerInteractEntityListener.n);
                    i2.setItem(13, PlayerInteractEntityListener.separator);
                    i2.setItem(22, PlayerInteractEntityListener.separator);
                    i2.setItem(31, PlayerInteractEntityListener.separator);
                    i2.setItem(40, PlayerInteractEntityListener.separator);
                    i2.setItem(49, PlayerInteractEntityListener.n);
                    u1.getPlayer().openInventory(i1);
                    u2.getPlayer().openInventory(i2);
                    u1.getTradeOptions().setLastOffer(null);
                }
                else {
                    if (u2.getTradeOptions().getLastOffer() == u1) {
                        u1.getPlayer().sendMessage(ChatUtil.fixColor("&9&lERROR: &7Ten gracz otrzymal juz od ciebie zapytanie o wymiane"));
                        return;
                    }
                    u2.getTradeOptions().setLastOffer(u1);
                    u1.getPlayer().sendMessage(ChatUtil.fixColor("&8[&4&lWYMIANA&8] &7Wyslano prosbe o wymiane do &c" + u2.getName()));
                    ChatUtil.sendMessage(u2.getPlayer(), ChatUtil.fixColor("&7Gracz&c " + u1.getName() + " &azaprasza&7 cie do wymiany"));
                    ChatUtil.sendMessage(u2.getPlayer(), ChatUtil.fixColor("&7Aby &azaakceptowac &7wymiane kucnij (shift) i nacisnij na niego prawym"));
                }
            }
        }
        }
    }
    
    static {
        PlayerInteractEntityListener.separator = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE, 1).setTitle(" ").build();
        PlayerInteractEntityListener.n = new ItemBuilder(Material.RED_STAINED_GLASS_PANE, 1).setTitle(ChatUtil.fixColor("&c&lBRAK GOTOWOSCI DO WYMIANY")).build();
    }
}

