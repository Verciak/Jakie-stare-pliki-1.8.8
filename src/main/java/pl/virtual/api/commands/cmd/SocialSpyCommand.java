package pl.virtual.api.commands.cmd;

import java.util.*;
import org.bukkit.entity.*;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

public class SocialSpyCommand extends PlayerCommand
{
    public static List<String> socialspy;
    
    static {
        SocialSpyCommand.socialspy = new ArrayList<String>();
    }
    
    public SocialSpyCommand() {
        super("socialspy", "socialspy", "/socialspy", "core.cmd.socialspy", new String[] { "socsp" });
    }
    
    @Override
    public boolean onCommand(final Player sender, final String[] args) {
        if (!SocialSpyCommand.socialspy.contains(sender.getName())) {
            SocialSpyCommand.socialspy.add(sender.getName());
            sender.sendMessage(ChatUtil.fixColor("&7Tryb szpiega zostal &aWlaczony"));
        }
        else {
            SocialSpyCommand.socialspy.remove(sender.getName());
            sender.sendMessage(ChatUtil.fixColor("&7Tryb szpiega zostal &aWylaczony"));
        }
        return false;
    }
}
