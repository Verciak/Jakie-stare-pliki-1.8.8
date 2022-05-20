// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.guild.Guild;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.GuildManager;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.rank.tops.RankingManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.entity.Player;

public class RankingCommand extends PlayerCommand
{
    public RankingCommand() {
        super("ranking", "sprawdzanie ranking gracza", "&9/ranking <gracz>", "core.cmd.user", new String[] { "gracz" });
    }
    
    @Override
    public boolean onCommand(final Player player, final String[] args) {
        User u = null;
        if (args.length == 0) {
            u = UserManager.getUser(player);
        }
        else {
            u = UserManager.getUser(args[0]);
        }
        if (u == null) {
            return ChatUtil.sendMessage((CommandSender)player, "&9&lERROR: &7Gracz nie istnieje");
        }
        if (u.getName().equalsIgnoreCase(player.getName())) {
            String tag = "Brak";
            final Guild ge = GuildManager.getGuild(player);
            if (ge != null) {
                tag = ge.getTag();
            }
            ChatUtil.sendMessage((CommandSender)player, "&8&m-----&8{ &9RANKING &8}&8&m-----&8");
            ChatUtil.sendMessage((CommandSender)player, "");
            ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Pozycja: &9" + RankingManager.getPlaceUser(u));
            ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Punktow: &9" + u.getPoints());
            ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Zabojstw: &9" + u.getKills());
            ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Zgonow &9" + u.getDeaths());
            ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Asyst: &9" + u.getAsyst());
            ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Logout: &9" + u.getLogout());
            ChatUtil.sendMessage((CommandSender)player, " &8&l» &7K/D: &9" + u.getKd());
            ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Coinsy: &9" + u.getCoins());
            ChatUtil.sendMessage((CommandSender)player, "");
            return ChatUtil.sendMessage((CommandSender)player, "&8&m-----&8{ &9RANKING &8}&8&m-----&8");
        }
        ChatUtil.sendMessage((CommandSender)player, "&8&m-----&8{ &9" + u.getName() + " &8}&8&m-----&8");
        String tag = "Brak";
        final Guild ge = GuildManager.getGuild(player);
        if (ge != null) {
            tag = ge.getTag();
        }
        ChatUtil.sendMessage((CommandSender)player, "");
        ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Pozycja: &9" + RankingManager.getPlaceUser(u));
        ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Punktow: &9" + u.getPoints());
        ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Zabojstw: &9" + u.getKills());
        ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Zgonow &9" + u.getDeaths());
        ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Asyst: &9" + u.getAsyst());
        ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Logout: &9" + u.getLogout());
        ChatUtil.sendMessage((CommandSender)player, " &8&l» &7K/D: &9" + u.getKd());
        ChatUtil.sendMessage((CommandSender)player, " &8&l» &7Coinsy: &9" + u.getCoins());
        ChatUtil.sendMessage((CommandSender)player, "");
        return ChatUtil.sendMessage((CommandSender)player, "&8&m-----&8{ &9" + u.getName() + " &8}&8&m-----&8");
    }
}
