package pl.virtual.api.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemBuilder;

public class ZwojListener implements Listener
{
	
    @EventHandler(priority = EventPriority.NORMAL)
    public void OnClick(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = p.getItemInHand();
        final ItemBuilder exp = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA TURBOEXP 15m")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac boosta")).addLore(ChatUtil.fixColor(""));
        if (item.getType() != Material.PAPER) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&4&lVOUCHER NA TURBOEXP 15m"))) {
            return;
        }
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo exp " + p.getName() + " 15m");
        ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lVOUCHER&8] &7Gracz &c" + p.getName() + " &7aktywowal Voucher na TurboEXP 15m");
        ChatUtil.removeItems(p, exp.build());
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void OnClick7(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = p.getItemInHand();
        final ItemBuilder vip = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA DARK'A")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        if (item.getType() != Material.PAPER) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&4&lVOUCHER NA DARK'A"))) {
            return;
        }
    	if(p.hasPermission("admin")) {
            ChatUtil.sendMessage(p, "&9&lERROR: &7Masz wieksza range");
            return;
    	}
    	if(p.hasPermission("dark")) {
            ChatUtil.sendMessage(p, "&9&lERROR: &7Masz wieksza range");
            return;
    	}
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set vip");
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set svip");
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set dark");
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "pex user " + p.getName() + " group set DARK");
        ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lVOUCHER&8] &7Gracz &c" + p.getName() + " &7aktywowal Voucher na DARK'a");
        ChatUtil.removeItems(p, vip.build());
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void OnClick1(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = p.getItemInHand();
        final ItemBuilder vip = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA VIP'A")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        if (item.getType() != Material.PAPER) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&4&lVOUCHER NA VIP'A"))) {
            return;
        }
    	if(p.hasPermission("admin")) {
            ChatUtil.sendMessage(p, "&9&lERROR: &7Masz wieksza range");
            return;
    	}
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set vip");
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "pex user " + p.getName() + " group set VIP");
        ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lVOUCHER&8] &7Gracz &c" + p.getName() + " &7aktywowal Voucher na VIP'a");
        ChatUtil.removeItems(p, vip.build());
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void OnClick2(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = p.getItemInHand();
        final ItemBuilder vip3d = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA VIP'A 3d")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        if (item.getType() != Material.PAPER) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&4&lVOUCHER NA VIP'A 3d"))) {
            return;
        }
    	if(p.hasPermission("admin")) {
            ChatUtil.sendMessage(p, "&9&lERROR: &7Masz wieksza range");
            return;
    	}
    	if(p.hasPermission("vip")) {
            ChatUtil.sendMessage(p, "&9&lERROR: &7Masz wieksza range");
            return;
    	}
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp group.VIP true 3d");
        ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lVOUCHER&8] &7Gracz &c" + p.getName() + " &7aktywowal Voucher na VIP'a 3d");
        ChatUtil.removeItems(p, vip3d.build());
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void OnClick3(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = p.getItemInHand();
        final ItemBuilder svip = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA SVIP'A")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        if (item.getType() != Material.PAPER) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&4&lVOUCHER NA SVIP'A"))) {
            return;
        }
    	if(p.hasPermission("admin")) {
            ChatUtil.sendMessage(p, "&9&lERROR: &7Masz wieksza range");
            return;
    	}
    	if(p.hasPermission("svip")) {
            ChatUtil.sendMessage(p, "&9&lERROR: &7Masz wieksza range");
            return;
    	}
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set vip");
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set svip");
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "pex user " + p.getName() + " group set SVIP");
        ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lVOUCHER&8] &7Gracz &c" + p.getName() + " &7aktywowal Voucher na SVIP'a");
        ChatUtil.removeItems(p, svip.build());
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void OnClick4(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = p.getItemInHand();
        final ItemBuilder svip3d = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA SVIP'A 3d")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        if (item.getType() != Material.PAPER) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&4&lVOUCHER NA SVIP'A 3d"))) {
            return;
        }
    	if(p.hasPermission("admin")) {
            ChatUtil.sendMessage(p, "&9&lERROR: &7Masz wieksza range");
            return;
    	}
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp group.SVIP true 3d");
        ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lVOUCHER&8] &7Gracz &c" + p.getName() + " &7aktywowal Voucher na SVIP'a 3d");
        ChatUtil.removeItems(p, svip3d.build());
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void OnClick5(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = p.getItemInHand();
        final ItemBuilder turbo = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA TURBODROP 15m")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac boosta")).addLore(ChatUtil.fixColor(""));
        if (item.getType() != Material.PAPER) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&4&lVOUCHER NA TURBODROP 15m"))) {
            return;
        }
        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "turbo drop " + p.getName() + " 15m");
        ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lVOUCHER&8] &7Gracz &c" + p.getName() + " &7aktywowal Voucher na TurboDrop 15m");
        ChatUtil.removeItems(p, turbo.build());
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void OnClick8(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack item = p.getItemInHand();
        final User user = UserManager.getUser(p);
        final ItemBuilder turbo = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA COINS 2500")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac boosta")).addLore(ChatUtil.fixColor(""));
        if (item.getType() != Material.PAPER) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&4&lVOUCHER NA COINS 2500"))) {
            return;
        }
		user.addCoins(2500);
        ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lVOUCHER&8] &7Gracz &c" + p.getName() + " &7aktywowal Voucher na COINS");
        ChatUtil.removeItems(p, turbo.build());
    }
}


