package pl.virtual.api.listeners;

import org.bukkit.enchantments.*;
import org.bukkit.event.*;
import org.bukkit.event.enchantment.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import org.bukkit.event.inventory.*;
 
public class EnchantyListener implements Listener
{
    @EventHandler
    public void Enchant(final PrepareItemEnchantEvent e) {
        final ItemStack is = e.getItem();
        if (isSet(is) && is.containsEnchantment(Enchantment.DURABILITY) && is.getEnchantmentLevel(Enchantment.DURABILITY) > 2) {
            is.removeEnchantment(Enchantment.DURABILITY);
            is.addEnchantment(Enchantment.DURABILITY, 2);
        }
        if (is.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL) && is.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 3) {
            is.removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
            is.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        }
        if (!isSet2(is) && is.containsEnchantment(Enchantment.DAMAGE_ALL) && is.getEnchantmentLevel(Enchantment.DAMAGE_ALL) > 3) {
            is.removeEnchantment(Enchantment.DAMAGE_ALL);
            is.addEnchantment(Enchantment.DAMAGE_ALL, 3);
        }
        if (is.containsEnchantment(Enchantment.FIRE_ASPECT) && is.getEnchantmentLevel(Enchantment.FIRE_ASPECT) > 1) {
            is.removeEnchantment(Enchantment.FIRE_ASPECT);
            is.addEnchantment(Enchantment.FIRE_ASPECT, 1);
        }
        if (is.containsEnchantment(Enchantment.ARROW_DAMAGE) && is.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) > 3) {
            is.removeEnchantment(Enchantment.ARROW_DAMAGE);
            is.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
        }
        if (is.containsEnchantment(Enchantment.ARROW_INFINITE) && is.getEnchantmentLevel(Enchantment.ARROW_INFINITE) < 2) {
            is.removeEnchantment(Enchantment.ARROW_INFINITE);
            is.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
        }
        if (is.containsEnchantment(Enchantment.ARROW_KNOCKBACK) && is.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK) >= 1) {
            is.removeEnchantment(Enchantment.ARROW_KNOCKBACK);
            is.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
        }
    }
    
    @EventHandler
    public void Enchant1(final PrepareItemEnchantEvent e) {
        final ItemStack is = e.getItem();
        if (isSet1(is) && is.containsEnchantment(Enchantment.DURABILITY) && is.getEnchantmentLevel(Enchantment.DURABILITY) > 3) {
            is.removeEnchantment(Enchantment.DURABILITY);
            is.addEnchantment(Enchantment.DURABILITY, 3);
        }
        if (isSet2(is) && is.containsEnchantment(Enchantment.DAMAGE_ALL) && is.getEnchantmentLevel(Enchantment.DAMAGE_ALL) > 3) {
            is.removeEnchantment(Enchantment.DAMAGE_ALL);
            is.addEnchantment(Enchantment.DAMAGE_ALL, 3);
        }
    }
   
    @EventHandler
    public void onInventoryOpen(final InventoryOpenEvent e) {
        if (e.getInventory().getType().equals((Object)InventoryType.ENCHANTING)) {
            final EnchantingInventory en = (EnchantingInventory)e.getInventory();
            en.setSecondary(new ItemStack(Material.INK_SAC, 64, (short)4));
        }
    }
   
    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        if (event.getInventory().getType().equals((Object)InventoryType.ENCHANTING)) {
            event.getInventory().setItem(1, (ItemStack)null);
        }
    }
   
    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getInventory().getType().equals((Object)InventoryType.ENCHANTING) && event.getRawSlot() == 1) {
            event.setCancelled(true);
        }
    }
   
    public static boolean isSet(final ItemStack e) {
        switch (e.getType()) {
            case DIAMOND_HELMET: {
                return true;
            }
            case DIAMOND_CHESTPLATE: {
                return true;
            }
            case DIAMOND_LEGGINGS: {
                return true;
            }
            case DIAMOND_BOOTS: {
                return true;
            }
            case ENCHANTED_BOOK: {
                return true;
            }
            case GOLDEN_HELMET: {
                return true;
            }
            case GOLDEN_CHESTPLATE: {
                return true;
            }
            case GOLDEN_LEGGINGS: {
                return true;
            }
            case GOLDEN_BOOTS: {
                return true;
            }
            case LEATHER_HELMET: {
                return true;
            }
            case LEATHER_CHESTPLATE: {
                return true;
            }
            case LEATHER_LEGGINGS: {
                return true;
            }
            case LEATHER_BOOTS: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isSet1(final ItemStack e) {
        switch (e.getType()) {
            case IRON_HELMET: {
                return true;
            }
            case IRON_CHESTPLATE: {
                return true;
            }
            case IRON_LEGGINGS: {
                return true;
            }
            case IRON_BOOTS: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isSet2(final ItemStack e) {
        switch (e.getType()) {
            case IRON_SWORD: {
                return true;
            }
            case IRON_AXE: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
}