package pl.virtual.api.commands.cmd;

import org.bukkit.enchantments.Enchantment;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.*;
import pl.virtual.api.utils.*;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Material;

public class ZwojCommand extends PlayerCommand
{
    public ZwojCommand() {
        super("voucher", "voucher", "/voucher <vip/svip/vip3d/svip3d/turbodrop/turboexp> <ilosc>", "core.cmd.voucher", new String[] { "vo" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        final int size = Integer.parseInt(args[1]);
        final ItemBuilder svip3d = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA SVIP'A 3d")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder vip3d = new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA VIP'A 3d")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder vip = new ItemBuilder(Material.PAPER, size).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA VIP'A")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder svip = new ItemBuilder(Material.PAPER, size).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA SVIP'A")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder dark = new ItemBuilder(Material.PAPER, size).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA SPONSORA'A")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac range")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder mvip = new ItemBuilder(Material.PAPER, size).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA TURBODROP 15m")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac boosta")).addLore(ChatUtil.fixColor(""));
        final ItemBuilder exp = new ItemBuilder(Material.PAPER, size).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA TURBOEXP 15m")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac boosta")).addLore(ChatUtil.fixColor(""));
        if (args[0].equalsIgnoreCase("vip")) {
            ChatUtil.giveItems(p, vip.build());
            ChatUtil.sendMessage((CommandSender)p, "&7Otrzymales voucher &8(&fVIP&8)");
        }
        if (args[0].equalsIgnoreCase("vip3d")) {
            ChatUtil.giveItems(p, vip3d.build());
            ChatUtil.sendMessage((CommandSender)p, "&7Otrzymales voucher &8(&fVIP 3d&8)");
        }
        if (args[0].equalsIgnoreCase("svip3d")) {
            ChatUtil.giveItems(p, svip3d.build());
            ChatUtil.sendMessage((CommandSender)p, "&7Otrzymales voucher &8(&fVIP 3d&8)");
        }
        if (args[0].equalsIgnoreCase("svip")) {
            ChatUtil.giveItems(p, svip.build());
            ChatUtil.sendMessage((CommandSender)p, "&7Otrzymales voucher &8(&fSVIP&8)");
        }
        if (args[0].equalsIgnoreCase("dark")) {
            ChatUtil.giveItems(p, dark.build());
            ChatUtil.sendMessage((CommandSender)p, "&7Otrzymales voucher &8(&fSPONSOR&8)");
        }
        if (args[0].equalsIgnoreCase("turbodrop")) {
            ChatUtil.giveItems(p, mvip.build());
            ChatUtil.sendMessage((CommandSender)p, "&7Otrzymales voucher &8(&fTURBODROP 15m&8)");
        }
        if (args[0].equalsIgnoreCase("turboexp")) {
            ChatUtil.giveItems(p, exp.build());
            ChatUtil.sendMessage((CommandSender)p, "&7Otrzymales voucher &8(&fTURBOEXP 15m&8)");
        }
        return false;
    }
}

