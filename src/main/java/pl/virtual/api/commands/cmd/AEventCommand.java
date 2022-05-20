package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;

import pl.virtual.api.API.Config;
import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.DataUtil;

import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

public class AEventCommand extends PlayerCommand
{
    public AEventCommand() {
        super("aevent", "aevent", "/aevent <stone> set <czas>", "core.cmd.aevent", new String[] { "aeventy" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final String s;
        final String s2;
        switch (s2 = (s = args[0])) {
            case "stone": {
                if (args.length <= 2) {
                    return ChatUtil.sendMessage((CommandSender)p, Lang.USE("/aevent stone set <czas>"));
                }
                final long time = Config.STONE = DataUtil.parseDateDiff(args[2], true);
                Config.saveConfig();
                for (final Player online : Bukkit.getOnlinePlayers()) {
                    ChatUtil.sendTitleMessage(online, "&8[&9EVENT&8]", "&7Drop &9Case &7z Stone zostal aktywowany na &9" + DataUtil.secondsToString(time), 20, 60, 20);

                }
            }
            default:
                break;
        }
        return false;
    }
}
