package pl.virtual.api.commands.cmd;

import org.bukkit.entity.*;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.*;

public class VanishCommand extends PlayerCommand
{
    public VanishCommand() {
        super("vanish", "Vanish", "/vanish <gracz>", "core.cmd.vanish", new String[] { "v" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length > 0 && p.hasPermission("core.cmd.vanishplus")) {
            final Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                return ChatUtil.sendMessage(p, "&9&lERROR: &7Nie odnaleziono gracza");
            }
            final boolean isVanish = UserManager.isVanish(player);
            if (isVanish) {
                UserManager.disableVanish(player);
                ChatUtil.sendMessage(player, "&7Tryb niewidzialnosci zostal &cwylaczony &7dla &c" + player);
                return ChatUtil.sendMessage(player, "&7Tryb niewidzialnosci zostal &cwylaczony");
            }
            else {
                UserManager.enableVanish(player);
                ChatUtil.sendMessage(player, "&7Tryb niewidzialnosci zostal &awlaczony &7dla &c" + player);
                return ChatUtil.sendMessage(player, "&7Tryb niewidzialnosci zostal &awlaczony");
            }
        }
        else {
            final boolean isVanish = UserManager.isVanish(p);
            if (isVanish) {
                UserManager.disableVanish(p);
                return ChatUtil.sendMessage(p, "&7Tryb niewidzialnosci zostal &cwylaczony");
            }
            else {
                UserManager.enableVanish(p);
                return ChatUtil.sendMessage(p, "&7Tryb niewidzialnosci zostal &awlaczony");
            }
        }
    }
}

