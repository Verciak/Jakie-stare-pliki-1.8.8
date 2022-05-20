// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.command.CommandSender;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.managers.EnchantManager;
import pl.virtual.api.utils.ChatUtil;
import org.bukkit.entity.Player;

public class EnchantCommand extends PlayerCommand
{
    public EnchantCommand() {
        super("enchant", "nadawanie zaklec przedmiotom", "/enchant <zaklecie> [poziom]", "core.cmd.enchant", new String[0]);
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final ItemStack item = p.getItemInHand();
        final String enchantmentName = args[0];
        final Enchantment enchant = EnchantManager.get(enchantmentName);
        if (enchant == null) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie znaleziono podanego enchantu");
        }
		if (!ChatUtil.isInteger(args[1])) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7To nie jest liczba");
		}
        int level = enchant.getMaxLevel();
        if (args.length == 2) {
            level = Integer.parseInt(args[1]);
        }
        item.addUnsafeEnchantment(enchant, level);
        return ChatUtil.sendMessage((CommandSender)p, "&7Zaklecie &9" + enchant.getName().toLowerCase().replace("_", " ") + " &7zostalo dodane do przedmiotu w Twojej rece");
    }
}
