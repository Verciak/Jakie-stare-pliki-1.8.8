package pl.virtual.api.commands.guild;

import org.bukkit.entity.*;
import org.bukkit.command.*;

import pl.virtual.api.API.Config;
import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

import org.bukkit.*;

public class CreateCommand extends PlayerCommand
{
    public CreateCommand() {
        super("zaloz", "zaklada gildie", "/zaloz <tag> <pelna nazwa>", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (!p.hasPermission("core.cmd.admin") && !Config.ENABLE_CREATE) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Zakladanie gildii jest tymczasowo wylaczone");
        }
        final Guild guild = GuildManager.getGuild(p);
        if (guild != null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Posiadasz juz gildie");
        }
        if (args.length != 2) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final String tag = args[0].toUpperCase();
        final String name = args[1];
        if (tag.length() > 5 || tag.length() < 2 || name.length() > 32 || name.length() < 4) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Tag gildi musi zawierac 2-5 zankow, nawzwa 4-32 znakow");
        }
        if (GuildManager.getGuild(tag) != null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Istenieje juz gildia o takim tagu");
        }
        if (GuildManager.getGuild(name) != null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Istenieje juz gildia o takiej nazwie");
        }
        if (!ChatUtil.isAlphaNumeric(tag)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Tag nie moze byc alfanumeryczny");
        }
        if (!ChatUtil.isAlphaNumeric(name)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nazwa nie moze byc alfanumeryczna");
        }
        if (!GuildManager.canCreateGuildBySpawn(p.getLocation())) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Jestes zbyt blisko spawnu");
        }
        if (!GuildManager.canCreateGuildByGuild(p.getLocation())) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7W poblizu znajduje sie gildia");
        }
        if (!GuildManager.canCreateGuildByBorder(p.getLocation())) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Jestes zbyt blisko borderu");
        }
        String it = Config.COST_CREATE_NORMAL;
        if (p.hasPermission("core.create.admin")) {
            it = "STONE:0-1:nic;";
        }
        else if (p.hasPermission("core.create.dark")) {
            it = Config.COST_CREATE_DARK;
        }
        else if (p.hasPermission("core.create.svip")) {
            it = Config.COST_CREATE_SVIP;
        }
        else if (p.hasPermission("core.create.vip")) {
            it = Config.COST_CREATE_VIP;
        }
        if (!ItemUtil.checkItems(p, it, 1)) {
            ItemUtil.getItem1(p, it, 1);
            return true;
        }
        ItemUtil.removeItems(p, it, 1);
        final Location home = new Location(p.getWorld(), p.getLocation().getX(), p.getWorld().getHighestBlockYAt(p.getLocation().getBlockX(), p.getLocation().getBlockZ()) + 1.5, p.getLocation().getZ());
        final Guild g = GuildManager.createGuild(tag.toUpperCase(), name, p, home);
        try {
            NameTagManager.createGuild(g, p);
        }
        catch (Exception e1) {
            Logger.warning("Blad podczas wczytywania tagu ", e1.getMessage());
        }
        ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "&8[&4&lGILDIE&8] &7Gracz &c" + p.getName() + " &7zalozyl gildie &8[&c" + g.getTag() + "&8]");
        return false;
    }
}
