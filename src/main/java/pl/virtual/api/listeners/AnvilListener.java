package pl.virtual.api.listeners;

import org.bukkit.event.inventory.*;
import org.bukkit.enchantments.*;
import org.bukkit.inventory.*;


import org.bukkit.event.*;

public class AnvilListener implements Listener
{
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        final ItemStack item = e.getCurrentItem();
        if (e.getInventory().getType() == InventoryType.ANVIL && e.getRawSlot() == 2) {
            if (item == null || item.getEnchantments().size() == 0) {
                return;
            }
            if (EnchantyListener.isSet(item)) {
                if (item.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL) && item.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 3) {
                    item.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
                    item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                }
            } 
            if (EnchantyListener.isSet(item)) {
                if (item.containsEnchantment(Enchantment.DURABILITY) && item.getEnchantmentLevel(Enchantment.DURABILITY) > 2) {
                    item.removeEnchantment(Enchantment.DURABILITY);
                    item.addEnchantment(Enchantment.DURABILITY, 2);
                }
                return;
            }
            if (!EnchantyListener.isSet2(item) && item.containsEnchantment(Enchantment.DAMAGE_ALL) && item.getEnchantmentLevel(Enchantment.DAMAGE_ALL) > 3) {
                item.removeEnchantment(Enchantment.DAMAGE_ALL);
                item.addEnchantment(Enchantment.DAMAGE_ALL, 3);
            }
            if (item.containsEnchantment(Enchantment.ARROW_DAMAGE) && item.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) > 3) {
                item.removeEnchantment(Enchantment.ARROW_DAMAGE);
                item.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
            }
            if (item.containsEnchantment(Enchantment.FIRE_ASPECT) && item.getEnchantmentLevel(Enchantment.FIRE_ASPECT) > 1) {
                item.removeEnchantment(Enchantment.FIRE_ASPECT);
                item.addEnchantment(Enchantment.FIRE_ASPECT, 1);
            }
            if (item.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL) && item.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 3) {
            	item.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
            	item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
            }
            if (item.containsEnchantment(Enchantment.ARROW_INFINITE) && item.getEnchantmentLevel(Enchantment.ARROW_INFINITE) < 2) {
            	item.removeEnchantment(Enchantment.ARROW_INFINITE);
                item.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
            }
            if (item.containsEnchantment(Enchantment.ARROW_KNOCKBACK) && item.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK) >= 1) {
            	item.removeEnchantment(Enchantment.ARROW_KNOCKBACK);
                item.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
            }
            if (EnchantyListener.isSet2(item)) {
                if (item.containsEnchantment(Enchantment.DAMAGE_ALL) && item.getEnchantmentLevel(Enchantment.DAMAGE_ALL) > 3) {
                    item.removeEnchantment(Enchantment.DAMAGE_ALL);
                    item.addEnchantment(Enchantment.DAMAGE_ALL, 3);
                }
                return;
            }
        }
    }
}
