package pl.virtual.api.commands.cmd;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.inventory.*;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;

public class InvCommand extends PlayerCommand
{
    public InvCommand() {
        super("inv", "Zarzadzanie ekwipunkiem", "/inv <nick>", "core.cmd.inv", new String[] { "invsee" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length != 1) {
            return ChatUtil.sendMessage((CommandSender)p, "&cPoprawne uzycie: &7/inv <nick>");
        }
        if (Bukkit.getPlayer(args[0]) == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz jest Offline");
        }
        final Player other = Bukkit.getPlayer(args[0]);
        	p.openInventory(other.getInventory());
        	return ChatUtil.sendMessage((CommandSender)p, "&7Otworzono ekwipunek gracza &9" + other.getName());
        }
    }
