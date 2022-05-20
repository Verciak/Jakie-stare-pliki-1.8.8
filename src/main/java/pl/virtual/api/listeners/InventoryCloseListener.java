package pl.virtual.api.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.trade.Trade;
import pl.virtual.api.trade.TradeOptions;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemsUtil;
import pl.virtual.api.utils.TradeUtil;

public class InventoryCloseListener implements Listener
{
    @EventHandler
    public void onInventoryCloseEvent(final InventoryCloseEvent e) {
        if (e.getView().getTitle().contains(ChatUtil.fixColor("&4&lWymiana:"))) {
            final User tradeOption = UserManager.getUser(e.getPlayer().getName());
            TradeOptions tradeOptions = tradeOption.getTradeOptions();
            final Trade trade = tradeOptions.getTrade();
            if (tradeOptions.isTrading()) {
                trade.getU2().getTradeOptions().setIsTrading(false);
                trade.getU1().getTradeOptions().setIsTrading(false);
                trade.getU2().getTradeOptions().setReady(false);
                trade.getU1().getTradeOptions().setReady(false);
                if (e.getPlayer().equals(trade.getU1().getPlayer())) {
                    trade.getU1().getPlayer().sendMessage(ChatUtil.fixColor("&8[&4&lWYMIANA&8] &7Zakonczyles wymiane"));
                    trade.getU2().getPlayer().sendMessage(ChatUtil.fixColor("&8[&4&lWYMIANA&8] &7Druga osoba zakonczyla wymiane"));
                    trade.getU2().getPlayer().closeInventory();
                }
                else {
                    trade.getU2().getPlayer().sendMessage(ChatUtil.fixColor("&8[&4&lWYMIANA&8] &7Zakonczyles wymiane"));
                    trade.getU1().getPlayer().sendMessage(ChatUtil.fixColor("&8[&4&lWYMIANA&8] &7Druga osoba zakonczyla wymiane"));
                    trade.getU1().getPlayer().closeInventory();
                }
                Integer[] leftSideSlots;
                for (int length = (leftSideSlots = TradeUtil.getLeftSideSlots()).length, j = 0; j < length; ++j) {
                    final int i = leftSideSlots[j];
                    if (trade.getI2().getItem(i) != null) {
                        if (trade.getU2().getPlayer().getInventory().firstEmpty() == -1) {
                            ItemsUtil.addItemsToPlayer(trade.getU2().getPlayer(), trade.getI2().getItem(i), trade.getU2().getPlayer().getLocation().getBlock());
                        }
                        else {
                            trade.getU2().getPlayer().getInventory().addItem(new ItemStack[] { trade.getI2().getItem(i) });
                        }
                    }
                    if (trade.getI1().getItem(i) != null) {
                        if (trade.getU1().getPlayer().getInventory().firstEmpty() == -1) {
                            ItemsUtil.addItemsToPlayer(trade.getU1().getPlayer(), trade.getI1().getItem(i), trade.getU1().getPlayer().getLocation().getBlock());
                        }
                        else {
                            trade.getU1().getPlayer().getInventory().addItem(new ItemStack[] { trade.getI1().getItem(i) });
                        }
                    }
                }
                trade.getU1().getPlayer().updateInventory();
                trade.getU2().getPlayer().updateInventory();
            }
        }
    }
}

