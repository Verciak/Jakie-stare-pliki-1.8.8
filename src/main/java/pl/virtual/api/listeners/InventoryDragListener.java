package pl.virtual.api.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.trade.TradeOptions;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.TradeUtil;

public class InventoryDragListener implements Listener
{
    @EventHandler
    public void onInventoryDragEvent(final InventoryDragEvent e) {
        if (e.getView().getTitle().contains(ChatUtil.fixColor("&4&lWymiana:"))) {
            final User tradeOption = UserManager.getUser(e.getWhoClicked().getName());
            final TradeOptions tradeOptions =tradeOption.getTradeOptions();
            if (tradeOptions.isTrading()) {
                if (TradeUtil.canClickSlot(e.getRawSlots())) {
                    TradeUtil.updateTrade(tradeOptions);
                }
                else {
                    e.setCancelled(true);
                    ChatUtil.sendMessage((Player)e.getWhoClicked(), "&9&lERROR: &7Nie mozesz modyfikowac tego miejsca");
                }
            }
        }
    }
}
