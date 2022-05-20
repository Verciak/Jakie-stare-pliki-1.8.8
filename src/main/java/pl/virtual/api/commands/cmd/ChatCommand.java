// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;

import pl.virtual.api.API.Config;
import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.managers.ChatManager;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class ChatCommand extends Command
{
    public ChatCommand() {
        super("chat", "zarzadzanie chatem", "/chat <cc|on|off|level|vip>", "core.cmd.chat", new String[] { "czat" });
    }
    	@Override
    	public boolean onExecute(final CommandSender sender, final String[] args) {
    		if (args.length < 1) {
    			return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
    		}
    		final String s;
    		switch ((s = args[0]).hashCode()) {
    		case 3168: {
    			if (!s.equals("cc")) {
    				return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
    			}
    			for (int i = 0; i < 100; ++i) {
    				ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "");
    			}
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "              &9&lNOMENHC.EU");
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "");
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "     &7Administrator: &9" + sender.getName() + "");
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "      &7Wyczyscil chat serwera");
                return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "");

    		}
    		case 3551: {
    			if (!s.equals("on")) {
    				return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
    			}
    			if (ChatManager.enable) {
    				return ChatUtil.sendMessage(sender,
    						"&9&lERROR: &7Chat jest juz wlaczony");
    			}
    			ChatManager.enable = true;
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "              &9&lNOMENHC.EU");
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "");
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "     &7Administrator: &9" + sender.getName() + "");
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "      &aWlaczyl &7chat serwera");
                return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "");

    		}
    		case 107554: {
    			if (!s.equals("lvl")) {
    				return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
    			}
    			break;
    		}
    		case 109935: {
    			if (!s.equals("off")) {
    				return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
    			}
    			if (!ChatManager.enable) {
    				return ChatUtil.sendMessage(sender,
    						"&9&lERROR: &7Chat jest juz wylaczony");
    			}
    			ChatManager.enable = false;
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "              &9&lNOMENHC.EU");
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "");
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "     &7Administrator: &9" + sender.getName() + "");
                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "      &cWylaczyl &7chat serwera");
                return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "");

    		}
    		case 116765: {
    			if (!s.equals("vip")) {
    				return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
    			}
    			ChatManager.vipChat = !ChatManager.vipChat;
    	                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "              &9&lNOMENHC.EU");
    	                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "");
    	                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "        &7Administrator: &9" + sender.getName() + "");
    	                ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "    " + (ChatManager.vipChat ? "&aWlaczyl &7chat dla rang &9&lPremium" : "&9Wylaczyl &7chat dla rang &9&lPremium"));
    	                return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "");
    		}
    		case 3533313: {
    			if (!s.equals("slow")) {
    				return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
    			}
    			if (args.length < 2) {
    				return ChatUtil.sendMessage(sender,
    						Lang.USE("/chat slow <czas w sekundach>"));
    			}
    			if (!ChatUtil.isInteger(args[1])) {
    				return ChatUtil.sendMessage(sender,
    						"&9&lERROR: &9To nie jest liczba");
    			}
    			final int slow = Config.CHAT_SLOWMODE = Integer.parseInt(args[1]);
    			Config.saveConfig();
    			return ChatUtil.sendMessage(sender, "&7Ustawiles slow chatu na &9" + args[1] + " &7sekundy");
    		}
    		case 102865796: {
    			if (!s.equals("level")) {
    				return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
    			}
    			break;
    		}
    		}
    		if (args.length < 2) {
    			return ChatUtil.sendMessage(sender, Lang.USE("/chat lvl <poziom>"));
    		}
    		if (!ChatUtil.isInteger(args[1])) {
    			return ChatUtil.sendMessage(sender, "&9&lERROR: &7To nie liczba");
    		}
    		int i = Config.LVL = Integer.parseInt(args[1]);
    		Config.saveConfig();
            ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "              &9&lNOMENHC.EU");
            ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "");
            ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "     &7Administrator: &9" + sender.getName() + "");
            ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "       &7Ustawil lvl chatu na &9" + i + "");
            return ChatUtil.sendMessage(Bukkit.getOnlinePlayers(), "");
    	}
    }