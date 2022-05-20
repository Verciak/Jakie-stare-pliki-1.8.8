// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.listeners;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;

import org.bukkit.inventory.ItemStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.Material;

import pl.virtual.api.API.Config;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemBuilder;
import pl.virtual.api.utils.RandomUtil;
import org.bukkit.GameMode;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.Listener;

public class EventyListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(final BlockBreakEvent e) {
        final Player p = e.getPlayer();
        final User u = UserManager.getUser(p);
        if (!p.getGameMode().equals((Object)GameMode.SURVIVAL)) {
            return;
        }
        if (Config.STONE > System.currentTimeMillis() && RandomUtil.getChance(0.1)) {
            final ItemStack d = new ItemBuilder(Material.ENDER_CHEST, 1).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &9&lCase &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Jest to jeden z cenniejszych")).addLore(ChatUtil.fixColor("&8� &7Przedmiotow ktore mozna zdobyc w grze")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &7Przedmioty jakie mozesz zdobyc")).addLore(ChatUtil.fixColor("&8� &7Z magicznej skrzynki znajdziesz pod &8&l/Drop")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8� &8&lDarkCase &7zakupisz na")).addLore(ChatUtil.fixColor("&8� &7darkhard.pl")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("             &f&lAby otworzyc, kliknij PPM")).addLore(ChatUtil.fixColor("")).build();
            for (final Player po : Bukkit.getOnlinePlayers()) {
                final User u2 = UserManager.getUser(po);
                if (u2.isEventMessages()) {
                    ChatUtil.sendMessage((CommandSender)po, "&8[&4&lEVENT&8] &7Gracz &c" + p.getName() + " &7wydropil &8&lDarkCase");
                }
            }
            ChatUtil.sendTitle(Bukkit.getOnlinePlayers(), "&8[&4&lEVENT&8]", "&7Trafiles na: &8&lDarkCase");
            u.setExp(u.getExp() + 20);
            ChatUtil.giveItems(p, d);
        }
    }
}

