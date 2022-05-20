// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import java.util.UUID;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import pl.virtual.api.API.Config;
import pl.virtual.api.commands.Command;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.DataUtil;

import org.bukkit.command.CommandSender;

public class TurboCommand extends Command
{
    public TurboCommand() {
        super("turbo", "turbodrop", "/turbo <drop/exp> <all/guild> <players/tag/gracz> [czas]", "core.cmd.admin", new String[0]);
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 3) {
            return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7" + this.getUsage());
        }
        if (!args[0].equalsIgnoreCase("drop") && !args[0].equalsIgnoreCase("exp")) {
            return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7" + this.getUsage());
        }
        if (args[0].equalsIgnoreCase("drop")) {
            if (args[1].equalsIgnoreCase("all")) {
                if (!args[2].equalsIgnoreCase("players")) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7" + this.getUsage());
                }
                if (args.length != 4) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7" + this.getUsage());
                }
                final long time = Config.TURBO_DROP = DataUtil.parseDateDiff(args[3], true);
                Config.saveConfig();
                return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&7Na serwerze zostal aktywowany &6&lTurboDrop &7do &c" + DataUtil.getDate(time));
            }
            else if (args[1].equalsIgnoreCase("guild")) {
                final Guild g = GuildManager.getGuild(args[2]);
                if (g == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gildia nie istnieje");
                }
                if (args.length != 4) {
                    return ChatUtil.sendMessage(sender, "&cPoprawne uzycie: &7" + this.getUsage());
                }
                final long time2 = DataUtil.parseDateDiff(args[3], true);
                for (final String p : g.getMembers()) {
                    final Player pl = Bukkit.getPlayer(p);
                    if (g.getTurboDrop() > System.currentTimeMillis()) {
                        g.setTurboDrop(time2 + (g.getTurboDrop() - System.currentTimeMillis()));
                        ChatUtil.sendMessage((CommandSender)pl, "&7Aktywowano dla Twojej gildii &6&lTurboDrop &7do &9" + DataUtil.getDate(time2) + "&7, wiec czas Twojego TurboDropu zostal wydluzony do &9" + DataUtil.getDate(time2 + (g.getTurboDrop() - System.currentTimeMillis())));
                    }
                    else {
                        if (pl.isOnline()) {
                            ChatUtil.sendMessage((CommandSender)pl, "&7Aktywowano dla Twojej gildii &6&lTurboDrop &7do &9" + DataUtil.getDate(time2));
                        }
                        g.setTurboDrop(time2);
                    }
                }
                return ChatUtil.sendMessage(sender, "&7Ustawiles &dTurboDrop &7dla gildii &9" + g.getTag() + " &7do &9" + DataUtil.getDate(time2));
            }
            else {
                final User u = UserManager.getUser(args[1]);
                if (u == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie istnieje");
                }
                final long time2 = DataUtil.parseDateDiff(args[2], true);
                u.setTurboDrop(time2);
                u.save();
                ChatUtil.sendMessage((CommandSender)Bukkit.getPlayer(args[1]), "&7Aktywowano dla Ciebie &6&lTurboDrop &7do &9" + DataUtil.getDate(time2));
                return ChatUtil.sendMessage(sender, "&7Ustawiles &6&lTurboDrop &7dla gracza &9" + Bukkit.getPlayer(args[1]).getName() + " &7do &9" + DataUtil.getDate(time2));
            }
        }
        else {
            if (!args[0].equalsIgnoreCase("exp")) {
                return ChatUtil.sendMessage(sender, "&7Poprawne uzycie: &c" + this.getUsage());
            }
            if (args[1].equalsIgnoreCase("all")) {
                if (!args[2].equalsIgnoreCase("players")) {
                    return ChatUtil.sendMessage(sender, "&7Poprawne uzycie: &c" + this.getUsage());
                }
                if (args.length != 4) {
                    return ChatUtil.sendMessage(sender, "&7Poprawne uzycie: &c" + this.getUsage());
                }
                final long time = Config.TURBO_EXP = DataUtil.parseDateDiff(args[3], true);
                Config.saveConfig();
                return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&7Na serwerze zostal aktywowany &6&lTurboExp &7do &9" + DataUtil.getDate(time));
            }
            else if (args[1].equalsIgnoreCase("guild")) {
                final Guild g = GuildManager.getGuild(args[2]);
                if (g == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gildia nie istnieje");
                }
                if (args.length != 4) {
                    return ChatUtil.sendMessage(sender, "&7Poprawne uzycie: &c" + this.getUsage());
                }
                final long time2 = DataUtil.parseDateDiff(args[3], true);
                for (final String p : g.getMembers()) {
                    final Player pl = Bukkit.getPlayer(p);
                    if (g.getTurboExp() > System.currentTimeMillis()) {
                        g.setTurboExp(time2 + (g.getTurboExp() - System.currentTimeMillis()));
                        ChatUtil.sendMessage((CommandSender)pl, "&7Aktywowano dla Twojej gildii &6&lTurboExp &7do &9" + DataUtil.getDate(time2) + "&7, wiec czas Twojego TurboExpa zostal wydluzony do &9" + DataUtil.getDate(time2 + (g.getTurboExp() - System.currentTimeMillis())));
                    }
                    else {
                        if (pl.isOnline()) {
                            ChatUtil.sendMessage((CommandSender)pl, "&7Aktywowano dla Twojej gildii &6&lTurboExp &7do &9" + DataUtil.getDate(time2));
                        }
                        g.setTurboExp(time2);
                    }
                }
                return ChatUtil.sendMessage(sender, "&7Ustawiles &6&lTurboDrop &7dla gildii &9" + g.getTag() + " &7do &9" + DataUtil.getDate(time2));
            }
            else {
                if (args.length != 3) {
                    return ChatUtil.sendMessage(sender, "&7Poprawne uzycie: &c" + this.getUsage());
                }
                final User u = UserManager.getUser(args[1]);
                if (u == null) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie istnieje");
                }
                final long time2 = DataUtil.parseDateDiff(args[2], true);
                u.setTurboExp(time2);
                u.save();
                ChatUtil.sendMessage((CommandSender)Bukkit.getPlayer(args[1]), "&7Aktywowano dla Ciebie &6&lTurboExp &7do &9" + DataUtil.getDate(time2));
                return ChatUtil.sendMessage(sender, "&7Ustawiles &6&lTurboExp &7dla gracza &9" + Bukkit.getPlayer(args[1]).getName() + " &7do &9" + DataUtil.getDate(time2));
            }
        }
    }
    
    public static Player[] getMemberList(final Set<UUID> members) {
        final Player[] s = new Player[members.size()];
        int i = 0;
        for (final UUID u : members) {
            final Player op = Bukkit.getPlayer(u);
            s[i] = op;
            ++i;
        }
        return s;
    }
}
