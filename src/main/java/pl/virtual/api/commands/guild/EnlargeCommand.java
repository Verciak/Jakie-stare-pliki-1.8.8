package pl.virtual.api.commands.guild;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import pl.virtual.api.API.Config;
import pl.virtual.api.commands.*;
import pl.virtual.api.data.base.guild.*;
import pl.virtual.api.data.base.user.Upr;
import pl.virtual.api.managers.*;
import pl.virtual.api.utils.*;

public class EnlargeCommand extends PlayerCommand
{
    public EnlargeCommand() {
        super("powieksz", "Powieksza teren gildii", "/powieksz", "core.cmd.user", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        final Guild g = GuildManager.getGuild(p);
        if (g == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie posiadasz gildii");
        }
        Upr perms = UprManager.getUser(p);
        if (g != null && perms.getEnlarge() == 0 && !g.isLeader(p.getName())) {
            return ChatUtil.sendMessage(p, "&9&lERROR: &7Nie masz do tego uprawnien. Popros lidera o nadanie &c/uprawnienia");
        }
        if (g.getRegion().getSize() >= Config.CUBOID_SIZE_MAX) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gildia posiada maksymalny rozmiar");
        }
        final int mod = (g.getRegion().getSize() - Config.CUBOID_SIZE_START) / 5 + 1;
        final String it = Config.COST_ENLARGE_NORMAL;
        if (!ItemUtil.checkItems(p, it, mod)) {
            ItemUtil.getItem(p, it, mod);
            return true;
        }
        ItemUtil.removeItems(p, it, mod);
        g.addSize(Config.CUBOID_SIZE_ADD);
        final int size = g.getRegion().getSize() * 2 + 1;
        return ChatUtil.sendMessage((CommandSender)p, "&8[&4&lGILDIE&8] &7Powiekszyles rozmiar gildii do &c" + size + "&7x&c" + size);
    }
}
