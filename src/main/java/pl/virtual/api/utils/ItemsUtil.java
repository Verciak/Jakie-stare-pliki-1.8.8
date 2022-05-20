package pl.virtual.api.utils;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ItemsUtil
{
    public static void recalculateDurability(final Player player, final ItemStack item) {
        if (item.getType().getMaxDurability() == 0) {
            return;
        }
        final int enchantLevel = item.getEnchantmentLevel(Enchantment.DURABILITY);
        final short d = item.getDurability();
        if (enchantLevel > 0) {
            if (100 / (enchantLevel + 1) > RandomUtil.getRandInt(0, 100)) {
                if (d == item.getType().getMaxDurability()) {
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                    player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
                }
                else {
                    item.setDurability((short)(d + 1));
                }
            }
        }
        else if (d == item.getType().getMaxDurability()) {
            player.getInventory().clear(player.getInventory().getHeldItemSlot());
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
        }
        else {
            item.setDurability((short)(d + 1));
        }
    }
    
    public static void addItemsToPlayer(final Player player, final List<ItemStack> items, final Block b) {
        final PlayerInventory inv = player.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)inv.addItem((ItemStack[])items.toArray(new ItemStack[items.size()]));
        for (final Map.Entry<Integer, ItemStack> en : notStored.entrySet()) {
            b.getWorld().dropItemNaturally(b.getLocation(), (ItemStack)en.getValue());
        }
    }
    
    public static void addItemsToPlayer(final Player player, final ItemStack items, final Block b) {
        final PlayerInventory inv = player.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)inv.addItem(new ItemStack[] { items });
        for (final Map.Entry<Integer, ItemStack> en : notStored.entrySet()) {
            player.getWorld().dropItemNaturally(player.getLocation(), (ItemStack)en.getValue());
        }
    }
    
    public static void addItemsToPlayer(final Player player, final ItemStack items) {
        final PlayerInventory inv = player.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)inv.addItem(new ItemStack[] { items });
        for (final Map.Entry<Integer, ItemStack> en : notStored.entrySet()) {
            player.getWorld().dropItemNaturally(player.getLocation(), (ItemStack)en.getValue());
        }
    }
}

