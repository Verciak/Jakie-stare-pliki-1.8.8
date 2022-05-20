// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import java.util.Iterator;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import pl.virtual.api.utils.DropUtil;
import pl.virtual.api.utils.RandomUtil;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.Listener;

public class EntityDeathListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDeath(final EntityDeathEvent e) {
        final List<ItemStack> drops = (List<ItemStack>)e.getDrops();
        if (e.getEntity().getKiller() == null) {
            return;
        }
        final Player killer = e.getEntity().getKiller();
        if (e.getEntity().getType() == EntityType.CREEPER) {
            removeItem(Material.LEGACY_SULPHUR, drops);
            final ItemStack item = new ItemStack(Material.LEGACY_SULPHUR, getAmount(killer.getItemInHand()));
            drops.add(item);
        }
        if (killer.equals(e.getEntity())) {
            return;
        }
        DropUtil.addItemsToPlayer(killer, drops, e.getEntity().getLocation().getBlock());
        killer.giveExp(e.getDroppedExp());
        e.setDroppedExp(0);
        e.getDrops().clear();
    }
    
    public static int getAmount(final ItemStack tool) {
        if (tool == null) {
            return RandomUtil.getRandInt(2, 5);
        }
        if (!tool.containsEnchantment(Enchantment.LOOT_BONUS_MOBS)) {
            return RandomUtil.getRandInt(2, 5);
        }
        switch (tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS)) {
            case 1: {
                return RandomUtil.getRandInt(3, 8);
            }
            case 2: {
                return RandomUtil.getRandInt(4, 11);
            }
            case 3: {
                return RandomUtil.getRandInt(5, 15);
            }
            default: {
                return RandomUtil.getRandInt(3, 8);
            }
        }
    }
    
    public static void removeItem(final Material type, final List<ItemStack> items) {
        final Iterator<ItemStack> it = items.iterator();
        ItemStack i = null;
        while (it.hasNext()) {
            i = it.next();
            if (i.getType() == type) {
                it.remove();
            }
        }
    }
}
