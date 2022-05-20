package pl.virtual.api.trade;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import pl.virtual.api.data.base.user.User;
import pl.virtual.api.utils.ChatUtil;

public class Trade
{
    private final User u1;
    private final User u2;
    private final Inventory i1;
    private final Inventory i2;
    
    public Trade(final User u1, final User u2) {
        this.u1 = u1;
        this.u2 = u2;
        this.i1 = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.fixColor("&4&lWymiana: &7" + u2.getName()));
        this.i2 = Bukkit.createInventory((InventoryHolder)null, 54, ChatUtil.fixColor("&4&lWymiana: &7" + u1.getName()));
    }
    
    public User getU1() {
        return this.u1;
    }
    
    public User getU2() {
        return this.u2;
    }
    
    public Inventory getI1() {
        return this.i1;
    }
    
    public Inventory getI2() {
        return this.i2;
    }
}

