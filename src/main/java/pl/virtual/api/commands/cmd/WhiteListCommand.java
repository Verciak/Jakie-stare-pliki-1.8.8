// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import java.util.Collection;
import org.apache.commons.lang.StringUtils;

import pl.virtual.api.API.Config;
import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class WhiteListCommand extends Command
{
    public WhiteListCommand() {
        super("whitelist", "whitelist serwera", "/whitelist <on|off|add|remove|list> [gracz]", "core.cmd.whitelist", new String[] { "wl" });
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Lang.USE("/wl <add|remove|list|reason|on|off>"));
        }
        Label_0542: {
            Label_0412: {
                Label_0310: {
                    Label_0283: {
                        final String s;
                        switch ((s = args[0]).hashCode()) {
                            case -934964668: {
                                if (!s.equals("reason")) {
                                    return ChatUtil.sendMessage(sender, Lang.USE("/wl <add|remove|list|reason|on|off>"));
                                }
                                break Label_0542;
                            }
                            case -934610812: {
                                if (!s.equals("remove")) {
                                    return ChatUtil.sendMessage(sender, Lang.USE("/wl <add|remove|list|reason|on|off>"));
                                }
                                break Label_0412;
                            }
                            case -773038450: {
                                if (!s.equals("wylacz")) {
                                    return ChatUtil.sendMessage(sender, Lang.USE("/wl <add|remove|list|reason|on|off>"));
                                }
                                break Label_0283;
                            }
                            case 3551: {
                                if (!s.equals("on")) {
                                    return ChatUtil.sendMessage(sender, Lang.USE("/wl <add|remove|list|reason|on|off>"));
                                }
                                break;
                            }
                            case 96417: {
                                if (!s.equals("add")) {
                                    return ChatUtil.sendMessage(sender, Lang.USE("/wl <add|remove|list|reason|on|off>"));
                                }
                                break Label_0310;
                            }
                            case 109935: {
                                if (!s.equals("off")) {
                                    return ChatUtil.sendMessage(sender, Lang.USE("/wl <add|remove|list|reason|on|off>"));
                                }
                                break Label_0283;
                            }
                            case 3322014: {
                                if (!s.equals("list")) {
                                    return ChatUtil.sendMessage(sender, Lang.USE("/wl <add|remove|list|reason|on|off>"));
                                }
                                return ChatUtil.sendMessage(sender, "&7Lista graczy na whitelist: &c" + StringUtils.join((Collection)Config.WL_LIST, "&c, &7"));
                            }
                            case 3599799: {
                                if (!s.equals("usun")) {
                                    return ChatUtil.sendMessage(sender, Lang.USE("/wl <add|remove|list|reason|on|off>"));
                                }
                                break Label_0412;
                            }
                            case 95758114: {
                                if (!s.equals("dodaj")) {
                                    return ChatUtil.sendMessage(sender, Lang.USE("/wl <add|remove|list|reason|on|off>"));
                                }
                                break Label_0310;
                            }
                            case 106859053: {
                                if (!s.equals("powod")) {
                                    return ChatUtil.sendMessage(sender, Lang.USE("/wl <add|remove|list|reason|on|off>"));
                                }
                                break Label_0542;
                            }
                            case 113212835: {
                                if (!s.equals("wlacz")) {
                                    return ChatUtil.sendMessage(sender, Lang.USE("/wl <add|remove|list|reason|on|off>"));
                                }
                                break;
                            }
                        }
                        if (Config.WL_ENABLE) {
                            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Whitelist jest juz on");
                        }
                        Config.WL_ENABLE = true;
                        Config.saveConfig();
                        return ChatUtil.sendMessage(sender, "&7Whitelist zostala &aWlaczona");
                    }
                    if (!Config.WL_ENABLE) {
                        return ChatUtil.sendMessage(sender, "&9&lERROR: &7Whitelist jest juz off");
                    }
                    Config.WL_ENABLE = false;
                    Config.saveConfig();
                    return ChatUtil.sendMessage(sender, "&7Whitelist zostala &cWylaczona");
                }
                if (args.length < 2) {
                    return ChatUtil.sendMessage(sender, Lang.USE("/wl add <gracz>"));
                }
                final String nick = args[1];
                if (Config.WL_LIST.contains(nick)) {
                    return ChatUtil.sendMessage(sender, "&9&lERROR: &7Ten gracz jest juz na whitelist");
                }
                Config.WL_LIST.add(nick);
                Config.saveConfig();
                return ChatUtil.sendMessage(sender, "&7Gracz &c" + nick + " &7zostal dodany do whitelist");
            }
            if (args.length < 2) {
                return ChatUtil.sendMessage(sender, Lang.USE("/wl remove <gracz>"));
            }
            final String nick = args[1];
            if (!Config.WL_LIST.contains(nick)) {
                return ChatUtil.sendMessage(sender, "&9&lERROR: &7Ten gracz jest juz na whitelist");
            }
            Config.WL_LIST.remove(nick);
            Config.saveConfig();
            return ChatUtil.sendMessage(sender, "&7Gracz &c" + nick + " &7zostal usuniety z whitelist");
        }
        if (args.length < 2) {
            return ChatUtil.sendMessage(sender, Lang.USE("/wl reason <powod>"));
        }
        final String reason = StringUtils.join((Object[])args, " ", 1, args.length);
        Config.WL_REASON = ChatUtil.fixColor(reason);
        Config.saveConfig();
        return ChatUtil.sendMessage(sender, "&7Ustawiles powod whitelist na: &c" + reason);
    }
}
