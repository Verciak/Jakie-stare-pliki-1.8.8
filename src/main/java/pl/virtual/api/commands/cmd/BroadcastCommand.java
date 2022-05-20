// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.entity.Player;

public class BroadcastCommand extends PlayerCommand
{
    public BroadcastCommand() {
        super("bc", "Ogloszenia do graczy", "/bc <title/subtitle/chat> <wiadomosc>", "core.cmd.bc", new String[] { "bc", "bc" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(p, "&cPoprawne uzycie: &7/bc <chat/title/subtitle/actionbar> <wiadomosc>");
        }
        String out = "";
        for (int i = 1; i < args.length; ++i) {
            out = String.valueOf(out) + args[i] + " ";
        }
        final String message = out;
        final String lowerCase;
        switch (lowerCase = args[0].toLowerCase()) {
            case "subtitle": {
                return ChatUtil.sendTitle(Bukkit.getOnlinePlayers(), "", message);
            }
            case "chat": {
                return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), message);
            }
            case "title": {
                return ChatUtil.sendTitle(Bukkit.getOnlinePlayers(), message, "");
            }
            case "actionbar": {
                return ChatUtil.sendActionBar(Bukkit.getOnlinePlayers(), message);
            }
            default:
                break;
        }
        return false;
    }
}