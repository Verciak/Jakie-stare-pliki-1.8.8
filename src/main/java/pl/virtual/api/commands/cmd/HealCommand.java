// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;

import org.bukkit.potion.PotionEffect;
import org.bukkit.entity.Player;

public class HealCommand extends PlayerCommand
{
    public HealCommand() {
        super("heal", "Uleczanie graczy", "/heal [gracz]", "core.cmd.heal", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            p.setFireTicks(0);
            p.setHealth(p.getMaxHealth());
            p.setFoodLevel(20);
            for (final PotionEffect effect : p.getActivePotionEffects()) {
                p.removePotionEffect(effect.getType());
            }
            return ChatUtil.sendMessage((CommandSender)p, "&7Zostales uleczony");
        }
        if (!p.hasPermission("core.cmd.admin")) {
            return ChatUtil.sendMessage((CommandSender)p, "&cNie ma takiej komendy wpisz /pomoc aby dowiedziec sie wiecej");
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Gracz jest offline");
        }
        o.setFireTicks(0);
        o.setHealth(p.getMaxHealth());
        o.setFoodLevel(20);
        for (final PotionEffect effect2 : o.getActivePotionEffects()) {
            o.removePotionEffect(effect2.getType());
        }
        ChatUtil.sendMessage((CommandSender)o, "&7Zostales uleczony przez &9" + p.getName());
        return ChatUtil.sendMessage((CommandSender)p, "&7Uleczyles gracza &9" + o.getName());
    }
}
