// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.World;
import org.bukkit.Bukkit;

import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.managers.BanManager;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.MuteManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.Ticking;

import org.bukkit.entity.Player;

public class GcCommand extends PlayerCommand
{
    public GcCommand() {
        super("gc", "statystki serwera", "/gc", "core.cmd.gc", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        ChatUtil.sendMessage((CommandSender)p, "&7&l&m----------&r&7( &9&lInformacje &7)&7&m&l----------");
        ChatUtil.sendMessage((CommandSender)p, "");
        ChatUtil.sendMessage((CommandSender)p, "&8» &7TPS: &9" + (Ticking.getTPS()));
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Gracze: &9" + Bukkit.getOnlinePlayers().size());
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Dostepne Rdzenie: &9" + Runtime.getRuntime().availableProcessors());
        ChatUtil.sendMessage((CommandSender)p, "");
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Pamiec:");
        ChatUtil.sendMessage((CommandSender)p, "  &7- &7Calkowita: &9" + Runtime.getRuntime().maxMemory() / 1024L / 1024L + " &9MB");
        ChatUtil.sendMessage((CommandSender)p, "  &7- &7Zarezerwowana: &9" + Runtime.getRuntime().totalMemory() / 1024L / 1024L + " &9MB");
        ChatUtil.sendMessage((CommandSender)p, "  &7- &7Wolna: &9" + Runtime.getRuntime().freeMemory() / 1024L / 1024L + " &9MB");
        final World world = Bukkit.getWorld("world");
        ChatUtil.sendMessage((CommandSender)p, "");
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Statystyki edycji:");
        ChatUtil.sendMessage((CommandSender)p, "  &7- &7Zarejestrowanych graczy &9" + UserManager.getUsers().size());
        ChatUtil.sendMessage((CommandSender)p, "  &7- &7Stworzonych gildii &9" + GuildManager.getGuilds().size());
        ChatUtil.sendMessage((CommandSender)p, "  &7- &7Zbanowanych graczy &9" + BanManager.getBans().size());
        ChatUtil.sendMessage((CommandSender)p, "  &7- &7Wyciszonych graczy &9" + MuteManager.getMutes().size());
        ChatUtil.sendMessage((CommandSender)p, "");
        ChatUtil.sendMessage((CommandSender)p, "&8» &7Mapa:");
        ChatUtil.sendMessage((CommandSender)p, "  &7- &7Chunki: &9" + world.getLoadedChunks().length);
        ChatUtil.sendMessage((CommandSender)p, "  &7- &7Wszystkie Entites: &9" + world.getEntities().size());
        ChatUtil.sendMessage((CommandSender)p, "  &7- &7Zywe Entites: &9" + world.getLivingEntities().size());
        ChatUtil.sendMessage((CommandSender)p, "");
        ChatUtil.sendMessage((CommandSender)p, "&7&l&m----------&r&7( &9&lInformacje &7)&7&m&l----------");
        return false;
    }
}