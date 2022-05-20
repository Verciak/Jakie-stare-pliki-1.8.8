// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.GameMode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class GamemodeCommand extends Command
{
    public GamemodeCommand() {
        super("gamemode", "Zmiana trybu gry graczy", "/gamemode [gracz] <tryb>", "core.cmd.gamemode", new String[] { "gm", "gmode" });
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        if (this.getMode(args[0]) == null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Nie odnaleziono trybu gamemode");
        }
        if (args.length == 1) {
            final Player p = (Player)sender;
            p.setGameMode(this.getMode(args[0]));
            return ChatUtil.sendMessage(sender, "&7Twoj tryb gamemode zostal zmieniony na &9" + p.getGameMode().toString().toLowerCase() + "");
        }
        if (args.length != 2) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        if (!sender.hasPermission("core.cmd.gamemode.other")) {
            return ChatUtil.sendMessage(sender, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
        }
        final Player o = Bukkit.getPlayer(args[1]);
        if (o == null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz jest offline");
        }
        o.setGameMode(this.getMode(args[0]));
        return ChatUtil.sendMessage(sender, "&7Zmieniles tryb gamemode graczowi &9" + o.getName() + " &7na &9" + o.getGameMode().toString().toLowerCase() + "");
    }
    
    private GameMode getMode(final String args) {
        if (args.equalsIgnoreCase("1") || args.equalsIgnoreCase("CREATIVE") || args.equalsIgnoreCase("true")) {
            return GameMode.CREATIVE;
        }
        if (args.equalsIgnoreCase("0") || args.equalsIgnoreCase("SURVIVAL") || args.equalsIgnoreCase("false")) {
            return GameMode.SURVIVAL;
        }
        if (args.equalsIgnoreCase("2") || args.equalsIgnoreCase("ADVENTURE")) {
            return GameMode.ADVENTURE;
        }
        if (args.equalsIgnoreCase("3") || args.equalsIgnoreCase("SPECTATOR")) {
            return GameMode.SPECTATOR;
        }
        return null;
    }
}
