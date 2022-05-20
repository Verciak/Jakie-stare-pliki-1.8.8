// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.Command;
import pl.virtual.api.data.base.user.User;
import pl.virtual.api.managers.UserManager;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.command.CommandSender;

public class StatsCommand extends Command
{
    public StatsCommand() {
        super("stats", "stats", "/stats <gracz> <kills/deaths/points/logouts/asysts/restart/stone/coins> <ilosc>", "core.cmd.stats", new String[0]);
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        final User u = UserManager.getUser(args[0]);
        if (u == null) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7Gracz nie istnieje!");
        }
        if (!ChatUtil.isInteger(args[2])) {
            return ChatUtil.sendMessage(sender, "&9&lERROR: &7To nie liczba!");
        }
        final int i = Integer.parseInt(args[2]);
        Label_0913: {
            Label_0840: {
                Label_0767: {
                    final String s;
                    switch ((s = args[1]).hashCode()) {
                        case -1408015797: {
                            if (!s.equals("asysts")) {
                                return ChatUtil.sendMessage(sender, this.getUsage());
                            }
                            break;
                        }
                        case -1335772033: {
                            if (!s.equals("deaths")) {
                                return ChatUtil.sendMessage(sender, this.getUsage());
                            }
                            if (args.length < 3) {
                                return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
                            }
                            u.setDeaths(i);
                            u.save();
                            return ChatUtil.sendMessage(sender, "&7Ustawiles &c" + args[1] + " &7na &c" + i + " &7graczowi &c" + u.getName());
                        }
                        case -1097329270: {
                            if (!s.equals("logout")) {
                                return ChatUtil.sendMessage(sender, this.getUsage());
                            }
                            break Label_0767;
                        }
                        case -1090712320: {
                            if (!s.equals("lvlset")) {
                                return ChatUtil.sendMessage(sender, this.getUsage());
                            }
                            break Label_0913;
                        }
                        case -982754077: {
                            if (!s.equals("points")) {
                                return ChatUtil.sendMessage(sender, this.getUsage());
                            }
                            if (args.length < 3) {
                                return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
                            }
                            u.setPoints(i);
                            u.save();
                            return ChatUtil.sendMessage(sender, "&7Ustawiles &c" + args[1] + " &7na &c" + i + " &7graczowi &c" + u.getName());
                        }
                        case -715587296: {
                            if (!s.equals("coinsset")) {
                                return ChatUtil.sendMessage(sender, this.getUsage());
                            }
                            break Label_0840;
                        }
                        case 107554: {
                            if (!s.equals("lvl")) {
                                return ChatUtil.sendMessage(sender, this.getUsage());
                            }
                            break Label_0913;
                        }
                        case 93127464: {
                            if (!s.equals("asyst")) {
                                return ChatUtil.sendMessage(sender, this.getUsage());
                            }
                            break;
                        }
                        case 94839810: {
                            if (!s.equals("coins")) {
                                return ChatUtil.sendMessage(sender, this.getUsage());
                            }
                            break Label_0840;
                        }
                        case 102052053: {
                            if (!s.equals("kills")) {
                                return ChatUtil.sendMessage(sender, this.getUsage());
                            }
                            if (args.length < 3) {
                                return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
                            }
                            u.setKills(i);
                            u.save();
                            return ChatUtil.sendMessage(sender, "&7Ustawiles &c" + args[1] + " &7na &c" + i + " &7graczowi &c" + u.getName());
                        }
                        case 109770853: {
                            if (!s.equals("stone")) {
                                return ChatUtil.sendMessage(sender, this.getUsage());
                            }
                            if (args.length < 3) {
                                return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
                            }
                            u.setWykstone(i);
                            u.save();
                            return ChatUtil.sendMessage(sender, "&7Ustawiles Wykopany Stone na &c" + i + " &7graczowi &c" + u.getName());
                        }
                        case 342531113: {
                            if (!s.equals("logouts")) {
                                return ChatUtil.sendMessage(sender, this.getUsage());
                            }
                            break Label_0767;
                        }
                        case 1097506319: {
                            if (!s.equals("restart")) {
                                return ChatUtil.sendMessage(sender, this.getUsage());
                            }
                            u.setPoints(500);
                            u.setKills(0);
                            u.setDeaths(0);
                            u.setAsyst(0);
                            u.setLogout(0);
                            u.setWykstone(0);
                            u.save();
                            return ChatUtil.sendMessage(sender, "&7Zresetowales staty gracza &c" + u.getName());
                        }
                    }
                    if (args.length < 3) {
                        return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
                    }
                    u.setAsyst(i);
                    u.save();
                    return ChatUtil.sendMessage(sender, "&7Ustawiles &c" + args[1] + " &7na &c" + i + " &7graczowi &c" + u.getName());
                }
                if (args.length < 3) {
                    return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
                }
                u.setLogout(i);
                u.save();
                return ChatUtil.sendMessage(sender, "&7Ustawiles &c" + args[1] + " &7na &c" + i + " &7graczowi &c" + u.getName());
            }
            if (args.length < 3) {
                return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
            }
            u.setCoins(i);
            u.save();
            return ChatUtil.sendMessage(sender, "&7Ustawiles &c" + args[1] + " &7na &c" + i + " &7graczowi &c" + u.getName());
        }
        if (args.length < 3) {
            return ChatUtil.sendMessage(sender, Lang.USE(this.getUsage()));
        }
        u.setLvl(i);
        u.save();
        return ChatUtil.sendMessage(sender, "&7Ustawiles &c" + args[1] + " &7na &c" + i + " &7graczowi &c" + u.getName());
    }
}
