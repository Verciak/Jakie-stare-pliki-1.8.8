// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.API.Config;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GCommand extends PlayerCommand
{
    public GCommand() {
        super("g", "komenda do g", "/g <text>", "core.cmd.user", new String[] { "g" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        ChatUtil.sendMessage((CommandSender)p, "");
        ChatUtil.sendMessage((CommandSender)p, "&8&m----------&8[ &9&lGILDIE &8]&8&m----------");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/zaloz <tag> <pelna_nazwa> &8- &7zalozenie gildii");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/usun &8- &7usuwa gildie");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/dolacz <tag/nazwa> &8- &7dolaczasz do gildii");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/opusc &8- &7opuszczasz gildie");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/baza &8- &7teleportacja do gildii");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/odnow &8- &7oplaca gildie na " + Config.PROLONG_ADD + " dni");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/ustawbaza &8- &7ustawia baze gildii");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/powieksz &8- &7powieksza obszar gildii");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/wyrzuc <nick> &8- &7wyrzuca gracza z gildii");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/zapros <nick> &8- &7zaprasza gracza do gildii");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/lider <nick> &8- &7przekazuje wlasciciela gildii");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/zastepca <nick> &8- &7zmienia zastepce gildii");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/gildia <gildia> &8- &7informacje o gildii");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/pvp &8- &7wlacza/wylacza pvp w gildii");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/pvp sojusz &8- &7wlacza/wylacza pvp w sojuszu");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/gpanel &8- &7panel gildyjny");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/kordy <tag> &8- &7Kupujesz kordy gildii");
        ChatUtil.sendMessage((CommandSender)p, "&8» &l&9/uprawnienia <nick> &8- &7Edytujesz uprawnienia graczy");
        ChatUtil.sendMessage((CommandSender)p, "&8&m----------&8[ &9&lGILDIE &8]&8&m----------");
        ChatUtil.sendMessage((CommandSender)p, "");
        return false;
    }
}
