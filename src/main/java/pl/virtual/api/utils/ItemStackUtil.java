// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.Material;

public class ItemStackUtil
{
    public static int getAmountOfItem(final Material material, final Player player, final short durability) {
        int amount = 0;
        ItemStack[] contents;
        for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack itemStack = contents[i];
            if (itemStack != null && itemStack.getType().equals((Object)material) && itemStack.getDurability() == durability) {
                amount += itemStack.getAmount();
            }
        }
        return amount;
    }
    
    public static int remove(final ItemStack base, final Player player, final int amount) {
        int actual = 0;
        int remaining = amount;
        ItemStack[] contents;
        for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
            final ItemStack itemStack = contents[i];
            if (actual == amount) {
                break;
            }
            if (itemStack != null && itemStack.getType().equals((Object)base.getType()) && itemStack.getDurability() == base.getDurability()) {
                if (remaining == 0) {
                    actual += itemStack.getAmount();
                    player.getInventory().remove(itemStack);
                }
                else if (itemStack.getAmount() >= amount) {
                    actual += itemStack.getAmount() - amount;
                    itemStack.setAmount(amount);
                    remaining = 0;
                }
                else {
                    final int add = itemStack.getAmount();
                    remaining -= add;
                    player.getInventory().remove(itemStack);
                    actual += add;
                }
            }
        }
        return actual;
    }
}
