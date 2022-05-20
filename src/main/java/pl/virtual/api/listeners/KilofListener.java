// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pl.virtual.api.utils.ChatUtil;

public class KilofListener implements Listener
{
    @EventHandler
    public void onMine(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack s = e.getPlayer().getItemInHand();
        final Action ea = e.getAction();
        if ((ea.equals((Object)Action.LEFT_CLICK_AIR) || ea.equals((Object)Action.LEFT_CLICK_BLOCK)) && e.getMaterial().equals((Object)Material.DIAMOND_PICKAXE) && s.getDurability() == 1555 && p.getItemInHand().getEnchantmentLevel(Enchantment.DIG_SPEED) > 5) {
        	p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 100));
        	p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 0));
            s.setDurability((short)1555);
            p.updateInventory();
            p.sendMessage(ChatUtil.fixColor("&9&lERROR: &7Twoj kilof jest na wyczerpaniu, Niszczenie zostalo zablokowane"));
            e.setCancelled(true);

        }
    }
}
