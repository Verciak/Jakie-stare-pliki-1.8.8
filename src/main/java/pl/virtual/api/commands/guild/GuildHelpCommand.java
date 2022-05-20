package pl.virtual.api.commands.guild;

import org.bukkit.entity.*;

import pl.virtual.api.API.Config;
import pl.virtual.api.commands.*;
import pl.virtual.api.utils.*;

import org.bukkit.command.*;

public class GuildHelpCommand extends PlayerCommand
{
    public GuildHelpCommand() {
        super("gildie", "info o gildiach", "/gildie", "core.cmd.user", new String[] { "gildiepomoc", "gildia" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            ChatUtil.sendMessage((CommandSender)p, "");
            ChatUtil.sendMessage((CommandSender)p, "&8&m----------&8[ &a&lGILDIE &8]&8&m----------");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/zaloz <tag> <pelna_nazwa> &8- &7zalozenie gildii");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/usun &8- &7usuwa gildie");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/dolacz <tag/nazwa> &8- &7dolaczasz do gildii");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/opusc &8- &7opuszczasz gildie");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/baza &8- &7teleportacja do gildii");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/odnow &8- &7oplaca gildie na " + Config.PROLONG_ADD + " dni");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/ustawbaza &8- &7ustawia baze gildii");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/powieksz &8- &7powieksza obszar gildii");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/wyrzuc <nick> &8- &7wyrzuca gracza z gildii");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/zapros <nick> &8- &7zaprasza gracza do gildii");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/lider <nick> &8- &7przekazuje wlasciciela gildii");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/zastepca <nick> &8- &7zmienia zastepce gildii");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/gildia <gildia> &8- &7informacje o gildii");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/pvp &8- &7wlacza/wylacza pvp w gildii");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/pvp sojusz &8- &7wlacza/wylacza pvp w sojuszu");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/pay <tag> <ilosc> &8- &7przelewa coinsy dla gildii");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/gpanel &8- &7panel gildyjny");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/kordy <tag> &8- &7Kupujesz kordy gildii");
            ChatUtil.sendMessage((CommandSender)p, "&8» &l&c/uprawnienia <nick> &8- &7Edytujesz uprawnienia graczy");
            ChatUtil.sendMessage((CommandSender)p, "&8&m----------&8[ &a&lGILDIE &8]&8&m----------");
            ChatUtil.sendMessage((CommandSender)p, "");
        }
        return false;
    }
}
