package pl.virtual.api.listeners;

import org.bukkit.event.player.*;

import pl.virtual.api.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.scheduler.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.*;
import org.bukkit.*;

public class PlayerJoinListener implements Listener
{
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player p = event.getPlayer();
        NameTagManager.initPlayer(event.getPlayer());
        for (int i = 0; i < 100; ++i) {
            ChatUtil.sendMessage((CommandSender)p, "");
        }
        ChatUtil.sendMessage((CommandSender)p, "&8&m----------&f &9&lNOMENHC.EU &8&m----------");
        ChatUtil.sendMessage((CommandSender)p, "");
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Witaj na serwerze &9&lNOMENHC.EU");
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Jest To nasza druga Edycja. Wprowadzilismy wiele zmian wiec");
        ChatUtil.sendMessage((CommandSender)p, "");
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Wszystkie przydatne komendy znajdziesz pod: &9/pomoc");
        ChatUtil.sendMessage((CommandSender)p, "");
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Nasz Discord: &9dc.nomenhc.eu");
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Nasza strona www: &9nomenhc.eu.pl");
        ChatUtil.sendMessage((CommandSender)p, "");
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Dziekujemy ze wybrales nasz serwer");
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Zyczymy milej gry :)");
        ChatUtil.sendMessage((CommandSender)p, "");
        ChatUtil.sendMessage((CommandSender)p, "&8&m----------&f &9&lNOMENHC.EU &8&m----------");
    }
    
    @EventHandler
    public void onJ(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (p.isDead()) {
            new BukkitRunnable() {
                public void run() {
                    p.spigot().respawn();
                    ChatUtil.giveItems(p, new ItemStack(Material.STONE_AXE));
                    ChatUtil.giveItems(p, new ItemStack(Material.ENDER_CHEST));
                    ChatUtil.giveItems(p, new ItemStack(Material.COOKED_BEEF, 32));
                }
            }.runTaskLater((Plugin)ServerPlugin.getPlugin(), 1L);
        }
        if (!p.hasPermission("core.cmd.gamemode") && p.getGameMode() != GameMode.SURVIVAL) {
            p.sendMessage(ChatUtil.fixColor("&8[&4&lLOGGER&8] &7wykryto tryb &cCreative &7tryb zostal zmieniony na &cSurvival"));
            p.setGameMode(GameMode.SURVIVAL);
        }
        if (!p.hasPermission("core.cmd.gamemode") && p.getAllowFlight()) {
            p.setAllowFlight(false);
        }
    }
}

