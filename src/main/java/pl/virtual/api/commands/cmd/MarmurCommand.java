// 
// Decompiled by Procyon v0.5.30
// 

package pl.virtual.api.commands.cmd;

import org.bukkit.enchantments.Enchantment;

import pl.virtual.api.API.Lang;
import pl.virtual.api.commands.PlayerCommand;
import pl.virtual.api.utils.ChatUtil;
import pl.virtual.api.utils.ItemBuilder;
import pl.virtual.api.utils.ItemUtil;
import pl.virtual.api.utils.RandomUtil;

import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.Material;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;
import java.util.List;

public class MarmurCommand extends PlayerCommand
{
    public static List<ItemStack> drops;
    
    static {
        (MarmurCommand.drops = new ArrayList<ItemStack>()).add(new ItemStack(Material.BOOK, 12));
        MarmurCommand.drops.add(new ItemStack(Material.BOOK, 3, (short)1));
        MarmurCommand.drops.add(new ItemStack(Material.BOOK, 16));
        MarmurCommand.drops.add(new ItemStack(Material.FEATHER, 8));
        MarmurCommand.drops.add(new ItemStack(Material.FEATHER, 4));
        MarmurCommand.drops.add(new ItemStack(Material.SLIME_BALL, 4));
        MarmurCommand.drops.add(new ItemStack(Material.SLIME_BALL, 8));
        MarmurCommand.drops.add(new ItemStack(Material.TNT, 16));
        MarmurCommand.drops.add(new ItemStack(Material.TNT, 32));
        MarmurCommand.drops.add(new ItemStack(Material.ENDER_PEARL, 3));
        MarmurCommand.drops.add(new ItemStack(Material.ENDER_PEARL, 1));
        MarmurCommand.drops.add(new ItemStack(Material.DIAMOND, 16));
        MarmurCommand.drops.add(new ItemStack(Material.DIAMOND, 32));
        MarmurCommand.drops.add(new ItemStack(Material.GOLD_INGOT, 16));
        MarmurCommand.drops.add(new ItemStack(Material.GOLD_INGOT, 8));
        MarmurCommand.drops.add(new ItemStack(Material.EMERALD, 8));
        MarmurCommand.drops.add(new ItemStack(Material.EMERALD, 16));
        MarmurCommand.drops.add(new ItemStack(Material.OBSIDIAN, 16));
        MarmurCommand.drops.add(new ItemStack(Material.OBSIDIAN, 32));
        MarmurCommand.drops.add(new ItemStack(Material.APPLE, 16));
        MarmurCommand.drops.add(new ItemStack(Material.APPLE, 32));
        MarmurCommand.drops.add(new ItemStack(Material.WATER_BUCKET, 1));
        MarmurCommand.drops.add(new ItemStack(Material.LAVA_BUCKET, 1));
        MarmurCommand.drops.add(new ItemStack(Material.STONE, 32));
        MarmurCommand.drops.add(new ItemStack(Material.STONE, 64));
        MarmurCommand.drops.add(new ItemStack(Material.ARROW, 8));
        MarmurCommand.drops.add(new ItemStack(Material.ARROW, 8));
        MarmurCommand.drops.add(new ItemStack(Material.ARROW, 8));
        MarmurCommand.drops.add(new ItemStack(Material.ARROW, 16));
        MarmurCommand.drops.add(new ItemStack(Material.ARROW, 16));
        MarmurCommand.drops.add(new ItemBuilder(Material.PAPER, 1).addEnchantment(Enchantment.DURABILITY, 10).setTitle(ChatUtil.fixColor("&4&lVOUCHER NA TURBOEXP 15m")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7Kliknij aby otrzymac boosta")).addLore(ChatUtil.fixColor("")).build());
    }
    
    public MarmurCommand() {
        super("marmur", "Marmur", "/marmur", "core.cmd.user", new String[] { "cobblex", "cx" });
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length != 0) {
            return ChatUtil.sendMessage((CommandSender)p, Lang.USE(this.getUsage()));
        }
        if (!ItemUtil.checkItems(p, "COBBLESTONE:0-64:COBBLESTONE", 9)) {
            return ChatUtil.sendMessage((CommandSender)p, "&9&lERROR: &7Nie masz 9x64 cobblestone");
        }
        final int r = RandomUtil.getRandInt(1, 3);
        final ItemStack cx = new ItemBuilder(Material.MOSSY_STONE_BRICKS, r).setTitle(ChatUtil.fixColor("&7&k:&f&k:&7&k:&6&l&l &f&lCobbleX &7&k:&f&k:&7&k:&6&l&l")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8» &7Jest to magiczna skala")).addLore(ChatUtil.fixColor("&8» &7Ktora skrywa w sobie cenne itemy")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("             &f&lAby otworzyc, kliknij PPM             ")).addLore(ChatUtil.fixColor("")).build();
        ChatUtil.giveItems(p, cx);
        ItemUtil.removeItems(p, "COBBLESTONE:0-64:COBBLESTONE", 9);
        p.updateInventory();
        return ChatUtil.sendMessage((CommandSender)p, "&a&lSukces &7Stowrzyles CobbleX");
    }
}
