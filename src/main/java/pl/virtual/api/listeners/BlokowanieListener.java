package pl.virtual.api.listeners;

import org.bukkit.event.player.*;

import pl.virtual.api.utils.*;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;

import java.util.*;

public class BlokowanieListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        if (!player.hasPermission("core.plugins")) {
            final String message = event.getMessage();
            final String[] splittedMessage = message.split(" ");
            final String[] pluginCommands = { "/seed", "/lp", "/minelogin", "/?", "/help", "/ver", "/version", "/bukkit", "/bukkit:ver", "/bukkit:version", "/icanhasbukkit", "/bukkit:help", "bukkit:?", "/me", "/bukkit:me", "/minecraft:me", "/about" };
            if (containsIgnoreCase(pluginCommands, splittedMessage[0])) {
                event.setCancelled(true);
                ChatUtil.sendMessage((CommandSender)player, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerCommandPreprocess1(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        if (!player.hasPermission("core.plugins")) {
            final String message = event.getMessage();
            final String[] splittedMessage = message.split(" ");
            final String[] pluginCommands = { "/pl", "/plugins" };
            if (containsIgnoreCase(pluginCommands, splittedMessage[0])) {
                event.setCancelled(true);
                ChatUtil.sendMessage((CommandSender)player, "");
                ChatUtil.sendMessage((CommandSender)player, "&8» &7Serwer: &cDarkHard.pl");
                ChatUtil.sendMessage((CommandSender)player, "&8» &7Autor: &cVirtual343");
                ChatUtil.sendMessage((CommandSender)player, "&8» &7Discord: &chttps://discord.gg/3zrVjtB");
                ChatUtil.sendMessage((CommandSender)player, "");
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerCommandPreprocesss(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        if (!player.hasPermission("core.root")) {
            final String message = event.getMessage();
            final String[] splittedMessage = message.split(" ");
            final String[] pluginCommands = { "/reload", "holograms", "hd", "holo", "hologram", "/we", "/tps", "/ping", "/wg", "/rg", "/br", "//wand", "/server", "/alert", "/list", "/end", "/ip", "/minecraft:op", "/permissionsex:pex", "/rl", "/reload", "/stop", "/op", "/pex", "/minecraft:ban", "//brush", "/brush", "//br", "/br"  };
            if (containsIgnoreCase(pluginCommands, splittedMessage[0])) {
                event.setCancelled(true);
                ChatUtil.sendMessage((CommandSender)player, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
            }
        }
    }
    
    public static boolean containsIgnoreCase(final String[] board, final String string) {
        final String[] arrayOfString = board;
        for (int j = board.length, i = 0; i < j; ++i) {
            final String otherstring = arrayOfString[i];
            if (otherstring.equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean containsIgnoreCase(final List<String> board, final String string) {
        for (final String otherstring : board) {
            if (otherstring.equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }
}
