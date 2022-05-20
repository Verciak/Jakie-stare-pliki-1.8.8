package pl.virtual.api.utils;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import pl.virtual.api.ServerPlugin;
import pl.virtual.api.trade.Trade;
import pl.virtual.api.trade.TradeOptions;

public class TradeUtil
{
    private static ItemStack separator;
    private static ItemStack n;
    private static ItemStack y;
    
    public static boolean isLeftSide(final int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 9:
            case 10:
            case 11:
            case 12:
            case 18:
            case 19:
            case 20:
            case 21:
            case 27:
            case 28:
            case 29:
            case 30:
            case 36:
            case 37:
            case 38:
            case 39:
            case 45:
            case 46:
            case 47:
            case 48: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean canClickSlot(final Set<Integer> set) {
        final Integer[] intt = { 4, 13, 22, 31, 40, 49, 5, 14, 23, 32, 41, 50, 6, 15, 24, 33, 42, 51, 7, 16, 25, 34, 43, 52, 8, 17, 26, 35, 44, 53 };
        Integer[] array;
        for (int length = (array = intt).length, j = 0; j < length; ++j) {
            final int i = array[j];
            if (set.contains(i)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean canClickSlot(final int slot) {
        final Integer[] intt = { 4, 13, 22, 31, 40, 49, 5, 14, 23, 32, 41, 50, 6, 15, 24, 33, 42, 51, 7, 16, 25, 34, 43, 52, 8, 17, 26, 35, 44, 53 };
        Integer[] array;
        for (int length = (array = intt).length, j = 0; j < length; ++j) {
            final int i = array[j];
            if (slot == i) {
                return false;
            }
        }
        return true;
    }
    
    public static Integer[] getLeftSideSlots() {
        return new Integer[] { 48, 39, 30, 21, 12, 3, 47, 38, 29, 20, 11, 2, 46, 37, 28, 19, 10, 1, 45, 36, 27, 18, 9, 0 };
    }
    
    public static Integer[] getRightSideSlots() {
        return new Integer[] { 5, 14, 23, 32, 41, 50, 6, 15, 24, 33, 42, 51, 7, 16, 25, 34, 43, 52, 8, 17, 26, 35, 44, 53 };
    }
    
    public static void playTradeAcceptAnimation(final TradeOptions tradeOptions) {
        new BukkitRunnable() {
            int i = 4;
            int i2 = 49;
            boolean t = true;
            
            public void run() {
                if (tradeOptions.getTrade() == null) {
                    this.cancel();
                }
                if (!tradeOptions.getTrade().getU1().getTradeOptions().isReady()) {
                    tradeOptions.getTrade().getI1().setItem(13, TradeUtil.separator);
                    tradeOptions.getTrade().getI1().setItem(22, TradeUtil.separator);
                    tradeOptions.getTrade().getI1().setItem(31, TradeUtil.separator);
                    tradeOptions.getTrade().getI1().setItem(40, TradeUtil.separator);
                    tradeOptions.getTrade().getI1().setItem(49, TradeUtil.n);
                    tradeOptions.getTrade().getI2().setItem(4, TradeUtil.n);
                    tradeOptions.getTrade().getI2().setItem(13, TradeUtil.separator);
                    tradeOptions.getTrade().getI2().setItem(22, TradeUtil.separator);
                    tradeOptions.getTrade().getI2().setItem(31, TradeUtil.separator);
                    tradeOptions.getTrade().getI2().setItem(40, TradeUtil.separator);
                    this.t = false;
                }
                if (!tradeOptions.getTrade().getU2().getTradeOptions().isReady()) {
                    tradeOptions.getTrade().getI2().setItem(13, TradeUtil.separator);
                    tradeOptions.getTrade().getI2().setItem(22, TradeUtil.separator);
                    tradeOptions.getTrade().getI2().setItem(31, TradeUtil.separator);
                    tradeOptions.getTrade().getI2().setItem(40, TradeUtil.separator);
                    tradeOptions.getTrade().getI2().setItem(49, TradeUtil.n);
                    tradeOptions.getTrade().getI1().setItem(4, TradeUtil.n);
                    tradeOptions.getTrade().getI1().setItem(13, TradeUtil.separator);
                    tradeOptions.getTrade().getI1().setItem(22, TradeUtil.separator);
                    tradeOptions.getTrade().getI1().setItem(31, TradeUtil.separator);
                    tradeOptions.getTrade().getI1().setItem(40, TradeUtil.separator);
                    this.t = false;
                }
                if (!this.t) {
                    this.cancel();
                    return;
                }
                this.i += 9;
                this.i2 -= 9;
                tradeOptions.getTrade().getI2().setItem(this.i, TradeUtil.y);
                tradeOptions.getTrade().getI2().setItem(this.i2, TradeUtil.y);
                tradeOptions.getTrade().getI1().setItem(this.i, TradeUtil.y);
                tradeOptions.getTrade().getI1().setItem(this.i2, TradeUtil.y);
                if (this.i > 22) {
                    TradeUtil.endTrade(tradeOptions.getTrade());
                    this.cancel();
                }
            }
        }.runTaskTimer(ServerPlugin.getPlugin(), 20L, 20L);
    }
    
    public static void updateTrade(final TradeOptions tradeOptions) {
        Bukkit.getScheduler().runTaskLater(ServerPlugin.getPlugin(), () -> {
        	Integer[] arrinteger = getLeftSideSlots();
        	int n = 0;
        	int n2 = 0;
        	int i = arrinteger[n2];
            for (n = arrinteger.length, n2 = 0; n2 < n; ++n2) {
                i = arrinteger[n2];
                tradeOptions.getTrade().getI2().setItem(i + 5, tradeOptions.getTrade().getI1().getItem(i));
            }
            int n3;
            int n4;
            int j;
            final Integer[] arrinteger2 = getLeftSideSlots();
            for (n3 = arrinteger2.length, n4 = 0; n4 < n3; ++n4) {
                j = arrinteger2[n4];
                tradeOptions.getTrade().getI1().setItem(j + 5, tradeOptions.getTrade().getI2().getItem(j));
            }
        }, 2L);
    }
    
    public static void endTrade(final Trade trade) {
        trade.getU2().getTradeOptions().setIsTrading(false);
        trade.getU1().getTradeOptions().setIsTrading(false);
        trade.getU2().getTradeOptions().setTrade(null);
        trade.getU1().getTradeOptions().setTrade(null);
        trade.getU2().getTradeOptions().setReady(false);
        trade.getU1().getTradeOptions().setReady(false);
        trade.getU2().getPlayer().closeInventory();
        trade.getU1().getPlayer().closeInventory();
        Integer[] rightSideSlots;
        for (int length = (rightSideSlots = getRightSideSlots()).length, j = 0; j < length; ++j) {
            final int i = rightSideSlots[j];
            if (trade.getI2().getItem(i) != null) {
                ItemsUtil.addItemsToPlayer(trade.getU2().getPlayer(), trade.getI2().getItem(i), trade.getU2().getPlayer().getLocation().getBlock());
            }
            if (trade.getI1().getItem(i) != null) {
                ItemsUtil.addItemsToPlayer(trade.getU1().getPlayer(), trade.getI1().getItem(i), trade.getU1().getPlayer().getLocation().getBlock());
            }
        }
    }
    
    static {
        TradeUtil.separator = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1).setTitle(" ").build();
        TradeUtil.n = new ItemBuilder(Material.RED_STAINED_GLASS_PANE, 1).setTitle(ChatUtil.fixColor("&c&lBRAK GOTOWOSCI DO WYMIANY")).build();
        TradeUtil.y = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE, 1).setTitle(ChatUtil.fixColor("&a&cAKCEPTACJA W TOKU...")).build();
    }
}

